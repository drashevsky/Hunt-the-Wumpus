import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GraphicalInterface extends JFrame implements ActionListener {
	/* Name: V. Raj Ajmera
	 * Class Name: Graphical Interface
	 * Object: Displaying state of the current game
	 * Revision History: 3/5/19 - Introduction
	 */
	
	//no fields as of right now due to only displaying
	//and getting resources from other classes
	
	//constructor

    static final String gapList[] = {"0", "10", "15", "20"};
    final static int maxGap = 20;
    JComboBox horGapComboBox;
    JComboBox verGapComboBox;
    JButton applyButton = new JButton("Apply gaps");
    GridLayout experimentLayout = new GridLayout(0,2);


	public GraphicalInterface(String name)
	{
        super(name);
        setResizable(false);
	}
	
	 public void initGaps() {
	        horGapComboBox = new JComboBox(gapList);
	        verGapComboBox = new JComboBox(gapList);
	    }
	     
	    public void addComponentsToPane(final Container pane) {
	        initGaps();
	        final JPanel compsToExperiment = new JPanel();
	        compsToExperiment.setLayout(experimentLayout);
	        JPanel controls = new JPanel();
	        controls.setLayout(new GridLayout(2,3));
	         
	        //Set up components preferred size
	        JButton b = new JButton("Play");
	        Dimension buttonSize = b.getPreferredSize();
	        compsToExperiment.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,
	                (int)(buttonSize.getHeight() * 3.5)+maxGap * 2));
	         
	        //Add buttons to experiment with Grid Layout
	        compsToExperiment.add(b);
	        compsToExperiment.add(new JButton("Settings"));
	        compsToExperiment.add(new JButton("About"));
	        compsToExperiment.add(new JButton("High Score++"));
	        compsToExperiment.add(new JButton("Print Room"));
	        compsToExperiment.add(new JButton("High Scores"));
	         
	        //Add controls to set up horizontal and vertical gaps
	        controls.add(new Label("Horizontal gap:"));
	        controls.add(new Label("Vertical gap:"));
	        controls.add(new Label(" "));
	        controls.add(horGapComboBox);
	        controls.add(verGapComboBox);
	        controls.add(applyButton);
	         
	        //Process the Apply gaps button press
	        
	        b.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	                //Get the horizontal gap value
	            	System.out.println("1");
	        
	                String horGap = (String)horGapComboBox.getSelectedItem();
	                //Get the vertical gap value
	                String verGap = (String)verGapComboBox.getSelectedItem();
	                //Set up the horizontal gap value
	                experimentLayout.setHgap(Integer.parseInt(horGap));
	                //Set up the vertical gap value
	                experimentLayout.setVgap(Integer.parseInt(verGap));
	                //Set up the layout of the buttons
	                experimentLayout.layoutContainer(compsToExperiment);
	            }
	        });
	        pane.add(compsToExperiment, BorderLayout.NORTH);
	        pane.add(new JSeparator(), BorderLayout.CENTER);
	        pane.add(controls, BorderLayout.SOUTH);
	    }
	     
	    /**
	     * Create the GUI and show it.  For thread safety,
	     * this method is invoked from the
	     * event dispatch thread.
	     */
	    private static void createAndShowGUI() {
	        //Create and set up the window.
	        GraphicalInterface frame = new GraphicalInterface("GUI");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //Set up the content pane.
	        frame.addComponentsToPane(frame.getContentPane());
	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }
	     
	    public static void main(String[] args) {
	        /* Use an appropriate Look and Feel */
	        try {
	            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
	        } catch (UnsupportedLookAndFeelException ex) {
	            ex.printStackTrace();
	        } catch (IllegalAccessException ex) {
	            ex.printStackTrace();
	        } catch (InstantiationException ex) {
	            ex.printStackTrace();
	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        }
	        /* Turn off metal's use of bold fonts */
	        UIManager.put("swing.boldMetal", Boolean.FALSE);
	         
	        //Schedule a job for the event dispatch thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
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

}
