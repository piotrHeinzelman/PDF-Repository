package com.heinzelman.pegaz;

import com.heinzelman.pegaz.frontEnds.Window;
import com.heinzelman.pegaz.tools.ConnectDataSource;
import com.heinzelman.pegaz.tools.FileTools;
import com.heinzelman.pegaz.tools.MyConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class PegazApplication {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e ) { e.printStackTrace(); }


		final MyConfig myConfig = new MyConfig();
		final FileTools fileTools = new FileTools(myConfig);
		final ConnectDataSource connectDataSource = new ConnectDataSource(myConfig);

		Model model = new Model();
		MyCommand myCommand = new MyCommand(  model , null , fileTools, connectDataSource );
		Window win = new Window( model, myCommand, fileTools );
		myCommand.setWindow(win);
		win.setVisible(true);
	}


}
