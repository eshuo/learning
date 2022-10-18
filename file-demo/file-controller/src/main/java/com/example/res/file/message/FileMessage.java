package com.example.res.file.message;

/**
 * @Description
 * @Author wangshuo
 * @Date 2022-10-17 18:02
 * @Version V1.0
 */
public enum FileMessage  {

    FILE_ERROR(420, " 文件异常: %s"),
    ;

    private int code;

    private String message;

    FileMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static FileMessage judgeValue(String code) {

        FileMessage[] values = FileMessage.values();
        for (FileMessage value : values) {
            if (value.name().equals(code)) {
                return value;
            }
        }
        return null;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
