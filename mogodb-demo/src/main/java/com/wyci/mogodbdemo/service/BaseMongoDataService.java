package com.wyci.mogodbdemo.service;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-07-29 11:09
 * @Version V1.0
 */
public interface BaseMongoDataService {





    <T, ID> MongoRepository<T, ID> getMongoRepository();


    default <T> T defaultSave(T data) {
        return getMongoRepository().save(data);
    }

    default <T> T defaultUpdate(T data) {
        return getMongoRepository().save(data);
    }

    default <T> void defaultDelete(T data) {
        getMongoRepository().delete(data);
    }


    default <ID> void defaultDeleteById(ID id) {
        getMongoRepository().deleteById(id);
    }


//    default <T, ID> T defaultReadById(ID id) {
//        final Optional<T> optionalO = getMongoRepository().findById(id);
//    }




}
