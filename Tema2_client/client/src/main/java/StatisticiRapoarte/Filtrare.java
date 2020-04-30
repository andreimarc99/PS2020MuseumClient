package StatisticiRapoarte;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Controller.ServerCommunication;
import Model.Institutie;
import Model.Muzeu;
import Model.OperaDeArtaPlastica;
import Model.Sculptura;
import Model.Tablou;

public class Filtrare {
	private ServerCommunication serverCommunication = new ServerCommunication();

	public List<OperaDeArtaPlastica> filtrareDupaArtist(String artist) {
		List<Institutie> institutii = new ArrayList<Institutie>();
		try {
			institutii = serverCommunication.getAllInstitutii();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<OperaDeArtaPlastica> opereAleArtistuluiDat = new ArrayList<OperaDeArtaPlastica>();
		for (Institutie i : institutii) {
			for (OperaDeArtaPlastica o : i.getOpereDeArta()) {
				if (o.getNumeArtist().equals(artist)) {
					if (i instanceof Muzeu) {
						opereAleArtistuluiDat.add(o);
					} else {
						opereAleArtistuluiDat.add(o);
					}
				}
			}
		}
		return opereAleArtistuluiDat;
	}

	public List<OperaDeArtaPlastica> filtrareDupaTipulOperei(String tipulOperei) {
		assert tipulOperei.equals("Sculptura") || tipulOperei.equals("Tablou");
		List<Institutie> institutii = new ArrayList<Institutie>();
		try {
			institutii = serverCommunication.getAllInstitutii();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<OperaDeArtaPlastica> opereAleArtistuluiDat = new ArrayList<OperaDeArtaPlastica>();
		for (Institutie i : institutii) {
			for (OperaDeArtaPlastica o : i.getOpereDeArta()) {
				if (tipulOperei.equals("Sculptura")) {
					if (o instanceof Sculptura) {
						if (i instanceof Muzeu) {
							opereAleArtistuluiDat.add(o);
						} else {
							opereAleArtistuluiDat.add(o);
						}
					}
				} else if (tipulOperei.equals("Tablou")) {
					if (o instanceof Tablou) {
						if (i instanceof Muzeu) {
							opereAleArtistuluiDat.add(o);
						} else {
							opereAleArtistuluiDat.add(o);
						}
					}
				}
			}
		}
		return opereAleArtistuluiDat;
	}

	public List<OperaDeArtaPlastica> filtrareDupaInstitutie(String institutie) {
		List<Institutie> institutii = new ArrayList<Institutie>();
		try {
			institutii = serverCommunication.getAllInstitutii();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Integer idInst = null;
		try {
			idInst = Integer.valueOf(institutie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Institutie i : institutii) {
			if (i.getId() == idInst) {
				return i.getOpereDeArta();
			}
		}
		return null;
	}
}
