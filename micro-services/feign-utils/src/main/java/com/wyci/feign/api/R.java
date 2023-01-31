package com.wyci.feign.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 响应信息主体
 *
 * @author ruoyi
 * @common 对外接口统一返回格式，用于远程接口调用及对外接口
 */
public class R<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 扩展值 **/
    protected HashMap<String,Object> extensionMap;

    /** 响应值 **/
    @ApiModelProperty(value = "响应码（0为成功）",required=true)
    protected int code;

    /** 错误原因 **/
    @ApiModelProperty(value = "原因（描述引起错误的原因，用于显示给客户端）",required=true)
    protected String reason;

    /** 详细错误信息 **/
    @ApiModelProperty(value = "描述（错误描述的更多信息，用于服务端排查异常）")
    protected String message;

    /** 业务数据 **/
    @ApiModelProperty(value = "查询结果数据",required=true)
    protected T data;

    public static <T> R<T> ok()
    {
        return restResult(null, ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),"",null);
    }

    public static <T> R<T> ok(T data)
    {
        return restResult(data, ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),"",null);
    }
    public static <T> R<T> ok(T data,int code)
    {
        return restResult(data, code, ResponseCode.SUCCESS.getMessage(),"",null);
    }

    public static <T> R<T> ok(T data,HashMap<String,Object> extensionMap)
    {
        return restResult(data, ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),"",extensionMap);
    }

    public static <T> R<T> ok(T data, String reason)
    {
        return restResult(data, ResponseCode.SUCCESS.getCode(), reason,"",null);
    }

    public static <T> R<T> ok(T data, String reason,String message)
    {
        return restResult(data, ResponseCode.SUCCESS.getCode(), reason,message,null);
    }

    public static <T> R<T> ok(T data, String reason, HashMap<String,Object> extensionMap)
    {
        return restResult(data, ResponseCode.SUCCESS.getCode(), reason,"",extensionMap);
    }

    public static <T> R<T> ok(T data, String reason, String message,HashMap<String,Object> extensionMap)
    {
        return restResult(data, ResponseCode.SUCCESS.getCode(), reason,message,extensionMap);
    }

    public static <T> R<T> fail(String reason)
    {
        return restResult(null, ResponseCode.ERROR.getCode(), reason,"",null);
    }

    public static <T> R<T> fail(int code, String reason)
    {
        return restResult(null, code, reason,"",null);
    }

    public static <T> R<T> fail(int code, String reason,String message)
    {
        return restResult(null, code, reason,message,null);
    }

    public static <T> R<T> fail(int code, String reason,String message, HashMap<String,Object> extensionMap)
    {
        return restResult(null, code, reason,message, extensionMap);
    }

    /**
     * 鉴权失败  没有Token Token过期
     * @param msg
     * @return
     */
    public static R unauthorized(String msg){
        return restResult(null, ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getMessage(),msg,null);
    }

    private static <T> R<T> restResult(T data, int code, String reason,String message, HashMap<String,Object> extensionMap)
    {
        R<T> result = new R<>();
        result.setExtensionMap(extensionMap);
        result.setCode(code);
        result.setData(data);
        result.setReason(reason);
        result.setMessage(message);
        return result;
    }

    /**
     * 将R转换成json
     * @return
     */
    public String toJson()
    {
        JSONObject result = new JSONObject();
        result.put("code",this.code);
        result.put("reason",this.reason);
        result.put("message",this.message);
//        if(StringUtils.isNotEmpty(this.message))
//        {
//            result.put("message",this.message);
//        }
        if(this.data != null)
        {
            result.put("data", this.data);
        }
        if(this.extensionMap != null)
        {
            result = convert(result,this.extensionMap);
        }
        return result.toJSONString();
    }

    /**
     * 设置自定义响应体，可以设置响应网络状态码，和响应头
     * 一般不需要使用，使用通用R.ok()/R.fail(...)即可
     * @param status
     * @param headers
     * @return
     */
    public ResponseEntity toResponseEntity(HttpStatus status,HttpHeaders headers)
    {
        return ResponseEntity.status(status).headers(headers).body(toJson());
    }

    public ResponseEntity toResponseEntity(HttpStatus status)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return toResponseEntity(status,headers);
    }

    public ResponseEntity toResponseEntity()
    {
        return toResponseEntity(HttpStatus.OK);
    }

    /**
     * 将hashMap转换成JsonObject
     * @param obj
     * @param extensionMap
     * @return
     */
    public static JSONObject convert(JSONObject obj,HashMap<String,Object> extensionMap)
    {
        if(!extensionMap.isEmpty())
        {
            extensionMap.keySet().forEach(k->{
                obj.put(k,extensionMap.get(k));
            });
        }
        return obj;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public HashMap<String, Object> getExtensionMap() {
        return extensionMap;
    }

    public void setExtensionMap(HashMap<String, Object> extensionMap) {
        this.extensionMap = extensionMap;
    }
}
