/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package anjapp;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.RED;
import javafx.scene.paint.Paint;
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
            
            Button prihlasitButton = new Button("Prihlásiť");
            prihlasitButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    try {
                        logika.prihlasenieUzivatela(prihlMenoTextField.getText(), hesloTextField.getText());
                    } catch (Exception ex) {
                        Logger.getLogger(Anjapp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
                    
            
            Button registrovatButton = new Button("Registrácia");
            registrovatButton.setOnAction(event ->
                    primaryStage.setScene(registraciaScene()));
            
            VBox centerVBox = new VBox();
            centerVBox.getChildren().addAll(privitanieLabel, prihlasovacieMenoLabel, prihlMenoTextField, hesloLabel,
                    hesloTextField, prihlasitButton, registrovatButton);
            
            
            //prerobiť paddingy a alignmenty
            Label prazdnyLabel = new Label();
            prazdnyLabel.setMinWidth(200);
            
            rozlozenieUvod.setCenter(centerVBox);
            rozlozenieUvod.setLeft(prazdnyLabel);
        return new Scene(rozlozenieUvod, 768, 432);
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
        
        return new Scene(rozlozenieRegistracia, 768, 432);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }



}
