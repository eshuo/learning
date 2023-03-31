package com.example.tokenuserdemo.demos.domain;

import lombok.Data;
/**
 * @Description @Author wangshuo @Date 2023-03-28 19:38 @Version V1.0
 */
@Data
public class ApiResult {

  /** 防重放随机数 */
  private String nonce;

  /** 业务数据 */
  private UserInfo data;
}
