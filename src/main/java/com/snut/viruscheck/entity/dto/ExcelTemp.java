package com.snut.viruscheck.entity.dto;

import java.util.Objects;

public class ExcelTemp {
    //    "序号", "专业班级", "学号", "姓名", "性别", "上午体温", "下午体温", "测量地点", "异常状态简要说明", "报告人联系电话", "备注"
    private String className;//专业班级
    private String studentId;//学号
    private String name;//姓名
    private String sex;//性别
    private String tempAm;//上午体温
    private String tempPm;//下午体温
    private String address;//测量地点
    private String des;//描述
    private String phone;//手机

    public ExcelTemp() {
    }

    public ExcelTemp(String className, String studentId, String name, String sex, String tempAm, String tempPm, String address, String des, String phone) {
        this.className = className;
        this.studentId = studentId;
        this.name = name;
        this.sex = sex;
        this.tempAm = tempAm;
        this.tempPm = tempPm;
        this.address = address;
        this.des = des;
        this.phone = phone;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTempAm() {
        return tempAm;
    }

    public void setTempAm(String tempAm) {
        this.tempAm = tempAm;
    }

    public String getTempPm() {
        return tempPm;
    }

    public void setTempPm(String tempPm) {
        this.tempPm = tempPm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExcelTemp excelTemp = (ExcelTemp) o;
        return Objects.equals(className, excelTemp.className) &&
                Objects.equals(studentId, excelTemp.studentId) &&
                Objects.equals(name, excelTemp.name) &&
                Objects.equals(sex, excelTemp.sex) &&
                Objects.equals(tempAm, excelTemp.tempAm) &&
                Objects.equals(tempPm, excelTemp.tempPm) &&
                Objects.equals(address, excelTemp.address) &&
                Objects.equals(des, excelTemp.des) &&
                Objects.equals(phone, excelTemp.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, studentId, name, sex, tempAm, tempPm, address, des, phone);
    }

    @Override
    public String toString() {
        return "ExcelTemp{" +
                "className='" + className + '\'' +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tempAm='" + tempAm + '\'' +
                ", tempPm='" + tempPm + '\'' +
                ", address='" + address + '\'' +
                ", des='" + des + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
