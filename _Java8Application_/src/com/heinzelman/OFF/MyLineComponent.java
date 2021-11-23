package com.heinzelman;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyLineComponent extends JPanel {

    private static final Color FONTCOLOR_NoFile = new Color ( 20,20,20 );
    private static final Color FONTCOLOR_ExistsFile = new Color ( 200,200,200 );

    private static final Color BG_NoFile = new Color( 196,196,196 );
    private static final Color BG_ExistsFile = new Color( 40,127,25 );

    private static final Color BORDERLINE = new Color( 176,176,176 );
    private static final Border BORDER = BorderFactory.createLineBorder( BORDERLINE );
    private static final Insets INSERTS = new Insets(5, 120, 5, 120);

    private static final Dimension DIM = new Dimension ( 140 , 26 );
    //private static final Font FONT = new Font ("Segoe Script", Font.BOLD, 20);



    private static final String EMPTY_TEXT = "DROP PDF HERE";

    private static final FileDrop.Listener listener = new FileDropListener();

    private final int type;
    private boolean isEmpty = false;

    private final JLabel label;
    private final JTextField textArea;
    private final JButton buttonEdit;
    private final JButton buttonDel;


    public MyLineComponent( int type ) throws HeadlessException {

        this.type = type;
        this.label = new JLabel("            ", SwingConstants.RIGHT);
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
        this.textArea.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        new FileDrop( this.textArea , listener );

        this.buttonEdit = new JButton(" Edit ");
        this.buttonEdit.addActionListener( new ButtonEditActionListener());


        this.buttonDel = new JButton(" Delete ");
        this.buttonDel.addActionListener( new ButtonDelActionListener());


            this.add( label );
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


    public void loadContent( Long databaseId ){
        System.out.println( Main.PATH  + " :: " + databaseId );
    }

}






 class ButtonEditActionListener implements ActionListener {

    private static DropTarget dropTarget;
    private static DropTargetListener dropTargetListener;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println( "Edit: " + e );

        JButton myButton = (JButton) e.getSource();
        MyLineComponent myLineComponent = (MyLineComponent) myButton.getParent();
        myLineComponent.turnToExistFile();
    }
}




 class ButtonDelActionListener implements ActionListener {

    private static DropTarget dropTarget;
    private static DropTargetListener dropTargetListener;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println( "delete !" + e );

        JButton myButton = (JButton) e.getSource();
        MyLineComponent myLineComponent = (MyLineComponent) myButton.getParent();
        myLineComponent.turnToNoFile();
    }
}