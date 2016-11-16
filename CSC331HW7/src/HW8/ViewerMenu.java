package HW8;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * 
 * @author Nick
 * 
 * Creates the viewer menu
 *
 */
public class ViewerMenu {

	JTextArea output;
	JScrollPane scrollPane;
	Main viewer;

	String[] images = { "Spring.jpg", "Summer.jpg", "Fall.jpg", "Winter.jpg" };

	int[] numberOfProblems = { 4, 9, 16 };
	String[] calculationType = { "Add/Subtract", "Multiply/Divide" };

	int acceleratorKeyCodeTracker = KeyEvent.VK_1;
	int mnemonicKeyCode = KeyEvent.VK_1;

	public JMenuBar menu(Main viewer) {
		this.viewer = viewer;

		JMenuBar menuBar;
		JMenu menu;
		JMenu submenu;
		JMenuItem menuItem;

		menuBar = new JMenuBar();

		// Create File menu
		menu = new JMenu("File");
		menuBar.add(menu);

		// Saves enhanced image to project folder
		menuItem = new JMenuItem("Save", KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveImage();
			}
		});

		// Saves CSV to project folder
		menuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				writeCsv();
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
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					BufferedImage img = null;
					try {
						img = ImageIO.read(new FileInputStream(images[index]));

					} catch (IOException e) {
						e.printStackTrace();
					}

					viewer.images.add(new ImageData(img));
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

		downloadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url = urlField.getText();

				BufferedImage img = null;
				try {
					img = ImageIO.read(new URL(url));
				} catch (IOException e) {
					e.printStackTrace();
				}

				viewer.images.add(new ImageData(img));
				viewer.changeImage(img);
			}
		});

		submenu.add(urlPanel);
		menu.add(submenu);

		// Create Enhancement menu
		menu = new JMenu("Enhancement");
		menuBar.add(menu);

		menuItem = new JMenuItem("Histogram Equalized", KeyEvent.VK_H);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveImage();
			}
		});

		menuItem = new JMenuItem("Sobel Edge Detection", KeyEvent.VK_E);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveImage();
			}
		});
		
		submenu = new JMenu("Background Image");
		
		menuItem = new JMenuItem("Original color image");
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Gray scale image");
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("Black image");
		submenu.add(menuItem);
		
		menuItem = new JMenuItem("White image");
		submenu.add(menuItem);
		

		return menuBar;
	}

	private void writeCsv() {
		String filename = new SimpleDateFormat("yyyyMMddhhmmss'.csv'").format(new Date());

		try {
			PrintWriter printWriter = new PrintWriter(filename, "UTF-8");
			printWriter.println("Nick Fields");

			ArrayList<String> strings = new ArrayList<String>();
			for (int i = 0; i <= 255; i++) {
				strings.add(Integer.toString(i));
			}
			// print zero thru 255, separated by commas
			printWriter.println("Pixel," + String.join(",", strings));
			// blank line
			printWriter.println();

			//
			for (ImageData iData : viewer.images) {
				// initialize list of values
				ArrayList<String> s = new ArrayList<>();
				// name
				s.add(iData.getName());
				// mode
				s.add("Original");
				// average intensity
				s.add(Integer.toString(iData.originalHistogram.getAveragePixelIntensity()));
				// most frequent intensity
				s.add("MostFrequentRed=" + Integer.toString(iData.originalHistogram.getMostFrequentRed()));
				s.add("MostFrequentGreen" + Integer.toString(iData.originalHistogram.getMostFrequentGreen()));
				s.add("MostFrequentBlue=" + Integer.toString(iData.originalHistogram.getMostFrequentBlue()));
				// sum of pixel counts in each quarter
				s.add("Q1 Pixel Count: ");
				s.add("Q2 Pixel Count: ");
				s.add("Q3 Pixel Count: ");
				s.add("Q4 Pixel Count: ");
				// histogram data

				printWriter.println("");

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void saveImage() {
		String filename = new SimpleDateFormat("yyyyMMddhhmmss'.txt'").format(new Date());
	}
}
