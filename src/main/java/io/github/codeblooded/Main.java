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
      // 1. Φόρτωση της οθόνης Login κατά την εκκίνηση
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("/io/github/codeblooded/view/UserLogin.fxml"));
      Parent root = loader.load();

      // 2. Ρύθμιση του παραθύρου
      primaryStage.setTitle("Green Budget Manager 2025");
      primaryStage.setScene(new Scene(root));
      primaryStage.setResizable(false); // Το Login συνήθως δεν αλλάζει μέγεθος
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("CRITICAL ERROR: Could not load UserLogin.fxml");
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
