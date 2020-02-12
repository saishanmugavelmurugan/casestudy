package com.rbs.model;

import org.springframework.http.HttpStatus;

public class RestApiStatus {
    private HttpStatus httpStatus;
    private String statusCode;
    private String status;
    private String message;

    public RestApiStatus(HttpStatus httpStatus, String statusCode, String status, String message) {
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RestApiError{" +
                "httpStatus=" + httpStatus +
                ", statusCode='" + statusCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
