package com.heinzelman;

import com.heinzelman.frontEnds.CodesJList;
import com.heinzelman.frontEnds.ItemJPanel;
import com.heinzelman.frontEnds.SearchJPanel;

import javax.swing.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;

public class AListener implements java.awt.event.ActionListener {

    private SearchJPanel searchJPanel;
    private CodesJList codesJList;
    private ItemJPanel itemJPanel;
    private FileTools fileTools;

    public AListener() {
        fileTools = new FileTools();
    }

    public void setCodesJList( CodesJList codesJList ) {this.codesJList = codesJList;}
    public void setSearchJPanel( SearchJPanel searchJPanel ) {this.searchJPanel = searchJPanel;}
    public void setItemPanel( ItemJPanel itemPanel ) {this.itemJPanel = itemPanel;}

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println( "\n\n"+e.getSource().getClass() );
        // File ?
        if ( e.getSource() instanceof JFileChooser ) {
            System.out.println(  );
        }


        // Buttons
        if ( e.getSource() instanceof JButton ) {
            JButton j = (JButton) e.getSource();
            switch (j.getMaximumSize().height) {

                // find code button
                case 5000:
                    if (searchJPanel.getText().equals("")) return;
                    codesJList.prepareData(searchJPanel.getText());
                    itemJPanel.hideMe();
                    break;

                case 5001:
                    if (searchJPanel.getText().equals("")) return;
                    System.out.println( " *** ADD CODE TO REPO !!! *** " + searchJPanel.getText() );
                    //codesJList.prepareData(searchJPanel.getText());
                    itemJPanel.hideMe();
                    break;

                default:
                    System.out.println(e);
                    System.out.println(codesJList);
                    System.out.println(searchJPanel);
                    System.out.println(itemJPanel);

            }
            return;
        }
        //switch ( e.getSource() );

    }

    public void listChagneEvent( Long id, String name ) {
        codesJList.prepareData("");
        itemJPanel.reload( id , name  );
    }

    public void addFile( int type, Long baseId, ActionEvent e ) {
        JFileChooser jFile = new JFileChooser();
        int result = jFile.showDialog( null  , "wgraj plik");
        if( result!=0 ) return;
        System.out.println( type + "" + baseId + "" + e  );
        System.out.println( jFile.getSelectedFile() );

        fileTools.addFile( type , baseId , e , jFile.getSelectedFile() );

        //codesJList.prepareData("");
        //itemJPanel.reload( id , name  );
    }


    public void fileEdit( int type, Long baseId, ActionEvent e ) {
        System.out.println( type + "" + baseId + "" + e  );
        //codesJList.prepareData("");
        //itemJPanel.reload( id , name  );

        fileTools.editFile( type , baseId , e , null );


    }

    public void fileDelete( int type, Long baseId, ActionEvent e ) {

        String[] opcje = { "Usuń", "Anuluj" };
        int rc = JOptionPane.showOptionDialog(
                null, // okno
                "Usunąć plik ?", // komunikat
                "", // tytuł
                JOptionPane.DEFAULT_OPTION, // rodzaj przycisków
                JOptionPane.QUESTION_MESSAGE,// typ komunikatu
                null, // własna ikona (tu: brak)
                opcje, // własne opcje - przyciski
                opcje[1]); // domyślny przycisk

        if( rc==1 ) { return; }


        System.out.println( type + "" + baseId + "" + e );
        //codesJList.prepareData("");
        //itemJPanel.reload( id , name  );
        fileTools.deleteFile( type , baseId , e , null );
    }


    public void DropFile( int type , Long baseId , DropTargetDropEvent dtde ){

        Transferable transferable = dtde.getTransferable();
        System.out.println( transferable );
        System.out.println( transferable.getTransferDataFlavors() );

        fileTools.addFile( type , baseId , null , null );
    }

}
