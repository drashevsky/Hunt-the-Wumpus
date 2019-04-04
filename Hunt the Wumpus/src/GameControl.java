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
 */
public class GameControl {
	public static GameControl gameControl;
	private Cave cave;
	private GameLocations gameLocations;
	private GraphicalInterface gui;
	private HighScore highscore;
	private Player player;
	private Trivia trivia;
	public GameControl() {
		cave = new Cave("map.txt");
		gameLocations = new GameLocations(cave);
		gui = new GraphicalInterface("GUI");
		highscore = new HighScore();
		player = new Player(gameLocations, "temp_name");
		trivia = new Trivia();
	}
	public static void main(String[] args) {
		// Create an instance of GameControl
		gameControl = new GameControl();
	}
	
	// Determines the keystrokes of the player and validates the input
	// Returns false if invalid
	public boolean userInput(Player player) {
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
