/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash.verifier;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author adds08
 */
public class HashDialog extends Stage implements Initializable{
    
    String msgLabel,hashCalc,hashTaken;
    boolean isSame;
    @FXML
    Label messageLabel,dialog_hash_calc,dialog_hash_taken;
      
    public HashDialog(String label,String _hashCalc,String _hashTaken,boolean isSame){   
        this.msgLabel = label;
        this.hashCalc = _hashCalc;
        this.hashTaken = _hashTaken;
        this.isSame = isSame;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fx_dialog.fxml"));
        loader.setController(this);
        try
        {
            setScene(new Scene((Parent) loader.load()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        set_dialog();
    }
    
    private void set_dialog(){
        if(!isSame){
            messageLabel.setTextFill(Color.RED);
        }
        else{
            messageLabel.setTextFill(Color.BLUE);
        }
        messageLabel.setText(msgLabel);
        dialog_hash_calc.setText(hashCalc);
        dialog_hash_taken.setText(hashTaken);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.centerOnScreen();
    }
    
    @FXML
    private void onAction(){
        this.close();
    }
}
