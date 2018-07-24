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
import javafx.scene.control.Alert.AlertType;
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
    private boolean zhodaUsernamu = false;
    private User prihlasenyUzivatel = null;
    
    
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
                prihlasenyUzivatel = null;
            }
            else{
                
                prihlasenyUzivatel = new User(vysledokVyhladania.getString("username"),
                vysledokVyhladania.getString("meno"), vysledokVyhladania.getString("email"),
                vysledokVyhladania.getString("heslo"), vysledokVyhladania.getBoolean("isAdmin"),
                vysledokVyhladania.getBoolean("isActivated"));
                
                System.out.println(prihlasenyUzivatel.getUsername() + prihlasenyUzivatel.isIsActivated());
            }
            connection.close();
        }
  
        catch (Exception e){
        System.out.println(e);
        }
        return true;
    }
    
    
    public boolean registraciaUzivatela(String username, String meno, String email,
            String heslo) throws Exception {
        boolean navrat = false;
        zhodaUsernamu = false;
        try{
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(DB_CONNECTION, USER, PASSWORD);
        Statement statement = connection.createStatement();
        int vysledokStatementu = statement.executeUpdate("INSERT INTO ajapp.users VALUES ('"+ username +
                "', '"+ meno + "', '" + email + "', '"+ heslo + "', false, false);");
        navrat = true;
        connection.close();
        
        } catch (Exception e){
            System.out.println(e);
            if (e.toString().matches("java.sql.SQLIntegrityConstraintViolationException.*")){
                zhodaUsernamu = true;
            navrat = false;
            }
        }
        
    
    return navrat;
    }

    public boolean isZhodaUsernamu() {
        return zhodaUsernamu;
    }

    public void setZhodaUsernamu(boolean zhodaUsernamu) {
        this.zhodaUsernamu = zhodaUsernamu;
    }

    public User getPrihlasenyUzivatel() {
        return prihlasenyUzivatel;
    }
    
    
}
