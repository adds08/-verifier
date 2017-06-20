/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash.verifier;

import hash.function.HashClass;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author adds08
 */
public class fx_viewController implements Initializable {
    
    HashClass hclass;
    private static String _curAlgo;
    private Path path;
    @FXML
    RadioMenuItem radio_md5;
    @FXML
    RadioMenuItem radio_sha1;
    @FXML
    RadioMenuItem radio_sha256;
    @FXML
    RadioMenuItem radio_sha512;
    @FXML
    TextField txt_path;
    @FXML
    TextField txt_hash;
    @FXML
    Label show_hash_function,show_hash_value;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show_hash_value.setWrapText(true);
        System.out.println(radio_md5.getText());
        radio_md5.setSelected(true);
         _curAlgo = "MD5";
        show_hash_function.setText(_curAlgo);
    }
    
    @FXML
    public void select_path(){
        FileChooser fchooser = new FileChooser();
        fchooser.setTitle("Select File");
        path = fchooser.showOpenDialog(new Stage()).toPath();
        System.out.println(path);
        txt_path.setEditable(true);
        txt_path.setText(path.toString());
        
        hclass = new HashClass(_curAlgo, path);
        show_hash_value.setText(hclass.show_hash());
    }
    @FXML
    public void _func_sha1(){
        _curAlgo = "SHA-1";
        show_hash_function.setText(_curAlgo);
        radio_md5.setSelected(false);
        radio_sha256.setSelected(false);
        radio_sha512.setSelected(false);
        hclass = new HashClass(_curAlgo,path); 
        show_hash_value.setText(hclass.show_hash());
    }
    
    @FXML
    public void _func_sha256(){
        _curAlgo = "SHA-256";
        show_hash_function.setText(_curAlgo);        
        radio_md5.setSelected(false);
        radio_sha1.setSelected(false);
        radio_sha512.setSelected(false);
        hclass = new HashClass(_curAlgo,path); 
        show_hash_value.setText(hclass.show_hash());

    }
    
    @FXML
    public void _func_sha512(){        
        radio_md5.setSelected(false);
        radio_sha256.setSelected(false);
        radio_sha1.setSelected(false);
        _curAlgo = "SHA-512";
        show_hash_function.setText(_curAlgo);
        hclass = new HashClass(_curAlgo,path); 
        show_hash_value.setText(hclass.show_hash());
    }
    
    @FXML
    public void _func_md5(){
        radio_sha1.setSelected(false);
        radio_sha256.setSelected(false);
        radio_sha512.setSelected(false);
        _curAlgo = "MD5";
        show_hash_function.setText(_curAlgo);
        hclass = new HashClass(_curAlgo,path);
        show_hash_value.setText(hclass.show_hash());
    }
    
    @FXML
    public void _hashVerify(){
        String hashStatus = "Hash";
        boolean isSame=false;
        if(txt_hash.getText().length()>0 && txt_path.getText().length()>0){           
            System.out.println("Hash original : "+txt_hash.getText()+" :: "+txt_hash.getText().length());
            if(hclass.compareHash(txt_hash.getText().trim())){
                System.out.println("match");
                hashStatus+=" match!";
                isSame=true;
            }
            else{
                System.out.println("unmatch");
                hashStatus+=" NOT match!";
            }
            HashDialog hashD = new HashDialog(hashStatus,hclass.show_hash(),txt_hash.getText(),isSame);
            hashD.showAndWait();
        }
    }
}
