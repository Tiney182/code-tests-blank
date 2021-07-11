package com.interview.code.service;

import org.junit.jupiter.api.Test;

import java.util.HashMap;


class StringAnalyserTest {

    StringAnalyser stringAnalyser = new StringAnalyser();

    @Test
    public void getMap_validInputLine_returnsHashMapForLine() {
        HashMap<Integer, Integer> map = stringAnalyser.getMap("Hello world & good morning. The date is 18/05/2016");
        assert map.get(1) == 1;
        assert map.get(2) == 1;
        assert map.get(3) == 1;
        assert map.get(4) == 2;
        assert map.get(5) == 2;
        assert map.get(7) == 1;
        assert map.get(10) == 1;
    }
}