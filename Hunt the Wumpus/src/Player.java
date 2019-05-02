/**
 * @author Shrey Srivastava
 * Class Name: Player
 * Description: Manages the player's state
 * Rev. History: (Date - Revision)
 * 
 * 4/03/2019 - [DANIEL] Fixed formatting 
 */

public class Player {
	int arrows;
	int secrets;
	int goldCoins;
	boolean killedWumpus;
	String name;
	private GameLocations location;
	
	public Player(GameLocations location, String name) {
		arrows = 3;
		secrets = 0;
		goldCoins = 0;
		this.location = location;
		this.name = name;
		killedWumpus = false;
	}
	
	// Returns string the player
	public String toString()
	{
		return "Player";
	}
	public void shootArrow(int x) {
		arrows--;
		if (x == location.trackWumpus()) {
			killedWumpus = true;
		}
		
		/*
		else {
			location.moveWumpus();
		}
		if 
		*/
	}

	
	public int getGoldCoins() {
		return goldCoins;
	}
	public int computeScore() {
		if (killedWumpus == false)
		{
			return 100-location.numberOfTurns()+goldCoins+arrows;
		}
		return 100-location.numberOfTurns()+goldCoins+arrows+ 50;
	}
	
	public String name() {
		return this.name;
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