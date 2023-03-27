package com.wyci.opendemo.service.impl;

import com.wyci.opendemo.service.ChatAuthService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultChatAuthServiceImpl implements ChatAuthService {
    @Override
    public String getUserId() {
        return ChatAuthService.super.getUserId();
    }

    @Override
    public String getUserName() {
        return ChatAuthService.super.getUserName();
    }
}
