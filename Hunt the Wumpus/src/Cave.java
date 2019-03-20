/**
 * @author Daniel Rashevsky
 * Class Name: Cave
 * Description: Stores and updates the game map in memory
 * Rev. History: (Date - Revision)
 * 
 * 3/12/2019 - Added dummy methods 
 * 3/19/2019 - Wrote 3 helper methods to convert between 1d and 2d arrays
 */

public class Cave {
	String[][] caveMap;
	
	public Cave(String[][] map) {
		caveMap = map;
	}
	
	/** Returns entire map of cave
	 * @return the entire map
	 */
	public String[][] fullMap() {
		return caveMap;
	}
	
	/** Returns the properties (represented as a String) of a single room
	 * 
	 * @param room - the room # whose properties you want to access
	 * @return the String representing the properties
	 * Precondition: room must exist
	 */
	public String roomProperties(int room) {
		return "";
	}
	
	/** Returns the row number of a room
	 * 
	 * @param room - The room to find the row of
	 * @return The row number of the room
	 * Precondition: the room exists
	 */
	private int roomRow(int room) {
		int row = room / caveMap[0].length;
		return (room % caveMap[0].length == 0) ? row - 1 : row; //all the multiples of the row length must be reduced by one
	}
	
	/** Returns the column number of a room
	 * 
	 * @param room - The room to find the column of
	 * @return The column number of the room
	 * Precondition: the room exists
	 */
	private int roomCol(int room) {
		return room - (roomRow(room) * caveMap[0].length) - 1;	//Converts room to a number from the first row, then subtracts 1
	}
	
	/** Returns the room number given a 2d index
	 * 
	 * @param column
	 * @param row
	 * @return The room number from the column and row as an int
	 * Precondition: the room exists
	 */
	private int vectorIndexToRoom(int row, int column) {
		return row * caveMap[0].length + column + 1; //converts to 1d index and adds one for room number
	}
	
	/** Returns an array of the rooms which are adjacent to a select room 
	 * 
	 * @param room - the room number to look around
	 * @return the integer array of adjacent rooms
	 * Precondition: room must exist
	 */
	public int[] adjacentRooms(int room) {
		int[] rooms = new int[6];
		int roomRow = roomRow(room) + 1;
		int roomCol = roomCol(room) + 1;
		
		if (roomCol == 0) {
			rooms[0] = vectorIndexToRoom(roomRow, caveMap[0].length - 1);
		} else if (roomCol == caveMap[0].length) {
			//rooms[0] = vectorIndexToRoom
			//fix
		} else {
			
		}
		
		if (room % 2 == 0) {
			
		} else {
			
		}
		return rooms;
	}
	
	/** Returns a list of rooms containing the specified property
	 * 
	 * @param room - The current room the player is in
	 * @param property - A specified property to look for, like hazards: if "" the adjacent rooms to room are returned
	 * @return An array of room numbers containing the property
	 * Calls roomProperties
	 */
	public int[] selectRoomsByProperty(int room, String property) {
		int[] rooms = {0, 0, 0};
		return rooms;
	}
	
	/** Returns the room # with the wumpus in it
	 * 
	 * @return The room with the wumpus
	 * Calls roomProperties
	 */
	public int wumpusRoom() {
		return 0;
	}
	
	public String toString() {
		return "Cave Object";
	}
}
