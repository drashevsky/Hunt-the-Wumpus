/**
 * @author Daniel Rashevsky
 * Class Name: Cave
 * Description: Stores and updates the game map in memory
 * Useful Methods: fullMap(), getRoom(), toString(), adjacentRooms()
 * Rev. History: (Date - Revision)
 * 
 * 3/12/2019 - Added dummy methods 
 * 3/19/2019 - Wrote 3 helper methods to convert between 1d and 2d arrays
 * 3/21/2019 - Finished up the first working draft of adjacentRooms
 * 3/26/2019 - Removed methods supposed to be in GameLocations
 *             Added Room object as the core element of 2d array
 *             Implemented reading map files, untested
 *             Reorganized class, determined public/private/static
 * 4/02/2019 - Wrote some new tests, updated documentation/some methods
 * 4/12/2019 - Updated documentation, removed bulky javadoc
 * 4/24/2019 - Added private mapRows and mapCols facilities to make 
 * 			   adjacentRooms compatible with the randomMap generator, 
 * 			   developed 2 randomMap generator algorithms and helper methods 
 * 			   (both still in testing), fixed loadmap to be compatible with 
 *             mapRows/Cols
 */

import java.util.*;
import java.io.*;

public class Cave {
	private Room[][] caveMap;
	private String mapFile;
	private int mapRows, mapCols;
	
	public Cave(String mapFile) {
		this.mapRows = 5;
		this.mapCols = 6;
		
		this.mapFile = mapFile;
		loadMap(mapFile);
		tester();
	}
	
	//Returns entire map of cave
	public Room[][] fullMap() {
		return caveMap;
	}
	
	//Returns a single room object from the map given its number (1 - 30)
	public Room getRoom(int room) {
		return caveMap[roomRow(room)][roomCol(room)];
	}
	
	//Returns a string containing information about the Cave object
	public String toString() {
		return "Cave Object: " + caveMap.length + " x " + caveMap[0].length + ", " + this.mapFile;
	}
	
	//Reads from a map file into caveMap following a specific format: 
	// * First line contains two ints with 2d array size (e.g. 6 5),
	// * Followed by each room's properties occupying their own line (e.g. 1 4 5).
	// * Only property so far is connected rooms, 3 ints ----------------^
	private void loadMap(String mapFile) {
		Scanner src;
		
		try {
			src = new Scanner(new File("./input/" + mapFile));						//Read from this map file, or give error message
		} catch (FileNotFoundException e) {
			System.out.println("Could not access \"" + mapFile + "\".");
			return;
		}
		
		caveMap = new Room[src.nextInt()][src.nextInt()];								//First line contains two ints with 2d array size
		src.nextLine();																	//Force next line
		
		for (int i = 1; src.hasNextLine() && i <= caveMap.length * caveMap[0].length; i++) {	//Each room's properties take up one line
			Room newRoom = new Room(src.nextInt(), src.nextInt(), src.nextInt());				//First 3 ints are connected rooms
			caveMap[roomRow(i)][roomCol(i)] = newRoom;
			if (src.hasNextLine())
				src.nextLine();																//Force next line
		}
		
		this.mapRows = caveMap.length;												//Updates map rows and cols for adjacentRooms
		this.mapCols = caveMap[0].length;
		src.close();
	}
	
	//Creates a random map of connected room starting with startRoom (1 - 30)
	private int[][] randomMap(int startRoom, int numRooms) {
 		int[][] map = new int[numRooms][3];
 		int currRoom = startRoom;
 		int nextRoom;
 		
 		ArrayList<Integer> stack = new ArrayList<Integer>();
 		ArrayList<Integer> usedRooms = new ArrayList<Integer>();
 		
 		while (unconnectedRoomsExist(map)) {
 			int[] adjRooms = this.adjacentRooms(currRoom);
 			boolean unconnectedAdjacentRooms = false;
 			
 			for (int i = 0; i < adjRooms.length; i++) {
 	 			for (int j = 0; j < usedRooms.size(); j++) {
 	 				if (adjRooms[i] == usedRooms.get(j))
 	 					adjRooms[i] = 0;
 	 			}
 	 			if (adjRooms[i] != 0)
 					unconnectedAdjacentRooms = true;
 	 		}
 			
 			if (unconnectedAdjacentRooms) {
 				while ((nextRoom = adjRooms[(int)(Math.random() * adjRooms.length)]) == 0);
 				stack.add(nextRoom);
 				
 				int cStartIndex, nStartIndex;
	 			for (cStartIndex = 0; cStartIndex < 3 && map[currRoom - 1][cStartIndex] != 0; cStartIndex++);
	 			for (nStartIndex = 0; nStartIndex < 3 && map[nextRoom - 1][nStartIndex] != 0; nStartIndex++);
	 			
 				map[currRoom - 1][cStartIndex] = nextRoom;
 				map[nextRoom - 1][nStartIndex] = currRoom;
 				currRoom = nextRoom;
 				usedRooms.add(currRoom);
 			} else if (stack.size() > 0) {
 				currRoom = stack.remove(stack.size() - 1);
 			}
 		}
 		return map;
 	}
 	
