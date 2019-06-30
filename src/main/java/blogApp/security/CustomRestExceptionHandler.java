package blogApp.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ UsernameNotFoundException.class })
    protected ResponseEntity<Object> handleUserNameNotFoundException(final UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getLocalizedMessage());
    }
}
