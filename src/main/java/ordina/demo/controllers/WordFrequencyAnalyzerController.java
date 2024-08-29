package ordina.demo.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class WordFrequencyAnalyzerController {

    @GetMapping("/test")
    public String test() {
        return "backend started";
    }

    @GetMapping("/highestFrequency")
    public int getCalculateHighestFrequency(@RequestParam("text") String text) {
        return calculateHighestFrequency(text);
    }

    public int calculateHighestFrequency(String text) {
        String[] words = processWords(text);

        int maxFrequency = 0;

        for (int i = 0; i < words.length; i++) {
            String temp = words[i];
            int count = 1;
            for (int j = i + 1; j < words.length; j++) {
                if (temp.equalsIgnoreCase(words[j])) {
                    count++;
                }
            }

            if (maxFrequency < count) {
                maxFrequency = count;
            }
        }

        return maxFrequency;
    }

    @GetMapping("/wordFrequency")
    public int calculateFrequencyForWord (@RequestParam("text") String text, @RequestParam("word") String word) {
        String[] words = processWords(text);

        int count = 0;

        for (String s : words) {
            if (word.equals(s)) {
                count++;
            }
        }

        return count;
    };

    @GetMapping("/mostFrequentWords")
    public List<Map<String, Integer>> calculateMostFrequentNWords(@RequestParam("text") String text, @RequestParam("limit") int limit) {
        String[] words = processWords(text);

        Map<String, Integer> map = new HashMap<>();
        for (String s : words) {
            Integer count = map.get(s);
            count = (count == null) ? 1 : ++count;
            map.put(s, count);
        }

        List<Map<String, Integer>> wordsList = getFrequentNWords(map, limit);

        return wordsList;
    }

    private List<Map<String, Integer>> getFrequentNWords(Map<String, Integer> wordFrequencyMap, int limit) {
        int maxFrequency = 0;
        for (Integer value : wordFrequencyMap.values()) {
            if (value > maxFrequency)
                maxFrequency = value;
        }

        List<Map<String, Integer>>[] wordList = new List[maxFrequency];
        for (Map.Entry<String, Integer> entry : wordFrequencyMap.entrySet()) {
            int index = entry.getValue() - 1;

            if (wordList[index] == null) {
                wordList[index] = new LinkedList<>();
            }
            HashMap<String, Integer> entryMap = new HashMap<>();
            entryMap.put(entry.getKey(), entry.getValue());
            wordList[index].add(entryMap);
        }

        List<Map<String, Integer>> words = new LinkedList<>();

        for (int i = wordList.length - 1; i >= 0 && limit > 0; i--) {
            if (wordList[i] != null) {
                for (Map<String, Integer> map : wordList[i]) {
                    words.add(map);
                    limit--;
                }
            }
        }

        return words;
    }

    private String[] processWords(String text) {
        text = text.replaceAll("[^'a-zA-Z\\s]+", "");

        String[] words = text.trim().toLowerCase().split("\\s+");

        return words;
    }
}
