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
	private Player player;
	private Trivia trivia;
	private TextUI textUI;
	private boolean GUIMODE = false;
	private Room currentRoom;
	private Scanner scan;

	public GameControl() {
		
		int randRoom = 4;//(int)(Math.random()*30);
		cave = new Cave("map2.txt");
		gameLocations = new GameLocations(cave, randRoom);
		gui = new GraphicalInterface("GUI");
		highscore = new HighScore();
		player = new Player(gameLocations, "temp_name", cave);
		trivia = new Trivia();
		textUI = new TextUI(5);
		scan = new Scanner(System.in);
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
			textUI.showRoom(currentRoom, surroundingRooms);
		}
	}
	public void takeAction(String input, Room playerRoom) {
		if(input.equals("1")) {
			System.out.println("Which room would you like to move to?");
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
			getGameLocations().movePlayer(Integer.parseInt(input));
			
			System.out.println("The wumpus moves...");
			System.out.println("The wumpus is in room: " + gameControl.getGameLocations().trackWumpus());
			//gameControl.getGameLocations().moveWumpus();
		} else if(input.equals("2")) {
			System.out.println("Which room would you like to shoot an arrow in?");
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