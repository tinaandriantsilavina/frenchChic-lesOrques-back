package com.frenchchic.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class View2 extends Application {
    Pane pane ;
    public View2() throws IOException {
        pane = FXMLLoader.load(getClass().getResource("/view/view2.fxml"));
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(getScene());
        stage.show();
    }
    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/view2.fxml"));
        return new Scene(fxmlLoader.load());
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
