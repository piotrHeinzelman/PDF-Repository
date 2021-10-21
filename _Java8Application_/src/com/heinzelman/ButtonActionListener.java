package com.heinzelman;

import com.sun.javafx.property.adapter.PropertyDescriptor;

import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Array;
import java.util.TooManyListenersException;
import com.sun.javafx.property.adapter.PropertyDescriptor.Listener;

public class ButtonActionListener implements ActionListener {

    private static DropTarget dropTarget;
    private static DropTargetListener dropTargetListener;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println( e );
    }

}
