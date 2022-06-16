package likelion.guestbook.controller;

import likelion.guestbook.dto.ErrorResponseDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgument(RuntimeException e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(e.getMessage());
        return ResponseEntity.badRequest().body(errorResponseDto);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorResponseDto> handleDataNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleUnknownError() {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("알 수 없는 에러가 발생했습니다.");
        return ResponseEntity.internalServerError().body(errorResponseDto);
    }
}
