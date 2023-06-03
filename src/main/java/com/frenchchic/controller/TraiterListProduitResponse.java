package com.frenchchic.controller;

import com.frenchchic.model.Client;
import com.frenchchic.model.Produit;

import java.util.ArrayList;
import java.util.List;

public class TraiterListProduitResponse {
    public EnumTypeEcran typeEcran;
    public List<Produit> lesProduits;
    public TraiterListProduitResponse(EnumTypeEcran type, List<Produit> prod){
        this.typeEcran = type;
        this.lesProduits = prod;
    }
}