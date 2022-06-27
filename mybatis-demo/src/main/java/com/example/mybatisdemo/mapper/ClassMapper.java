package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.entity.Clazz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisdemo.entity.Student;
import com.example.mybatisdemo.entity.Teacher;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2022-06-27
 */
public interface ClassMapper extends BaseMapper<Clazz> {

    Clazz getClazz(int id);
    Clazz getClazz2(int id);

    Teacher getTeacher(int id);

   Student getStuList(int id);


}
