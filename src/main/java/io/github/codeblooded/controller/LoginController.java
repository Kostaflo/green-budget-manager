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

public class LoginController {

  @FXML private Button adminButton;
  @FXML private Button guestButton;

  private final UserService userService = UserService.getInstance();

  @FXML
  private void handleAdminLogin() {
    System.out.println("Logging in as Admin...");

    // Χρησιμοποιούμε την ΥΠΑΡΧΟΥΣΑ μέθοδο login(Role role)
    // Δεν χρειάζεται έλεγχος κωδικού, το κουμπί είναι αρκετό
    userService.login(Role.ADMIN);

    loadDashboard();
  }

  @FXML
  private void handleGuestLogin() {
    System.out.println("Logging in as Guest...");

    // Ομοίως για τον Guest
    userService.login(Role.GUEST);

    loadDashboard();
  }

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
