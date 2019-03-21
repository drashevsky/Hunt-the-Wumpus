import java.util.*;
import java.io.*;
//Deepayan Sanyal pd. 4 Group Big Brother
//Object Trivia, asks the Player Trivia Questions
	// 3.5.18 - First Assignment: Class Header + Constructor + toString
public class Trivia {
	
	private ArrayList<String> questions;
	private ArrayList<String> correctAnswers;
	private ArrayList<String> incorrectAnswersOne;
	private ArrayList<String> incorrectAnswersTwo;
	
	private int coinCount = 0;
	private int currentQuestionIndex = -1;
	
	//Four File Parameters are required in this order: questions, correctAnswers, incorrectAnswersOne, incorrectAnswersTwo
	//Each line of the file represents the a single question or answer
	public Trivia(File questions, File correctAnswers, File incorrectAnswersOne, File incorrectAnswersTwo) throws FileNotFoundException {
		
		this.questions = new ArrayList<String>();
		this.correctAnswers = new ArrayList<String>();
		this.incorrectAnswersOne = new ArrayList<String>();
		this.incorrectAnswersTwo = new ArrayList<String>();
		
		Scanner questionsScanner = new Scanner(questions);
		Scanner correctAnswersScanner = new Scanner(correctAnswers);
		Scanner incorrectAnswersOneScanner = new Scanner(incorrectAnswersOne);
		Scanner incorrectAnswersTwoScanner = new Scanner(incorrectAnswersTwo);
		
		while(questionsScanner.hasNextLine()) {
			this.questions.add(questionsScanner.nextLine());
		}
		questionsScanner.close();
		
		while(correctAnswersScanner.hasNextLine()) {
			this.questions.add(correctAnswersScanner.nextLine());
		}
		correctAnswersScanner.close();
		
		while(incorrectAnswersOneScanner.hasNextLine()) {
			this.questions.add(incorrectAnswersOneScanner.nextLine());
		}
		incorrectAnswersOneScanner.close();
		
		while(incorrectAnswersTwoScanner.hasNextLine()) {
			this.questions.add(incorrectAnswersTwoScanner.nextLine());
		}
		incorrectAnswersTwoScanner.close();
		
	}
	
	//Generates object with default set of questions and answers
	public Trivia() {
		
		this.questions = new ArrayList<String>();
		this.correctAnswers = new ArrayList<String>();
		this.incorrectAnswersOne = new ArrayList<String>();
		this.incorrectAnswersTwo = new ArrayList<String>();
		
		questions.add("Did you remember to add File Parameters?");
		correctAnswers.add("No");
		incorrectAnswersOne.add("Yes");
		incorrectAnswersTwo.add("You don't need them");
		
	}
				
	//This method returns the value of the coinCount field
	public int getCoinCount() {
		return coinCount;
	}
	
	//This method sets the value of the coinCount field. The parameter is an integer which the coinCount field is set to.
	public void setCoinCount(int value) {
		coinCount = value;
	}
	
	//This method increments the value of the coinCount field. The parameter is the integer by which coinCount is incremented.
	public void incrementCoinCount(int value) {
		coinCount += value;
	}
	
	//This method returns a question from the question set at random as a String.
	public String getQuestion() {
		return questions.get((int)(questions.size()*Math.random()));
	}
	
	//When a question is answered, this method is called. The parameter is a boolean that is true if the answer is correct
	//This method changes this object internally, like removing the question from the set of questions
	public void answerQuestion(boolean isCorrect) {
		
	}
	
	public String toString() {
		return "Trivia";
	}
	
}