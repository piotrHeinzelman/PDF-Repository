package com.heinzelman.pegaz;

import com.heinzelman.pegaz.frontEnds.MyButton;
import com.heinzelman.pegaz.frontEnds.SearchJPanel;
import com.heinzelman.pegaz.frontEnds.Window;
import com.heinzelman.pegaz.tools.ConnectDataSource;
import com.heinzelman.pegaz.tools.FileTools;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

public class MyCommand implements ActionListener , ListSelectionListener {

    private final Model model;
    private Window window;
    private final FileTools fileTools;
    private final ConnectDataSource connectDataSource;
    private String loadedText;
    private String fileName;

    public MyCommand( Model model, Window window, FileTools fileTools, ConnectDataSource connectDataSource ) {
        this.model = model;
        this.window = window;
        this.fileTools = fileTools;
        this.connectDataSource = connectDataSource;

    }

    void setWindow( Window window ) {
        this.window=window;
    }

    @Override
    public void actionPerformed( ActionEvent e ) {


        MyButton myButton = (MyButton) e.getSource();
        switch ( myButton.getType() ) {
            case "SEARCHPROJ":
                //System.out.println( " *** search *** " );
                loadedText = ((SearchJPanel)myButton.getParent()).get_Text().trim().toUpperCase();
                if ( loadedText.equals("")) break;
                model.setProjId(null);
                model.setActiveProj( "" );
                model.setMapOfProj( connectDataSource.getLike( loadedText ));
                window.redraw();
                break;

            case "ADDPROJ":
                //System.out.println( " *** add  *** " );
                loadedText = ((SearchJPanel)myButton.getParent()).get_Text();
                model.setProjId( connectDataSource.addProject( loadedText ));
                model.setActiveProj( loadedText );
                model.setMapOfProj( null );
                window.redraw();
                break;

            case "AddFile":
                fileName =  fileTools.idToFileName( model.getProjId() , ((MyButton) e.getSource()).getRow() );
                System.out.println("addFile:" + fileName );
                fileTools.addFile(fileName);
                window.redraw();
                break;

            case "EditFile":
                fileName =  fileTools.idToFileName( model.getProjId() , ((MyButton) e.getSource()).getRow() );
                fileTools.executeFile(fileName);
                window.redraw();
                break;

            case "DeleteFile":
                fileName = fileTools.idToFileName( model.getProjId() , ((MyButton) e.getSource()).getRow() );
                //System.out.println( "File:" + fileName );

                String[] opcje = { "Usuń", "Anuluj" };
                int rc = JOptionPane.showOptionDialog(
                        null, // okno
                        "Usunąć plik ?", // komunikat
                        "", // tytuł
                        JOptionPane.DEFAULT_OPTION, // rodzaj przycisków
                        JOptionPane.QUESTION_MESSAGE,// typ komunikatu
                        null, // własna ikona (tu: brak)
                        opcje, // własne opcje - przyciski
                        opcje[1]); // domyślny przycisk

                if( rc==1 ) { break; }
                fileTools.deleteFile( fileName );
                window.redraw();
                break;




            default:
                System.out.println("DEFAUL!");
                break;


        }


    }



    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(  !e.getValueIsAdjusting() ) {
            JList jl = (JList) e.getSource();
            String str = (String) jl.getSelectedValue();
            for (Map.Entry<Long, String> m : model.getMapOfProj().entrySet()) {
                if (m.getValue() != str) continue;
                model.setProjId(m.getKey());
                model.setActiveProj(str);
                model.setMapOfProj(null);
            }
            window.redraw();
        }

    }

}
