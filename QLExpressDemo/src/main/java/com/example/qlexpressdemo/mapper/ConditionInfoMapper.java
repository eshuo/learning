package com.example.qlexpressdemo.mapper;

import com.example.qlexpressdemo.entity.ConditionInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 条件表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2022-06-24
 */
public interface ConditionInfoMapper extends BaseMapper<ConditionInfo> {


    /**
     * 带子集
     *
     * @param id
     * @return
     */
    ConditionInfo findById(Integer id);


    ConditionInfo findId(Integer id);

    List<ConditionInfo> selectAllConditionInfo();


    List<ConditionInfo> getSubConditionInfo();

    /**
     * 获取规则条件
     *
     * @param ruleId
     * @return
     */
    List<ConditionInfo> findByRuleId(Integer ruleId);



    //curd
//
//    int insertConditionInfo(ConditionInfo conditionInfo);
//
//
//    int updateConditionInfo(ConditionInfo conditionInfo);
//
//
//    int delteConfitionInfo(Integer id);









}
