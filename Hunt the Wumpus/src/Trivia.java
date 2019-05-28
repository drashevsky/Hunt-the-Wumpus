import java.util.*;
import java.io.*;
//Deepayan Sanyal pd. 4 Group Big Brother
//Object Trivia, asks the Player Trivia Questions
	// 3.5.18 - First Assignment: Class Header + Constructor + toString
public class Trivia {
	
	private static final String triviaPath = "./input/Trivia.txt";
	private static File triviaFile;
	
	//each element is an array of 4 Strings
	//each element in String represents [Question, CorrectAnswer, OtherAnswer, OtherAnswer]
	private ArrayList<String[]> trivia;
	
	private String[] currentTrivia;
	private String[] displayedAnswers;
	
	//Generates object using questions and answers from the question file
	public Trivia() {
		
		triviaFile = new File(triviaPath);
		
		retrieveTrivia();
		
	}
	
	//Retrieves trivia questions from file and stores them in "trivia"
	private void retrieveTrivia() {
		
		Scanner reader;
		
		try {
			reader = new Scanner(triviaFile);
		} catch(FileNotFoundException e){
			e.printStackTrace();
			return;
		}
		
		trivia = new ArrayList<String[]>();
		
		while(reader.hasNextLine()) {
			
			String[] data = new String[4];
			
			data[0] = reader.nextLine();
			
			reader.nextLine();
			
			for(int i = 1; i <= 3; i++) {
				data[i] = reader.nextLine();
			}
			
			if(reader.hasNextLine()) {
				reader.nextLine();
			}
			
			trivia.add(data);
			
		}
		
		reader.close();
		
	}
	
	//Starts a Round of Trivia
	//Parameters are the Number of Correct Answers to Pass and Number of Questions Asked
	public boolean startTrivia(int correctAnswersToPass, int questionsTotal) {
		
		int asked = 0;
		int correct = 0;
		
		while(asked < questionsTotal && correct < correctAnswersToPass /* && gameControl.getPlayer().getGoldCoins() */ ) {
			
			getTrivia();
			
			displayedAnswers = new String[3];
			
			for(int i = 0; i < displayedAnswers.length; i++) {
				displayedAnswers[i] = currentTrivia[i+1];
			}
			
			shuffle(displayedAnswers);
			
			displayTrivia(currentTrivia[0], displayedAnswers);

			int answer = getAnswer();
			boolean isCorrect = isAnswerCorrect(answer);
			displayCorrect(isCorrect);
			
			asked++;
			
			if(isCorrect) {
				correct++;
			}
			
		}
		
		if(correct >= correctAnswersToPass) {
			
			//player.endTrivia(true);
			System.out.println();
			System.out.println("You passed the trivia");
			return true;
			
		} else {
			
			//player.endTrivia(false);
			System.out.println();
			System.out.println("You failed the trivia");
			return false;
			
		}
		
	}
	
	private void getTrivia() {
		currentTrivia = trivia.get(( (int) (Math.random() * trivia.size()) ));
		trivia.remove(currentTrivia);
	}
	
	private void displayTrivia(String question, String[] answers) {
		
		System.out.println();
		System.out.println(question);
		System.out.println();
		System.out.println("a) " + answers[0]);
		System.out.println("b) " + answers[1]);
		System.out.println("c) " + answers[2]);
		
	}
	
	private void displayCorrect(boolean isCorrect) {
		
		System.out.println();
		if(isCorrect) {
			System.out.println("Correct!");
		} else {
			System.out.println("Incorrect!");
		}
		
	}
	
	public int getAnswer() {
		Scanner input = new Scanner(System.in);
		
		String line = input.next();
		
		if(line.equals("a")) {
			return 0;
		} else if(line.equals("b")) {
			return 1;
		} else if(line.equals("c")) {
			return 2;
		} else {
			return -1;
		}
		
	}
	
	public boolean isAnswerCorrect(int index) {
		
		if(index < 0 || index > 2) {
			return false;
		}
		
		if(displayedAnswers[index].equals(currentTrivia[1])) {
			return true;
		} else {
			return false;
		}
		
	}
	
	private void shuffle(String[] array) {
		
		Random random = new Random();
		
		for(int i = array.length-1; i > 0; i--) {
			
			int index = random.nextInt(i + 1);
			String placeholder = array[index];
			array[index] = array[i];
			array[i] = placeholder;
		}
		
	}
	
	public String toString() {
		return "Trivia";
	}
	
}