package com.snut.viruscheck.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_student", schema = "snut_virus")
public class TbStudentEntity {
    private String id;
    private String name;
    private String sex;
    private String phone;
    private String address;
    private String className;
    private Byte isDanger;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "className")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "isDanger")
    public Byte getIsDanger() {
        return isDanger;
    }

    public void setIsDanger(Byte isDanger) {
        this.isDanger = isDanger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbStudentEntity that = (TbStudentEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(address, that.address) &&
                Objects.equals(className, that.className) &&
                Objects.equals(isDanger, that.isDanger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex, phone, address, className, isDanger);
    }
}
