package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import rmi.RemoteHelper;

public class FileNameFrame extends JFrame {
	private JFrame frame;
	private String filename;
	private int frameWidth = 300;
	private int frameHeight = 200;
	private JLabel fileLabel;
	private JTextField filenameTextField;
	private JButton ensure;
	private JLabel resultLabel;
	
	public FileNameFrame() {
		frame = new JFrame("Login");
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(frameWidth, frameHeight);
		frame.setLocation((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2);
		fileLabel = new JLabel();
		fileLabel.setBounds(30, 30, 150, 20);
		fileLabel.setText("The name of the file: ");
		frame.getContentPane().add(fileLabel);
		filenameTextField = new JTextField();
		filenameTextField.setBounds(30, 60, 200, 20);
		frame.getContentPane().add(filenameTextField);
		
		resultLabel = new JLabel();
		resultLabel.setText("2333");
		resultLabel.setBounds(30, 90, 200, 20);
		frame.getContentPane().add(resultLabel);
		ensure = new JButton("确定");
		ensure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				filename = filenameTextField.getText();
				if (!filename.equals("")) {
					try {
						if (!RemoteHelper.getInstance().getIOService().fileExists(MainFrame.getUsername(), filename)) {
							MainFrame.setFilename(filename);
							MainFrame.getOutputLabel().setText("文件名为 " + filename);
							RemoteHelper.getInstance().getIOService().writeFile(MainFrame.getCode(), MainFrame.getUsername(), filename);
							resultLabel.setText("可以保存");
							frame.setVisible(false);
						} else {
							resultLabel.setText("该文件名已存在");
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				} else {
					resultLabel.setText("文件名不能为空");
				}
				
			}
		});
		ensure.setBounds(100, 120, 60, 20);
		frame.getContentPane().add(ensure);
		
	}
	
}
