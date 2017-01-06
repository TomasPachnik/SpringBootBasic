package sk.tomas.app.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by tomas on 06.01.2017.
 */


@Aspect
public class LoggingAspect {

    @Around(value = "@within(sk.tomas.app.annotation.Logger) || @annotation(sk.tomas.app.annotation.Logger)")
    public Object aroundInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType().getName());

        if (logger != null) {
            if (logger.isDebugEnabled()) {
                logger.debug(startLogMessage(joinPoint) + " args: " + Arrays.toString(joinPoint.getArgs()));
            }
            if (logger.isInfoEnabled() && !logger.isDebugEnabled()) {
                logger.info(startLogMessage(joinPoint));
            }
        }

        Object result = joinPoint.proceed();

        if (logger != null) {
            if (logger.isDebugEnabled()) {
                logger.debug(stopLogMessage(joinPoint) + " result: " + result);
            }
            if (logger.isInfoEnabled() && !logger.isDebugEnabled()) {
                logger.info(stopLogMessage(joinPoint));
            }
        }
        return result;
    }

    private String startLogMessage(ProceedingJoinPoint joinPoint) {
        return defaultLogMessage("start", joinPoint);
    }

    private String stopLogMessage(ProceedingJoinPoint joinPoint) {
        return defaultLogMessage("stop", joinPoint);
    }

    private String defaultLogMessage(String text, ProceedingJoinPoint joinPoint) {
        return text + " executing: " + joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + joinPoint.getSignature().getName();
    }

}
