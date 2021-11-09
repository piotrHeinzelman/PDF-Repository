package com.heinzelman;

import com.sun.javafx.scene.layout.region.Margins;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class MySearchComponent extends JPanel {

    private static final Color FONTCOLOR_NoFile = new Color ( 20,20,20 );
    private static final Color FONTCOLOR_ExistsFile = new Color ( 200,200,200 );

    private static final Color BG_NoFile = new Color( 196,196,196 );
    private static final Color BG_ExistsFile = new Color( 40,127,25 );

    private static final Color BORDERLINE = new Color( 176,176,176 );
    private static final Border BORDER = BorderFactory.createLineBorder( BORDERLINE );
    private static final Insets INSERTS = new Insets(5, 120, 5, 120);

    private static final Dimension DIM = new Dimension ( 140 , 26 );
    private static final Dimension HALF_DIM = new Dimension ( 60 , 26 );



    private final JLabel label;
    private final JTextField textArea;
    private final JButton buttonFind;

    private Map<Long,String> listIds = null;







    public MySearchComponent( Map<Long, String> listIds ) throws HeadlessException {

        this.listIds = listIds;

        this.label = new JLabel(" szukaj ", SwingConstants.RIGHT);
        this.label.setPreferredSize( DIM );

        this.textArea = new JTextField( "" );
        this.textArea.setPreferredSize( DIM );

        this.buttonFind = new JButton(" Szukaj ");
        this.buttonFind.addActionListener( new ButtonFindActionListener());
        this.buttonFind.setPreferredSize( DIM );

        this.add( label );
        this.add( textArea );
        this.add( buttonFind );
    }

    public Map<Long,String> getListIds() { return this.listIds; }
}


class ButtonFindActionListener implements ActionListener {


   //private static DropTarget dropTarget;
   //private static DropTargetListener dropTargetListener;

   @Override
   public void actionPerformed(ActionEvent e) {
       //System.out.println( "This:" + this + "\ne:" + e + "\nModifiers: " + e ); //.getClass().getSimpleName()
       JButton myButton = ( JButton ) e.getSource();
       MySearchComponent mySearchComponent = ( MySearchComponent ) myButton.getParent();
       JTextField myTextField = ( JTextField ) mySearchComponent.getComponent( 1 );
       Map<Long,String> listIds = mySearchComponent.getListIds();
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
       //JButton myButton = (JButton) e.getSource();
       //MyLineComponent myLineComponent = (MyLineComponent) myButton.getParent();

   }
}


