package com.heinzelman;

import com.heinzelman.MyLineComponent;
import com.heinzelman.MySearchComponent;

import javax.swing.*;
import java.awt.*;

public class MyJFrame extends JFrame {


    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel centerPanel;


    public MyJFrame(  String name ) throws HeadlessException {
        super( name );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(570, 520);
        this.getContentPane();

           topPanel = (JPanel) this.getContentPane().add(BorderLayout.NORTH,  new JPanel());
        centerPanel = (JPanel) this.getContentPane().add(BorderLayout.CENTER, new JPanel());
        bottomPanel = (JPanel) this.getContentPane().add(BorderLayout.SOUTH,  new JPanel());

    }

    public JComponent addToN( JComponent comp ){ return (JComponent) topPanel.add( comp ); }
    public JComponent addToC( JComponent comp ){ return (JComponent) centerPanel.add( comp ); }
    public JComponent addToS( JComponent comp ){ return (JComponent) bottomPanel.add( comp ); }





/*
        // FIND SECTION
        JPanel topPanel = new JPanel();
        topPanel.add( new MySearchComponent( listIds ));
        topPanel.add( new JSeparator());
        topPanel.add( new JSeparator());
        topPanel.add( new JSeparator());
        contentPane.add( BorderLayout.NORTH , topPanel);

        // LINES
        JPanel centerPanel = new JPanel();
        MyLineComponent line1 = new MyLineComponent(1);
        MyLineComponent line2 = new MyLineComponent(2);
        MyLineComponent line3 = new MyLineComponent(3);
        MyLineComponent line4 = new MyLineComponent(4);
        MyLineComponent line5 = new MyLineComponent(5);
        MyLineComponent line6 = new MyLineComponent(6);
        MyLineComponent line7 = new MyLineComponent(7);
        MyLineComponent line8 = new MyLineComponent(8);
        MyLineComponent line9 = new MyLineComponent(9);
        MyLineComponent line0 = new MyLineComponent(0);
        centerPanel.add( line1 );
        centerPanel.add( line2 );
        centerPanel.add( line3 );
        centerPanel.add( line4 );
        centerPanel.add( line5 );
        centerPanel.add( line6 );
        centerPanel.add( line7 );
        centerPanel.add( line8 );
        centerPanel.add( line9 );
        centerPanel.add( line0 );


*/
  //  }
}
