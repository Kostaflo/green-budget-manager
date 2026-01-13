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

/**
 * Ο κεντρικός Controller για την οθόνη Ανάλυσης και Διαχείρισης Δεδομένων.
 *
 * <p>Αυτή η κλάση είναι υπεύθυνη για τη σύνδεση της λογικής της εφαρμογής με το γραφικό περιβάλλον
 * (View). Υλοποιεί τις εξής βασικές λειτουργίες:
 *
 * <ul>
 *   <li><b>Προβολή Δεδομένων:</b> Εμφάνιση των εγγραφών του προϋπολογισμού σε πίνακα (TableView).
 *   <li><b>Ζωντανή Αναζήτηση:</b> Φιλτράρισμα δεδομένων με βάση το Όνομα Προγράμματος και το
 *       Υπουργείο.
 *   <li><b>Οπτικοποίηση:</b> Αυτόματη ενημέρωση των γραφημάτων (Pie Chart, Bar Chart) βάσει των
 *       φιλτραρισμένων δεδομένων.
 *   <li><b>Επεξεργασία (Admin Only):</b> Δυνατότητα τροποποίησης ποσών απευθείας στον πίνακα για
 *       τους διαχειριστές.
 * </ul>
 *
 * @author CodeBlooded Team
 * @version 1.0
 */
public class GreenBudgetViewController {

  // --- FXML Στοιχεία Διασύνδεσης ---
  @FXML private TableView<GreenBudgetTag> budgetTable;
  @FXML private TableColumn<GreenBudgetTag, Integer> idColumn;
  @FXML private TableColumn<GreenBudgetTag, String> nameColumn;
  @FXML private TableColumn<GreenBudgetTag, String> ministryColumn;
  @FXML private TableColumn<GreenBudgetTag, Double> amountColumn;
  @FXML private TableColumn<GreenBudgetTag, String> tagColumn;

  // Στήλες για τα επιμέρους Tags (Περιβαλλοντικοί Στόχοι)
  @FXML private TableColumn<GreenBudgetTag, String> meivshColumn;
  @FXML private TableColumn<GreenBudgetTag, String> prosarmofhColumn;
  @FXML private TableColumn<GreenBudgetTag, String> ydatinaColumn;
  @FXML private TableColumn<GreenBudgetTag, String> kyklikhColumn;
  @FXML private TableColumn<GreenBudgetTag, String> rypanshColumn;
  @FXML private TableColumn<GreenBudgetTag, String> biopoikilothtaColumn;

  // Πεδία Αναζήτησης & Γραφήματα
  @FXML private TextField searchField;
  @FXML private TextField ministryFilter;
  @FXML private PieChart budgetPieChart;
  @FXML private BarChart<String, Double> budgetBarChart;
  @FXML private CategoryAxis xAxis;
  @FXML private NumberAxis yAxis;

  // --- Μοντέλα Δεδομένων & Υπηρεσίες ---
  private final UserService userService = UserService.getInstance();
  private final Database database = new Database();

  /** Η λίστα που περιέχει τα φιλτραρισμένα δεδομένα που βλέπει ο χρήστης. */
  private FilteredList<GreenBudgetTag> filteredData;

  /**
   * Αρχικοποιεί τον Controller. Καλείται αυτόματα από το JavaFX μετά τη φόρτωση του FXML.
   *
   * <p>Η μέθοδος αυτή εκτελεί τη βασική προετοιμασία:
   *
   * <ol>
   *   <li>Ρυθμίζει τις στήλες του πίνακα.
   *   <li>Φορτώνει τα δεδομένα από τη βάση.
   *   <li>Ενεργοποιεί τα φίλτρα αναζήτησης.
   *   <li>Ελέγχει αν ο χρήστης είναι Admin για να ενεργοποιήσει την επεξεργασία.
   *   <li>Ενημερώνει τα γραφήματα.
   * </ol>
   */
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

  /**
   * Συνδέει τις στήλες του πίνακα (TableColumn) με τις ιδιότητες (Properties) της κλάσης {@link
   * GreenBudgetTag}.
   */
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

  /**
   * Ανακτά τα δεδομένα από τη βάση δεδομένων και τα προετοιμάζει για τον πίνακα.
   *
   * <p>Δημιουργεί μια {@link FilteredList} για να επιτρέψει την αναζήτηση και μια {@link
   * SortedList} για να επιτρέψει την ταξινόμηση κάνοντας κλικ στις επικεφαλίδες.
   */
  private void loadData() {
    List<GreenBudgetTag> rawData = database.getAllGreenBudgetTags();
    ObservableList<GreenBudgetTag> masterData = FXCollections.observableArrayList(rawData);

    // Αρχικά το φίλτρο είναι true (δείχνει τα πάντα)
    filteredData = new FilteredList<>(masterData, p -> true);

    // Σύνδεση με SortedList για λειτουργικότητα ταξινόμησης
    SortedList<GreenBudgetTag> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(budgetTable.comparatorProperty());
    budgetTable.setItems(sortedData);
  }

  /**
   * Προσθέτει Listeners στα πεδία αναζήτησης για δυναμικό φιλτράρισμα. Καλείται η {@link
   * #updatePredicate()} κάθε φορά που αλλάζει το κείμενο.
   */
  private void setupFilters() {
    if (searchField != null) {
      searchField.textProperty().addListener((obs, old, newValue) -> updatePredicate());
    }
    if (ministryFilter != null) {
      ministryFilter.textProperty().addListener((obs, old, newValue) -> updatePredicate());
    }
  }

