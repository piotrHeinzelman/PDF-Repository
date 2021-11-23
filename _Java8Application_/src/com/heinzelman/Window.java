package com.heinzelman;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JFrame window;

    private SearchJPanel searchJPanel = null;
    private CodesJList codesJList = null;
    private ItemJPanel itemPanel = null;


    public Window() {
        super("myWindow");

        // akcje !
        AListener aListener = new AListener();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFont(new Font("Verdana", Font.PLAIN, 13));
        setSize(490,620);
        setLayout( new BorderLayout(10,20));

        JPanel CenterPanel = new JPanel();
            CenterPanel.setLayout(new BoxLayout(CenterPanel, BoxLayout.PAGE_AXIS));
            add( CenterPanel, BorderLayout.CENTER );


        // SEARCH
        searchJPanel = new SearchJPanel( aListener );
        add( searchJPanel, BorderLayout.NORTH );


        // LIST :- )
        codesJList = new CodesJList( aListener );
        CenterPanel.add( codesJList );


        // ITEMS
        itemPanel = new ItemJPanel( aListener );
        CenterPanel.add( itemPanel );
        itemPanel.hideMe();




        // send panels to LISTENER
        aListener.setCodesJList( codesJList );
        aListener.setSearchJPanel( searchJPanel );
        aListener.setItemPanel( itemPanel );

        }
}
