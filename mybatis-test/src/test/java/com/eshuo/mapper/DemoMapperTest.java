package com.eshuo.mapper;


import com.eshuo.domain.Demo;
import java.util.List;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description
 * @Author wangshuo
 * @Date 2023-12-18 17:40
 * @Version V1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoMapperTest {

    @Resource
    private DemoMapper demoMapper;

    @Test
    public void selectDemo() {

        Demo demo = new Demo();

//        demo.setId("1");
        demo.setName("Zhu Zitao  'OR' 'a'='a \"");

        final List<Demo> demo1 = demoMapper.selectDemo(demo);

        for (Demo demo2 : demo1) {
            System.out.println(demo2);
        }

    }
}