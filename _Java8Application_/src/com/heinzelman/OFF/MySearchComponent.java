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

public class MySearchComponent extends JComponent {

    private static final Color FONTCOLOR_NoFile = new Color ( 20,20,20 );
    private static final Color FONTCOLOR_ExistsFile = new Color ( 200,200,200 );

    private static final Color BG_NoFile = new Color( 196,196,196 );
    private static final Color BG_ExistsFile = new Color( 40,127,25 );

    private static final Color BORDERLINE = new Color( 176,176,176 );
    private static final Border BORDER = BorderFactory.createLineBorder( BORDERLINE );
    private static final Insets INSERTS = new Insets(5, 120, 5, 120);

    private static final Dimension DIM = new Dimension ( 140 , 26 );
    private static final Dimension HALF_DIM = new Dimension ( 60 , 26 );

    private JLabel label;
    private JTextField textField;
    private JButton jButton;

    public JLabel getJLabel(){ return label; }
    public JTextField getTextField(){ return textField; }
    public JButton getSearchButton(){ return jButton; }
    public Object myMaster;

    public void showAll(){
        label.setVisible(true);
        textField.setVisible(true);
    }

    public void hideAll(){
        label.setVisible(false);
        textField.setVisible(false);
    }

    public void setActionListener( ActionListener actionListener ){
        System.out.println( textField.getText() );
        jButton.addActionListener( actionListener );
    }


    public MySearchComponent() {
        label = new JLabel("   "); //        String[] aryData = new String[22];
        //aryData[0]="abc";
        //aryData[1]="def";
        label.setPreferredSize( DIM );

        textField = new JTextField( "" );
        textField.setPreferredSize( DIM );

        jButton = new JButton(" Szukaj ");
        jButton.setPreferredSize( DIM );
    }

    public void setMyMaster( Object myMaster ) {
        this.myMaster = myMaster;
    }

}

