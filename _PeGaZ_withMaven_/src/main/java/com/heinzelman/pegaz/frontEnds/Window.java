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
    private MyLineComponent lineComp6;
    private MyLineComponent lineComp7;
    private MyLineComponent lineComp8;
    private MyLineComponent lineComp9;
    private MyLineComponent lineComp0;




    public Window(Model model, MyCommand myCommand, FileTools fileTools) {
        super("PeGaZ");
        this.model=model;
        this.myCommand=myCommand;
        this.fileTools=fileTools;


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFont(new Font("Verdana", Font.PLAIN, 3));
        this.setSize(560,620);

        setLayout( new FlowLayout( FlowLayout.CENTER, 3,3  ) );

        searchJPanel = new SearchJPanel( model, myCommand );
        searchJPanel.setSize(690,20);
        add ( searchJPanel );



        label = new JLabel( "                                     " , null, JLabel.CENTER );
        label.setFont(new Font("Verdana", Font.PLAIN, 23));
        label.setSize(630,70);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        add ( label );


        codesJList = new CodesJList( myCommand );
        codesJList.setSize(690,200);
        add( codesJList );




        lineComp1 = new MyLineComponent( 1 ,  myCommand ); add ( lineComp1 ); lineComp1.setSize(690,20);
        lineComp2 = new MyLineComponent( 2 ,  myCommand ); add ( lineComp2 ); lineComp2.setSize(690,20);
        lineComp3 = new MyLineComponent( 3 ,  myCommand ); add ( lineComp3 ); lineComp3.setSize(690,20);
        lineComp4 = new MyLineComponent( 4 ,  myCommand ); add ( lineComp4 ); lineComp4.setSize(690,20);
        lineComp5 = new MyLineComponent( 5 ,  myCommand ); add ( lineComp5 ); lineComp5.setSize(690,20);
        lineComp6 = new MyLineComponent( 6 ,  myCommand ); add ( lineComp6 ); lineComp6.setSize(690,20);
        lineComp7 = new MyLineComponent( 7 ,  myCommand ); add ( lineComp7 ); lineComp7.setSize(690,20);
        lineComp8 = new MyLineComponent( 8 ,  myCommand ); add ( lineComp8 ); lineComp8.setSize(690,20);
        lineComp9 = new MyLineComponent( 9 ,  myCommand ); add ( lineComp9 ); lineComp9.setSize(690,20);
        lineComp0 = new MyLineComponent( 0 ,  myCommand ); add ( lineComp0 ); lineComp0.setSize(690,20);

        lineComp1.setVisible(false);
        lineComp2.setVisible(false);
        lineComp3.setVisible(false);
        lineComp4.setVisible(false);
        lineComp5.setVisible(false);
        lineComp6.setVisible(false);
        lineComp7.setVisible(false);
        lineComp8.setVisible(false);
        lineComp9.setVisible(false);
        lineComp0.setVisible(false);


        //pack();
        }


        public void redraw(){

            //System.out.println( model );

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
            lineComp6.setVisible(false);
            lineComp7.setVisible(false);
            lineComp8.setVisible(false);
            lineComp9.setVisible(false);
            lineComp0.setVisible(false);

            if ( model.getProjId()!=null ){
                Long id = model.getProjId();
                //System.out.println( " ** id: "+  id );
                lineComp1.setVisible(true); lineComp1.setBaseId( id ); lineComp1.refresh( new File( fileTools.idToFileName( id, 1 )).exists());
                lineComp2.setVisible(true); lineComp2.setBaseId( id ); lineComp2.refresh( new File( fileTools.idToFileName( id, 2 )).exists());
                lineComp3.setVisible(true); lineComp3.setBaseId( id ); lineComp3.refresh( new File( fileTools.idToFileName( id, 3 )).exists());
                lineComp4.setVisible(true); lineComp4.setBaseId( id ); lineComp4.refresh( new File( fileTools.idToFileName( id, 4 )).exists());
                lineComp5.setVisible(true); lineComp5.setBaseId( id ); lineComp5.refresh( new File( fileTools.idToFileName( id, 5 )).exists());
                lineComp6.setVisible(true); lineComp6.setBaseId( id ); lineComp6.refresh( new File( fileTools.idToFileName( id, 6 )).exists());
                lineComp7.setVisible(true); lineComp7.setBaseId( id ); lineComp7.refresh( new File( fileTools.idToFileName( id, 7 )).exists());
                lineComp8.setVisible(true); lineComp8.setBaseId( id ); lineComp8.refresh( new File( fileTools.idToFileName( id, 8 )).exists());
                lineComp9.setVisible(true); lineComp9.setBaseId( id ); lineComp9.refresh( new File( fileTools.idToFileName( id, 9 )).exists());
                lineComp0.setVisible(true); lineComp0.setBaseId( id ); lineComp0.refresh( new File( fileTools.idToFileName( id, 0 )).exists());
            }


        //pack();
        }

}
