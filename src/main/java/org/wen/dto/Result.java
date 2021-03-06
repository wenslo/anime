package org.wen.dto;

/**
 * Ajax结果类，向前台返回结果进行判断
 * 2016年7月27日16:31:18
 * @author 温海林
 */
public class Result {
    private int result;
    private String message;
    private Object data;

    public Result() {
    }

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
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
