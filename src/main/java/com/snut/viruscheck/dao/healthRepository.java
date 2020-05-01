package com.snut.viruscheck.dao;

import com.snut.viruscheck.entity.TbHealthEntity;
import com.snut.viruscheck.entity.dto.OtherStudent;
import com.snut.viruscheck.entity.dto.tableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.util.List;

public interface healthRepository extends JpaRepository<TbHealthEntity, String> , JpaSpecificationExecutor<TbHealthEntity> {

    @Query(value = "select t from TbHealthEntity t where t.sysdate =:sysDate")
    List<TbHealthEntity> findBySysDate( @Param("sysDate") String sysDate);

    @Query(value = "select new com.snut.viruscheck.entity.dto.tableEntity(t.xh,t.state,s.name) from TbHealthEntity t join TbStudentEntity s on t.xh=s.id where t.sysdate =:sysDate order by t.xh asc")
    List<tableEntity> findBySysDateWithBaseInfo(@Param("sysDate") String sysDate);

    @Query(value = "select t.qrcode from health t where  t.sysdate=?1 and t.xh=?2" ,nativeQuery = true)
    byte[] findBase64(String strDay, String stuId);


}
