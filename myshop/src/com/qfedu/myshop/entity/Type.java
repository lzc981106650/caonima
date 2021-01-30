package com.qfedu.myshop.entity;

import java.io.Serializable;

/**
 * 商品类型
 */
public class Type implements Serializable {

    /**
     * 商品类型id
     */
    private int tid;

    /**
     * 商品类型 名
     */
    private String tname;

    /**
     * 商品类型 介绍
     */
    private String tInfo;


    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String gettInfo() {
        return tInfo;
    }

    public void settInfo(String tInfo) {
        this.tInfo = tInfo;
    }

    @Override
    public String toString() {
        return "Type{}";
    }
}
