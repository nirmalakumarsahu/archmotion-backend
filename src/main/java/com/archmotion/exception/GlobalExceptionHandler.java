package com.archmotion.exception;

import com.archmotion.exception.custom.*;
import com.archmotion.exception.util.ProblemDetailUtil;
import org.springframework.http.HttpStatus;
import com.archmotion.exception.dto.ApiError;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceOperationNotAllowException.class)
    public ProblemDetail handleResourceOperationNotAllowException(
            ResourceOperationNotAllowException resourceOperationNotAllowException) {
        return ProblemDetailUtil.build(HttpStatus.CONFLICT,
                resourceOperationNotAllowException);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ProblemDetail handleUnauthorizedException(UnauthorizedException unauthorizedException) {
        return ProblemDetailUtil.build(HttpStatus.UNAUTHORIZED,
                unauthorizedException.getApiError());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return ProblemDetailUtil.build(HttpStatus.NOT_FOUND,
                resourceNotFoundException.getApiError());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ProblemDetail handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException resourceAlreadyExistsException) {
        return ProblemDetailUtil.build(HttpStatus.CONFLICT,
                resourceAlreadyExistsException.getApiError());
    }

    @ExceptionHandler(ApiException.class)
    public ProblemDetail handleApiException(ApiException apiException) {
        return ProblemDetailUtil.build(HttpStatus.BAD_REQUEST,
                apiException.getApiError());
    }

    @ExceptionHandler(FileStorageException.class)
    public ProblemDetail handleFileStorageException(FileStorageException fileStorageException) {
        return ProblemDetailUtil.build(HttpStatus.INTERNAL_SERVER_ERROR,
                fileStorageException.getApiError());
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ProblemDetail handleInvalidRefreshTokenException(InvalidRefreshTokenException invalidRefreshTokenException) {
        return ProblemDetailUtil.build(HttpStatus.UNAUTHORIZED,
                invalidRefreshTokenException.getApiError());
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequestException(BadRequestException badRequestException) {
        return ProblemDetailUtil.build(HttpStatus.BAD_REQUEST,
                badRequestException.getApiError());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleAuthenticationException(AuthenticationException authenticationException) {
        return ProblemDetailUtil.build(HttpStatus.UNAUTHORIZED,
                ApiError.build("User not Authorized", "Invalid username or password", "INVALID_USERNAME_OR_PASSWORD"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ProblemDetailUtil.build(HttpStatus.BAD_REQUEST, methodArgumentNotValidException);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception exception) {
        return ProblemDetailUtil.build(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

}
