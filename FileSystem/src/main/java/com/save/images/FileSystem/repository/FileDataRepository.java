package com.save.images.FileSystem.repository;

import com.save.images.FileSystem.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData,Integer> {
    FileData findByName(String name);
}
