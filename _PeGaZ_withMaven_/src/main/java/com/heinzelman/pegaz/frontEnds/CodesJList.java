package com.heinzelman.pegaz.frontEnds;



import com.heinzelman.pegaz.ConnectDataSource;
import com.heinzelman.pegaz.PegazApplication;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.HashMap;
import java.util.*;

public class CodesJList extends JList {

    private Map<String, Long> myDataList = new HashMap<>();
    private PegazApplication aListener;
    private final ConnectDataSource connectDataSource = new ConnectDataSource();


    public void prepareData( String code ){
        // ***** TU PRZESZUKIWANIE BAZ !! *******
            myDataList.clear();
            if ( !code.trim().equals("")) {
                final Map<Long, String> map = connectDataSource.getLike(code);

                for ( Long key : map.keySet()) {
                    myDataList.put(  map.get(key) , key  );
                }
            }
            setListData(  myDataList.keySet().toArray() );
            setVisible( true );
    }



    public CodesJList(PegazApplication aListener ) {
        super( );
        this.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        this.aListener = aListener;
        this.addListSelectionListener( new ListSelectionListener() {

            @Override
            public void valueChanged( ListSelectionEvent e) {
                if (myDataList.isEmpty()) return;
                if( !e.getValueIsAdjusting() ) {
                    int index = getAnchorSelectionIndex();
                    String name = getModel().getElementAt( index ).toString();
                    Long id =  myDataList.get( name );
                    aListener.listChagneEvent( id, name );
                }
            }
        });

        setBackground( new Color( 0xd6d9df ) );
    }
}


