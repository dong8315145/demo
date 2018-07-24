package com.example.demo.dao.mapper;

import com.example.demo.dao.UploadFile;

public interface UploadFileMapper {
    int deleteByPrimaryKey(String id);

    int insert(UploadFile record);

    int insertSelective(UploadFile record);

    UploadFile selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UploadFile record);

    int updateByPrimaryKey(UploadFile record);
}