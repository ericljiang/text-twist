import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Game {
	public HashSet<String> allWords;
	public HashSet<String> sixWords;
	public String myWord;
	public HashSet<String> subWords;
	
	public Game() {
		allWords = new HashSet<String>();
		sixWords = new HashSet<String>();
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
		
		// Create a set of all subwords
		subWords = getSubWords();
		
		// Prompt player
		prompt();
	}
	
	public void prompt() {
		// Print jumbled word
		
		// Print words so far
		
		// Get input
		
		// Check if valid word
	}
	
	public HashSet<String> getSubWords() {
		HashSet<String> result = new HashSet<String>();
		return result;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
