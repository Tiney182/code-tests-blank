package com.interview.code.controller;

import com.interview.code.domain.SynalogikResponse;
import com.interview.code.service.SynalogikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SynalogikController {

    @Value("classpath:bible.txt")
    Resource bibleResource;

    @Value("classpath:exampleFile.txt")
    Resource exampleFileResource;

    @Autowired
    SynalogikService synalogikService;

    @GetMapping("/exampleFile")
    public List<String> generateSynalogikStringResponseForSmallFile() throws IOException {
        return generateStringResponseFromResource(exampleFileResource);
    }

    @GetMapping("/bible")
    public List<String> generateSynalogikStringResponseForBible() throws IOException {
        return generateStringResponseFromResource(bibleResource);
    }

    private List<String> generateStringResponseFromResource(Resource resource) throws IOException {
        List<String> response = new ArrayList<>();

        SynalogikResponse synalogikResponse = synalogikService.buildResponseForResource(resource);

        response.add("Word count = " + synalogikResponse.getWordCount());
        response.add("Average word length = " + synalogikResponse.getAverageWordLength());
        for (Map.Entry<Integer, Integer> entry : synalogikResponse.getLengthOfWordAndAmount().entrySet()) {
            response.add("Number of words of length " + entry.getKey() + " is " + entry.getValue());
        }
        response.add(synalogikService.getMostFrequentOccurringWordAndTheirLengths(synalogikResponse.getLengthOfWordAndAmount()));
        return response;
    }
}
