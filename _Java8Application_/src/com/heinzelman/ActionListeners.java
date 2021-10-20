package com.heinzelman;

import com.sun.javafx.property.adapter.PropertyDescriptor;

import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Array;
import java.util.TooManyListenersException;
import com.sun.javafx.property.adapter.PropertyDescriptor.Listener;

public class ActionListeners implements ActionListener {

    private static DropTarget dropTarget;
    private static DropTargetListener dropTargetListener;



    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println( e );
    }



    public static ActionListener  button1getActionListener(){

        ActionListener myListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println( e );
            }
        };

    return myListener;
    }





    public static FileDrop.Listener button1getListener() {

        FileDrop.Listener myListener = new FileDrop.Listener() {

            public void dragExit(DropTargetEvent dte) {
                System.out.println(dte);
            }

            public void drop(DropTargetDropEvent dtde) {
                System.out.println(dtde);
            }

            @Override
            public void filesDropped(File[] files) {
                for ( File f : files ){
                    System.out.println( f.toString() );
                }
            }
        };




        return myListener;
    }


}
