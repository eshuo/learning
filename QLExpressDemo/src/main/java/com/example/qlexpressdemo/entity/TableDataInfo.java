package com.example.qlexpressdemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 动态查询表信息
 * @Author wangshuo
 * @Date 2022-07-05 16:04
 * @Version V1.0
 */
@Data
public class TableDataInfo implements Serializable {


    private String tableName;

    /**
     * sum(a)  +  别名
     */

    private HashMap<String, String> queryRows;

    /**
     * 拼接条件？？？  a = a1
     */
    @Deprecated
    private HashMap<String, Object> whereValue;


    private List<QueryInfo> queryInfos;

    /**
     * and or
     */
    private String separator;


    @Data
    static class QueryInfo {
        /**
         * 字段名
         */
        private String name;

        /***
         * 拼接符  = > < != like in 后续追加
         */
        private String separator;

        /**
         * 字段值
         */
        private String value;

    }


}
