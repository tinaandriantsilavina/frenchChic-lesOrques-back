package com.frenchchic.controller;

import com.frenchchic.model.Client;
import com.frenchchic.model.Commande;
import com.frenchchic.model.Produit;

public class Session {
    private EnumTypeEcran typeEcran;
    private static Client client;
    private Commande laCommande;
//    private static Session instance;
//    public static Session getInstance() {
//        if (instance == null) {
//            instance = new Session();
//        }
//        return instance;
//    }

    public TraiterIdentificationResponse traiterIdentification(String pseudo, String mdp) {
        Client leClient = new Client().rechercheClientParPseudo(pseudo, mdp);
        Produit leProduit = new Produit().rechercheProduitDuJour();
        TraiterIdentificationResponse reponse = new TraiterIdentificationResponse(EnumTypeEcran.ECRAN_ACCUEIL_PERSO, leClient, leProduit);
        return reponse;
    }


    public TraitementListPanier traiterAjoutPanier(Produit leProduit, int quantite) {
        Commande laCommande = new Commande();
        laCommande.ajouterProduit(leProduit, quantite);
        TraitementListPanier reponse = new TraitementListPanier(EnumTypeEcran.ECRAN_PANIER, laCommande);

        return reponse;
    }
}
