package com.wyci.mogodbdemo.rest.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-03 14:49
 * @Version V1.0
 */
public class RequestPage {

    /**
     * 当前页面
     */
    private Integer currentPage;

    /**
     * 页面大小
     */
    private Integer size;

    private List<PageSort> orders = new ArrayList<>(3);


    public RequestPage() {
        this.currentPage = 1;
        this.size = 20;
    }

    public Integer getCurrentPage() {
        if (currentPage == null) {
            currentPage = 1;
        }
        if (currentPage < 1) {
            currentPage = 1;
        }
        return currentPage;
    }

    public Integer getQueryPage() {
        if (currentPage == null) {
            currentPage = 1;
        }
        if (currentPage < 1) {
            currentPage = 1;
        }
        return getCurrentPage() - 1;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getSize() {
        if (size == null) {
            size = 20;
        }
        if (size < 1) {
            size = 1;
        }
        return size;
    }

    public Long getSkip() {
        return (long) getQueryPage() * getSize();
    }


    public void setSize(Integer size) {
        this.size = size;
    }

    public List<PageSort> getOrders() {
        return orders;
    }

    public void setOrders(List<PageSort> orders) {
        this.orders = orders;
    }

    public void of(String column, boolean asc) {
        this.orders.add(new PageSort(column, asc));
    }


    public PageRequest toPageRequest() {
        final List<Sort.Order> _orders = toSortOrderList();
        return PageRequest.of(getQueryPage(), getSize(), Sort.by(_orders));
    }

    public List<Sort.Order> toSortOrderList() {
        final List<Sort.Order> _orders = orders.stream().map(pageSort -> {
            if (pageSort.isAsc()) {
                return new Sort.Order(Sort.Direction.ASC, pageSort.getColumn());
            } else {
                return new Sort.Order(Sort.Direction.DESC, pageSort.getColumn());
            }
        }).collect(Collectors.toList());
        return _orders;
    }


    /**
     * 排序
     */
    public static class PageSort implements Serializable {

        private String column = "";

        private boolean asc = false;

        private boolean unsafe = false;

        public PageSort() {
        }

        public PageSort(String column, boolean asc) {
            this.column = column;
            this.asc = asc;
        }


        public PageSort(String column, boolean asc, boolean unsafe) {
            this.column = column;
            this.asc = asc;
            this.unsafe = unsafe;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public boolean isAsc() {
            return asc;
        }

        public void setAsc(boolean asc) {
            this.asc = asc;
        }

        public boolean isUnsafe() {
            return unsafe;
        }

        public void setUnsafe(boolean unsafe) {
            this.unsafe = unsafe;
        }
    }
}
