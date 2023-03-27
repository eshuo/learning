package com.wyci.opendemo.controller;

import com.wyci.opendemo.dto.ChatDTO;
import com.wyci.opendemo.dto.EditDTO;
import com.wyci.opendemo.dto.ImageDTO;
import com.wyci.opendemo.params.chat.ChatMessage;
import com.wyci.opendemo.params.chat.ChatParams;
import com.wyci.opendemo.params.chat.ChatResult;
import com.wyci.opendemo.params.edit.EditParams;
import com.wyci.opendemo.params.edit.EditResult;
import com.wyci.opendemo.params.edit.constant.EditModelEnum;
import com.wyci.opendemo.params.image.ImageParams;
import com.wyci.opendemo.params.image.ImageResult;
import com.wyci.opendemo.service.ChatAuthService;
import com.wyci.opendemo.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author kindear
 * chat 聊天相关接口
 */
@Slf4j
@RestController
@RequestMapping("openai")
@Api(tags = "OpenAI服务")
public class ChatController {
    @Autowired
    ChatService chatService;

    @Autowired
    ChatAuthService authService;


    @PostMapping("chat")
    @ApiOperation(value = "聊天",notes = "")
    public Object doChat(@RequestBody ChatDTO dto){
        ChatParams params = new ChatParams();

        List<ChatMessage> messages = chatService.getContext(dto.getChatId(),dto.getWithContext());
        messages.add(chatService.buildUserMessage(dto.getContent()));
        params.setMessages(messages);
        // 执行
        ChatResult result = chatService.doChat(params, dto.getChatId());

        return chatService.simpleResult(result);
    }

    @ApiIgnore
    @PostMapping("chat/str")
    @ApiOperation(value = "聊天STR",notes = "")
    public Object doChatStr(@RequestBody ChatDTO dto){
        ChatParams params = new ChatParams();

        List<ChatMessage> messages = chatService.getContext(dto.getChatId(),dto.getWithContext());
        messages.add(chatService.buildUserMessage(dto.getContent()));
        params.setMessages(messages);
        // 执行
       String result = chatService.doChatStr(params, dto.getChatId());
       return result;
    }

    @PostMapping("edit/text")
    @ApiOperation(value = "文本编辑",notes = "")
    public Object doEditText(@Validated @RequestBody EditDTO dto){
        EditParams params = new EditParams();
        params.setInput(dto.getContent());
        params.setInstruction(dto.getTips());
        params.setModel(EditModelEnum.TEXT.getModel());
        EditResult result = chatService.doEdit(params);
        return chatService.simpleResult(result);
    }

    @PostMapping("edit/code")
    @ApiOperation(value = "代码编辑",notes = "")
    public Object doEditCode(@Validated @RequestBody EditDTO dto){
        EditParams params = new EditParams();
        params.setInput(dto.getContent());
        params.setInstruction(dto.getTips());
        params.setModel(EditModelEnum.CODE.getModel());
        EditResult result = chatService.doEdit(params);
        return chatService.simpleResult(result);
    }

    @PostMapping("draw")
    @ApiOperation(value = "图像绘制",notes = "")
    public Object doDraw(@Validated @RequestBody ImageDTO dto){
        ImageParams params = new ImageParams();
        // 设置描述
        params.setPrompt(dto.getTips());
        // 设置返回结果个数
        params.setN(dto.getN());
        ImageResult result = chatService.doDraw(params);
        return chatService.simpleResult(result);
    }
}
