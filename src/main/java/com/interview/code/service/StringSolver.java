package com.interview.code.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class StringSolver {

    public boolean checkString(String inputString) {
        String lowerCased = inputString.toLowerCase();

        char[] chars = lowerCased.toCharArray();

        boolean validString = true;
        String previousValue = "";

        if(chars.length == 1 && String.valueOf(chars[0]).equals("b")) {
            return true;
        }

        for (char aChar : chars) {
            String currentChar = String.valueOf(aChar);
            if (currentChar.equals("a") && previousValue.equals("b")) {
                validString = false;
            }
            previousValue = currentChar;
        }
        return validString;
    }

}
