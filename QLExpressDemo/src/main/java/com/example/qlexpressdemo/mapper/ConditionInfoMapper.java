package com.example.qlexpressdemo.mapper;

import com.example.qlexpressdemo.entity.ConditionInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 条件表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2022-06-24
 */
public interface ConditionInfoMapper extends BaseMapper<ConditionInfo> {



    ConditionInfo findById(Integer id);

    List<ConditionInfo> selectAllConditionInfo();


    List<ConditionInfo> getSubConditionInfo();


}
