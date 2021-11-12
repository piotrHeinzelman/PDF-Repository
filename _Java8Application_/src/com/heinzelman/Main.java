package com.heinzelman;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static final String PATH = "C:\\temp\\_PDFSYSTEM_\\";

    private static Map<Long, String> listProj = new HashMap<>();
    private static Long selectedId = null;

    public static void main(String[] args) {

    MyJFrame frame = new MyJFrame( "PDF Repo"  );

        JComponent jComponent = frame.addToN( new JLabel("?") );
        JComponent     search = frame.addToN( new MySearchComponent( listProj ) );

        frame.setVisible( true );
    }

    /*

    this.panel = new JPanel();
this.panel.setLayout(new FlowLayout());
add(panel, BorderLayout.CENTER);
JButton button = new JButton("CLICK HERE");
add(button, BorderLayout.SOUTH);
button.addActionListener(this);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setSize(500, 500);
setVisible(true);

this.panel.add(new JButton("Button"));
this.panel.revalidate();
validate();

     */



}

