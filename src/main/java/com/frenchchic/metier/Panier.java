package com.frenchchic.metier;

public class Panier {
    private String libelle;
    private Double prix;
    private Integer quantite;
    private Double montant;
    private Integer stock;

    public Panier() {

    }
    public Panier(String libelle, Double prix, Integer quantite, Double montant, Integer stock) {
        this.libelle = libelle;
        this.prix = prix;
        this.quantite = quantite;
        this.montant = montant;
        this.stock = stock;
    }


    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
