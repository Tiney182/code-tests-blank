package com.interview.code.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

@Service
public class FileReader {
    public List<String> getFileContents(Resource resource) throws IOException {
        File file = resource.getFile();
        return Files.readAllLines(file.toPath());
    }
}
