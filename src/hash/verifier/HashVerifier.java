/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash.verifier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author adds08
 */
public class HashVerifier extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(getClass().getResource("fx_view.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("fx_view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
