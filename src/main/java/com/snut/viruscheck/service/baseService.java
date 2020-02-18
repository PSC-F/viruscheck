package com.snut.viruscheck.service;


import com.snut.viruscheck.entity.TbStudentEntity;
import com.snut.viruscheck.entity.TbTempEntity;
import com.snut.viruscheck.entity.dto.ExcelTemp;
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
}
