package com.frenchchic.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
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

    private String errorMesssage="";
    private boolean isFieldFilled(){
        boolean isFilled = true;
        if(tfPseudo.getText().isEmpty()){
            isFilled = false;
            errorMesssage="Veuillez entrer votre pseudo";
        }
        if(pfPass.getText().isEmpty()){
            isFilled = false;
            if(errorMesssage.isEmpty()){
                errorMesssage = "Veullez entrer votre mot de passe";
            }else{
                errorMesssage =errorMesssage+ " \nVeullez entrer votre mot de passe";
            }
        }
        errorMessageLabel.setText(errorMesssage);
        return isFilled;
    }
    private boolean isValid(){
        boolean isValid = true;
        if(!tfPseudo.getText().equals(com.frenchchic.view.Login.PSEUDO)){
            isValid = false;
            errorMesssage = "Pseudo invalide";
        }
        if(!pfPass.getText().equals(com.frenchchic.view.Login.PASS)){
            isValid = false;
           if(errorMesssage.isEmpty()){
                errorMesssage = "Mot de passe  invalide";
           }else{
               errorMesssage = errorMesssage+" \nMot de passe  invalide";
           }
        }
        errorMessageLabel.setText(errorMesssage);
        return isValid;
    }

    public  void startHomeWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("src/main/java/com/frenchchic/view/home.fxml").toURI().toURL());
        Stage stage = new Stage();
        stage.setMaximized(true);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("French Chic");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle ressources){
        btnClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });
        btnLogin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                errorMesssage="";
                if(isFieldFilled() && isValid()){
                    try {
                        startHomeWindow();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}