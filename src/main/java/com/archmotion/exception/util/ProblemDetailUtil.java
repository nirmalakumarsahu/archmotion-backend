package com.archmotion.exception.util;

import com.archmotion.exception.dto.ApiError;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;

@UtilityClass
public class ProblemDetailUtil {

    public ProblemDetail build(HttpStatus httpStatus, ApiError apiError) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);
        problemDetail.setTitle(apiError.title());
        problemDetail.setDetail(apiError.detail());
        problemDetail.setProperty("code", apiError.code());

        return problemDetail;
    }

    public ProblemDetail build(HttpStatus httpStatus, Exception exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(httpStatus);

        if (exception instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            problemDetail.setTitle("Validation Failed");
            problemDetail.setDetail("Validation Failed");
            problemDetail.setProperty("errors", methodArgumentNotValidException.getBindingResult()
                    .getFieldErrors().stream()
                    .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                    .toList()
            );
        } else {
            problemDetail.setTitle(exception.getMessage());
            problemDetail.setDetail(exception.getMessage());
        }
        return problemDetail;
    }

}
