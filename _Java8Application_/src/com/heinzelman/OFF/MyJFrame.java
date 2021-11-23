package com.heinzelman;

import javax.swing.*;
import java.awt.*;

public class MyJFrame extends JFrame {

    public MyJFrame(  String name ) throws HeadlessException {
        super( name );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(570, 520);
        //this.getContentPane();

    }

    public JComponent addToN(JComponent component ){
        return (JComponent) getContentPane().add( BorderLayout.NORTH , component );
    }

    public JComponent addToC(JComponent component ){
        return (JComponent) getContentPane().add( BorderLayout.CENTER , component );
    }

    public JComponent addToS(JComponent component ){
        return (JComponent) getContentPane().add( BorderLayout.SOUTH , component );
    }
  //  public void addToC( JComponent comp ){ return (JComponent) centerPanel.add( comp ); }
  //  public void addToS( JComponent comp ){ return (JComponent) bottomPanel.add( comp ); }





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
