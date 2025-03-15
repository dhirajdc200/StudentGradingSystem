package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

public class DashboardController {

    @FXML
    private PieChart gradePieChart;

    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu, helpMenu;
    @FXML
    private MenuItem exitMenuItem, aboutMenuItem;

    @FXML
    private TextField studentNameField, subjectField, gradeField;
    @FXML
    private ListView<String> studentListView;
    @FXML
    private Button addStudentButton, addGradeButton, deleteStudentButton;
    @FXML
    private TableView<Grade> gradeTableView;
    @FXML
    private TableColumn<Grade, String> subjectColumn;
    @FXML
    private TableColumn<Grade, Double> gradeColumn;
    @FXML
    private BarChart<String, Number> gradeChart;

    private ObservableList<Grade> gradeList = FXCollections.observableArrayList();
    private ObservableList<String> students = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        loadStudents();

        // lambda expressions
        // Set button actions
        addStudentButton.setOnAction(event -> addStudent());
        addGradeButton.setOnAction(event -> addGrade());
        deleteStudentButton.setOnAction(event -> deleteStudent());

        //  Handle student selection
        studentListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        loadGradesForStudent(newVal);
                    }
                });

        // Menu actions
        exitMenuItem.setOnAction(event -> exitApplication());
        aboutMenuItem.setOnAction(event -> showAboutDialog());

        // Set initial data in the TableView
        gradeTableView.setItems(gradeList);
    }

    private void loadStudents() {
        students.addAll("Dhiraj", "Ram", "Shyam");
        studentListView.setItems(students);
    }

    private void addStudent() {
        String studentName = studentNameField.getText().trim();
        if (!studentName.isEmpty()) {
            students.add(studentName);
            studentNameField.clear();
        }
    }

    private void loadGradesForStudent(String student) {
        gradeList.clear();
        gradeList.add(new Grade("Math", 85));
        gradeList.add(new Grade("Science", 90));
        gradeTableView.setItems(gradeList);
        loadGradeChart();
        loadGradePieChart();
    }

    private void loadGradeChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Grades");

        gradeChart.getData().clear();

        for (Grade grade : gradeList) {
            series.getData().add(new XYChart.Data<>(grade.getSubject(), grade.getGrade()));
        }

        gradeChart.getData().add(series);
    }

    private void addGrade() {
        String subject = subjectField.getText().trim();
        String gradeText = gradeField.getText().trim();

        if (!subject.isEmpty() && !gradeText.isEmpty()) {
            try {
                double grade = Double.parseDouble(gradeText);
                Grade newGrade = new Grade(subject, grade);

                gradeList.add(newGrade);
                gradeTableView.setItems(gradeList);
                gradeTableView.refresh(); // Refresh UI

                loadGradeChart();
                loadGradePieChart();

                subjectField.clear();
                gradeField.clear();
            } catch (NumberFormatException e) {
                showError("Invalid grade input. Please enter a number.");
            }
        } else {
            showError("Please enter both subject and grade.");
        }
    }

    private void loadGradePieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Grade grade : gradeList) {
            pieChartData.add(new PieChart.Data(grade.getSubject(), grade.getGrade()));
        }

        gradePieChart.setData(pieChartData);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void deleteStudent() {
        String selectedStudent = studentListView.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            students.remove(selectedStudent);
        } else {
            showError("No student selected for deletion.");
        }
    }

    private void exitApplication() {
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.close();
    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Student Grading System");
        alert.setContentText("This application helps manage student grades using JavaFX.");
        alert.showAndWait();
    }
}
