package com.heinzelman;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Main {

    private static String PATH = "C:\\temp\\_PDFSYSTEM_\\";
    private static ActionListeners actionListeners;

    public static void main(String[] args) {

        //Creating the Frame
        JFrame frame = new JFrame("PDF Repo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550,360);


        JButton button1 = new JButton("Button 1");

        if ( actionListeners==null ) { actionListeners = new ActionListeners();}
        button1.addActionListener( actionListeners.button1getListener() );

        frame.add(button1);



/*

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, label);
       //frame.getContentPane().add(BorderLayout.NORTH, mb);
       // frame.getContentPane().add(BorderLayout.CENTER, ta);

        frame.setVisible(true);
*/


    }


      /*


        JButton button = new JButton("Press");
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        frame.getContentPane().add(button1);
        frame.getContentPane().add(button2);
        frame.getContentPane().add(button); // Adds Button to content pane of frame





        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        JTextArea ta = new JTextArea();


        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);

        */


}
