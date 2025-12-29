package io.github.codeblooded.controller;

import io.github.codeblooded.service.UserService;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainDashboardController {

  @FXML private StackPane mainPane;

  private UserService userService;

  @FXML
  public void initialize() {
    System.out.println("MainDashboardController initialized");
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
    loadDashboard();
  }

  private void loadDashboard() {
    try {
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("/io/github/codeblooded/view/DashboardView.fxml"));
      Parent dashboardView = loader.load();
      DashboardController dashboardController = loader.getController();
      dashboardController.setMainController(this);
      dashboardController.setUserService(this.userService);

      mainPane.getChildren().setAll(dashboardView);

    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Failed to load DashboardView.fxml");
    }
  }

  public void loadView(String fxmlPath) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
      Parent view = loader.load();
      Object controller = loader.getController();
      if (controller instanceof GreenBudgetController) {
        ((GreenBudgetController) controller).setUserService(this.userService);
      }

      mainPane.getChildren().setAll(view);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Failed to load " + fxmlPath);
    }
  }

  public void logout() {
    try {
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("/io/github/codeblooded/view/UserLogin.fxml"));
      Parent loginView = loader.load();
      Stage stage = (Stage) mainPane.getScene().getWindow();
      stage.setScene(new Scene(loginView));
      stage.centerOnScreen();
      UserLoginController loginController = loader.getController();
      loginController.setUserService(this.userService);
      System.out.println("Logged out successfully");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void handleExit() {
    System.exit(0);
  }
}
