package com.frenchchic.controller;

import com.frenchchic.model.Commande;
import com.frenchchic.model.Produit;
import com.frenchchic.utils.Utils;
import com.frenchchic.view.VueJetable;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class TraitementAccueilPerso extends ViewController implements Initializable {
    private Produit produit;
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
        produit = new Produit().rechercheProduitDuJour();
        initDynamicLabel();
        initBtnAjout();
        initChampNumber();
    }
    public void afficherPanier(){

    }

    public void initBtnAjout(){
        btnAjout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    Commande commande = new Commande();
                    commande.ajouterProduit(produit, getQuantite());
                    FXMLLoader panier = Utils.getFxml(VueJetable.PANIER);
                    TraitementListPanier panierController = panier.getController();
                    panierController.setCommande(commande);
                    panierController.setListe();
                    getVueParent().loadChildPane(panier);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
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
        quantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, produit.getQuantiteEnStock()));
    }

    public void initDynamicLabel(){
        produit = new Produit().rechercheProduitDuJour();
        this.setPrix(String.valueOf(produit.getPrix()));
        this.setStock(String.valueOf(produit.getQuantiteEnStock()));
        this.setNomProduit(produit.getLibelle());
    }

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
