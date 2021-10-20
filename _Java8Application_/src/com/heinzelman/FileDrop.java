
package com.heinzelman;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;

public class FileDrop
{
    private transient javax.swing.border.Border normalBorder;
    private transient java.awt.dnd.DropTargetListener dropListener;

    private java.awt.Component c;
    private Listener listener;
    private File file;

    
    /** Discover if the running JVM is modern enough to have drag and drop. */
    private static Boolean supportsDnD;
    
    // Default border color
    private static java.awt.Color defaultBorderColor = new java.awt.Color( 0f, 0f, 1f, 0.25f );
    

    public FileDrop(  Component c,  Listener listener ) {

            dropListener = new java.awt.dnd.DropTargetListener()
            {   public void dragEnter( java.awt.dnd.DropTargetDragEvent evt )
                {
                javax.swing.JComponent jc = (javax.swing.JComponent) c;
                 evt.acceptDrag( java.awt.dnd.DnDConstants.ACTION_COPY );
                 }



                public void dragOver( java.awt.dnd.DropTargetDragEvent evt )
                {   // This is called continually as long as the mouse is
                    // over the drag target.
                }   // end dragOver

                public void drop( java.awt.dnd.DropTargetDropEvent evt )
                {
                 java.awt.datatransfer.Transferable tr = evt.getTransferable();
                 evt.acceptDrop ( java.awt.dnd.DnDConstants.ACTION_COPY );

                    try {
                        System.out.println( tr.getTransferData(DataFlavor.javaFileListFlavor));
                    } catch (UnsupportedFlavorException e) { e.printStackTrace(); }
                      catch (IOException e) { e.printStackTrace(); }

                    evt.getDropTargetContext().dropComplete(true);

                    }   // end if: file list




                public void dragExit( java.awt.dnd.DropTargetEvent evt )
                {
                    // If it's a Swing component, reset its border
                    if( c instanceof javax.swing.JComponent )
                    {   javax.swing.JComponent jc = (javax.swing.JComponent) c;
                        jc.setBorder( normalBorder );

                    }   // end if: JComponent
                }   // end dragExit

                public void dropActionChanged( java.awt.dnd.DropTargetDragEvent evt ) {
                    evt.acceptDrag(java.awt.dnd.DnDConstants.ACTION_COPY);
                }
            }; // end DropTargetListener

            // Make the component (and possibly children) drop targets
            makeDropTarget( c );

    }   // end constructor


    private static boolean supportsDnD()
    {   // Static Boolean
        if( supportsDnD == null )
        {
            boolean support = false;
            try
            {   Class arbitraryDndClass = Class.forName( "java.awt.dnd.DnDConstants" );
                support = true;
            }   // end try
            catch( Exception e )
            {   support = false;
            }   // end catch
            supportsDnD = new Boolean( support );
        }   // end if: first time through
        return supportsDnD.booleanValue();
    }   // end supportsDnD




    private void makeDropTarget( final java.awt.Component c  )
    {
        // Make drop target
        final java.awt.dnd.DropTarget dt = new java.awt.dnd.DropTarget();
        try
        {   dt.addDropTargetListener( dropListener );
        }   // end try
        catch( java.util.TooManyListenersException e )
        {   e.printStackTrace();
         }   // end catch


        // Listen for hierarchy changes and remove the drop target when the parent gets cleared out.
        c.addHierarchyListener( new java.awt.event.HierarchyListener()
        {   public void hierarchyChanged( java.awt.event.HierarchyEvent evt )
            {   java.awt.Component parent = c.getParent();
                if( parent == null ) {   c.setDropTarget( null ); }
                else  {   new java.awt.dnd.DropTarget(c, dropListener);}
            }   // end hierarchyChanged
        }); // end hierarchy listener
        if( c.getParent() != null )
            new java.awt.dnd.DropTarget(c, dropListener);
    }   // end dropListener



    /** Determine if the dragged data is a file list. */
    private boolean isDragOk( final java.awt.dnd.DropTargetDragEvent evt ) { return true; }


    /** Outputs <tt>message</tt> to <tt>out</tt> if it's not null. */
    private static void log( java.io.PrintStream out, String message )
    {   // Log message if requested
        if( out != null )
            out.println( message );
    }   // end log




    
/* ********  I N N E R   I N T E R F A C E   L I S T E N E R  ******** */    
    

    public static interface Listener {
        public abstract void filesDropped( File file );
    }
    
    
/* ********  I N N E R   C L A S S  ******** */    

    public static class Event extends java.util.EventObject {

        private File file;

        public Event( File file, Object source ) {
            super( source );
            this.file = file;
        }

        public File getFile() {
            return file;
        }

    }


    
/* ********  I N N E R   C L A S S  ******** */

    public static class TransferableObject implements java.awt.datatransfer.Transferable
    {

        public final static String MIME_TYPE = "application/x-net.iharder.dnd.TransferableObject";

        public final static java.awt.datatransfer.DataFlavor DATA_FLAVOR = 
            new java.awt.datatransfer.DataFlavor( FileDrop.TransferableObject.class, MIME_TYPE );


        private Fetcher fetcher;
        private Object data;

        private java.awt.datatransfer.DataFlavor customFlavor;

        public TransferableObject( Object data )
        {   this.data = data;
            this.customFlavor = new java.awt.datatransfer.DataFlavor( data.getClass(), MIME_TYPE );
        }




        public TransferableObject( Fetcher fetcher ) { this.fetcher = fetcher; }


        public TransferableObject( Class dataClass, Fetcher fetcher )
        {   this.fetcher = fetcher;
            this.customFlavor = new java.awt.datatransfer.DataFlavor( dataClass, MIME_TYPE );
        }


        public java.awt.datatransfer.DataFlavor getCustomDataFlavor()
        {   return customFlavor;
        }


    /* ********  T R A N S F E R A B L E   M E T H O D S  ******** */    

        public java.awt.datatransfer.DataFlavor[] getTransferDataFlavors()
        {   
            if( customFlavor != null )
                return new java.awt.datatransfer.DataFlavor[]
                {   customFlavor,
                    DATA_FLAVOR,
                    java.awt.datatransfer.DataFlavor.stringFlavor
                };  // end flavors array
            else
                return new java.awt.datatransfer.DataFlavor[]
                {   DATA_FLAVOR,
                    java.awt.datatransfer.DataFlavor.stringFlavor
                };  // end flavors array
        }   // end getTransferDataFlavors




        public Object getTransferData( java.awt.datatransfer.DataFlavor flavor )
        throws java.awt.datatransfer.UnsupportedFlavorException, java.io.IOException 
        {   
            // Native object
            if( flavor.equals( DATA_FLAVOR ) )
                return fetcher == null ? data : fetcher.getObject();

            // String
            if( flavor.equals( java.awt.datatransfer.DataFlavor.stringFlavor ) )
                return fetcher == null ? data.toString() : fetcher.getObject().toString();

            // We can't do anything else
            throw new java.awt.datatransfer.UnsupportedFlavorException(flavor);
        }   // end getTransferData




        public boolean isDataFlavorSupported( java.awt.datatransfer.DataFlavor flavor ) 
        {
            // Native object
            if( flavor.equals( DATA_FLAVOR ) )
                return true;

            if( flavor.equals( java.awt.datatransfer.DataFlavor.stringFlavor ) )
                return true;

            return false;
        }


    /* ********  I N N E R   I N T E R F A C E   F E T C H E R  ******** */    


        public static interface Fetcher
        {
            public abstract Object getObject();
        }
    }
}
