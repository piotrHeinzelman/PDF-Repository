package com.heinzelman.pegaz.frontEnds;

import lombok.Setter;

import javax.swing.*;

public class MyButton extends JButton {
    private Integer row;
    private String type="ADD";

    public MyButton( String name, int row , String type  ) {
        super( name );
        this.row = row;
        this.type = type;
    }

    public int getRow() { return row; }
    public void setRow(int row) { this.row = row; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

}
