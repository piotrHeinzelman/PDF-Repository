package com.heinzelman;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static final String PATH = "C:\\temp\\_PDFSYSTEM_\\";

    private static Map<Long, String> listIds = new HashMap<>();
    private static Long selectedId = null;


    public static void main(String[] args) {

        //Creating the Frame
        JFrame frame = new JFrame("PDF Repo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(570,520);
        Container contentPane = frame.getContentPane();


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



        contentPane.add( BorderLayout.CENTER , centerPanel );
        //centerPanel.setVisible( selectedId!=null );

        frame.setVisible(true);
    }
}

