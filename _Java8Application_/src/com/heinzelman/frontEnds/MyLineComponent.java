package com.heinzelman.frontEnds;

import com.heinzelman.AListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TooManyListenersException;

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

    private static final String EMPTY_TEXT = "DROP PDF HERE";


    private final int type;
    private boolean isEmpty = false;

    private final JLabel label;
    private final JTextField textArea;
    private final JButton buttonAddFile;
    private final JButton buttonEdit;
    private final JButton buttonDel;


    public MyLineComponent( int type , Long baseId, AListener aListener ) throws HeadlessException {

        this.type = type;
        this.label = new JLabel("            ", SwingConstants.RIGHT );
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

        this.textArea.setDropTarget( new DropTarget(){
            @Override
            public synchronized void drop( DropTargetDropEvent dtde ) {
                aListener.DropFile( type , baseId , dtde );
            }
        });





        this.buttonAddFile = new JButton(" Add File ");
        this.buttonAddFile.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aListener.addFile( type, baseId, e );
            }
        });



        this.buttonEdit = new JButton(" Edit ");
        this.buttonEdit.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aListener.fileEdit( type , baseId , e );
            }
        });

        this.buttonDel = new JButton(" Delete ");
        this.buttonDel.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aListener.fileDelete( type, baseId, e );
            }
        });


            this.add( label );
            this.add( buttonAddFile );
            this.add( textArea );
            this.add( buttonEdit );
            this.add( buttonDel );

            this.getInsets( INSERTS );

        this.turnToNoFile();
    }


   public void  turnToExistFile(){
       isEmpty = false;
       textArea.setText( "DATA:" );
       textArea.setBackground( BG_ExistsFile );
       textArea.setDisabledTextColor( FONTCOLOR_ExistsFile );
       buttonDel.enable();
       buttonEdit.disable();
    }


    public void turnToNoFile(){
        isEmpty = true;
        textArea.setText( EMPTY_TEXT );
        textArea.setBackground( BG_NoFile );
        textArea.setDisabledTextColor( FONTCOLOR_NoFile );
        buttonDel.disable();
        buttonEdit.enable();
    }
}

