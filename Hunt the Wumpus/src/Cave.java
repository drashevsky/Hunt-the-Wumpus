/**
 * @author Daniel Rashevsky
 * Class Name: Cave
 * Description: Stores and updates the game map in memory
 * Rev. History: (Date - Revision)
 * 
 * 3/12/2019 - Added dummy methods 
 */

public class Cave {
	String[][] caveMap;
	
	public Cave() {
		
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
	 * Precondition room must exist
	 */
	public String roomProperties(int room) {
		return "";
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