 	private int[][] randomMap2(int startRoom, int numRooms) {
 		int[][] map = new int[numRooms][3];

 		for (int i = 1; i <= numRooms; i++) {
 			int[] adjRooms = this.adjacentRooms(i);
 			
 			for (int j = 0; j < adjRooms.length; j++) {
 	 			if (map[adjRooms[j] - 1][0] != 0 && map[adjRooms[j] - 1][1] != 0 && map[adjRooms[j] - 1][2] != 0)
 	 					adjRooms[j] = 0;
 	 		}
 			
 			int count = 0;
 			for (int j = 0; j < adjRooms.length; j++) count += adjRooms[j];
 			System.out.println(count);
 			
 			if (map[i - 1][0] == 0 || map[i - 1][1] == 0 || map[i - 1][2] == 0) {
	 			int startIndex;
	 			int endIndex = 2;
	 			for (startIndex = 0; startIndex < 3 && map[i - 1][startIndex] != 0; startIndex++);
	 			if (startIndex == 0) endIndex = 1;
	 			
	 			for (int j = startIndex; j <= endIndex; j++) {
	 				int randRoom = 0;
	 				int randRoomIndex = 0;
	 				
	 				while (randRoom == 0) {
	 					randRoomIndex = (int)(Math.random() * adjRooms.length);
	 					randRoom = adjRooms[randRoomIndex];
	 				}
	 				map[i - 1][j] = randRoom;
	 				
	 				int startIndexRand;
		 			for (startIndexRand = 0; startIndexRand < 3 && map[randRoom - 1][startIndexRand] != 0; startIndexRand++);
	 				map[randRoom - 1][startIndexRand] = i;
	 				adjRooms[randRoomIndex] = 0;
	 			}
 			}
 		}
 		return map;
 	}
	
 	private boolean unconnectedRoomsExist(int[][] map) {
 		for (int i = 0; i < map.length; i++) {
 			int a = 0;
 			for (int j = 0; j < map[0].length; j++) {
 				a += map[i][j];
 			}
 			if (a == 0) return true;
 		}
 		return false;
 	}
	
 	//Tests all the other methods
 	private void tester() {
		System.out.println("Testing 1D -> 2D index conversion:");
		for (int i = 1; i <= 30; i++) {
			System.out.print("[ " + roomRow(i) + ", " + roomCol(i) + " ]" );
			if (i % this.mapCols == 0) {
				System.out.println();
			}
		}
		
		System.out.println("\nTesting 2D -> 1D index conversion:");
		for (int i = 0; i < this.mapRows; i++) {
			for (int j = 0; j < this.mapCols; j++) {
				System.out.print("[");
				if (vectorIndexToRoom(i, j) < 10)
					System.out.print(" ");
				System.out.print(vectorIndexToRoom(i, j) + "] ");
			}
			System.out.println();
		}
		
		System.out.println("\nTesting adjacent rooms:");
		for (int i = 1; i <= 30; i++) {
			for (int j = 0; j < 6; j++) {
				if (adjacentRooms(i)[j] < 10)
					System.out.print(" ");
				System.out.print(adjacentRooms(i)[j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("\nTesting getRoom:");
		for (int i = 0; i < 3; i++) {
			for (int j = 1; j <= 30; j++)
				System.out.print(getRoom(j).getConnectedRooms()[i] + " ");
			System.out.println();
		}
		
		System.out.println("\nFile read test: " + caveMap.length + " " + caveMap[0].length);
		System.out.println(this); //toString test
		
		System.out.println("\nTesting random map:");
		int[][] newMap = randomMap(1, 30);
		for (int i = 0; i < newMap.length; i++) {
			System.out.print(i + 1 + ": ");
			for (int j = 0; j < newMap[0].length; j++) {
				System.out.print(newMap[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	//-------------------------------------------------------------------Adjacent Rooms Methods-------------------------------------------------------------------//
	
	//Returns an array of the rooms which are adjacent to a select room (restricted to 1 - 30)
 	public int[] adjacentRooms(int room) {
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
			rooms[2] = vectorIndexToRoom(this.mapRows - 1, roomCol); //Wrap around to bottom room
		} else {
			rooms[2] = room - this.mapCols;
		}
		
		//Finding room below current room
		if (roomRow == this.mapRows - 1) {
			rooms[3] = vectorIndexToRoom(0, roomCol); //Wrap around to top room
		} else {
			rooms[3] = room + this.mapCols;
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
	
	//Returns the numbers of the rooms to the immediate left and right of a select room (1 - 30)
	//roomRow and roomCol are the room's indexes in the 2d array
 	private int[] adjacentRoomHelper(int room, int roomRow, int roomCol) {
		int[] rooms = new int[2];
		
		//Finds room to the immediate left
		if (roomCol == 0) {
			rooms[0] = vectorIndexToRoom(roomRow, this.mapCols - 1); //wrap around to last room
		} else {
			rooms[0] = room - 1;
		}
		
		//Finds room to the immediate right
		if (roomCol == this.mapCols - 1) {
			rooms[1] = vectorIndexToRoom(roomRow, 0); //wrap around to first room
		} else {
			rooms[1] = room + 1;
		}
		return rooms;
	}
	
	//Returns the row number of a room (restricted to 1 - 30)
 	private int roomRow(int room) {
		int row = room / this.mapCols;
		return (room % this.mapCols == 0) ? row - 1 : row; //all the multiples of the row length must be reduced by one
	}
	
	//Returns the column number of a room (restricted to 1 - 30)
 	private int roomCol(int room) {
		return room - (roomRow(room) * this.mapCols) - 1;	//Converts room to a number from the first row, then subtracts 1
	}
	
	//Returns the room number given a 2d index
 	private int vectorIndexToRoom(int row, int column) {
		return row * this.mapCols + column + 1; //converts to 1d index and adds one for room number
	}
}