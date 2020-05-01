package com.snut.viruscheck.service;


import com.snut.viruscheck.entity.TbHealthEntity;
import com.snut.viruscheck.entity.TbStudentEntity;
import com.snut.viruscheck.entity.TbTempEntity;
import com.snut.viruscheck.entity.dto.ExcelTemp;
import com.snut.viruscheck.entity.dto.OtherStudent;
import com.snut.viruscheck.entity.dto.tableEntity;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Component
public interface baseService {
    /**
     * 获取用户实体
     *
     * @param id
     * @return
     */
    Object getStudent(String id);

    /**
     * 更新温度信息表
     *
     * @param tbTempEntity
     * @return
     */
    Object updateTemp(TbTempEntity tbTempEntity);

    /**
     * 获取当日温度信息记录
     *
     * @param day
     * @return
     */
    List<Object[]> selectTempByDay(Date day);

    TbTempEntity getTemp(String fid);

    void saveHealth(TbHealthEntity healthEntity);

    /**
     * 根据日期获取Health表格对象
     * @param sysDate
     * @return
     */
    List<TbHealthEntity> selectHealthByDate(String sysDate);
    List<tableEntity> selectHealthBaseInfo(String sysDate);

    byte[]  findBase64By(String strDay, String stuId);

    List<Object[]> findOtherStudentsInfo(String strDay);
}
