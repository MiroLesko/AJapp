/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package anjapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author MIRO
 */
public class Logic {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost/ajapp";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    
    public Logic(){
    
    }
    
    public boolean prihlasenieUzivatela(String username, String heslo) throws Exception{
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_CONNECTION, USER, PASSWORD);
        
            System.out.println("Spojenie s databázou úspešné");
            Statement sqlStatement = connection.createStatement();
            
            ResultSet vysledokVyhladania = sqlStatement.executeQuery("SELECT * FROM "
                    + "ajapp.users WHERE username='" + username + "' AND heslo='" + heslo + "';");
            
            if (vysledokVyhladania.next() == false){
                System.out.println("Žiaden záznam nenájdený");
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Vitajte " + username);
                alert.showAndWait();
            }
            connection.close();
        }
  
        catch (Exception e){
        System.out.println(e);
        }
        return true;
    }
    
    
}
