import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GraphicalInterfaceBackup extends JPanel
{	
	private static JFrame frame;
    private Image caveImage;
    private Image batImage;
    private Image pitImage;
    private Image wumpusImage;
    
    private boolean showCave = false;
    private boolean showMenu = false;
    private boolean showHighScore = false;
    private boolean showCaveChooser = false;
    private boolean showTrivia = false;
    private boolean showNameChooser = false;
    private boolean showTriviaOutcome = false;
    
    private boolean showingHazard = false;
    private boolean showingWumpus = false;
    
    private boolean hitWumpus = false;
    
    private String playerName;
    
    private static Clip gongClip;
    
    private HighScore highScore;
    
    private boolean gameOver = false;
    
    HashMap<Rectangle, ActionListener> buttonActions = new HashMap<Rectangle, ActionListener>();
    
    private GameControl gameControl;
    
    private String currentTip = "";
    
    private String caveName;
    
    /*
     * 1 = purchasing arrows
     * 2 = purchasing secrets
     * 3 = bottomless pit
     * 4 = escaping wumpus
     */
    private int queuedAction = -1;
    
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
        
        // ok just make sure you declare static Clip clip near ur other private instance variables
        //but yeah all u have to change is the file name and it should work
         /*try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/gong.wav").toURI().toURL());
                gongClip = AudioSystem.getClip();
                gongClip.open(ais);
        }catch (IOException | LineUnavailableException | UnsupportedAudioFileException e){
                System.out.print("Error");
        }
        FloatControl gainControl = (FloatControl) gongClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(0.0f);
        // 0     asdasdasdamakes it so it doesn't loop at all, 1 would make it so it ran and then ran again
        gongClip.loop(0);;
        */
	}

	public GraphicalInterfaceBackup(GameControl gameControl)
	{		
		this.gameControl = gameControl;
		
		// Lead the cave background image, to be used later in drawing.
		try {
			caveImage = ImageIO.read(new File("res/cave.jpg"));
			batImage = ImageIO.read(new File("res/bats.png"));
			pitImage = ImageIO.read(new File("res/pit.png"));
			wumpusImage = ImageIO.read(new File("res/wumpus.png"));
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
    
	public void showNameChooser() {
		showNameChooser = true;
		showCave = false;
		showMenu = false;
		showHighScore = false;
		showCaveChooser = false;
		showTrivia = false;
		showTriviaOutcome = false;
		playerName = "";
	}
	
	public void showTrivia() {
		showTrivia = true;
		showCave = false;
		showMenu = false;
		showHighScore = false;
		showCaveChooser = false;
		showNameChooser = false;
		showTriviaOutcome = false;
	}
	
	// Tells paintComponent method that it should draw the cave
    public void showCave()
    {
    	showCave = true;
    	showMenu = false;
    	showHighScore = false;
    	showCaveChooser = false;
    	showTrivia = false;
    	showNameChooser = false;
    	showTriviaOutcome = false;
    	repaint();
    }
    
    public void showMenu() {
    	showMenu = true;
    	showCave = false;
    	showHighScore = false;
    	showCaveChooser = false;
    	showTrivia = false;
    	showNameChooser = false;
    	showTriviaOutcome = false;
    	repaint();
    }
    
    public void displayHighScore(String[] players, int[] scores, String[] caves) {    	
    	showHighScore = true;
    	showMenu = false;
    	showCave = false;
    	showCaveChooser = false;
    	showTrivia = false;
    	showNameChooser = false;
    	showTriviaOutcome = false;
    	repaint();
    }
    
    public void showCaveChooser() {
    	showCaveChooser = true;
    	showCave = false;
    	showMenu = false;
    	showHighScore = false;
    	showTrivia = false;
    	showNameChooser = false;
    	showTriviaOutcome = false;
    	repaint();
    }
    
    public void showTriviaOutcome() {
    	showTriviaOutcome = true;
    	showCaveChooser = false;
    	showCave = false;
    	showMenu = false;
    	showHighScore = false;
    	showTrivia = false;
    	showNameChooser = false;
    	repaint();
    }
    
    public void startTrivia(int correctAnswersToPass, int questionsTotal, int action) {
    	queuedAction = action;
    	gameControl.getTrivia().startGUITrivia(correctAnswersToPass, questionsTotal);
    	gameControl.getTrivia().nextQuestion();
    	showTrivia();
    }
    
    public void gameOver() {
    	
    	System.out.println(gameControl.getGameLocations().getScore());
    	System.out.println(playerName);
    	System.out.println(caveName);
    	
    	gameControl.getHighScore().addScore(gameControl.getGameLocations().getScore(), 
    			playerName, caveName);
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
    	} else if (showTrivia) {
    		paintTrivia(g);
    	} else if (showNameChooser) {
    		paintNameChooser(g);
    	} else if (showTriviaOutcome) {
    		paintTriviaOutcome(g);
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
    
    public void paintTrivia(Graphics g) {
    	
    	if(!gameControl.getTrivia().isActive()) {
    		showTriviaOutcome();
    		repaint();
    	}
    	
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRect(0, 0, 800, 500);
    	
    	Font buttonFont = new Font("Arial", Font.BOLD, 30);
    	
    	g.setColor(Color.BLACK);
    	
    	drawCenteredString(g, gameControl.getTrivia().getCurrentTrivia()[0], new Rectangle(0, 0, 800, 100), new Font("Arial", Font.BOLD, 30));
    	
    	drawCenteredButton(g, gameControl.getTrivia().getCurrentTrivia()[1], buttonFont, new Rectangle(400, 150, 600, 50), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameControl.getTrivia().answerQuestion(0);
            	repaint();
            }
        });
    	
    	drawCenteredButton(g, gameControl.getTrivia().getCurrentTrivia()[2], buttonFont, new Rectangle(400, 225, 600, 50), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameControl.getTrivia().answerQuestion(1);
            	repaint();
            }
        }); 	
    	
    	drawCenteredButton(g, gameControl.getTrivia().getCurrentTrivia()[3], buttonFont, new Rectangle(400, 300, 600, 50), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameControl.getTrivia().answerQuestion(2);
            	repaint();
            }
        }); 	
    	
    }
    
    public void paintTriviaOutcome(Graphics g) {
    	
    	String outcome = "";
    	
    	if(gameControl.getGameLocations().getPlayer().getGoldCoins() < 0) {
    		outcome = "You ran out of gold coins and died";
    		
    	} else if(queuedAction == 1) {
    		
    		if(gameControl.getTrivia().passedPreviousTrivia()) {	
    			outcome = "You purchased two more arrows";
    		} else {
    			outcome = "You failed to purchase arrows";
    		}    		
    	} else if(queuedAction == 2) {
    		
    		if(gameControl.getTrivia().passedPreviousTrivia()) {
    			outcome = "You purchased a secret";
    		} else {
    			outcome = "You failed to purchase a secret";
    		}
    		
    	} else if(queuedAction == 3) {
    		
    		if(gameControl.getTrivia().passedPreviousTrivia()) {
    			outcome = "You climbed out of the pit to your start location";
    		} else {
    			outcome = "You fell into the pit";
    		}
    		
    	} else if(queuedAction == 4) {
    		
    		if(gameControl.getTrivia().passedPreviousTrivia()) {
    			outcome = "The Wumpus ran away";
    		} else {
    			outcome = "You have been eaten by the Wumpus";
    		}
    		
    	}
    	
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRect(0, 0, 800, 500);
    	
    	Font buttonFont = new Font("Arial", Font.BOLD, 30);
    	
    	g.setColor(Color.BLACK);
    	
    	drawCenteredString(g, outcome, new Rectangle(0, 0, 800, 100), new Font("Arial", Font.BOLD, 30));
    	
    	drawCenteredButton(g, "Continue", buttonFont, new Rectangle(400, 225, 200, 50), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	if(gameControl.getGameLocations().getPlayer().getGoldCoins() < 0) {
            		
            		gameControl.getGameLocations().getPlayer().setCoins(0);
            		gameOver();
            		showCave = false;
                	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            		
            	} else if(queuedAction == 1) {
            		
            		if(gameControl.getTrivia().passedPreviousTrivia()) {	
            			gameControl.getGameLocations().getPlayer().purchaseArrows();
            		}
            		
            	} else if(queuedAction == 2) {
            		
            		if(gameControl.getTrivia().passedPreviousTrivia()) {
            			currentTip = "Secret: " + gameControl.getTrivia().getSecret();
            			gameControl.getGameLocations().getPlayer().purchaseSecrets();
            		}
            		
            	} else if(queuedAction == 3) {
            		
            		if(gameControl.getTrivia().passedPreviousTrivia()) {
            			gameControl.getGameLocations().handleHazard();
            		} else {
            			gameOver();
            			showCave = false;
                    	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            		}
            		
            	} else if(queuedAction == 4) {
            		
            		if(gameControl.getTrivia().passedPreviousTrivia()) {
            			gameControl.getGameLocations().getWumpus().move();
            			gameControl.getGameLocations().getWumpus().move();
            			gameControl.getGameLocations().getWumpus().move();
            		} else {
            			gameOver();
            			showCave = false;
                    	frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            		}
            		
            	}
            	
            	showCave();
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
            	gameControl.getGameLocations().setHazardTypes(gameControl.getCave());
            	caveName = "Cave 1";
            	showNameChooser();
            	repaint();
            }
        });
    	
    	drawCenteredButton(g, "Cave 2", buttonFont, new Rectangle(400, 190, 100, 30), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameControl.getCave().loadMap("map2.txt");
            	gameControl.getGameLocations().setHazardTypes(gameControl.getCave());
            	caveName = "Cave 2";
            	showNameChooser();
            	repaint();
            }
        });
    	
    	drawCenteredButton(g, "Cave 3", buttonFont, new Rectangle(400, 230, 100, 30), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameControl.setCave(new Cave("map3.txt"));
            	gameControl.getGameLocations().setHazardTypes(gameControl.getCave());
            	caveName = "Cave 3";
            	showNameChooser();
            	repaint();
            }
        });
    	
    	drawCenteredButton(g, "Cave 4", buttonFont, new Rectangle(400, 270, 100, 30), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameControl.setCave(new Cave("map4.txt"));
            	gameControl.getGameLocations().setHazardTypes(gameControl.getCave());
            	caveName = "Cave 4";
            	showNameChooser();
            	repaint();
            }
        });
    	
    	drawCenteredButton(g, "Cave 5", buttonFont, new Rectangle(400, 310, 100, 30), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameControl.setCave(new Cave("map5.txt"));
            	gameControl.getGameLocations().setHazardTypes(gameControl.getCave());
            	caveName = "Cave 5";
            	showNameChooser();
            	repaint();
            }
        });
    	
    	
    	drawCenteredButton(g, "Random Generator", buttonFont, new Rectangle(400, 350, 280, 30), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	gameControl.setCave(new Cave(""));
            	gameControl.getGameLocations().setHazardTypes(gameControl.getCave());
            	caveName = "Random Cave";
            	showNameChooser();
            	repaint();
            }
        });
    }
    
