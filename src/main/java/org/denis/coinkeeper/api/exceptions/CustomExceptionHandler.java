package org.denis.coinkeeper.api.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;
import java.util.logging.Logger;

@Log4j2
@RequiredArgsConstructor
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception ex, WebRequest request) throws Exception {

        log.error("Exception during execution of application", ex);

        return handleException(ex, request);
    }

//    @ExceptionHandler(BindException.class)
//    public ResponseEntity<Object> bindExceptionHandler(BindException exception,
//                                                       WebRequest request,
//                                                       Locale locale) {
//
//        log.info("BindException occured: URL={}", request.getContextPath());
//
//        String message = this.messageSource.getMessage("error.400.title", new Object[0],"error.400.title",locale);
//
//        ProblemDetail problemDetail = ProblemDetail
//                .forStatusAndDetail(HttpStatus.BAD_REQUEST,message);
//
//        problemDetail.setProperty("errors",
//                exception.getAllErrors().stream()
//                        .map(ObjectError::getDefaultMessage)
//                        .toList());
//
//        return ResponseEntity.badRequest()
//                .body(problemDetail);
//    }
}