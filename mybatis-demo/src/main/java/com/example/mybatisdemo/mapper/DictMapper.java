package com.example.mybatisdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisdemo.entity.Dict;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 数据字典表 Mapper 接口
 * </p>
 *
 * @author wangshuo
 * @since 2023-08-15
 */
public interface DictMapper extends BaseMapper<Dict> {



    /**
     *  父子查询  根据 ID 查询
     * subDicts 子集
     * parentDict 父级
     * @param id 主键ID
     */
    @Override
    @Results({
            @Result(property = "subDicts", column = "id", many = @Many(select = "com.example.mybatisdemo.mapper.DictMapper.selectSubDicts")),
            @Result(property = "id", column = "id",id = true),
            @Result(property = "parentDict", column = "parent_id", one = @One(select = "com.example.mybatisdemo.mapper.DictMapper.selectById"))
    })
    @Select("SELECT * FROM t_dict WHERE id = #{id}")
    Dict selectById(Serializable id);

    @Select("SELECT * FROM t_dict WHERE parent_id = #{id} order by sort")
    List<Dict> selectSubDicts(String id);

}
