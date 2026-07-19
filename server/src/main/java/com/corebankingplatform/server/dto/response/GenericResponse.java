package com.corebankingplatform.server.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class GenericResponse<T> {
    private final boolean isSuccess;
    private final String errorMessage;
    private final T data;

    public GenericResponse(boolean isSuccess, String errorMessage, T data) {
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static <T> GenericResponse<T> success(T data) {
        return new GenericResponse<>(true, null, data);
    }

    public static <T> GenericResponse<T> failure(String errorMessage) {
        return new GenericResponse<>(false, errorMessage, null);
    }

    public static <T> GenericResponse<T> failure(String errorMessage, T data) {
        return new GenericResponse<>(false, errorMessage, null);
    }
}
