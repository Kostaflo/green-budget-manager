package io.github.codeblooded.controller;

import io.github.codeblooded.service.UserService;
import javafx.fxml.FXML;

public class DashboardController {

  private MainDashboardController mainController;
  private UserService userService;

  public void setMainController(MainDashboardController mainController) {
    this.mainController = mainController;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @FXML
  private void handleView() {
    mainController.loadView("/io/github/codeblooded/view/GreenBudgetView.fxml");
  }

  @FXML
  private void handleEdit() {
    // Future implementation for edit functionality
  }

  @FXML
  private void handleLogout() {
    mainController.logout();
  }
}
