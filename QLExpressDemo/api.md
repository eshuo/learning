[TOC]

##### 简要描述

- 数据上报接口

##### 请求URL

- ` http://xx.com/api/risk/reporting `

##### 请求方式

- POST

##### 请求示例

``` 
  后续补充
```

##### 基本参数

| 参数名       |必选|类型| 说明                           |
|:----------|:---|:----- |------------------------------|
| timestamp |是  |Long | 时间戳/防重放一分钟有效，精确到毫秒           |
| nonce     |是  |string | 防重放随机数，一分钟有效                 |
| sign      |是  |string | 签名/防篡改签名                     |
| appId     |是  |string | 客户端唯一标识/防伪装接入能力后同一分配的客户端唯一标识 |
| data      |是  |Json | 请求数据                         |

##### data参数

| 参数名         | 必选  | 类型     | 说明           |
|:------------|:----|:-------|--------------|
| async       | 是   | string | 是否同步         |
| nodeId      | 是   | string | 决策流节点        |
| userId      | 是   | string | 用户标识         |
| sessionId   | 否   | string | 会话Id         |
| clientId    | 否   | string | 设备标识         |
| version     | 否   | string | 版本           |
| ip          | 否   | string | ip           |
| contextInfo | 是   | Json   | 上下文信息 （指标信息） |

##### 返回示例

``` 
  后续补充
```

##### 返回参数说明

##### 基本返回参数

| 参数名       | 类型     | 说明   |
|:----------|:-------|------|
| code      | int    | 响应码  |
| status    | int    | 状态码  |
| message   | string | 响应信息 |
| requestId | string | 响应id |
| data      | Json   | 响应数据 |


##### data参数 决策 policy

| 参数名            | 类型     | 说明     |
|:---------------|:-------|--------|
| targetId       | String | 决策目标id |
| policyId       | String | 决策id   |
| policyType     | int    | 决策类型   |
| policyMsg      | string | 决策信息   |
| HitDecisionId  | String | 命中决策Id |
| HitDecisionMsg | String | 命中决策信息 |


##### 备注

- 更多返回错误代码请看首页的错误代码描述




