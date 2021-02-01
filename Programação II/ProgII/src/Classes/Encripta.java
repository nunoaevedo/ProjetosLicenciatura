/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.mycompany.trabalho_programacao.Painel_CriarUser;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nuno
 */
public class Encripta {
    
    public static String encriptaPassword(String password){
        String generatedPassword = "";
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            
            StringBuilder sb = new StringBuilder();
            
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
            
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Painel_CriarUser.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        return generatedPassword;
        
    }
    
}
