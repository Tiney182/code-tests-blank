package com.interview.code.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class FileReaderTest {

    @Autowired
    private FileReader fileReader;

    @Value("classpath:bible.txt")
    private Resource resource;

    @Value("classpath:thisDoesntExist.txt")
    private Resource invalidResource;

    @Test
    public void getFileContents_ValidFilePassed_StringArrayReturned() throws IOException {
        List<String> readFile = fileReader.getFileContents(resource);
        assert !readFile.isEmpty();
    }

    @Test
    public void getFileContents_InvalidPathPassed_IOExceptionThrown() {
        Assertions.assertThrows(IOException.class, () -> fileReader.getFileContents(invalidResource));
    }
}