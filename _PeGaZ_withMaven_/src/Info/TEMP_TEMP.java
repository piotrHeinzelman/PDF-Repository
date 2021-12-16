package com.heinzelman.pegaz;

import javax.swing.*;
import java.awt.*;

public class TEMP_TEMP {
    private AListener aListener;

    private void none() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.addActionListener(aListener);
        int result = jFileChooser.showDialog(this.getParent(), "wgraj plik");
        //System.out.println( result );
    }


    private Component getParent(){ /*  :- )  */  return null; }

}