public void paintNameChooser(Graphics g) {
    	
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRect(0, 0, 800, 500);
    	
    	Font buttonFont = new Font("Arial", Font.BOLD, 30);
    	
    	g.setColor(Color.BLACK);
    	
    	drawCenteredString(g, "Type your Name", new Rectangle(0, 0, 800, 100), new Font("Arial", Font.BOLD, 60));
    	
    	drawCenteredString(g, playerName, new Rectangle(0, 70, 800, 100), new Font("Arial", Font.PLAIN, 40));
    	
    	char[][] characters = {
    			{'A', 'B', 'C', 'D', 'E', 'F', 'G'},
    			{'H', 'I', 'J', 'K', 'L', 'M', 'N'},
    			{'O', 'P', 'Q', 'R', 'S', 'T', 'U'},
    			{'U', 'V', 'W', 'X', 'Y', 'Z'}
    	};
    	
    	for(int r = 0; r < characters.length; r++) {
    		for(int c = 0; c < characters[r].length; c++) {
    			char ch = characters[r][c];
		    	drawCenteredButton(g, "" + ch, buttonFont, new Rectangle(220 + c*60, 200 + r*40, 30, 30), new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	playerName += "" + ch;
		            	System.out.println(playerName);
		            	repaint();
		            }
		        });
    		}	    	
    	}
    	
    	drawCenteredButton(g, "Backspace", buttonFont, new Rectangle(400, 160, 160, 30), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(playerName.length() > 0) {
            		playerName = playerName.substring(0, playerName.length()-1);
            	}
            	repaint();
            }
        });
    	
    	drawCenteredButton(g, "Start", buttonFont, new Rectangle(400, 380, 160, 30), new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(playerName.length() > 0) {
	            	gameControl.getPlayer().setName(playerName);
	            	showCave();
            	}
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
    	
    	boolean foundWumpus = gameControl.getGameLocations().trackWumpus() == gameControl.getGameLocations().trackPlayer();
    	
    	if(showingHazard) {
    		showingHazard = false;
    		try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(gameControl.getCave().getRoom(gameControl.getGameLocations().trackPlayer()).getHazard() == 2) {
	        	gameControl.getGameLocations().handleHazard();
    		} else if(gameControl.getCave().getRoom(gameControl.getGameLocations().trackPlayer()).getHazard() == 1){
    			startTrivia(2, 3, 3);
    		}
        	repaint();
    	}
    	
    	if(showingWumpus) {
    		showingWumpus = false;
    		try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(gameControl.getGameLocations().trackWumpus() == gameControl.getGameLocations().trackPlayer()) {
    			startTrivia(3, 5, 4);
    		}
        	repaint();
    	}
    	
    	int playerRoomValue = gameControl.getGameLocations().trackPlayer();
    	Room playerRoom = gameControl.getCave().getRoom(playerRoomValue);
    	int[] connectedRooms = playerRoom.getConnectedRooms();
    	
    	// Draw background image
    	//g.drawImage(caveImage, 0, 0, getSize().width, getSize().height, null);
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRect(0, 0, 800, 500);
    	
    	g.setColor(Color.WHITE);
    	g.fillRect(300, 150, 200, 200);
    	
    	//Display Bats
    	if(playerRoom.getHazard() == 2) {
    		g.drawImage(batImage, 300, 150, 200, 200, null);
    	}
    	
    	if(playerRoom.getHazard() == 1) {
    		g.drawImage(pitImage, 300, 150, 200, 200, null);
    	}
    	
    	if(foundWumpus) {
    		g.drawImage(wumpusImage, 350, 200, 100, 100, null);
    	}
    	
    	if(foundWumpus || playerRoom.getHazard() != 0) {
    		SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                	
                try {
                        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/hazard.wav").toURI().toURL());
                        gongClip = AudioSystem.getClip();
                        gongClip.open(ais);
                }catch (IOException | LineUnavailableException | UnsupportedAudioFileException e){
                        System.out.print("Error");
                }
                FloatControl gainControl = (FloatControl) gongClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(0.0f);
                // 0     asdasdasdamakes it so it doesn't loop at all, 1 would make it so it ran and then ran again
                gongClip.loop(0);;
                repaint();
                }
            });
    	}
    	
    	// Draw some white text information
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + gameControl.getPlayer().computeScore(), 10, 20);
        g.drawString("Arrows: " + gameControl.getGameLocations().getPlayer().getArrows(), 10, 50);
        g.drawString("Room: " + playerRoomValue, 10, 80);
        g.drawString("Coins: " + gameControl.getGameLocations().getPlayer().getGoldCoins(), 10, 110);
        
        if(gameControl.getGameLocations().getPlayer().killedWumpus) {
        	g.drawString("The Wumpus has been vanquished!", 10, 410);
        	repaint();
        }
        else if(playerRoom.getHazard() == 0 && !showingHazard && !foundWumpus) {
        	g.drawString("Near Hazard Check: " + gameControl.getGameLocations().nearHazard(playerRoom), 10, 410);
        } else if(playerRoom.getHazard() == 1 && !showingHazard){
        	g.drawString("You have stumbled upon a bottomless pit...", 10, 410);
        	showingHazard = true;
        	repaint();
        	repaint();
        } else if (playerRoom.getHazard() == 2 && !showingHazard){
        	g.drawString("You have encountered Super Bats! Moving to a random room...", 10, 410);
        	showingHazard = true;
        	repaint();
        } else if(playerRoom.getHazard() == 0 && !showingHazard && foundWumpus) {
        			g.drawString("You found the Wumpus...", 10, 410);
        			showingWumpus = true;
        			repaint();
        }
        	
        g.drawString(currentTip, 10, 440);
        
        System.out.println(gameControl.getGameLocations().nearHazard(playerRoom));
        System.out.println(playerRoom.getHazard());
        for (int i : gameControl.getGameLocations().getHazards()) {
        	System.out.print(" " + i + " ");
        }
        
        //Room 1
        if(playerRoom.getHazard() == 0 && !foundWumpus) {
	        drawButton(g, "" + connectedRooms[0], new Rectangle(275, 225, 20, 20), new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	gameControl.getGameLocations().movePlayer(connectedRooms[0]);
	            	gameControl.getPlayer().incrementGoldCoins(1);
	            	currentTip = "Hint: " + gameControl.getTrivia().getHint();
	            	gameControl.getGameLocations().incrementTurns(1);
	            	repaint();
	            }
	        });
        }
        
        //Room 2
        if(connectedRooms.length >= 1 && connectedRooms[1] != 0 && playerRoom.getHazard() == 0 && !foundWumpus) {
            drawButton(g, "" + connectedRooms[1], new Rectangle(400, 125, 20, 20), new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	gameControl.getGameLocations().movePlayer(connectedRooms[1]);
                	gameControl.getPlayer().incrementGoldCoins(1);
                	currentTip = "Hint: " + gameControl.getTrivia().getHint();
                	gameControl.getGameLocations().incrementTurns(1);
                	g.drawString("The Wumpus has been vanquished!", 10, 450);
                	repaint();
                }
            });
        }
        
        //Room 3
        if(connectedRooms.length >= 2 && connectedRooms[2] != 0 && playerRoom.getHazard() == 0 && !foundWumpus) {
            drawButton(g, "" + connectedRooms[2], new Rectangle(525, 225, 20, 20), new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	gameControl.getGameLocations().movePlayer(connectedRooms[2]);
                	gameControl.getPlayer().incrementGoldCoins(1);
                	currentTip = "Hint: " + gameControl.getTrivia().getHint();
                	gameControl.getGameLocations().incrementTurns(1);
                	g.drawString("The Wumpus has been vanquished!", 10, 450);
                	repaint();
                }
            });
        }
        
        if(gameControl.getGameLocations().getPlayer().getArrows() > 0 && playerRoom.getHazard() == 0) {
        
        	g.drawString("Shoot Arrow", 640, 220);
        	
	        //Room 1 Shoot
	        if(playerRoom.getHazard() == 0 && !foundWumpus) {
		        drawButton(g, "" + connectedRooms[0], new Rectangle(640, 250, 20, 20), new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	hitWumpus = gameControl.getGameLocations().getPlayer().shootArrow(connectedRooms[0]);
		            	gameControl.getGameLocations().incrementTurns(1);
		            	
		            	if(hitWumpus) {
		            		gameControl.getGameLocations().getPlayer().killedWumpus = true;
		            		showingWumpus = true;
		            	}
		            	
		            	repaint();
		            }
		        });
	        }
	        
	        //Room 2 Shoot
	        if(connectedRooms.length >= 1 && connectedRooms[1] != 0 && playerRoom.getHazard() == 0 && !foundWumpus) {
	            drawButton(g, "" + connectedRooms[1], new Rectangle(670, 250, 20, 20), new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                	hitWumpus = gameControl.getGameLocations().getPlayer().shootArrow(connectedRooms[1]);
		            	gameControl.getGameLocations().incrementTurns(1);
		            	
		            	if(hitWumpus) {
		            		gameControl.getGameLocations().getPlayer().killedWumpus = true;
		            		showingWumpus = true;
		            	}
		            	
		            	repaint();
	                }
	            });
	        }
	        
	        //Room 3 Shoot
	        if(connectedRooms.length >= 2 && connectedRooms[2] != 0 && playerRoom.getHazard() == 0 && !foundWumpus) {
	            drawButton(g, "" + connectedRooms[2], new Rectangle(700, 250, 20, 20), new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                	hitWumpus = gameControl.getGameLocations().getPlayer().shootArrow(connectedRooms[2]);
		            	gameControl.getGameLocations().incrementTurns(1);
		            	
		            	if(hitWumpus) {
		            		gameControl.getGameLocations().getPlayer().killedWumpus = true;
		            		showingWumpus = true;
		            	}
		            	
		            	repaint();
	                }
	            });
	        }
        
        }

        
        // Draw another simple button
        if(!foundWumpus) {
	        drawButton(g, "Buy Arrow", new Rectangle(50, 200, 100, 50), new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	startTrivia(2, 3, 1);
	            	repaint();
	            }
	        });
        }
        
        if(!foundWumpus) {
        	drawButton(g, "Buy Secret", new Rectangle(50, 300, 100, 50), new ActionListener() {
        		public void actionPerformed(ActionEvent e) {
        			startTrivia(2, 3, 2);
        			gameControl.getGameLocations().incrementTurns(1);
	            	repaint();
	            }
	        });
        }
        
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