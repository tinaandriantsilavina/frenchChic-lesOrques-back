package com.frenchchic.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Login extends Application {
    public static final String PSEUDO = "Admin";
    public static final String PASS = "123";
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/com/frenchchic/view/login.fxml").toURI().toURL());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("French Chic");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}