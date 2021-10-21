package com.heinzelman;

import com.sun.javafx.scene.layout.region.Margins;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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



    public MySearchComponent() throws HeadlessException {

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
}


class ButtonFindActionListener implements ActionListener {

   private static DropTarget dropTarget;
   private static DropTargetListener dropTargetListener;

   @Override
   public void actionPerformed(ActionEvent e) {
       System.out.println( "Find: " + e );

       JButton myButton = (JButton) e.getSource();
       MyLineComponent myLineComponent = (MyLineComponent) myButton.getParent();

   }
}


