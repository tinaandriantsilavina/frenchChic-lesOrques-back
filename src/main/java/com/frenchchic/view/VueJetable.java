package com.frenchchic.view;

import com.frenchchic.controller.*;
import com.frenchchic.metier.Panier;
import com.frenchchic.model.Client;
import com.frenchchic.model.Commande;
import com.frenchchic.model.LigneCommande;
import com.frenchchic.model.Produit;
import com.frenchchic.utils.Utils;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class VueJetable extends Application implements Initializable {
//    public VueJetable vueJetable;
    public final static String VUEJETABLE= "/view/viewJetable.fxml";
    public final static String LOGIN ="/view/login.fxml";
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

    public VueJetable vueParent;

    public void setClient(Client client) { this.client = client; }
    public void start(Stage stage) throws Exception {
        try{
            laSession = new Session();
            TraiterConnexionResponse reponse = laSession.traiterConnexion();
            if (reponse.typeEcran == EnumTypeEcran.ECRAN_ACCUEIL) {
                FXMLLoader fxmlLoader = Utils.getFxml(LOGIN);
                Scene scene = new Scene(fxmlLoader.getRoot());
                initializeTraitementConnexion(fxmlLoader.getController());
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

    public void setChildPane(Node node) {
        childPane.getChildren().setAll(node);
    }

    @FXML
    void afficherEcranPanier(ActionEvent event) throws Exception {
        childPane.getChildren().setAll( (AnchorPane) FXMLLoader.load(getClass().getResource("/view/panier.fxml")));
    }

//    @FXML
//    void afficherEcranAccueilPerso(ActionEvent event) throws Exception {
//        FXMLLoader fxmlLoader = Utils.getFxml(PERSO);
//        Parent root = fxmlLoader.getRoot();
//        AnchorPane anchorPane = new AnchorPane();
//        anchorPane.getChildren().add(root);
//        childPane.getChildren().setAll( anchorPane );
//    }

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
        vueParent = fxmlLoader.getController();
        FXMLLoader perso = Utils.getFxml(VueJetable.PERSO);
        initializeAccueilPerso(perso.getController(),client,produit);
        vueParent.loadChildPane(perso);
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
        initInputAcceuilPerso(vuePerso,client,produit);
//        initBtnAjout(vuePerso);
        initChampNumber(vuePerso, produit);
    }
    public void afficherPanier(){

    }

    public void initBtnAjout(VueJetable v){
        vueParent = v;
        v.btnAjout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try{
                    FXMLLoader fxmlLoader = Utils.getFxml(VueJetable.VUEJETABLE);
                    VueJetable vue = fxmlLoader.getController();
                    Commande commande = new Commande();
                    commande.ajouterProduit(produit, quantite.getValue());
                    FXMLLoader panier = Utils.getFxml(VueJetable.PANIER);
                    TraitementAjoutPanierReponse response = laSession.traiterAjoutPanier(produit,quantite.getValue());
                    panier.getController();
                    vueParent.loadChildPane(panier);
//                    panierController.commande = commande;
//                    panierController.setListeCommande();
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

    public void initInputAcceuilPerso(VueJetable v, Client cl,  Produit produit){
        v.prix.setText( (String.valueOf(produit.getPrix())) );
        v.stock.setText(String.valueOf(produit.getQuantiteEnStock()));
        v.nomProduit.setText( produit.getLibelle() );
        v.nomClient.setText(cl.getPrenom()+" "+cl.getNom());
        this.quantite = v.quantite;
        this.produit = produit;
    }




    // ************ Traitement Ajout pannier

//    public Commande commande ;

    public void initalizeAjoutPanier(){

    }

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
    public ReadOnlyDoubleWrapper doubleWrap;

    ObservableList<Panier> tabPanier_listPanier = FXCollections.observableArrayList();

    private void loadData() {
        tabPanier_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tabPanier_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tabPanier_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        tabPanier_montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        tabPanier_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

    public void setListeCommande(Commande commande) {
        ObservableList<LigneCommande> list = FXCollections.observableList( commande.getLesCommandes());
        tabPanier_panierTable.setItems(list);
        tabPanier_libelle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduit().getLibelle()));
        tabPanier_prix.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getProduit().getPrix())));
        tabPanier_quantite.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantite())));
        tabPanier_montant.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantite())));
        tabPanier_stock.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getProduit().getQuantiteEnStock())));
    }

    public void afficherFenetreCommande(VueJetable v, Produit produit, Integer quantite){

    }




    @FXML
    private void afficherCommande(ActionEvent event) {
        try {
            Node source = (Node) event.getSource();
            Scene scene = source.getScene();
            Window window = scene.getWindow();

            if (window instanceof Stage) {
                Stage stage = (Stage) window;
                FXMLLoader loader = (FXMLLoader) stage.getScene().getUserData();
                VueJetable controller = loader.getController();
            }

//            FXMLLoader loader = new FXMLLoader(getClass().getResource(PANIER));
//            loader.setController(this);
//            Parent root = loader.load();
//            // Accédez au contrôleur de la vue si vous avez besoin d'y accéder pour configurer des éléments spécifiques
//            VueJetable controller = loader.getController();
////            controller.initialize();
//            // Appelez la méthode setChildPane pour changer le contenu du childPane
//            setChildPane(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}