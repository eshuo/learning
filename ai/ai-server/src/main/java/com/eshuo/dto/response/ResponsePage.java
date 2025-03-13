package com.eshuo.dto.response;

import com.eshuo.dto.request.RequestPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.Data;
import org.springframework.data.domain.Page;

/**
 * @Description 简单分页响应
 * @Author wangshuo
 * @Date 2022-08-10 16:10
 * @Version V1.0
 */
@Data
@ApiModel(description = "分页响应")
public class ResponsePage<T> {

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

    public ResponsePage(Page<T> page) {
        this.pageData = page.getContent();
        init(page.getNumber() + 1, page.getSize(), page.getTotalElements());
    }

    public void init(long currentPage, long size, long maxSize) {
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

    @ApiModelProperty(value = "分页数据")
    private List<T> pageData;


    public ResponsePage() {
        init(0, 20, 0);
    }

    public ResponsePage(List<T> pageData) {
        init(0, 20, 0);
        this.pageData = pageData;
    }


    public ResponsePage(long size, long currentPage) {
        init(currentPage, size, 0);
    }


    public ResponsePage(List<T> pageData, RequestPage requestPage, long maxSize) {
        this.pageData = pageData;
        init(requestPage.getCurrentPage(), requestPage.getSize(), maxSize);
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
        init(currentPage, size, maxSize);
    }


}
