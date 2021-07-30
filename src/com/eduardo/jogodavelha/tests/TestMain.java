package com.eduardo.jogodavelha.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class TestMain extends Application {

    URL url = getClass().getResource("C:\\Users\\Eduardo\\Documents\\GitHub\\jogodavelha_2\\src\\com\\eduardo\\jogodavelha\\view\\sample.fxml");
    URL urlText = getClass().getResource("view/teste.txt");

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource(""));
        stage.setTitle("Jogo da Velha");
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();

    }

    public static void main(String[] args) {

        launch(args);
    }

    public URL getUrl() {
        return url;
    }

    public URL getUrlText() {
        return urlText;
    }

    public void setUrlText(URL urlText) {
        this.urlText = urlText;
    }
}
