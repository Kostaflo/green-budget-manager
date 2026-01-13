package io.github.codeblooded;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Η κεντρική κλάση εκκίνησης της εφαρμογής Green Budget Manager.
 *
 * <p>Κληρονομεί από την κλάση {@link Application} του JavaFX και είναι υπεύθυνη για:
 *
 * <ul>
 *   <li>Την αρχικοποίηση του κύκλου ζωής (Lifecycle) της εφαρμογής.
 *   <li>Τη φόρτωση της πρώτης οθόνης (UserLogin.fxml).
 *   <li>Τον καθορισμό των βασικών ιδιοτήτων του παραθύρου.
 * </ul>
 *
 * @author CodeBlooded Team
 * @version 1.0
 */
public class Main extends Application {

  /**
   * Η μέθοδος εκκίνησης (Entry Point) της JavaFX εφαρμογής.
   *
   * <p>Καλείται αυτόματα από το σύστημα (μέσω της {@link #launch(String...)}). Φορτώνει το αρχείο
   * {@code UserLogin.fxml} και εμφανίζει το παράθυρο.
   *
   * @param primaryStage Το βασικό παράθυρο (Stage) της εφαρμογής.
   */
  @Override
  public void start(Stage primaryStage) {
    try {
      // 1. Φόρτωση της οθόνης Login κατά την εκκίνηση
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("/io/github/codeblooded/view/UserLogin.fxml"));
      Parent root = loader.load();

      // 2. Ρύθμιση του παραθύρου
      primaryStage.setTitle("Green Budget Manager");
      primaryStage.setScene(new Scene(root));
      primaryStage.setResizable(false); // Το Login συνήθως δεν αλλάζει μέγεθος
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("CRITICAL ERROR: Could not load UserLogin.fxml");
    }
  }

  /**
   * Η κύρια μέθοδος (Main Method) της Java.
   *
   * <p>Αποτελεί το σημείο εισόδου για την JVM και εκκινεί την JavaFX πλατφόρμα.
   *
   * @param args Τα ορίσματα γραμμής εντολών.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
