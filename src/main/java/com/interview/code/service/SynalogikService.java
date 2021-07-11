package com.interview.code.service;

import com.interview.code.domain.SynalogikResponse;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SynalogikService {

    final FileReader fileReader;
    final StringAnalyser stringAnalyser;

    public SynalogikService(FileReader fileReader, StringAnalyser stringAnalyser) {
        this.fileReader = fileReader;
        this.stringAnalyser = stringAnalyser;
    }

    SynalogikResponse buildResponseForResource(Resource resource) throws IOException {
        List<String> fileContents = fileReader.getFileContents(resource);
        HashMap<Integer, Integer> map = new HashMap<>();
        fileContents.stream()
                .map(stringAnalyser::getMap)
                .forEach(lineMap -> mergeHashMaps(map, lineMap));
        return SynalogikResponse.builder()
                .wordCount(getWordCount(map))
                .lengthOfWordAndAmount(map)
                .averageWordLength(getAverageWordLength(map))
                .build();
    }

    private void mergeHashMaps(HashMap<Integer, Integer> map, HashMap<Integer, Integer> lineMap) {
        for (Map.Entry<Integer, Integer> entry : lineMap.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (!map.containsKey(key)) {
                map.put(key, value);
            } else {
                map.replace(key, map.get(key) + value);
            }
        }
    }

    private Integer getWordCount(HashMap<Integer, Integer> map) {
        return map.values().stream().mapToInt(i -> i).sum();
    }

    private BigDecimal getAverageWordLength(HashMap<Integer, Integer> map) {
        long totalWordLength = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int i = entry.getKey() * entry.getValue();
            totalWordLength = totalWordLength + i;
        }
        BigDecimal wordCountBD = BigDecimal.valueOf(getWordCount(map));
        BigDecimal totalWordLengthBD = BigDecimal.valueOf(totalWordLength);
        return totalWordLengthBD.divide(wordCountBD, 3, RoundingMode.HALF_UP);
    }
}
