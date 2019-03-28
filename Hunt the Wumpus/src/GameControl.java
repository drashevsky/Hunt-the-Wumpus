import java.io.FileNotFoundException;

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
	private Cave cave;
	private GameLocations gameLocations;
	private GraphicalInterface gui;
	private HighScore highscore;
	private Player player;
	private Trivia trivia;
	public GameControl() {
		try {
			cave = new Cave("map.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gameLocations = new GameLocations(cave);
		gui = new GraphicalInterface();
		highscore = new HighScore();
		player = new Player();
		trivia = new Trivia();
	}
	public static void main(String[] args) {
		// Create an instance of GameControl
		GameControl gameControl = new GameControl();
		
		
		
		
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
