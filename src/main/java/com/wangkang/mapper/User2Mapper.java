package com.wangkang.mapper;

import com.wangkang.entity.User2;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface User2Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated Tue Jan 30 11:23:27 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated Tue Jan 30 11:23:27 CST 2018
     */
    int insert(User2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated Tue Jan 30 11:23:27 CST 2018
     */
    int insertSelective(User2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated Tue Jan 30 11:23:27 CST 2018
     */
    User2 selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated Tue Jan 30 11:23:27 CST 2018
     */
    int updateByPrimaryKeySelective(User2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated Tue Jan 30 11:23:27 CST 2018
     */
    int updateByPrimaryKey(User2 record);
    
    int truncated();

    @Select("select count(*) from user2")
    int count();

    @Select("select * from user2 where name=#{name}")
    List<User2> selectByName(@Param("name") String name);
}