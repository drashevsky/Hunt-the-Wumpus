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
	private Cave cave;
	private GameLocations gameLocations;
	private GraphicalInterface gui;
	private HighScore highscore;
	private Wumpus wumpus;
	private Player player;
	private Trivia trivia;
	private TextUI textUI;
	private boolean GUIMODE = false;
	private Room currentRoom;
	private Scanner scan;

	public GameControl() {
		scan = new Scanner(System.in);
		int randRoom = (int)(Math.random()*30);
		cave = new Cave(getMap());
		gameLocations = new GameLocations(cave, randRoom);
		gui = new GraphicalInterface("GUI");
		highscore = new HighScore();
		wumpus = new Wumpus(cave, gameLocations);
		player = new Player(gameLocations, "temp_name", cave, wumpus);
		trivia = new Trivia();
		textUI = new TextUI(5);
	}
	
	public static void main(String[] args) {
		// Create an instance of GameControl
		gameControl = new GameControl();
		gameControl.start();
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
	
	public void start()
	{
		textUI.showMainMenu();
		textUI.runEvents(gameControl);
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
		System.out.println("Type a number between 1-5 for a map.");
		String input = scan.nextLine();
		for(int x = 1; x <= 5; x++) {
			if(input.equals("" + x)) {
				return "map" + input + ".txt";
			}
		}
		return "";
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
			getGameLocations().movePlayer(Integer.parseInt(input));
			
			System.out.println("The wumpus moves...");
			gameControl.getGameLocations().moveWumpus();
			System.out.println("---[The wumpus is in room: " + gameControl.getGameLocations().trackWumpus() + "]---");
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
		} else if (input.equals("3")) {			
			if(trivia.startTrivia(2, 3)) {
				player.purchaseArrows();
				System.out.println("You bought 2 arrows");
			} else {
				System.out.println("You failed to purchase arrows");
			}
		}
	}
}