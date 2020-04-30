package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Administrator;
import Model.Angajat;
import Model.User;
import View.AdminPage;
import View.AngajatPage;
import View.LoginPage;
import View.RegisterPage;
import View.VizitatorPage;

public class LoginPageActionListeners {

	public void addActionListeners(final LoginPage loginPage) {
		loginPage.getBtnLogin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean ok = false;
				if (!loginPage.getUsername().isEmpty() && !loginPage.getPassword().isEmpty()) {
					
					String username = loginPage.getUsername().trim();
					String password = loginPage.getPassword().trim();

					List<User> users = new ArrayList<User>();
					
					try {
						users = new ServerCommunication().getAllUsers();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

					for (User u : users) {
						if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
							if (u instanceof Angajat) {
								loginPage.getFrame().setVisible(false);
								new AngajatPage().getFrame().setVisible(true);
							} else if (u instanceof Administrator) {
								loginPage.getFrame().setVisible(false);
								new AdminPage().getFrame().setVisible(true);
							}
							
							ok = true;
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Completeaza toate campurile necesare.");
					ok = true;
				} 
				if (!ok) {
					JOptionPane.showMessageDialog(null, "Username sau parola incorecta");
				}
			}
		});

		loginPage.getBtnVizitator().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPage.getFrame().setVisible(false);
				new VizitatorPage().getFrame().setVisible(true);
			}
		});
		
		loginPage.getBtnRegister().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPage.getFrame().setVisible(false);
				new RegisterPage().getFrame().setVisible(true);
			}
		});
	}
}
