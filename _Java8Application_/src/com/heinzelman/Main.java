package com.heinzelman;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionListener;
import java.io.File;

public class Main {

    private static String PATH = "C:\\temp\\_PDFSYSTEM_\\";
    private static ActionListeners actionListeners;

    public static void main(String[] args) {

    if ( actionListeners==null ) { actionListeners = new ActionListeners();}




        // Prepare buttons
        JTextArea loadFile1 = new JTextArea("[ dodaj plik ]") ;
        new FileDrop( loadFile1,  actionListeners.button1getListener() );


     //   JButton button2 = new JButton("Button 2"); button2.addActionListener( actionListeners.button1getListener() );
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



        JPanel centerPanel  = new JPanel( new FlowLayout());
        centerPanel.add( loadFile1 );


        Container contentPane = frame.getContentPane();
            contentPane.add(BorderLayout.CENTER, centerPanel);



        frame.setBounds( 100, 100, 300, 400 );
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setVisible(true);


    }
}

