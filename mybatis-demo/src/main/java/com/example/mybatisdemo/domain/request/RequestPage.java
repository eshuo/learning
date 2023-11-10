package com.example.mybatisdemo.domain.request;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 简单分页请求
 * @Author wangshuo
 * @Date 2022-08-10 16:20
 * @Version V1.0
 */
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

    public RequestPage(int size, int currentPage, List<OrderItem> orders) {
        this.size = size;
        this.currentPage = currentPage;
        this.orders = orders;
    }

    public RequestPage(int size, int currentPage, T queryData, List<OrderItem> orders) {
        this.size = size;
        this.currentPage = currentPage;
        this.queryData = queryData;
        this.orders = orders;
    }

    @ApiModelProperty(hidden = true, value = "排序")
    private List<OrderItem> orders = new ArrayList<>(3);

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }

    public Page toPage() {
        return new Page(getQueryPage(), getQuerySize());
    }

    public Page toJpaPage() {
        return new Page(getQueryPage() - 1, getQuerySize());
    }

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
        return getCurrentPage();
//        Mybatis plus 从1开始  Jpa从0开始
//        return getCurrentPage() - 1;
    }


    public Integer getQuerySize() {
        if (size <= 0) {
            size = 20;
        }
        return size;
    }


}
