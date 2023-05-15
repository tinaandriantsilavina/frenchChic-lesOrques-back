package com.frenchchic.model;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Produit {
    String reference;
    String libelle;
    List<String> motCles;
    float prix;
    boolean estDuJour;
    int quantiteEnStock;
    List<Produit> lesProduits = Arrays.asList(
            new Produit("ref001", "pantalon zouk", Arrays.asList("pantalon", "zouk"), 50, true, 100),
            new Produit("ref002", "chapeau zouk", Arrays.asList("chapeau", "zouk"), 10, true, 100),
            new Produit("ref003", "pull", Arrays.asList("pull", "chaud"), 60, true, 100)
    );

    public List<Produit> rechercheProduitParMotCle(String motCle) {
        List<Produit> results = getLesProduits().stream()
                        .filter(p -> p.motCles.contains(motCle))
                        .collect(Collectors.toList());

        return results;
    }

    public void retirerDuStock(int quantite) throws Exception {
        if (quantite <= 0)
            throw new Exception("La quantité doit être positif et non nulle");
        if (getQuantiteEnStock() < quantite)
            throw new Exception("La quantité en stock est insuffisant");

        setQuantiteEnStock(quantiteEnStock - quantite);
    }

    public Produit rechercheProduitDuJour() {
        List<Produit> result = getLesProduits().stream()
                .filter(p -> p.estDuJour)
                .collect(Collectors.toList());
        return result.get(0);
    }

    public Produit() {
    }

    public Produit(String reference, String libelle, List<String> motCles, float prix, boolean estDuJour, int quantiteEnStock) {
        this.reference = reference;
        this.libelle = libelle;
        this.motCles = motCles;
        this.prix = prix;
        this.estDuJour = estDuJour;
        this.quantiteEnStock = quantiteEnStock;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<String> getMotCles() {
        return motCles;
    }

    public void setMotCles(List<String> motCles) {
        this.motCles = motCles;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isEstDuJour() {
        return estDuJour;
    }

    public void setEstDuJour(boolean estDuJour) {
        this.estDuJour = estDuJour;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public List<Produit> getLesProduits() {
        return lesProduits;
    }

    public void setLesProduits(List<Produit> lesProduits) {
        this.lesProduits = lesProduits;
    }
}
