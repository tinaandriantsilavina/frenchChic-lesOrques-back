package com.frenchchic.controller;

import com.frenchchic.model.Client;
import com.frenchchic.model.Commande;

public class Session {
    private EnumTypeEcran typeEcran;
//    private static Session instance;
    private static Client client;
    private Commande laCommande;

    public TraiterConnexionResponse traiterConnexion() {
        return new TraiterConnexionResponse(EnumTypeEcran.ECRAN_ACCUEIL);
    }

//    public static Session getInstance() {
//        if (instance == null) {
//            instance = new Session();
//        }
//        return instance;
//    }
}
