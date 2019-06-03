/**
 * @author Shrey Srivastava
 * Class Name: Player
 * Description: Manages the player's state
 * Rev. History: (Date - Revision)
 * 
 * 4/03/2019 - [DANIEL] Fixed formatting
 * 5/28/2019 - [DEEPAYAN] Added several methods
 */

public class Player {
	int arrows;
	int secrets;
	int goldCoins;
	boolean killedWumpus;
	String name;
	Cave c;
	Wumpus w;
	private GameLocations location;
	
	public Player(GameLocations location, String name, Cave C, Wumpus W) {
		arrows = 3;
		secrets = 0;
		goldCoins = 0;
		this.location = location;
		this.name = name;
		killedWumpus = false;
		w = W;
		c = C;
	}
	
	// Returns string the player
	public String toString()
	{
		return name;
	}
	// hello
	public boolean shootArrow(int x) {
		
		if (arrows == 0) {
			System.out.print("You can't shoot no more");
			return false;
		}
		else if (x == location.getWumpus().track()) {
			arrows--;
			killedWumpus = true;
			System.out.println("Hit!");
			return true;
		}
		else {
			arrows--;
			location.getWumpus().move();
			System.out.println("Missed");
			return false;
		}
	}
	public boolean wumpusState() {
		return killedWumpus;
	}
	public int getGoldCoins() {
		return goldCoins;
	}
	
	//Increments goldCoins by num
	public void incrementGoldCoins(int num) {
		goldCoins += num;
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getArrows() {
		return arrows;
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
	
	//Purchases arrows
	public void purchaseArrows() {
		this.arrows += 2;
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
	
	public void purchaseSecrets() {
		this.secrets++;
	}
	
}