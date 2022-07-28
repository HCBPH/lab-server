package com.ycj.lab.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 53059
 * @date 2021/7/13 13:32
 */
@Mapper
public interface TestMapper {
    @Select("select count(*) from test where uid=#{uid}")
    public int checkUser(String uid);
}