  /**
   * Ενημερώνει τα κριτήρια φιλτραρίσματος (Predicate) της λίστας.
   *
   * <p>Ελέγχει αν το όνομα του προγράμματος Ή το όνομα του υπουργείου περιέχουν τους όρους
   * αναζήτησης. Στη συνέχεια καλεί την ενημέρωση των γραφημάτων.
   */
  private void updatePredicate() {
    String nameSearch = (searchField.getText() != null) ? searchField.getText().toLowerCase() : "";
    String minSearch =
        (ministryFilter.getText() != null) ? ministryFilter.getText().toLowerCase() : "";

    filteredData.setPredicate(
        item -> {
          String pName = (item.getProgramName() != null) ? item.getProgramName().toLowerCase() : "";
          String mName =
              (item.getOwningEntityName() != null) ? item.getOwningEntityName().toLowerCase() : "";

          // Λογική AND: Πρέπει να ταιριάζει και στα δύο (αν έχουν κείμενο)
          return pName.contains(nameSearch) && mName.contains(minSearch);
        });

    // Τα γραφήματα πρέπει να ενημερωθούν για να δείχνουν ΜΟΝΟ τα φιλτραρισμένα
    updateCharts();
  }

  /**
   * Ενεργοποιεί τη δυνατότητα επεξεργασίας του πίνακα (Editable TableView).
   *
   * <p>Αυτή η μέθοδος καλείται μόνο αν ο χρήστης είναι Admin. Επιτρέπει την αλλαγή του Ποσού
   * (Amount) με διπλό κλικ στο κελί.
   */
  private void enableAdminEdit() {
    budgetTable.setEditable(true);
    amountColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

    // Όταν ο χρήστης πατήσει Enter μετά την αλλαγή ποσού:
    amountColumn.setOnEditCommit(
        event -> {
          GreenBudgetTag tag = event.getRowValue();
          tag.setPoso(event.getNewValue());

          // TODO: Προσθήκη κλήσης database.updateBudget(...) για μόνιμη αποθήκευση

          // Ενημέρωση γραφημάτων με τα νέα ποσά
          updateCharts();
        });
  }

  /**
   * Υπολογίζει και ενημερώνει τα δεδομένα των γραφημάτων (Pie & Bar Chart).
   *
   * <p>Χρησιμοποιεί τα δεδομένα της {@code filteredData}, ώστε τα γραφήματα να αντικατοπτρίζουν
   * πάντα αυτό που βλέπει ο χρήστης στον πίνακα.
   */
  private void updateCharts() {
    // --- Pie Chart Logic (Κατανομή ανά Green Tag) ---
    Map<String, Double> pieSums = new HashMap<>();

    // --- Bar Chart Logic (Θετικός Αντίκτυπος ανά Στόχο) ---
    double sMeiwsh = 0, sPros = 0, sYdat = 0, sKyk = 0, sRyp = 0, sBio = 0;

    for (GreenBudgetTag item : filteredData) {
      // Άθροιση για Pie Chart
      String tag = item.getGreenTag();
      pieSums.put(tag, pieSums.getOrDefault(tag, 0.0) + item.getPoso());

      // Άθροιση για Bar Chart (Μετατροπή σε εκατομμύρια € για ευκρίνεια)
      double mEuro = item.getPoso() / 1_000_000.0;

      if ("Θ".equals(item.getTagKlhmatikhMeiwsh())) sMeiwsh += mEuro;
      if ("Θ".equals(item.getTagKlhmatikhProsarmogh())) sPros += mEuro;
      if ("Θ".equals(item.getTagYdatina())) sYdat += mEuro;
      if ("Θ".equals(item.getTagKyklikhOikonomia())) sKyk += mEuro;
      if ("Θ".equals(item.getTagRypanshElegxos())) sRyp += mEuro;
      if ("Θ".equals(item.getTagBiopoikilothtaProstasla())) sBio += mEuro;
    }

    // Ενημέρωση Pie Chart
    ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
    pieSums.forEach((k, v) -> pieData.add(new PieChart.Data(k, v)));
    budgetPieChart.setData(pieData);

    // Ενημέρωση Bar Chart
    XYChart.Series<String, Double> series = new XYChart.Series<>();
    series.setName("Ποσό (M€)"); // Όνομα σειράς δεδομένων
    series.getData().add(new XYChart.Data<>("Μείωση", sMeiwsh));
    series.getData().add(new XYChart.Data<>("Προσαρμ.", sPros));
    series.getData().add(new XYChart.Data<>("Υδάτινα", sYdat));
    series.getData().add(new XYChart.Data<>("Κυκλική", sKyk));
    series.getData().add(new XYChart.Data<>("Ρύπανση", sRyp));
    series.getData().add(new XYChart.Data<>("Βιοποικ.", sBio));

    budgetBarChart.getData().setAll(series);
  }

  /**
   * Χειρίζεται την ενέργεια του κουμπιού "Επιστροφή".
   *
   * <p>Φορτώνει την οθόνη {@code DashboardLayout.fxml} και επιστρέφει τον χρήστη στο κεντρικό
   * μενού.
   */
  @FXML
  private void handleBack() {
    try {
      FXMLLoader loader =
          new FXMLLoader(
              getClass().getResource("/io/github/codeblooded/view/DashboardLayout.fxml"));
      Parent root = loader.load();

      // Παίρνουμε το stage από τον πίνακα και αλλάζουμε σκηνή
      Stage stage = (Stage) budgetTable.getScene().getWindow();

      // Προσθήκη CSS (αν χρειάζεται να ξαναφορτωθεί)
      Scene scene = new Scene(root, 1200, 800);
      String css = getClass().getResource("/io/github/codeblooded/view/style.css").toExternalForm();
      scene.getStylesheets().add(css);

      stage.setScene(scene);
      stage.centerOnScreen();

    } catch (IOException e) {
      e.printStackTrace();
      System.err.println("CRITICAL: Failed to load DashboardLayout.fxml");
    }
  }
}
