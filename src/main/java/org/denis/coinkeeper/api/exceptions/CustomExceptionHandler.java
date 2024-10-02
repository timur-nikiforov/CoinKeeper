package org.denis.coinkeeper.api.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServerErrorException.class)
    protected ResponseEntity<ErrorDto> handleServerErrorException(ServerErrorException ex) {

        return ResponseEntity.internalServerError()
                .body(ErrorDto
                        .builder()
                        .error("Server error")
                        .errorDescription(List.of(ex.getMessage()))
                        .build()
                );
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ErrorDto> handleBadRequestException(BadRequestException ex) {

        if (ex.getErrors().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ErrorDto
                            .builder()
                            .error("Bad request")
                            .errorDescription(List.of(ex.getMessage()))
                            .build()
                    );
        } else {
            return ResponseEntity.badRequest()
                    .body(ErrorDto
                            .builder()
                            .error("Bad request")
                            .errorDescription(ex.getErrors())
                            .build()
                    );
        }
    }
}