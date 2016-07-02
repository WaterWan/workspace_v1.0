package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import img.ImgSystem;
import rmi.RemoteHelper;

public class MainFrame extends JFrame {
	private JFrame frame;
	private static JTextArea codeArea;
	public static JTextArea getCodeArea() {
		return codeArea;
	}


	private JTextArea inputArea;
	private JLabel resultLabel;
	private JLabel remindLabel;
	private JLabel inputLabel;
	private static JLabel outputLabel;
	public static JLabel getOutputLabel() {
		return outputLabel;
	}

	public static void setOutputLabel(JLabel outputLabel) {
		MainFrame.outputLabel = outputLabel;
	}


	private int frameWidth = 1200;
	private int frameHeight = 800;
	private static String username;
	private static String password;
	private static String filename;
	private static String code;
	private String param;
	private String result;


	private Font myFont;
	private Font codeAreaFont;
	private Font inputAreaFont;
	private Font resultFont;
	private int codeSize = 15;
	private int inputSize = 15;
	private int resultSize = 15;
	private int offset = 20;
	private ChangeStyle cs;
	private static JMenu versionMenu;
	


	public static JMenu getVersionMenu() {
		return versionMenu;
	}

	public static String getCode() {
		return code;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String name) {
		username = name;
	}

	public String getPassword() {
		return password;
	}

	public static void setPassword(String word) {
		password = word;
	}

	public static String getFilename() {
		return filename;
	}

	public static void setFilename(String filename) {
		MainFrame.filename = filename;
	}

	public MainFrame() {
		// 创建窗体
		frame = new JFrame("BF Client");
		// frame.setLayout(new BorderLayout());
		frame.setLayout(null);

		cs = new ChangeStyle();
		username = "";
		filename = "";
		
		myFont = new Font("TimesRoman", Font.PLAIN, 20);
		codeAreaFont = new Font("TimesRoman", Font.PLAIN, codeSize);
		inputAreaFont = new Font("TimesRoman", Font.PLAIN, inputSize);
		resultFont = new Font("TimesRoman", Font.PLAIN, resultSize);
		frame.setFont(myFont);
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		fileMenu.setBounds(0, 0, 0, 0);
		JMenu runMenu = new JMenu("Run");
		menuBar.add(runMenu);
		JMenu userMenu = new JMenu("User");
		menuBar.add(userMenu);
		JMenu codeMenu = new JMenu("Code");
		menuBar.add(codeMenu);
		JMenu inputMenu = new JMenu("Input");
		menuBar.add(inputMenu);
		JMenu resultMenu = new JMenu("Result");
		menuBar.add(resultMenu);
		versionMenu = new JMenu("Version");
		menuBar.add(versionMenu);
		

		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(exitMenuItem);
		
		JMenuItem runMenuItem = new JMenuItem("Execute");
		runMenu.add(runMenuItem);
		
		
		JMenuItem registerMenuItem = new JMenuItem("Register");
		userMenu.add(registerMenuItem);
		JMenuItem loginMenuItem = new JMenuItem("Login");
		userMenu.add(loginMenuItem);
		JMenuItem logoutMenuItem = new JMenuItem("Logout");
		userMenu.add(logoutMenuItem);
		
		JMenuItem enlargeCodeMenuItem = new JMenuItem("Enlarge");
		codeMenu.add(enlargeCodeMenuItem);
		JMenuItem shrinkCodeMenuItem = new JMenuItem("Shrink");
		codeMenu.add(shrinkCodeMenuItem);
		
		JMenuItem enlargeInputMenuItem = new JMenuItem("Enlarge");
		inputMenu.add(enlargeInputMenuItem);
		JMenuItem shrinkInputMenuItem = new JMenuItem("Shrink");
		inputMenu.add(shrinkInputMenuItem);
		
		
		JMenuItem enlargeResultMenuItem = new JMenuItem("Enlarge");
		resultMenu.add(enlargeResultMenuItem);
		JMenuItem shrinkResultMenuitem = new JMenuItem("Shrink");
		resultMenu.add(shrinkResultMenuitem);
		
		newMenuItem.addActionListener(new NewActionLister());
		openMenuItem.addActionListener(new OpenActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		runMenuItem.addActionListener(new RunActionListener());
		exitMenuItem.addActionListener(new ExitActionListener());
		registerMenuItem.addActionListener(new RegisterActionListener());
		enlargeCodeMenuItem.addActionListener(new EnlargeCodeActionListener());
		shrinkCodeMenuItem.addActionListener(new ShrinkCodeActionListener());
		enlargeInputMenuItem.addActionListener(new EnlargeInputActionListener());
		shrinkInputMenuItem.addActionListener(new ShrinkInputActionListener());
		enlargeResultMenuItem.addActionListener(new EnlargeResultActionListener());
		shrinkResultMenuitem.addActionListener(new ShrinkResultActionListener());
		loginMenuItem.addActionListener(new LoginActionListener());
		logoutMenuItem.addActionListener(new LogoutActionListener());
		
		
		codeArea = new JTextArea();
		codeArea.setFont(codeAreaFont);
		codeArea.setBackground(Color.WHITE);
		JScrollPane jsp = new JScrollPane(codeArea);
		jsp.setBounds(0, 0, frameWidth, frameHeight / 2);
		frame.getContentPane().add(jsp);
		inputArea = new JTextArea();
		inputArea.setFont(inputAreaFont);
		inputArea.setBackground(Color.WHITE);
		JScrollPane inputJsp = new JScrollPane(inputArea);
		inputJsp.setBounds(0, frameHeight / 2 + offset * 2, frameWidth / 2 - offset * 4, frameHeight / 2 - offset * 7);
		frame.getContentPane().add(inputJsp);

		// 显示结果
		inputLabel = new JLabel();
		inputLabel.setText("Input:");
		inputLabel.setFont(myFont);
		inputLabel.setBounds(0, frameHeight / 2 + offset - 5, 50, 30);
		frame.add(inputLabel);

		// 显示结果
		remindLabel = new JLabel();
		remindLabel.setText("Result:");
		remindLabel.setFont(myFont);
		remindLabel.setBounds(frameWidth / 2, frameHeight / 2 + offset, 100, 30);
		frame.add(remindLabel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		// 不允许改变窗口大小
		frame.setResizable(false);
		// 获取屏幕尺寸
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 居中显示
		frame.setLocation((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2);
		frame.setVisible(true);

		resultLabel = new JLabel();
		resultLabel.setBounds(frameWidth / 2, frameHeight / 2 , 200, 200);
		resultLabel.setFont(resultFont);
		frame.add(resultLabel);
		
		outputLabel = new JLabel();
		outputLabel.setBounds(0, frameHeight - 5 * offset, 200, 30);
		frame.add(outputLabel);
		outputLabel.setText("尚未登录");

		// 设置图标
		frame.setIconImage(ImgSystem.LOGO);
		frame.setVisible(true);
	}

	class OpenActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!username.equals("")) {
				OpenFrame openFrame = new OpenFrame();
			}else {
				outputLabel.setText("尚未登录，无法打开文件");
			}
			
		}
		
	}

