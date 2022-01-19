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

    //private static final String EMPTY_TEXT = "DROP PDF HERE";


    private final int type;
    private boolean isEmpty = false;

    private final JLabel label;
    private final JTextField textArea;
    private final MyButton buttonAddFile;
    private final MyButton buttonEdit;
    private final MyButton buttonDel;

    private Long baseId;

    public void setBaseId( Long baseId ) { this.baseId = baseId; }

    public MyLineComponent( int type , MyCommand myCommand ) throws HeadlessException {

        this.setSize(490,10);

        this.type = type;
        this.label = new JLabel("            ", SwingConstants.LEFT );
        this.label.setPreferredSize( DIM );

        switch ( this.type ){
            case 1: this.label.setText( "1. Archiektura okładka  " ) ; break;
            case 2: this.label.setText( "2. Architekura  " ) ; break;
            case 3: this.label.setText( "3. Techniczny okładka  " ) ; break;
            case 4: this.label.setText( "4. Techniczny  " ) ; break;
            case 5: this.label.setText( "5. Koszty okładka  " ) ; break;
            case 6: this.label.setText( "6. Koszty  " ) ; break;
            case 7: this.label.setText( "7. Inne okładka  " ) ; break;
            case 8: this.label.setText( "8. Inne  " ) ; break;
            case 9: this.label.setText( "9. Dodatkowe okładka  " ) ; break;
            case 0: this.label.setText( "0. Dodatkowe  " ) ; break;
        }


        this.textArea = new JTextField( ""  );
        this.textArea.setBorder( BORDER );
        this.textArea.setPreferredSize( DIM );
        this.textArea.disable();
        this.textArea.setHorizontalAlignment( (int) CENTER_ALIGNMENT );
        //this.setSize(200,20);




        this.buttonAddFile = new MyButton(" Add File ", this.type,  "AddFile" );
        this.buttonAddFile.addActionListener( myCommand );

        this.buttonEdit = new MyButton(" Edit ", this.type,  "EditFile");
        this.buttonEdit.addActionListener( myCommand );

        this.buttonDel = new MyButton(" Delete ", this.type,  "DeleteFile");
        this.buttonDel.addActionListener( myCommand );


            this.add( label );
            this.add( buttonAddFile );
            this.add( textArea );
            this.add( buttonEdit );
            this.add( buttonDel );





    }


    public void refresh( Boolean fileExists ){
        if ( fileExists ) {
            textArea.setText( "plik istnieje" );
            buttonDel.setEnabled(true);
            buttonEdit.setEnabled(true);
        } else {
            textArea.setText( " ** brak pliku ** " );
            buttonDel.setEnabled(false);
            buttonEdit.setEnabled(false);
        }
        textArea.setText( "BaseId: " + baseId );
    }

}

