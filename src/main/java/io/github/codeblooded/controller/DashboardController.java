package io.github.codeblooded.controller;

import javafx.fxml.FXML;

public class DashboardController {

  private MainDashboardController mainController;

  public void setMainController(MainDashboardController mainController) {
    this.mainController = mainController;
  }

  @FXML
  private void handleView() {
    mainController.loadView("/io/github/codeblooded/view/GreenBudgetView.fxml");
  }

  @FXML
  private void handleLogout() {
    mainController.logout();
  }
}
