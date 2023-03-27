package com.wyci.opendemo.api.chat;

import com.dtflys.forest.annotation.*;
import com.dtflys.forest.http.ForestResponse;
import com.wyci.opendemo.params.chat.ChatParams;
import com.wyci.opendemo.params.chat.ChatResult;

import java.util.Map;

/**
 * @author kindear
 * 聊天相关API
 */

public interface ChatApi {
    // 新建聊天接口
    @Post("#{openai.chat.host}/v1/chat/completions")
    ForestResponse<ChatResult> ChatCompletions(@Header Map<String, Object> headers, @JSONBody ChatParams params);

    @Post("#{openai.chat.host}/v1/chat/completions")
    String ChatCompletionsStr(@Header Map<String, Object> headers, @JSONBody ChatParams params);
}
