package projekt_grupp_5;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bird extends JLabel {
	
	private int  y_position;
	private int  x_position;
	private int height;
	private int width;
	private ImageIcon img = new ImageIcon(this.getClass().getResource("/bird.png"));
	
	public Bird() 
	{
	setImage();
	Dimension size = getPreferredSize();

	//ger position till f�geln och skapar bilden
	setPosition(400, 200, size.width, size.height);
	}
	
	public void setPosition(int x, int y, int width, int height) {
		x_position = x;
		y_position = y;
		this.width = width;
		this.height = height;
		setBounds(x, y, width, height);	
	}
	
	private void setImage() {
		super.setIcon(img);
	}
	

	public int getX() {return x_position;}
	
	public int getY() {return y_position;}
	
	public int getWidth() {return width;}
	
	public int getHeight() {return height;}
	
	
}
