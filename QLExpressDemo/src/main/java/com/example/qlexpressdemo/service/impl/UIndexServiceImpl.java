package com.example.qlexpressdemo.service.impl;

import com.example.qlexpressdemo.entity.UIndex;
import com.example.qlexpressdemo.mapper.UIndexMapper;
import com.example.qlexpressdemo.service.IUIndexService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2022-06-29
 */
@Service("iUIndexService")
public class UIndexServiceImpl extends ServiceImpl<UIndexMapper, UIndex> implements IUIndexService {

    @Override
    public UIndex findById(String id) {
        return baseMapper.findById(id);
    }

    @Override
    public boolean save(UIndex uIndex) {
        baseMapper.save(uIndex);
        return true;
    }
}
