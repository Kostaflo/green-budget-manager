package io.github.codeblooded.database;

import io.github.codeblooded.model.GreenBudgetTag;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Κλάση διαχείρισης της βάσης δεδομένων (Database Access Object).
 *
 * <p>Είναι υπεύθυνη για τη σύνδεση με την ενσωματωμένη βάση SQLite και την εκτέλεση SQL ερωτημάτων.
 * Μετατρέπει τα αποτελέσματα της βάσης (ResultSet) σε αντικείμενα της Java ({@link GreenBudgetTag})
 * για χρήση από την εφαρμογή.
 *
 * @author CodeBlooded Team
 * @version 1.0
 */
public class Database {

  /** Το URL σύνδεσης JDBC για τη βάση δεδομένων SQLite. */
  public static final String DB_URL = "jdbc:sqlite:budget.db";

  /**
   * Ανακτά όλα τα δεδομένα του Πράσινου Προϋπολογισμού.
   *
   * <p>Εκτελεί ένα σύνθετο SQL ερώτημα που ενώνει (JOIN) τους πίνακες:
   *
   * <ul>
   *   <li><b>Programs:</b> Βασικά στοιχεία προγραμμάτων και Tags.
   *   <li><b>Budget:</b> Οικονομικά στοιχεία (Ποσά).
   *   <li><b>Ministries:</b> Ονόματα Υπουργείων/Φορέων.
   * </ul>
   *
   * @return Μια λίστα με αντικείμενα {@link GreenBudgetTag} που περιέχουν τα συνδυασμένα δεδομένα.
   */
  public List<GreenBudgetTag> getAllGreenBudgetTags() {
    List<GreenBudgetTag> tags = new ArrayList<>();

    // SQL Query με JOINs για ανάκτηση πλήρους πληροφορίας
    String sql =
        "SELECT p.program_id, p.program_name, m.ministry_name, b.poso, p.green_tag, "
            + "p.tag_klhmatikh_meivsh, p.tag_klhmatikh_prosarmofh, p.tag_ydatina, "
            + "p.tag_kyklikh_oikonomia, p.tag_rypansh_elegxos, p.tag_biopoikilothta_prostasla "
            + "FROM Programs p "
            + "JOIN Budget b ON p.program_id = b.program_id "
            + "LEFT JOIN Ministries m ON p.ministry_id = m.ministry_id";

    // Χρήση try-with-resources για αυτόματο κλείσιμο της σύνδεσης
    try (Connection conn = DriverManager.getConnection(DB_URL)) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        // Mapping: Μετατροπή της γραμμής του πίνακα σε αντικείμενο Java
        GreenBudgetTag tag =
            new GreenBudgetTag(
                rs.getInt("program_id"),
                rs.getString("program_name"),
                rs.getString("ministry_name"),
                rs.getDouble("poso"),
                rs.getString("green_tag"),
                rs.getString("tag_klhmatikh_meivsh"),
                rs.getString("tag_klhmatikh_prosarmofh"),
                rs.getString("tag_ydatina"),
                rs.getString("tag_kyklikh_oikonomia"),
                rs.getString("tag_rypansh_elegxos"),
                rs.getString("tag_biopoikilothta_prostasla"));
        tags.add(tag);
      }
      System.out.println("Loaded " + tags.size() + " programs from database.");

    } catch (SQLException e) {
      System.err.println("Database Error: " + e.getMessage());
      e.printStackTrace();
    }
    return tags;
  }
}
