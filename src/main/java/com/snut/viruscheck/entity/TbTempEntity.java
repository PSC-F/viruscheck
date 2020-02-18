package com.snut.viruscheck.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_temp", schema = "snut_virus")
public class TbTempEntity {
    private int id;
    private String tempAm;
    private String tempPm;
    private String des;
    private Date sysDate;
    private String studentId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tempAM")
    public String getTempAm() {
        return tempAm;
    }

    public void setTempAm(String tempAm) {
        this.tempAm = tempAm;
    }

    @Basic
    @Column(name = "tempPM")
    public String getTempPm() {
        return tempPm;
    }

    public void setTempPm(String tempPm) {
        this.tempPm = tempPm;
    }

    @Basic
    @Column(name = "des")
    public String getDesc() {
        return des;
    }

    public void setDesc(String des) {
        this.des = des;
    }

    @Basic
    @Column(name = "sysDate")
    public Date getSysDate() {
        return sysDate;
    }

    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }

    @Basic
    @Column(name = "studentId")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbTempEntity that = (TbTempEntity) o;
        return id == that.id &&
                Objects.equals(tempAm, that.tempAm) &&
                Objects.equals(tempPm, that.tempPm) &&
                Objects.equals(des, that.des) &&
                Objects.equals(sysDate, that.sysDate) &&
                Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tempAm, tempPm, des, sysDate,studentId);
    }
}
