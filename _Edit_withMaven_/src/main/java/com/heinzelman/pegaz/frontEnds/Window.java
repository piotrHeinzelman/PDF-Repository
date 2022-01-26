package com.heinzelman.pegaz.frontEnds;

import com.heinzelman.pegaz.Model;
import com.heinzelman.pegaz.MyCommand;
import com.heinzelman.pegaz.PegazApplication;
import com.heinzelman.pegaz.tools.FileTools;

import javax.swing.*;
import java.awt.*;
import java.io.File;

import static javax.swing.SwingConstants.CENTER;


public class Window extends JFrame  {

    private Model model;
    private MyCommand myCommand;
    private JLabel label;
    private SearchJPanel searchJPanel;
    private CodesJList codesJList;

    private FileTools fileTools;

    private MyLineComponent lineComp1;
    private MyLineComponent lineComp2;
    private MyLineComponent lineComp3;
    private MyLineComponent lineComp4;
    private MyLineComponent lineComp5;
    private MyLineComponent lineComp0;
    private MyLineComponent lineCompT;



    public Window(Model model, MyCommand myCommand, FileTools fileTools) {
        super("PeGaZ");
        this.model=model;
        this.myCommand=myCommand;
        this.fileTools=fileTools;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFont(new Font("Verdana", Font.PLAIN, 3));
        this.setSize(380,550);

        setLayout( new FlowLayout( FlowLayout.CENTER, 3,3  ) );

        searchJPanel = new SearchJPanel( model, myCommand );
        searchJPanel.setSize(380,20);
        add ( searchJPanel );

        label = new JLabel( "                                     " , null, JLabel.CENTER );
        label.setFont(new Font("Verdana", Font.PLAIN, 23));
        label.setSize(380,70);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        add ( label );

        codesJList = new CodesJList( myCommand );
        codesJList.setSize(380,200);
        add( codesJList );

        lineComp1 = new MyLineComponent( 1 ,  myCommand ); add ( lineComp1 ); lineComp1.setSize(380,20);
        lineComp2 = new MyLineComponent( 2 ,  myCommand ); add ( lineComp2 ); lineComp2.setSize(380,20);
        lineComp3 = new MyLineComponent( 3 ,  myCommand ); add ( lineComp3 ); lineComp3.setSize(380,20);
        lineComp4 = new MyLineComponent( 4 ,  myCommand ); add ( lineComp4 ); lineComp4.setSize(380,20);
        lineComp5 = new MyLineComponent( 5 ,  myCommand ); add ( lineComp5 ); lineComp5.setSize(380,20);
        lineComp0 = new MyLineComponent( 0 ,  myCommand ); add ( lineComp0 ); lineComp0.setSize(380,20);
        lineCompT = new MyLineComponent( -1 ,  myCommand ); add ( lineCompT ); lineCompT.setSize(380,100);


        lineComp1.setVisible(false);
        lineComp2.setVisible(false);
        lineComp3.setVisible(false);
        lineComp4.setVisible(false);
        lineComp5.setVisible(false);
        lineComp0.setVisible(false);
        lineCompT.setVisible(false);
        }


        public void redraw(){

            label.setVisible(false);
            if ( model.getActiveProj()!=null) {
                label.setText( "           " + model.getActiveProj() + "           ");
                label.setVisible(true);
            }

            codesJList.setVisible(false);
            if ( model.getMapOfProj()!=null) {
                codesJList.rereshData(model);
                codesJList.setVisible(true);
            }

            lineComp1.setVisible(false);
            lineComp2.setVisible(false);
            lineComp3.setVisible(false);
            lineComp4.setVisible(false);
            lineComp5.setVisible(false);
            lineComp0.setVisible(false);
            lineCompT.setVisible(false);
            if ( model.getProjId()!=null ){
                Long id = model.getProjId();

                File f0; File f1;

                lineComp1.setVisible(true); lineComp1.setBaseId( id );

                f0 = new File ( fileTools.idToFileName( id, 1 ));
                f1 = new File ( fileTools.idToFileName( id, 2 ));
                if ( f0.exists() && f1.exists()) { lineComp1.turnOnButton(); } else { lineComp1.turnOffButton(); }



                lineComp2.setVisible(true); lineComp2.setBaseId( id );

                f0 = new File ( fileTools.idToFileName( id, 3 ));
                f1 = new File ( fileTools.idToFileName( id, 4 ));
                if ( f0.exists() && f1.exists()) { lineComp2.turnOnButton(); } else { lineComp2.turnOffButton(); }



                lineComp3.setVisible(true); lineComp3.setBaseId( id );

                f0 = new File ( fileTools.idToFileName( id, 5 ));
                f1 = new File ( fileTools.idToFileName( id, 6 ));
                if ( f0.exists() && f1.exists()) { lineComp3.turnOnButton(); } else { lineComp3.turnOffButton(); }



                lineComp4.setVisible(true); lineComp4.setBaseId( id );

                f0 = new File ( fileTools.idToFileName( id, 7 ));
                f1 = new File ( fileTools.idToFileName( id, 8 ));
                if ( f0.exists() && f1.exists()) { lineComp4.turnOnButton(); } else { lineComp4.turnOffButton(); }



                lineComp5.setVisible(true); lineComp5.setBaseId( id );

                f0 = new File ( fileTools.idToFileName( id, 9 ));
                f1 = new File ( fileTools.idToFileName( id, 0 ));
                if ( f0.exists() && f1.exists()) { lineComp5.turnOnButton(); } else { lineComp5.turnOffButton(); }

                lineComp0.setVisible(true); lineComp0.setBaseId( id );
                lineCompT.setVisible(true); lineCompT.setBaseId( id );

            }

        //pack();
        }

}
