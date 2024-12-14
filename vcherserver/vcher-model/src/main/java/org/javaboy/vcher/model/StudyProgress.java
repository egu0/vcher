package org.javaboy.vcher.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StudyProgress {
    /*记录ID*/
    Integer id;
    /*记录者ID*/
    Integer hrId;
    /*记录者*/
    String hrName;
    /*进度日期*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    LocalDate tDate;
    /*进度*/
    Integer tProgress;
    /*最近修改日期*/
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    LocalDateTime lastUpdateTime;
    /*备注*/
    String remark;

    public StudyProgress() {
    }

    public StudyProgress(Integer id, Integer hrId, String hrName, LocalDate tDate, Integer tProgress, LocalDateTime lastUpdateTime, String remark) {
        this.id = id;
        this.hrId = hrId;
        this.hrName = hrName;
        this.tDate = tDate;
        this.tProgress = tProgress;
        this.lastUpdateTime = lastUpdateTime;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public LocalDate gettDate() {
        return tDate;
    }

    public void settDate(LocalDate tDate) {
        this.tDate = tDate;
    }

    public Integer gettProgress() {
        return tProgress;
    }

    public void settProgress(Integer tProgress) {
        this.tProgress = tProgress;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "StudyProgress{" +
                "id=" + id +
                ", hrId=" + hrId +
                ", hrName='" + hrName + '\'' +
                ", tDate=" + tDate +
                ", tProgress=" + tProgress +
                ", lastUpdateTime=" + lastUpdateTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}
