package com.example.qlexpressdemo.service;

import com.example.qlexpressdemo.entity.ConditionInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 条件表 服务类
 * </p>
 *
 * @author author
 * @since 2022-06-24
 */
public interface IConditionInfoService extends IService<ConditionInfo> {


    ConditionInfo findById(String id);

    public List<ConditionInfo> getAll();



    List<ConditionInfo> findByRuleId(String ruleId);

}
