package com.example.mybatisdemo.dao;

import com.example.mybatisdemo.domain.Login;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangshuo
 * @since 2022-09-24
 */
public interface ILoginService extends IService<Login> {



    Map<String, List<Map<String, Object>>> init();


    void csv() throws IOException;

}
