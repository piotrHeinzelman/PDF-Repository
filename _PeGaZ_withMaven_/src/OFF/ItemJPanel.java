package com.heinzelman.pegaz.frontEnds;



import com.heinzelman.pegaz.MyCommand;
import com.heinzelman.pegaz.PegazApplication;

import javax.swing.*;
import java.awt.*;

public class ItemJPanel extends JPanel {

    private PegazApplication aListener;
    private JLabel jLabel;


    public ItemJPanel( MyCommand myCommand ){
        super();
        this.aListener = aListener;
        this.jLabel = new JLabel(" ** PANEL ** " , SwingConstants.CENTER );
        jLabel.setFont(new Font("Verdana", Font.PLAIN, 23));
    }

    public void reload( Long baseId , String name ){
        if ( baseId==null ) { /* clear... */ }

        this.removeAll();
        this.add( "label" , jLabel  );
        for ( int type = 1 ; type < 11 ; type++ ) {
          //  lines[type%10] = new MyLineComponent( type%10 , baseId , aListener );
          //  this.add( lines[type%10] );
        }

        jLabel.setText( baseId + " :: " + name  );
        this.setVisible( true );
        System.out.println( baseId + " : " + name );
    }

    public void hideMe(){
        this.setVisible( false );
    }
}