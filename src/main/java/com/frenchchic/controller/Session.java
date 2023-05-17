package com.frenchchic.controller;

import com.frenchchic.model.Client;
import com.frenchchic.view.VueJetable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Session implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;
    public VueJetable vueJetable;
    @FXML
    private Button btnClose;

    @FXML
    private TextField tfPseudo;

    @FXML
    private PasswordField pfPass;

    @FXML
    private Button btnLogin;

    @FXML
    private Label errorMessageLabel;
 
    private boolean isFieldFilled() throws Exception{ 
        if(tfPseudo.getText().isEmpty()) 
            throw new Exception("Veuillez entrer votre pseudo"); 
        if(pfPass.getText().isEmpty())
            throw new Exception("Veullez entrer votre mot de passe"); 
        return true;
    }
     

    @FXML 
    public void initialize(URL location, ResourceBundle ressources){
        errorMessageLabel.setText(""); 
    }


    @FXML
    private void onMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML
    private void onMouseDragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }
    @FXML
    private void login(ActionEvent event){
        errorMessageLabel.setText("");
        try {
            isFieldFilled();
            Client client=Client.login(tfPseudo.getText(),pfPass.getText()) ;
            vueJetable.client=client;
            vueJetable.home();
        } catch (Exception e) {
            errorMessageLabel.setText(e.getMessage()); 
        }
 
    }




}