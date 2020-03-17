package com.wangkang.mapper;

import com.wangkang.entity.User2;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 19:22 2020/3/17
 * @Modified By:
 */
public interface ReasoningTestMapper2 {
    @Transactional(propagation = Propagation.REQUIRED)
    @Select("insert into user2(id,name) values(1,'wk')")
    void required();

    @Transactional(propagation = Propagation.SUPPORTS)
    @Select("insert into user2(id,name) values(1,'wk')")
    void supports();

    @Transactional(propagation = Propagation.MANDATORY)
    @Select("insert into user2(id,name) values(1,'wk')")
    void mandatory();

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Select("insert into user2(id,name) values(1,'wk')")
    void requires_new();

    @Transactional(propagation = Propagation.NESTED)
    @Select("insert into user2(id,name) values(1,'wk')")
    void nested();

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Select("insert into user2(id,name) values(1,'wk')")
    void not_supported();

    @Transactional(propagation = Propagation.NEVER)
    @Select("insert into user2(id,name) values(1,'wk')")
    void never();

    @Transactional(propagation = Propagation.REQUIRED)
    @Select("insert into user2(id,name) values(1,'wk')")
    void REQUIRED();
    @Transactional(propagation = Propagation.REQUIRED)
    @Select("insert into user22(id,name) values(1,'wk')")
    void REQUIRED_ERROR();

    @Transactional(propagation = Propagation.SUPPORTS)
    @Select("insert into user2(id,name) values(1,'wk')")
    void SUPPORTS();

    @Transactional(propagation = Propagation.SUPPORTS)
    @Select("insert into user22(id,name) values(1,'wk')")
    void SUPPORTS_ERROR();

    @Transactional(propagation = Propagation.MANDATORY)
    @Select("insert into user2(id,name) values(1,'wk')")
    void MANDATORY();
    @Transactional(propagation = Propagation.MANDATORY)
    @Select("insert into user22(id,name) values(1,'wk')")
    void MANDATORY_ERROR();
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Select("insert into user2(id,name) values(1,'wk')")
    void REQUIRES_NEW();
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Select("insert into user22(id,name) values(1,'wk')")
    void REQUIRES_NEW_ERROR();
    @Transactional(propagation = Propagation.NESTED)
    @Select("insert into user2(id,name) values(1,'wk')")
    void NESTED();
    @Transactional(propagation = Propagation.NESTED)
    @Select("insert into user22(id,name) values(1,'wk')")
    void NESTED_ERROR();
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Select("insert into user2(id,name) values(1,'wk')")
    void NOT_SUPPORTED();
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Select("insert into user22(id,name) values(1,'wk')")
    void NOT_SUPPORTED_ERROR();
    @Transactional(propagation = Propagation.NEVER)
    @Select("insert into user2(id,name) values(1,'wk')")
    void NEVER();

    @Select("insert into user2(id,name) values(1,'wk')")
    void null0();

    @Select("insert into user2(id,name) values(1,'wk')")
    void NULL0();

    @Select("insert into user22(id,name) values(1,'wk')")
    void NULL0_ERROR();

    @Update("truncate table user2")
    void truncate();

    @Select("select * from user2 where id=#{id}")
    User2 selectById(@Param("id") Integer id);
}
