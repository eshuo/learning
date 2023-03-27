package com.wyci.opendemo.auto;

import com.dtflys.forest.springboot.annotation.ForestScan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @author kindear Chat 模块
 */
@Slf4j
@AutoConfiguration
@ComponentScan(basePackages = {"com.wyci.opendemo"})
@ForestScan(basePackages = {"com.wyci.opendemo.api"})
public class ChatAutoConfiguration implements EnvironmentAware {
  @Override
  public void setEnvironment(Environment environment) {
    // 此处注册全局环境
  }
}
