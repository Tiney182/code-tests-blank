package com.interview.code.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ResultsSolver {
    public int solution(String[] T, String[] R) {

        String[] strippedStrings = stripLeadingString(T);
        Integer amountOfAnswers = checkHowManyAnswers(T);
        Integer totalCorrectAnswers = 0;
        for (int currentTestNumber = 0; currentTestNumber < amountOfAnswers; currentTestNumber++) {
            totalCorrectAnswers = totalCorrectAnswers + checkCurrentTestNumberAgainstResults(currentTestNumber, strippedStrings, R);
        }
        int score = totalCorrectAnswers * 100 / amountOfAnswers;
        BigDecimal roundedScore = new BigDecimal(score).setScale(0, RoundingMode.DOWN);
        return roundedScore.intValue();
    }

    private int checkCurrentTestNumberAgainstResults(int currentTestNumber, String[] strippedStrings, String[] results) {
        int answer = 0;
        for (int i = 0; i < strippedStrings.length; i++) {
            // current test case  is not part of the values so can be ignored
            if (checkCurrentNumber(strippedStrings[i]) == currentTestNumber ) {
                if(results[i].equals("OK")) {
                    answer = answer + 1;
                } else {
                    answer = answer - 1;
                }
            }
        }
        if (answer > 1) {
            answer = 1;
        } else if(answer < 0) {
            answer = 0;
        }
        return answer;
    }

    public String[] stripLeadingString(String[] T) {
        List<String> strippedStrings = new ArrayList<>();

        for (String t : T) {
            char[] chars = t.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (Character.isDigit(chars[i])) {
                    strippedStrings.add(t.substring(i));
                }
            }
        }
        return strippedStrings.toArray(String[]::new);
    }

    public int checkHowManyAnswers(String[] stringArray) {
        List<Integer> answers = new ArrayList<>();
        for (String s : stringArray) {
            char[] chars = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    sb.append(c);
                }
            }
            answers.add(Integer.valueOf(sb.toString()));
        }
        Collections.sort(answers);
        return answers.get(answers.size() - 1);
    }

    public int checkCurrentNumber(String currentValue) {
        char[] chars = currentValue.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                sb.append(chars[i]);
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
