/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash.function;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private String _hash;
    private Path realPath;
    
    public HashClass(String _algo,Path path){
        try {
            msgDigest = MessageDigest.getInstance(_algo.toUpperCase());
            if(getFile(path)){
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
        /* try {
            msgDigest.update(Files.readAllBytes(realPath));
            byte[] digest = msgDigest.digest();
            _hash = DatatypeConverter.printHexBinary(digest).toLowerCase().trim();
            System.out.println(_hash);
        } catch (IOException ex) {
            Logger.getLogger(HashClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        try (InputStream inputStream = Files.newInputStream(realPath)) {
        byte[] bytesBuffer = new byte[1024];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(bytesBuffer)) != -1) {
            msgDigest.update(bytesBuffer, 0, bytesRead);
        }
        byte[] hashedBytes = msgDigest.digest();
        _hash = DatatypeConverter.printHexBinary(hashedBytes).toLowerCase().trim();
        System.out.println(_hash);
    } catch (IOException ex) {
        System.out.println(ex);
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
    
    protected boolean getFile(Path path){
        if(!path.toString().isEmpty()){
            realPath = path;
            return true;
        }
        return false;
    }
    
}
