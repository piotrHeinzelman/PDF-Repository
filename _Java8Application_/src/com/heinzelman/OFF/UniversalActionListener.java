package com.heinzelman;

import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UniversalActionListener implements ActionListener {

    private static DropTarget dropTarget;
    private static DropTargetListener dropTargetListener;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println( e );
    }

}
