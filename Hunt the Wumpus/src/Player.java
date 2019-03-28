
public class Player {
int arrows;
int secrets;
int goldCoins;
private GameLocations location;
public Player(GameLocations location) {
	arrows = 0;
	secrets = 0;
	goldCoins = 0;
	
}
// Returns string the player
public String toString()
{
	return "Player";
}
public void move() {
	int x = location.trackPlayer();
	Room y = Cave.getRoom(x);
	int[] z = y.getConnectedRooms();
	
}
public int getGoldCoins() {
	return goldCoins;
}
//Purchases arrow based on the number of questions answered, will probably call Trivia method for parameter
public boolean purchaseArrows(int questionsAnswered)
{
	if (questionsAnswered > 2)
	{
		questionsAnswered -=2;
		this.arrows += 1;
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
		this.secrets += 1;
		return true;
	}
	return false;
}

}