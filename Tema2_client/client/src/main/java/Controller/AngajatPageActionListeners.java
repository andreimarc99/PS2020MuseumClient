package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.CitireInstitutii;
import Model.Institutie;
import Model.OperaDeArtaPlastica;
import Model.SculpturaGalerieArta;
import Model.SculpturaMuzeu;
import Model.TablouGalerieArta;
import Model.TablouMuzeu;
import View.AngajatPage;
import View.LoginPage;
import StatisticiRapoarte.*;

public class AngajatPageActionListeners {
	private ServerCommunication serverCommunication = new ServerCommunication();

	public void addActionListeners(final AngajatPage angajatPage) {

		angajatPage.getBtnFiltrareDupaInstitutie().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<OperaDeArtaPlastica> opere = new Filtrare()
						.filtrareDupaInstitutie(angajatPage.getInstitutiiComboBox());
				angajatPage.getOpereDeArtaTextArea().setText("");
				for (OperaDeArtaPlastica o : opere) {
					angajatPage.appendOperaDeArta(o.toString() + "\n");
				}
			}
		});

		angajatPage.getBtnFiltrareDupaArtist().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<OperaDeArtaPlastica> opere = new Filtrare().filtrareDupaArtist(angajatPage.getArtistiComboBox());
				angajatPage.getOpereDeArtaTextArea().setText("");
				for (OperaDeArtaPlastica entry : opere) {
					angajatPage.appendOperaDeArta(entry.toString() + "\n");
				}
			}
		});

		angajatPage.getBtnExit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					serverCommunication.exitServer();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});

		angajatPage.getBtnBack().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				angajatPage.getFrame().setVisible(false);
				new LoginPage().getFrame().setVisible(true);
			}
		});

		angajatPage.getBtnFiltrareDupaTipulOperei().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<OperaDeArtaPlastica> opere = new Filtrare()
						.filtrareDupaTipulOperei(angajatPage.getTipulOpereiComboBox());
				angajatPage.getOpereDeArtaTextArea().setText("");
				for (OperaDeArtaPlastica o : opere) {
					angajatPage.appendOperaDeArta(o.toString() + "\n");
				}
			}
		});

		angajatPage.getBtnCautare().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Institutie> institutii = new ArrayList<Institutie>();
				try {
					institutii = serverCommunication.getAllInstitutii();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if (!angajatPage.getCautareOperaTextField().isEmpty()) {
					boolean ok = false;
					for (Institutie i : institutii) {
						for (OperaDeArtaPlastica o : i.getOpereDeArta()) {
							if (o.getTitlu().equals(angajatPage.getCautareOperaTextField())) {
								JOptionPane.showMessageDialog(null, o.toString());
								ok = true;
							}
						}
					}
					if (!ok) {
						JOptionPane.showMessageDialog(null, "Nu exista opera cu titlul dat.");
					}
				}
			}
		});

		angajatPage.getBtnCreareOpera().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tip = angajatPage.getTipOperaCreareComboBox();
				try {
					if (tip.equals("Tablou muzeu")) {
						TablouMuzeu tab = new InputOpere().newTablouMuzeu();
						serverCommunication.createTablouMuzeu(tab.getTitlu(), tab.getNumeArtist(),
								tab.getAnulRealizarii(), tab.getGenPictura(), tab.getTehnica(), tab.getIdInst());

					} else if (tip.equals("Tablou galerie")) {
						TablouGalerieArta tab = new InputOpere().newTablouGalerie();
						serverCommunication.createTablouGalerie(tab.getTitlu(), tab.getNumeArtist(),
								tab.getAnulRealizarii(), tab.getGenPictura(), tab.getTehnica(), tab.getPret(),
								tab.getIdInst());

					} else if (tip.equals("Sculptura muzeu")) {
						SculpturaMuzeu tab = new InputOpere().newSculpturaMuzeu();
						serverCommunication.createSculpturaMuzeu(tab.getTitlu(), tab.getNumeArtist(),
								tab.getAnulRealizarii(), tab.getTipSculptura(), tab.getIdInst());
					} else {
						SculpturaGalerieArta tab = new InputOpere().newSculpturaGalerie();
						serverCommunication.createSculpturaGalerie(tab.getTitlu(), tab.getNumeArtist(),
								tab.getAnulRealizarii(), tab.getTipSculptura(), tab.getPret(), tab.getIdInst());
					}

					List<Institutie> institutii = serverCommunication.getAllInstitutii();
					angajatPage.getOpereDeArtaTextArea().setText("");
					for (Institutie j : institutii) {
						angajatPage.appendOperaDeArta(j.toString());
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		angajatPage.getBtnStergereOpera().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				List<Institutie> institutii = new ArrayList<Institutie>();
				try {
					institutii = serverCommunication.getAllInstitutii();
					String opera = angajatPage.getOpereComboBox();
					for (Institutie i : institutii) {
						for (int j = 0; j < i.getOpereDeArta().size(); ++j) {
							if (opera.equals(i.getOpereDeArta().get(j).toString())) {
								if (i.getOpereDeArta().get(j) instanceof TablouMuzeu) {
									serverCommunication.deleteTablouMuzeu(i.getOpereDeArta().get(j));
								} else if (i.getOpereDeArta().get(j) instanceof TablouGalerieArta) {
									serverCommunication.deleteTablouGalerie(i.getOpereDeArta().get(j));
								} else if (i.getOpereDeArta().get(j) instanceof SculpturaMuzeu) {
									serverCommunication.deleteSculpturaMuzeu(i.getOpereDeArta().get(j));
								} else {
									serverCommunication.deleteSculpturaGalerie(i.getOpereDeArta().get(j));
								}
							}
						}
					}

					angajatPage.getOpereDeArtaComboBox().removeAllItems();
					institutii = serverCommunication.getAllInstitutii();

					for (Institutie i : institutii) {
						for (OperaDeArtaPlastica o : i.getOpereDeArta()) {
							angajatPage.getOpereDeArtaComboBox().addItem(o.toString());
						}
					}

					angajatPage.getOpereDeArtaTextArea().setText("");
					for (Institutie i : institutii) {
						angajatPage.appendOperaDeArta(i.toString());
					}

				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		angajatPage.getBtnEditareOpera().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				List<Institutie> institutii = new ArrayList<Institutie>();
				try {
					institutii = serverCommunication.getAllInstitutii();

					boolean tMuzeu = false;
					boolean tGalerie = false;
					boolean sMuzeu = false;
					boolean sGalerie = false;
					int indexInst = 0;
					int indexOpera = 0;
					String opera = angajatPage.getOpereComboBox();
					for (int i = 0; i < institutii.size(); ++i) {
						for (int j = 0; j < institutii.get(i).getOpereDeArta().size(); ++j) {
							if (opera.equals(institutii.get(i).getOpereDeArta().get(j).toString())) {
								indexInst = i;
								indexOpera = j;
								if (institutii.get(i).getOpereDeArta().get(j) instanceof TablouMuzeu) {
									tMuzeu = true;
								} else if (institutii.get(i).getOpereDeArta().get(j) instanceof TablouGalerieArta) {
									tGalerie = true;
								} else if (institutii.get(i).getOpereDeArta().get(j) instanceof SculpturaMuzeu) {
									sMuzeu = true;
								} else if (institutii.get(i).getOpereDeArta().get(j) instanceof SculpturaGalerieArta) {
									sGalerie = true;
								}
							}
						}
					}

					if (tMuzeu) {
						OperaDeArtaPlastica t = institutii.get(indexInst).getOpereDeArta().get(indexOpera);
						TablouMuzeu newT = new InputOpere().newTablouMuzeu();
						serverCommunication.editTablouMuzeu(t, newT);
					} else if (tGalerie) {
						OperaDeArtaPlastica t = institutii.get(indexInst).getOpereDeArta().get(indexOpera);
						TablouGalerieArta newT = new InputOpere().newTablouGalerie();
						serverCommunication.editTablouGalerie(t, newT);
					} else if (sMuzeu) {
						OperaDeArtaPlastica s = institutii.get(indexInst).getOpereDeArta().get(indexOpera);
						SculpturaMuzeu newS = new InputOpere().newSculpturaMuzeu();
						serverCommunication.editSculpturaMuzeu(s, newS);

					} else if (sGalerie) {
						OperaDeArtaPlastica s = institutii.get(indexInst).getOpereDeArta().get(indexOpera);
						SculpturaGalerieArta newS = new InputOpere().newSculpturaGalerie();
						serverCommunication.editSculpturaGalerie(s, newS);
					}

					institutii = serverCommunication.getAllInstitutii();

					new CitireInstitutii().getInstitutii();
					angajatPage.getOpereDeArtaTextArea().setText("");
					for (Institutie i : institutii) {
						angajatPage.appendOperaDeArta(i.toString());
					}
					angajatPage.getOpereDeArtaComboBox().removeAllItems();

					for (Institutie i : institutii) {
						for (OperaDeArtaPlastica o : i.getOpereDeArta()) {
							angajatPage.getOpereDeArtaComboBox().addItem(o.toString());
						}
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});

		angajatPage.getBtnStatistici().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Statistici().generareStatistici();
			}
		});

		angajatPage.getBtnGenerareRapoarte().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new GenerareRapoarte().salvareRaportXML();
				new GenerareRapoarte().salvareRaportCSV();
				try {
					new GenerareRapoarte().salvareRaportJSON();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Rapoarte generate cu succes");
			}
		});
	}

}
