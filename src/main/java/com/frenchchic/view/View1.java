package com.frenchchic.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class View1 extends Application {
    Pane pane;
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(getScene());
        stage.show();
    }
    public View1() throws IOException {
        pane = FXMLLoader.load(getClass().getResource("/view/panier.fxml"));
    }
    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/panier.fxml"));
        return new Scene(fxmlLoader.load());
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
