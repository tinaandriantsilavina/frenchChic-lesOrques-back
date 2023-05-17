package com.frenchchic.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.frenchchic.model.Produit;
import com.frenchchic.view.VueJetable;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos; 
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 
import javafx.scene.layout.GridPane; 
import javafx.scene.layout.VBox; 

public class CartController implements Initializable {

    @FXML
    public GridPane   cardBox; // Replace AnchorPane with GridPane;
    public VueJetable vueJetable;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cardBox.setHgap(10);
        cardBox.setVgap(10);
        cardBox.setPadding(new Insets(10));
        for(Produit produit :Produit.lesProduits)
            this.createProduitCard(produit); 
        
    }
    private void createProduitCard(Produit produit) {
        // Créer une carte pour le produit
        Label nameLabel = new Label(produit.getLibelle());
        Label refLabel = new Label(produit.getReference());
        Label priceLabel = new Label(String.format("$ %.2f", produit.getPrix()));
        nameLabel.getStyleClass().add("nameLabel");
        refLabel.getStyleClass().add("refLabel"); 
        Image image = new Image(getClass().getResource(produit.imagePath).toExternalForm() ); 
        ImageView imageView = new ImageView(image);  
        Button addToCart=new Button("Add To Cart"); 
        addToCart.setAlignment(Pos.CENTER);
        VBox  produitCard = new VBox (imageView, nameLabel,refLabel, priceLabel,addToCart);  
        
        
        
        produitCard.getStyleClass().add("cardbox"); 
        priceLabel.getStyleClass().add("label"); 
        priceLabel.getStyleClass().add("priceLabel");
        addToCart.getStyleClass().add("btn");
        
        
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);         
        
        
        int i=Produit.lesProduits.indexOf((produit) );
        int numColumns=3; 
        int columnIndex = i % numColumns;
        int rowIndex = i / numColumns;
        produitCard.setPadding(new Insets(10));
        cardBox.add(produitCard,columnIndex ,rowIndex);
        addToCart.setOnAction((arg)->{
            vueJetable.panier.add(produit);
        });


    }



}
