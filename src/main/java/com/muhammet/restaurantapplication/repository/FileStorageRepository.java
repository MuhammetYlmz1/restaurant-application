package com.muhammet.restaurantapplication.repository;

import com.muhammet.restaurantapplication.model.entity.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {

    FileStorage findByFileName(String fileName);

}
