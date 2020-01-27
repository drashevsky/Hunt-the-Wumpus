import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class GraphicalInterfaceSample extends JPanel
{	
	static JFrame frame;
    static GraphicalInterfaceSample gui;
    Image caveImage;
    int currentRoom = 0;
    static Clip clip;
    boolean showCave = false;
    HashMap<Rectangle, ActionListener> buttonActions = new HashMap<Rectangle, ActionListener>();
    
    public static void main(String[] args)
    {
    	// Create the GUI window
    	frame = new JFrame("Sample GUI");
    	frame.setSize(new Dimension(800, 500));
    	
    	// Add our stuff to it
    	gui = new GraphicalInterfaceSample();
    	gui.setVisible(true);
    	frame.add(gui);
    	frame.setVisible(true);
    	
    	// Close the app when the window is closed
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	gui.showCave();
            	try {

                    AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/airplane-landing_daniel_simion.wav").toURI().toURL());

                    clip = AudioSystem.getClip();

                    clip.open(ais);

            }catch (IOException | LineUnavailableException | UnsupportedAudioFileException e){

                    System.out.print("Error");
             }
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(0.0f);
            // 0asdasdasdamakes it so it doesn't loop at all, 1 would make it so it ran and then ran again
            clip.loop(0);
            }
            });
        }
	

	public GraphicalInterfaceSample()
	{
		// Lead the cave background image, to be used later in drawing.
		try {
			caveImage = ImageIO.read(new File("res/cave.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Handle button clicks
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
				{
					Point point = e.getPoint();
					int x = point.x;
					int y = point.y;
					for (Rectangle rect : buttonActions.keySet())
					{
						if (x > rect.x && x < rect.x + rect.width && y > rect.y && y < rect.y + rect.height)
						{
							buttonActions.get(rect).actionPerformed(new ActionEvent(e, 0, "Click"));
						}
					}
				}
			}
		});
	}
    
	// Tells paintComponent method that it should draw the cave
    public void showCave()
    {
    	showCave = true;
    	repaint();
    }
    
    // This gets called every time you call repaint()
    public void paintComponent(Graphics g)
    {
    	// We're going to recreate all the buttons, so clear the old ones
    	buttonActions.clear();
    	
    	// Have different if-conditions for things like start menu, high score menu, etc.
    	if (showCave)
    	{
	        paintCave(g);
    	}
    }
    
    public void paintCave(Graphics g)
    {
    	// Draw background image
    	g.drawImage(caveImage, 0, 0, getSize().width, getSize().height, null);
    	
    	// Draw some white text information
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: 103", 10, 20);
        g.drawString("Arrows: 12", 10, 50);
        g.drawString("Room: " + currentRoom, 10, 80);
        
        // Draw a simple button
        drawButton(g, "Move", new Rectangle(100, 100, 50, 50), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Moved");
            	currentRoom++;
            	repaint();
            }
        });
        
        // Draw another simple button
        drawButton(g, "Buy Arrow", new Rectangle(100, 200, 100, 50), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Bought an arrow");
            	repaint();
            }
        });
    }
    	
    private void drawButton(Graphics g, String text, Rectangle rect, ActionListener listener)
    {
    	g.setColor(Color.WHITE);
    	g.fillRect(rect.x, rect.y, rect.width, rect.height);
    	
    	g.setColor(Color.BLACK);
    	drawCenteredString(g, text, rect, new Font("Arial", Font.BOLD, 12));

    	buttonActions.put(rect, listener);
    }
    
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
}