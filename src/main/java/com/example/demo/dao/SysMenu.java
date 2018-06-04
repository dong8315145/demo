package com.example.demo.dao;

import java.io.Serializable;
import java.util.Date;

public class SysMenu implements Serializable {
    private String id;

    private String text;

    private String pid;

    private String url;

    private String custom;

    private String createChild;

    private String flag;

    private String description;

    private String editUser;

    private Date editTime;

    private String image;

    private String showOrder;

    private String template;

    private String detialTemplate;

    private String descriptionEn;

    private String textEn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom == null ? null : custom.trim();
    }

    public String getCreateChild() {
        return createChild;
    }

    public void setCreateChild(String createChild) {
        this.createChild = createChild == null ? null : createChild.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getEditUser() {
        return editUser;
    }

    public void setEditUser(String editUser) {
        this.editUser = editUser == null ? null : editUser.trim();
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(String showOrder) {
        this.showOrder = showOrder == null ? null : showOrder.trim();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    public String getDetialTemplate() {
        return detialTemplate;
    }

    public void setDetialTemplate(String detialTemplate) {
        this.detialTemplate = detialTemplate == null ? null : detialTemplate.trim();
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn == null ? null : descriptionEn.trim();
    }

    public String getTextEn() {
        return textEn;
    }

    public void setTextEn(String textEn) {
        this.textEn = textEn == null ? null : textEn.trim();
    }
}