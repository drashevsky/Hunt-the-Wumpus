import java.util.*;
import java.io.*;
//Deepayan Sanyal pd. 4 Group Big Brother
//Object HighScore, keeps track of player's high score
	// 3.5.18 - First Assignment: Class Header + Constructor + toString
public class HighScore {

	private int[] highScores;
	private String[] highScorePlayers;
	private File highScoresFile;	
	private File highScorePlayersFile;

	public HighScore() {

		highScoresFile = new File("HighScoresFile.txt");

		retrieveHighScores();
		retrievePlayers();		
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
	
	//Returns the top 10 High Scored Players as an array of String and retrieves it from the text file
	private String[] retrievePlayers() {
		int counter = 0;

		Scanner textCounter;
		try {
			textCounter = new Scanner(highScorePlayersFile);
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
			textReader = new Scanner(highScorePlayersFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		highScorePlayers = new String[counter];

		for(int i = 0; i < counter; i++){

			highScorePlayers[i] = textReader.nextLine();

		}

		textReader.close();

		return highScorePlayers;

	}

	//Returns High Scores as an array
	public int[] getHighScores(){
		return highScores;
	}
	
	//Returns High Scored Players as an array
	public String[] getHighScorePlayers() {
		return highScorePlayers;
	}
	
	//Returns the High Score at the given index. The integer parameter represents the index of the High Score.
	public int getHighScore(int index) {
		return highScores[index];
	}
	
	//Returns the Player at the given index as a String. The integer parameter represents the index of the Player
	public String getHighScorePlayer(int index) {
		return highScorePlayers[index];
	}
	
	//Adds the score given as an integer parameter to the highScores array at the appropriate positions.
	//Returns an boolean representing whether the score is a High Score
	private boolean addScore(int score, String player) {
		return false;
	}
	
	public String toString() {
		return "HighScore";
	}
	
}