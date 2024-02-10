package com.save.images.FileSystem.service;

import com.save.images.FileSystem.entity.FileData;
import com.save.images.FileSystem.repository.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Component
public class StorageService {

    @Autowired
    private FileDataRepository fileDataRepository;

    private final String FOLDER_PATH = "E:\\FileSystem\\fileStorage\\";

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH+file.getOriginalFilename();

        FileData fileData = fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath)
                .build()
        );

        file.transferTo(new File(filePath));

        if(fileData != null){
            return "File uploaded successfully : "+ filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem (String fileName) throws IOException {
        FileData fileData = fileDataRepository.findByName(fileName);
        String filePath = fileData.getFilePath();
        byte[] image = Files.readAllBytes(new File(filePath).toPath());
        return image;
    }

}
