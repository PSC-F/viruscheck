package com.snut.viruscheck.dao;

import com.snut.viruscheck.entity.TbTempEntity;
import com.snut.viruscheck.entity.dto.ExcelTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface tempRepository extends JpaRepository<TbTempEntity, Object> {
    @Query(nativeQuery = true, value = "SELECT s.className,s.id,s.name,s.sex,t.tempAM,t.tempPM,s.address,t.des,s.phone FROM tb_temp t, tb_student s WHERE s.id = t.studentId AND t.sysDate =:day AND t.studentId ORDER BY t.studentId ASC")
    List<Object[]> selectTempByDay(@Param("day") Date day);
}
