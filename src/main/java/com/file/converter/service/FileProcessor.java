package com.file.converter.service;

import java.io.IOException;
import java.nio.file.Path;

public interface FileProcessor {
    String readFromFile(String filePath) throws IOException;

    void writeToFile(String folder, String fileName, String newExtension, String content) throws IOException;

    boolean isProcessedFile(Path fileInputPath);

    void deleteFile(String filePath) throws IOException;

    void folderFileChecker(String folderInputPath, String fileOutputPath) throws IOException, InterruptedException;
}
