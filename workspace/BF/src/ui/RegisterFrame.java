package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import rmi.RemoteHelper;

public class RegisterFrame extends JFrame {
	private JFrame frame;
	private String username;
	private String password;
	private int frameWidth = 300;
	private int frameHeight = 200;
	private JLabel resultLabel;
	private JPasswordField passwordField; 
	private JTextField usernameTextField;
	
	public RegisterFrame() {
		initGUI();
	}
	
	public void initGUI() {
		frame = new JFrame("Register");
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(frameWidth, frameHeight);
		frame.setLocation((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2);
		JLabel usernameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");
		usernameLabel.setBounds(30, 30, 60, 20);
		passwordLabel.setBounds(30, 50, 60, 20);
		frame.getContentPane().add(usernameLabel);
		frame.getContentPane().add(passwordLabel);
		resultLabel = new JLabel();
		resultLabel.setBounds(80, 80, 170, 20);
		frame.getContentPane().add(resultLabel);
		usernameTextField = new JTextField();
		usernameTextField.setBounds(100, 30, 150, 20);
		frame.getContentPane().add(usernameTextField);
		passwordField = new JPasswordField();
		passwordField.setBounds(100, 50, 150, 20);
		frame.getContentPane().add(passwordField);
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(100, 120, 90, 20);
		frame.getContentPane().add(registerButton);
		registerButton.addActionListener(new RegisterActionListener());
	}

	private String turnCharsToString(char[] chars) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			strBuf.append(chars[i]);
		}
		return strBuf.toString().trim();
	}
	
	class RegisterActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				username = usernameTextField.getText();
				password = turnCharsToString(passwordField.getPassword());
				resultLabel.setText(RemoteHelper.getInstance().getUserService().register(username, password));
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}
}
