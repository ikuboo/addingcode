package com.ikuboo.serialize.utils;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/1 0001.
 * 简单的实体
 */
public class SimpleEntity implements Serializable {
    private Integer finteger;
    private String fstring;
    private String fstringnull;
    private int fint;
    private float ffloat;
    private double fdouble;
    private Boolean fboolean;
    private Long flong;

    public Integer getFinteger() {
        return finteger;
    }

    public void setFinteger(Integer finteger) {
        this.finteger = finteger;
    }

    public String getFstring() {
        return fstring;
    }

    public void setFstring(String fstring) {
        this.fstring = fstring;
    }

    public int getFint() {
        return fint;
    }

    public void setFint(int fint) {
        this.fint = fint;
    }

    public float getFfloat() {
        return ffloat;
    }

    public void setFfloat(float ffloat) {
        this.ffloat = ffloat;
    }

    public double getFdouble() {
        return fdouble;
    }

    public void setFdouble(double fdouble) {
        this.fdouble = fdouble;
    }

    public Boolean getFboolean() {
        return fboolean;
    }

    public void setFboolean(Boolean fboolean) {
        this.fboolean = fboolean;
    }

    public Long getFlong() {
        return flong;
    }

    public void setFlong(Long flong) {
        this.flong = flong;
    }

    public String getFstringnull() {
        return fstringnull;
    }

    public void setFstringnull(String fstringnull) {
        this.fstringnull = fstringnull;
    }
}
