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
 * 5/21/2019 - [ERIC] Added a setHazardType function because Shrey didn't do it
 * 5/22/2019 - [DANIEL] fixed nearhazard to function properly
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
		Hazards[0] = 1;//(int)(Math.random()*30) + 1;
		Hazards[1] = 2;//(int)(Math.random()*30) + 1;
		Hazards[2] = 3;//(int)(Math.random()*30) + 1;
		Hazards[3] = 4;//(int)(Math.random()*30) + 1;
		/*while (x < Hazards.length && Hazards[x] == Hazards[x-1]) { //Shreyo you need to fix this it doesn't check if [3] and [1] are the
			Hazards[x] = (int)(Math.random()*30) + 1;			   //same
			x++;
		}*/
		setHazardTypes(c);
	}

	// Sets each room to either a bat or a pit 
	public void setHazardTypes(Cave cave) {
		cave.setRoomHazard(Hazards[0], 1);
		cave.setRoomHazard(Hazards[1], 1);
		cave.setRoomHazard(Hazards[2], 2);
		cave.setRoomHazard(Hazards[3], 2);
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
			int currConnRoom = x.getConnectedRooms()[a];
			if (currConnRoom != 0) {
				if (c.getRoom(currConnRoom).getHazard() == 1) {
					return "I feel a draft";
				}
				else if (c.getRoom(currConnRoom).getHazard() == 2) {
					return "Bats Nearby";
				}
				else if (currConnRoom == wumpus) {
					return "I smell a Wumpus!";
				}
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
		if (player == Hazards[1]) {
				player = (int)(Math.random()*30) + 1;
				Hazards[1] = (int)(Math.random()*30) + 1;
				while (Hazards[1] == player) {
					Hazards[1] = (int)(Math.random()*30) + 1;
		}
		}
		if (player == Hazards[2] || player == Hazards[3]) {
			
		}
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
	
	public void startTrivia() {
		//
	}
	
	public void endTrivia(boolean result) {
		//
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



