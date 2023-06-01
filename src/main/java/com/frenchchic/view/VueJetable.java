package com.frenchchic.view;

import com.frenchchic.controller.EnumTypeEcran;
import com.frenchchic.controller.Session;
import com.frenchchic.controller.TraitementAccueilPerso;
import com.frenchchic.controller.TraiterConnexionResponse;
import com.frenchchic.controller.TraitementListPanier;
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
    public final static String vueJetable= "/view/viewJetable.fxml";
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

    public void startVueJetable() throws IOException, Exception {
        Stage stage = new Stage();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/viewJetable.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        VueJetable temp = fxmlLoader.getController();
        stage.setTitle("French chic");
        stage.setScene(scene);
        stage.show();
    }
    public void loadChildPane(String fxmlPath) {
        try {
            childPane.getChildren().clear();
            childPane.getChildren().add(FXMLLoader.load(getClass().getResource(fxmlPath)));
        } catch (IOException e) {
            e.printStackTrace();
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

    public void loadChildPane(Parent parent) {
        childPane.getChildren().clear();
        childPane.getChildren().add(parent);
    }
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

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
















    // TraitementConnexion

    @FXML
    private Button btnClose;

    @FXML
    private TextField tfPseudo;

    @FXML
    private PasswordField pfPass;

    @FXML
    private Button btnLogin;

    @FXML
    private Label errorMessageLabel;

    private String errorMesssage="";





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
    private boolean isValid(){
        boolean isValid = true;
        try{
            Client cl= new Client().rechercheClientParPseudo(tfPseudo.getText(),pfPass.getText());
            if(cl!=null){
                this.setClient(cl);
            }else{
                errorMesssage = "Votre pseudo ou votre mot de passe est incorrect";
                isValid = false;
            }

        }catch (Exception ex){
            errorMesssage = "Votre pseudo ou votre mot de passe est incorrect";
            isValid = false;
        }
        errorMessageLabel.setText(errorMesssage);
        return isValid;
    }



    public  void startAccueilPerso(Client client, Produit produit) throws IOException,Exception {
//        Produit produitt = new Produit().rechercheProduitDuJour();
        FXMLLoader fxmlLoader = Utils.getFxml(VueJetable.vueJetable);
        VueJetable vue = fxmlLoader.getController();
        FXMLLoader perso = Utils.getFxml(VueJetable.PERSO);
        TraitementAccueilPerso persoController = perso.getController();
        persoController.setVueParent(vue);
        vue.setClient(client);
//        persoController.setNomClient(client.getPrenom()+" "+client.getNom());
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
                if(isFieldFilled() && isValid()){
                    try {
                        Stage stage = (Stage) btnLogin.getScene().getWindow();
                        stage.close();
                        startAccueilPerso(null,null);
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
    private Produit produit;
    @FXML
    private Spinner<Integer> quantite;
    @FXML
    private Label nomClient;
    @FXML
    private Label nomProduit;
    @FXML
    private Label prix;
    @FXML
    private Button btnAjout;

    @FXML
    private Label stock;

    public void initializeAccueilPerso() {
        produit = new Produit().rechercheProduitDuJour();
        initDynamicLabel();
        initBtnAjout();
        initChampNumber();
    }
    public void afficherPanier(){

    }

    public void initBtnAjout(){
        btnAjout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    Commande commande = new Commande();
                    commande.ajouterProduit(produit, getQuantite());
                    FXMLLoader panier = Utils.getFxml(VueJetable.PANIER);
                    TraitementListPanier panierController = panier.getController();
                    panierController.setCommande(commande);
                    panierController.setListe();
//                    getVueParent().loadChildPane(panier);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }




    public void initChampNumber(){
        String numberRegex = "\\d+";
        UnaryOperator<TextFormatter.Change> numberFilter = change -> {
            String newText = change.getControlNewText();
            if (Pattern.matches(numberRegex, newText)) {
                return change;
            }
            return null;
        };
        TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), 1, numberFilter);
        quantite.getEditor().setTextFormatter(textFormatter);
        quantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, produit.getQuantiteEnStock()));
    }

    public void initDynamicLabel(){
        produit = new Produit().rechercheProduitDuJour();
        this.setPrix(String.valueOf(produit.getPrix()));
        this.setStock(String.valueOf(produit.getQuantiteEnStock()));
        this.setNomProduit(produit.getLibelle());
    }

    public Integer getQuantite() {
        return quantite.getValue();
    }

    public void setQuantite(Spinner<Integer> quantite) {
        this.quantite = quantite;
    }

    public Label getNomClient() {
        return nomClient;
    }



    public Label getNomProduit() {
        return nomProduit;
    }

    public VueJetable setNomProduit(String nomProduit) {
        this.nomProduit.setText( nomProduit );
        return this;
    }

    public Label getPrix() {
        return prix;
    }

    public VueJetable setPrix(String prix) {
        this.prix.setText( prix );
        return this;
    }

    public String getStock() {
        return this.stock.getText();
    }

    public VueJetable setStock(String stock) {
        this.stock.setText(stock);
        return this;
    }
}