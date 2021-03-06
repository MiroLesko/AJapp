/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package anjapp;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.RED;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author MIRO
 */
public class Anjapp extends Application {
    
    Background pozadie = new Background(new BackgroundImage(new Image("images/bgimage.jpg"), BackgroundRepeat.SPACE, 
                BackgroundRepeat.SPACE, BackgroundPosition.CENTER, BackgroundSize.DEFAULT));
    Logic logika = new Logic();
    Stage primaryStage = new Stage();

    
    @Override
    public void start(Stage stage) {
            
            primaryStage.setTitle("Výuková aplikácia anglického jazyka");
            primaryStage.setScene(uvodnaScene());
            primaryStage.show();
        
        
    }
    
    public Scene uvodnaScene(){
            BorderPane rozlozenieUvod = new BorderPane();
            rozlozenieUvod.setBackground(pozadie);
            Label privitanieLabel = new Label("Vitajte vo výukovej aplikácií anglického jazyka");
            Label prihlasovacieMenoLabel = new Label("Prihlasovacie meno");
            Label hesloLabel = new Label("Heslo");
            
            TextField prihlMenoTextField = new TextField();
            prihlMenoTextField.setMaxWidth(200);
            PasswordField hesloTextField = new PasswordField();
            hesloTextField.setMaxWidth(200);
            
            
            Label infoLabel = new Label();

            ImageView prihlasitButton = new ImageView(new Image("images/login.png"));
            prihlasitButton.setOnMouseEntered(event ->
                    prihlasitButton.setImage(new Image("images/login_hover.png"))
             );
            
            prihlasitButton.setOnMouseExited(event ->
                    prihlasitButton.setImage(new Image("images/login.png"))
             );
            
            
            prihlasitButton.setOnMouseReleased(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    try {
                        logika.prihlasenieUzivatela(prihlMenoTextField.getText(), hesloTextField.getText());
                        if(logika.getPrihlasenyUzivatel() != null){
                            if(logika.getPrihlasenyUzivatel().isIsActivated() == true){
                                logika.getLogger().zaloguj("User " + prihlMenoTextField.getText() + " has been successfully logged in from "
                                        + logika.ipExternaAdresa());
                                if(logika.getPrihlasenyUzivatel().isIsAdmin() == true){
                                    primaryStage.setScene(zakladnaAdminScene());
                                } else {
                                    primaryStage.setScene(zakladnaUserScene());
                                    }
                            } else {
                                infoLabel.setText("Prosím, počkajte na aktiváciu účtu administrátorom aplikácie.");
                                logika.getLogger().zaloguj("Login of user " + prihlMenoTextField.getText() + " failed: user"
                                        + " has not been activated yet");
                            }
                        } else {
                                infoLabel.setText("Užívateľ neexistuje, prosím zaregistrujte sa!");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(Anjapp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
                    
            
            
            ImageView registrovatIw = new ImageView(new Image("images/register.png"));
            registrovatIw.setOnMouseReleased(event -> 
                primaryStage.setScene(registraciaScene()));
            
            
            HBox buttonyHBox = new HBox();
            buttonyHBox.getChildren().addAll(prihlasitButton, registrovatIw);
            buttonyHBox.setAlignment(Pos.CENTER);
            buttonyHBox.setSpacing(15);
            
            VBox centerVBox = new VBox();
            centerVBox.setAlignment(Pos.CENTER);
            centerVBox.getChildren().addAll(privitanieLabel, prihlasovacieMenoLabel, prihlMenoTextField, hesloLabel,
                    hesloTextField, buttonyHBox, infoLabel);
            
            
            rozlozenieUvod.setCenter(centerVBox);
         
        return new Scene(rozlozenieUvod, 900, 546);
    }

    public Scene registraciaScene(){
        BorderPane rozlozenieRegistracia = new BorderPane();
        rozlozenieRegistracia.setBackground(pozadie);
        
        Label regLabel = new Label("Registrujte sa");
        Label menoLabel = new Label("Vaše meno");
        Label usernameLabel = new Label("Nickname");
        Label emailLabel = new Label("Email");
        Label hesloLabel = new Label("Heslo");
        Label hesloAgLabel = new Label("Zopakujte heslo");
        
        TextField menoTf = new TextField();
        TextField usernameTf = new TextField();
        TextField emailTf = new TextField();
        PasswordField hesloPf = new PasswordField();
        PasswordField hesloAgPf = new PasswordField();
        
        Button registrovatButton = new Button("Registrovať"); 
        Label zhodaHesielLabel = new Label();
        zhodaHesielLabel.setMinHeight(30);
        
        HBox buttonyHBox = new HBox();
        Button spatButton = new Button("Späť");
        buttonyHBox.getChildren().addAll(registrovatButton, spatButton);
        
        VBox centralnyVBox = new VBox();
        centralnyVBox.getChildren().addAll(regLabel, menoLabel, menoTf, usernameLabel, usernameTf,
                emailLabel, emailTf, hesloLabel, hesloPf, hesloAgLabel, hesloAgPf, buttonyHBox,
                zhodaHesielLabel);
        centralnyVBox.setMaxWidth(350);
        
        spatButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(uvodnaScene());
            }
        });
        
        registrovatButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               zhodaHesielLabel.setText("");
               logika.setZhodaUsernamu(false);
                       
               if(menoTf.getText().equals("") || usernameTf.getText().equals("") ||
                       emailTf.getText().equals("") || hesloPf.getText().equals("")){
                   zhodaHesielLabel.setText("Všetky polia musia byť vyplnené!");
                   return;
               }
               
               if(hesloPf.getText().equals(hesloAgPf.getText())){
                   if(emailTf.getText().matches(".*@.*\\..*")){
                       try{
                           logika.registraciaUzivatela(usernameTf.getText(), menoTf.getText(), 
                           emailTf.getText(), hesloPf.getText());
                              if(logika.isZhodaUsernamu() == true){
                                zhodaHesielLabel.setText("Nickname už registrovaný");
                              }else {
                                zhodaHesielLabel.setText("Registrácia úspešná, pokračujte prihlásením!");  
                               /* Chyba pri navazovaní SSL spojenia, missing certificate
                                new MailSender().generateAndSendEmail("Nová registrácia v aplikácií anglického jazyka",
                                    "Nová registrácia - test <br><br> Prosím aktivujte užívateľa");
                              */
                              }
                   
                           } catch (Exception e){
                             System.out.println(e);
                           }
                   
                    } else {
                       zhodaHesielLabel.setText("Neplatný email!");
                   }
                 
               } else{
                   zhodaHesielLabel.setText("Heslá sa musia zhodovať!");      
               }     
            }
        });

        rozlozenieRegistracia.setCenter(centralnyVBox);
        
        return new Scene(rozlozenieRegistracia, 900, 546);
    }
    
    
    public Scene zakladnaUserScene(){
        BorderPane zaklUserRozlozenie = new BorderPane();
        zaklUserRozlozenie.setBackground(pozadie);
        Label vitajteLabel = new Label("Vitajte " + logika.getPrihlasenyUzivatel().getMeno());
        vitajteLabel.setFont(Font.font(20));
        vitajteLabel.setTextFill(Paint.valueOf("BLACK"));
        
        ImageView profilIw = new ImageView(new Image("images/profile.png"));
        ImageView feedbackIw = new ImageView(new Image("images/feedback.png"));
        ImageView level1Iw = new ImageView(new Image("images/level1.png"));
        ImageView level2Iw = new ImageView(new Image("images/level2.png"));
        
        HBox ponukaLevelovHBox = new HBox();
        ponukaLevelovHBox.setSpacing(20);
        ponukaLevelovHBox.setAlignment(Pos.CENTER);
        ponukaLevelovHBox.getChildren().addAll(level1Iw, level2Iw);
        
        VBox pravyPanelNastrojovVBox = new VBox();
        pravyPanelNastrojovVBox.setSpacing(10);
        pravyPanelNastrojovVBox.getChildren().addAll(profilIw, feedbackIw);
        
        zaklUserRozlozenie.setCenter(ponukaLevelovHBox);
        zaklUserRozlozenie.setRight(pravyPanelNastrojovVBox);
        zaklUserRozlozenie.setTop(vitajteLabel);
        return new Scene(zaklUserRozlozenie, 900, 546);
    }
    
        public Scene zakladnaAdminScene(){
        BorderPane zaklUserRozlozenie = new BorderPane();
        zaklUserRozlozenie.setBackground(pozadie);
        Label vitajteLabel = new Label("Vitajte " + logika.getPrihlasenyUzivatel().getMeno());
        vitajteLabel.setFont(Font.font("Verdana",20));
        vitajteLabel.setTextFill(Paint.valueOf("WHITE"));
        
        ImageView profilIw = new ImageView(new Image("images/profile.png"));
        ImageView feedbackIw = new ImageView(new Image("images/feedback.png"));
        ImageView spravaUctovIw = new ImageView(new Image("images/maintain.png"));
        ImageView historiaIw = new ImageView(new Image("images/history.png"));
        
        
        ImageView level1Iw = new ImageView(new Image("images/level1.png"));
        level1Iw.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
               logika.naplnBatohUlohami(1);
               primaryStage.setScene(level1Scene());
            }
            });        
           
        
        ImageView level2Iw = new ImageView(new Image("images/level2.png"));
        
        spravaUctovIw.setOnMouseReleased(event ->
                primaryStage.setScene(spravaUctovScene())
        );
        
        HBox ponukaLevelovHBox = new HBox();
        ponukaLevelovHBox.setSpacing(20);
        ponukaLevelovHBox.setAlignment(Pos.CENTER);
        ponukaLevelovHBox.getChildren().addAll(level1Iw, level2Iw);
        
        VBox pravyPanelNastrojovVBox = new VBox();
        pravyPanelNastrojovVBox.setSpacing(10);
        pravyPanelNastrojovVBox.getChildren().addAll(profilIw, feedbackIw, spravaUctovIw, historiaIw);
        
        zaklUserRozlozenie.setCenter(ponukaLevelovHBox);
        zaklUserRozlozenie.setRight(pravyPanelNastrojovVBox);
        zaklUserRozlozenie.setTop(vitajteLabel);
        return new Scene(zaklUserRozlozenie, 900, 546);
    }
        
        public Scene spravaUctovScene(){
            logika.getZoznamVybranychUserov().clear();
            BorderPane rozlozenieBP = new BorderPane();
            rozlozenieBP.setBackground(pozadie);
           
           ImageView aktivovatIw = new ImageView(new Image("images/activate.png")); 
           ImageView zrusitIw = new ImageView(new Image("images/deactivate.png"));
          
                        
           aktivovatIw.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                if(logika.getZoznamVybranychUserov().isEmpty()){
                    System.out.println("Zoznam prázdny");
                } else {
                    logika.aktivaciaUserov(logika.getZoznamVybranychUserov());
                    primaryStage.setScene(spravaUctovScene());
                }
            }
            });        
           
           zrusitIw.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                if(logika.getZoznamVybranychUserov().isEmpty()){
                    System.out.println("Zoznam prázdny");
                } else {
                    logika.deaktivaciaUserov(logika.getZoznamVybranychUserov());
                    primaryStage.setScene(spravaUctovScene());
                }
            }
            });        
           
           
           ImageView aktualizovatIw = new ImageView(new Image("images/refresh.png"));
           ImageView headingIw = new ImageView(new Image("images/zoznam.png"));
           
           
           ImageView spatIw = new ImageView(new Image("images/back.png"));
           spatIw.setOnMouseReleased(event ->
                   primaryStage.setScene(zakladnaAdminScene())
           );
           
           ObservableList<HBox> zoznamUserovObser = FXCollections.observableArrayList();
           for (User user : logika.zoznamUzivatelov()){
               System.out.println("Prechádzam zoznam");
               HBox polozkaZoznamu = new HBox();
               RadioButton rb = new RadioButton();
               Label label1 = new Label(user.getUsername());
               label1.setMinWidth(150);
               Label label2 = new Label(user.getMeno());
               label2.setMinWidth(150);
               ImageView puntik = null;
               if (user.isIsActivated() == true){
                   puntik = new ImageView(new Image("images/green.png"));
               } else {
                   puntik = new ImageView(new Image("images/red.png"));
               }
              
               rb.setOnAction(event -> 
                   logika.getZoznamVybranychUserov().add(user));
               
                    
               polozkaZoznamu.getChildren().addAll(rb, label1, label2, puntik);
              zoznamUserovObser.add(polozkaZoznamu);
              
           }
           ListView lw = new ListView(zoznamUserovObser);
           
           
           
           VBox pravyPanelNastrojovVBox = new VBox();
           pravyPanelNastrojovVBox.setSpacing(10);
           pravyPanelNastrojovVBox.getChildren().addAll(aktivovatIw, zrusitIw, aktualizovatIw);
           
           rozlozenieBP.setCenter(lw);
           rozlozenieBP.setTop(headingIw);
           rozlozenieBP.setRight(pravyPanelNastrojovVBox);
           rozlozenieBP.setBottom(spatIw);
           
            
        return new Scene(rozlozenieBP, 900, 546);
        }
        
        public Scene level1Scene (){
            BorderPane hlavnyBorderPane = new BorderPane();
            hlavnyBorderPane.setBackground(pozadie);
            
            Button uloha1Button = new Button("Uloha 1");
            uloha1Button.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    logika.zbuildujUlohuVocabulary(logika.najdiUlohu("UL00000001"));
                    
                }
            });
            
            Button uloha2Button = new Button("Uloha 2");
            Button spatButton = new Button("Spat");
            spatButton.setOnAction(event -> 
                primaryStage.setScene(zakladnaAdminScene()));
            
            HBox ponukaHBox = new HBox();
            
            ponukaHBox.getChildren().addAll(uloha1Button, uloha2Button);
            hlavnyBorderPane.setCenter(ponukaHBox);
            hlavnyBorderPane.setBottom(spatButton);
           
            
            return new Scene(hlavnyBorderPane, 900, 546);
        }
        
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }



}
