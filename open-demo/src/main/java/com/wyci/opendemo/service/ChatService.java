package com.wyci.opendemo.service;

import com.wyci.opendemo.params.chat.ChatMessage;
import com.wyci.opendemo.params.chat.ChatParams;
import com.wyci.opendemo.params.chat.ChatResult;
import com.wyci.opendemo.params.edit.EditParams;
import com.wyci.opendemo.params.edit.EditResult;
import com.wyci.opendemo.params.image.ImageParams;
import com.wyci.opendemo.params.image.ImageResult;

import java.util.List;
import java.util.Map;

/**
 * 使用ChatGpt 相关服务接口
 */
public interface ChatService {
    // 构建请求头
    Map<String,Object> headers();

    // 构建用户消息
    ChatMessage buildUserMessage(String content);

    // 构建会话
    ChatResult doChat(ChatParams params);

    // 构建会话 V2 -- 携带会话ID
    ChatResult doChat(ChatParams params, String chatId);

    // Str 形式返回
    String doChatStr(ChatParams params, String chatId);

    // 构建编辑执行
    EditResult doEdit(EditParams params);

    // 构建图形绘制
    ImageResult doDraw(ImageParams params);

    // 获取上下文
    List<ChatMessage> getContext(String chatId);

    // 获取上下文 -- 指定条数
    List<ChatMessage> getContext(String chatId, Integer num);

    // 简化返回结果
    String simpleResult(ChatResult result);

    // 简化编辑返回结果
    String simpleResult(EditResult result);

    // 简化图片绘制返回结果
    List<String> simpleResult(ImageResult result);
}
