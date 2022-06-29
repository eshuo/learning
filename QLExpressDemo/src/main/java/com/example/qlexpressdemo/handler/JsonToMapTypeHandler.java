package com.example.qlexpressdemo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.io.StringReader;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-29 16:45
 * @Version V1.0
 */
public class JsonToMapTypeHandler extends BaseTypeHandler<Map<String, Object>> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, Object> parameter, JdbcType jdbcType) throws SQLException {
        final String parameterString = mapToJson(parameter);
        StringReader reader = new StringReader(parameterString);
        ps.setCharacterStream(i, reader, parameterString.length());
    }

    public Map<String, Object> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = "";
        Clob clob = rs.getClob(columnName);
        if (clob != null) {
            int size = (int) clob.length();
            value = clob.getSubString(1L, size);
        }

        return jsonToMap(value);
    }

    public Map<String, Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = "";
        Clob clob = rs.getClob(columnIndex);
        if (clob != null) {
            int size = (int) clob.length();
            value = clob.getSubString(1L, size);
        }

        return jsonToMap(value);
    }

    public Map<String, Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = "";
        Clob clob = cs.getClob(columnIndex);
        if (clob != null) {
            int size = (int) clob.length();
            value = clob.getSubString(1L, size);
        }

        return jsonToMap(value);
    }

    private Map<String, Object> jsonToMap(String from) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(from, HashMap.class);
        } catch (IOException e) {
            throw new Error();
        }
    }

    private String mapToJson(Map<String, Object> from) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(from);
        } catch (IOException e) {
            throw new Error();
        }
    }
}
