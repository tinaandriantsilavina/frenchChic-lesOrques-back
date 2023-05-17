package com.frenchchic.view;

import com.frenchchic.controller.Session;
import com.frenchchic.model.Client;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VueJetable extends Application implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Stage primaryStage;

    private Client client;


    public void setClient(Client client) {
        this.client = client;
    }
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    public void startVueJetable(Stage stage) throws IOException, Exception {
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/viewJetable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("French chic");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void afficherEcranPanier(ActionEvent event) throws Exception {
        rootPane.getChildren().setAll( (AnchorPane) FXMLLoader.load(getClass().getResource("/view/panier.fxml")));
    }

    @FXML
    void afficherEcranAccueilPerso(ActionEvent event) throws Exception {
        rootPane.getChildren().setAll( (AnchorPane) FXMLLoader.load(getClass().getResource("/view/perso.fxml")));
    }

//    @FXML
    void afficherEcranAccueil(ActionEvent event) throws Exception {
        rootPane.getChildren().setAll( (AnchorPane) FXMLLoader.load(getClass().getResource("/view/login.fxml")));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/perso.fxml"));
        Node vueFille = loader.load();
        rootPane.getChildren().add(vueFille);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}