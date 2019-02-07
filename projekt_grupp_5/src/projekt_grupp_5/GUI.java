package projekt_grupp_5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class GUI implements KeyListener, ActionListener  {
	private JFrame frame;
	private Container contentPane;
	private Bird untz;

	//f�gelns y position
	private int y_pos = 200;
	private static int period = 150;
	private Pipe array[] = new Pipe[6];
	int kaos;

	//�ndra tiden f�r att �teropa funktionen
	private Timer timer = new Timer(period,this); // Swing-timer

	public GUI() {
		makefram();
	}

	private void makefram() {
		frame = new JFrame("FLAPPY BIRDS");
		frame.setFocusable( true );
		contentPane = frame.getContentPane();
		//skapa paneler
		makePanels(contentPane);
		//l�gg  till knapp avlyssnare i JFramen
		frame.addKeyListener(this);
		frame.pack();
		//storlek p� jFRame
		frame.setSize(1280,850);
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
		//System.out.println("testar START \n");
		//rensar bort start meny
		contentPane.removeAll();
		contentPane.setLayout(null);
		//skapa bird
		untz = new Bird();
		contentPane.add(untz);
		//skapa (just nu) 3 pipes
		makepipes();
		//skapa bakground
		ImageIcon back = new ImageIcon(this.getClass().getResource("/sten.jpg"));
		JLabel background = new JLabel(back);
		background.setBounds(0, 0,1280,800);
		contentPane.add(background);
		//starta timer f�r att f�geln automatiskt skall �ka ned�t
		timer.start();
		contentPane.repaint();
	}

	//hur f�r jag dem att flytta p� sig?
	//kanske l�gga alla r�r i en array.
	//sedan anropa en funktion tex move. Som �ndrar positionen i x led p� alla r�r tex -20
	//funktionen move anropas efter en viss period 0.5s ?
	//om pipe �ker f�rbi sk�rmen �ndras positionen s� att den kommer tillbaka
	private void makepipes() {
		boolean upPipe = true;

		int set = 1280;
		int set2 = 1280;
		for(int i = 0; i<6; i++) {

			if(upPipe == true) {	
				Pipe p = new Pipe(true);
				upPipe(set,p);
				array[i] = p;
				contentPane.add(p);
				set = set + 500;
			}

			if(upPipe == false) {
				Pipe pipe = new Pipe(false);
				downPipe(set2,pipe);
				array[i] = pipe;
				contentPane.add(pipe);
				set2 = set2 + 500;
			}
			upPipe = !upPipe;
		}
	}



	private void upPipe(int x_pos, Pipe pipe) {
		Random rand = new Random();
		int random = rand.nextInt(150)-150;
		kaos = pipe.prefHeight() + random;
		pipe.setPosition(x_pos, 0 , pipe.prefWidth(), kaos );

	}
	private void downPipe(int x_pos,Pipe pipe) {
		pipe.setPosition(x_pos, (500+kaos) , pipe.prefWidth(), pipe.prefHeight()*4 );
	}



	//funktion f�r att trigga HighScore
	private void HS() {
		System.out.println("testar HIGH SCORE \n");
	}

	//timer ropar upp den h�r funktionen f�r att f� f�gelna att �ka ner automatiskt(tyngkraft)
	// testa med att flytta r�ren ocks�
	public void actionPerformed(ActionEvent e) {
		///////////////////////////////////////f�geln
		y_pos = y_pos + 40;
		untz.setPosition(400, y_pos);
		//contentPane.repaint(); 
		////////////////////////////////////////////
		///////////////////////////////////////////f� r�ren att r�ren r�ra p�s sig
		for(Pipe test : array) {
			int x_pos =	test.getX();
			int y_pos = test.getY();

			if(test.upPipe() == true) {
				if(x_pos < 0 - test.prefWidth()) {
					upPipe(1280, test);
				}
				else {
					test.setPosition(x_pos-40,  y_pos , test.prefWidth(), test.prefHeight());
				}
			}
			if(test.upPipe() == false) {
				if(x_pos < 0 - test.prefWidth()) {
					downPipe(1280, test);
				}
				else {
					test.setPosition(x_pos-40,  y_pos , test.prefWidth(), test.prefHeight()*4);
				}
			}
		}
	} 




	@Override
	//vid klick upp s� skall f�geln r�ra sig upp�t
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("testar key UP \n");
			y_pos = y_pos - 100;
			untz.setPosition(400, y_pos);
			contentPane.repaint(); 
		}
	}

	//anv�nds ej
	public void keyPressed (KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
