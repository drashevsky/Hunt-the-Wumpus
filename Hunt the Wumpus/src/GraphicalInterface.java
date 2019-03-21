
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
	
	public static void drawCanvas()
	{

	    final String title = "Test Window";
	    final int width = 1200;
	    final int height = width / 16 * 9;

	    //Creating the frame.
	    JFrame frame = new JFrame(title);

	    frame.setSize(width, height);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.setVisible(true);

	    //Creating the canvas.
	    Canvas canvas = new Canvas();

	    canvas.setSize(width, height);
	    canvas.setBackground(Color.BLACK);
	    canvas.setVisible(true);
	    canvas.setFocusable(false);


	    //Putting it all together.
	    frame.add(canvas);

	    canvas.createBufferStrategy(3);

	    boolean running = true;

	    BufferStrategy bufferStrategy;
	    Graphics graphics;

	    while (running) {
	        bufferStrategy = canvas.getBufferStrategy();
	        graphics = bufferStrategy.getDrawGraphics();
	        graphics.clearRect(0, 0, width, height);

	        graphics.setColor(Color.GREEN);
	        graphics.drawString("This is some text placed in the top left corner.", 5, 15);

	        bufferStrategy.show();
	        graphics.dispose();
	    }
	}

}
