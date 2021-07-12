package com.interview.code.service;

import com.interview.code.domain.SynalogikResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

@SpringBootTest
class SynalogikServiceTest {

    @Autowired
    FileReader fileReader;

    @Autowired
    StringAnalyser stringAnalyser;

    @Value("classpath:smallFile.txt")
    Resource smallFile;

    @Value("classpath:bible.txt")
    Resource bible;

    @Value("classpath:exampleFile.txt")
    Resource exampleFile;

    @Test
    public void getResponseForResource_KnownSizesFilePassed_ResponseCorrect() throws IOException {
        SynalogikService synalogikService = new SynalogikService(fileReader, stringAnalyser);
        SynalogikResponse synalogikResponse = synalogikService.buildResponseForResource(smallFile);
        HashMap<Integer, Integer> hashMap = synalogikResponse.getLengthOfWordAndAmount();
        assert hashMap.get(1) == 2;
        assert hashMap.get(2) == 2;
        assert hashMap.get(3) == 2;
        assert hashMap.get(4) == 4;
        assert hashMap.get(5) == 3;
        assert hashMap.get(7) == 3;
        assert hashMap.get(10) == 2;
        assert synalogikResponse.getWordCount() == 18;
        assert synalogikResponse.getAverageWordLength().equals(BigDecimal.valueOf(4.667));
    }

    @Test
    public void getResponseForResource_ExampleFilePassed_ResponseCorrect() throws IOException {
        SynalogikService synalogikService = new SynalogikService(fileReader, stringAnalyser);
        SynalogikResponse synalogikResponse = synalogikService.buildResponseForResource(exampleFile);
        HashMap<Integer, Integer> hashMap = synalogikResponse.getLengthOfWordAndAmount();
        assert hashMap.get(1) == 1;
        assert hashMap.get(2) == 1;
        assert hashMap.get(3) == 1;
        assert hashMap.get(4) == 2;
        assert hashMap.get(5) == 2;
        assert hashMap.get(7) == 1;
        assert hashMap.get(10) == 1;
        assert synalogikResponse.getWordCount() == 9;
        assert synalogikResponse.getAverageWordLength().equals(BigDecimal.valueOf(4.556));
    }

    /**
     * I added this more out of curiosity than anything.
     * https://stackoverflow.com/questions/19486077/java-fastest-way-to-read-through-text-file-with-2-million-lines
     * Was checking read efficiency of readline from bufferedReader compared to read all lines which in my opinion is
     * more usable. as it gives you something to iterate over without a needless for each.
     */
    @Test
    public void getResponseForResource_BiblePassed_CheckHowLongMethodTakesToRun() throws IOException {
        SynalogikService synalogikService = new SynalogikService(fileReader, stringAnalyser);
        long startTime = System.nanoTime();
        synalogikService.buildResponseForResource(bible);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Time to read bible and return response was : " + totalTime / 1000000 + "ms");
    }

    @Test
    public void getMostFrequentOccurringWordAndTheirLengths_TwoWordsOfMaxLength_ReturnsStringWithAmpersand() {
        SynalogikService synalogikService = new SynalogikService(fileReader, stringAnalyser);
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 5);
        map.put(2, 5);
        String mostFrequentOccurringWordAndTheirLengths = synalogikService.getMostFrequentOccurringWordAndTheirLengths(map);
        assert mostFrequentOccurringWordAndTheirLengths.equals("The most frequently occurring word length is 5, for word lengths of 1 & 2");
    }

    @Test
    public void getMostFrequentOccurringWordAndTheirLengths_SizWordsOfMaxLength_ReturnsStringWithAmpersand() {
        SynalogikService synalogikService = new SynalogikService(fileReader, stringAnalyser);
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 5);
        map.put(2, 5);
        map.put(3, 5);
        map.put(4, 5);
        map.put(5, 5);
        map.put(6, 5);
        String mostFrequentOccurringWordAndTheirLengths = synalogikService.getMostFrequentOccurringWordAndTheirLengths(map);
        assert mostFrequentOccurringWordAndTheirLengths.equals("The most frequently occurring word length is 5, for word lengths of 1 & 2 & 3 & 4 & 5 & 6");
    }

    @Test
    public void getMostFrequentOccurringWordAndTheirLengths_SingleWordOfMaxLength_ReturnsStringSingular() {
        SynalogikService synalogikService = new SynalogikService(fileReader, stringAnalyser);
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 5);
        map.put(2, 6);
        String mostFrequentOccurringWordAndTheirLengths = synalogikService.getMostFrequentOccurringWordAndTheirLengths(map);
        assert mostFrequentOccurringWordAndTheirLengths.equals("The most frequently occurring word length is 6, for word length 2");
    }
}