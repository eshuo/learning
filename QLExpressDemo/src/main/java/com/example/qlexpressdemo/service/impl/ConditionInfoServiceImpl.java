package com.example.qlexpressdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.qlexpressdemo.entity.ConditionInfo;
import com.example.qlexpressdemo.mapper.ConditionInfoMapper;
import com.example.qlexpressdemo.service.IConditionInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 条件表 服务实现类
 * </p>
 *
 * @author author
 * @since 2022-06-24
 */
@Service
public class ConditionInfoServiceImpl extends ServiceImpl<ConditionInfoMapper, ConditionInfo> implements IConditionInfoService {


    @Resource
    private ConditionInfoMapper conditionInfoMapper;


    @Override
    public List<ConditionInfo> getAll() {

        final ConditionInfo byId = conditionInfoMapper.findById(1000);

        return conditionInfoMapper.selectAllConditionInfo();

//        return new ArrayList<>();
    }


    @Override
    public boolean save(ConditionInfo entity) {
        final int insert = conditionInfoMapper.insert(entity);
        return true;
    }




}
