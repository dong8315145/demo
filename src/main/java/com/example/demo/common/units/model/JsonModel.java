package com.example.demo.common.units.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class JsonModel {
    private boolean success;

    private Object root;

    @XmlElement
    public Object getRoot() {
        return root;
    }

    public void setRoot(Object root) {
        this.root = root;
    }

    @XmlElement
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public JsonModel() {
    }

    public JsonModel(boolean success) {
        this.success = success;
    }

    public JsonModel(Object root) {
        this.root = root;
    }

    public JsonModel(boolean success, Object root) {
        this.success = success;
        this.root = root;
    }
}
