package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import Controller.RegisterPageActionListeners;

import javax.swing.JButton;

public class RegisterPage {

	private JFrame frame;
	private JTextField usernameTextField;
	private JPasswordField parolaTextField;
	private JComboBox<String> tipUtilizatorComboBox;
	private JButton btnInregistrare;
	private JButton btnBack;

	/**
	 * Create the application.
	 */
	public RegisterPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 11, 142, 14);
		frame.getContentPane().add(lblUsername);

		usernameTextField = new JTextField();
		usernameTextField.setBounds(10, 36, 154, 20);
		frame.getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);

		JLabel lblParola = new JLabel("Parola");
		lblParola.setBounds(10, 83, 142, 14);
		frame.getContentPane().add(lblParola);

		parolaTextField = new JPasswordField();
		parolaTextField.setBounds(10, 108, 154, 20);
		frame.getContentPane().add(parolaTextField);
		parolaTextField.setColumns(10);

		tipUtilizatorComboBox = new JComboBox<String>();
		tipUtilizatorComboBox.setBounds(270, 35, 154, 22);
		tipUtilizatorComboBox.addItem("Angajat");
		tipUtilizatorComboBox.addItem("Administrator");
		frame.getContentPane().add(tipUtilizatorComboBox);

		JLabel lblTipUtilizator = new JLabel("Tip utilizator");
		lblTipUtilizator.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipUtilizator.setBounds(270, 11, 154, 14);
		frame.getContentPane().add(lblTipUtilizator);

		btnInregistrare = new JButton("Inregistrare");
		btnInregistrare.setBounds(289, 227, 135, 23);
		frame.getContentPane().add(btnInregistrare);

		btnBack = new JButton("Back");
		btnBack.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnBack);
		
		new RegisterPageActionListeners().addActionListeners(this);
	}

	public JFrame getFrame() {
		return frame;
	}

	public String getUsername() {
		return usernameTextField.getText().trim();
	}

	public void setUsername(String username) {
		this.usernameTextField.setText(username);
	}

	@SuppressWarnings("deprecation")
	public String getParola() {
		return parolaTextField.getText().trim();
	}

	public void setParola(String parola) {
		this.parolaTextField.setText(parola);
	}

	public String getTipUtilizator() {
		return tipUtilizatorComboBox.getSelectedItem().toString();
	}

	public void setTipUtilizatorComboBox(JComboBox<String> tipUtilizatorComboBox) {
		this.tipUtilizatorComboBox = tipUtilizatorComboBox;
	}

	public JButton getBtnInregistrare() {
		return btnInregistrare;
	}

	public void setBtnInregistrare(JButton btnInregistrare) {
		this.btnInregistrare = btnInregistrare;
	}

	public JButton getBtnBack() {
		return btnBack;
	}

	public void setBtnBack(JButton btnBack) {
		this.btnBack = btnBack;
	}
}
