package com.heinzelman;

import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TooManyListenersException;

public class ActionListeners implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println( e );
    }


    public static ActionListener  button1getListener(){

        ActionListener myListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println( e );
            }
        };

    return myListener;
    }




    public static DropTarget getDropTargetListener(){

        DropTarget dt = new DropTarget();


        DropTargetListener dtl = new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {
                System.out.println( dtde );
            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {
                System.out.println( dtde );
            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {
                System.out.println( dtde );
            }

            @Override
            public void dragExit(DropTargetEvent dte) {
                System.out.println( dte );
            }

            @Override
            public void drop(DropTargetDropEvent dtde) {
                System.out.println( dtde  );
            }


        };

        try {
            dt.addDropTargetListener( dtl );
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }

        return dt;
    }



}
