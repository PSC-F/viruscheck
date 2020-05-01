package com.snut.viruscheck.dao;

import com.snut.viruscheck.entity.TbStudentEntity;
import com.snut.viruscheck.entity.dto.OtherStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRespository extends  JpaRepository<TbStudentEntity, String>, JpaSpecificationExecutor<TbStudentEntity>  {
    @Query( value = "select stu.name,stu.id from tb_student stu where stu.id not in  (select h.xh from health h where h.sysdate=?1 order by h.xh asc)",nativeQuery = true)
    List<Object[]> findOtherStudents(String strDay);
}
