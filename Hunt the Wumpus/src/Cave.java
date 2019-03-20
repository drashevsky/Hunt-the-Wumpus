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
	private String[][] caveMap;
	
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
	
	/** Returns the rooms to the immediate left and right of room
	 * 
	 * @param room - The room to find the left and right of
	 * @param roomRow - The room's column in the map array
	 * @param roomCol - The room's row in the map array
	 * @return An array containing the numbers of the rooms to the immediate left and right of the selected room
	 * Precondition: room must exist, indexes correct
	 */
	private int[] adjacentRoomHelper(int room, int roomRow, int roomCol) {
		int[] rooms = new int[2];
		
		//Finds room to the immediate left
		if (roomCol == 0) {
			rooms[0] = vectorIndexToRoom(roomRow, caveMap[0].length - 1); //wrap around to last room
		} else {
			rooms[0] = room - 1;
		}
		
		//Finds room to the immediate right
		if (roomCol == caveMap[0].length) {
			rooms[1] = vectorIndexToRoom(roomRow, 0); //wrap around to first room
		} else {
			rooms[1] = room + 1;
		}
		return rooms;
	}
	
	/** Returns an array of the rooms which are adjacent to a select room 
	 * 
	 * @param room - the room number to look around
	 * @return the integer array of adjacent rooms
	 * Precondition: room must exist
	 */
	public int[] adjacentRooms(int room) {
		int[] rooms = new int[6];
		
		//Get room indexes in 2d array
		int roomRow = roomRow(room) + 1;
		int roomCol = roomCol(room) + 1;
		
		//Finding room to immediate left
		rooms[0] = adjacentRoomHelper(room, roomRow, roomCol)[0];
		
		//Finding room to immediate right
		rooms[1] = adjacentRoomHelper(room, roomRow, roomCol)[1];
		
		//Finding room above current room
		if (roomRow == 0) {
			rooms[2] = vectorIndexToRoom(caveMap.length - 1, roomCol); //Wrap around to bottom room
		} else {
			rooms[2] = room - caveMap[0].length;
		}
		
		//Finding room below current room
		if (roomRow == caveMap.length) {
			rooms[3] = vectorIndexToRoom(0, roomCol); //Wrap around to top room
		} else {
			rooms[3] = room + caveMap[0].length;
		}
		
		//Determine whether the last two adjacent rooms are above or below
		if (room % 2 == 0) {
			int ref = rooms[3];
			rooms[4] = adjacentRoomHelper(ref, roomRow(ref), roomCol(ref))[0];	//Below-to-the-left room
			rooms[5] = adjacentRoomHelper(ref, roomRow(ref), roomCol(ref))[1];	//Below-to-the-right room
		} else {
			int ref = rooms[2];
			rooms[4] = adjacentRoomHelper(ref, roomRow(ref), roomCol(ref))[0]; //Above-to-the-left room
			rooms[5] = adjacentRoomHelper(ref, roomRow(ref), roomCol(ref))[1]; //Above-to-the-right room
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
