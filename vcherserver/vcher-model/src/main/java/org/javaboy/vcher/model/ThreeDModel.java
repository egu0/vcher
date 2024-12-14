package org.javaboy.vcher.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ThreeDModel {
    Integer id;
    /*类型*/
    Integer type;
    String typeName;
    String typeDesp;
    /*模型元数据*/
    String desp;
    String src;
    String mtl;
    String dirName;
    /*添加or修改*/
    Integer hrId;
    String hrName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    LocalDateTime insertTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    LocalDateTime updateTime;
    /*逻辑删除*/
    Integer isDeleted;

    public ThreeDModel() {
    }

    public ThreeDModel(Integer id, Integer type, String typeName, String typeDesp, String desp, String src, String mtl, String dirName, Integer hrId, String hrName, LocalDateTime insertTime, LocalDateTime updateTime, Integer isDeleted) {
        this.id = id;
        this.type = type;
        this.typeName = typeName;
        this.typeDesp = typeDesp;
        this.desp = desp;
        this.src = src;
        this.mtl = mtl;
        this.dirName = dirName;
        this.hrId = hrId;
        this.hrName = hrName;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDesp() {
        return typeDesp;
    }

    public void setTypeDesp(String typeDesp) {
        this.typeDesp = typeDesp;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getMtl() {
        return mtl;
    }

    public void setMtl(String mtl) {
        this.mtl = mtl;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "ThreeDModel{" +
                "id=" + id +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", typeDesp='" + typeDesp + '\'' +
                ", desp='" + desp + '\'' +
                ", src='" + src + '\'' +
                ", mtl='" + mtl + '\'' +
                ", dirName='" + dirName + '\'' +
                ", hrId=" + hrId +
                ", hrName='" + hrName + '\'' +
                ", insertTime=" + insertTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
