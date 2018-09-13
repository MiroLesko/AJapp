/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package anjapp;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 *
 * @author MIRO
 */
public class Logger {
    
    
    public Logger(){;
    }
    
    public void zaloguj (String znenieLogu) throws IOException{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
        LocalDateTime aktualnyDatumCas = LocalDateTime.now();
        
        try{
            FileWriter fileWriter = new FileWriter("C:\\Users\\MIRO\\Documents\\NetBeansProjects\\ANJAPP\\src\\log\\log.txt", true);
            fileWriter.append("\n"+aktualnyDatumCas.format(dtf)+ " " + znenieLogu);
            fileWriter.close();
        
        } catch(Exception e){
            System.out.println(e);
        }
        

    
    } 
    
    
}
