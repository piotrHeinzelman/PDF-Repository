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
import java.util.HashSet;
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

        Integer type;


        MyButton myButton = (MyButton) e.getSource();
        switch ( myButton.getType() ) {
            case "SEARCHPROJ":
                loadedText = ((SearchJPanel)myButton.getParent()).get_Text().trim().toUpperCase();
                if ( loadedText.equals("")) break;
                model.setProjId(null);
                model.setActiveProj( "" );
                model.setMapOfProj( connectDataSource.getLike( loadedText ));
                window.redraw();
                break;


            case "SaveFile":
//                fileName =  fileTools.idToFileName( model.getProjId() , ((MyButton) e.getSource()).getRow() );
//                fileTools.executeFile(fileName);
//                window.redraw();

                type = ((MyButton) e.getSource()).getRow();
                type=(type*2)-1;

                String fileNameCover =  fileTools.idToFileName( model.getProjId() ,type );
                String fileNameMeans =  fileTools.idToFileName( model.getProjId() , (type+1)%10 );

                // STAMP !! ?
                fileTools.save_OjoinS_File ( fileNameCover, fileNameMeans, model.getActiveProj() , type, true  );
                break;





            case "PrintFile": case "PrintFileO": case "PrintFileS":

                HashSet<String> setString = new HashSet<>();

                type = ((MyButton) e.getSource()).getRow();
                for ( int i=1;i<6;i++) {
                    int j=i+i-1+type;
                    j=j%10;
                    //System.out.println( j );
                    String name = fileTools.idToFileName( model.getProjId() , j );
                    File f = new File (name);

                    if (!f.exists()) continue;
                    //System.out.println( name );
                    setString.add( name );
                }

                fileTools.save_PDF_File ( setString , model.getActiveProj() , type );

                //String fileNameCover =  fileTools.idToFileName( model.getProjId() ,type );
                //String fileNameMeans =  fileTools.idToFileName( model.getProjId() , (type+1)%10 );

                //fileTools.save_OjoinS_File ( fileNameCover, fileNameMeans, model.getActiveProj() , type );

                //System.out.println( "PrintFile" +  model.getActiveProj() + " : " +model.getProjId() + " type : " + type );
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
