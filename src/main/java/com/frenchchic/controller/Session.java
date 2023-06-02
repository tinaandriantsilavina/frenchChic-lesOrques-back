package com.frenchchic.controller;

import com.frenchchic.model.Client;
import com.frenchchic.model.Commande;
import com.frenchchic.model.Produit;

public class Session {
    private EnumTypeEcran typeEcran;
    private static Client client;
    private Commande laCommande;


    public TraiterConnexionResponse traiterConnexion() {
        return new TraiterConnexionResponse(EnumTypeEcran.ECRAN_ACCUEIL);
    }

    public TraiterIdentificationResponse traiterIdentification(String ps, String mdp) {
        Client leClient = new Client().rechercheClientParPseudo(ps, mdp);
        Produit leProduit = new Produit().rechercheProduitDuJour();
        TraiterIdentificationResponse reponse = new TraiterIdentificationResponse(EnumTypeEcran.ECRAN_ACCUEIL_PERSO, leClient, leProduit);
        return reponse;
    }

    public TraitementAjoutPanierReponse traiterAjoutPanier(Produit leProduit, int quantite) {
        Commande laCommande = new Commande();
        laCommande.ajouterProduit(leProduit, quantite);
        TraitementAjoutPanierReponse reponse = new TraitementAjoutPanierReponse(EnumTypeEcran.ECRAN_PANIER, laCommande);
        return reponse;
    }
}
