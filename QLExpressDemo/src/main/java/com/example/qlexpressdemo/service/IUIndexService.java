package com.example.qlexpressdemo.service;

import com.example.qlexpressdemo.entity.UIndex;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-06-29
 */
public interface IUIndexService extends IService<UIndex> {



    UIndex findById(String id);


    boolean save(UIndex uIndex);




}
