package io.github.codeblooded.controller;

import io.github.codeblooded.service.UserService;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController {

  @FXML private Label welcomeLabel; // Αντιστοιχεί στο fx:id="welcomeLabel"

  private final UserService userService = UserService.getInstance();

  @FXML
  public void initialize() {
    // Ενημέρωση μηνύματος καλωσορίσματος
    if (welcomeLabel != null) {
      if (userService.isAdmin()) {
        welcomeLabel.setText("Καλωσήρθατε, Διαχειριστή (Admin)");
      } else {
        welcomeLabel.setText("Καλωσήρθατε, Επισκέπτη (Guest)");
      }
    }
  }

  @FXML
  private void handleView() {
    try {
      System.out.println("Loading GreenBudgetView...");
      FXMLLoader loader =
          new FXMLLoader(
              getClass().getResource("/io/github/codeblooded/view/GreenBudgetView.fxml"));
      Parent root = loader.load();

      Stage stage = (Stage) welcomeLabel.getScene().getWindow();
      stage.setScene(new Scene(root, 1400, 900)); // Μεγαλώνουμε το παράθυρο
      stage.centerOnScreen();

    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("CRITICAL: Failed to load GreenBudgetView.fxml");
    }
  }

  @FXML
  private void handleLogout() {
    try {
      System.out.println("Logging out...");
      userService.logout(); // Καθαρισμός του χρήστη

      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("/io/github/codeblooded/view/UserLogin.fxml"));
      Parent root = loader.load();

      Stage stage = (Stage) welcomeLabel.getScene().getWindow();
      stage.setScene(new Scene(root));
      stage.centerOnScreen();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
