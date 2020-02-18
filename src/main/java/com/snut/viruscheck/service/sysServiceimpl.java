package com.snut.viruscheck.service;

import com.snut.viruscheck.dao.sysRepository;
import com.snut.viruscheck.dao.tempRepository;
import com.snut.viruscheck.entity.TbStudentEntity;
import com.snut.viruscheck.entity.TbTempEntity;
import com.snut.viruscheck.entity.dto.ExcelTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class sysServiceimpl implements baseService {
    @Autowired
    private sysRepository sysRepository;
    @Autowired
    private tempRepository tempRepository;

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

}
