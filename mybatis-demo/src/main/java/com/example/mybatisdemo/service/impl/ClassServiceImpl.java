package com.example.mybatisdemo.service.impl;

import com.example.mybatisdemo.entity.Clazz;
import com.example.mybatisdemo.entity.Student;
import com.example.mybatisdemo.entity.Teacher;
import com.example.mybatisdemo.mapper.ClassMapper;
import com.example.mybatisdemo.service.IClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2022-06-27
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Clazz> implements IClassService {

    @Resource
    private ClassMapper classMapper;

    @Override
    public Clazz getClazz(int id) {
        return classMapper.getClazz(id);
    }

    @Override
    public Clazz getClazz2(int id) {
        return classMapper.getClazz2(id);
    }

    @Override
    public Teacher getTeacher(int id) {
        return classMapper.getTeacher(id);
    }

    @Override
    public Student getStuList(int id) {
        return classMapper.getStuList(id);
    }
}
