import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * A GUI for ImgurMiner
 * @author Jayden Weaver
 *
 */
public class ImgurMinerGUI {

	public static void main(String args[]){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}


		JFrame frame = new JFrame("ImgurMiner");
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFocusable(false); //get rid of ugly dotted outline.
		tabbedPane.setPreferredSize(new Dimension(700,300));

		//Pane 1
		JPanel panel1 = new JPanel();
		JLabel label = new JLabel("User:");
		panel1.add(label);
		JTextField textField = new JTextField(20);
		panel1.add(textField);
		JButton enterButton = new JButton("Begin");

		//event listener on button
		enterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				UserMiner userMine = new UserMiner(textField.getText());
				userMine.startMining();		
			}
		});

		//add button
		panel1.add(enterButton);

		//Pane 2

		JPanel panel2 = new JPanel();
		JLabel label2 = new JLabel("Number of attempts: ");
		panel2.add(label2);
		JTextField textField2 = new JTextField(5);
		panel2.add(textField2);
		JButton enterButton2 = new JButton("Begin");

		//event listener on button
		enterButton2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ErraticMiner erratic = new ErraticMiner();
				ArrayList<String> images = erratic.startMining(Integer.parseInt(textField2.getText()));
				AlbumMiner save = new AlbumMiner("http://www.thisisjustaplaceholder.com/"); //this needs to be long, otherwise an index out of bounds exception is thrown by albumMiner.
				save.setUsername("Z-180");
				save.setAlbumName("Scattershot");
				save.saveImages(images);
			}
		});

		//add button
		panel2.add(enterButton2);

		//About panel		
		JPanel aboutPanel = new JPanel();
		aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));
		JLabel author = new JLabel("Jayden Weaver");
		JLabel github = new JLabel("github.com/jayden2013");
		JLabel twitter = new JLabel("twitter.com/weaverfever69");
		JLabel year = new JLabel("2017");
		aboutPanel.add(author);
		aboutPanel.add(github);
		aboutPanel.add(twitter);
		aboutPanel.add(year);

		//Add everything to tabbedPane
		tabbedPane.add("Z-750 Binary Rifle", panel1);	
		tabbedPane.add("Z-180 Scattershot", panel2);
		tabbedPane.add("About", aboutPanel);
		frame.add(tabbedPane);

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

	}

}
