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
}
