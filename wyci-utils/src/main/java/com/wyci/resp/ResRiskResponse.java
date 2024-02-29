package com.wyci.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description 风险引擎响应
 * @Author wangshuo
 * @Date 2022-12-10 15:09
 * @Version V1.0
 */
public interface ResRiskResponse {

    @NoArgsConstructor
    @Data
    @ToString
    public static class DictResponse{


        /**
         * pageInfo
         */
        @JsonProperty("pageInfo")
        private PageInfoVO pageInfo;

        /**
         * pageData
         */
        @JsonProperty("pageData")
        private List<PageDataVO> pageData;

        /**
         * PageInfoVO
         */
        @NoArgsConstructor
        @Data
        public static class PageInfoVO {
            /**
             * currentPage
             */
            @JsonProperty("currentPage")
            private String currentPage;

            /**
             * size
             */
            @JsonProperty("size")
            private String size;

            /**
             * maxPage
             */
            @JsonProperty("maxPage")
            private String maxPage;

            /**
             * maxSize
             */
            @JsonProperty("maxSize")
            private String maxSize;

            /**
             * start
             */
            @JsonProperty("start")
            private String start;

            /**
             * end
             */
            @JsonProperty("end")
            private String end;
        }

        /**
         * PageDataVO
         */
        @NoArgsConstructor
        @Data
        public static class PageDataVO {
            /**
             * id
             */
            @JsonProperty("id")
            private Integer id;

            /**
             * type
             */
            @JsonProperty("type")
            private String type;

            /**
             * fieldKey
             */
            @JsonProperty("fieldKey")
            private String fieldKey;

            /**
             * fieldValue
             */
            @JsonProperty("fieldValue")
            private String fieldValue;

            /**
             * label
             */
            @JsonProperty("label")
            private String label;

            /**
             * status
             */
            @JsonProperty("status")
            private Integer status;

            /**
             * showNumber
             */
            @JsonProperty("showNumber")
            private Integer showNumber;
        }
    }


}
