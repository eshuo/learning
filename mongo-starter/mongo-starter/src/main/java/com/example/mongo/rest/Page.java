package com.example.mongo.rest;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-03 14:55
 * @Version V1.0
 */
@Data
@ToString
public class Page<T> {


    /**
     * 当前页数
     */
    private Integer currentPage = 1;

    /**
     * 每页显示条数
     */
    private Integer size = 1;

    /**
     * 排序参数
     */
    private List<Page.PageSort> orders = new ArrayList<>(3);


    /**
     * 数据
     */
    private List<T> data;

    /**
     * 最大页数
     */
    private Integer maxPage = 0;


    /**
     * 最大条数
     */
    private Integer maxSize = 0;

    /**
     * 当前页数在整体数据中的位置
     */
    private Integer start;

    /**
     * 当前页数截止在整体数据中的位置
     */
    private Integer end;


    public Page() {
        init();
    }


    public Page(List<T> data) {
        this.data = data;
        init();
    }


    public Page(Integer currentPage, Integer size) {
        this.currentPage = currentPage;
        this.size = size;
    }

    public Page(List<T> data, int size, int currentPage, int maxSize) {
        this.data = data;
        this.currentPage = currentPage;
        this.size = size;
        this.maxSize = maxSize;
        init();
    }


    public Page(List<T> data, Page page, int maxSize) {
        this(data, page.getSize(), page.getCurrentPage(), maxSize);
    }

    public Page(List<T> data, Page page, long maxSize) {
        this(data, page.getSize(), page.getCurrentPage(), Math.toIntExact(maxSize));
    }


    /**
     * 初始化方法
     */
    public void init() {
        if (size < 1) {
            throw new RuntimeException("请求每页条数size < 1！！！");
        }

        if (currentPage < 1) {
            throw new RuntimeException("请求页数currentPage < 1！！！！");
        }

        if (maxSize < 0) {
            throw new RuntimeException("返回总页数 < 0！！！！");
        }
        if (data == null) {
            data = new ArrayList<>();
            maxPage = 0;
            start = 0;
            end = 0;
        } else {
            maxPage = (int) Math.ceil(div(maxSize, size));
            start = (currentPage - 1) * size + 1;
            end = currentPage * size;
            if (end > maxSize) {
                end = maxSize;
            }
        }
    }

    public static double div(Integer v1, Integer v2) {
        return new BigDecimal(v1).divide(new BigDecimal(v2), 10, RoundingMode.HALF_UP).doubleValue();
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

    /**
     * 排序  字段 + 升序？
     *
     * @param column
     * @param asc
     */
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
    @Data
    @ToString
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

    }

}
