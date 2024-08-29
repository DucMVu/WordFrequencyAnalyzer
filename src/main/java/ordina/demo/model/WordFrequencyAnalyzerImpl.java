package ordina.demo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer {
    private static final Pattern WORD_PATTERN = Pattern.compile("[a-zA-Z]+", Pattern.CASE_INSENSITIVE);

    private Map<String, Integer> getWordCounts(String text) {
        Map<String, Integer> freq = new HashMap<>();
        Matcher matcher = WORD_PATTERN.matcher(text);
        while (matcher.find()) {
            String word = matcher.group().toLowerCase();
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }
        return freq;
    }

    @Override
    public int calculateHighestFrequency(String text) {
        return getWordCounts(text).values().stream().max(Integer::compareTo).orElse(0);
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        if (word == null) return 0;
        return getWordCounts(text).getOrDefault(word.toLowerCase(), 0);
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int limit) {
        Map<String, Integer> freq = getWordCounts(text);
        return freq.entrySet().stream()
            .sorted((e1, e2) -> {
                int cmp = Integer.compare(e2.getValue(), e1.getValue());
                if (cmp == 0) return e1.getKey().compareTo(e2.getKey());
                return cmp;
            })
            .limit(limit)
            .map(e -> new WordFrequencyImpl(e.getKey(), e.getValue()))
            .collect(Collectors.toList());
    }
}
