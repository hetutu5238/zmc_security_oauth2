package cn.com.megalith.common;

/**
 * @Description:
 * @author: zhoum
 * @Date: 2018-11-22
 * @Time: 14:49
 */
public class ResponseData {

    private String code;
    private String message;
    private Object data;

    public ResponseData(String code , String message , Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
