package com.frenchchic.controller;

import com.frenchchic.model.Produit;
import com.frenchchic.view.VueJetable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class TraitementAccueilPerso {
    VueJetable parentPane;
    @FXML
    private Spinner<Integer> quantite;
    @FXML
    private Label nomClient;
    @FXML
    private Label nomProduit;
    @FXML
    private Label prix;
    @FXML
    private Button btnAjout;



    public void initialize() {
        initDynamicLabel();
        initChampNumber();
        initButton(btnAjout);
    }
    public void afficherPanier(){

    }

    public void initButton(Button button){
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("hehehe");
            }
        });
    }

    public void initChampNumber(){
        String numberRegex = "\\d+";
        UnaryOperator<TextFormatter.Change> numberFilter = change -> {
            String newText = change.getControlNewText();
            if (Pattern.matches(numberRegex, newText)) {
                return change;
            }
            return null;
        };
        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 1, numberFilter);
        quantite.getEditor().setTextFormatter(textFormatter);
        quantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE));
    }

    public void initDynamicLabel(){
        Produit produit = new Produit().rechercheProduitDuJour();

    }

    public VueJetable getParentPane() { return parentPane; }

    public void setParentPane(VueJetable parentPane) { this.parentPane = parentPane; }
}
