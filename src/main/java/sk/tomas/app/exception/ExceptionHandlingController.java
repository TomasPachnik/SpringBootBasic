package sk.tomas.app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by tomas on 06.01.2017.
 */

@EnableWebMvc
@RestController
@ControllerAdvice
public class ExceptionHandlingController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);
    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ServerMessage badCredentials(BadCredentialsException e) {
        return new ServerMessage("401", e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ServerMessage businessException(AccessDeniedException e) {
        return new ServerMessage("403", "Forbidden");
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public ServerMessage accessDenied(BusinessException e) {
        return new ServerMessage("BusinessException", e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ServerMessage handleAllException(Exception e) {
        UUID uuid = UUID.randomUUID();
        logger.error(uuid.toString(), e);
        return new ServerMessage(uuid.toString(), "interna chyba servera");
    }

    @RequestMapping(value = PATH)
    public void error(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (throwable != null) {
            throw throwable;
        }

        int status = response.getStatus();
        if (status != HttpServletResponse.SC_OK) {
            if (status == HttpServletResponse.SC_UNAUTHORIZED) {
                throw new BadCredentialsException("Unauthorized");
            } else {
                throw new Exception("nepriradeny http status: " + status);
            }
        }

    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
