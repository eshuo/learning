package com.example.qlexpressdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.qlexpressdemo.entity.TableDataInfo;
import com.example.qlexpressdemo.mapper.TableDataInfoMapper;
import com.example.qlexpressdemo.service.ITableDataInfoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-05 16:27
 * @Version V1.0
 */
@Service
public class TableDataInfoImpl extends ServiceImpl<TableDataInfoMapper, TableDataInfo> implements ITableDataInfoService {

    @Override
    public List<HashMap<String, Object>> queryData(String tableName, HashMap<String, Object> map) {
        return baseMapper.queryTableInfo(tableName,map);
    }

    @Override
    public List<HashMap<String, Object>> queryInfo(TableDataInfo tableDataInfo) {
        return baseMapper.queryInfo(tableDataInfo);
    }
}
