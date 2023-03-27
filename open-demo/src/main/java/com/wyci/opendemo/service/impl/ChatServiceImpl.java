package com.wyci.opendemo.service.impl;

import com.dtflys.forest.http.ForestResponse;
import com.wyci.opendemo.api.chat.ChatApi;
import com.wyci.opendemo.api.edit.EditApi;
import com.wyci.opendemo.api.image.ImageApi;
import com.wyci.opendemo.cache.ChatCache;
import com.wyci.opendemo.constant.ChatRoleConst;
import com.wyci.opendemo.params.chat.ChatMessage;
import com.wyci.opendemo.params.chat.ChatParams;
import com.wyci.opendemo.params.chat.ChatResult;
import com.wyci.opendemo.params.chat.model.ChoiceModel;
import com.wyci.opendemo.params.edit.EditParams;
import com.wyci.opendemo.params.edit.EditResult;
import com.wyci.opendemo.params.edit.model.EditChoiceModel;
import com.wyci.opendemo.params.image.ImageParams;
import com.wyci.opendemo.params.image.ImageResult;
import com.wyci.opendemo.params.image.model.ImageChoiceModel;
import com.wyci.opendemo.service.ChatAuthService;
import com.wyci.opendemo.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author kindear
 * OpenAI相关服务实现
 */
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {
    @Value("${openai.chat.key}")
    private String chatKey;

    @Resource
    private ChatApi chatApi;

    @Resource
    private EditApi editApi;

    @Resource
    private ImageApi imageApi;

    @Autowired
    ChatAuthService authService;

    @Override
    public Map<String, Object> headers() {
        Map<String,Object> headers = new HashMap<>();
        headers.put("Authorization","Bearer " + chatKey);
        return headers;
    }

    @Override
    public ChatMessage buildUserMessage(String content) {
        ChatMessage message = new ChatMessage();
        message.setRole(ChatRoleConst.USER);
        message.setContent(content);
        return message;
    }

    @Override
    public ChatResult doChat(ChatParams params) {
        // 写入用户ID
        params.setUser(authService.getUserId());
        ForestResponse<ChatResult> response =  chatApi.ChatCompletions(headers(),params);
        ChatResult result = response.getResult();
        return result;
    }

    @Override
    public ChatResult doChat(ChatParams params, String chatId) {
        // 写入用户ID
        params.setUser(authService.getUserId());
        ForestResponse<ChatResult> response =  chatApi.ChatCompletions(headers(),params);
        ChatResult result = response.getResult();
        // 获取返回结果
        List<ChoiceModel> choices = result.getChoices();
        // 获取第一条结果
        ChoiceModel choice = choices.get(0);
        // 获取消息
        ChatMessage message = choice.getMessage();
        // 写入缓存
        List<ChatMessage> messages = ChatCache.get(chatId);
        messages.add(message);
        ChatCache.put(chatId,messages);
        // 返回结果
        return result;
    }

    @Override
    public String doChatStr(ChatParams params, String chatId) {
        params.setUser(authService.getUserId());
        return chatApi.ChatCompletionsStr(headers(),params);
    }

    @Override
    public EditResult doEdit(EditParams params) {
        ForestResponse<EditResult> response =  editApi.ChatEdits(headers(),params);
        EditResult result = response.getResult();
        return result;
    }

    @Override
    public ImageResult doDraw(ImageParams params) {
        // 写入用户
        params.setUser(authService.getUserId());
        ForestResponse<ImageResult> response =  imageApi.ChatDraw(headers(),params);
        ImageResult result = response.getResult();
        return result;
    }

    @Override
    public List<ChatMessage> getContext(String chatId) {
        List<ChatMessage> messages = ChatCache.get(chatId);
        return messages;
    }


    @Override
    public List<ChatMessage> getContext(String chatId, Integer num) {
        List<ChatMessage> messages = getContext(chatId);
        int msgSize = messages.size();

        if (msgSize > num){
            return messages.subList(msgSize - num,msgSize);
        }else {
            return messages;
        }
    }

    @Override
    public String simpleResult(ChatResult result) {
        // 获取返回结果
        List<ChoiceModel> choices = result.getChoices();
        // 获取第一条结果
        ChoiceModel choice = choices.get(0);
        // 获取消息
        ChatMessage message = choice.getMessage();
        // 返回内容
        return message.getContent();
    }

    @Override
    public String simpleResult(EditResult result) {
        List<EditChoiceModel> choices = result.getChoices();
        // 获取结果
        EditChoiceModel choice = choices.get(0);
        // 获取消息
        return choice.getText();
    }

    @Override
    public List<String> simpleResult(ImageResult result) {
        List<ImageChoiceModel> choices = result.getData();
        List<String> res = choices.stream().map(ImageChoiceModel::getUrl).collect(Collectors.toList());
        return res;
    }
}
