package com.example.demo.repository;

import com.example.demo.dao.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface UploadFileRepository extends Serializable,JpaRepository<UploadFile,String>,
        JpaSpecificationExecutor<UploadFile> {


}
