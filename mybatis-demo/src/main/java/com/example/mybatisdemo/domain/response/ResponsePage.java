package com.example.mybatisdemo.domain.response;

import com.example.mybatisdemo.domain.request.RequestPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @Description 简单分页响应
 * @Author wangshuo
 * @Date 2022-08-10 16:10
 * @Version V1.0
 */
@ApiModel(description = "分页响应")
public class ResponsePage<T> {

    @ApiModelProperty(value = "分页信息")
    private PageInfo pageInfo;

    @ApiModelProperty(value = "分页数据")
    private List<T> pageData;

    public ResponsePage() {
        this.pageInfo = new PageInfo(0, 20, 0);
    }

    public ResponsePage(List<T> pageData) {
        this.pageInfo = new PageInfo(0, 20, 0);
        this.pageData = pageData;
    }

    public ResponsePage(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public ResponsePage(long size, long currentPage) {
        this.pageInfo = new PageInfo(currentPage, size, 0);
    }

    public ResponsePage(List<T> pageData, PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        this.pageData = pageData;
    }

    public ResponsePage(List<T> pageData, RequestPage requestPage, long maxSize) {
        this.pageData = pageData;
        this.pageInfo = new PageInfo(requestPage.getCurrentPage(), requestPage.getSize(), maxSize);
    }

    /**
     * 此方法只有底层返回调用,否则分页失败
     *
     * @param pageData
     * @param size
     * @param currentPage
     * @param maxSize
     */
    public ResponsePage(List<T> pageData, long size, long currentPage, long maxSize) {
        this.pageData = pageData;
        this.pageInfo = new PageInfo(currentPage, size, maxSize);
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }

    public static class PageInfo {
        public PageInfo() {

        }

        /**
         * 当前页数
         */
        @ApiModelProperty(value = "当前页数")
        private long currentPage = 1;

        /**
         * 每页显示条数
         */
        @ApiModelProperty(value = "每页显示条数")
        private long size = 1;

        /**
         * 最大页数
         */
        @ApiModelProperty(value = "最大页数")
        private long maxPage = 0;

        /**
         * 最大条数
         */
        @ApiModelProperty(value = "最大条数")
        private long maxSize = 0;

        /**
         * 当前页数在整体数据中的位置
         */
        @ApiModelProperty(value = "当前页数在整体数据中的位置")
        private long start = 0;

        /**
         * 当前页数截止在整体数据中的位置
         */
        @ApiModelProperty(value = "当前页数截止在整体数据中的位置")
        private long end = 0;

        public PageInfo(long currentPage, long size, long maxSize) {
            if (currentPage < 1) {
                currentPage = 1;
            }
            this.currentPage = currentPage;
            this.size = size;
            this.maxSize = maxSize;
            if (size > 0 && maxSize > 0) {
                this.maxPage = (int) Math.ceil(new BigDecimal(maxSize).divide(new BigDecimal(size), 10, RoundingMode.HALF_UP).doubleValue());
                this.start = (currentPage - 1) * size + 1;
                this.end = currentPage * size;
                if (this.end > this.maxSize) {
                    this.end = this.maxSize;
                }
            }
        }

        public long getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(long currentPage) {
            this.currentPage = currentPage;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public long getMaxPage() {
            return maxPage;
        }

        public void setMaxPage(long maxPage) {
            this.maxPage = maxPage;
        }

        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        public long getStart() {
            return start;
        }

        public void setStart(long start) {
            this.start = start;
        }

        public long getEnd() {
            return end;
        }

        public void setEnd(long end) {
            this.end = end;
        }
    }

}
