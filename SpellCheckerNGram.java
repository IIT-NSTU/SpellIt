import java.util.*;
public class SpellCheckerNGram {
    private Set<String> dictionary;
    private int n;
    public SpellCheckerNGram(Set<String> dictionary, int n) {
        this.dictionary = dictionary;
        this.n = n;
    }

    public List<String> suggestCorrections(String word) {
        List<String> suggestions = new ArrayList<>();
        double maxSimilarity = 0.0;

        Set<String> wordNgrams = generateNgrams(word);

        for (String dictWord : dictionary) {
            Set<String> dictNgrams = generateNgrams(dictWord);
            double similarity = calculateJaccardSimilarity(wordNgrams, dictNgrams);

            if (similarity > maxSimilarity) {
                suggestions.clear();
                suggestions.add(dictWord);
                maxSimilarity = similarity;
            } else if (similarity == maxSimilarity) {
                suggestions.add(dictWord);
            }
        }

        return suggestions;
    }

    private Set<String> generateNgrams(String word) {
        Set<String> ngrams = new HashSet<>();

        for (int i = 0; i <= word.length() - n; i++) {
            String ngram = word.substring(i, i + n);
            ngrams.add(ngram);
        }
        return ngrams;
    }
    private double calculateJaccardSimilarity(Set<String> ngrams1, Set<String> ngrams2) {
        Set<String> intersection = new HashSet<>(ngrams1);
        intersection.retainAll(ngrams2);

        Set<String> union = new HashSet<>(ngrams1);
        union.addAll(ngrams2);

        return (double) intersection.size() / union.size();
    }


}
