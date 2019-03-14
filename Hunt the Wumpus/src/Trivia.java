import java.util.*;
//Deepayan Sanyal pd. 4 Group Big Brother
//Object Trivia, asks the Player Trivia Questions
	// 3.5.18 - First Assignment: Class Header + Constructor + toString
public class Trivia {
	
	private static ArrayList<String> questions;
	private static ArrayList<String> correctAnswers;
	private static ArrayList<String> incorrectAnswersOne;
	private static ArrayList<String> incorrectAnswersTwo;
	
	private int coinCount;
	
	public Trivia() {
		
	}
	
	//This method returns the value of the coinCount field
	public int getCoinCount() {
		return 0;
	}
	
	//This method sets the value of the coinCount field. The parameter is an integer which the coinCount field is set to.
	public void setCoinCount(int value) {
		
	}
	
	//This method increments the value of the coinCount field. The parameter is the integer by which coinCount is incremented.
	public void incrementCoinCount(int value) {
		
	}
	
	//This method returns a question from the question set at random as a String.
	public String getQuestion() {
		return "";
	}
	
	//When a question is answered, this method is called. The parameter is a boolean that is true if the answer is correct
	//This method changes this object internally, like removing the question from the set of questions
	public void answerQuestion(boolean isCorrect) {
		
	}
	
	public String toString() {
		return "Trivia";
	}
	
}