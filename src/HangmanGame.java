import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HangmanGame {
    private final Set<Character> VOWELS = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    private final String WORD = RandomWordGenerator.getRandomWord();
    private final Set<Character> CHARS_IN_WORD;
    private final Set<Character> consonantsPresentInWord = new HashSet<>();
    private final Set<Character> consonantsNotPresentInWord = new HashSet<>();
    private final StringBuilder wordFormedSoFar;
    private int attempts;

    HangmanGame(int attempts) {
        this.attempts = attempts;
        CHARS_IN_WORD = alphabetsInWord();
        wordFormedSoFar = generateWordKnownSoFar();
    }

    private StringBuilder generateWordKnownSoFar() {
        StringBuilder result = new StringBuilder();
        for (int index = 0 ; index < WORD.length() ; index++) {
            result.append(isVowel(WORD.charAt(index)) ? WORD.charAt(index) : '_');
        }
        return result;
    }

    private Set<Character> alphabetsInWord() {
        Set<Character> alphabets = new HashSet<>();
        for (int index = 0 ; index < WORD.length() ; index++) {
            alphabets.add(WORD.charAt(index));
        }
        return alphabets;
    }

    public boolean isOver() {
        return lost() || won();
    }

    public Set<Character> getConsonantsPresentInWord() {
        return consonantsPresentInWord;
    }

    public Set<Character> getConsonantsNotPresentInWord() {
        return consonantsNotPresentInWord;
    }

    public void makeGuess(char alphabet) {
        if (isVowel(alphabet) || consonantsPresentInWord.contains(alphabet)
                || consonantsNotPresentInWord.contains(alphabet)) {
            return;
        }

        if (CHARS_IN_WORD.contains(alphabet)) {
            consonantsPresentInWord.add(alphabet);
            updateWordFormedSoFar(alphabet);
        } else {
            consonantsNotPresentInWord.add(alphabet);
            attempts--;
        }
    }

    private void updateWordFormedSoFar(char alphabet) {
        for (int index = 0 ; index < wordFormedSoFar.length() ; index++) {
            if (wordFormedSoFar.charAt(index) == '_' && alphabet == WORD.charAt(index)) {
                wordFormedSoFar.setCharAt(index, alphabet);
            }
        }
    }

    private boolean isVowel(char alphabet) {
        return VOWELS.contains(alphabet);
    }

    public String wordFormedSoFar() {
        return wordFormedSoFar.toString();
    }

    public int attemptsRemaining() {
        return attempts;
    }

    public boolean won() {
        return WORD.equals(wordFormedSoFar.toString());
    }

    public boolean lost() {
        return attempts <= 0;
    }

    public String getWord() {
        if (isOver()) {
            return WORD;
        }
        return null;
    }
}
