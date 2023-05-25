package com.frenchchic.model;

public class LigneCommande {
    Produit produit;
    int quantite;

    public LigneCommande() {
    }

    public LigneCommande(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    public float getPrix () {
        return quantite * produit.getPrix();
    }
    
    @Override
    public String toString() {
        return "LigneCommande{" + "produit=" + produit + ", quantite=" + quantite + '}';
    }
}
