package com.interview.code.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.regex.Pattern;

@Service
public class StringAnalyser {

    HashMap<Integer, Integer> getMap(String lineOfFile) {
        HashMap<Integer, Integer> wordsAndLengthMap = new HashMap<>();
        String[] strings = regexForSingleLineSplit(lineOfFile);
        for (String s : strings) {
            if (!s.isBlank()) {
                if (!wordsAndLengthMap.containsKey(s.length())) {
                    wordsAndLengthMap.put(s.length(), 1);
                } else {
                    Integer nextValue = wordsAndLengthMap.get(s.length()) + 1;
                    wordsAndLengthMap.replace(s.length(), nextValue);
                }
            }
        }
        return wordsAndLengthMap;
    }

    public String[] regexForSingleLineSplit(String lineOfFile) {
        Pattern pattern = Pattern.compile("[^A-Za-z0-9/&]");
        return lineOfFile.split(pattern.pattern());
    }
}
