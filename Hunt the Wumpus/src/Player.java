
public class Player {
int arrows;
int secrets;
int goldCoins;
public Player() {
	arrows = 0;
	secrets = 0;
	goldCoins = 0;
}
// Returns string of the player
public String toString()
{
	return "Player";
}
// Moves the player based on its current room, needs location from Cave
public int[] Move(int currentRoom) {
	int[] connectedRooms = {0, 0, 0, 0, 0, 0};
	return connectedRooms;
}
// Checks if the shot arrow hit the Wumpus, supposed to be used by game control
public boolean shootArrow(int Wumpus, int Arrow)
{
	if (Wumpus == Arrow) {
		return true;
	}
	return false;
}
// Purchases arrow based on the number of questions answered, will probably call Trivia method for parameter
public boolean purchaseArrows(int questionsAnswered)
{
	if (questionsAnswered > 2)
	{
		questionsAnswered -=2;
		return true;
	}
	return false;
}
//Purchase a secret based on the number of questions answered, will probably call Trivia method for parameter
public boolean purchaseSecrets(int questionsAnswered)
{
	if (questionsAnswered > 2)
	{
		questionsAnswered -=2;
		return true;
	}
	return false;
}
}
