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
 * 5/20/2019 - [DANIEL] Added a way for GameControl to set where player starts
 */

public class GameLocations {
	private int player;
	private int wumpus;
	private int[] Hazards;
	private Cave c; 
	private int turns;

	
	public GameLocations(Cave c, int playerLocation) {
		this.c = c;
		int x = 1;
		turns = 0;
		player = playerLocation;
		wumpus = (int)(Math.random()*30) + 1;
		while (wumpus == player) {
			wumpus = (int)(Math.random()*30) + 1;
		}
		Hazards = new int[4];
		Hazards[0] = (int)(Math.random()*30) + 1;
		Hazards[1] = (int)(Math.random()*30) + 1;
		Hazards[2] = (int)(Math.random()*30) + 1;
		Hazards[3] = (int)(Math.random()*30) + 1;
		while (x < Hazards.length && Hazards[x] == Hazards[x-1]) {
			Hazards[x] = (int)(Math.random()*30) + 1;
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
	public String nearHazard(Room x) {
		for(int a = 0; a < x.getConnectedRooms().length; a++)
		{
			if (x.getConnectedRooms()[a] == 1) {
				return "I feel a draft";
			}
			else if (x.getConnectedRooms()[a] == 2) {
				return "Bats Nearby";
			}
			else if(x.getConnectedRooms()[a] == wumpus) {
				return "I smell a Wumpus!";
			}
		}
		return "none";
	}
	public void handleHazard(String s) {
		if (player == Hazards[0]) {
			player = (int)(Math.random()*30) + 1;
			Hazards[0] = (int)(Math.random()*30) + 1;
			while (Hazards[0] == player) {
				Hazards[0] = (int)(Math.random()*30) + 1;
			}
		}
		if (player == Hazards[2] || player == Hazards[3]) {
			//
		}
	}
	
	public void movePlayer(int d)
	{
		int[] possible = c.getRoom(player).getConnectedRooms();
		for(int x = 0; x < possible.length; x++) {
			if (d == possible[x]) {
				player = d;
				turns++;
				break;
			}
		}
	}
	
	public void moveWumpus()
	{
		int[] possible = c.adjacentRooms(wumpus);
		wumpus = possible[(int)(Math.random()*3 + 1)];
	}
	
	public int trackWumpus() {
		return wumpus;
	}
	public int trackPlayer() {
		return player;
	}
}



