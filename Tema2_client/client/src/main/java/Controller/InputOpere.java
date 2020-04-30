package Controller;

import javax.swing.JOptionPane;

import Model.SculpturaGalerieArta;
import Model.SculpturaMuzeu;
import Model.TablouGalerieArta;
import Model.TablouMuzeu;

public class InputOpere {


	public SculpturaMuzeu newSculpturaMuzeu() {
		String titlu = new String();
		String artist = new String();
		String anRealizare = new String();
		String tipSculptura = new String();
		String denumireMuzeu = new String();
		Integer idMuzeu = 0;
		Integer an = 0;
		titlu = JOptionPane.showInputDialog(null, "Introduceti titlul sculpturii: ");
		if (!titlu.equals("")) {
			artist = JOptionPane.showInputDialog(null, "Introduceti numele artistului: ");
			if (!artist.equals("")) {
				anRealizare = JOptionPane.showInputDialog(null, "Introduceti anul realizarii: ");
				if (!anRealizare.equals("")) {
					tipSculptura = JOptionPane.showInputDialog(null, "Introduceti tipul sculpturii: ");
					if (!tipSculptura.equals("")) {
						denumireMuzeu = JOptionPane.showInputDialog(null,
								"Introduceti ID-ul muzeului de care face parte: ");
						if (!denumireMuzeu.equals("")) {
							try {
								an = Integer.valueOf(anRealizare);
								idMuzeu = Integer.valueOf(denumireMuzeu);
								return new SculpturaMuzeu(titlu, artist, an, tipSculptura, idMuzeu);
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "Format incorect");
							}
						}
					}
				}
			}
		}
		return null;
	}

	public SculpturaGalerieArta newSculpturaGalerie() {
		String titlu = new String();
		String artist = new String();
		String anRealizare = new String();
		String tipSculptura = new String();
		String denumireMuzeu = new String();
		String pretStr = new String();
		Integer pret = 0;
		Integer idMuzeu = 0;
		Integer an = 0;
		titlu = JOptionPane.showInputDialog(null, "Introduceti titlul sculpturii: ");
		if (!titlu.equals("")) {
			artist = JOptionPane.showInputDialog(null, "Introduceti numele artistului: ");
			if (!artist.equals("")) {
				anRealizare = JOptionPane.showInputDialog(null, "Introduceti anul realizarii: ");
				if (!anRealizare.equals("")) {
					tipSculptura = JOptionPane.showInputDialog(null, "Introduceti tipul sculpturii: ");
					if (!tipSculptura.equals("")) {
						pretStr = JOptionPane.showInputDialog(null, "Introduceti pretul sculpturii: ");
						if (!pretStr.equals("")) {
							denumireMuzeu = JOptionPane.showInputDialog(null,
									"Introduceti ID-ul muzeului de care face parte: ");
							if (!denumireMuzeu.equals("")) {
								try {
									pret = Integer.valueOf(pretStr);
									an = Integer.valueOf(anRealizare);
									idMuzeu = Integer.valueOf(denumireMuzeu);
									return new SculpturaGalerieArta(titlu, artist, an, tipSculptura, pret, idMuzeu);
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(null, "Format incorect");
								}
							}
						}
					}
				}
			}
		}
		return null;
	}


	public TablouMuzeu newTablouMuzeu() {
		String titlu = new String();
		String artist = new String();
		String anRealizare = new String();
		String genPictura = new String();
		String tehnica = new String();
		String denumireMuzeu = new String();
		Integer idMuzeu = 0;
		Integer an = 0;
		titlu = JOptionPane.showInputDialog(null, "Introduceti titlul tabloului: ");
		if (!titlu.equals("")) {
			artist = JOptionPane.showInputDialog(null, "Introduceti numele artistului: ");
			if (!artist.equals("")) {
				anRealizare = JOptionPane.showInputDialog(null, "Introduceti anul realizarii: ");
				if (!anRealizare.equals("")) {
					genPictura = JOptionPane.showInputDialog(null, "Introduceti genul picturii: ");
					if (!genPictura.equals("")) {
						tehnica = JOptionPane.showInputDialog(null, "Introduceti tehnica: ");
						if (!tehnica.equals("")) {
							denumireMuzeu = JOptionPane.showInputDialog(null,
									"Introduceti ID-ul muzeului de care face parte: ");
							if (!denumireMuzeu.equals("")) {
								try {
									an = Integer.valueOf(anRealizare);
									idMuzeu = Integer.valueOf(denumireMuzeu);
									// buildTablou("Muzeu", titlu, artist, an, genPictura, tehnica, 0, idMuzeu);
									return new TablouMuzeu(titlu, artist, an, genPictura, tehnica, idMuzeu);
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(null, "Format incorect");
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	public TablouGalerieArta newTablouGalerie() {
		String titlu = new String();
		String artist = new String();
		String anRealizare = new String();
		String genPictura = new String();
		String tehnica = new String();
		String denumireMuzeu = new String();
		String pretStr = new String();
		Integer pret = 0;
		Integer idMuzeu = 0;
		Integer an = 0;
		titlu = JOptionPane.showInputDialog(null, "Introduceti titlul tabloului: ");
		if (!titlu.equals("")) {
			artist = JOptionPane.showInputDialog(null, "Introduceti numele artistului: ");
			if (!artist.equals("")) {
				anRealizare = JOptionPane.showInputDialog(null, "Introduceti anul realizarii: ");
				if (!anRealizare.equals("")) {
					genPictura = JOptionPane.showInputDialog(null, "Introduceti genul picturii: ");
					if (!genPictura.equals("")) {
						tehnica = JOptionPane.showInputDialog(null, "Introduceti tehnica: ");
						if (!tehnica.equals("")) {
							pretStr = JOptionPane.showInputDialog(null, "Introduceti pretul: ");
							if (!pretStr.equals("")) {
								denumireMuzeu = JOptionPane.showInputDialog(null,
										"Introduceti ID-ul muzeului de care face parte: ");
								if (!denumireMuzeu.equals("")) {
									try {
										an = Integer.valueOf(anRealizare);
										pret = Integer.valueOf(pretStr);
										idMuzeu = Integer.valueOf(denumireMuzeu);
										return new TablouGalerieArta(titlu, artist, an, genPictura, tehnica, pret,
												idMuzeu);
									} catch (Exception e1) {
										JOptionPane.showMessageDialog(null, "Format incorect");
									}
								}
							}
						}
					}
				}
			}
		}

		return null;
	}
}
