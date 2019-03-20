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
 */
public class GameControl {
	public static void main(String[] args) {
		// Create an instance of each Object
		Cave cave = new Cave(new String[5][6]);
		GameLocations gameLocations = new GameLocations();
		GraphicalInterface gui = new GraphicalInterface();
		HighScore highscore = new HighScore();
		Player player = new Player();
		Trivia trivia = new Trivia();
		
		Object[] objects = new Object[] {cave, gameLocations, gui, highscore, player, trivia};
		for(Object o : objects) {
			System.out.println(o);
		}
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
