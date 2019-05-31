import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * 
 * @author Eric Anderson
 * Date: 3/13/19
 * Version 1.1
 * Rev. History: 
 * 	V1.0
 * 		- Added main method
 * 		- Created instantiations of each object
 *	V1.01 - 3/13/19
 *		- Created stubbed classes
 *		- Added comments
 *	V1.02 - 3/20/19
 *		- Changed constructor info for cave object
 *	V.03 - 4/4/19
 *		- Cleaned up class constructors
 *		- Declared static instance of GameControl
 *		- Deleted unnecessary methods
 */
public class GameControl{
	public static GameControl gameControl;
	public final boolean GUIMODE = true;
	public final boolean SAMPLEGUIMODE = true;
	private boolean gameOver = false;
	private Cave cave;
	private GameLocations gameLocations;
	private GraphicalInterface gui;
	private GraphicalInterfaceBackup guiBackup;
	private HighScore highScore;
	private Wumpus wumpus;
	private Player player;
	private Trivia trivia;
	private TextUI textUI;
	private Room currentRoom;
	private Scanner scan;

	public GameControl() {
		scan = new Scanner(System.in);
		highScore = new HighScore();
		int randRoom = (int)(Math.random()*30);
		cave = new Cave(getMap());
		gameLocations = new GameLocations(cave, randRoom);
		gui = new GraphicalInterface("GUI");
		guiBackup = new GraphicalInterfaceBackup(this);
		wumpus = new Wumpus(cave, gameLocations);
		player = new Player(gameLocations, "temp_name", cave, wumpus);
		trivia = new Trivia(gameLocations);
		textUI = new TextUI(4);
	}
	
	public static void main(String[] args) {
		// Create an instance of GameControl
		gameControl = new GameControl();
		gameControl.start();
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public Scanner getScanner() {
		return scan;
	}
	
	public GameLocations getGameLocations() {
		return gameLocations;
	}
	
	public Cave getCave() {
		return cave;
	}
	
	public Trivia getTrivia() {
		return trivia;
	}
	
	public Wumpus getWumpus() {
		return wumpus;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public HighScore getHighScore() {
		return highScore;
	}
	
	public void start()
	{

		if(!GUIMODE) {
			textUI.showMainMenu();
			textUI.runEvents(gameControl);
		} else {
			if(!SAMPLEGUIMODE) {
				gui.showMainMenu();
			} else {
				guiBackup.startMainMenu();
			}
		}
	}
	public void newGameButtonClicked() {
		// Reset the cave
		// Reset game locations...
		int currentRoom = gameLocations.trackPlayer();
		Room surroundingRooms = cave.getRoom(currentRoom);
		
		if (GUIMODE){
			//gui.showRoom(firstRoom);
		} else {
			//textUI.showRoom(currentRoom, surroundingRooms);
		}
	}
	
	public String getMap() {
		if(!GUIMODE) {
			System.out.println("Type a number between 1-5 for a map.");
			String input = scan.nextLine();
			for(int x = 1; x <= 5; x++) {
				if(input.equals("" + x)) {
					return "map" + input + ".txt";
				}
			}
		} else {
			return "map1.txt";
		}
		return "";
	}
	
	public void gameOver(boolean isOver) {
		if(isOver && player.wumpusState())
			System.out.println("You've killed the Wumpus! You win the game.");
		else if(isOver && !player.wumpusState())
			System.out.println("You are dead! Game over.");
		gameOver = isOver;
	}
	
	public void takeAction(String input, Room playerRoom) {
		if(input.equals("1")) {
			System.out.println("Which room would you like to move to?");
			// Print out room numbers:
			for(int x = 0; x < playerRoom.getConnectedRooms().length; x++) {
				if(playerRoom.getConnectedRooms()[x] != 0) {
					System.out.print("[" + playerRoom.getConnectedRooms()[x] + "] ");
				}
			}
			// Check to see if room number is valid first before moving player
			// Change to a while loop
			boolean validRoomNumber = false;
			int x;
			while(!validRoomNumber) {
				x = 0;
				input = scan.nextLine();
				while(x < playerRoom.getConnectedRooms().length && !validRoomNumber){
					if(input.equals("" + playerRoom.getConnectedRooms()[x]) && !input.equals("0")) {
						validRoomNumber = true;
					}
					x++;
				}
				if(!validRoomNumber) {
					System.out.println("Invalid room number, type in another room: ");
				}
			}
			gameLocations.movePlayer(Integer.parseInt(input));
			
			System.out.println("The wumpus moves... [NOT REALLY]");
			//gameControl.getGameLocations().moveWumpus();
			System.out.println("---[The wumpus is in room: " + gameControl.getGameLocations().trackWumpus() + "]---");
			//textUI.showRoom(gameLocations.trackPlayer(), cave.getRoom(gameLocations.trackPlayer()));
		} else if(input.equals("2")) {
			System.out.println("Which room would you like to shoot an arrow in?");
			System.out.println("You have " + player.getArrows() + " arrows left.");
			// Print out room numbers:
			for(int x = 0; x < playerRoom.getConnectedRooms().length; x++) {
				if(playerRoom.getConnectedRooms()[x] != 0) {
					System.out.print("[" + playerRoom.getConnectedRooms()[x] + "] ");
				}
				System.out.println();
			}
			
			// Check to see if room number is valid first before moving player
			// Change to a while loop
			boolean validRoomNumber = false;
			int x;
			while(!validRoomNumber) {
				x = 0;
				input = scan.nextLine();
				while(x < playerRoom.getConnectedRooms().length && !validRoomNumber){
					if(input.equals("" + playerRoom.getConnectedRooms()[x]) && !input.equals("0")) {
						validRoomNumber = true;
					}
					x++;
				}
				if(!validRoomNumber) {
					System.out.println("Invalid room number, type in another room: ");
				}
			}
			player.shootArrow(Integer.parseInt(input));
			System.out.println("You shot an arrow!");
			if(player.wumpusState()) {
				gameOver(true);
			}
		} else if (input.equals("3")) {			
			if(trivia.startTrivia(2, 3)) {
				player.purchaseArrows();
				System.out.println("You bought 2 arrows");
			} else {
				System.out.println("You failed to purchase arrows");
			}
		} else if (input.equals("4")) {
			if(trivia.startTrivia(2, 3)) {
				System.out.println("You obtained a secret!");
				trivia.displaySecret();
			} else {
				System.out.println("You didn't get a secret");
			}
		} else {
			System.out.println("The input is invalid");
		}
	}
}