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
		wumpus = new Wumpus(cave, gameLocations);
		player = new Player(gameLocations, "temp_name", cave, wumpus);
		trivia = new Trivia(gameLocations, this);
		textUI = new TextUI(4); // Creates a textUI with a scale factor
		gui = new GraphicalInterface("GUI");
		guiBackup = new GraphicalInterfaceBackup(this);
	}
	
	public int getRandRoom() { //Accesses a random room;
		return (int)(Math.random()*30);
	}
	
	public static void main(String[] args) {
		// Create an instance of GameControl
		gameControl = new GameControl();
		gameControl.start();
	}
	
//====================================================================
//=== Accessor Land ==================================================
//====================================================================
	
	public void setCave(Cave cave) {
		this.cave = cave;
	}
	
	public void setTrivia(Trivia trivia) {
		this.trivia = trivia;
	}
	
	public void setGameLocations(GameLocations gameLocations) {
		this.gameLocations = gameLocations;
	}
	
	public void setWumpus(Wumpus wumpus) {
		this.wumpus = wumpus;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
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
	
	public GraphicalInterfaceBackup getGraphicalInterfaceBackup () {
		return guiBackup;
	}
	
//====================================================================
//=== Method Land ====================================================
//====================================================================
	public void start() // Starts the game based off either TextUI or GUI
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
	}
	
	public String getMap() { //Gets a map for the TextUI
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
	
	public void gameOver(boolean isOver) { //Checks the end game state whether a win or loss
		if(isOver && player.wumpusState())
			System.out.println("You've killed the Wumpus! You win the game.");
		else if(isOver && !player.wumpusState())
			System.out.println("You are dead! Game over.");
		gameOver = isOver;
	}
	
	public void move(int room, Room playerRoom) { //GameControl method to move player
		gameLocations.movePlayer(room);
	}
	
	//An input detector for the player to choose in the TextUI
	public void takeAction(String input, Room playerRoom) {
		//Typing "1" will load a prompt for you to move rooms
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
		//Typing "2" will shoot an arrow in a specified room
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
			
			// Check to see if room number is valid first before shooting
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
		//Typing "3" will load a prompt for trivia and answering trivia will give you
		//arrows and decrease your current coins
		} else if (input.equals("3")) {			
			if(trivia.startTrivia(2, 3)) {
				player.purchaseArrows();
				System.out.println("You bought 2 arrows");
			} else {
				System.out.println("You failed to purchase arrows");
			}
		//Typing "4" loads trivia prompts and will give you answers to trivia questions
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