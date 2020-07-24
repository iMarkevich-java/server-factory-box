package com.markevich.factorybox.net;

public enum ResponseCode {

    UserAuthorized(100, "Authorized"),

    UserUnauthorized(401, "Unauthorized"),

    OkCode(200, "OK"),

    BadRequestCode(400, "Bad web.common.Request"),

    NotFoundCode(404, "Not Found"),

    ServerErrorCode(500, "Internal Server Error");

    private final int code;

    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
