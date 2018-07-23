package com.example.demo.service.impl;

import com.example.demo.dao.UploadFile;
import com.example.demo.repository.UploadFileRepository;
import com.example.demo.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: demo
 * @description:
 * @author: Terdy.Zheng
 * @create: 2018-07-23 15:34
 **/
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    UploadFileRepository uploadFileRepository;

    @Override
    public UploadFile getFileById(String fileId) {
        return uploadFileRepository.getOne(fileId);
    }
}
