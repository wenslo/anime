package org.wen.dto;

/**
 * Created by wen on 2016/6/19.
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

    public Result(int result, String message, Object data) {

        this.result = result;
        this.message = message;
        this.data = data;
    }
}
