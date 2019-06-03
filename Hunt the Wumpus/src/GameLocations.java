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
 * 5/28/2019 - [DEEPAYAN] Added trackWumpus and moveWumpus method
 */

public class GameLocations {
	private int player;
	private int[] Hazards;
	private Cave c; 
	private int turns;
	private int start;
	private Wumpus wumpus;
	private Player actualPlayer;
	private int totalGoldCoins;
	
	public GameLocations(Cave c, int playerLocation) {
		this.c = c;
		turns = 0;
		wumpus = new Wumpus(c, this);
		player = playerLocation;
		actualPlayer = new Player(this, "bob", c, wumpus);
		start = playerLocation;
		Hazards = new int[4];
		Hazards[0] = (int)(Math.random()*30) + 1;
		Hazards[1] = (int)(Math.random()*30) + 1;
		Hazards[2] = (int)(Math.random()*30) + 1;
		Hazards[3] = (int)(Math.random()*30) + 1;
		for (int x = 0; x < Hazards.length; x++) {
			for (int y = 0; y < Hazards.length; y++)
			{
				if (player == Hazards[x] || Hazards[x] == Hazards[y]) {
					Hazards[x] = (int)(Math.random()*30) + 1;
				}
			}
		}
		setHazardTypes(c);
	}

	// Sets each room to either a bat(2s) or a pit (1s) 
	public void setHazardTypes(Cave cave) {
		cave.setRoomHazard(Hazards[0], 1);
		cave.setRoomHazard(Hazards[1], 1);
		cave.setRoomHazard(Hazards[2], 2);
		cave.setRoomHazard(Hazards[3], 2);
	}
	
	// returns the name of the object
	public String toString() {
		String hazards = "";
		for(int room : Hazards) {
			hazards += room + " \n";
		}
		return hazards;
	}
	
	public int[] getHazards() {
		return Hazards;
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
				else if (currConnRoom == wumpus.track()) {
					return "I smell a Wumpus!";
				}
			}
		}
		return "none";
	}
	
	public void handleHazard() {
		// Handle situation with pit
		if (c.getRoom(trackPlayer()).getHazard() == 1) {
			if(/*startTrivia()*/true) {
				System.out.println("---[Moving player back to start]---");
				player = start;
			}
		}
		// Handle situation with bats
		else if (c.getRoom(trackPlayer()).getHazard() == 2) {
			for (int x = 0; x < Hazards.length; x++) {
				while (Hazards[x] == trackPlayer()) {
					Hazards[x] = (int)(Math.random()*30) + 1;
					player = (int)(Math.random()*30) + 1;
				}
			}
		}
	}
	
	public void movePlayer(int d)
	{
		/*int[] possible = c.getRoom(player).getConnectedRooms();
		for(int x = 0; x < possible.length; x++) {
			if (d == possible[x]) {
				player = d;
				turns++;
				actualPlayer.incrementGoldCoins(1);
				break;
			}
		}*/
		player = d;
		turns++;
		actualPlayer.incrementGoldCoins(1);
	}
	
	public void moveWumpus() {
		wumpus.move();
	}
	
	public boolean startTrivia() {
		return GameControl.gameControl.getTrivia().startTrivia(2, 3);
	}
	
	public void endTrivia(boolean result) {
		//
	}
	
	
	public int trackPlayer() {
		return player;
	}
	
	public int trackWumpus() {
		return wumpus.track();
	}
	
	//Returns true if Wumpus is within two rooms of the Player
	public boolean playerWithinTwoRooms() {
		int[] surroundingRooms = c.adjacentRooms(player);
		int wumpusPos = wumpus.track();
		for(int i : surroundingRooms) {
			if(wumpusPos == i) {
				return true;
			} else {
				int[] surroundingRoomsSecond = c.adjacentRooms(i);
				for(int j : surroundingRoomsSecond) {
					if(wumpusPos == j) {
						return true;
					}
				}				
			}
		}
		return false;
	}
	
}