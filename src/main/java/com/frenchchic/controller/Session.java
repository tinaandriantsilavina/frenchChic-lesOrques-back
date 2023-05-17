package com.frenchchic.controller;

import com.frenchchic.model.Client;
import com.frenchchic.view.VueJetable;
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
    private Client client;
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
        try{
            Client cl= new Client().rechercheClientParPseudo(tfPseudo.getText(),pfPass.getText());
            if(cl!=null){
                setClient(cl);
            }else{
                errorMesssage = "Votre pseudo ou votre mot de passe est incorrect";
                isValid = false;
            }

        }catch (Exception ex){
            errorMesssage = "Votre pseudo ou votre mot de passe est incorrect";
            isValid = false;
        }
        errorMessageLabel.setText(errorMesssage);
        return isValid;
    }

    public  void startHomeWindow() throws IOException,Exception {
        VueJetable log = new VueJetable();
        Stage stage = new Stage();
        log.setClient(client);
        log.startVueJetable(stage);
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
                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();
                        startHomeWindow();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

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