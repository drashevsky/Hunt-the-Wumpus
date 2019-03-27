/**
 * @author Daniel Rashevsky
 * Class Name: Room
 * Description: Class to represent all the properties of a single room
 * Rev. History: (Date - Revision)
 * 
 * 3/26/2019 - Added functionality to allow each room to point to three adjacent rooms
 */

//NOTE: For connected rooms, the int can be either 0 or 1 - 30: if 0, that pathway isn't used 

public class Room {
	private int[] connectedRooms = new int[3];	//Allow a room to point to a maximum of 3 others, use 0 to indicate an unused pointer
	
	public Room(int r1, int r2, int r3) {
		connectedRooms[0] = r1;
		connectedRooms[1] = r2;
		connectedRooms[2] = r3;
	}
	
	/** Returns an array containing connected rooms to a room */
	public int[] getConnectedRooms() {
		return connectedRooms;
	}
}
