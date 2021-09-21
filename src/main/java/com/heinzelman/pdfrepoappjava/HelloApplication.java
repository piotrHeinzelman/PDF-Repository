package com.heinzelman.pdfrepoappjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class HelloApplication extends Application {

    Properties properties;

    @Override
    public void start(Stage stage) throws IOException {

        // https://www.youtube.com/watch?v=fMa-GK3AfEc
        // package to artifact javaFX

        loadProperties();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }



    public void loadProperties()  {
        try ( InputStream input = new FileInputStream( this.getClass().getResource( "application.properties" ).toURI().getPath().toString()  ) ){
            properties = new Properties();
            properties.load( input );
            System.out.println( "files in: " + properties.getProperty("targetFolder") );
        } catch( Exception ex ) { System.out.println( ex ); }
    }


    public static void main(String[] args) {
        launch();
    }
}