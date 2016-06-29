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

public class LoginFrame extends JFrame {
	private JFrame frame;
	public String username;
	public String password;
	private int frameWidth = 300;
	private int frameHeight = 200;
	private JLabel resultLabel;
	private JPasswordField passwordField; 
	private JTextField usernameTextField;
	
	public LoginFrame(){
		initGUI();
	}
	
	public void initGUI(){
		frame = new JFrame("Login");
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(frameWidth, frameHeight);
		frame.setLocation((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2);
		JLabel usernameLabel = new JLabel("Username");
		JLabel passwordLabel = new JLabel("Password");
		username = "";
		password = "";
		usernameLabel.setBounds(30, 30, 60, 20);
		passwordLabel.setBounds(30, 50, 60, 20);
		frame.getContentPane().add(usernameLabel);
		frame.getContentPane().add(passwordLabel);
		resultLabel = new JLabel();
		resultLabel.setBounds(100, 80, 170, 20);
		frame.getContentPane().add(resultLabel);
		usernameTextField = new JTextField();
		usernameTextField.setBounds(100, 30, 150, 20);
		frame.getContentPane().add(usernameTextField);
		passwordField = new JPasswordField();
		passwordField.setBounds(100, 50, 150, 20);
		frame.getContentPane().add(passwordField);
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(100, 120, 90, 20);
		frame.add(loginButton);
		loginButton.addActionListener(new LoginActionListener());
	}

	private String turnCharsToString(char[] chars) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			strBuf.append(chars[i]);
		}
		return strBuf.toString().trim();
	}
	
	class LoginActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String tempUsername = usernameTextField.getText();
			String tempPassword = turnCharsToString(passwordField.getPassword());
			boolean canLogin = false;
			try {
				canLogin = RemoteHelper.getInstance().getUserService().login(tempUsername, tempPassword);
				if (canLogin) {
					MainFrame.getOutputLabel().setText("登录成功!");
					MainFrame.setUsername(tempUsername);
					MainFrame.setPassword(tempPassword);
					frame.setVisible(false);
					
				}else {
					MainFrame.setUsername("");
					MainFrame.setPassword("");
					passwordField.setText("");
					resultLabel.setText("账号不存在或密码有误!");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		}
	}
}
