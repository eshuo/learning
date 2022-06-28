package com.example.qlexpressdemo.service.impl;

import com.example.qlexpressdemo.entity.ConditionInfo;
import com.example.qlexpressdemo.service.IConditionInfoService;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-06-24 16:16
 * @Version V1.0
 */
@SpringBootTest
class ConditionInfoServiceImplTest {

    @Autowired
    private IConditionInfoService iConditionInfoService;

    @Test
    public void testAll(){





        final List<ConditionInfo> all =


                iConditionInfoService.getAll();


        Assert.assertNotNull(all);

    }


    @Test
    public void test() throws Exception {
        System.out.println('a' < 98);
        ExpressRunner runner = new ExpressRunner();
        String[] expList = new String[] {
                "'a' < 'b'",
                "'a' <= 'b'",
                "'a' == 'a'",
                "test == 'a'",
                "test <= 'a'",
                "'a' >= test",
        };
        DefaultContext<String, Object> context = new DefaultContext<>();
        context.put("test", 'a' + 0);
        for (String exp : expList) {
            Object result = runner.execute(exp, context, null, true, false);
            System.out.println(result);
            assert ((Boolean)result);
        }
    }



}