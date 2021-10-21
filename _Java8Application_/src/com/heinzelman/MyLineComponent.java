package com.heinzelman;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyLineComponent extends JPanel {

    private static final Color BG_NoFile = new Color( 196,196,196 );
    private static final Color BG_ExistsFile = new Color( 40,127,25 );
    private static final Color BORDERLINE = new Color( 176,176,176 );
    private static final Border BORDER = BorderFactory.createLineBorder( BORDERLINE );
    private static final Dimension DIM = new Dimension ( 170 , 32 );
    private static final String EMPTY_TEXT = " DROP PDF FILE HERE ";

    private static final FileDrop.Listener listener = new FileDropListener();

    private final JTextField textArea;
    private final JButton buttonEdit;
    private final JButton buttonDel;


    public MyLineComponent() throws HeadlessException {
        this.textArea = new JTextField( ""  );
        this.textArea.setBorder( BORDER );
        this.textArea.setPreferredSize( DIM );
        //


        this.buttonEdit = new JButton(" Edit ");
        buttonEdit.addActionListener( new ButtonEditActionListener());


        this.buttonDel = new JButton(" Delete ");
        buttonDel.addActionListener( new ButtonDelActionListener());

        //ImageIcon icon = createImageIcon("images/wavy.gif",  "wavy-line border icon"); //20x22
        //JImageComponent ic = new JImageComponent(myImageGoesHere);
        //imagePanel.add(ic);

        this.add( textArea );
        this.add( buttonEdit );
        this.turnToNoFile();
    }


    public void  turnToExistFile(){
        this.textArea.setBackground( BG_NoFile );
        new FileDrop( this.textArea , listener );
    }

    public void turnToNoFile(){
        for (ActionListener al : this.textArea.getActionListeners()) {
            System.out.println( al );
        }
        this.textArea.setText( EMPTY_TEXT );
        this.textArea.setBackground( BG_NoFile );
        new FileDrop( this.textArea , listener );
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





 class ButtonEditActionListener implements ActionListener {

    private static DropTarget dropTarget;
    private static DropTargetListener dropTargetListener;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println( e );
    }

}




 class ButtonDelActionListener implements ActionListener {

    private static DropTarget dropTarget;
    private static DropTargetListener dropTargetListener;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println( e );
    }

}