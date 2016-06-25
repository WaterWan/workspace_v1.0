package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import img.ImgSystem;
import rmi.RemoteHelper;

public class MainFrame extends JFrame {
	private JTextArea textArea;
	private JTextArea inputArea;
	private JLabel resultLabel;
	private JLabel remindLabel;
	private JLabel inputLabel;
	private int frameWidth = 1200;
	private int frameHeight = 800;
	private String code;
	private String param;
	private String result;
	private Font myFont;
	private int offset = 20;

	public MainFrame() {
		// 创建窗体
		JFrame frame = new JFrame("BF Client");
		// frame.setLayout(new BorderLayout());
		frame.setLayout(null);

		myFont = new Font("TimesRoman", Font.PLAIN, 15);
		frame.setFont(myFont);
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		fileMenu.setBounds(0, 0, 0, 0);

		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		JMenuItem openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		JMenuItem saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		JMenuItem runMenuItem = new JMenuItem("Run");
		fileMenu.add(runMenuItem);
		frame.setJMenuBar(menuBar);

		newMenuItem.addActionListener(new MenuItemActionListener());
		openMenuItem.addActionListener(new MenuItemActionListener());
		saveMenuItem.addActionListener(new SaveActionListener());
		runMenuItem.addActionListener(new RunActionListener());

		textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);
		JScrollPane jsp = new JScrollPane(textArea);
		jsp.setBounds(0, 0, frameWidth, frameHeight / 2);
		frame.getContentPane().add(jsp);

		inputArea = new JTextArea();
		inputArea.setBackground(Color.WHITE);
		JScrollPane inputJsp = new JScrollPane(inputArea);
		inputJsp.setBounds(0, frameHeight / 2 + offset * 2, frameWidth / 2 - offset * 3, frameHeight / 2 - offset * 7);
		frame.getContentPane().add(inputJsp);

		// 显示结果
		inputLabel = new JLabel();
		inputLabel.setText("Input:");
		inputLabel.setFont(myFont);
		inputLabel.setBounds(0, frameHeight / 2 + offset - 5, 50, 20);
		frame.add(inputLabel);

		// 显示结果
		remindLabel = new JLabel();
		remindLabel.setText("Result:");
		remindLabel.setFont(myFont);
		remindLabel.setBounds(frameWidth / 2, frameHeight / 2 + offset, 50, 20);
		frame.add(remindLabel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		// 获取屏幕尺寸
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 居中显示
		frame.setLocation((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2);
		frame.setVisible(true);

		resultLabel = new JLabel();
		resultLabel.setBounds(frameWidth / 2, frameHeight / 2 + offset * 3, 200, 200);
		resultLabel.setText("1111");
		frame.add(resultLabel);

		// 设置图标
		frame.setIconImage(ImgSystem.LOGO);
		frame.setVisible(true);
	}

	class MenuItemActionListener implements ActionListener {
		/**
		 * 子菜单响应事件
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Open")) {
				textArea.setText("Open");
			} else if (cmd.equals("Save")) {
				textArea.setText("Save");
			} else if (cmd.equals("Run")) {
				resultLabel.setText("Hello, result");
			}
		}
	}

	class SaveActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			code = textArea.getText();
			try {
				RemoteHelper.getInstance().getIOService().writeFile(code, "admin", "code");
				System.out.println("Save action finishes");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}

	}

	class RunActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			code = textArea.getText();
			param = inputArea.getText();
			try {
				System.out.println("try");
				result = "";
				result = RemoteHelper.getInstance().getExecuteService().execute(code, param);
				System.out.println("get...");
				resultLabel.setText(result);
				System.out.println("Succeed!");
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}
}
