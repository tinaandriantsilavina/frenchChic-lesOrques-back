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

public class TraiterIdentificationResponse  {
    public EnumTypeEcran typeEcran;
    public Client leClient;
    public Produit leProduit;
    public TraiterIdentificationResponse(EnumTypeEcran typeEcran, Client leClient, Produit leProduit){
        this.typeEcran = typeEcran;
        this.leClient = leClient;
        this.leProduit = leProduit;
    }
}