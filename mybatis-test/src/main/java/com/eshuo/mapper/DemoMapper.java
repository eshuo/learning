package com.eshuo.mapper;

import com.eshuo.domain.Demo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-12-18 17:30
 * @Version V1.0
 */
@Mapper
public interface DemoMapper {

    List<Demo> selectDemo(Demo demo);
}
