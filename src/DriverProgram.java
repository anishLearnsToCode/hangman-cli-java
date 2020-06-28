import java.util.Scanner;

public class DriverProgram {
    private static final int ATTEMPTS = 7;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        HangmanGame game = new HangmanGame(ATTEMPTS);
        while (!game.isOver()) {
            System.out.println("\nGuessed Alphabets: " + game.wordFormedSoFar());
            System.out.println("Attempts Remaining: " + game.attemptsRemaining());
            System.out.println("Consonants Present in word: " + game.getConsonantsPresentInWord());
            System.out.println("Consonants Not Present in word: " + game.getConsonantsNotPresentInWord());
            System.out.print("Guess an alphabet: \t");
            char guess = SCANNER.next().charAt(0);
            game.makeGuess(guess);
        }

        System.out.println(game.won()
                    ? "Congratulations!! You have guessed the word correctly"
                    : "Better luck next time :(");
        System.out.println("The word was : " + game.getWord());
    }
}
