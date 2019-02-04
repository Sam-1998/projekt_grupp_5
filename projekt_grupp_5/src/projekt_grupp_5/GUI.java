package projekt_grupp_5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI implements KeyListener, ActionListener  {
	private JFrame frame;
	private Container contentPane;
	private Bird untz;
    private Dimension size;
    private int y_pos = 200;
    
    JLabel background;
    Timer timer = new Timer(600,this); // Swing-timer
	
	public GUI() {
		makefram();
	}

	private void makefram() {
		frame = new JFrame("FLAPPY BIRDS");
		contentPane = frame.getContentPane();

		//skapa paneler
		makePanels(contentPane);
		 //l�gg  till knapp avlyssnare i JFramen
		frame.addKeyListener(this);
		//frame.pack();
		
		//storlek p� jFRame
		frame.setSize(1280,800);
		frame.setVisible(true);
	}

	//skapar knapparna till start menyn och 
	public void makePanels(Container contentPane) {
		
		contentPane.setLayout(new GridLayout(3,2,3,3));
		JButton start = new JButton("START");
		//procedur n�r start trycks
		start.addActionListener(
				(ActionEvent e) -> {start(); }
				);
		contentPane.add(start);

		JButton HS = new JButton("HIGH SCORE");
		HS.addActionListener(
				(ActionEvent e) -> {HS(); }
				);
		contentPane.add(HS);
	}

	//funktion f�r att trigga ig�ng spelet
	private void start() {
		System.out.println("testar START \n");
		//rensar bort start meny
		contentPane.removeAll();
		
		//anropa funktion som startar spelet?????
		//game.start();
		
		//skapa bird
		ImageIcon img = new ImageIcon(this.getClass().getResource("/bird.png"));
		untz = new Bird(img);
		 size = untz.getPreferredSize();
		 untz.setBounds(400, 200, size.width, size.height);
		contentPane.add(untz);
		
		//skapa background
        ImageIcon back = new ImageIcon(this.getClass().getResource("/sten.jpg"));
		background = new JLabel(back);
		background.setBounds(0, 0,1280,800);
		contentPane.add(background);
		
		//starta timer f�r att f�geln ska automatiskt �ka ned�t
		timer.start();
		
		contentPane.repaint();
	}

	//funktion f�r att trigga HighScore
	private void HS() {
		System.out.println("testar HIGH SCORE \n");
		}
	
	 public void keyPressed (KeyEvent e) {}
	 public void keyTyped(KeyEvent e) {}
	    
	 //timer ropar upp den h�r funktionen f�r att f� f�gelna att �ka ner
	    public void actionPerformed(ActionEvent e) {
	    	y_pos = y_pos + 10;
        	untz.setBounds(400, y_pos, size.width, size.height);
        	contentPane.repaint(); 
	    	 } 
	
	
	 @Override
	    public void keyReleased(KeyEvent e) {
	        if (e.getKeyCode() == KeyEvent.VK_UP) {
	    		System.out.println("testar key UP \n");
	        	y_pos = y_pos - 10;
	        	untz.setBounds(400, y_pos, size.width, size.height);
	        	contentPane.repaint(); 
	        }
	    }
	
}
