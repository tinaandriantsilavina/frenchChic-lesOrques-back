package com.frenchchic.controller;

import com.frenchchic.model.Commande;
import com.frenchchic.model.LigneCommande;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TraitementAjoutPanier extends ViewController implements Initializable {
    Commande commande ;
    @FXML
    private TableView<LigneCommande> panierTable;
    @FXML
    private TableColumn<LigneCommande,String> libelle;
    @FXML
    private TableColumn<LigneCommande,String> prix;
    @FXML
    private TableColumn<LigneCommande,String> quantite;
    @FXML
    private TableColumn<LigneCommande,String> montant;
    @FXML
    private TableColumn<LigneCommande,String> stock;
    private ReadOnlyDoubleWrapper doubleWrap;

    ObservableList<com.frenchchic.metier.Panier> listPanier = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void loadData() {
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    private void refreshList(){
        listPanier.clear();
        List<com.frenchchic.metier.Panier> list = new ArrayList<>();
        for(int i=0; i<5; i++){
            com.frenchchic.metier.Panier panier = new com.frenchchic.metier.Panier("Libelee"+i,2.0+i,20+i,1.0+i,50-i);
            list.add(panier);
            listPanier.add(panier);
        }
//        panierTable.setItems(listPanier);
    }

    public void setListe() {
        ObservableList<LigneCommande> list = FXCollections.observableArrayList();
        list =FXCollections.observableList( commande.getLesCommandes());
        panierTable.setItems(list);

        libelle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduit().getLibelle()));
        prix.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getProduit().getPrix())));
        quantite.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantite())));
        montant.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantite())));
        stock.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getProduit().getQuantiteEnStock())));
    }

    public Commande getCommande() { return commande; }

    public void setCommande(Commande commande) { this.commande = commande; }
}
