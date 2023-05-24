package com.frenchchic.controller;

import com.frenchchic.model.Produit;
import com.frenchchic.view.VueJetable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class TraitementAccueilPerso implements Initializable {
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

    @FXML
    private Label stock;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();
    }
    public void initialize() {
        initDynamicLabel();
        initChampNumber();
        initBtnAjout();
    }
    public void afficherPanier(){

    }

    public void initBtnAjout(){
        btnAjout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(getQuantite());
                System.out.println(getStock());
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

    public Integer getQuantite() {
        return quantite.getValue();
    }

    public void setQuantite(Spinner<Integer> quantite) {
        this.quantite = quantite;
    }

    public Label getNomClient() {
        return nomClient;
    }

    public TraitementAccueilPerso setNomClient(String nomClient) {
        this.nomClient.setText( nomClient);
        return this;
    }

    public Label getNomProduit() {
        return nomProduit;
    }

    public TraitementAccueilPerso setNomProduit(String nomProduit) {
        this.nomProduit.setText( nomProduit );
        return this;
    }

    public Label getPrix() {
        return prix;
    }

    public TraitementAccueilPerso setPrix(String prix) {
        this.prix.setText( prix );
        return this;
    }

    public String getStock() {
        return this.stock.getText();
    }

    public TraitementAccueilPerso setStock(String stock) {
        this.stock.setText(stock);
        return this;
    }
}
