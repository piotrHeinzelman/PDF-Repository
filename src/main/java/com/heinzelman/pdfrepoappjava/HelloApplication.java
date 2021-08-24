package com.heinzelman.pdfrepoappjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }



    public void loadPropertiesFromFile()  {
        try {
            URI fileURI = HelloApplication.class.getResource("application.properties").toURI();
            File file = new File( fileURI );
            List<String> lines = Files.readAllLines(  file.toPath()  );
            Iterator<String> iterator = lines.iterator();
            while (iterator.hasNext()){
                System.out.println( iterator.next() );
            }

 //            File fileURI = new File(fileUrlName.toURI());
  //            Files()

        } catch (Exception ex ) { System.out.println( ex );}
    }



    public static void main(String[] args) {
        launch();
    }
}