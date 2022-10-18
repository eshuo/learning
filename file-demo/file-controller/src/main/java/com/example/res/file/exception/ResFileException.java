package com.example.res.file.exception;

import com.example.res.file.message.FileMessage;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-10-17 18:00
 * @Version V1.0
 */
public class ResFileException extends RuntimeException {
    private static final long serialVersionUID = -8271924000933150241L;


    public ResFileException(String message) {
        super(String.format(FileMessage.FILE_ERROR.getMessage(), message));
    }
//
//
//    public ResFileException(BaseMessageInterface message, Object... parameters) {
//        super(message, parameters);
//    }
//
//    public ResFileException(Throwable cause, Object... parameters) {
//        super(FileMessage.FILE_ERROR, cause, parameters);
//    }


}
