package com.wyci.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description 风险引擎请求
 * @Author wangshuo
 * @Date 2022-12-10 15:07
 * @Version V1.0
 */
public interface ResRiskRequest {


    @NoArgsConstructor
    @Data
    @ToString
    public static class QueryDict{

        /**
         * size
         */
        @JsonProperty("size")
        private Integer size;

        /**
         * currentPage
         */
        @JsonProperty("currentPage")
        private Integer currentPage;

        /**
         * type
         */
        @JsonProperty("type")
        private String type;
    }


}
