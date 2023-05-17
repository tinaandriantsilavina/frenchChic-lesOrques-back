package com.frenchchic.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Client {
    String numero;
    String nom;
    String prenom;
    String adresseFacturation;
    String pseudo;
    String adresseLivraison;
    String motDePasse;
    List<Client> lesClients;


    public Client rechercheClientParPseudo(String pseudo, String motDePasse) {
        List<Client> result = getLesClients().stream()
                        .filter(c -> c.pseudo.equals(pseudo) && c.motDePasse.equals(motDePasse))
                        .collect(Collectors.toList());
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    public Client() {
    }

    public Client(String numero, String nom, String prenom, String adresseFacturation, String pseudo, String adresseLivraison, String motDePasse) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseFacturation = adresseFacturation;
        this.pseudo = pseudo;
        this.adresseLivraison = adresseLivraison;
        this.motDePasse = motDePasse;
    }
    
    public void initLesClients() {
        this.lesClients = Arrays.asList(
            new Client("c001", "Dupont", "Marie", "15 rue des Romarins", "Dupont", "15 rue des Romarins", "123"),
            new Client("c001", "Martin", "Thomas", "24 rue de la libération", "Martin", "24 rue de la libération", "123"),
            new Client("c001", "Hubert", "Robert", "5 rue de la paix", "Dupont", "5 rue de la paix", "123")
        );
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresseFacturation() {
        return adresseFacturation;
    }

    public void setAdresseFacturation(String adresseFacturation) {
        this.adresseFacturation = adresseFacturation;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public List<Client> getLesClients() {
        return lesClients;
    }

    public void setLesClients(List<Client> lesClients) {
        this.lesClients = lesClients;
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.initLesClients();
        
        System.out.println(client.rechercheClientParPseudo("Dupont", "123"));
    }
}
