package io.github.codeblooded;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      System.out.println("Starting application...");

      // Load FXML with explicit path
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("/io/github/codeblooded/view/BudgetViewer.fxml"));
      Parent root = loader.load();

      primaryStage.setTitle("Greek Budget Viewer");
      primaryStage.setScene(new Scene(root, 800, 600));
      primaryStage.show();

      System.out.println("Application started successfully!");

    } catch (Exception e) {
      System.err.println("Failed to start application: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
