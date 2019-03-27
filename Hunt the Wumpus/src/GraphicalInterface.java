import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
public class GraphicalInterface implements ActionListener {
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
		JFrame f = new JFrame("Menu Demo");
		JButton bu = new JButton();
		JButton bu2 = new JButton();
		
	    f.setSize(500,500);
	    
	    bu.setSize(50,50);
	    bu.setText("Play");
	    bu.setVisible(true);
	    f.add(bu);


	    
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JMenuBar jmb = new JMenuBar();
	    
	    JMenu jmFile = new JMenu("File");
	    JMenuItem jmiOpen = new JMenuItem("Open");
	    JMenuItem jmiClose = new JMenuItem("Close");
	    JMenuItem jmiSave = new JMenuItem("Save");
	    JMenuItem jmiExit = new JMenuItem("Exit");
	    jmFile.add(jmiOpen);
	    jmFile.add(jmiClose);
	    jmFile.add(jmiSave);
	    jmFile.addSeparator();
	    jmFile.add(jmiExit);
	    jmb.add(jmFile);

	    JMenu jmOptions = new JMenu("Options");
	    JMenu a = new JMenu("A");
	    JMenuItem b = new JMenuItem("B");
	    JMenuItem c = new JMenuItem("C");
	    JMenuItem d = new JMenuItem("D");
	    a.add(b);
	    a.add(c);
	    a.add(d);
	    jmOptions.add(a);

	    JMenu e = new JMenu("E");
	    e.add(new JMenuItem("F"));
	    e.add(new JMenuItem("G"));
	    jmOptions.add(e);

	    jmb.add(jmOptions);

	    JMenu jmHelp = new JMenu("Help");
	    JMenuItem jmiAbout = new JMenuItem("About");
	    jmHelp.add(jmiAbout);
	    jmb.add(jmHelp);

	    jmiOpen.addActionListener(this);
	    jmiClose.addActionListener(this);
	    jmiSave.addActionListener(this);
	    jmiExit.addActionListener(this);
	    b.addActionListener(this);
	    c.addActionListener(this);
	    d.addActionListener(this);
	    jmiAbout.addActionListener(this);

	    f.setJMenuBar(jmb);
	    f.setVisible(true);
	}
	
	//toString
	public String toString()
	{
		return "Graphical Interface";
	}

	//display the Player score (Parameter - score)
	public void displayScore(int score)
	{
		System.out.print(score);
	}
	
	//display the Player inventory (parameter - items in an array)
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
	//takes possible actions as a String parameter
	public void displayOptions(String actions)
	{
		System.out.print(actions);
	}
	 public void actionPerformed(ActionEvent ae) {
		    String comStr = ae.getActionCommand();
		    System.out.println(comStr + " Selected");
		  }
	 public static void main(String args[]) {
		    new GraphicalInterface();
		  }
	

}
