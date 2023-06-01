package com.frenchchic.controller;

import com.frenchchic.model.Client;
import com.frenchchic.model.Produit;
import com.frenchchic.utils.Utils;
import com.frenchchic.view.VueJetable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TraitementConnexion  {

    private double xOffset = 0;
    private double yOffset = 0;


//    private boolean isValid(){
//        boolean isValid = true;
//        Client cl= new Client().rechercheClientParPseudo(tfPseudo.getText(),pfPass.getText());
//        if(!tfPseudo.getText().equals("Admin")){
//            isValid = false;
//            errorMesssage = "Pseudo invalide";
//        }
//        if(!pfPass.getText().equals("123")){
//            isValid = false;
//            if(errorMesssage.isEmpty()){
//                errorMesssage = "Mot de passe  invalide";
//            }else{
//                errorMesssage = errorMesssage+" \nMot de passe  invalide";
//            }
//        }
//        errorMessageLabel.setText(errorMesssage);
//        return isValid;
//    }
}