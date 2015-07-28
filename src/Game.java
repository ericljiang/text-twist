import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Game {
	public HashSet<String> sixWords;
	
	public Game() {
		sixWords = new HashSet<String>();
		loadWords();
	}
	
	public void loadWords() {
		try {
			File file = new File("enable1.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				sixWords.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		System.out.println("Game started!");
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
}
