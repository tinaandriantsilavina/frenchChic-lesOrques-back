package com.frenchchic.controller;

import com.frenchchic.metier.Panier;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListController implements Initializable {

    @FXML
    private TableView<Panier> panierTable;
    @FXML
    private TableColumn<Panier,String> libelle;
    @FXML
    private TableColumn<Panier,String> prix;
    @FXML
    private TableColumn<Panier,String> quantite;
    @FXML
    private TableColumn<Panier,String> montant;
    @FXML
    private TableColumn<Panier,String> stock;
    private ReadOnlyDoubleWrapper doubleWrap;

    ObservableList<Panier> listPanier = FXCollections.observableArrayList();

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
        List<Panier> list = new ArrayList<>();
        for(int i=0; i<5; i++){
            Panier panier = new Panier("Libelee"+i,2.0+i,20+i,1.0+i,50-i);
            list.add(panier);
            listPanier.add(panier);
        }
        panierTable.setItems(listPanier);
//        listPanier.setAll(list);
    }

    public void setListe() {
        ObservableList<Panier> nouvelleListe = FXCollections.observableArrayList();
        nouvelleListe.add(new Panier("Ketrika 01",2.0,20,1.0,50));
        nouvelleListe.add(new Panier("Ketrika 02",2.0,20,1.0,50));

        panierTable.setItems(nouvelleListe);

        libelle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLibelle()));
        prix.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrix().toString()));
        quantite.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantite().toString()));
        montant.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMontant().toString()));
        stock.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStock().toString()));

//        doubleWrap = new ReadOnlyDoubleWrapper();
//        doubleWrap.bind(new SimpleDoubleProperty(10.2));
//
//        prix.setCellValueFactory(cellData -> doubleWrap);
        panierTable.setItems(nouvelleListe);
    }
}
