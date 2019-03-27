/**
 * @author Daniel Rashevsky
 * Class Name: Cave
 * Description: Stores and updates the game map in memory
 * Rev. History: (Date - Revision)
 * 
 * 3/12/2019 - Added dummy methods 
 * 3/19/2019 - Wrote 3 helper methods to convert between 1d and 2d arrays
 * 3/21/2019 - Finished up the first working draft of adjacentRooms
 * 3/26/2019 - Removed methods supposed to be in GameLocations
 *             Added Room object as the core element of 2d array
 *             Implemented reading map files, untested
 *             Reorganized class, determined public/private/static
 */

import java.util.*;
import java.io.*;

public class Cave {
	private static Room[][] caveMap;
	
	public Cave(String mapFile) throws FileNotFoundException {
		caveMap = loadMap(mapFile);
		tester();
	}
	
	/** Reads from a map file following a specific format
	 * 
	 * @param mapFile - the location of the map file
	 * @return - a finished map
	 * @throws FileNotFoundException
	 * Preconditions: file exists, correct format specified in method
	 */
	private static Room[][] loadMap(String mapFile) throws FileNotFoundException {
		Scanner src = new Scanner(new File("./input/" + mapFile));						//Read from this map file
		Room[][] map = new Room[src.nextInt()][src.nextInt()];							//First line contains two ints with 2d array size
		src.nextLine();																	//Force next line
		
		for (int i = 1; src.hasNextLine() && i <= map.length * map[0].length; i++) {	//Each room's properties take up one line
			Room newRoom = new Room(src.nextInt(), src.nextInt(), src.nextInt());		//First 3 ints are connected rooms
			map[roomRow(i)][roomCol(i)] = newRoom;
			src.nextLine();																//Force next line
		}
		
		src.close();
		return map;
	}
	
	/** Returns entire map of cave
	 * @return the entire map
	 */
	public static Room[][] fullMap() {
		return caveMap;
	}
	
	/** Returns a single room from the map
	 * 
	 * @param room - room # between 1 - 30
	 * @return the requested room
	 * Precondition: the room exists
	 */
	public static Room getRoom(int room) {
		return caveMap[roomRow(room)][roomCol(room)];
	}
	
 	/** Tests all the other methods */
	public void tester() {
		System.out.println("Testing 1D -> 2D index conversion:");
		for (int i = 1; i <= 30; i++) {
			System.out.print("[ " + roomRow(i) + ", " + roomCol(i) + " ]" );
			if (i % caveMap[0].length == 0) {
				System.out.println();
			}
		}
		
		System.out.println("\nTesting 2D -> 1D index conversion:");
		for (int i = 0; i < caveMap.length; i++) {
			for (int j = 0; j < caveMap[0].length; j++) {
				System.out.print("[");
				if (vectorIndexToRoom(i, j) < 10)
					System.out.print(" ");
				System.out.print(vectorIndexToRoom(i, j) + "] ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < 6; i++)
			System.out.println(adjacentRooms(6)[i]);
	}

	public String toString() {
		return "Cave Object";
	}
	
	//------------------------------------------------------------------------Adjacent Rooms Methods------------------------------------------------------------------------//
	
	/** Returns an array of the rooms which are adjacent to a select room 
	 * 
	 * @param room - the room number to look around
	 * @return the integer array of adjacent rooms
	 * Precondition: room must exist
	 */
	public static int[] adjacentRooms(int room) {
		int[] rooms = new int[6];
		
		//Get room indexes in 2d array
		int roomRow = roomRow(room);
		int roomCol = roomCol(room);
		
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
		if (roomRow == caveMap.length - 1) {
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
		
		Arrays.sort(rooms);
		return rooms;
	}
	
	/** Returns the rooms to the immediate left and right of room
	 * 
	 * @param room - The room to find the left and right of
	 * @param roomRow - The room's column in the map array
	 * @param roomCol - The room's row in the map array
	 * @return An array containing the numbers of the rooms to the immediate left and right of the selected room
	 * Precondition: room must exist, indexes correct
	 */
	private static int[] adjacentRoomHelper(int room, int roomRow, int roomCol) {
		int[] rooms = new int[2];
		
		//Finds room to the immediate left
		if (roomCol == 0) {
			rooms[0] = vectorIndexToRoom(roomRow, caveMap[0].length - 1); //wrap around to last room
		} else {
			rooms[0] = room - 1;
		}
		
		//Finds room to the immediate right
		if (roomCol == caveMap[0].length - 1) {
			rooms[1] = vectorIndexToRoom(roomRow, 0); //wrap around to first room
		} else {
			rooms[1] = room + 1;
		}
		return rooms;
	}
	
	/** Returns the row number of a room
	 * 
	 * @param room - The room to find the row of (1 - 30)
	 * @return The row number of the room
	 * Precondition: the room exists
	 */
	private static int roomRow(int room) {
		int row = room / caveMap[0].length;
		return (room % caveMap[0].length == 0) ? row - 1 : row; //all the multiples of the row length must be reduced by one
	}
	
	/** Returns the column number of a room
	 * 
	 * @param room - The room to find the column of (1 - 30)
	 * @return The column number of the room
	 * Precondition: the room exists
	 */
	private static int roomCol(int room) {
		return room - (roomRow(room) * caveMap[0].length) - 1;	//Converts room to a number from the first row, then subtracts 1
	}
	
	/** Returns the room number given a 2d index
	 * 
	 * @param column
	 * @param row
	 * @return The room number from the column and row as an int
	 * Precondition: the room exists
	 */
	private static int vectorIndexToRoom(int row, int column) {
		return row * caveMap[0].length + column + 1; //converts to 1d index and adds one for room number
	}
}