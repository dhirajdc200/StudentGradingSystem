package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/login.fxml")));
        stage.setScene(new Scene(root));
        stage.setTitle("Student Grading System - Login");
        stage.show();
    }

    public static void changeScene(String fxmlFile, String title) throws Exception {
        Parent pane = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxmlFile)));
        primaryStage.getScene().setRoot(pane);
        primaryStage.setTitle(title);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
