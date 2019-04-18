import java.util.*;
import java.io.*;
//Deepayan Sanyal pd. 4 Group Big Brother
//Object HighScore, keeps track of player's high score
	// 3.5.18 - First Assignment: Class Header + Constructor + toString
public class HighScore {

	private int[] highScores;
	private String[] highScorePlayers;
	
	private static final String highScoresPath = "./input/HighScoresFile.txt";
	private static final String highScorePlayersPath = "./input/HighScorePlayersFile.txt";
	
	private File highScoresFile;	
	private File highScorePlayersFile;

	public HighScore() {

		highScoresFile = new File(highScoresPath);
		highScorePlayersFile = new File(highScorePlayersPath);
		
		retrieveHighScores();
		retrievePlayers();		
	}
	
	//Returns the top 10 High Scores as an array of integers and retrieves it from the text file
	private int[] retrieveHighScores() {
		int counter = 0;

		try {
			highScoresFile.createNewFile();
		} catch (IOException e1) {
			
		}
		
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
	
	//Writes High Score values and Player values. Returns true if successful and false if unsuccessful
	private boolean writeHighScoreToFile() {
		highScoresFile.delete();
		
		try {
			highScoresFile.createNewFile();
		} catch (IOException e1) {
			return false;
		}
		
		FileWriter writerScore;
		PrintWriter printerScore;
		try {
			writerScore = new FileWriter(highScoresPath, false);
			printerScore = new PrintWriter(writerScore);
			
			for(int score : highScores) {
				printerScore.println(score);
			}
			
			writerScore.close();
			printerScore.close();
			
		} catch (IOException e) {
			return false;
		}

		try {
			highScorePlayersFile.createNewFile();
		} catch (IOException e1) {
			return false;
		}
		
		FileWriter writerPlayer;
		PrintWriter printerPlayer;
		try {
			writerPlayer = new FileWriter(highScorePlayersPath, false);
			printerPlayer = new PrintWriter(writerPlayer);
			
			for(String player : highScorePlayers) {
				printerPlayer.println(player);
			}
			
			writerPlayer.close();
			printerPlayer.close();
			
		} catch (IOException e) {
			return false;
		}
		
		return true;
		
	}
	
	// Adds the high score and player to file if they are in the top 10
	// Returns an int from 0-9 if the player is in the top 10, representing the ranking
	// Returns -1 if the score is not in the top 10
	public int addScore(int score, String player) {
		
		int[] highScoresTemp = new int[highScores.length + 1];
		for(int i = 0; i < highScores.length; i++) {
			highScoresTemp[i] = highScores[i];
		}
		
		String[] highScorePlayersTemp = new String[highScorePlayers.length + 1];
		for(int i = 0; i < highScorePlayers.length; i++) {
			highScorePlayersTemp[i] = highScorePlayers[i];
		}
	
		for(int i = 0; i < highScoresTemp.length - 1; i++) {
			
			if(highScoresTemp[i] < score) {
				
				for(int j = highScoresTemp.length - 1; j > i; j--) {
					highScoresTemp[j] = highScoresTemp[j - 1];
					highScorePlayersTemp[j] = highScorePlayersTemp[j - 1];
				}
				
				highScoresTemp[i] = score;
				highScorePlayersTemp[i] = player;
				
				if(highScoresTemp.length <= 10) {
					
					highScores = highScoresTemp;
					highScorePlayers = highScorePlayersTemp;
					
					writeHighScoreToFile();
					
					return i;
					
				} else {
					
					for(int k = 0; k < highScores.length; k++) {
						
						highScores[k] = highScoresTemp[k];
						highScorePlayers[k] = highScorePlayersTemp[k];
						
						writeHighScoreToFile();
						
						return i;
						
					}
					
				}
					
			}
			
		}
		
		if(highScoresTemp.length > 10)
			return -1;
		
		highScores = highScoresTemp;
		highScores[highScores.length - 1] = score;
		highScorePlayers = highScorePlayersTemp;
		highScorePlayers[highScorePlayers.length - 1] = player;
		
		writeHighScoreToFile();
			return highScores.length - 1;
	
	}
	
	public String toString() {
		return "HighScore";
	}
	
}