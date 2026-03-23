package com.file.converter.service.impl;

import com.file.converter.service.FileProcessor;
import com.file.converter.service.constans.Extension;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

public class FileProcessorImpl implements FileProcessor {
    @Override
    public String readFromFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    @Override
    public void writeToFile(String folder, String fileName, String newExtension, String content) throws IOException {
        if(!Paths.get(folder).toFile().exists()) {
            Paths.get(folder).toFile().mkdir();
        }

        String fileOutput = folder.concat(File.separator).concat(changeExtension(fileName, newExtension));
        Files.writeString(Path.of(fileOutput), content);
    }

    private String changeExtension(String fileName, String newExtension) {
        int dotIndex = fileName.lastIndexOf('.');
        return ((dotIndex == 1) ? fileName : fileName.substring(0, dotIndex)) + "." + newExtension;
    }

    @Override
    public boolean isProcessedFile(Path filePath) {
        if(Files.exists(filePath) && Files.isRegularFile(filePath)) {
            String fileName = filePath.getFileName().toString();
            int extensionIndex = fileName.lastIndexOf('.');
            String extension = fileName.substring(extensionIndex + 1);
            return Arrays.stream(Extension.values()).toList().contains(Extension.valueOf(extension.toUpperCase()));
        }
        return false;
    }

    @Override
    public void deleteFile(String filePath) throws IOException {
        Files.delete(Path.of(filePath));
    }

    @Override
    public void folderFileChecker(String folderInputPath, String folderOutputPath) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService(); // класс который отслеживает папку
        Path inputFolder = Paths.get(folderInputPath);

        inputFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE); // регистрируем наш вотч сервис и ивент по которому он будет работать

        WatchKey key;
        while((key = watchService.take()) != null) {
            for(WatchEvent<?> event : key.pollEvents()) { // pollEvents() будет опрашивать нашу папку на наличие чего-то нового
                Path p = (Path) event.context();
                String filePath = folderInputPath + File.separator + p;
                System.out.println(Path.of(filePath));

                if(isProcessedFile(Path.of(filePath))) {
                    System.out.printf("File path %s is already processed%n", filePath);
                    String contentIn = readFromFile(filePath);

                    
                }
            }
            key.reset();
        }
    }
}
