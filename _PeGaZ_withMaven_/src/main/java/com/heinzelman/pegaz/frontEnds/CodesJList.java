package com.heinzelman.pegaz.frontEnds;


import com.heinzelman.pegaz.Model;
import com.heinzelman.pegaz.MyCommand;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CodesJList extends JList {

    private Map<String, Long> myDataList = new HashMap<>();

    public void rereshData( Model model ){
        //System.out.println( "?? setListData(  myDataList.keySet().toArray() ); " );
            myDataList.clear();
            setVisible( false );

            setListData(  model.getMapOfProj().values().toArray());

            setSize(490,200);
            setVisible( true );
    }



    public CodesJList( MyCommand myCommand ) {
        super( );
        this.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        this.addListSelectionListener( myCommand );

        setBackground( new Color( 0xd6d9df ) );
    }
}


