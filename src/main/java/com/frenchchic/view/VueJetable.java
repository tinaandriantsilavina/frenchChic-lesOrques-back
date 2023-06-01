package com.frenchchic.view;

import com.frenchchic.controller.*;
import com.frenchchic.model.Client;
import com.frenchchic.model.Commande;
import com.frenchchic.model.Produit;
import com.frenchchic.utils.Utils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class VueJetable extends Application implements Initializable {
//    public VueJetable vueJetable;
    public final static String VUEJETABLE= "/view/viewJetable.fxml";
    public final static String PANIER= "/view/panier.fxml";
    public final static String PERSO= "/view/perso.fxml";
    static Session laSession;

    @FXML
    private Pane childPane;
    @FXML
    private Stage primaryStage;

    private Client client;

    @FXML
    private Button btnAffichage;

    public void setClient(Client client) {
        this.client = client;
    }
    public void start(Stage stage) throws IOException {
        laSession = new Session();
        TraiterConnexionResponse reponse = laSession.traiterConnexion();
        if (reponse.typeEcran == EnumTypeEcran.ECRAN_ACCUEIL) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            initializeTraitementConnexion(fxmlLoader.getController());
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        }

    }

//    public void startVueJetable() throws IOException, Exception {
//        Stage stage = new Stage();
//        stage.close();
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/viewJetable.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        VueJetable temp = fxmlLoader.getController();
//        stage.setTitle("French chic");
//        stage.setScene(scene);
//        stage.show();
//    }
//    public void loadChildPane(String fxmlPath) {
//        try {
//            childPane.getChildren().clear();
//            childPane.getChildren().add(FXMLLoader.load(getClass().getResource(fxmlPath)));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void loadChildPane(FXMLLoader fxml) {
        try {
            childPane.getChildren().clear();
            childPane.getChildren().add(fxml.getRoot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void loadChildPane(Parent parent) {
//        childPane.getChildren().clear();
//        childPane.getChildren().add(parent);
//    }
    @FXML
    void afficherEcranPanier(ActionEvent event) throws Exception {
        childPane.getChildren().setAll( (AnchorPane) FXMLLoader.load(getClass().getResource("/view/panier.fxml")));
    }

    @FXML
    void afficherEcranAccueilPerso(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/perso.fxml"));
        Parent root = fxmlLoader.load();
        Produit produit = new Produit().rechercheProduitDuJour();
        TraitementAccueilPerso controller =  fxmlLoader.getController();
//        controller.setNomClient("klklklkl");
//        controller.setPrix(String.valueOf(produit.getPrix()));
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(root);
        childPane.getChildren().setAll( anchorPane );
    }

    @FXML
    void afficherEcranAccueil(ActionEvent event) throws Exception {
        childPane.getChildren().setAll( (AnchorPane) FXMLLoader.load(getClass().getResource("/view/login.fxml")));
    }


    @FXML
    void afficher(ActionEvent event) throws Exception {
        Stage stage = (Stage) btnAffichage.getScene().getWindow();
        stage.close(); // Fermer la fenêtre actuelle
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

//    public Stage getPrimaryStage() {
//        return primaryStage;
//    }
//
//    public void setPrimaryStage(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//    }
















    // TraitementConnexion

    @FXML
    public Button btnClose;

    @FXML
    public TextField tfPseudo;

    @FXML
    public PasswordField pfPass;

    @FXML
    public Button btnLogin;

    @FXML
    public Label errorMessageLabel;

    public String errorMesssage="";





    private boolean isFieldFilled(){
        boolean isFilled = true;
        if(tfPseudo.getText().isEmpty()){
            isFilled = false;
            errorMesssage="Veuillez entrer votre pseudo";
        }
        if(pfPass.getText().isEmpty()){
            isFilled = false;
            if(errorMesssage.isEmpty()){
                errorMesssage = "Veullez entrer votre mot de passe";
            }else{
                errorMesssage =errorMesssage+ " \nVeullez entrer votre mot de passe";
            }
        }
        errorMessageLabel.setText(errorMesssage);
        return isFilled;
    }
    private TraiterIdentificationResponse identification(){
        boolean isValid = true;
        TraiterIdentificationResponse response=null;
        try{
            response = laSession.traiterIdentification(tfPseudo.getText(),pfPass.getText());
            if(response.leClient!=null){
//                this.setClient(cl);
            }else{
                errorMesssage = "Votre pseudo ou votre mot de passe est incorrect";
                isValid = false;
            }

        }catch (Exception ex){
            errorMesssage = "Votre pseudo ou votre mot de passe est incorrect";
            isValid = false;
        }
        errorMessageLabel.setText(errorMesssage);
        return response;
    }



    public  void startAccueilPerso( Client client, Produit produit) throws IOException,Exception {
        FXMLLoader fxmlLoader = Utils.getFxml(VueJetable.VUEJETABLE);
        VueJetable vue = fxmlLoader.getController();
        FXMLLoader perso = Utils.getFxml(VueJetable.PERSO);
        initializeAccueilPerso(perso.getController(),client,produit);
        vue.loadChildPane(perso);
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.getRoot());
        stage.setTitle("French chic");
        stage.setScene(scene);
        stage.show();
    }


    public void initializeTraitementConnexion(VueJetable v){
        this.btnClose =v.btnClose;
        this.btnLogin = v.btnLogin;
        this.tfPseudo = v.tfPseudo;
        this.pfPass =v.pfPass;
        this.errorMesssage =v.errorMesssage;
        this.errorMessageLabel = v.errorMessageLabel;

        btnClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.exit(0);
            }
        });
        btnLogin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                errorMesssage="";
                if(isFieldFilled()){
                    try {
                        TraiterIdentificationResponse response = identification();
                        if(response.leClient!=null && response.typeEcran.equals( EnumTypeEcran.ECRAN_ACCUEIL_PERSO)){
                            Stage stage = (Stage) btnLogin.getScene().getWindow();
                            stage.close();
                            startAccueilPerso(response.leClient,response.leProduit);
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

    public Client getClient() {
        return client;
    }




    ///////////*********************  AccueilPersoController
    public Produit produit;
    @FXML
    public Spinner<Integer> quantite;
    @FXML
    public Label nomClient;
    @FXML
    public Label nomProduit;
    @FXML
    public Label prix;
    @FXML
    public Button btnAjout;

    @FXML
    public Label stock;

    public void initializeAccueilPerso(VueJetable vuePerso,Client client , Produit produit) {
        initDynamicLabel(vuePerso,client,produit);
        initBtnAjout(vuePerso);
        initChampNumber(vuePerso, produit);
    }
    public void afficherPanier(){

    }

    public void initBtnAjout(VueJetable v){
        v.btnAjout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    Commande commande = new Commande();
                    commande.ajouterProduit(produit, quantite.getValue());
                    FXMLLoader panier = Utils.getFxml(VueJetable.PANIER);
                    TraitementListPanier panierController = panier.getController();
                    panierController.setCommande(commande);
                    panierController.setListe();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    public void initChampNumber(VueJetable v, Produit prod){
        String numberRegex = "\\d+";
        UnaryOperator<TextFormatter.Change> numberFilter = change -> {
            String newText = change.getControlNewText();
            if (Pattern.matches(numberRegex, newText)) {
                return change;
            }
            return null;
        };
//        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 1, numberFilter);
//        v.quantite.getEditor().setTextFormatter(textFormatter);
        v.quantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, prod.getQuantiteEnStock()));
    }

    public void initDynamicLabel(VueJetable v, Client cl,  Produit produit){
        v.prix.setText( (String.valueOf(produit.getPrix())) );
        v.stock.setText(String.valueOf(produit.getQuantiteEnStock()));
        v.nomProduit.setText( produit.getLibelle() );
        v.nomClient.setText(cl.getPrenom()+" "+cl.getNom());
    }
}