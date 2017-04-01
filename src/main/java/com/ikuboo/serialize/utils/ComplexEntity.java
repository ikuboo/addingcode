package com.ikuboo.serialize.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yuanchunsen on 2017/4/1.
 * 复杂的对象
 */
public class ComplexEntity implements Serializable {

    private SimpleEntity fSimpleEntity;

    private Map<String, SimpleEntity> fmap;
    private Set<SimpleEntity> fset;
    private List<SimpleEntity> flist;

    private Map<Object, Object> fobjectmap;

    public SimpleEntity getfSimpleEntity() {
        return fSimpleEntity;
    }

    public void setfSimpleEntity(SimpleEntity fSimpleEntity) {
        this.fSimpleEntity = fSimpleEntity;
    }

    public Map<String, SimpleEntity> getFmap() {
        return fmap;
    }

    public void setFmap(Map<String, SimpleEntity> fmap) {
        this.fmap = fmap;
    }

    public Set<SimpleEntity> getFset() {
        return fset;
    }

    public void setFset(Set<SimpleEntity> fset) {
        this.fset = fset;
    }

    public List<SimpleEntity> getFlist() {
        return flist;
    }

    public void setFlist(List<SimpleEntity> flist) {
        this.flist = flist;
    }

    public Map<Object, Object> getFobjectmap() {
        return fobjectmap;
    }

    public void setFobjectmap(Map<Object, Object> fobjectmap) {
        this.fobjectmap = fobjectmap;
    }
}
