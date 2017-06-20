/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash.function;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author adds08
 */
public final class HashClass{
    
    private MessageDigest msgDigest;
    private File hFile;
    private String _hash;
    
    public HashClass(String _algo,File cfile){
        try {
            msgDigest = MessageDigest.getInstance(_algo.toUpperCase());
            if(getFile(cfile)){
                calculate_hash();
            }
            else{
                System.out.println("Error in file path");
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String show_hash(){
        return _hash;
    }
    
    private void calculate_hash(){
        try {
            String path = hFile.getAbsolutePath();
            msgDigest.update(Files.readAllBytes(Paths.get(path)));
            byte[] digest = msgDigest.digest();
            _hash = DatatypeConverter.printHexBinary(digest).toLowerCase().trim();
            System.out.println(_hash);
        } catch (IOException ex) {
            Logger.getLogger(HashClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean compareHash(String fxhash){
        System.out.println("Hash calculated : "+_hash+" :: "+_hash.length());
        if(fxhash.isEmpty()){
            System.out.println("Enter a hash");
            return false;
        }
        else{
            if(fxhash.equals(_hash)){
                return true;
            }
            return false;
        }
    }
    
    protected boolean getFile(File file){
        if(file.exists()){
            System.out.println(file.getAbsoluteFile());
            hFile = file;
            return true;
        }
        return false;
    }
    
}
