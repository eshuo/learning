package com.example.qlexpressdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.qlexpressdemo.entity.RuleInfo;
import com.example.qlexpressdemo.entity.TableDataInfo;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author author
 * @since 2022-06-24
 */
public interface ITableDataInfoService extends IService<TableDataInfo> {


    public List<HashMap<String, Object>> queryData(String tableName, HashMap<String, Object> map);
    public List<HashMap<String, Object>> queryInfo(TableDataInfo tableDataInfo);



}
