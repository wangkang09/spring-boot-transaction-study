package com.wangkang.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 22:48 2020/3/16
 * @Modified By:
 */
public interface PropagationTestMapper {

    @Transactional(propagation = Propagation.REQUIRED)
    @Select("select count(*) from user1")
    int required();
    @Transactional(propagation = Propagation.SUPPORTS)
    @Select("select count(*) from user1")
    int supports();
    @Transactional(propagation = Propagation.MANDATORY)
    @Select("select count(*) from user1")
    int mandatory();
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Select("select count(*) from user1")
    int requires_new();
    @Transactional(propagation = Propagation.NESTED)
    @Select("select count(*) from user1")
    int nested();
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Select("select count(*) from user1")
    int not_supported();
    @Transactional(propagation = Propagation.NEVER)
    @Select("select count(*) from user1")
    int never();

    @Transactional(propagation = Propagation.REQUIRED)
    @Select("select count(*) from user1")
    int REQUIRED();
    @Transactional(propagation = Propagation.SUPPORTS)
    @Select("select count(*) from user1")
    int SUPPORTS();
    @Transactional(propagation = Propagation.MANDATORY)
    @Select("select count(*) from user1")
    int MANDATORY();
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Select("select count(*) from user1")
    int REQUIRES_NEW();
    @Transactional(propagation = Propagation.NESTED)
    @Select("select count(*) from user1")
    int NESTED();
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Select("select count(*) from user1")
    int NOT_SUPPORTED();
    @Transactional(propagation = Propagation.NEVER)
    @Select("select count(*) from user1")
    int NEVER();
}
