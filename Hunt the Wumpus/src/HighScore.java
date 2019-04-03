import java.util.*;
import java.io.*;
//Deepayan Sanyal pd. 4 Group Big Brother
//Object HighScore, keeps track of player's high score
	// 3.5.18 - First Assignment: Class Header + Constructor + toString
public class HighScore {

	private int[] highScores;
	private File highScoresFile;	

	public HighScore() {

		highScoresFile = new File("HighScoresFile.txt");

		retrieveHighScores();
		
	}
	
	//Returns the top 10 High Scores as an array of integers and retrieves it from the text file
	private int[] retrieveHighScores() {
		int counter = 0;

		Scanner textCounter;
		try {
			textCounter = new Scanner(highScoresFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		while(textCounter.hasNextLine()){

			counter++;
			textCounter.nextLine();

		}

		textCounter.close();
		
		Scanner textReader;
		try {
			textReader = new Scanner(highScoresFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		highScores = new int[counter];

		for(int i = 0; i < counter; i++){

			highScores[i] = textReader.nextInt();		

		}

		textReader.close();

		return highScores;

	}

	//Returns High Scores as an array
	public int[] getHighScores(){
		return highScores;
	}
	
	//Returns the High Score at the given index. The integer paramter represents the index of the High Score.
	private int getHighScore(int index) {
<<<<<<< HEAD
		return highScores[index];
=======
		highScores[index];
>>>>>>> master
	}
	
	//Adds the score given as an integer parameter to the highScores array at the appropriate positions.
	//Returns an boolean representing whether the score is a High Score
	private boolean addScore(int score) {
		return false;
	}
	
	public String toString() {
		return "HighScore";
	}
	
}