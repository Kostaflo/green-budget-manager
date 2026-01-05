package io.github.codeblooded.controller;

import io.github.codeblooded.database.Database;
import io.github.codeblooded.model.GreenBudgetTag;
import io.github.codeblooded.service.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class GreenBudgetViewController {

  @FXML private TableView<GreenBudgetTag> budgetTable;
  @FXML private TableColumn<GreenBudgetTag, Integer> idColumn;
  @FXML private TableColumn<GreenBudgetTag, String> nameColumn;
  @FXML private TableColumn<GreenBudgetTag, String> ministryColumn;
  @FXML private TableColumn<GreenBudgetTag, Double> amountColumn;
  @FXML private TableColumn<GreenBudgetTag, String> tagColumn;

  // Στήλες των 6 Στόχων
  @FXML private TableColumn<GreenBudgetTag, String> meivshColumn;
  @FXML private TableColumn<GreenBudgetTag, String> prosarmofhColumn;
  @FXML private TableColumn<GreenBudgetTag, String> ydatinaColumn;
  @FXML private TableColumn<GreenBudgetTag, String> kyklikhColumn;
  @FXML private TableColumn<GreenBudgetTag, String> rypanshColumn;
  @FXML private TableColumn<GreenBudgetTag, String> biopoikilothtaColumn;

  @FXML private TextField searchField;
  @FXML private TextField ministryFilter;

  @FXML private PieChart budgetPieChart;
  @FXML private BarChart<String, Double> budgetBarChart;
  @FXML private CategoryAxis xAxis;
  @FXML private NumberAxis yAxis;

  // ΑΦΑΙΡΕΣΑΜΕ ΤΟ: private MainDashboardController mainController;

  private final UserService userService = UserService.getInstance();
  private final Database database = new Database();
  private FilteredList<GreenBudgetTag> filteredData;

  @FXML
  public void initialize() {
    setupTableColumns();
    loadData();
    setupFilters();

    if (userService.isAdmin()) {
      enableAdminEdit();
    }

    updateCharts();
  }

  private void setupTableColumns() {
    idColumn.setCellValueFactory(new PropertyValueFactory<>("programId"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("programName"));
    ministryColumn.setCellValueFactory(new PropertyValueFactory<>("owningEntityName"));
    amountColumn.setCellValueFactory(new PropertyValueFactory<>("poso"));
    tagColumn.setCellValueFactory(new PropertyValueFactory<>("greenTag"));

    meivshColumn.setCellValueFactory(new PropertyValueFactory<>("tagKlhmatikhMeiwsh"));
    prosarmofhColumn.setCellValueFactory(new PropertyValueFactory<>("tagKlhmatikhProsarmogh"));
    ydatinaColumn.setCellValueFactory(new PropertyValueFactory<>("tagYdatina"));
    kyklikhColumn.setCellValueFactory(new PropertyValueFactory<>("tagKyklikhOikonomia"));
    rypanshColumn.setCellValueFactory(new PropertyValueFactory<>("tagRypanshElegxos"));
    biopoikilothtaColumn.setCellValueFactory(
        new PropertyValueFactory<>("tagBiopoikilothtaProstasla"));
  }

  private void loadData() {
    List<GreenBudgetTag> rawData = database.getAllGreenBudgetTags();
    ObservableList<GreenBudgetTag> masterData = FXCollections.observableArrayList(rawData);
    filteredData = new FilteredList<>(masterData, p -> true);

    SortedList<GreenBudgetTag> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(budgetTable.comparatorProperty());
    budgetTable.setItems(sortedData);
  }

  private void setupFilters() {
    // Χρήση Listeners για δυναμικό φιλτράρισμα
    if (searchField != null) {
      searchField.textProperty().addListener((obs, old, newValue) -> updatePredicate());
    }
    if (ministryFilter != null) {
      ministryFilter.textProperty().addListener((obs, old, newValue) -> updatePredicate());
    }
  }

  private void updatePredicate() {
    String nameSearch = (searchField.getText() != null) ? searchField.getText().toLowerCase() : "";
    String minSearch =
        (ministryFilter.getText() != null) ? ministryFilter.getText().toLowerCase() : "";

    filteredData.setPredicate(
        item -> {
          String pName = (item.getProgramName() != null) ? item.getProgramName().toLowerCase() : "";
          String mName =
              (item.getOwningEntityName() != null) ? item.getOwningEntityName().toLowerCase() : "";
          return pName.contains(nameSearch) && mName.contains(minSearch);
        });
    updateCharts();
  }

  private void enableAdminEdit() {
    budgetTable.setEditable(true);
    amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    amountColumn.setOnEditCommit(
        event -> {
          GreenBudgetTag tag = event.getRowValue();
          tag.setPoso(event.getNewValue());
          // Εδώ θα έμπαινε η ενημέρωση βάσης: database.updateBudget(...)
          updateCharts();
        });
  }

  private void updateCharts() {
    // Pie Chart Logic
    Map<String, Double> pieSums = new HashMap<>();
    // Bar Chart Logic
    double sMeiwsh = 0, sPros = 0, sYdat = 0, sKyk = 0, sRyp = 0, sBio = 0;

    for (GreenBudgetTag item : filteredData) {
      String tag = item.getGreenTag();
      pieSums.put(tag, pieSums.getOrDefault(tag, 0.0) + item.getPoso());

      double mEuro = item.getPoso() / 1_000_000.0;
      if ("Θ".equals(item.getTagKlhmatikhMeiwsh())) sMeiwsh += mEuro;
      if ("Θ".equals(item.getTagKlhmatikhProsarmogh())) sPros += mEuro;
      if ("Θ".equals(item.getTagYdatina())) sYdat += mEuro;
      if ("Θ".equals(item.getTagKyklikhOikonomia())) sKyk += mEuro;
      if ("Θ".equals(item.getTagRypanshElegxos())) sRyp += mEuro;
      if ("Θ".equals(item.getTagBiopoikilothtaProstasla())) sBio += mEuro;
    }

    ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
    pieSums.forEach((k, v) -> pieData.add(new PieChart.Data(k, v)));
    budgetPieChart.setData(pieData);

    XYChart.Series<String, Double> series = new XYChart.Series<>();
    series.getData().add(new XYChart.Data<>("Μείωση", sMeiwsh));
    series.getData().add(new XYChart.Data<>("Προσαρμ.", sPros));
    series.getData().add(new XYChart.Data<>("Υδάτινα", sYdat));
    series.getData().add(new XYChart.Data<>("Κυκλική", sKyk));
    series.getData().add(new XYChart.Data<>("Ρύπανση", sRyp));
    series.getData().add(new XYChart.Data<>("Βιοποικ.", sBio));
    budgetBarChart.getData().setAll(series);
  }

  // 🔥 ΑΥΤΟΝΟΜΗ ΜΕΘΟΔΟΣ BACK (Χωρίς MainDashboardController)
  @FXML
  private void handleBack() {
    try {
      FXMLLoader loader =
          new FXMLLoader(
              getClass().getResource("/io/github/codeblooded/view/DashboardLayout.fxml"));
      Parent root = loader.load();

      // Παίρνουμε το stage από τον πίνακα και αλλάζουμε σκηνή
      Stage stage = (Stage) budgetTable.getScene().getWindow();
      stage.setScene(new Scene(root, 1200, 800)); // Επιστροφή στο κανονικό μέγεθος
      stage.centerOnScreen();

    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("CRITICAL: Failed to load DashboardLayout.fxml");
    }
  }
}
