package com.backend.ecommercebackend.service.impl;

import com.backend.ecommercebackend.service.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path rootLocation= Paths.get("uploads");

    public FileStorageServiceImpl() {
        try{
            Files.createDirectories(rootLocation);
        } catch(IOException e){
            throw new RuntimeException("Could not create upload directory!",e);
        }
    }

    @Override
    public String storeFile(MultipartFile file) throws IOException {
        String fileName= UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
        Path destinationFile=this.rootLocation.resolve(fileName);
        try(var inputStream=file.getInputStream()){
            Files.copy(inputStream,destinationFile);
        }
        return fileName;
    }

    @Override
    public Path loadFile(String fileName) {
        return rootLocation.resolve(fileName);
    }

    @Override
    public void deleteFile(String fileName) throws IOException {
        Path file=loadFile(fileName);
        Files.deleteIfExists(file);
    }
}
