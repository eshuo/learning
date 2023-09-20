package com.wyci.gabapidemo.demos.gab;

import com.wyci.gabapidemo.demos.gab.dto.MockRequest;
import com.wyci.gabapidemo.demos.gab.dto.MockResponse;
import com.wyci.gabapidemo.demos.gab.ws.MockWs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Description 模拟响应 @Author wangshuo @Date 2023-09-07 15:42 @Version V1.0
 */
@Tag(
        name = "模拟响应 @Author wangshuo @Date 2023-09-07 15:42 @Version V1.0",
        description = "模拟响应 @Author wangshuo @Date 2023-09-07 15:42 @Version V1.0")
@RestController
@RequestMapping("/msg/rest/api/messages")
public class MockApi {

    private static final Logger logger = LoggerFactory.getLogger(MockApi.class);

    @Autowired
    private MockWs mockWs;

    /**
     * 新增消息
     */
    @Operation(summary = "新增消息", description = "新增消息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/message")
    public void addMsg(@RequestBody MockRequest.AddMessage message) {

        mockWs.sendMessageAll(
                " {\n"
                        + "        \"messageId\": \"" + UUID.randomUUID().toString() + "\",\n"
                        + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
                        + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
                        + "        \"sendUserName\": \"张小宁\",\n"
                        + "        \"sendUserCode\": \"zhangxiaoning\",\n"
                        + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
                        + "        \"appCode\": \"oa\",\n"
                        + "        \"appName\": \"OA\",\n"
                        + "        \"updateUser\": null,\n"
                        + "        \"updateTime\": null,\n"
                        + "        \"type\": \"1\",   "
                        + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
                        + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
                        + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
                        + "        \"receiveUserName\": \"管理员\",\n"
                        + "        \"receiveUserCode\": \"superadmin\",\n"
                        + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
                        + "        \"state\": \"0\", "
                        + "        \"modularName\": \"公文管理\",\n"
                        + "        \"isValid\": \"1\",\n"
                        + "     \"operateType\": \"0\" "
                        + "    }\n");
    }

    /**
     * 批量新增消息
     */
    @Operation(summary = "批量新增消息", description = "批量新增消息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/messagebatch")
    public void addBatchMsg(@RequestBody List<MockRequest.AddMessage> message) {
        mockWs.sendMessageAll(
                " {\n"
                        + "        \"messageId\": \"" + UUID.randomUUID().toString() + "\",\n"
                        + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
                        + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
                        + "        \"sendUserName\": \"张小宁\",\n"
                        + "        \"sendUserCode\": \"zhangxiaoning\",\n"
                        + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
                        + "        \"appCode\": \"oa\",\n"
                        + "        \"appName\": \"OA\",\n"
                        + "        \"updateUser\": null,\n"
                        + "        \"updateTime\": null,\n"
                        + "        \"type\": \"1\",   "
                        + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
                        + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
                        + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
                        + "        \"receiveUserName\": \"管理员\",\n"
                        + "        \"receiveUserCode\": \"superadmin\",\n"
                        + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
                        + "        \"state\": \"0\", "
                        + "        \"modularName\": \"公文管理\",\n"
                        + "        \"isValid\": \"1\",\n"
                        + "     \"operateType\": \"0\" "
                        + "    }\n");
    }

