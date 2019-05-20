/**
 * @author Daniel Rashevsky
 * Class Name: Room
 * Description: Class to represent all the properties of a single room
 * Rev. History: (Date - Revision)
 * 
 * 3/26/2019 - Added functionality to allow each room to point to three adjacent rooms
 * 3/27/2019 - Testing bitbucket -> discord functionality (just this line)
 * 4/17/2019 - Added a hazard property
 * 4/24/2019 - Updated getters and setters for hazards
 * 5/20/2019 - Added toString
 */

//NOTE: For connected rooms, the int can be either 0 or 1 - 30: if 0, that pathway isn't used 

public class Room {
	private int[] connectedRooms = new int[3];	//Allow a room to point to a maximum of 3 others, use 0 to indicate an unused pointer
	private int hazardType; 					//Determine what hazard a room has, 0 = none, 1 = pit, 2 = bat
	
	public Room(int r1, int r2, int r3) {
		connectedRooms[0] = r1;
		connectedRooms[1] = r2;
		connectedRooms[2] = r3;
		hazardType = 0;
	}
	
	//Returns an array containing connected rooms to a room
	public int[] getConnectedRooms() {
		return connectedRooms;
	}
	
	//Sets the hazard type, checks to make sure it is between 0 and 2
	public void setHazard(int hazardType) {
		if (hazardType >= 0 && hazardType <= 2)
			this.hazardType = hazardType;
	}
		
	//Returns the type of hazard the room has, or 0 if none
	public int getHazard() {
		return hazardType;
	}
	
	//Returns a text summary of the room's properties
	public String toString() {
		String hazard;
		if (hazardType == 0) {
			hazard = "None";
		} else if (hazardType == 1) {
			hazard = "Pit";
		} else {
			hazard = "Bat";
		}
		return "Connected to rooms " + connectedRooms[0] + ", " + connectedRooms[1] + ", " + connectedRooms[2] + ". Hazard: " + hazard;
	}
}
