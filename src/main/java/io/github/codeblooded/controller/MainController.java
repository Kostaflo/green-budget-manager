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
    setupTableColumns();
    setupButtonAction();
  }

  private void setupTableColumns() {
    // Configure the columns to use the appropriate getter methods from BudgetItem
    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    agencyColumn.setCellValueFactory(new PropertyValueFactory<>("agency"));
    amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
    percentageColumn.setCellValueFactory(new PropertyValueFactory<>("percentage"));
  }

  private void setupButtonAction() {
    showButton.setOnAction(event -> loadBudgetData());
  }

  private void loadBudgetData() {
    // Clear existing data
    budgetData.clear();

    // Load data from database
    budgetData.addAll(database.getAllBudgets());

    // Set the data to table
    tableView.setItems(budgetData);

    // Make table visible if it wasn't already
    tableView.setVisible(true);
  }

  // Optional: Method to refresh data
  public void refreshData() {
    loadBudgetData();
  }
}
