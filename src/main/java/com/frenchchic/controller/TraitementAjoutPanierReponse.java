package com.frenchchic.controller;

import com.frenchchic.model.Commande;

public class TraitementAjoutPanierReponse {
    public EnumTypeEcran typeEcran;
    public Commande laCommande;
    public TraitementAjoutPanierReponse(EnumTypeEcran typeEcran, Commande laCommande){
        this.typeEcran = typeEcran;
        this.laCommande = laCommande;
    }
}
