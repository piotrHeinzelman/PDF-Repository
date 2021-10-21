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

        MyLineComponent line1 = new MyLineComponent();
        contentPane.add( BorderLayout.CENTER , line1 );


        frame.setVisible(true);


    }
}

