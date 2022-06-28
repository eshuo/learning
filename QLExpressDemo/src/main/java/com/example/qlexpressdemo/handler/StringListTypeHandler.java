package com.example.qlexpressdemo.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.jetbrains.annotations.Nullable;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-27 11:36
 * @Version V1.0
 */
@MappedTypes(List.class) //映射的Java数据类型
@MappedJdbcTypes(JdbcType.VARCHAR)  //映射的JDBC数据类型
public class StringListTypeHandler extends BaseTypeHandler<List<String>> {

    //重写mybatis设置参数的方法
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> param,
                                    JdbcType jdbcType) throws SQLException {
        List<String> list = new ArrayList<>();
        for (String item : param) {
            list.add(String.valueOf(item));
        }
        ps.setString(i, String.join(",", list));
    }

    //重写mybatis获取字段字符串型内容到Bean类List变量的方法
    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        String tempJson = rs.getString(columnName);
        return getIntegerList(tempJson);
    }

    @Nullable
    private List<String> getIntegerList(String tempJson) {
        if (null != tempJson && !tempJson.trim().isEmpty()) {
            return Arrays.stream(tempJson.split(",")).collect(Collectors.toList());
        }
        return null;
    }


    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        String tempJson = rs.getString(columnIndex);
        return getIntegerList(tempJson);
    }


    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String tempJson = cs.getString(columnIndex);
        return getIntegerList(tempJson);
    }

}