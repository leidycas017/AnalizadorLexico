package sample;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private AnchorPane escenaUno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //listaPalabras = new ArrayList<Token>();
    }

     public void callLexico(ActionEvent event) throws IOException {
         escenaUno.setVisible(true);
         AnalizadorLexico.showWindows();

     }
    public void callExit(ActionEvent event){
        Platform.exit();
        System.exit(0);
    }
}
