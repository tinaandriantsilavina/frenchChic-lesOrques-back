package com.frenchchic.view;

import com.frenchchic.controller.*;
import com.frenchchic.metier.Panier;
import com.frenchchic.model.Client;
import com.frenchchic.model.Commande;
import com.frenchchic.model.LigneCommande;
import com.frenchchic.model.Produit;
import com.frenchchic.utils.Utils;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class VueJetable extends Application implements Initializable {
    public Produit produit;
    public final static String VUEJETABLE= "/view/viewJetable.fxml";
    public final static String LOGIN ="/view/login.fxml";
    public final static String PANIER= "/view/panier.fxml";
    public final static String PERSO= "/view/perso.fxml";
    private Client client;
    static Session laSession;
    @FXML
    private Pane childPane;
    public VueJetable vueParent;

    //**** Login ****\\
    @FXML
    public Button login_btnClose;
    @FXML
    public TextField login_tfPseudo;
    @FXML
    public PasswordField login_pfPass;
    @FXML
    public Button btnLogin;
    @FXML
    public Label login_errorMessageLabel;
    public String login_errorMesssage ="";

    //**** Accueil perso ****\\
    @FXML
    public Spinner<Integer> perso_quantite;
    @FXML
    public Label perso_nomClient;
    @FXML
    public Label perso_nomProduit;
    @FXML
    public Label perso_prixProduit;
    @FXML
    public Button perso_btnAjout;
    @FXML
    public Label perso_stock;


    //**** Panier ****\\
    @FXML
    public TableView<LigneCommande> tabPanier_panierTable;
    @FXML
    public TableColumn<LigneCommande,String> tabPanier_libelle;
    @FXML
    public TableColumn<LigneCommande,String> tabPanier_prix;
    @FXML
    public TableColumn<LigneCommande,String> tabPanier_quantite;
    @FXML
    public TableColumn<LigneCommande,String> tabPanier_montant;
    @FXML
    public TableColumn<LigneCommande,String> tabPanier_stock;
    ObservableList<Panier> tabPanier_listPanier = FXCollections.observableArrayList();

    public void start(Stage stage) throws Exception {
        try{
            laSession = new Session();
            TraiterConnexionResponse reponse = laSession.traiterConnexion();
            if (reponse.typeEcran == EnumTypeEcran.ECRAN_ACCUEIL) {
                FXMLLoader fxmlLoader = Utils.getFxml(LOGIN);
                Scene scene = new Scene(fxmlLoader.getRoot());
                login_initializeTraitementConnexion(fxmlLoader.getController());
                stage.setTitle("Home");
                stage.setScene(scene);
                stage.show();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void loadChildPane(FXMLLoader fxml) {
        try {
            childPane.getChildren().clear();
            childPane.getChildren().add(fxml.getRoot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void afficherEcranPanier(ActionEvent event) throws Exception {
        childPane.getChildren().setAll( (AnchorPane) FXMLLoader.load(getClass().getResource("/view/panier.fxml")));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    //** login

    private boolean login_isFieldFilled(){
        boolean isFilled = true;
        if(login_tfPseudo.getText().isEmpty()){
            isFilled = false;
            login_errorMesssage ="Veuillez entrer votre pseudo";
        }
        if(login_pfPass.getText().isEmpty()){
            isFilled = false;
            if(login_errorMesssage.isEmpty()){
                login_errorMesssage = "Veullez entrer votre mot de passe";
            }else{
                login_errorMesssage = login_errorMesssage + " \nVeullez entrer votre mot de passe";
            }
        }
        login_errorMessageLabel.setText(login_errorMesssage);
        return isFilled;
    }
    private TraiterIdentificationResponse login_identification(){
        boolean isValid = true;
        TraiterIdentificationResponse response=null;
        try{
            response = laSession.traiterIdentification(login_tfPseudo.getText(), login_pfPass.getText());
            if(response.leClient!=null){
//                this.setClient(cl);
            }else{
                login_errorMesssage = "Votre pseudo ou votre mot de passe est incorrect";
                isValid = false;
            }

        }catch (Exception ex){
            login_errorMesssage = "Votre pseudo ou votre mot de passe est incorrect";
            isValid = false;
        }
        login_errorMessageLabel.setText(login_errorMesssage);
        return response;
    }

    public  void perso_startAccueilPerso(Client client, Produit produit) throws IOException,Exception {
        FXMLLoader fxmlLoader = Utils.getFxml(VueJetable.VUEJETABLE);
        vueParent = fxmlLoader.getController();
        FXMLLoader perso = Utils.getFxml(VueJetable.PERSO);
        perso_initializeAccueilPerso(perso.getController(),client,produit);
        vueParent.loadChildPane(perso);
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.getRoot());
        stage.setTitle("French chic");
        stage.setScene(scene);
        stage.show();
    }


    public void login_initializeTraitementConnexion(VueJetable v){
        this.login_btnClose =v.login_btnClose;
        this.btnLogin = v.btnLogin;
        this.login_tfPseudo = v.login_tfPseudo;
        this.login_pfPass =v.login_pfPass;
        this.login_errorMesssage =v.login_errorMesssage;
        this.login_errorMessageLabel = v.login_errorMessageLabel;

        login_btnClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });
        btnLogin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                login_errorMesssage ="";
                if(login_isFieldFilled()){
                    try {
                        TraiterIdentificationResponse response = login_identification();
                        if(response.leClient!=null && response.typeEcran.equals( EnumTypeEcran.ECRAN_ACCUEIL_PERSO)){
                            Stage stage = (Stage) btnLogin.getScene().getWindow();
                            stage.close();
                            perso_startAccueilPerso(response.leClient,response.leProduit);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

   //**** Accueil Perso
    public void perso_initializeAccueilPerso(VueJetable vuePerso, Client client , Produit produit) {
        perso_initInputAcceuilPerso(vuePerso,client,produit);
        perso_initChampNumber(vuePerso, produit);
        perso_initBtnAjout(vuePerso);
    }
    public void perso_initBtnAjout(VueJetable v){
        v.perso_btnAjout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    Commande commande = new Commande();
                    commande.ajouterProduit(produit, perso_quantite.getValue());
                    FXMLLoader panier = Utils.getFxml(VueJetable.PANIER);
                    TraitementAjoutPanierReponse response = laSession.traiterAjoutPanier(produit, perso_quantite.getValue());
                    commande.creerPanier();
                    // init Commande
                    tabPanier_setListeCommande(commande, panier.getController());
                    vueParent.loadChildPane(panier);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public void perso_initChampNumber(VueJetable v, Produit prod){
        String numberRegex = "\\d+";
        UnaryOperator<TextFormatter.Change> numberFilter = change -> {
            String newText = change.getControlNewText();
            if (Pattern.matches(numberRegex, newText)) {
                return change;
            }
            return null;
        };
        v.perso_quantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, prod.getQuantiteEnStock()));
    }

    public void perso_initInputAcceuilPerso(VueJetable v, Client cl, Produit produit){
        v.perso_prixProduit.setText( (String.valueOf(produit.getPrix())) );
        v.perso_stock.setText(String.valueOf(produit.getQuantiteEnStock()));
        v.perso_nomProduit.setText( produit.getLibelle() );
        v.perso_nomClient.setText(cl.getPrenom()+" "+cl.getNom());
        this.perso_quantite = v.perso_quantite;
        this.produit = produit;
    }


    //**** Panier ****\\
    public void tabPanier_setListeCommande(Commande commande, VueJetable panier) {
        ObservableList<LigneCommande> list = FXCollections.observableList( commande.getLesCommandes());
        panier.tabPanier_panierTable.setItems(list);
        panier.tabPanier_libelle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduit().getLibelle()));
        panier.tabPanier_prix.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getProduit().getPrix())));
        panier.tabPanier_quantite.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantite())));
        panier.tabPanier_montant.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrix())));
        panier.tabPanier_stock.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getProduit().getQuantiteEnStock())));
    }
}