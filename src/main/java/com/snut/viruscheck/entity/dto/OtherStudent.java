package com.snut.viruscheck.entity.dto;

import java.util.Objects;

public class OtherStudent {
    String name;
    String id;

    public OtherStudent() {
    }

    @Override
    public String toString() {
        return "OtherStudent{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OtherStudent that = (OtherStudent) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OtherStudent(String name, String id) {
        this.name = name;
        this.id = id;
    }


}

