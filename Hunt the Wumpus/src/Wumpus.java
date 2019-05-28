/**
 * @author Shrey Srivastava
 * Class Name: Wumpus
 * Description: Controls lazy wumpus movement
 * Rev. History: (Date - Revision)
 * 
 * 5/24/2019 - Implemented all lazy wumpus functional
 */

public class Wumpus {
	private int location;
	private int x;
	private Cave C;
	
	public Wumpus(Cave c, GameLocations loc) {
		C = c;
		location = (int)(Math.random()*30) + 1;
		while (location == loc.trackPlayer()) {
			location = (int)(Math.random()*30) + 1;
		}
	}
	
	public void move() {
		int random = (int)(Math.random()*3);
		int[] possible = C.getRoom(location).getConnectedRooms();
		while (possible[random] != 0) 
			random = (int)(Math.random()*3);
		location = possible[random];
	}
	
	public void endTrivia() {
		int random = (int)(Math.random()*2 + 2);
		for (int x = random; x > 0; x--) {
			move();
		}
	}
	
	public int track() {
		return location;
	}
}
