package com.apuope.apuope_re.dto;

public class ResponseData<T> {
    public boolean success;
    public T data;

    public ResponseData(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
