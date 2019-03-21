
public class GraphicalInterface {
	/* Name: V. Raj Ajmera
	 * Class Name: Graphical Interface
	 * Object: Displaying state of the current game
	 * Revision History: 3/5/19 - Introduction
	 */
	
	//no fields as of right now due to only displaying
	//and getting resources from other classes
	
	//constructor
	public GraphicalInterface()
	{
		
	}
	
	//toString
	public String toString()
	{
		return "Graphical Interface";
	}
	
	//draw cave- taking a param of the starting room
	public void drawCave(int room)
	{
		System.out.print(room);
	}
	
	//display the Player score (param - score)
	public void displayScore(int score)
	{
		System.out.print(score);
	}
	
	//display the Player inventory (param - items in an array)
	public void displayInventory(String[] inventory)
	{
		for(String s: inventory)
		{
			System.out.print(s + " ");
		}
	}
	
	//display hint (requires hint from trivia)
	public void displayHint(String hint)
	{
		System.out.print(hint);
	}
	
	//display the actions a Player can take on their turn
	//takes possible actions as a String param
	public void displayOptions(String actions)
	{
		System.out.print(actions);
	}
	

}
