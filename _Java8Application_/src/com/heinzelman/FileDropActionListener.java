package com.heinzelman;

import com.sun.javafx.property.adapter.PropertyDescriptor;

import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class FileDropListener implements FileDrop.Listener {

    @Override
    public void filesDropped( File[] files ) {

        for( int i = 0; i < files.length; i++ ) {
            try {
                System.out.println( files[i].getCanonicalPath() );

            } catch( java.io.IOException e ) {}
        }   // end for: through each dropped file

        System.out.println( files );

    }
/*
        public FileDropListener( java.io.File[] files ) {
           for( int i = 0; i < files.length; i++ ) {
               try {
                   text.append( files[i].getCanonicalPath() + "\n" );
                } catch( java.io.IOException e ) {}
            }   // end for: through each dropped file
        };   // end filesDropped

 */
    }




