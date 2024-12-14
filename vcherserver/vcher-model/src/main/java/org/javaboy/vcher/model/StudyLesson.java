package org.javaboy.vcher.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class StudyLesson {
    /*主键ID*/
    Integer id;
    /*描述*/
    String desp;
    /*总数量*/
    Integer total;
    /*完成数量*/
    Integer finished;
    /*链接*/
    String link;
    /*添加者编号*/
    Integer hrId;
    /*添加者*/
    String hr;
    /*添加时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    LocalDateTime insertTime;
    /*修改时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    LocalDateTime updateTime;

    public StudyLesson() {
    }

    public StudyLesson(Integer id, String desp, Integer total, Integer finished, String link, String hr, LocalDateTime insertTime, LocalDateTime updateTime) {
        this.id = id;
        this.desp = desp;
        this.total = total;
        this.finished = finished;
        this.link = link;
        this.hr = hr;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
    }

    public Integer getHrId() {
        return hrId;
    }

    public void setHrId(Integer hrId) {
        this.hrId = hrId;
    }

    @Override
    public String toString() {
        return "StudyLesson{" +
                "id=" + id +
                ", desp='" + desp + '\'' +
                ", total=" + total +
                ", finished=" + finished +
                ", link='" + link + '\'' +
                ", hr=" + hr +
                ", insertTime=" + insertTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
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
