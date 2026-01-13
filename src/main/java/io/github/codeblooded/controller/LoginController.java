package io.github.codeblooded.controller;

import io.github.codeblooded.model.User.Role; // Σημαντικό import!
import io.github.codeblooded.service.UserService;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Ελέγχει τη διαδικασία εισόδου (Login) στην εφαρμογή.
 *
 * <p>Η κλάση αυτή διαχειρίζεται την αυθεντικοποίηση του χρήστη, αναθέτοντας τον κατάλληλο ρόλο
 * (Admin ή Guest) μέσω του {@link UserService}.
 *
 * @author CodeBlooded Team
 * @version 1.0
 */
public class LoginController {

  @FXML private Button adminButton;
  @FXML private Button guestButton;

  private final UserService userService = UserService.getInstance();

  /**
   * Χειρίζεται την είσοδο ως Διαχειριστής (Admin).
   *
   * <p>Ορίζει τον ρόλο του χρήστη σε {@link Role#ADMIN} και φορτώνει το Dashboard.
   */
  @FXML
  private void handleAdminLogin() {
    System.out.println("Logging in as Admin...");

    // Χρησιμοποιούμε την ΥΠΑΡΧΟΥΣΑ μέθοδο login(Role role)
    // Δεν χρειάζεται έλεγχος κωδικού, το κουμπί είναι αρκετό
    userService.login(Role.ADMIN);

    loadDashboard();
  }

  /**
   * Χειρίζεται την είσοδο ως Επισκέπτης (Guest).
   *
   * <p>Ορίζει τον ρόλο του χρήστη σε {@link Role#GUEST} και φορτώνει το Dashboard.
   */
  @FXML
  private void handleGuestLogin() {
    System.out.println("Logging in as Guest...");

    // Ομοίως για τον Guest
    userService.login(Role.GUEST);

    loadDashboard();
  }

  /**
   * Φορτώνει την επόμενη σκηνή (DashboardLayout.fxml).
   *
   * <p>Ρυθμίζει το μέγεθος του παραθύρου σε 1200x800 και το κεντράρει στην οθόνη.
   */
  private void loadDashboard() {
    try {
      FXMLLoader loader =
          new FXMLLoader(
              getClass().getResource("/io/github/codeblooded/view/DashboardLayout.fxml"));
      Parent root = loader.load();

      // Παίρνουμε το παράθυρο από όποιο κουμπί πατήθηκε
      Stage stage =
          (Stage)
              (adminButton.getScene() != null
                  ? adminButton.getScene().getWindow()
                  : guestButton.getScene().getWindow());

      stage.setScene(new Scene(root, 1200, 800));
      stage.setResizable(true);
      stage.centerOnScreen();

    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("CRITICAL: Failed to load DashboardLayout.fxml");
    }
  }
}
