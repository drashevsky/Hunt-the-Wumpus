import java.util.Scanner;

public class TextUI {
	private int width;
	private int length;
	private final int scale;
	
	public TextUI(int scale) {
		this.scale = scale;
		width = scale * 4;
		length = scale * 2;
	}
	
	public void runEvents(GameControl gameControl) {
		System.out.println("Type 'x' to start a new game.");
		String input = gameControl.getScanner().nextLine();
		if (input.equals("x"))
		{
			GameControl.gameControl.newGameButtonClicked();
		}
		while(true) {
			//First check hazards
			System.out.println("Checking for hazards: ");
			int playerRoomValue = gameControl.getGameLocations().trackPlayer();
			Room playerRoom = gameControl.getCave().getRoom(playerRoomValue);
			String nearHazardCheck = gameControl.getGameLocations().nearHazard(playerRoom);
			System.out.println(nearHazardCheck);
			
			//Players turn
			System.out.println("What action would you like to take?");
			System.out.println("[1] Move [2] Shoot an arrow \n[3] Purchase arrows [4] Purchase secrets");
			gameControl.takeAction(gameControl.getScanner().nextLine(), playerRoom);
		}
	}
	
	public void showRoom(int roomNumber, Room surroundingRooms) {
		System.out.println("You are in room: " + roomNumber);
		System.out.println(surroundingRooms.toString());
	}
	
	public void showMainMenu() {
		System.out.println("Printing a text menu: ");
		for(int x = 0; x < length; x++) {
			for(int y = 0; y < width; y++) {
				if(x == 0 || x == length - 1) {
					System.out.print("*");
				}else if(y == 0 || y == width - 1) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
