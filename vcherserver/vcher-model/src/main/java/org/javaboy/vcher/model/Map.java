package org.javaboy.vcher.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Map {
    /*记录ID*/
    Integer id;
    /*记录者ID*/
    Integer hrId;
    /*记录者*/
    String hrName;
    /*备注*/
    String remark;
    /*地图缩放等级*/
    Integer zoom;
    /*中心点纬度*/
    String lat;
    /*中心点经度*/
    String lng;
    /*地图覆盖层数据*/
    String data;
    /*添加日期*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    LocalDateTime insertTime;
    /*修改日期*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")

    LocalDateTime updateTime;
    /*是否被删除*/
    Boolean isDeleted;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public Map() {
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Map{" +
                "id=" + id +
                ", hrId=" + hrId +
                ", hrName='" + hrName + '\'' +
                ", remark='" + remark + '\'' +
                ", zoom=" + zoom +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", data='" + data + '\'' +
                ", insertTime=" + insertTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public Map(Integer id, Integer hrId, String hrName, String remark, Integer zoom, String lat, String lng, String data, LocalDateTime insertTime, LocalDateTime updateTime, Boolean isDeleted) {
        this.id = id;
        this.hrId = hrId;
        this.hrName = hrName;
        this.remark = remark;
        this.zoom = zoom;
        this.lat = lat;
        this.lng = lng;
        this.data = data;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
        this.isDeleted = isDeleted;
    }
}
