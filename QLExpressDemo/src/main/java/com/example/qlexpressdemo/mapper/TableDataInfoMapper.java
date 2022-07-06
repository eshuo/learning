package com.example.qlexpressdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.qlexpressdemo.entity.ConditionInfo;
import com.example.qlexpressdemo.entity.ParamInfo;
import com.example.qlexpressdemo.entity.TableDataInfo;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 条件表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2022-06-24
 */
public interface TableDataInfoMapper extends BaseMapper<TableDataInfo> {

    List<HashMap<String,Object>> queryTableInfo(String tableName, HashMap<String,Object> map);


    List<HashMap<String,Object>>  queryInfo(TableDataInfo tableDataInfo);


}
