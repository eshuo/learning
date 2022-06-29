package com.example.qlexpressdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.qlexpressdemo.entity.ConditionInfo;
import com.example.qlexpressdemo.entity.ParamInfo;
import com.example.qlexpressdemo.mapper.ConditionInfoMapper;
import com.example.qlexpressdemo.service.IConditionInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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


//    baseMapper

    @Override
    public ConditionInfo findById(String id) {
        final ConditionInfo byId = baseMapper.findById(id);
        return byId;
    }

    @Override
    public List<ConditionInfo> getAll() {


        final ConditionInfo byId = getBaseMapper().findById(String.valueOf(1000));

        return getBaseMapper().selectAllConditionInfo();

//        return new ArrayList<>();
    }

    @Override
    public List<ConditionInfo> findByRuleId(String ruleId) {
        return baseMapper.findByRuleId(ruleId);
    }


    @Override
    public boolean save(ConditionInfo entity) {
        final int insert = getBaseMapper().insert(entity);
        return true;
    }


    @Override
    public boolean updateById(ConditionInfo conditionInfo) {

        final int i = getBaseMapper().updateById(conditionInfo);
        return true;
    }

    @Override
    public boolean removeById(Serializable id) {
        final int i = getBaseMapper().deleteById(id);
        return true;
    }


}
