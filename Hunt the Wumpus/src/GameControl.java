import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public GameControl() {
		
		int randRoom = 4;//(int)(Math.random()*30);
		cave = new Cave("map2.txt");
		gameLocations = new GameLocations(cave, randRoom);
		gui = new GraphicalInterface("GUI");
		highscore = new HighScore();
		player = new Player(gameLocations, "temp_name", cave);
		trivia = new Trivia();
		textUI = new TextUI(5);
	}
	
	public static void main(String[] args) {
		// Create an instance of GameControl
		gameControl = new GameControl();
		gameControl.start();
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
	public void takeAction(int input) {
		
	}
}
