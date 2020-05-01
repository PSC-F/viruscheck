package com.snut.viruscheck.entity.dto;

import javax.persistence.Column;
import java.util.Objects;

public class tableEntity {

    private String xh;
    private String state;
    private String name;

    @Override
    public String toString() {
        return "tableEntity{" +
                "xh='" + xh + '\'' +
                ", state='" + state + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        tableEntity that = (tableEntity) o;
        return Objects.equals(xh, that.xh) &&
                Objects.equals(state, that.state) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xh, state, name);
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public tableEntity(String xh, String state, String name) {
        this.xh = xh;
        this.state = state;
        this.name = name;
    }
}