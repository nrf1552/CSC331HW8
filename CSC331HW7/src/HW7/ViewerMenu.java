package HW7;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ViewerMenu {

	JTextArea output;
	JScrollPane scrollPane;

	String[] images = { "Spring.jpg", "Summer.jpg", "Fall.jpg", "Winter.jpg" };

	int[] numberOfProblems = { 4, 9, 16 };
	String[] calculationType = {"Add/Subtract","Multiply/Divide"};

	int acceleratorKeyCodeTracker = KeyEvent.VK_1;
	int mnemonicKeyCode = KeyEvent.VK_1;

	public JMenuBar menu(Viewer viewer) {
		JMenuBar menuBar;
		JMenu menu;
		JMenu submenu;
		JMenuItem menuItem;

		menuBar = new JMenuBar();
		
		// Create File menu
		menu = new JMenu("File");
		menuBar.add(menu);
		menuItem = new JMenuItem("Save", KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menu.add(menuItem);		
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		// Create menu for all available images
		menu = new JMenu("Image");
		menuBar.add(menu);
		submenu = new JMenu("Select a supplied image");
		for (int i = 0; i < images.length; i++) {
			menuItem = new JMenuItem(images[i].replaceAll(".jpg", ""), acceleratorKeyCodeTracker + i);
			acceleratorKeyCodeTracker += i;
			menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1 + i, ActionEvent.ALT_MASK));
			submenu.add(menuItem);
			
			final int index = i;// needed to pass iterator into ActionListener 
			menuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					BufferedImage img = null;
					try {
						img = ImageIO.read(new FileInputStream(images[index]));
						
					} catch (IOException  e) {
						e.printStackTrace();
					}
					
					viewer.changeImage(img);
				}
			});
		}
		menu.add(submenu);
		
		// Pull from web option submenu
		menu.addSeparator();		
		submenu = new JMenu("Select from web");
		
		// Put label, field and button in a border layout for aesthetics
		JPanel urlPanel = new JPanel(new BorderLayout());
		JTextField urlField = new JTextField(20);
		JButton downloadButton = new JButton("Download");
		urlPanel.add(new JLabel("URL: "), BorderLayout.WEST);
		urlPanel.add(urlField, BorderLayout.CENTER);
		urlPanel.add(downloadButton, BorderLayout.EAST);
		
		downloadButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				String url = urlField.getText();
				
				BufferedImage img = null;
				try {
					img = ImageIO.read(new URL(url));
				} catch (IOException  e) {
					e.printStackTrace();
				}
				
				viewer.changeImage(img);
			}
		});
		
		submenu.add(urlPanel);
		menu.add(submenu);
		
		return menuBar;
	}

}
