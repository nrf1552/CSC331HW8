package HW7;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
		JMenuItem menuitem;
		ButtonGroup radiogroup;
		JRadioButtonMenuItem radioItem;

		menuBar = new JMenuBar();
		
		// Create File menu
		menu = new JMenu("File");
		menuBar.add(menu);
		menuitem = new JMenuItem("Save", KeyEvent.VK_S);
		menuitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menu.add(menuitem);
		
		menuitem = new JMenuItem("Exit", KeyEvent.VK_X);
		menuitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menu.add(menuitem);		
		menuitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		// Create menu for all available images
		menu = new JMenu("Image");
		menuBar.add(menu);
		submenu = new JMenu("Select a supplied image");
		for (int i = 0; i < images.length; i++) {
			menuitem = new JMenuItem(images[i].replaceAll(".jpg", ""), acceleratorKeyCodeTracker + i);
			acceleratorKeyCodeTracker += i;
			menuitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1 + i, ActionEvent.ALT_MASK));
			submenu.add(menuitem);
			
			final int index = i;// needed to pass iterator into ActionListener 
			menuitem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					viewer.selectedImage = images[index];
					viewer.displayImageComponents();
				}
			});
		}
		menu.add(submenu);
		menu.addSeparator();
		menuitem = new JMenuItem("Select from web");
		menu.add(menuitem);
		

		return menuBar;
	}

}
