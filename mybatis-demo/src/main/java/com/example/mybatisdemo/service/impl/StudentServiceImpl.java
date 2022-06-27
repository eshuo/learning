package com.example.mybatisdemo.service.impl;

import com.example.mybatisdemo.entity.Student;
import com.example.mybatisdemo.mapper.StudentMapper;
import com.example.mybatisdemo.service.IStudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
