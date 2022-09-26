package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.domain.Login;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangshuo
 * @since 2022-09-24
 */
public interface LoginMapper extends BaseMapper<Login> {



    List<String> getUserName();


    List<String> getAllTime();


    List<Map<String, Object>>  getJson(@Param("userName")String userName);
    Map<String,Object> getJson1(String userName);

    Object getJson2(String userName);


    List<Map<String,Object>> getCsv();
}
