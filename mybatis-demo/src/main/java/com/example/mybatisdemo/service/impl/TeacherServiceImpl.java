package com.example.mybatisdemo.service.impl;

import com.example.mybatisdemo.entity.Teacher;
import com.example.mybatisdemo.mapper.TeacherMapper;
import com.example.mybatisdemo.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2022-06-27
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

}
