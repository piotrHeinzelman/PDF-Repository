package com.heinzelman.pegaz.frontEnds;


import com.heinzelman.pegaz.Model;
import com.heinzelman.pegaz.MyCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchJPanel extends JPanel {

    private JButton searchCodeButton;
    private JButton addCodeButton;
    private JTextField searchTextField;


    public SearchJPanel( Model model, MyCommand myCommand ) {
        super();

        searchTextField = new JTextField( 15 );

        searchCodeButton = new MyButton("szukaj..." , 0 , "SEARCHPROJ"  );
            searchCodeButton.addActionListener( myCommand );


        addCodeButton = new MyButton("dodaj" ,0, "ADDPROJ"  );
            addCodeButton.addActionListener( myCommand );

        this.add( "label" , new JLabel("     podaj kod:" , SwingConstants.RIGHT)  );
        this.add( "text" , searchTextField );
        this.add( "searchCodeButton" , searchCodeButton );
        this.add( "addCodeButton" , addCodeButton );

    }

    public String get_Text(){
        String s =searchTextField.getText().trim().toUpperCase();
        searchTextField.setText("");
        return s;
    }


}
