package com.echochain.EchoChainAPI.Responses;

public class BaseResponse {

    private Object data;
    private String message;
    private boolean error = true;

    public BaseResponse(Object data, String message, boolean error){
        this.data = data;
        this.message = message;
        this.error = error;
    }

    public BaseResponse(){}

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", error=" + error +
                '}';
    }
}
