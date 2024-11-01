package Function;

import DocumentManager.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchBook {

    /**
     *
     * @param sentences
     * @param input
     * @return Book has same.
     */
    public static List<String> searchByWordsInSentence(List<String> sentences, String input) {
        Set<String> keywords = extractWords(input);
        List<String> result = new ArrayList<>();

        for (String sentence : sentences) {
            if (containsAnyWord(sentence, keywords)) {
                result.add(sentence);
            }
        }
        return result;
    }
    private static Set<String> extractWords(String sentence) {
        String[] words = sentence.split("\\s+");
        Set<String> wordSet = new HashSet<>();
        for (String word : words) {
            wordSet.add(word.toLowerCase());
        }
        return wordSet;
    }
    private static boolean containsAnyWord(String sentence, Set<String> keywords) {
        String[] words = sentence.split("\\s+");
        for (String word : words) {
            if (keywords.contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
