package io.github.codeblooded.database;

import static org.junit.jupiter.api.Assertions.*;

import io.github.codeblooded.model.GreenBudgetTag;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DatabaseTest {

  private Database database;

  @BeforeEach
  void setUp() {
    // Αρχικοποίηση πριν από κάθε test
    database = new Database();
  }

  @Test
  @DisplayName("Test αν η βάση επιστρέφει δεδομένα και αν το JOIN δουλεύει")
  void testGetAllGreenBudgetTags() {
    // Εκτέλεση της μεθόδου
    List<GreenBudgetTag> tags = database.getAllGreenBudgetTags();

    // 1. Έλεγχος αν η λίστα δημιουργήθηκε
    assertNotNull(tags, "Η λίστα δεν πρέπει να είναι null");

    // 2. Έλεγχος αν περιέχει δεδομένα (Με βάση το schema.sql περιμένουμε 108 εγγραφές)
    assertFalse(tags.isEmpty(), "Η λίστα δεν πρέπει να είναι άδεια");

    // Προαιρετικά: Έλεγχος ακριβούς αριθμού εγγραφών
    // assertEquals(108, tags.size(), "Θα έπρεπε να υπάρχουν 108 προγράμματα");

    // 3. Έλεγχος εγκυρότητας της πρώτης εγγραφής
    GreenBudgetTag firstItem = tags.get(0);

    assertNotNull(firstItem.getProgramName(), "Το όνομα του προγράμματος πρέπει να υπάρχει");
    assertTrue(firstItem.getPoso() >= 0, "Το ποσό πρέπει να είναι θετικός αριθμός");

    // Έλεγχος αν το JOIN με τα Ministries έφερε όνομα φορέα
    assertNotNull(
        firstItem.getOwningEntityName(), "Το όνομα του Υπουργείου (JOIN) δεν πρέπει να είναι null");

    System.out.println("Test Passed: Found " + tags.size() + " programs.");
    System.out.println(
        "Sample Data: " + firstItem.getProgramName() + " | Budget: " + firstItem.getPoso());
  }

  @Test
  @DisplayName("Test αν τα Green Tags διαβάζονται σωστά")
  void testGreenTagsMapping() {
    List<GreenBudgetTag> tags = database.getAllGreenBudgetTags();

    if (!tags.isEmpty()) {
      GreenBudgetTag sample = tags.get(0);
      // Ελέγχουμε αν το συνολικό green_tag είναι ένα από τα αποδεκτά (Θ, Α, Ο, ΜΠ, n/a)
      String tag = sample.getGreenTag();
      assertTrue(tag.matches("Θ|Α|Ο|ΜΠ|n/a"), "Το Green Tag '" + tag + "' δεν είναι έγκυρο");
    }
  }
}
