package com.wyci.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @Description 简单分页请求
 * @Author wangshuo
 * @Date 2022-08-10 16:20
 * @Version V1.0
 */
@Data
@ApiModel(value = "分页信息", description = "分页信息")
public class RequestPage<T> {


    @ApiModelProperty(value = "每页大小")
    private int size = 20;

    @ApiModelProperty(value = "当前页")
    private int currentPage = 1;

    @ApiModelProperty(value = "查询数据")
    private T queryData;


    public RequestPage() {
    }

    public RequestPage(int size, int currentPage) {
        this.size = size;
        this.currentPage = currentPage;
    }


    @ApiModelProperty("排序")
    private List<CSort> orders = new ArrayList<>(3);


//    public PageRequest toPageRequest() {
//        final List<Sort.Order> _orders = orders.stream().map(cSort -> {
//            if (cSort.isAsc()) {
//                return new Sort.Order(Sort.Direction.ASC, cSort.getColumn());
//            } else {
//                return new Sort.Order(Sort.Direction.DESC, cSort.getColumn());
//            }
//        }).collect(Collectors.toList());
//        return PageRequest.of(getQueryPage(), getSize(), Sort.by(_orders));
//    }


    public T getQueryData() {
        return queryData;
    }

    public void setQueryData(T queryData) {
        this.queryData = queryData;
    }

    public Integer getQueryPage() {
        if (currentPage < 1) {
            currentPage = 1;
        }
        return getCurrentPage() - 1;
//        Mybatis plus 从1开始  Jpa从0开始
//        return getCurrentPage() - 1;
    }


    public Integer getQuerySize() {
        if (size <= 0) {
            size = 20;
        }
        return size;
    }


    @Data
    public static class CSort implements Serializable {

        @ApiModelProperty("排序字段")
        private String column = "";

        @ApiModelProperty("asc排序")
        private boolean asc = false;

        private boolean unsafe = false;

        public CSort() {
        }

        public CSort(String column, boolean asc) {
            this.column = column;
            this.asc = asc;
        }


        public CSort(String column, boolean asc, boolean unsafe) {
            this.column = column;
            this.asc = asc;
            this.unsafe = unsafe;
        }
    }


}
