package com.file.converter;

import com.file.converter.service.FileProcessor;
import com.file.converter.service.impl.FileProcessorImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("File Converter v 1.0");
        FileProcessor fileProcessor = new FileProcessorImpl();

        fileProcessor.folderFileChecker("D:\\JavaProCourse\\checkFolder",
                "D:\\JavaProCourse\\checkOutputFolder");
    }
}
