package io.github.codeblooded.controller;

import io.github.codeblooded.database.Database;
import io.github.codeblooded.model.BudgetItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {

  @FXML private TableView<BudgetItem> tableView;

  @FXML private TableColumn<BudgetItem, Integer> idColumn;

  @FXML private TableColumn<BudgetItem, String> agencyColumn;

  @FXML private TableColumn<BudgetItem, Integer> amountColumn;

  @FXML private TableColumn<BudgetItem, Double> percentageColumn;

  @FXML private Button showButton;

  private ObservableList<BudgetItem> budgetData = FXCollections.observableArrayList();
  private Database database = new Database();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    System.out.println("Controller initialized!");
    System.out.println("tableView: " + tableView);
    System.out.println("idColumn: " + idColumn);
    System.out.println("agencyColumn: " + agencyColumn);

    setupTableColumns();
    setupButtonAction();
  }

  private void setupTableColumns() {
    // Add null checks
    if (idColumn != null
        && agencyColumn != null
        && amountColumn != null
        && percentageColumn != null) {
      idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
      agencyColumn.setCellValueFactory(new PropertyValueFactory<>("agency"));
      amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
      percentageColumn.setCellValueFactory(new PropertyValueFactory<>("percentage"));
      System.out.println("Table columns configured successfully");
    } else {
      System.err.println("One or more table columns are null!");
      System.err.println("idColumn: " + idColumn);
      System.err.println("agencyColumn: " + agencyColumn);
      System.err.println("amountColumn: " + amountColumn);
      System.err.println("percentageColumn: " + percentageColumn);
    }
  }

  private void setupButtonAction() {
    if (showButton != null) {
      showButton.setOnAction(event -> loadBudgetData());
      System.out.println("Button action configured");
    } else {
      System.err.println("showButton is null!");
    }
  }

  private void loadBudgetData() {
    try {
      budgetData.clear();
      budgetData.addAll(database.getAllBudgets());
      tableView.setItems(budgetData);
      System.out.println("Loaded " + budgetData.size() + " items");
    } catch (Exception e) {
      System.err.println("Error loading data: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
