/**
 * @author Shrey Srivastava
 * Class Name: GameLocations
 * Description: Stores the locations of all the objects
 * Rev. History: (Date - Revision)
 * 
 * 3/05/2019 - Began Working on the project
 * 3/12/2019 - Added dummy methods 
 * 3/19/2019 - Wrote 2 methods that return both the player and the wumpus positions, based on dummy positions when intialized. 
 */


public class GameLocations {
	private int[][] map;
	private int player;
	private int wumpus;
public GameLocations() {
	map = new int[5][6];
	int player = (int)(Math.random()*30) + 1;
	int wumpus = (int)(Math.random()*30) + 1;
	while (wumpus == player) {
		wumpus = (int)(Math.random()*30) + 1;
	}
}
// returns the name of the object
public String toString() {
	return "GameLocations";
}
// returns the integer position of the wumpus, needs cave for map
public int trackWumpus() {
	for (int x = 0; x < map.length; x++)
	{
		for (int y = 0; y < map[x].length; y++)
		{
			if (x+y == wumpus) {
				return x+y;
			}
		}
	}
	return 0;
}
//returns the integer position of the player, needs cave for map
public int trackPlayer() {
	for (int x = 0; x < map.length; x++)
	{
		for (int y = 0; y < map[x].length; y++)
		{
			if (x+y == player) {
				return x+y;
			}
		}
	}
	return 0;
}
}
