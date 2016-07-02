package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import rmi.RemoteHelper;

public class OpenFrame extends JFrame{
	private JFrame frame;
	private String filename;
	private int frameWidth = 300;
	private int frameHeight = 200;
	private JLabel openLabel;
	private JComboBox<String> fileBox;
	private String filepath;
	private JButton ensure;
	
	
	public OpenFrame() {
		frame = new JFrame("Open");
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		filepath = "user/" + MainFrame.getUsername();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(frameWidth, frameHeight);
		frame.setLocation((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2);
		openLabel = new JLabel();
		frame.getContentPane().add(openLabel);
		openLabel.setBounds(0, 30, 80, 20);
		ensure = new JButton("确定");
		ensure.setBounds(120, 80, 60, 40);
		frame.add(ensure);

		try {
			fileBox = new JComboBox<>(RemoteHelper.getInstance().getIOService().getFileNames(filepath));
			fileBox.setMaximumRowCount(6);
			fileBox.setBounds(80, 0, 150, 50);
			frame.getContentPane().add(fileBox);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		ensure.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.getOutputLabel().setText((String)fileBox.getSelectedItem());
				MainFrame.setFilename((String)fileBox.getSelectedItem());
				MainFrame.getCodeArea().setText("");
				frame.setVisible(false);
				String path = "user/" + MainFrame.getUsername() + "/" + (String)fileBox.getSelectedItem();
				try {
					String[] versionNames = RemoteHelper.getInstance().getIOService().getVersionNames(path);
 					MainFrame.getVersionMenu().removeAll();
					for (int i = 0; i < versionNames.length; i++) {
 						final int index = i;
 						JMenuItem jmi = new JMenuItem(versionNames[i]);
						MainFrame.getVersionMenu().add(jmi);
						jmi.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String code = "";
								try {
									MainFrame.getOutputLabel().setText(MainFrame.getFilename() + "/" + versionNames[index]);
									code = RemoteHelper.getInstance().getIOService().readFile(MainFrame.getUsername(), MainFrame.getFilename() + "/" + versionNames[index]);
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								MainFrame.getCodeArea().setText(code);
							}
						});
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
}
