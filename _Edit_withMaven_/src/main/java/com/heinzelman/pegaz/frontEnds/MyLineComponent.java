package com.heinzelman.pegaz.frontEnds;


import com.heinzelman.pegaz.MyCommand;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MyLineComponent extends JPanel {

    private static final Color FONTCOLOR_NoFile = new Color ( 20,20,20 );
    private static final Color FONTCOLOR_ExistsFile = new Color ( 200,200,200 );

    private static final Color BG_NoFile = new Color( 196,196,196 );
    private static final Color BG_ExistsFile = new Color( 40,127,25 );

    private static final Color BORDERLINE = new Color( 176,176,176 );
    private static final Border BORDER = BorderFactory.createLineBorder( BORDERLINE );
    private static final Insets INSERTS = new Insets(5, 120, 5, 120);

    private static final Dimension DIM = new Dimension ( 140 , 26 );
    private static final Font FONT = new Font ("Segoe Script", Font.BOLD, 20);


    private final int type;
    private boolean isEmpty = false;

    private JLabel label;
    //private JTextField textArea;
    private final MyButton buttonSave;
    private final MyButton buttonPrintO;
    private final MyButton buttonPrintS;

    private Long baseId;

    public void setBaseId( Long baseId ) { this.baseId = baseId; }

    public MyLineComponent( int type , MyCommand myCommand ) throws HeadlessException {

        this.type = type;

        if (this.type!=-1) this.setSize(380,10);
        if (this.type==-1) this.setSize(360,90);

/*

1. Archiektura okładka
2. Architekura
3. Techniczny okładka
4. Techniczny
5. Koszty okładka
6. Koszty
7. Inne okładka
8. Inne
9. Dodatkowe okładka
0. Dodatkowe

*/

        this.buttonSave = new MyButton(" Save  ", this.type,  "SaveFile" );
        this.buttonSave.addActionListener( myCommand );

        this.buttonPrintO = new MyButton(" drukuj okladki ", 0,  "PrintFileO");
        this.buttonPrintO.addActionListener( myCommand );

        this.buttonPrintS = new MyButton(" drukuj srodki ", 1,  "PrintFileS");
        this.buttonPrintS.addActionListener( myCommand );




        if ( this.type>=1 ) {

            this.label = new JLabel("            ", SwingConstants.LEFT );
            this.label.setPreferredSize( DIM );
            this.add( label );

            switch ( this.type ){
                case 1: this.label.setText( "Archiektura" ) ; break;
                case 2: this.label.setText( "Techniczny" ) ; break;
                case 3: this.label.setText( "Koszty" ) ; break;
                case 4: this.label.setText( "Inne" ) ; break;
                case 5: this.label.setText( "Dodatkowe" ) ; break;
            }
            this.add( buttonSave );
            //System.out.println( "\n\nbuttonSave: " + buttonSave + " : type: " + type + ", baseId: " + baseId );
        }
        if ( this.type==0 ) {
            this.add( buttonPrintO );
            this.add( buttonPrintS );
            return;
        }
        if ( this.type==-1 ){
            JTextArea textArea = new JTextArea( "... napis ...", 5, 25 );
            this.add ( textArea );
            textArea.enable();
            textArea.setEnabled( true );
        }
    }

    public void turnOffButton(){
        this.buttonSave.disable();
        this.buttonSave.setEnabled( false );
    }

    public void turnOnButton(){
        this.buttonSave.enable();
        this.buttonSave.setEnabled( true );
    }



}


