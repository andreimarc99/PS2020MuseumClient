package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controller.LoginPageActionListeners;

import javax.swing.JButton;

public class LoginPage {

	private JFrame frame;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JButton btnLogin;
	private JButton btnVizitator;
	private JButton btnRegister;

	/**
	 * Create the application.
	 */
	public LoginPage() {
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
		lblUsername.setBounds(10, 11, 76, 14);
		frame.getContentPane().add(lblUsername);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(10, 30, 96, 20);
		frame.getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 61, 76, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(10, 80, 96, 20);
		frame.getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		btnVizitator = new JButton("Vizitator");
		btnVizitator.setBounds(236, 227, 89, 23);
		frame.getContentPane().add(btnVizitator);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnRegister);
		
		new LoginPageActionListeners().addActionListeners(this);
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
	public String getPassword() {
		return passwordTextField.getText().trim();
	}

	public void setPasswordTextField(JPasswordField passwordTextField) {
		this.passwordTextField = passwordTextField;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	public JButton getBtnVizitator() {
		return btnVizitator;
	}

	public void setBtnVizitator(JButton btnVizitator) {
		this.btnVizitator = btnVizitator;
	}

	public JButton getBtnRegister() {
		return btnRegister;
	}

	public void setBtnRegister(JButton btnRegister) {
		this.btnRegister = btnRegister;
	}
}
