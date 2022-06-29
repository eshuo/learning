package com.example.qlexpressdemo.mapper;

import com.example.qlexpressdemo.entity.ParamInfo;
import com.example.qlexpressdemo.entity.UIndex;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2022-06-29
 */
public interface UIndexMapper extends BaseMapper<UIndex> {


    UIndex findById(String id);


    int save(UIndex uIndex);


    List<ParamInfo> getParamInfoList(String ids);
}
