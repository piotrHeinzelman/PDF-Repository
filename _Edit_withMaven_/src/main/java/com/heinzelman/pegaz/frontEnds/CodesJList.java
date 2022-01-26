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
            myDataList.clear();
            setVisible( false );

            setListData(  model.getMapOfProj().values().toArray());

            setVisible( true );
    }


    public CodesJList( MyCommand myCommand ) {
        super( );

        setFixedCellWidth( 340 );
        setAlignmentX( CENTER_ALIGNMENT );
        this.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        this.addListSelectionListener( myCommand );

        setBackground( new Color( 0xd6d9df ) );
    }
}


