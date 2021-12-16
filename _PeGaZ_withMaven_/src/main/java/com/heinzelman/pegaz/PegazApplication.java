package com.heinzelman.pegaz;

import com.heinzelman.pegaz.frontEnds.CodesJList;
import com.heinzelman.pegaz.frontEnds.ItemJPanel;
import com.heinzelman.pegaz.frontEnds.SearchJPanel;
import com.heinzelman.pegaz.frontEnds.Window;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SpringBootApplication
public class PegazApplication implements ActionListener {

	private SearchJPanel searchJPanel;
	private CodesJList codesJList;
	private ItemJPanel itemJPanel;
	private FileTools fileTools;

	public void setCodesJList ( CodesJList codesJList ){
		this.codesJList = codesJList;
	}
	public void setSearchJPanel (SearchJPanel searchJPanel ){
		this.searchJPanel = searchJPanel;
	}
	public void setItemPanel (ItemJPanel itemPanel ){
		this.itemJPanel = itemPanel;
	}

	private final ConnectDataSource connectDataSource = new ConnectDataSource();

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch ( ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e ) { e.printStackTrace(); }
		new Window().setVisible(true);
	}




		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("\n\n" + e.getSource().getClass());
			// File ?
			if (e.getSource() instanceof JFileChooser) {
				System.out.println();
			}


			// Buttons
			if (e.getSource() instanceof JButton) {
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
						String newName = searchJPanel.getText();
						connectDataSource.addProject( newName );
						codesJList.prepareData( newName );
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

		public void listChagneEvent (Long id, String name ){
			codesJList.prepareData("");
			itemJPanel.reload(id, name);
		}

		public void addFile ( int type, Long baseId, ActionEvent e ){
			JFileChooser jFile = new JFileChooser();
			int result = jFile.showDialog(null, "wgraj plik");
			if (result != 0) return;
			System.out.println(type + "" + baseId + "" + e);
			System.out.println(jFile.getSelectedFile());

			fileTools.addFile(type, baseId, e, jFile.getSelectedFile());

			//codesJList.prepareData("");
			//itemJPanel.reload( id , name  );
		}


		public void fileEdit ( int type, Long baseId, ActionEvent e ){
			System.out.println(type + "" + baseId + "" + e);
			//codesJList.prepareData("");
			//itemJPanel.reload( id , name  );

			fileTools.editFile(type, baseId, e, null);


		}

		public void fileDelete ( int type, Long baseId, ActionEvent e ){

			String[] opcje = {"Usuń", "Anuluj"};
			int rc = JOptionPane.showOptionDialog(
					null, // okno
					"Usunąć plik ?", // komunikat
					"", // tytuł
					JOptionPane.DEFAULT_OPTION, // rodzaj przycisków
					JOptionPane.QUESTION_MESSAGE,// typ komunikatu
					null, // własna ikona (tu: brak)
					opcje, // własne opcje - przyciski
					opcje[1]); // domyślny przycisk

			if (rc == 1) {
				return;
			}


			System.out.println(type + "" + baseId + "" + e);
			//codesJList.prepareData("");
			//itemJPanel.reload( id , name  );
			fileTools.deleteFile(type, baseId, e, null);
		}


		public void DropFile ( int type, Long baseId, DropTargetDropEvent dtde ){

			Transferable transferable = dtde.getTransferable();
			System.out.println(transferable);
			System.out.println(transferable.getTransferDataFlavors());

			fileTools.addFile(type, baseId, null, null);
		}





	public void _main(String[] args) {

		fileTools = new FileTools();


		//  insert project to DB
		if ( false ) { // dodaj nowt project - OFF
			ConnectDataSource connector = new ConnectDataSource();
			Long lastId = connector.addProject("New Project !");
			//System.out.println(lastId);
		}

		if (false) { // lista projektów
			ConnectDataSource connector = new ConnectDataSource();
			//System.out.println(connector.getAllProject());
		}

		if (false) { // lista projektów
			ConnectDataSource connector = new ConnectDataSource();
			//System.out.println(connector.getLike("kot")); // Map<Long, String> map = new HashMap<>();
		}
	}
	//SpringApplication.run(PegazApplication.class, args);






}
