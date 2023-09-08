package com.wyci.gabapidemo.demos.gab;

import com.wyci.gabapidemo.demos.gab.dto.MockRequest;
import com.wyci.gabapidemo.demos.gab.dto.MockResponse;
import com.wyci.gabapidemo.demos.gab.ws.MockWs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 模拟响应 @Author wangshuo @Date 2023-09-07 15:42 @Version V1.0
 */
@Tag(name = "模拟响应", description = "模拟响应")
@RestController
@RequestMapping("/msg/rest/api/messages")
public class MockApi {

  private static final Logger logger = LoggerFactory.getLogger(MockApi.class);

  @Autowired private MockWs mockWs;

  /** 新增消息 */
  @Operation(summary = "新增消息", description = "新增消息")
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, value = "/message")
  public void addMsg(@RequestBody MockRequest.AddMessage message) {

    mockWs.sendMessageAll(
        " {\n"
            + "        \"messageId\": \"b60ceaf0343842e6a82bb91d88650a40\",\n"
            + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
            + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
            + "        \"sendUserName\": \"张小宁\",\n"
            + "        \"sendUserCode\": \"zhangxiaoning\",\n"
            + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
            + "        \"appCode\": \"oa\",\n"
            + "        \"appName\": \"OA\",\n"
            + "        \"updateUser\": null,\n"
            + "        \"updateTime\": null,\n"
            + "        \"type\": \"1\",   (type含义：1-待办 2-通知 0-全部)\n"
            + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
            + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
            + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
            + "        \"receiveUserName\": \"管理员\",\n"
            + "        \"receiveUserCode\": \"superadmin\",\n"
            + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
            + "        \"state\": \"0\", (state含义：1-已读 0-未读)\n"
            + "        \"modularName\": \"公文管理\",\n"
            + "        \"isValid\": \"1\",\n"
            + "     \"operateType\": \"0\" (operateType含义：0-新增保存 1-逻辑删除 2-物理删除)\n"
            + "    }\n");
  }

  /** 批量新增消息 */
  @Operation(summary = "批量新增消息", description = "批量新增消息")
  @ResponseBody
  @RequestMapping(method = RequestMethod.POST, value = "/messagebatch")
  public void addBatchMsg(@RequestBody List<MockRequest.AddMessage> message) {
    mockWs.sendMessageAll(
        " {\n"
            + "        \"messageId\": \"b60ceaf0343842e6a82bb91d88650a40\",\n"
            + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
            + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
            + "        \"sendUserName\": \"张小宁\",\n"
            + "        \"sendUserCode\": \"zhangxiaoning\",\n"
            + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
            + "        \"appCode\": \"oa\",\n"
            + "        \"appName\": \"OA\",\n"
            + "        \"updateUser\": null,\n"
            + "        \"updateTime\": null,\n"
            + "        \"type\": \"1\",   (type含义：1-待办 2-通知 0-全部)\n"
            + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
            + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
            + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
            + "        \"receiveUserName\": \"管理员\",\n"
            + "        \"receiveUserCode\": \"superadmin\",\n"
            + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
            + "        \"state\": \"0\", (state含义：1-已读 0-未读)\n"
            + "        \"modularName\": \"公文管理\",\n"
            + "        \"isValid\": \"1\",\n"
            + "     \"operateType\": \"0\" (operateType含义：0-新增保存 1-逻辑删除 2-物理删除)\n"
            + "    }\n");
  }

  /** 根据模块名称和表单ID将待办改为已办 */
  @Parameters({
    @Parameter(name = "modularname", description = "", in = ParameterIn.QUERY),
    @Parameter(name = "orderId", description = "", in = ParameterIn.QUERY, required = true)
  })
  @Operation(summary = "根据模块名称和表单ID将待办改为已办", description = "根据模块名称和表单ID将待办改为已办")
  @ResponseBody
  @RequestMapping(method = RequestMethod.DELETE, value = "/deletemessage")
  public void deletemessage(
      @RequestParam(value = "modularname", required = false) String modularname,
      @RequestParam("orderId") String orderId) {
    mockWs.sendMessageAll(
        " {\n"
            + "        \"messageId\": \"b60ceaf0343842e6a82bb91d88650a40\",\n"
            + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
            + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
            + "        \"sendUserName\": \"张小宁\",\n"
            + "        \"sendUserCode\": \"zhangxiaoning\",\n"
            + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
            + "        \"appCode\": \"oa\",\n"
            + "        \"appName\": \"OA\",\n"
            + "        \"updateUser\": null,\n"
            + "        \"updateTime\": null,\n"
            + "        \"type\": \"1\",   (type含义：1-待办 2-通知 0-全部)\n"
            + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
            + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
            + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
            + "        \"receiveUserName\": \"管理员\",\n"
            + "        \"receiveUserCode\": \"superadmin\",\n"
            + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
            + "        \"state\": \"0\", (state含义：1-已读 0-未读)\n"
            + "        \"modularName\": \"公文管理\",\n"
            + "        \"isValid\": \"1\",\n"
            + "     \"operateType\": \"0\" (operateType含义：0-新增保存 1-逻辑删除 2-物理删除)\n"
            + "    }\n");
  }

  /** 批量将待办改为已办 */
  @Parameters({
    @Parameter(name = "modularname", description = "", in = ParameterIn.QUERY),
    @Parameter(name = "orderIds", description = "", in = ParameterIn.QUERY, required = true)
  })
  @Operation(summary = "批量将待办改为已办", description = "批量将待办改为已办")
  @ResponseBody
  @RequestMapping(method = RequestMethod.DELETE, value = "/deletemessagematch")
  public void deletemessagematch(
      @RequestParam(value = "modularname", required = false) String modularname,
      @RequestParam("orderIds") List<String> orderIds) {
    mockWs.sendMessageAll(
        " {\n"
            + "        \"messageId\": \"b60ceaf0343842e6a82bb91d88650a40\",\n"
            + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
            + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
            + "        \"sendUserName\": \"张小宁\",\n"
            + "        \"sendUserCode\": \"zhangxiaoning\",\n"
            + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
            + "        \"appCode\": \"oa\",\n"
            + "        \"appName\": \"OA\",\n"
            + "        \"updateUser\": null,\n"
            + "        \"updateTime\": null,\n"
            + "        \"type\": \"1\",   (type含义：1-待办 2-通知 0-全部)\n"
            + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
            + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
            + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
            + "        \"receiveUserName\": \"管理员\",\n"
            + "        \"receiveUserCode\": \"superadmin\",\n"
            + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
            + "        \"state\": \"0\", (state含义：1-已读 0-未读)\n"
            + "        \"modularName\": \"公文管理\",\n"
            + "        \"isValid\": \"1\",\n"
            + "     \"operateType\": \"0\" (operateType含义：0-新增保存 1-逻辑删除 2-物理删除)\n"
            + "    }\n");
  }

  /** 批量物理删除待办消息 */
  @Parameters({
    @Parameter(name = "modularname", description = "", in = ParameterIn.QUERY),
    @Parameter(name = "orderIds", description = "", in = ParameterIn.QUERY, required = true)
  })
  @Operation(summary = "批量物理删除待办消息", description = "批量物理删除待办消息")
  @ResponseBody
  @RequestMapping(method = RequestMethod.DELETE, value = "/removemessagematch")
  public void removemessagematch(
      @RequestParam(value = "modularname", required = false) String modularname,
      @RequestParam("orderIds") List<String> orderIds) {
    mockWs.sendMessageAll(
        " {\n"
            + "        \"messageId\": \"b60ceaf0343842e6a82bb91d88650a40\",\n"
            + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
            + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
            + "        \"sendUserName\": \"张小宁\",\n"
            + "        \"sendUserCode\": \"zhangxiaoning\",\n"
            + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
            + "        \"appCode\": \"oa\",\n"
            + "        \"appName\": \"OA\",\n"
            + "        \"updateUser\": null,\n"
            + "        \"updateTime\": null,\n"
            + "        \"type\": \"1\",   (type含义：1-待办 2-通知 0-全部)\n"
            + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
            + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
            + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
            + "        \"receiveUserName\": \"管理员\",\n"
            + "        \"receiveUserCode\": \"superadmin\",\n"
            + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
            + "        \"state\": \"0\", (state含义：1-已读 0-未读)\n"
            + "        \"modularName\": \"公文管理\",\n"
            + "        \"isValid\": \"1\",\n"
            + "     \"operateType\": \"0\" (operateType含义：0-新增保存 1-逻辑删除 2-物理删除)\n"
            + "    }\n");
  }

  /** 根据业务主键批量修改未读消息状态（置为已读） */
  @Parameters({
    @Parameter(name = "appcode", description = "", in = ParameterIn.QUERY),
    @Parameter(name = "orderIds", description = "", in = ParameterIn.QUERY, required = true)
  })
  @Operation(summary = "根据业务主键批量修改未读消息状态（置为已读）", description = "根据业务主键批量修改未读消息状态（置为已读）")
  @ResponseBody
  @RequestMapping(method = RequestMethod.PUT, value = "/messagebatch/state/orderids")
  public void orderids(
      @RequestParam(value = "appcode", required = false) String appcode,
      @RequestParam("orderIds") List<String> orderIds) {
    mockWs.sendMessageAll(
        " {\n"
            + "        \"messageId\": \"b60ceaf0343842e6a82bb91d88650a40\",\n"
            + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
            + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
            + "        \"sendUserName\": \"张小宁\",\n"
            + "        \"sendUserCode\": \"zhangxiaoning\",\n"
            + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
            + "        \"appCode\": \"oa\",\n"
            + "        \"appName\": \"OA\",\n"
            + "        \"updateUser\": null,\n"
            + "        \"updateTime\": null,\n"
            + "        \"type\": \"1\",   (type含义：1-待办 2-通知 0-全部)\n"
            + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
            + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
            + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
            + "        \"receiveUserName\": \"管理员\",\n"
            + "        \"receiveUserCode\": \"superadmin\",\n"
            + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
            + "        \"state\": \"0\", (state含义：1-已读 0-未读)\n"
            + "        \"modularName\": \"公文管理\",\n"
            + "        \"isValid\": \"1\",\n"
            + "     \"operateType\": \"0\" (operateType含义：0-新增保存 1-逻辑删除 2-物理删除)\n"
            + "    }\n");
  }

  /** 根据消息主键批量修改未读消息状态（置为已读） */
  @Parameters({
    @Parameter(name = "appcode", description = "", in = ParameterIn.QUERY),
    @Parameter(name = "messageIds", description = "", in = ParameterIn.QUERY, required = true)
  })
  @Operation(summary = "根据消息主键批量修改未读消息状态（置为已读）", description = "根据消息主键批量修改未读消息状态（置为已读）")
  @ResponseBody
  @RequestMapping(method = RequestMethod.PUT, value = "/messagebatch/state/messageids")
  public void messageids(
      @RequestParam(value = "appcode", required = false) String appcode,
      @RequestParam("messageIds") List<String> messageIds) {
    mockWs.sendMessageAll(
        " {\n"
            + "        \"messageId\": \"b60ceaf0343842e6a82bb91d88650a40\",\n"
            + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
            + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
            + "        \"sendUserName\": \"张小宁\",\n"
            + "        \"sendUserCode\": \"zhangxiaoning\",\n"
            + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
            + "        \"appCode\": \"oa\",\n"
            + "        \"appName\": \"OA\",\n"
            + "        \"updateUser\": null,\n"
            + "        \"updateTime\": null,\n"
            + "        \"type\": \"1\",   (type含义：1-待办 2-通知 0-全部)\n"
            + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
            + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
            + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
            + "        \"receiveUserName\": \"管理员\",\n"
            + "        \"receiveUserCode\": \"superadmin\",\n"
            + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
            + "        \"state\": \"0\", (state含义：1-已读 0-未读)\n"
            + "        \"modularName\": \"公文管理\",\n"
            + "        \"isValid\": \"1\",\n"
            + "     \"operateType\": \"0\" (operateType含义：0-新增保存 1-逻辑删除 2-物理删除)\n"
            + "    }\n");
  }

  /** 根据业务主键查询消息详情 */
  @Parameters({
    @Parameter(name = "modularName", description = "", in = ParameterIn.QUERY),
    @Parameter(name = "orderId", description = "", in = ParameterIn.QUERY, required = true)
  })
  @Operation(summary = "根据业务主键查询消息详情", description = "根据业务主键查询消息详情")
  @ResponseBody
  @RequestMapping(method = RequestMethod.GET, value = "/message")
  public MockResponse.BaseResponse<MockResponse.MessInfoResponse> message(
      @RequestParam(value = "modularName", required = false) String modularName,
      @RequestParam("orderId") String orderId) {

    return getMessInfoResponseBaseResponse();
  }

  private static MockResponse.BaseResponse<MockResponse.MessInfoResponse>
      getMessInfoResponseBaseResponse() {
    List<MockResponse.MessInfoResponse> list = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      MockResponse.MessInfoResponse response = new MockResponse.MessInfoResponse();
      response.setMessageId("b60ceaf0343842e6a82bb91d88650a4" + i);
      response.setMessageTitle("冬奥会相关文件签署做好防疫安全措施举措" + i);
      response.setSendUserId("aa33ba6c7cf842409aba43206e9f6553");
      response.setSendUserName("张小宁");
      response.setSendUserCode("zhangxiaoning");
      response.setCreateTime("2022/02/22 15:20:00");
      response.setAppCode("oa");
      response.setAppName("OA");
      response.setUpdateUser(null);
      response.setUpdateTime(null);
      response.setType("1");
      response.setUrl("http://127.0.0.1/platform/home");
      response.setDoneUrl("http://127.0.0.1/platform/home");
      response.setInnerUrl("http://127.0.0.1/platform/home");
      response.setInnerDoneUrl("http://127.0.0.1/platform/home");
      response.setOrderId("4321ba6c7cf842409aba43206e9c5553");
      response.setReceiveUserName("管理员");
      response.setReceiveUserCode("superadmin");
      response.setReceiveUserId("c8f1ba6c7cf842409aba43206e9f7442");
      response.setState("0");
      response.setModularName("公文管理");
      response.setIsValid("1");
      list.add(response);
    }
    return new MockResponse.BaseResponse<>(list);
  }

  /** 查询最近一个月消息 */
  @Parameters({
    @Parameter(name = "receiveUserId", description = "", in = ParameterIn.QUERY),
    @Parameter(name = "type", description = "", in = ParameterIn.QUERY, required = true)
  })
  @Operation(summary = "查询最近一个月消息", description = "查询最近一个月消息")
  @ResponseBody
  @RequestMapping(method = RequestMethod.GET, value = "/queryMessages")
  public MockResponse.BaseResponse<MockResponse.MessInfoResponse> queryMessages(
      @RequestParam(value = "receiveUserId", required = false) String receiveUserId,
      @RequestParam("type") String type) {
    return getMessInfoResponseBaseResponse();
  }

  /** 获取各个模块及待办和未读消息数量 */
  @Parameters({
    @Parameter(name = "appCodes", description = "", in = ParameterIn.QUERY),
    @Parameter(name = "receiveUserId", description = "", in = ParameterIn.QUERY, required = true)
  })
  @Operation(summary = "获取各个模块及待办和未读消息数量", description = "获取各个模块及待办和未读消息数量")
  @ResponseBody
  @RequestMapping(method = RequestMethod.GET, value = "/messagecount")
  public MockResponse.BaseResponse<MockResponse.GroupInfoNum> messagecount(
      @RequestParam(value = "appCodes", required = false) List<String> appCodes,
      @RequestParam("receiveUserId") String receiveUserId) {
    return getGroupInfoNumBaseResponse();
  }

  /** 获取OA各个模块及待办和未读消息数量 */
  @Parameters({
    @Parameter(name = "appCodes", description = "", in = ParameterIn.QUERY),
    @Parameter(name = "receiveUserId", description = "", in = ParameterIn.QUERY, required = true)
  })
  @Operation(summary = "获取OA各个模块及待办和未读消息数量", description = "获取OA各个模块及待办和未读消息数量")
  @ResponseBody
  @RequestMapping(method = RequestMethod.GET, value = "/oamessagecount")
  public MockResponse.BaseResponse<MockResponse.GroupInfoNum> oamessagecount(
      @RequestParam(value = "appCodes", required = false) List<String> appCodes,
      @RequestParam("receiveUserId") String receiveUserId) {
    return getGroupInfoNumBaseResponse();
  }

  private static MockResponse.BaseResponse<MockResponse.GroupInfoNum>
      getGroupInfoNumBaseResponse() {
    List<MockResponse.GroupInfoNum> list = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      MockResponse.GroupInfoNum groupInfoNum = new MockResponse.GroupInfoNum();
      groupInfoNum.setMessageCount(String.valueOf(Integer.valueOf(String.valueOf(Math.random()))));
      groupInfoNum.setModularName("模块名称" + i);
      groupInfoNum.setSort(String.valueOf(i));
      list.add(groupInfoNum);
    }
    return new MockResponse.BaseResponse<>(list);
  }

  /** 获取用户所属模块名称列表 */
  @Parameter(name = "receiveUserId", description = "", in = ParameterIn.QUERY, required = true)
  @Operation(summary = "获取用户所属模块名称列表", description = "获取用户所属模块名称列表")
  @ResponseBody
  @RequestMapping(method = RequestMethod.GET, value = "/modularnames")
  public MockResponse.BaseResponse<String> oamessagecount(
      @RequestParam("receiveUserId") String receiveUserId) {
    // TODO: 2023/9/7 返回
    List<String> list =
        Arrays.stream(
                new String[] {
                  "公文管理", "督办管理", "加班管理", "值班管理", "日常业务", "建议提案", "人事管理", "党建管理", "任务督办", "领导日程",
                  "会议管理"
                })
            .collect(Collectors.toList());
    return new MockResponse.BaseResponse<>(list);
  }

  /** 获取各个系统待办和未读消息数量 */
  @Parameters({
    @Parameter(name = "appCodes", description = "", in = ParameterIn.QUERY),
    @Parameter(name = "receiveUserId", description = "", in = ParameterIn.QUERY, required = true)
  })
  @Operation(summary = "获取各个系统待办和未读消息数量", description = "获取各个系统待办和未读消息数量")
  @ResponseBody
  @RequestMapping(method = RequestMethod.GET, value = "/system/count")
  public MockResponse.BaseResponse<MockResponse.AppInfoNum> count(
      @RequestParam(value = "appCodes", required = false) List<String> appCodes,
      @RequestParam("receiveUserId") String receiveUserId) {
    List<MockResponse.AppInfoNum> list = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      MockResponse.AppInfoNum groupInfoNum = new MockResponse.AppInfoNum();
      groupInfoNum.setAppCode("appCode" + i);
      groupInfoNum.setMessageCount(String.valueOf(i));
      list.add(groupInfoNum);
    }
    return new MockResponse.BaseResponse<>(list);
  }
}
