package com.heinzelman;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static String PATH = "C:\\temp\\_PDFSYSTEM_\\";

    public static void main(String[] args) {



//   JButton button3 = new JButton("Button 3"); button3.addActionListener( actionListeners.button1getListener() );
/*
        JTextArea
        JTextField
        JList
        JTextPane
  */


        //Creating the Frame
        JFrame frame = new JFrame("PDF Repo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550,360);
        Container contentPane = frame.getContentPane();

        // FIND SECTION
        JPanel topPanel = new JPanel();
            topPanel.add( new MySearchComponent());
        contentPane.add( BorderLayout.NORTH , topPanel);




        // LINES

        JPanel centerPanel = new JPanel();
            MyLineComponent line1 = new MyLineComponent(1);
            MyLineComponent line2 = new MyLineComponent(2);
            MyLineComponent line3 = new MyLineComponent(3);
            MyLineComponent line4 = new MyLineComponent(4);
            MyLineComponent line5 = new MyLineComponent(5);
            MyLineComponent line6 = new MyLineComponent(6);
        centerPanel.add( line1 );
        centerPanel.add( line2 );
        centerPanel.add( line3 );
        centerPanel.add( line4 );
        centerPanel.add( line5 );
        centerPanel.add( line6 );

        contentPane.add( BorderLayout.CENTER , centerPanel );




        frame.setVisible(true);


    }
}

