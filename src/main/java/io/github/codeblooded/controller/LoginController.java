package io.github.codeblooded.controller;

import io.github.codeblooded.model.User.Role;
import io.github.codeblooded.service.UserService;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {

  @FXML private Button adminButton;
  @FXML private Button guestButton;

  private final UserService userService = UserService.getInstance();

  @FXML
  public void initialize() {
    // Εδώ μπορούμε να αρχικοποιήσουμε στυλ αν χρειαστεί
  }

  @FXML
  private void handleAdminLogin() {
    enterApp(Role.ADMIN);
  }

  @FXML
  private void handleGuestLogin() {
    enterApp(Role.GUEST);
  }

  /**
   * Κοινή μέθοδος για την είσοδο στην εφαρμογή. 1. Ενημερώνει το Role στο UserService. 2. Φορτώνει
   * το RootLayout (MainDashboard).
   */
  private void enterApp(Role role) {
    // Ενημέρωση του Service
    userService.login(role);
    System.out.println("Logged in as: " + role);

    try {
      // Φόρτωση του MainDashboard (RootLayout.fxml)
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("/io/github/codeblooded/view/RootLayout.fxml"));

      // Δημιουργία MainDashboardController
      MainDashboardController mainDashboardController = new MainDashboardController();
      loader.setController(mainDashboardController);

      Parent root = loader.load();

      // Αλλαγή του Scene στο υπάρχον Stage
      Stage stage = (Stage) adminButton.getScene().getWindow();
      Scene scene = new Scene(root, 1200, 800);

      // CSS
      scene
          .getStylesheets()
          .add(getClass().getResource("/io/github/codeblooded/style/style.css").toExternalForm());

      stage.setScene(scene);
      stage.setTitle("Green Budget Manager - " + role);
      stage.show();

    } catch (IOException e) {
      System.err.println("Σφάλμα κατά τη φόρτωση του Main Dashboard: " + e.getMessage());
      e.printStackTrace();
    }
  }
}

