/**
 * 
 * @author Eric Anderson
 * Date: 3/13/19
 * Version 1.1
 * Comments: 
 * 	V1.0
 * 		- Added main method
 * 		- Created instantiations of each object
 *	V1.1
 *		- Created stubbed classes
 *		- Added comments
 */
public class GameControl {
	public static void main(String[] args) {
		// Create an instance of each Object
		Cave cave = new Cave();
		GameLocations gameLocations = new GameLocations();
		GraphicalInterface gui = new GraphicalInterface();
		HighScore highscore = new HighScore();
		Player player = new Player();
		Trivia trivia = new Trivia();
	}
	
	// Determines the keystrokes of the player and validates the input
	// Returns false if invalid
	public static boolean userInput(Player player) {
		return true;
	}
	
	// Determines the game location
	public static boolean gameState(GameLocations gameLocations) {
		return true;
	}
	
	// Manages the value of the high score
	public static boolean highScoreManagement(HighScore highScore) {
		return true;
	}
	
	// Logic updater
	// (May change this later if there is another way)
	public static boolean update(float dt) {
		return true;
	}
}
