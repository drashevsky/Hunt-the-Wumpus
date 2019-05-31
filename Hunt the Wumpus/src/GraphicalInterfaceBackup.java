import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class GraphicalInterfaceBackup extends JPanel
{	
	private static JFrame frame;
    private Image caveImage;
    private int currentRoom = 0;
    
    private boolean showCave = false;
    private boolean showMenu = false;
    private boolean showHighScore = false;
    private boolean showCaveChooser = false;
    
    private HighScore highScore;
    
    private Cave cave;
    
    HashMap<Rectangle, ActionListener> buttonActions = new HashMap<Rectangle, ActionListener>();
    
    private GameControl gameControl;
    
    public void startMainMenu()
    {
    	// Create the GUI window
    	frame = new JFrame("Hunt the Wumpus - Main Menu");
    	frame.setSize(new Dimension(800, 500));
    	
    	// Add our stuff to it
    	setVisible(true);
    	frame.add(this);
    	frame.setVisible(true);
    	
    	// Close the app when the window is closed
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	showMenu();
            }
        });
	}

	public GraphicalInterfaceBackup(GameControl gameControl)
	{		
		this.gameControl = gameControl;
		
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
    	showMenu = false;
    	showHighScore = false;
    	showCaveChooser = false;
    	repaint();
    }
    
    public void showMenu() {
    	showMenu = true;
    	showCave = false;
    	showHighScore = false;
    	showCaveChooser = false;
    	repaint();
    }
    
    public void displayHighScore(String[] players, int[] scores, String[] caves) {    	
    	showHighScore = true;
    	showMenu = false;
    	showCave = false;
    	showCaveChooser = false;
    	repaint();
    }
    
    public void showCaveChooser() {
    	showCaveChooser = true;
    	showCave = false;
    	showMenu = false;
    	showHighScore = false;
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
    	} else if (showMenu) {
    		paintMenu(g);
    	} else if (showHighScore) {
    		paintHighScores(g, highScore.getHighScorePlayers(), highScore.getHighScores(), highScore.getHighScoreCaves());
    	} else if (showCaveChooser) {
    		paintCaveChooser(g);
    	}
    }
    
    public void paintMenu(Graphics g) {
    	
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRect(0, 0, 800, 500);
    	
    	Font buttonFont = new Font("Arial", Font.BOLD, 30);
    	
    	g.setColor(Color.BLACK);
    	
    	drawCenteredString(g, "Hunt the Wumpus", new Rectangle(0, 0, 800, 100), new Font("Arial", Font.BOLD, 60));
    	
    	drawCenteredButton(g, "Play", buttonFont, new Rectangle(400, 150, 200, 50), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	showCaveChooser();
            	repaint();
            }
        });
    	
    	drawCenteredButton(g, "High Scores", buttonFont, new Rectangle(400, 225, 200, 50), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	highScore = gameControl.getHighScore();
            	System.out.println(highScore.toString());
            	displayHighScore(highScore.getHighScorePlayers(), highScore.getHighScores(), highScore.getHighScoreCaves());
            	repaint();
            }
        }); 	
    	
    }
    
    public void paintCaveChooser(Graphics g) {
    	
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRect(0, 0, 800, 500);
    	
    	Font buttonFont = new Font("Arial", Font.BOLD, 30);
    	
    	g.setColor(Color.BLACK);
    	
    	drawCenteredString(g, "Choose a Cave", new Rectangle(0, 0, 800, 100), new Font("Arial", Font.BOLD, 60));
    	
    	drawCenteredButton(g, "Cave 1", buttonFont, new Rectangle(400, 150, 100, 30), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameControl.setCave(new Cave("map1.txt"));
            	showCave();
            	repaint();
            }
        });
    	
    	drawCenteredButton(g, "Cave 2", buttonFont, new Rectangle(400, 190, 100, 30), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameControl.setCave(new Cave("map2.txt"));
            	showCave();
            	repaint();
            }
        });
    	
    	drawCenteredButton(g, "Cave 3", buttonFont, new Rectangle(400, 230, 100, 30), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameControl.setCave(new Cave("map3.txt"));
            	showCave();
            	repaint();
            }
        });
    	
    	drawCenteredButton(g, "Cave 4", buttonFont, new Rectangle(400, 270, 100, 30), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameControl.setCave(new Cave("map4.txt"));
            	showCave();
            	repaint();
            }
        });
    	
    	drawCenteredButton(g, "Cave 5", buttonFont, new Rectangle(400, 310, 100, 30), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameControl.setCave(new Cave("map5.txt"));
            	showCave();
            	repaint();
            }
        });
    	
    }
    
    public void paintHighScores(Graphics g, String[] players, int[] scores, String[] caves) {
    	
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRect(0, 0, 800, 500);
    	
    	Font buttonFont = new Font("Arial", Font.BOLD, 30);
    	
    	g.setColor(Color.BLACK);
    	
    	drawCenteredString(g, "High Scores:", new Rectangle(0, 0, 800, 100), new Font("Arial", Font.BOLD, 60));
    	
    	g.setFont(new Font("Arial", Font.BOLD, 20));
    	
    	g.drawString("Players:", 50, 110);
    	for(int i = 0; i < players.length; i++) {
    		g.drawString(players[i], 50, 140+i*20);
    	}
    	
    	g.drawString("Scores:", 300, 110);
    	for(int i = 0; i < scores.length; i++) {
    		g.drawString("" + scores[i], 300, 140+i*20);
    	}
    	
    	g.drawString("Caves:", 600, 110);
    	for(int i = 0; i < caves.length; i++) {
    		g.drawString(caves[i], 600, 140+i*20);
    	}
    	
    	drawCenteredButton(g, "Back", buttonFont, new Rectangle(400, 400, 200, 50), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	showMenu();
            	repaint();
            }
        });
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
    	
    private void drawButton(Graphics g, String text, Font font, Rectangle rect, ActionListener listener)
    {
    	g.setColor(Color.WHITE);
    	g.fillRect(rect.x, rect.y, rect.width, rect.height);
    	
    	g.setColor(Color.BLACK);
    	drawCenteredString(g, text, rect, font);

    	buttonActions.put(rect, listener);
    }
    
    private void drawButton(Graphics g, String text, Rectangle rect, ActionListener listener)
    {
    	g.setColor(Color.WHITE);
    	g.fillRect(rect.x, rect.y, rect.width, rect.height);
    	
    	g.setColor(Color.BLACK);
    	drawCenteredString(g, text, rect, new Font("Arial", Font.BOLD, 12));
    	
    	buttonActions.put(rect, listener);
    }
    
    private void drawCenteredButton(Graphics g, String text, Font font, Rectangle rect, ActionListener listener) {
    	Rectangle centeredRect = new Rectangle(rect.x-rect.width/2, rect.y, rect.width, rect.height);
    	drawButton(g, text, font, centeredRect, listener);
    }
    
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
}