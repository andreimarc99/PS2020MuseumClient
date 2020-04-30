package StatisticiRapoarte;

import java.awt.Color;
import java.io.IOException;
import java.util.*;

import Model.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import Controller.ServerCommunication;

public class Statistici {
	private ServerCommunication serverCommunication = new ServerCommunication();

	public int getNrOpere(List<Institutie> institutii) {
		int cnt = 0;
		for (Institutie i : institutii) {
			for (@SuppressWarnings("unused")
			OperaDeArtaPlastica o : i.getOpereDeArta()) {
				++cnt;
			}
		}
		return cnt;
	}

	public int getNrOpereDinInstitutie(Institutie i) {
		int cnt = 0;
		for (@SuppressWarnings("unused")
		OperaDeArtaPlastica o : i.getOpereDeArta()) {
			++cnt;
		}
		return cnt;
	}

	public void generareStatistici() {
		List<Institutie> institutii = new ArrayList<Institutie>();
		try {
			institutii = serverCommunication.getAllInstitutii();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int nrTotalOpere = getNrOpere(institutii);

		DefaultCategoryDataset dcd = new DefaultCategoryDataset();

		for (Institutie i : institutii) {
			dcd.setValue(100 * getNrOpereDinInstitutie(i) / (float) nrTotalOpere, "Procentaj", i.getDenumire());
		}

		JFreeChart chart = ChartFactory.createBarChart("Procente dupa institutia unde sunt expuse opere", "Opere",
				"Procentaj", dcd, PlotOrientation.VERTICAL, true, true, false);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.black);

		ChartFrame chartFrame = new ChartFrame("Procente dupa institutia unde sunt expuse opere", chart, true);
		chartFrame.setVisible(true);
		chartFrame.setSize(1000, 500);

		for (Institutie i : institutii) {
			DefaultCategoryDataset dcd1 = new DefaultCategoryDataset();
			int nrTabMuzeu = 0;
			int nrTabGal = 0;
			int nrSculMuzeu = 0;
			int nrSculGal = 0;
			for (OperaDeArtaPlastica o : i.getOpereDeArta()) {
				if (o instanceof TablouMuzeu) {
					++nrTabMuzeu;
				} else if (o instanceof TablouGalerieArta) {
					++nrTabGal;
				} else if (o instanceof SculpturaMuzeu) {
					++nrSculMuzeu;
				} else {
					++nrSculGal;
				}
			}
			dcd1.setValue(nrTabMuzeu, "Numar", "Tablou muzeu");
			dcd1.setValue(nrTabGal, "Numar", "Tablou galerie");
			dcd1.setValue(nrSculMuzeu, "Numar", "Sculptura muzeu");
			dcd1.setValue(nrSculGal, "Numar", "Sculptura galerie");

			ChartFrame chartFrame1 = new ChartFrame("Tipuri de opera la " + i.getDenumire(),
					ChartFactory.createBarChart("Tipuri de opera la " + i.getDenumire(), "Tip", "Numar", dcd1,
							PlotOrientation.VERTICAL, true, true, false),
					true);
			chartFrame1.setVisible(true);
			chartFrame1.setSize(700, 500);
		}
	}
}
