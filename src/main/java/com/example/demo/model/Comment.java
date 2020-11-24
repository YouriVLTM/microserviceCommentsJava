package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.Date;

public class Comment {

    @Id
    private String id;
    private String title;
    private String subTitle;
    private String description;
    private String userEmail;
    private String imageUrl;

    @DateTimeFormat(style="yyyyMMdd'T'HHmmss.SSSZ")
    private java.util.Date createDate;

    @DateTimeFormat(style="yyyyMMdd'T'HHmmss.SSSZ")
    private java.util.Date updateDate;

    public Comment(String title, String subTitle, String description, String userEmail, String imageUrl, Date createDate) {
        this.title = title;
        this.subTitle = subTitle;
        this.description = description;
        this.userEmail = userEmail;
        this.imageUrl = imageUrl;
        this.createDate = createDate;
    }


/* public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/


    public String getKey() {
        return DigestUtils.sha256Hex(this.title + this.userEmail + this.createDate);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
