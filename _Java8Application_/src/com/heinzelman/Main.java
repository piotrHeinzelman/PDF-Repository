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


    /*






        Label dropArea = new Label("Drop files here");
        FileDropTarget<Label> dropTarget = new FileDropTarget<>(dropArea, event -> {

            Collection<Html5File> files = event.getFiles();
            files.forEach(file -> {
                // Max 1 MB files are uploaded
                if (file.getFileSize() <= 1024 * 1024) {
                    file.setStreamVariable(new StreamVariable() {

                        // Output stream to write the file to
                        @Override
                        public OutputStream getOutputStream() {
                            try{
                                return new FileOutputStream("/path/to/files/"
                                        + file.getFileName());
                            }catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        // Returns whether onProgress() is called during upload
                        @Override
                        public boolean listenProgress() {
                            return true;
                        }

                        // Called periodically during upload
                        @Override
                        public void onProgress(StreamingProgressEvent event) {
                            Notification.show("Progress, bytesReceived="
                                    + event.getBytesReceived());
                        }

                        // Called when upload started
                        @Override
                        public void streamingStarted(StreamingStartEvent event) {
                            Notification.show("Stream started, fileName="
                                    + event.getFileName());
                        }

                        // Called when upload finished
                        @Override
                        public void streamingFinished(StreamingEndEvent event) {
                            Notification.show("Stream finished, fileName="
                                    + event.getFileName());
                        }

                        // Called when upload failed
                        @Override
                        public void streamingFailed(StreamingErrorEvent event) {
                            Notification.show("Stream failed, fileName="
                                    + event.getFileName());
                        }

                        @Override
                        public boolean isInterrupted() {
                            return false;
                        }
                    });
                }
            }
        });




        */





        if ( actionListeners==null ) { actionListeners = new ActionListeners();}




        // Prepare buttons
        JButton button1 = new JButton("Button 1"); button1.addActionListener( actionListeners.button1getListener() ); button1.setDropTarget( button1.getDropTarget() );
        JButton button2 = new JButton("Button 2"); button2.addActionListener( actionListeners.button1getListener() );
        JButton button3 = new JButton("Button 3"); button3.addActionListener( actionListeners.button1getListener() );
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
        centerPanel.add( button1 );
        centerPanel.add( button2 );
        centerPanel.add( button3 );



        Container contentPane = frame.getContentPane();
            contentPane.add(BorderLayout.CENTER, centerPanel);






        //javax.swing.border.TitledBorder dragBorder = new javax.swing.border.TitledBorder( "Drop 'em" );
        final javax.swing.JTextArea text = new javax.swing.JTextArea();
        frame.getContentPane().add(
                new javax.swing.JScrollPane( text ),
                java.awt.BorderLayout.CENTER );

        new FileDrop( System.out, text, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
        {   for( int i = 0; i < files.length; i++ )
        {   try
        {   text.append( files[i].getCanonicalPath() + "\n" );
        }   // end try
        catch( java.io.IOException e ) {}
        }   // end for: through each dropped file
        }   // end filesDropped
        }); // end FileDrop.Listener

        frame.setBounds( 100, 100, 300, 400 );
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setVisible(true);

        frame.setVisible(true);


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

