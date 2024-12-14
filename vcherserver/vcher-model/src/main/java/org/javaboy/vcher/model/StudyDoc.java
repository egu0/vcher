package org.javaboy.vcher.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class StudyDoc {
    /*主键ID*/
    Integer id;
    /*文件名*/
    String fName;
    /*文件描述*/
    String desp;
    /*服务端文件标识*/
    String link;
    /*上传者ID*/
    Integer hrId;
    /*上传者*/
    String hrName;
    /*是否被删除*/
    Boolean isDeleted;
    /*上传时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    LocalDateTime insertTime;
    /*更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    LocalDateTime updateTime;

    public StudyDoc(Integer id, String fName, String desp,
                    String link, Integer hrId, String hrName,
                    Boolean isDeleted, LocalDateTime insertTime,
                    LocalDateTime updateTime) {
        this.id = id;
        this.fName = fName;
        this.desp = desp;
        this.link = link;
        this.hrId = hrId;
        this.hrName = hrName;
        this.isDeleted = isDeleted;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
    }

    public StudyDoc() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getHrId() {
        return hrId;
    }

    public void setHrId(Integer hrId) {
        this.hrId = hrId;
    }

    public String getHrName() {
        return hrName;
    }

    @Override
    public String toString() {
        return "StudyDoc{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", desp='" + desp + '\'' +
                ", link='" + link + '\'' +
                ", hrId=" + hrId +
                ", hrName='" + hrName + '\'' +
                ", isDeleted=" + isDeleted +
                ", insertTime=" + insertTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(LocalDateTime insertTime) {
        this.insertTime = insertTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
