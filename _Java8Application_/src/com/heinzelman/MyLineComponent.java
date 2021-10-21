package com.heinzelman;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MyLineComponent extends JPanel {

    private static final Color BG = new Color( 196,196,196 );
    private static final Color BORDERLINE = new Color( 96,96,96 );
    private static final Border BORDER = BorderFactory.createLineBorder( BORDERLINE );
    private static final Dimension DIM = new Dimension ( 200 , 50 );
    private static final String EMPTY_TEXT = " | DROP PDF FILE HERE |";

    private static final FileDrop.Listener listener = new FileDropListener();


    private final JTextField textArea;

    public MyLineComponent() throws HeadlessException {
        textArea = new JTextField( ""  );
        textArea.setBorder( BORDER );
        textArea.setPreferredSize( DIM );
        textArea.setBackground( BG );


        new FileDrop( textArea , listener );

        //ImageIcon icon = createImageIcon("images/wavy.gif",  "wavy-line border icon"); //20x22
        //JImageComponent ic = new JImageComponent(myImageGoesHere);
        //imagePanel.add(ic);

        this.add( textArea );
    }
}


/*

        // Prepare buttons
        JTextArea loadFile1 = new JTextArea("[ dodaj plik ]") ;
        FileDrop.Listener listener = new FileDropListener();
        new FileDrop( loadFile1 , listener );


        JButton button2 = new JButton("Button 2");
        button2.addActionListener( new ButtonActionListener());



*
* */