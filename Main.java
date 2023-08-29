import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\hp\\Downloads\\words_alpha.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading dictionary file: " + e.getMessage());
            return;
        }

        SpellCheckerEditDistance editDistanceChecker = new SpellCheckerEditDistance(dictionary);
        SpellCheckerNGram ngramChecker = new SpellCheckerNGram(dictionary, 2);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String inputSentence = sc.nextLine();

        String[] words = inputSentence.split("\\s+");
        for (String word : words) {
            if (!dictionary.contains(word)) {
                List<String> correctionsEditDistance = editDistanceChecker.suggestCorrections(word);
                List<String> correctionsNGram = ngramChecker.suggestCorrections(word);

                System.out.println("Misspelled Word: " + word);
                System.out.println("Edit Distance Suggestions: " + correctionsEditDistance);
                System.out.println("N-gram Suggestions: " + correctionsNGram);
                System.out.println();
            }
        }
    }
}
