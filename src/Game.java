import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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
	Scanner in = new Scanner(System.in);
	
	public boolean debug;
	
	public Game() {
		this(false);
	}
	
	public Game(boolean deb) {
		allWords = new HashSet<String>();
		sixWords = new HashSet<String>();
		myGuesses = new HashSet<String>();
		random = new Random();
		debug = deb;
		loadWords();
	}
	
	public void loadWords() {
		try {
			File file = new File("35.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				allWords.add(line);
				if (line.length() == 6 && !line.contains("\'")) sixWords.add(line);
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
		
		// Print debug info
		if (debug) {
			System.out.println("*DEBUG INFO*");
			System.out.println("Word: \n" + myWord);
			System.out.println("Subwords:");
			for (String s : subWords) {
				System.out.println(s);
			}
			System.out.println("============");
		}
		
		// Prompt player on loop
		while (myGuesses.size() < subWords.size()) {
			prompt();
		}
	}
	
	public void prompt() {
		// Print words so far
		System.out.println("Guesses so far:");
		//myGuesses.forEach(System.out::println);
		printGuesses();

		// Print jumbled word
		System.out.println("Jumbled word:");
		System.out.println(jumble(myWord));

		// Get input
		System.out.print("> ");
		String guess = in.nextLine();
		
		// Check if valid word
		if (subWords.contains(guess)) {
			System.out.println("Valid");
			myGuesses.add(guess);
		} else {
			System.out.println("Not valid");
		}
		
		System.out.println("-------------");
	}
	
	public void printGuesses() {
		for (String sub : subWords) {
			if (myGuesses.contains(sub)) {
				System.out.println(sub);
			} else {
				for (int i = 0; i < sub.length(); i++) {
					System.out.print("_");
				}
				System.out.print("\n");
			}
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
		for (String subword : allWords) {
			if (isValid(subword, myWord) && subword.length() > 2) {
				result.add(subword);
			}
		}
		return result;
	}
	
	public boolean isValid(String subword, String word) {
		// Make map of subword
		HashMap<Character, Integer> subMap = getCharMap(subword);
		HashMap<Character, Integer> wordMap = getCharMap(word);
		
		for (char c : subMap.keySet()) {
			if (!wordMap.containsKey(c)) {
				return false;
			} else if (subMap.get(c) > wordMap.get(c)) {
				return false;
			}
		}
		
		return true;
	}
	
	public HashMap<Character, Integer> getCharMap(String word) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		char current;
		for (int i = 0; i < word.length(); i++) {
			current = word.charAt(i);
			if (!map.containsKey(current)) map.put(current, 0);
			map.put(current, map.get(current) + 1);			
		}
		return map;
	}
	
	public static void main(String[] args) {
		Game game = new Game(true);
		game.start();
	}
}
