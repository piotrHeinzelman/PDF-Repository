package com.heinzelman.pegaz.frontEnds;


import com.heinzelman.pegaz.AListener;

import javax.swing.*;
import java.awt.*;

public class SearchJPanel extends JPanel {

    private JButton searchCodeButton;
    private JButton addCodeButton;
    private JTextField searchTextField;

    public SearchJPanel( AListener aListener ) {
        super();
        searchTextField = new JTextField( 15 );

        searchCodeButton = new JButton("szukaj..."   );
            searchCodeButton.addActionListener( aListener );
            searchCodeButton.setMaximumSize( new Dimension( 99999, 5000) );

        addCodeButton = new JButton("dodaj"   );
            addCodeButton.addActionListener( aListener );
            addCodeButton.setMaximumSize( new Dimension( 99999, 5001) );


        this.add( "label" , new JLabel("     podaj kod:" , SwingConstants.RIGHT)  );
        this.add( "text" , searchTextField );
        this.add( "searchCodeButton" , searchCodeButton );
        this.add( "addCodeButton" , addCodeButton );


    }

    public String getText(){
        return searchTextField.getText().trim().toUpperCase();
    }

}
