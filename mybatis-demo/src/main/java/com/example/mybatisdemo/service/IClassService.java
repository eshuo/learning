package com.example.mybatisdemo.service;

import com.example.mybatisdemo.entity.Clazz;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisdemo.entity.Student;
import com.example.mybatisdemo.entity.Teacher;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-06-27
 */
public interface IClassService extends IService<Clazz> {

    Clazz getClazz(int id);
    Clazz getClazz2(int id);

    Teacher getTeacher(int id);

    Student getStuList(int id);

}
