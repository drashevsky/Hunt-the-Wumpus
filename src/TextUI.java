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
		while(!gameControl.isGameOver()) {
			//Display current score
			int currentScore = GameControl.gameControl.getPlayer().computeScore();
			System.out.println("Your current score is: " + currentScore);
			
			//First check hazards
			System.out.print("Checking for hazards nearby: ");
			int playerRoomValue = gameControl.getGameLocations().trackPlayer();
			Room playerRoom = gameControl.getCave().getRoom(playerRoomValue);
			String nearHazardCheck = gameControl.getGameLocations().nearHazard(playerRoom);
			System.out.println(nearHazardCheck);
			System.out.println("Hazards are in rooms: \n" + gameControl.getGameLocations().toString());
			if(gameControl.getCave().getRoom(playerRoomValue).getHazard() != 0) {
				System.out.println("---[Handling hazards]---");
				gameControl.getGameLocations().handleHazard();
			}
			playerRoomValue = gameControl.getGameLocations().trackPlayer();
			playerRoom = gameControl.getCave().getRoom(playerRoomValue);
			
			//Check for Wumpus
			if(playerRoomValue == gameControl.getGameLocations().trackWumpus()) {
				System.out.println("The Wumpus! Answer some trivia to stay alive!");
				boolean triviaPass = gameControl.getTrivia().startTrivia(3, 5);
				if(triviaPass) {
					gameControl.getWumpus().endTrivia();
				} else {
					gameControl.gameOver(true);
				}
			}
			
			if(!gameControl.isGameOver()) {
				//Players turn
				showRoom(playerRoomValue, playerRoom);
				System.out.println("What action would you like to take?");
				System.out.println("[1] Move [2] Shoot an arrow \n[3] Purchase arrows [4] Purchase secrets");
				gameControl.takeAction(gameControl.getScanner().nextLine(), playerRoom);
			}
		}
	}
	
	public void showRoom(int roomNumber, Room surroundingRooms) {
		System.out.println("---[You are in room: " + roomNumber + "]---");
		System.out.println("---[Some data about the room you're in]---");
		System.out.println(surroundingRooms.toString());
		System.out.println();
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