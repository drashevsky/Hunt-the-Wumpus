/**
 * @author Shrey Srivastava
 * Class Name: GameLocations
 * Description: Stores the locations of all the objects
 * Rev. History: (Date - Revision)
 * 
 * 3/05/2019 - Began Working on the project
 * 3/12/2019 - Added dummy methods 
 * 3/19/2019 - Wrote 2 methods that return both the player and the wumpus positions, based on dummy positions when intialized. 
 * 4/03/2019 - [DANIEL] Fixed bugs & formatting per Shrey's request
 */

public class GameLocations {
	private Room player;
	private Room wumpus;
	private Room[] Hazards;
	private Cave c; 
	private int turns;

	
	public GameLocations(Cave c) {
		this.c = c;
		int x = 1;
		turns = 0;
		player = c.getRoom(0);
		wumpus = c.getRoom((int)(Math.random()*30) + 1);
		while (wumpus == player) {
			wumpus = c.getRoom((int)(Math.random()*30) + 1);
		}
		Hazards = new Room[4];
		Hazards[0] = c.getRoom((int)(Math.random()*30) + 1);
		Hazards[1] = c.getRoom((int)(Math.random()*30) + 1);
		Hazards[2] = c.getRoom((int)(Math.random()*30) + 1);
		Hazards[3] = c.getRoom((int)(Math.random()*30) + 1);
		while (x < Hazards.length && Hazards[x] == Hazards[x-1]) {
			Hazards[x] = c.getRoom((int)(Math.random()*30) + 1);
			x++;
		}
	}

	
	// returns the name of the object
	public String toString() {
		return "GameLocations";
	}
	public int numberOfTurns() {
		return turns;
	}
	
	public void movePlayer(Room c)
	{
		player = c;
		turns++;
		
	}
	
	public Room trackWumpus() {
		return wumpus;
	}
	public Room trackPlayer() {
		return player;
	}
}



