package org.javaboy.vcher.model;
/*
系统消息
 */
public class SysMsg {
    private Integer id;

    private Integer mid;//消息id

    private Integer type;//0-群发消息，1-非群发消息

    private Integer hrid;//收件人

    private Integer state;//0-未读，1-已读

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHrid() {
        return hrid;
    }

    public void setHrid(Integer hrid) {
        this.hrid = hrid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}