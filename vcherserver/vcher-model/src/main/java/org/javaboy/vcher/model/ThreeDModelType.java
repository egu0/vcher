package org.javaboy.vcher.model;

public class ThreeDModelType {
    Integer id;
    String type;
    String desp;

    public ThreeDModelType() {
    }

    @Override
    public String toString() {
        return "ThreeDModelType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", desp='" + desp + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public ThreeDModelType(Integer id, String type, String desp) {
        this.id = id;
        this.type = type;
        this.desp = desp;
    }
}
