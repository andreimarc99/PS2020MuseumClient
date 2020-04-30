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
import View.LoginPage;
import View.RegisterPage;

public class RegisterPageActionListeners {

	private ServerCommunication serverCommunication = new ServerCommunication();

	public void addActionListeners(final RegisterPage registerPage) {
		registerPage.getBtnInregistrare().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean ok = true;
				if (!registerPage.getParola().isEmpty() && !registerPage.getUsername().isEmpty()) {
					List<User> users = new ArrayList<User>();

					try {
						users = serverCommunication.getAllUsers();

						for (User u : users) {
							if (registerPage.getUsername().trim().equals(u.getUsername())) {
								JOptionPane.showMessageDialog(null, "Existing username. Try again");
								ok = false;
							}
						}
						if (ok) {
							if (registerPage.getTipUtilizator().equals("Angajat")) {
								Angajat angajat = new Angajat(registerPage.getUsername().trim(),
										registerPage.getParola().trim());
								serverCommunication.createUser(angajat);
								registerPage.getFrame().setVisible(false);
								new LoginPage().getFrame().setVisible(true);
							} else {
								Administrator administrator = new Administrator(registerPage.getUsername().trim(),
										registerPage.getParola().trim());
								serverCommunication.createUser(administrator);
								registerPage.getFrame().setVisible(false);
								new LoginPage().getFrame().setVisible(true);
							}
						}
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		registerPage.getBtnBack().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerPage.getFrame().setVisible(false);
				new LoginPage().getFrame().setVisible(true);
			}
		});
	}
}