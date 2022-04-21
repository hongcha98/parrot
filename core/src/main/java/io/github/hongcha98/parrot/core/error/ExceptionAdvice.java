package io.github.hongcha98.parrot.core.error;


import io.github.hongcha98.parrot.common.error.ParrotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(ParrotException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String parrotExceptionHandler(ParrotException parrotException) {
        LOG.error("", parrotException);
        return parrotException.getMessage();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String throwableHandler(Throwable throwable) {
        LOG.error("", throwable);
        return "parrot server error";
    }
}
