package com.heinzelman.pdfrepoappjava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloApplicationTest {

    @Test
    void myFirstMethod() {
    HelloApplication myApp = new HelloApplication();
        myApp.loadProperties();
        System.out.println( myApp.properties );
    }


}



     /*
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

         */