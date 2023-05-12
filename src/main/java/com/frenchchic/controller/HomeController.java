package com.frenchchic.controller;

import com.frenchchic.view.Home;
import com.frenchchic.view.View1;
import com.frenchchic.view.View2;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane rootPane;

    @FXML
    void lanceWin1(ActionEvent event) throws Exception {
        View1 v = new View1();
        rootPane.getChildren().setAll(v.getPane());
    }

    @FXML
    void lanceWin2(ActionEvent event) throws Exception {
        View2 v = new View2();
        rootPane.getChildren().setAll(v.getPane());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}