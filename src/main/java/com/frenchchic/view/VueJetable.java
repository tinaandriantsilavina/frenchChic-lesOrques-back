package com.frenchchic.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.frenchchic.controller.CartController;
import com.frenchchic.controller.Session;
import com.frenchchic.model.Client;
import com.frenchchic.model.Produit;

public class VueJetable extends Application implements Initializable {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Stage primaryStage;
    public Client client;
    public List<Produit> panier=new ArrayList<>();


    Stage stage; 
    
    
    
    public void start(Stage stage) throws IOException {
        
        this.stage=stage; 
       login(); 

    }
    public void login() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root); 
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        Session session = fxmlLoader.getController();
        session.vueJetable=this;
    }
    public void home() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/viewJetable.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root); 
        scene.getStylesheets().add(getClass().getResource("/values/cardProduct.css").toExternalForm());
        stage.setTitle("Home");
        stage.setScene(scene); 
        stage.show();

       


    }

 

    @FXML
    void afficherEcranPanier(ActionEvent event) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/panier.fxml"));
        Parent root = fxmlLoader.load(); 
        rootPane.getChildren().setAll( (AnchorPane) root);
        // CartController controller=fxmlLoader.getController();
        // stage.getScene().widthProperty().addListener((obs, oldWidth, newWidth) ->controller.cardBox.requestLayout());
        // stage.getScene().heightProperty().addListener((obs, oldHeight, newHeight) -> controller.cardBox.requestLayout());
    }

    @FXML
    void afficherEcranAccueilPerso(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/perso.fxml"));
        GridPane root = fxmlLoader.load();
        rootPane.getChildren().setAll(   root)  ;
       
        Scene scene=rootPane.getScene();
        CartController controller=fxmlLoader.getController();
        controller.vueJetable=this;
        scene.widthProperty().addListener((obs, oldWidth, newWidth) ->controller.cardBox.requestLayout());
        scene.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            controller.cardBox.requestLayout();
        }); 
       
    }
 
    void afficherEcranAccueil(ActionEvent event) throws Exception {
        rootPane.getChildren().setAll( (AnchorPane) FXMLLoader.load(getClass().getResource("/view/login.fxml")));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
  

}