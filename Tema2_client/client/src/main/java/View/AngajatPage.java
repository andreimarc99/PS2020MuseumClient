package View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Controller.AngajatPageActionListeners;
import Controller.ServerCommunication;
import Model.GalerieArta;
import Model.Institutie;
import Model.Muzeu;
import Model.OperaDeArtaPlastica;

public class AngajatPage {

	private JFrame frame;
	private JButton btnFiltrareDupaInstitutie;
	private JButton btnFiltrareDupaArtist;
	private JButton btnExit;
	private JButton btnEditareOpera;
	private JButton btnCreareOpera;
	private JComboBox<String> tipulOpereiComboBox;
	private JButton btnFiltrareDupaTipulOperei;
	private JButton btnStatistici;
	private JButton btnCautare;
	private JComboBox<String> artistiComboBox;
	private JComboBox<String> opereComboBox;
	private JTextField cautareOperatextField;
	private JButton btnStergereOpera;
	private JTextArea opereDeArtaTextArea;
	private JComboBox<String> institutiiComboBox;
	private JButton btnBack;
	private JComboBox<String> tipOperaCreareComboBox;
	private JButton btnGenerareRapoarte;
	private ServerCommunication serverCommunication = new ServerCommunication();

	/**
	 * Create the application.
	 */
	public AngajatPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 724, 500);
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
		opereDeArtaTextArea.setEditable(false);
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

		opereComboBox = new JComboBox<String>();
		opereComboBox.setBounds(10, 381, 386, 22);
		for (Institutie i : institutii) {
			for (OperaDeArtaPlastica o : i.getOpereDeArta()) {
				opereComboBox.addItem(o.toString());
			}
		}
		frame.getContentPane().add(opereComboBox);

		btnStergereOpera = new JButton("Stergere opera");
		btnStergereOpera.setBounds(406, 381, 133, 23);
		frame.getContentPane().add(btnStergereOpera);

		btnEditareOpera = new JButton("Editare opera");
		btnEditareOpera.setBounds(549, 381, 133, 23);
		frame.getContentPane().add(btnEditareOpera);

		tipOperaCreareComboBox = new JComboBox<String>();
		tipOperaCreareComboBox.setBounds(122, 414, 152, 22);
		tipOperaCreareComboBox.addItem("Tablou muzeu");
		tipOperaCreareComboBox.addItem("Tablou galerie");
		tipOperaCreareComboBox.addItem("Sculptura muzeu");
		tipOperaCreareComboBox.addItem("Sculptura galerie");
		frame.getContentPane().add(tipOperaCreareComboBox);

		JLabel lblTipulNoiiOpere = new JLabel("Tipul noii opere");
		lblTipulNoiiOpere.setBounds(10, 414, 175, 14);
		frame.getContentPane().add(lblTipulNoiiOpere);

		btnCreareOpera = new JButton("Creare");
		btnCreareOpera.setBounds(284, 414, 112, 23);
		frame.getContentPane().add(btnCreareOpera);
		
		btnGenerareRapoarte = new JButton("Generare rapoarte");
		btnGenerareRapoarte.setBounds(406, 163, 277, 23);
		frame.getContentPane().add(btnGenerareRapoarte);

		new AngajatPageActionListeners().addActionListeners(this);
	}

	public JFrame getFrame() {
		return frame;
	}
	public JButton getBtnFiltrareDupaInstitutie() {
		return btnFiltrareDupaInstitutie;
	}
	public JButton getBtnFiltrareDupaArtist() {
		return btnFiltrareDupaArtist;
	}
	public JButton getBtnExit() {
		return btnExit;
	}
	public JButton getBtnEditareOpera() {
		return btnEditareOpera;
	}
	public JButton getBtnCreareOpera() {
		return btnCreareOpera;
	}
	public String getTipulOpereiComboBox() {
		return tipulOpereiComboBox.getSelectedItem().toString();
	}
	public JButton getBtnFiltrareDupaTipulOperei() {
		return btnFiltrareDupaTipulOperei;
	}
	public JButton getBtnStatistici() {
		return btnStatistici;
	}
	public JButton getBtnCautare() {
		return btnCautare;
	}
	public String getArtistiComboBox() {
		return artistiComboBox.getSelectedItem().toString();
	}
	public String getOpereComboBox() {
		return opereComboBox.getSelectedItem().toString();
	}
	public String getCautareOperaTextField() {
		return cautareOperatextField.getText().trim();
	}
	public JButton getBtnStergereOpera() {
		return btnStergereOpera;
	}
	public JTextArea getOpereDeArtaTextArea() {
		return opereDeArtaTextArea;
	}
	public void appendOperaDeArta(String str) {
		opereDeArtaTextArea.append(str);
	}
	public String getInstitutiiComboBox() {
		return institutiiComboBox.getSelectedItem().toString();
	}
	public JButton getBtnBack() {
		return btnBack;
	}
	public String getTipOperaCreareComboBox() {
		return tipOperaCreareComboBox.getSelectedItem().toString();
	}
	public JComboBox<String> getOpereDeArtaComboBox() {
		return opereComboBox;
	}
	public JButton getBtnGenerareRapoarte() {
		return btnGenerareRapoarte;
	}
}
