package com.wyci.jpa.query;

import com.wyci.jpa.repository.JpaDefaultRepository;
import java.beans.FeatureDescriptor;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import javax.persistence.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Example;

/**
 * @Description 查询
 * @Author wangshuo
 * @Date 2025-04-09 16:49
 * @Version V1.0
 */
public interface JpaBaseQueryService<T, ID> extends Serializable {


    JpaDefaultRepository<T, ID> getBaseDao();

    /**
     * 插入一条记录（选择字段，策略插入）
     *
     * @param entity 实体对象
     */
    default T save(T entity) {
        return getBaseDao().save(entity);
    }

    /**
     * 插入（批量）
     *
     * @param entityList 实体对象集合
     */
    default List<T> saveBatch(Collection<T> entityList) {
        return getBaseDao().saveAll(entityList);
    }

    /**
     * 根据 ID 选择修改
     *
     * @param data 实体对象
     */
    default void updateById(ID id, T data) {
        T byId = getById(id);
        if (null != byId) {
            BeanUtils.copyProperties(data, byId, getNullPropertyNames(data));
            getBaseDao().saveAndFlush(byId);
        }
    }

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    default void removeById(ID id) {
        getBaseDao().deleteById(id);
    }

    /**
     * 根据实体(ID)删除
     *
     * @param entity 实体
     * @since 3.4.4
     */
    default void removeByEntity(T entity) {
        getBaseDao().delete(entity);
    }

    /**
     * 删除（根据ID 批量删除）
     *
     * @param entities 主键ID或实体列表
     */
    default void removeByEntities(Collection<T> entities) {
        getBaseDao().deleteAll(entities);
    }

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    default T getById(ID id) {
        return getBaseDao().findById(id).orElse(null);
    }

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表
     */
    default List<T> listByIds(Collection<ID> idList) {
        return getBaseDao().findAllById(idList);
    }

    /**
     * 查询所有
     */
    default List<T> list() {
        return getBaseDao().findAll();
    }

    /**
     * 查询列表
     *
     * @param t
     * @return
     */
    default List<T> list(T t) {
        return getBaseDao().findAll(Example.of(t));
    }

//    /**
//     * 查询分页数据
//     *
//     * @param page
//     * @param <X>
//     *
//     * @return
//     */
//    default <X extends Pageable> ResponsePage<T> page(X page) {
//        return getBaseDao().responsePage(page);
//    }


    /**
     * 根据条件统计
     *
     * @param t
     * @return
     */
    default long count(T t) {
        return getBaseDao().count(Example.of(t));
    }

    /**
     * 得到一个
     *
     * @param t t
     * @return {@code T}
     */
    default T getOne(T t) {
        return getBaseDao().findOne(Example.of(t)).orElse(null);
    }


    /**
     * 给对象设置主键
     *
     * @param source 对象
     * @param id     主键
     */
    static void setId(Object source, Object id) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        Stream.of(wrappedSource.getPropertyDescriptors())
            .map(FeatureDescriptor::getName)
            .filter(propertyName -> (wrappedSource.getPropertyTypeDescriptor(propertyName) != null && wrappedSource.getPropertyTypeDescriptor(propertyName).hasAnnotation(Id.class)))
            .forEach(propertyName -> wrappedSource.setPropertyValue(propertyName, id));
    }

    /**
     * 获取主键
     *
     * @param source 对象
     */
    static Object getId(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
            .map(FeatureDescriptor::getName)
            .filter(propertyName -> (wrappedSource.getPropertyTypeDescriptor(propertyName) != null && wrappedSource.getPropertyTypeDescriptor(propertyName).hasAnnotation(Id.class)))
            .map(wrappedSource::getPropertyValue).findFirst().orElse(null);
    }


    /**
     * 不拷贝空值，和主键
     *
     * @param source 传入的对象
     * @return 需要忽略的值
     */
    static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
            .map(FeatureDescriptor::getName)
            .filter(propertyName -> {
                Object propertyValue = wrappedSource.getPropertyValue(propertyName);
                boolean result =
                    propertyValue == null || (wrappedSource.getPropertyTypeDescriptor(propertyName) != null && wrappedSource.getPropertyTypeDescriptor(propertyName).hasAnnotation(Id.class));

//                final Class<?> propertyType = wrappedSource.getPropertyType(propertyName);
//                if (propertyType != null && propertyValue != null && (Number.class.isAssignableFrom(propertyType) || String.class.isAssignableFrom(propertyType))) {
//                    if (propertyValue.equals(CommonConstants.LONG_IS_NULL)
//                        || propertyValue.equals(CommonConstants.DOUBLE_IS_NULL)
//                        || propertyValue.equals(CommonConstants.INTEGER_IS_NULL)
//                        || propertyValue.equals(CommonConstants.STRING_IS_NULL)
//                        || propertyValue.equals(CommonConstants.BIGDECIMAL_IS_NULL)
//                    ) {
//                        wrappedSource.setPropertyValue(propertyName, null);
//                    }
//                }
//                if (propertyType != null && propertyValue != null && String[].class.isAssignableFrom(propertyType)) {
//                    if (((String[]) propertyValue).length == 2
//                        && StringUtils.equals(((String[]) propertyValue)[0], CommonConstants.ARRAY_IS_NULL[0])
//                        && StringUtils.equals(((String[]) propertyValue)[1], CommonConstants.ARRAY_IS_NULL[1])
//                    ) {
//                        wrappedSource.setPropertyValue(propertyName, null);
//                    }
//                }

                return result;

            })
            .toArray(String[]::new);
    }

}
