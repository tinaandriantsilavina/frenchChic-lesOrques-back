package com.frenchchic.controller;

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

public class TraitementListPanier implements Initializable {

    @FXML
    private TableView<com.frenchchic.metier.Panier> panierTable;
    @FXML
    private TableColumn<com.frenchchic.metier.Panier,String> libelle;
    @FXML
    private TableColumn<com.frenchchic.metier.Panier,String> prix;
    @FXML
    private TableColumn<com.frenchchic.metier.Panier,String> quantite;
    @FXML
    private TableColumn<com.frenchchic.metier.Panier,String> montant;
    @FXML
    private TableColumn<com.frenchchic.metier.Panier,String> stock;
    private ReadOnlyDoubleWrapper doubleWrap;

    ObservableList<com.frenchchic.metier.Panier> listPanier = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        loadData();
//        refreshList();
        setListe();
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
        panierTable.setItems(listPanier);
    }

    public void setListe() {
        ObservableList<com.frenchchic.metier.Panier> nouvelleListe = FXCollections.observableArrayList();
        nouvelleListe.add(new com.frenchchic.metier.Panier("Ketrika 01",2.0,20,1.0,50));
        nouvelleListe.add(new com.frenchchic.metier.Panier("Ketrika 02",2.0,20,1.0,50));

        panierTable.setItems(nouvelleListe);

        libelle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLibelle()));
        prix.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrix().toString()));
        quantite.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantite().toString()));
        montant.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMontant().toString()));
        stock.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStock().toString()));

        panierTable.setItems(nouvelleListe);
    }
}
