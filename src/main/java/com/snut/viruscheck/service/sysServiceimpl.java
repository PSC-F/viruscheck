package com.snut.viruscheck.service;


import com.snut.viruscheck.dao.StudentRespository;
import com.snut.viruscheck.dao.healthRepository;
import com.snut.viruscheck.dao.sysRepository;
import com.snut.viruscheck.dao.tempRepository;
import com.snut.viruscheck.entity.TbHealthEntity;
import com.snut.viruscheck.entity.TbTempEntity;
import com.snut.viruscheck.entity.dto.OtherStudent;
import com.snut.viruscheck.entity.dto.tableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class sysServiceimpl implements baseService {
    @Autowired
    private sysRepository sysRepository;
    @Autowired
    private tempRepository tempRepository;
    @Autowired
    private healthRepository healthRepository;
    @Autowired
    private StudentRespository studentRespository;
    @Override
    public Object getStudent(String id) {
        return sysRepository.findById(id).orElse(null);

    }

    @Override
    public Object updateTemp(TbTempEntity tbTempEntity) {
        return tempRepository.save(tbTempEntity);

    }

    @Override
    public List<Object[]> selectTempByDay(Date day) {
        return tempRepository.selectTempByDay(day);

    }

    @Override
    public TbTempEntity getTemp(String fid) {
        return tempRepository.getOne(fid);
    }

    @Override
    public void saveHealth(TbHealthEntity healthEntity) {
        healthRepository.save(healthEntity);
    }

    @Override
    public List<TbHealthEntity> selectHealthByDate(String sysDate) {
        return healthRepository.findBySysDate(sysDate);
    }

    @Override
    public List<tableEntity> selectHealthBaseInfo(String sysDate) {
        return healthRepository.findBySysDateWithBaseInfo(sysDate);
    }

    @Override
    public  byte[]  findBase64By(String strDay, String stuId) {
        return healthRepository.findBase64(strDay,stuId);
    }

    @Override
    public List<Object[]> findOtherStudentsInfo(String strDay) {
        return studentRespository.findOtherStudents(strDay);
    }

}
