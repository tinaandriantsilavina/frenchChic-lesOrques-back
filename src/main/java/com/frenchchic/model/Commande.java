package com.frenchchic.model;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    float montant = 0;
    StatutCode statutCode = StatutCode.NON_CONFIRMER;
    List<LigneCommande> lesCommandes = new ArrayList<>();

    public Commande creerPanier() throws Exception {
        if (getLesCommandes().isEmpty())
            throw new Exception("Commande vide");
        setStatutCode(statutCode.CONFIRMER);
        lesCommandes.forEach(cmd -> {
            try {
                cmd.getProduit().retirerDuStock(cmd.getQuantite());
            } catch (Exception ex) {
                Logger.getLogger(Commande.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return this;
    }
    
    public void ajouterProduit(Produit produit, int quantite) {
        getLesCommandes().add(new LigneCommande(produit, quantite));
        setMontant(montant + (produit.getPrix() * quantite));
    }

    public Commande() {
    }


    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public StatutCode getStatutCode() {
        return statutCode;
    }

    public void setStatutCode(StatutCode statutCode) {
        this.statutCode = statutCode;
    }

    public List<LigneCommande> getLesCommandes() {
        return lesCommandes;
    }

    public void setLesCommandes(List<LigneCommande> lesCommandes) {
        this.lesCommandes = lesCommandes;
    }

    @Override
    public String toString() {
        return "Commande{" + "montant=" + montant + ", statutCode=" + statutCode + ", lesCommandes=" + lesCommandes + '}';
    }
    
    
    public static void main(String[] args) throws Exception {
        Produit produit = new Produit();
        produit.initLesProduit();
        Produit pDuJour = produit.rechercheProduitDuJour();
        Commande commande = new Commande();
        commande.ajouterProduit(pDuJour, 2);        
        commande.ajouterProduit(produit.getLesProduits().get(1), 3);
        System.out.println(commande);
        
        commande.creerPanier();
        System.out.println(commande);
    }
}
