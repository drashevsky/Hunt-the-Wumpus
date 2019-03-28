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
	private Room[][] map;
	private int player;
	private int wumpus;
	private Room[] Hazards = new Room[4];
public GameLocations(Cave c) {
	map = Cave.fullMap();
	Hazards[0] = new Room(3, 4, 5);
	Hazards[1] = new Room(7, 8, 9);
	Hazards[2] = new Room(12, 13, 14);
	Hazards[3] = new Room(21, 22, 23);
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
			if (x + y == wumpus) {
				return x+y;
				//s
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
			if (x + y == player) {
				return x+y;
			}
		}
	}
	return 0;
}
}
