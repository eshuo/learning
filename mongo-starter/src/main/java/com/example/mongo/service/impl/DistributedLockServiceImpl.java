package com.example.mongo.service.impl;

import com.example.mongo.entity.DistributedLock;
import com.example.mongo.service.AbstractMongoServiceImpl;
import com.example.mongo.service.DistributedLockService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author wangshuo
 * @Date 2025-11-07 14:05
 * @Version V1.0
 */
@Service("mongodbDistributedLockService")
public class DistributedLockServiceImpl extends AbstractMongoServiceImpl<DistributedLock> implements DistributedLockService {

}
