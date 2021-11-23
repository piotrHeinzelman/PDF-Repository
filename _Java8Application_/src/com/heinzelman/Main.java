package com.heinzelman;

import com.heinzelman.frontEnds.Window;

import javax.swing.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static java.awt.EventQueue.invokeLater;

public class Main {
    Object connecttion;

    public static void main  (String[] args) {

        invokeLater(new Runnable() {
            @Override public void run() {
                String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
                //String appConfigPath = rootPath + "com\\heinzelman\\config.ini";
                String appConfigPath = rootPath + "config.ini";
                Properties appProps = new Properties();
                try ( FileInputStream inputStream = new FileInputStream(appConfigPath) ){
                    appProps.load( inputStream );
                } catch(RuntimeException | IOException e ) { e.printStackTrace(); }


                System.out.println( appProps );
                //connection.connect(appProps.getProperty( "serverAddr" ));


                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e ) { e.printStackTrace(); }
                new Window().setVisible(true);


            }
        });

    }

                           /*




                Properties catalogProps = new Properties();
                catalogProps.load(new FileInputStream(catalogConfigPath));


                String appVersion = appProps.getProperty("version");
                assertEquals("1.0", appVersion);

                assertEquals("files", catalogProps.getProperty("c1"));
                        */


        /*
        javax.swing.UIManager$LookAndFeelInfo[Metal javax.swing.plaf.metal.MetalLookAndFeel]
        javax.swing.UIManager$LookAndFeelInfo[Nimbus javax.swing.plaf.nimbus.NimbusLookAndFeel]
        javax.swing.UIManager$LookAndFeelInfo[CDE/Motif com.sun.java.swing.plaf.motif.MotifLookAndFeel]
        javax.swing.UIManager$LookAndFeelInfo[Windows com.sun.java.swing.plaf.windows.WindowsLookAndFeel]
        javax.swing.UIManager$LookAndFeelInfo[Windows Classic com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel]
        */

}

