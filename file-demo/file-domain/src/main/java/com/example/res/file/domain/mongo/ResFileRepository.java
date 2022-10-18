package com.example.res.file.domain.mongo;

import com.example.res.file.entity.ResFileDO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-10-17 17:24
 * @Version V1.0
 */
public interface ResFileRepository extends MongoRepository<ResFileDO, String> {
}
