package com.frenchchic.controller;

import com.frenchchic.model.Produit;

import java.util.List;

public class TraiterProduitResponse {
    public EnumTypeEcran typeEcran;
    public List<Produit> lesProduits;
    public TraiterProduitResponse(EnumTypeEcran type, List<Produit> prod){
        this.typeEcran = type;
        this.lesProduits = prod;
    }
    public TraiterProduitResponse(){

    }
    public List<Produit> rechercheProduit(String motCle){
        Produit prod = new Produit();
        List<Produit> list=prod.getLesProduits();
        if(motCle!=null && !motCle.trim().equals("")){
            list = prod.rechercheProduitParMotCle(motCle);
        }
        return list;
    }
}