package com.heinzelman.frontEnds;

import com.heinzelman.AListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CodesJList extends JList {

    private Map<String, Long> myDataList = new HashMap<>();
    private AListener aListener;


    public void prepareData( String code ){
        // ***** TU PRZESZUKIWANIE BAZ !! *******
            myDataList.clear();
            if ( !code.trim().equals("")) {
                myDataList.put(   "Arch0123_A" + code , 123L  );
                myDataList.put(   "Arch0123_B" + code , 124L  );
                myDataList.put(   "Arch0123_C" + code , 125L  );

            }
            setListData(  myDataList.keySet().toArray() );
            setVisible( true );
    }



    public CodesJList( AListener aListener ) {
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


