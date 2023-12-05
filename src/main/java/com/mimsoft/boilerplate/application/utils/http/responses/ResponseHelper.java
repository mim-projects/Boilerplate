package com.mimsoft.boilerplate.application.utils.http.responses;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class ResponseHelper {
    public static Response Error(Status status, String error) {
        return Response.ok(ResponseString.error(error)).status(status).build();
    }

    public static Response ErrorMessage(Status status, String error) {
        return Response.ok(ResponseString.errorMessage(error)).status(status).build();
    }

    public static Response SuccessMessage(String key, String content) {
        return Response.ok(ResponseString.response(key, content)).build();
    }

    public static Response SuccessObject(String content) {
        return Response.ok(ResponseString.responseObject("data", content)).build();
    }

    public static Response SuccessMessage(String content) {
        return Response.ok(ResponseString.response("data", content)).build();
    }
}