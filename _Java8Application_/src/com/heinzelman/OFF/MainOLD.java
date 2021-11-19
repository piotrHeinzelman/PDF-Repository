package com.heinzelman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainOLD implements ActionListener  {

    public static final String PATH = "C:\\temp\\_PDFSYSTEM_\\";

    private  Map<Long, String> listProj;
    private  Long selectedId;

    private JButton searchButton;
    private JTextField textField;
    private JList jList;



    public static void main  (String[] args) {
        MainOLD app = new MainOLD();
        app.run();
    }




    public void run() {

        //initialize
        listProj = new HashMap<>();
        selectedId = null;

        MySearchComponent searchComponent = new MySearchComponent();
                          searchComponent.setActionListener( this );
        MyJFrame frame = new MyJFrame( "PDF Repo"  );

        JPanel topLine = (JPanel) frame.addToN(  new JPanel());
        topLine.setComponentOrientation( ComponentOrientation.LEFT_TO_RIGHT );

        JPanel centerLine = (JPanel) frame.addToC( new JPanel());
        centerLine.setComponentOrientation( ComponentOrientation.LEFT_TO_RIGHT);


        MySearchComponent mySearchComponent = new MySearchComponent();
        mySearchComponent.setMyMaster( this );
        mySearchComponent.add( searchComponent.getJLabel()  );


        System.out.println( this );
        textField = (JTextField) topLine.add( searchComponent.getTextField());
        searchButton = (JButton) topLine.add( searchComponent.getSearchButton() );


        String[] aryData = new String[22];
        //aryData[0]="abc";
        //aryData[1]="def";

        jList =  new JList< String >( aryData );
        jList.setFixedCellWidth( 400 );
        jList.setAlignmentX( Component.CENTER_ALIGNMENT );
        jList.setAlignmentY( Component.CENTER_ALIGNMENT );
        frame.addToC( jList );

        frame.setVisible( true );


    }










    @Override
    public void actionPerformed( ActionEvent e ) {
        if ( e.getSource().equals( searchButton ) && !textField.getText().trim().equals("")) {
            String search = textField.getText().trim().toUpperCase().toString();
            // dataList.loadData ( findAllItemsLike ( search ));

        }

        System.out.println(  e.getSource() );
        //System.out.println( e );
        /*
            JButton myButton = ( JButton ) e.getSource();
            MySearchComponent mySearchComponent = ( MySearchComponent ) myButton.getParent();
            JTextField myTextField = ( JTextField ) mySearchComponent.getComponent( 1 );

            String text = myTextField.getText().trim().toUpperCase();

            if ( text.equals("") ) return;
            System.out.println( "Text: " + text );

            listIds.clear();
            switch ( text ){
                case"A": listIds.put( 1L, "ProjektPierwszy!" ); break;
                case"AA": listIds.put( 1L, "ProjektPierwszy!" ); listIds.put( 2L, "Drugi!" ); break;
                case"AAA": listIds.put( 1L, "ProjektPierwszy!" ); listIds.put( 2L, "Drugi!" ); listIds.put( 3L, "Trzeci!" ); break;
            }

            System.out.println( listIds );
            */

    }





    private Map<Long,String> findAllItemsLike( String searchString ){

        Map<Long, String > dataRows = new HashMap<>();
        dataRows.put( 1L,"Proj1" );
        dataRows.put( 4L,"Proj2" );
        dataRows.put( 14L,"Proj3" );
        dataRows.put( 33L,searchString );
        return dataRows;

    }




}

