package com.frenchchic.model;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    float montant;
    int statutCode;
    List<LigneCommande> lesCommandes = new ArrayList<>();

    public Commande creerPanier() throws Exception {
        if (getLesCommandes().isEmpty())
            throw new Exception("Commande vide");
        return this;
    }
    public void ajouterProduit(Produit produit, int quantite) {
        getLesCommandes().add(new LigneCommande(produit, quantite));
    }

    public Commande() {
    }


    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public int getStatutCode() {
        return statutCode;
    }

    public void setStatutCode(int statutCode) {
        this.statutCode = statutCode;
    }

    public List<LigneCommande> getLesCommandes() {
        return lesCommandes;
    }

    public void setLesCommandes(List<LigneCommande> lesCommandes) {
        this.lesCommandes = lesCommandes;
    }
}
