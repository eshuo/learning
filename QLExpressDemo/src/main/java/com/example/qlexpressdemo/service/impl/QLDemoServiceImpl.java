package com.example.qlexpressdemo.service.impl;

import com.example.qlexpressdemo.bean.rest.QLDemo;
import com.example.qlexpressdemo.context.QLAliasContext;
import com.example.qlexpressdemo.entity.ConditionInfo;
import com.example.qlexpressdemo.entity.UserIndex;
import com.example.qlexpressdemo.mapper.RuleInfoMapper;
import com.example.qlexpressdemo.mapper.UserIndexMapper;
import com.example.qlexpressdemo.service.IConditionInfoService;
import com.example.qlexpressdemo.service.IRuleInfoService;
import com.example.qlexpressdemo.service.IUserIndexService;
import com.example.qlexpressdemo.service.QLDemoService;
import com.ql.util.express.ExpressRunner;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-28 15:02
 * @Version V1.0
 */
@Service
public class QLDemoServiceImpl implements QLDemoService, Serializable {


    @Autowired
    private IUserIndexService iUserIndexService;

    @Autowired
    private IConditionInfoService iConditionInfoService;

    @Override
    public boolean verify(QLDemo.verify verify) {

        final String userId = verify.getUserId();
        final UserIndex userIndex = iUserIndexService.getById(userId);
        if (null == userIndex) {
            return false;
        }


        final String ruleId = verify.getRuleId();

        final List<ConditionInfo> byRuleId = iConditionInfoService.findByRuleId(ruleId);
        if (CollectionUtils.isEmpty(byRuleId)) {
            return false;
        }

        ExpressRunner runner = new ExpressRunner();

//        runner.addOperatorWithAlias("如果", "if", null);
//        runner.addOperatorWithAlias("则", "then", null);
//        runner.addOperatorWithAlias("否则", "else", null);


//        List<Boolean> booleanList = new ArrayList<>();
        for (ConditionInfo conditionInfo : byRuleId) {
            final String expression = conditionInfo.getExpression();
            if (StringUtils.isNotBlank(expression)) {
                QLAliasContext context = new QLAliasContext();
                context.putAutoParams(userIndex);
                try {
                    final Object execute = runner.execute(expression, context, null, false, false);
                    final boolean equals = Boolean.TRUE.equals(execute);
                    if (equals) {
                        return true;
                    }
//                    booleanList.add(false);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
//        return booleanList.stream().anyMatch(s -> s);
        return false;
    }
}
