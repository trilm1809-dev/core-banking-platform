package com.corebankingplatform.server.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class GenericResponse<T> {
    private final Boolean isSuccess;
    private final String errorMessage;
    private final Optional<T> data;

    public GenericResponse(Boolean isSuccess, String errorMessage, Optional<T> data) {
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    // Success case
    public static <T> GenericResponse<T> success(T data) {
        return new GenericResponse<>(true, null, Optional.ofNullable(data));
    }

    // 🔹 Factory method cho thất bại
    public static <T> GenericResponse<T> failure(String errorMessage) {
        return new GenericResponse<>(false, errorMessage, Optional.empty());
    }

    // 🔹 Factory method cho thất bại có thêm data (ví dụ debug info)
    public static <T> GenericResponse<T> failure(String errorMessage, T data) {
        return new GenericResponse<>(false, errorMessage, Optional.ofNullable(data));
    }
}
