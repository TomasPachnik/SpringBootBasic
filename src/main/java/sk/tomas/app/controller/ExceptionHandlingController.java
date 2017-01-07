package sk.tomas.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sk.tomas.app.exception.ServerMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by tomas on 06.01.2017.
 */

@ResponseBody
@EnableWebMvc
@ControllerAdvice
public class ExceptionHandlingController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);


    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ServerMessage badCredentials(BadCredentialsException e) {
        UUID uuid = UUID.randomUUID();
        logger.error(uuid.toString(), e);
        return new ServerMessage(uuid, " 401 - bad credentials");
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ServerMessage handleAllException(Exception e) {
        UUID uuid = UUID.randomUUID();
        logger.error(uuid.toString(), e);
        return new ServerMessage(uuid, "interna chyba servera");
    }

}