    /**
     * 根据模块名称和表单ID将待办改为已办
     */
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
                        + "        \"messageId\": \"" + UUID.randomUUID().toString() + "\",\n"
                        + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
                        + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
                        + "        \"sendUserName\": \"张小宁\",\n"
                        + "        \"sendUserCode\": \"zhangxiaoning\",\n"
                        + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
                        + "        \"appCode\": \"oa\",\n"
                        + "        \"appName\": \"OA\",\n"
                        + "        \"updateUser\": null,\n"
                        + "        \"updateTime\": null,\n"
                        + "        \"type\": \"1\",   "
                        + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
                        + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
                        + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
                        + "        \"receiveUserName\": \"管理员\",\n"
                        + "        \"receiveUserCode\": \"superadmin\",\n"
                        + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
                        + "        \"state\": \"0\", "
                        + "        \"modularName\": \"公文管理\",\n"
                        + "        \"isValid\": \"1\",\n"
                        + "     \"operateType\": \"0\" "
                        + "    }\n");
    }

    /**
     * 批量将待办改为已办
     */
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
                        + "        \"messageId\": \"" + UUID.randomUUID().toString() + "\",\n"
                        + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
                        + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
                        + "        \"sendUserName\": \"张小宁\",\n"
                        + "        \"sendUserCode\": \"zhangxiaoning\",\n"
                        + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
                        + "        \"appCode\": \"oa\",\n"
                        + "        \"appName\": \"OA\",\n"
                        + "        \"updateUser\": null,\n"
                        + "        \"updateTime\": null,\n"
                        + "        \"type\": \"1\",   "
                        + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
                        + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
                        + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
                        + "        \"receiveUserName\": \"管理员\",\n"
                        + "        \"receiveUserCode\": \"superadmin\",\n"
                        + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
                        + "        \"state\": \"0\", "
                        + "        \"modularName\": \"公文管理\",\n"
                        + "        \"isValid\": \"1\",\n"
                        + "     \"operateType\": \"0\" "
                        + "    }\n");
    }

    /**
     * 批量物理删除待办消息
     */
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
                        + "        \"messageId\": \"" + UUID.randomUUID().toString() + "\",\n"
                        + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
                        + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
                        + "        \"sendUserName\": \"张小宁\",\n"
                        + "        \"sendUserCode\": \"zhangxiaoning\",\n"
                        + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
                        + "        \"appCode\": \"oa\",\n"
                        + "        \"appName\": \"OA\",\n"
                        + "        \"updateUser\": null,\n"
                        + "        \"updateTime\": null,\n"
                        + "        \"type\": \"1\",   "
                        + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
                        + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
                        + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
                        + "        \"receiveUserName\": \"管理员\",\n"
                        + "        \"receiveUserCode\": \"superadmin\",\n"
                        + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
                        + "        \"state\": \"0\", "
                        + "        \"modularName\": \"公文管理\",\n"
                        + "        \"isValid\": \"1\",\n"
                        + "     \"operateType\": \"0\" "
                        + "    }\n");
    }

    /**
     * 根据业务主键批量修改未读消息状态（置为已读）
     */
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
                        + "        \"messageId\": \"" + UUID.randomUUID().toString() + "\",\n"
                        + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
                        + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
                        + "        \"sendUserName\": \"张小宁\",\n"
                        + "        \"sendUserCode\": \"zhangxiaoning\",\n"
                        + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
                        + "        \"appCode\": \"oa\",\n"
                        + "        \"appName\": \"OA\",\n"
                        + "        \"updateUser\": null,\n"
                        + "        \"updateTime\": null,\n"
                        + "        \"type\": \"1\",   "
                        + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
                        + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
                        + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
                        + "        \"receiveUserName\": \"管理员\",\n"
                        + "        \"receiveUserCode\": \"superadmin\",\n"
                        + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
                        + "        \"state\": \"0\", "
                        + "        \"modularName\": \"公文管理\",\n"
                        + "        \"isValid\": \"1\",\n"
                        + "     \"operateType\": \"0\" "
                        + "    }\n");
    }

    /**
     * 根据消息主键批量修改未读消息状态（置为已读）
     */
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
                        + "        \"messageId\": \"" + UUID.randomUUID().toString() + "\",\n"
                        + "        \"messageTitle\": \"冬奥会相关文件签署做好防疫安全措施举措\",\n"
                        + "        \"sendUserId\": \"aa33ba6c7cf842409aba43206e9f6553\",\n"
                        + "        \"sendUserName\": \"张小宁\",\n"
                        + "        \"sendUserCode\": \"zhangxiaoning\",\n"
                        + "        \"createTime\": \"2022/02/22 15:20:00\",\n"
                        + "        \"appCode\": \"oa\",\n"
                        + "        \"appName\": \"OA\",\n"
                        + "        \"updateUser\": null,\n"
                        + "        \"updateTime\": null,\n"
                        + "        \"type\": \"1\",   "
                        + "        \"url\": \"http://127.0.0.1/platform/home\",\n"
                        + "\"doneUrl\":\"http://127.0.0.1/platform/home\",\n"
                        + "        \"orderId\": \"4321ba6c7cf842409aba43206e9c5553\",\n"
                        + "        \"receiveUserName\": \"管理员\",\n"
                        + "        \"receiveUserCode\": \"superadmin\",\n"
                        + "        \"receiveUserId\": \"c8f1ba6c7cf842409aba43206e9f7442\",\n"
                        + "        \"state\": \"0\", "
                        + "        \"modularName\": \"公文管理\",\n"
                        + "        \"isValid\": \"1\",\n"
                        + "     \"operateType\": \"0\" "
                        + "    }\n");
    }

    /**
     * 根据业务主键查询消息详情
     */
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

        return getMessInfoResponseBaseResponse(null, null, null, modularName);
    }

    private static MockResponse.BaseResponse<MockResponse.MessInfoResponse>
    getMessInfoResponseBaseResponse(String type, String state, String appCode, String modular) {
        final List<MockResponse.MessInfoResponse> list =
                getMessInfoResponses(type, state, appCode, modular, null, null);
        return new MockResponse.BaseResponse<>(list);
    }

    private static List<MockResponse.MessInfoResponse> getMessInfoResponses(
            String type, String state, String appCode, String modular, Integer page, Integer rows) {
        List<MockResponse.MessInfoResponse> list = new ArrayList<>();
        if (null == page || page < 1) {
            page = 1;
        }
        if (null == rows) {
            rows = 10;
        }
        final int i1 = (page - 1) * rows;
        if (i1 > 90) {
            return list;
        }
        for (int i = 0; i < rows; i++) {
            MockResponse.MessInfoResponse response = new MockResponse.MessInfoResponse();
            final int num = i1 + i + 1;
            response.setMessageId("b60ceaf0343842e6a82bb91d88650a4" + num);
            response.setMessageTitle(num + "冬奥会相关文件签署做好防疫安全措施举措" + num);
            response.setSendUserId("aa33ba6c7cf842409aba43206e9f6553");
            response.setSendUserName("张小宁");
            response.setSendUserCode("zhangxiaoning");
            response.setCreateTime("2022/02/22 15:20:00");
            if (StringUtils.isNotBlank(appCode)) {
                response.setAppCode(appCode);
                response.setAppName("系统编码-oa OA-email 邮件系统-dangan 档案管理-share 共享平台");
            } else {
                response.setAppCode("oa");
                response.setAppName("OA");
            }

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
            if (StringUtils.isNotBlank(state)) {
                response.setState(state);
            } else {
                response.setState(String.valueOf(i % 2));
            }

            if (StringUtils.isNotBlank(modular)) {
                response.setModularName(modular);
            } else {
                response.setModularName("公文管理");
            }
            if (StringUtils.isNotBlank(type)) {
                response.setType(type);
            } else {
                response.setType(String.valueOf(i % 2));
            }
            response.setIsValid(String.valueOf(i % 2));
            list.add(response);
        }
        return list;
    }

    /**
     * 查询最近一个月消息
     */
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
        return getMessInfoResponseBaseResponse(null, null, null, null);
    }

    /**
     * 获取各个模块及待办和未读消息数量
     */
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

    /**
     * 获取OA各个模块及待办和未读消息数量
     */
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

    /**
     * 分页查询最近待办和未读消息
     *
     * @return
     */
    @Parameters({
            @Parameter(name = "receiveUserId", description = "接收人用户ID（当前登录人ID）", in = ParameterIn.QUERY),
            @Parameter(
                    name = "type",
                    description = "类型，1 待办 2 通知 " + "0全部",
                    in = ParameterIn.QUERY,
                    required = true),
            @Parameter(name = "modularName", description = "模块名称 不传显示全部", in = ParameterIn.QUERY),
            @Parameter(name = "isValid", description = "0已办 1 待办", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "state", description = "0未读 1已读", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "messageTitle", description = "消息标题", in = ParameterIn.QUERY),
            @Parameter(
                    name = "appCode",
                    description = "系统编码" + "-oa  OA" + "-email 邮件系统" + "-dangan 档案管理" + "-share  共享平台",
                    in = ParameterIn.QUERY,
                    required = true),
            @Parameter(name = "page", description = "页码", in = ParameterIn.QUERY, required = true),
            @Parameter(name = "rows", description = "每页数据数", in = ParameterIn.QUERY, required = true)
    })
    @Operation(summary = "分页查询最近待办和未读消息", description = "分页查询最近待办和未读消息")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/queryPageMessages")
    public MockResponse.BasePageResponse<MockResponse.MessInfoResponse> queryPageMessages(
            @RequestParam(value = "receiveUserId", required = false) String receiveUserId,
            @RequestParam("type") String type,
            @RequestParam(value = "modularName", required = false) String modularName,
            @RequestParam("isValid") String isValid,
            @RequestParam(value = "state", required = true) String state,
            @RequestParam(value = "messageTitle", required = false) String messageTitle,
            @RequestParam(value = "appCode", required = true) String appCode,
            @RequestParam(value = "page", required = true) Integer page,
            @RequestParam(value = "rows", required = true) Integer rows) {
        final MockResponse.BasePageResponse<MockResponse.MessInfoResponse>
                messInfoResponseBasePageResponse =
                new MockResponse.BasePageResponse<>(
                        getMessInfoResponses(type, state, appCode, modularName, page, rows));
        messInfoResponseBasePageResponse.setTotal(100);

        return messInfoResponseBasePageResponse;
    }

    private static MockResponse.BaseResponse<MockResponse.GroupInfoNum>
    getGroupInfoNumBaseResponse() {

        List<String> nameList =
                Arrays.stream(
                                new String[]{
                                        "公文管理", "督办管理", "加班管理", "值班管理", "日常业务", "建议提案", "人事管理", "党建管理", "任务督办", "领导日程",
                                        "行政管理", "日程活动", "信息技术", "财务管理", "其他", "会议管理"
                                })
                        .collect(Collectors.toList());

        List<MockResponse.GroupInfoNum> list = new ArrayList<>();
        for (int i = 0; i < nameList.size(); i++) {
            MockResponse.GroupInfoNum groupInfoNum = new MockResponse.GroupInfoNum();
            groupInfoNum.setMessageCount(String.valueOf(new Random().nextInt(100)));
            groupInfoNum.setModularName(nameList.get(i));
            groupInfoNum.setSort(String.valueOf(i));
            list.add(groupInfoNum);
        }
        return new MockResponse.BaseResponse<>(list);
    }

    /**
     * 获取用户所属模块名称列表
     */
    @Parameter(name = "receiveUserId", description = "", in = ParameterIn.QUERY, required = true)
    @Operation(summary = "获取用户所属模块名称列表", description = "获取用户所属模块名称列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/modularnames")
    public MockResponse.BaseResponse<String> oamessagecount(
            @RequestParam("receiveUserId") String receiveUserId) {
        List<String> list =
                Arrays.stream(
                                new String[]{
                                        "公文管理", "督办管理", "加班管理", "值班管理", "日常业务", "建议提案", "人事管理", "党建管理", "任务督办", "领导日程",
                                        "行政管理", "日程活动", "信息技术", "财务管理", "其他", "会议管理"
                                })
                        .collect(Collectors.toList());

        return new MockResponse.BaseResponse<>(list);
    }

    /**
     * 获取各个系统待办和未读消息数量
     */
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


        List<String> codeList = new ArrayList<>();
        codeList.add("oa");
        codeList.add("email");
        codeList.add("dangan");
        codeList.add("share");
        for (int i = 0; i < 4; i++) {
            MockResponse.AppInfoNum groupInfoNum = new MockResponse.AppInfoNum();
            groupInfoNum.setAppCode(codeList.get(i));
            //设置100以内的随机数
            groupInfoNum.setMessageCount(String.valueOf(new Random().nextInt(100)));
            list.add(groupInfoNum);
        }
        return new MockResponse.BaseResponse<>(list);
    }

    /**
     * Recursively flattens a list.
     *
     * @param list the list to be flattened
     * @param <T>  the type of elements in the list
     *
     * @return a new flattened list
     */
    public static <T> List<T> flattenList(List<T> list) {
        List<T> flattenedList = new ArrayList<>();
        for (T t : list) {
            if (t instanceof List) {
                flattenedList.addAll(flattenList((List<T>) t));
            } else {
                flattenedList.add(t);
            }
        }
        return flattenedList;
    }


    public String base64(String code) {

        return Base64.getEncoder().encodeToString(code.getBytes());
    }

    /**
     * 写一个计算时间相差多少天的工具方法
     *
     * @param startTime 开始时间
     */
    public static long getDiffDays(String startTime) {
        long diffDays = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = sdf.parse(startTime);
            Date end = new Date();
            diffDays = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diffDays;
    }

    /**
     * 写一个计算时间相差多少天的工具方法
     *
     */
    public static long getDiffDays() {
        long diffDays = 0;
        Date start = new Date();
        Date end = new Date();
        diffDays = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
        return diffDays;
    }


    /**
     * 写一个校验手机号的方法
     *
     */
    public static boolean checkPhone(String phone) {
        Pattern p = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }






}
