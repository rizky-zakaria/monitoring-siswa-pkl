package com.example.monitoringsiswapkl.model;

import java.util.List;

public class ResponseNote {
    String status, message;
    List<DataNote> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataNote> getData() {
        return data;
    }

    public void setData(List<DataNote> data) {
        this.data = data;
    }
}
