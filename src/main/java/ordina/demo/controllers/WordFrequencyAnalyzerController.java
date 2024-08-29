package ordina.demo.controllers;

import ordina.demo.model.WordFrequency;
import ordina.demo.model.WordFrequencyAnalyzerImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WordFrequencyAnalyzerController {

    private final WordFrequencyAnalyzerImpl wordFrequencyAnalyzer = new WordFrequencyAnalyzerImpl();

    @GetMapping("/test")
    public String test() {
        return "backend started";
    }

    @GetMapping("/highestFrequency")
    public int calculateHighestFrequency(String text) {
        return wordFrequencyAnalyzer.calculateHighestFrequency(text);
    }

    @GetMapping("/wordFrequency")
    public int calculateFrequencyForWord(String text, String word) {
        return wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
    }

    @GetMapping("/mostFrequentWords")
    public List<WordFrequency> calculateMostFrequentNWords(String text, int limit) {
        return wordFrequencyAnalyzer.calculateMostFrequentNWords(text, limit);
    }
}
