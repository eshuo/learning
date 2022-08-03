package com.wyci.mogodbdemo.rest.response;

import com.wyci.mogodbdemo.rest.request.RequestPage;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-08-03 14:55
 * @Version V1.0
 */
public class ResponsePage<T> {


    /**
     * 数据
     */
    private List<T> data;

    /**
     * 当前页数
     */
    private int currentPage = 1;

    /**
     * 最大页数
     */
    private int maxPage = 0;

    /**
     * 每页显示条数
     */
    private int size = 1;

    /**
     * 最大条数
     */
    private int maxSize;

    /**
     * 当前页数在整体数据中的位置
     */
    private int start;

    /**
     * 当前页数截止在整体数据中的位置
     */
    private int end;


    public ResponsePage() {
        init();
    }

    public ResponsePage(List<T> data) {
        this.data = data;
        init();
    }


    public ResponsePage(List<T> data, int size, int currentPage, int maxSize) {
        this.data = data;
        this.currentPage = currentPage;
        this.size = size;
        this.maxSize = maxSize;
        init();
    }


    public ResponsePage(List<T> data, RequestPage page, int maxSize) {
        this(data, page.getSize(), page.getCurrentPage(), maxSize);
    }

    public  ResponsePage(List<T> data, RequestPage page, long count) {
        this(data, page.getSize(), page.getCurrentPage(), Math.toIntExact(count));
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


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
