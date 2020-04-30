package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JTextField;

import Controller.ServerCommunication;
import Controller.VizitatorPageActionListeners;
import Model.GalerieArta;
import Model.Institutie;
import Model.Muzeu;
import Model.OperaDeArtaPlastica;
import javax.swing.JComboBox;

public class VizitatorPage {

	private JFrame frame;
	private JTextField cautareOperatextField;
	private JComboBox<String> institutiiComboBox;
	private JTextArea opereDeArtaTextArea;
	private JComboBox<String> artistiComboBox;
	private JComboBox<String> tipulOpereiComboBox;
	private JButton btnFiltrareDupaInstitutie;
	private JButton btnFiltrareDupaTipulOperei;
	private JButton btnFiltrareDupaArtist;
	private JButton btnBack;
	private JButton btnExit;
	private JButton btnCautare;
	private JButton btnStatistici;
	private ServerCommunication serverCommunication = new ServerCommunication();
	/**
	 * Create the application.
	 */
	public VizitatorPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 709, 398);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		List<Institutie> institutii = new ArrayList<Institutie>();
		try {
			institutii = serverCommunication.getAllInstitutii();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JLabel lblOpereDeArta = new JLabel("Opere de arta disponibile");
		lblOpereDeArta.setBounds(10, 11, 145, 14);
		frame.getContentPane().add(lblOpereDeArta);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 386, 278);
		frame.getContentPane().add(scrollPane);
		
		opereDeArtaTextArea = new JTextArea();
		for (Institutie i : institutii) {
			opereDeArtaTextArea.append(i.toString());
		}
		scrollPane.setViewportView(opereDeArtaTextArea);
		
		institutiiComboBox = new JComboBox<String>();
		institutiiComboBox.setBounds(406, 41, 105, 22);
		for (Institutie i : institutii) {
			StringBuilder sb = new StringBuilder();
			if (i instanceof Muzeu) {
				sb.append("Muzeu ");
			} else if (i instanceof GalerieArta) {
				sb.append("GalerieArta ");
			}
			sb.append(i.getId());
			institutiiComboBox.addItem(sb.toString());
		}
		frame.getContentPane().add(institutiiComboBox);
		
		artistiComboBox = new JComboBox<String>();
		artistiComboBox.setBounds(406, 75, 105, 22);
		HashSet<String> artisti = new HashSet<String>();
		for (Institutie i : institutii) {
			for (OperaDeArtaPlastica o : i.getOpereDeArta()) {
				artisti.add(o.getNumeArtist());
			}
		}
		for (String a : artisti) {
			artistiComboBox.addItem(a);
		}
		frame.getContentPane().add(artistiComboBox);
		
		tipulOpereiComboBox = new JComboBox<String>();
		tipulOpereiComboBox.setBounds(406, 108, 105, 22);
		tipulOpereiComboBox.addItem("Tablou");
		tipulOpereiComboBox.addItem("Sculptura");
		frame.getContentPane().add(tipulOpereiComboBox);
		
		btnFiltrareDupaInstitutie = new JButton("Filtrare dupa institutie");
		btnFiltrareDupaInstitutie.setBounds(521, 40, 162, 23);
		frame.getContentPane().add(btnFiltrareDupaInstitutie);
		
		btnFiltrareDupaArtist = new JButton("Filtrare dupa artist");
		btnFiltrareDupaArtist.setBounds(521, 74, 162, 23);
		frame.getContentPane().add(btnFiltrareDupaArtist);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(109, 325, 89, 23);
		frame.getContentPane().add(btnExit);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(10, 325, 89, 23);
		frame.getContentPane().add(btnBack);
		
		btnFiltrareDupaTipulOperei = new JButton("Filtrare dupa tipul operei");
		btnFiltrareDupaTipulOperei.setBounds(521, 108, 162, 23);
		frame.getContentPane().add(btnFiltrareDupaTipulOperei);
		
		cautareOperatextField = new JTextField();
		cautareOperatextField.setBounds(406, 260, 277, 20);
		frame.getContentPane().add(cautareOperatextField);
		cautareOperatextField.setColumns(10);
		
		btnCautare = new JButton("Cautare");
		btnCautare.setBounds(594, 291, 89, 23);
		frame.getContentPane().add(btnCautare);
		
		btnStatistici = new JButton("Statistici");
		btnStatistici.setBounds(406, 226, 277, 23);
		frame.getContentPane().add(btnStatistici);
		
		new VizitatorPageActionListeners().addActionListeners(this);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	public String getOpereDeArtaText() {
		return opereDeArtaTextArea.getText().trim();
	}
	public String getInstitutiiComboBox() {
		return institutiiComboBox.getSelectedItem().toString();
	}
	public String getArtistiComboBox() {
		return artistiComboBox.getSelectedItem().toString();
	}
	public String getTipulOpereiComboBox() {
		return tipulOpereiComboBox.getSelectedItem().toString();
	}
	public JButton getBtnFiltrareDupaInstitutie() {
		return btnFiltrareDupaInstitutie;
	}
	public JButton getBtnFiltrareDupaTipulOperei() {
		return btnFiltrareDupaTipulOperei;
	}
	public JButton getBtnFiltrareDupaArtist() {
		return btnFiltrareDupaArtist;
	}
	public JButton getBtnBack() {
		return btnBack;
	}
	public JButton getBtnExit() {
		return btnExit;
	}
	public JButton getBtnCautare() {
		return btnCautare;
	}
	public String getCautareOperaText() {
		return cautareOperatextField.getText().trim();
	}
	public JButton getBtnStatistici() {
		return btnStatistici;
	}
	public JTextArea getOpereDeArtaTextArea() {
		return opereDeArtaTextArea;
	}
	public void appendOpereDeArta(String str) {
		opereDeArtaTextArea.append(str);
	}
}
