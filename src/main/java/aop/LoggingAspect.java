package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Example of @Before - logs before method execution in controllers
    @Before("execution(* com.example.demo.controller.*.*(..))")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        logger.debug("Before executing: " + joinPoint.getSignature().getName());
    }

    // Example of @After - logs after method execution in services
    @After("execution(* com.example.demo.service.*.*(..))")
    public void logAfterServiceMethod(JoinPoint joinPoint) {
        logger.debug("After executing: " + joinPoint.getSignature().getName());
    }

    // Specific aspect for login/logout
    @Before("execution(* com.example.demo.controller.AuthController.login(..))")
    public void beforeLogin(JoinPoint joinPoint) {
        logger.info("Attempting login...");
    }

    @After("execution(* com.example.demo.controller.AuthController.logout(..))")
    public void afterLogout(JoinPoint joinPoint) {
        logger.info("User session terminated");
    }
}
