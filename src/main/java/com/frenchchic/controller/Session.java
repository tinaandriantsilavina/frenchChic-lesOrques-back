package com.frenchchic.controller;

import com.frenchchic.model.Client;
import com.frenchchic.model.Commande;
import com.frenchchic.model.Produit;

import java.util.List;

public class Session {
    private EnumTypeEcran typeEcran;
    private static Client client;
    private Commande laCommande;
    private Produit produit;

    public TraiterConnexionResponse traiterConnexion() {
        return new TraiterConnexionResponse(EnumTypeEcran.ECRAN_ACCUEIL);
    }
    public Session(){
        produit = new Produit();
    }
    public TraiterIdentificationResponse traiterIdentification(String ps, String mdp) {
        Client leClient = new Client().rechercheClientParPseudo(ps, mdp);
        Produit leProduit = new Produit().rechercheProduitDuJour();
        TraiterIdentificationResponse reponse = new TraiterIdentificationResponse(EnumTypeEcran.ECRAN_ACCUEIL_PERSO, leClient, leProduit);
        return reponse;
    }

    public TraitementAjoutPanierReponse traiterAjoutPanier(Produit leProduit, int quantite) throws Exception {
        laCommande = new Commande();
        laCommande.ajouterProduit(leProduit, quantite);
        for (int i=0; i<produit.getLesProduits().size(); i++){
            if(produit.getLesProduits().get(i).getReference().equals(leProduit.getReference())){
                produit.getLesProduits().get(i).retirerDuStock(quantite);
                break;
            }
        }
        return traiterPanierEncours() ;
    }
    public TraitementAjoutPanierReponse traiterPanierEncours() {
        return new TraitementAjoutPanierReponse(EnumTypeEcran.ECRAN_PANIER, laCommande);
    }

    public TraiterProduitResponse traiterListProduit() {
        TraiterProduitResponse reponse = new TraiterProduitResponse(EnumTypeEcran.ECRAN_PRODUIT, produit.getLesProduits());
        return reponse;
    }
}
