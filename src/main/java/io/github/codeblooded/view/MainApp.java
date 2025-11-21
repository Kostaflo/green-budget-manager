import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@SuppressWarnings({"deprecation", "unchecked"})
public class MainApp extends Application {

    private Stage primaryStage;
    private Scene menuScene;
    private Scene tableScene;

    // ----------------------------------------------
    // Data Model
    // ----------------------------------------------
    public static class RowData {
        private final StringProperty col1;
        private final StringProperty col2;
        private final StringProperty col3;
        private final StringProperty col4;

        public RowData(String c1, String c2, String c3, String c4) {
            this.col1 = new SimpleStringProperty(c1);
            this.col2 = new SimpleStringProperty(c2);
            this.col3 = new SimpleStringProperty(c3);
            this.col4 = new SimpleStringProperty(c4);
        }

        public String getCol1() { return col1.get(); }
        public String getCol2() { return col2.get(); }
        public String getCol3() { return col3.get(); }
        public String getCol4() { return col4.get(); }
    }

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;

        createMenuScene();
        createTableScene();

        primaryStage.setTitle("Green Budget Manager");
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    // ----------------------------------------------
    // MENU SCENE
    // ----------------------------------------------
    private void createMenuScene() {
        Label welcomeLabel = new Label("Welcome to Green Budget Manager");
        welcomeLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        Button goToTableBtn = new Button("View Current State Budget");
        goToTableBtn.setStyle("-fx-font-size: 18px; -fx-padding: 10 20;");

        goToTableBtn.setOnAction(e -> primaryStage.setScene(tableScene));

        VBox layout = new VBox(30, welcomeLabel, goToTableBtn);
        layout.setStyle("-fx-padding: 40;");
        layout.setAlignment(Pos.CENTER);

        menuScene = new Scene(layout, 600, 400);
    }

    // ----------------------------------------------
    // TABLE SCENE
    // ----------------------------------------------
    private void createTableScene() {
        TableView<RowData> table = new TableView<>();
        table.setStyle("-fx-font-size: 16px;");

        // ----------------------------------------------
        // Columns
        // ----------------------------------------------
        TableColumn<RowData, String> col1 = new TableColumn<>("ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("col1"));

        TableColumn<RowData, String> col2 = new TableColumn<>("Agency");
        col2.setCellValueFactory(new PropertyValueFactory<>("col2"));

        TableColumn<RowData, String> col3 = new TableColumn<>("Ammount");
        col3.setCellValueFactory(new PropertyValueFactory<>("col3"));

        TableColumn<RowData, String> col4 = new TableColumn<>("Percentage");
        col4.setCellValueFactory(new PropertyValueFactory<>("col4"));

        table.getColumns().addAll(col1, col2, col3, col4);

        // Auto resize
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Fill table with row data
        for (int i = 1; i <= 22; i++) {
            table.getItems().add(new RowData(
                "Category " + i,
                (i * 1000) + " €",
                "2025",
                "Description " + i
            ));
        }

        // ----------------------------------------------
        // Back Button
        // ----------------------------------------------
        Button backBtn = new Button("Go to Menu");
        backBtn.setStyle("-fx-font-size: 18px; -fx-padding: 8 20;");
        backBtn.setOnAction(e -> primaryStage.setScene(menuScene));

        VBox layout = new VBox(25, table, backBtn);
        layout.setStyle("-fx-padding: 30;");
        layout.setAlignment(Pos.CENTER);

        tableScene = new Scene(layout, 900, 600);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
