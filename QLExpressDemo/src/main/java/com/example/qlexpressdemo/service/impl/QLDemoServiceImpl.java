package com.example.qlexpressdemo.service.impl;

import com.example.qlexpressdemo.bean.rest.QLDemo;
import com.example.qlexpressdemo.context.QLAliasContext;
import com.example.qlexpressdemo.entity.ConditionInfo;
import com.example.qlexpressdemo.entity.ParamInfo;
import com.example.qlexpressdemo.entity.UIndex;
import com.example.qlexpressdemo.entity.UserIndex;
import com.example.qlexpressdemo.service.*;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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


    @Autowired
    private IUIndexService iuIndexService;

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


    /**
     * 动态指标判断  （全部匹配才判断）
     *
     * @param verify
     * @return
     * @throws Exception
     */
    @Override
    public boolean check(QLDemo.verify verify) throws Exception {


        final String userId = verify.getUserId();
        final UIndex byId = iuIndexService.findById(userId);
        if (null == byId) {
            return false;
        }

        final Map<String, String> indexInfo = byId.getIndexInfo();
        if (null == indexInfo) {
            return false;
        }
        final List<ParamInfo> paramInfos = byId.getParamInfos();
        IExpressContext<String, Object> context = new DefaultContext<>();
        if (!CollectionUtils.isEmpty(paramInfos)) {
            for (ParamInfo paramInfo : paramInfos) {
                if (indexInfo.containsKey(paramInfo.getId())) {
                    indexInfo.put(paramInfo.getField(), indexInfo.get(paramInfo.getId()));
//                    context.put(paramInfo.getField(), indexInfo.get(paramInfo.getId()));
                }
            }
        }
        context.put("指标", indexInfo);
        context.put("规则", indexInfo);

        final String ruleId = verify.getRuleId();

        final List<ConditionInfo> byRuleId = iConditionInfoService.findByRuleId(ruleId);
        if (CollectionUtils.isEmpty(byRuleId)) {
            return false;
        }
        //匹配规则
        ExpressRunner runner = new ExpressRunner();
        if (filterRules(context, byRuleId, runner, indexInfo)) {
            return true;
        }

        return false;
    }

    /**
     * 过滤rule
     *
     * @param context
     * @param byRuleId
     * @param runner
     * @param indexInfo
     * @return
     * @throws Exception
     */
    private boolean filterRules(IExpressContext<String, Object> context, List<ConditionInfo> byRuleId, ExpressRunner runner, Map<String, String> indexInfo) throws Exception {
        for (ConditionInfo conditionInfo : byRuleId) {
            String expression = conditionInfo.getExpression();
            final List<ParamInfo> infos = conditionInfo.getParamInfos();
            if (null != infos && null != indexInfo && infos.stream().allMatch(p -> indexInfo.containsKey(p.getId())) && StringUtils.isNotBlank(expression)) {
                for (ParamInfo paramInfo : infos) {
                    expression = expression.replaceAll(paramInfo.getTitle(), paramInfo.getField());
                }
            } else {
                continue;
            }
            final Object execute = runner.execute(expression, context, null, false, false);
            if (Boolean.TRUE.equals(execute)) {
                if (CollectionUtils.isEmpty(conditionInfo.getSubConditionInfos())) {
                    return true;
                } else {
                    return filterRules(context, conditionInfo.getSubConditionInfos(), runner, indexInfo);
                }
            }
        }
        return false;
    }
}
