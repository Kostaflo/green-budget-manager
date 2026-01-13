package io.github.codeblooded.model;

/**
 * Data Transfer Object (DTO) που αντιπροσωπεύει μια εγγραφή του Πράσινου Προϋπολογισμού.
 *
 * <p>Η κλάση αυτή λειτουργεί ως "δοχείο" δεδομένων, συνδυάζοντας πληροφορίες από πολλαπλούς πίνακες
 * της βάσης (`Programs`, `Ministries`, `Budget`).
 *
 * <p>Χρησιμοποιείται κυρίως για την εμφάνιση των δεδομένων στον πίνακα (TableView) και για τους
 * υπολογισμούς των γραφημάτων.
 *
 * @author CodeBlooded Team
 * @version 1.0
 */
public class GreenBudgetTag {

  private int programId;
  private String programName;
  private String owningEntityName; // Από τον πίνακα Ministries
  private double poso; // Από τον πίνακα Budget
  private String greenTag; // Συνολική αποτίμηση (Θ, Α, ΜΠ, Ο)

  // Οι 6 περιβαλλοντικοί στόχοι (EU Taxonomy Tags)
  private String tagKlhmatikhMeiwsh;
  private String tagKlhmatikhProsarmogh;
  private String tagYdatina;
  private String tagKyklikhOikonomia;
  private String tagRypanshElegxos;
  private String tagBiopoikilothtaProstasla;

  /**
   * Κατασκευαστής που αρχικοποιεί όλα τα πεδία του DTO.
   *
   * @param programId Το μοναδικό ID του προγράμματος.
   * @param programName Ο τίτλος του προγράμματος.
   * @param owningEntityName Το όνομα του Υπουργείου ή Φορέα υλοποίησης.
   * @param poso Το ποσό του προϋπολογισμού (σε Ευρώ).
   * @param greenTag Ο συνολικός δείκτης περιβαλλοντικής διάστασης (π.χ. "Θ").
   * @param tagKlhmatikhMeiwsh Tag για τον στόχο: Μείωση Κλιματικής Αλλαγής.
   * @param tagKlhmatikhProsarmogh Tag για τον στόχο: Προσαρμογή στην Κλιματική Αλλαγή.
   * @param tagYdatina Tag για τον στόχο: Βιώσιμη Χρήση Υδάτινων Πόρων.
   * @param tagKyklikhOikonomia Tag για τον στόχο: Μετάβαση στην Κυκλική Οικονομία.
   * @param tagRypanshElegxos Tag για τον στόχο: Πρόληψη και Έλεγχος Ρύπανσης.
   * @param tagBiopoikilothtaProstasla Tag για τον στόχο: Προστασία Βιοποικιλότητας.
   */
  public GreenBudgetTag(
      int programId,
      String programName,
      String owningEntityName,
      double poso,
      String greenTag,
      String tagKlhmatikhMeiwsh,
      String tagKlhmatikhProsarmogh,
      String tagYdatina,
      String tagKyklikhOikonomia,
      String tagRypanshElegxos,
      String tagBiopoikilothtaProstasla) {
    this.programId = programId;
    this.programName = programName;
    this.owningEntityName = owningEntityName;
    this.poso = poso;
    this.greenTag = greenTag;
    this.tagKlhmatikhMeiwsh = tagKlhmatikhMeiwsh;
    this.tagKlhmatikhProsarmogh = tagKlhmatikhProsarmogh;
    this.tagYdatina = tagYdatina;
    this.tagKyklikhOikonomia = tagKyklikhOikonomia;
    this.tagRypanshElegxos = tagRypanshElegxos;
    this.tagBiopoikilothtaProstasla = tagBiopoikilothtaProstasla;
  }

  // --- Getters (Απαραίτητοι για το PropertyValueFactory του TableView) ---

  /**
   * Επιστρέφει το ID του προγράμματος.
   *
   * @return Το ID (ακέραιος).
   */
  public int getProgramId() {
    return programId;
  }

  /**
   * Επιστρέφει το όνομα του προγράμματος.
   *
   * @return Το όνομα (String).
   */
  public String getProgramName() {
    return programName;
  }

  /**
   * Επιστρέφει το όνομα του Υπουργείου/Φορέα.
   *
   * @return Το όνομα του φορέα.
   */
  public String getOwningEntityName() {
    return owningEntityName;
  }

  /**
   * Επιστρέφει το ποσό του προϋπολογισμού.
   *
   * @return Το ποσό σε double.
   */
  public double getPoso() {
    return poso;
  }

  /**
   * Επιστρέφει τον συνολικό περιβαλλοντικό δείκτη (Green Tag).
   *
   * @return Το Tag (π.χ. "Θ", "Α", "Ο", "ΜΠ").
   */
  public String getGreenTag() {
    return greenTag;
  }

  // --- Getters για τους 6 Περιβαλλοντικούς Στόχους ---

  public String getTagKlhmatikhMeiwsh() {
    return tagKlhmatikhMeiwsh;
  }

  public String getTagKlhmatikhProsarmogh() {
    return tagKlhmatikhProsarmogh;
  }

  public String getTagYdatina() {
    return tagYdatina;
  }

  public String getTagKyklikhOikonomia() {
    return tagKyklikhOikonomia;
  }

  public String getTagRypanshElegxos() {
    return tagRypanshElegxos;
  }

  public String getTagBiopoikilothtaProstasla() {
    return tagBiopoikilothtaProstasla;
  }

  // --- Setters (Χρησιμοποιούνται για την επεξεργασία από τον Admin) ---

  /**
   * Ενημερώνει το ποσό του προϋπολογισμού.
   *
   * <p>Χρησιμοποιείται όταν ο διαχειριστής κάνει αλλαγές στον πίνακα για σενάρια προσομοίωσης.
   *
   * @param poso Το νέο ποσό.
   */
  public void setPoso(double poso) {
    this.poso = poso;
  }

  /**
   * Ενημερώνει τον συνολικό δείκτη (Green Tag).
   *
   * @param greenTag Το νέο Tag.
   */
  public void setGreenTag(String greenTag) {
    this.greenTag = greenTag;
  }
}