	class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			code = codeArea.getText();
			try {
				if (!username.equals("")) {
					if (!filename.equals("")) {
						RemoteHelper.getInstance().getIOService().writeFile(code, username, filename);
					} else {
						code = codeArea.getText();
						FileNameFrame fnf = new FileNameFrame();
						
					}
					outputLabel.setText("保存成功");
				}else {
					outputLabel.setText("尚未登录，无法保存");
				}
				// TODO:writeFile的具体实现需要修改一下 
				
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	class NewActionLister implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (!username.equals("")) {
				new FileNameFrame();
			}else {
				outputLabel.setText("尚未登录，无法新建文件");
			}
			
			
		}
	}
	
	/**
	 * 代码运行
	 * @author Water
	 *
	 */
	class RunActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			code = codeArea.getText();
			param = inputArea.getText();
			try {
				result = "";
				result = RemoteHelper.getInstance().getExecuteService().execute(code, param);
				resultLabel.setText(result);
			} catch (Exception e2) {}
		}
	}
	
	class ExitActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO: 还有一些情况没有判断
			int option = JOptionPane.showConfirmDialog(frame, "您是否确定要退出？", "退出", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				try {
					if (username.equals("")) {
						MainFrame.setUsername("SystemTemp");
					}
					if (filename.equals("")) {
						MainFrame.setFilename("TempFile");
					}
					RemoteHelper.getInstance().getIOService().writeFile(code, username, filename);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					System.exit(0);
				}
				
			}
		}
	}
	
	class RegisterActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			RegisterFrame registerFrame = new RegisterFrame();
		}
	}
	
	class EnlargeCodeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			codeSize = cs.changeFontSize(codeArea, codeSize, 2);
		}
	}
	
	class ShrinkCodeActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (codeSize >= 11) {
				codeSize = cs.changeFontSize(codeArea, codeSize, -2);
			}
		}
	}
	
	class EnlargeInputActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			inputSize = cs.changeFontSize(inputArea, inputSize, 2);
		}
	}
	
	class ShrinkInputActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (inputSize >= 11) {
				inputSize = cs.changeFontSize(inputArea, inputSize, -2);
			}
		}
	}
	
	class EnlargeResultActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
				resultSize = cs.changeFontSize(resultLabel, resultSize, 2);
		}
	}
	
	class ShrinkResultActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (resultSize >= 11) {
				resultSize = cs.changeFontSize(resultLabel, resultSize, -2);
			}
		}
	}
	
	class LoginActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			LoginFrame loginFrame = new LoginFrame();
		}
	}
	
	
	class LogoutActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (username.equals("")) {
				outputLabel.setText("尚未登录，无需退出");
			} else {
				int option = JOptionPane.showConfirmDialog(frame, "您是否确定要登出？", "退出", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					username = "";
					password = "";
					outputLabel.setText("尚未登录");
				}
			}

		}
		
	}

}
