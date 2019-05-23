
public class Wumpus {
	private int location;
	private Cave C;
	public Wumpus(Cave c, GameLocations loc) {
		C = c;
		location = (int)(Math.random()*30) + 1;
		while (location == loc.trackPlayer()) {
			location = (int)(Math.random()*30) + 1;
		}
		}
	public void move() {
		int[] possible = C.adjacentRooms(location);
		location = possible[(int)(Math.random()*3 + 1)];
	}
	public void endTrivia() {
		int x = 0;
		int random = (int)(Math.random()*2 + 2);
		int[] possible = new int[random];
		for (int y = 0; y < possible.length; y++) {
			int[] possible2 = C.adjacentRooms(location);
			possible[y] = possible2[(int)(Math.random()*3 + 1)];
			location = possible[y];
		}
		location = possible[possible.length-1];
	}
	public int track() {
		return location;
	}
}
