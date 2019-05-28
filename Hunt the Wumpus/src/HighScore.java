import java.util.*;
import java.io.*;
//Deepayan Sanyal pd. 4 Group Big Brother
//Object HighScore, keeps track of player's high score
	// 3.5.18 - First Assignment: Class Header + Constructor + toString
public class HighScore {

	private int[] highScores;
	private String[] highScorePlayers;
	private String[] mapNames;
	
	private static final String highScoresPath = "./input/HighScoresFile.txt";
	private static final String highScorePlayersPath = "./input/HighScorePlayersFile.txt";
	private static final String mapNamesPath = "./input/HighScoreMapsFile.txt";
	
	private File highScoresFile;	
	private File highScorePlayersFile;
	private File mapNamesFile;

	public HighScore() {

		highScoresFile = new File(highScoresPath);
		highScorePlayersFile = new File(highScorePlayersPath);
		mapNamesFile = new File(mapNamesPath);
		
		retrieveHighScores();
		retrievePlayers();
		retrieveMapNames();
		
	}
	
	//Returns the top 10 High Scores as an array of integers and retrieves it from the text file
	private int[] retrieveHighScores() {
		
		return retrieveArray(highScoresFile, highScores);
		
	}
	
	//Retrieves an array of Strings from file and stores it in array
	private String[] retrieveArray(File file, String[] array) {
		
		int counter = 0;

		Scanner textCounter;
		try {
			textCounter = new Scanner(file);
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
			textReader = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		array = new String[counter];

		for(int i = 0; i < counter; i++){

			array[i] = textReader.nextLine();

		}

		textReader.close();

		return array;
		
	}
	
	//Retrieves an array of ints from file and stores it in array	
	private int[] retrieveArray(File file, int[] array) {
		
		int counter = 0;

		Scanner textCounter;
		try {
			textCounter = new Scanner(file);
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
			textReader = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		array = new int[counter];

		for(int i = 0; i < counter; i++){

			array[i] = textReader.nextInt();

		}

		textReader.close();

		return array;
		
	}
	
	//Returns the top 10 High Scored Players as an array of String and retrieves it from the text file
	private String[] retrievePlayers() {
		
		return retrieveArray(highScorePlayersFile, highScorePlayers);
		
	}

	//Returns the maps of the top 10 Highest Scored Players as an array of String and retrieves it from the text file
	private String[] retrieveMapNames() {
		
		return retrieveArray(mapNamesFile, mapNames);
		
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
		
		FileWriter writerMapNames;
		PrintWriter printerMapNames;
		try {
			writerMapNames = new FileWriter(mapNamesPath, false);
			printerMapNames = new PrintWriter(writerMapNames);
			
			for(String mapName : mapNames) {
				printerMapNames.println(mapName);
			}
			
			writerMapNames.close();
			printerMapNames.close();
			
		} catch (IOException e) {
			return false;
		}
		
		return true;
		
	}
	
	// Adds the high score and player to file if they are in the top 10
	// Returns an int from 0-9 if the player is in the top 10, representing the ranking
	// Returns -1 if the score is not in the top 10
	public int addScore(int score, String player, String mapName) {
		
		int[] highScoresTemp = new int[highScores.length + 1];
		for(int i = 0; i < highScores.length; i++) {
			highScoresTemp[i] = highScores[i];
		}
		
		String[] highScorePlayersTemp = new String[highScorePlayers.length + 1];
		for(int i = 0; i < highScorePlayers.length; i++) {
			highScorePlayersTemp[i] = highScorePlayers[i];
		}
		
		String[] mapNamesTemp = new String[mapNames.length + 1];
		for(int i = 0; i < mapNames.length; i++) {
			mapNamesTemp[i] = mapNames[i];
		}
	
		for(int i = 0; i < highScoresTemp.length - 1; i++) {
			
			if(highScoresTemp[i] < score) {
				
				for(int j = highScoresTemp.length - 1; j > i; j--) {
					highScoresTemp[j] = highScoresTemp[j - 1];
					highScorePlayersTemp[j] = highScorePlayersTemp[j - 1];
					mapNamesTemp[j] = mapNamesTemp[j - 1];
				}
				
				highScoresTemp[i] = score;
				highScorePlayersTemp[i] = player;
				mapNamesTemp[i] = mapName;
				
				
				if(highScoresTemp.length <= 10) {
					
					highScores = highScoresTemp;
					highScorePlayers = highScorePlayersTemp;
					mapNames = mapNamesTemp;
					
					writeHighScoreToFile();
					
					return i;
					
				} else {
					
					for(int k = 0; k < highScores.length; k++) {
						
						highScores[k] = highScoresTemp[k];
						highScorePlayers[k] = highScorePlayersTemp[k];
						mapNames[k] = mapNamesTemp[k];
						
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
		mapNames = mapNamesTemp;
		mapNames[mapNames.length - 1] = mapName;
		
		writeHighScoreToFile();
			return highScores.length - 1;
	
	}
	
	public String toString() {
		return "HighScore";
	}
	
}