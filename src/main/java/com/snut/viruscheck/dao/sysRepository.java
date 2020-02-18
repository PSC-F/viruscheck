package com.snut.viruscheck.dao;

import com.snut.viruscheck.entity.TbStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sysRepository extends JpaRepository<TbStudentEntity, Object> {
}
