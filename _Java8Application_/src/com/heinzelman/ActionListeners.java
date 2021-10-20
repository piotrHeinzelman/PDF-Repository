package com.heinzelman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


}
