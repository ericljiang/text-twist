import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Game {
	public HashSet<String> allWords;
	public HashSet<String> sixWords;
	
	public String myWord;
	public HashSet<String> subWords;
	public HashSet<String> myGuesses;
	
	Random random;
	
	public Game() {
		allWords = new HashSet<String>();
		sixWords = new HashSet<String>();
		myGuesses = new HashSet<String>();
		random = new Random();
		loadWords();
	}
	
	public void loadWords() {
		try {
			File file = new File("enable1.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				allWords.add(line);
				if (line.length() == 6) sixWords.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		// Pick a random 6 letter word
		String[] array = sixWords.toArray(new String[0]);
		myWord = array[random.nextInt(array.length)];
		
		// Create a set of all subwords
		subWords = getSubWords();
		
		// Prompt player on loop
		while (myGuesses.size() < subWords.size()) {
			prompt();
		}
	}
	
	public void prompt() {
		System.out.println(myWord);
		// Print jumbled word
		System.out.println("Jumbled word:");
		System.out.println(jumble(myWord));
		
		// Print words so far
		System.out.println("Guesses so far:");
		myGuesses.forEach(System.out::println);
		
		// Get input
		Scanner in = new Scanner(System.in);
		System.out.print("> ");
		String guess = in.nextLine();
		
		// Check if valid word
		if (subWords.contains(guess)) {
			System.out.println("Valid");
			myGuesses.add(guess);
		} else {
			System.out.println("Not valid");
		}
	}
	
	public String jumble(String word) {
		String result = "";
		HashSet<Integer> used = new HashSet<Integer>();
		while (result.length() < word.length()) {
			int index = random.nextInt(word.length());
			if (!used.contains(index)) {
				result += word.charAt(index);
				used.add(index);
			}
		}
		return result;
	}
	
	public HashSet<String> getSubWords() {
		HashSet<String> result = new HashSet<String>();
		for (String word : allWords) {
			if (myWord.toLowerCase().contains(word.toLowerCase()) && word.length() > 2) {
				result.add(word);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
