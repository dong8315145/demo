package com.example.demo.common.units.model;

import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

public class FileModel {
    private String fileName;

    private Object source;

    public FileModel() {
    }

    public FileModel(Object source) {
        this.source = source;
    }

    public FileModel(String fileName, Object source) {
        this.fileName = fileName;
        this.source = source;
    }

    public String getFileName() throws Exception {
        String returnFileName = "";
        if (StringUtils.isNotEmpty(this.fileName)) {
            returnFileName = URLEncoder.encode(this.fileName, "utf-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
            if (returnFileName.length() > 150) {
                returnFileName = new String(this.fileName.getBytes("GBK"),
                        "ISO8859-1");
                returnFileName = StringUtils
                        .replace(returnFileName, " ", "%20");
            }
        }
        return returnFileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
