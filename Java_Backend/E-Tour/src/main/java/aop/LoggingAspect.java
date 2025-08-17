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


    @Before("execution(* controller.CategoryController.getAllCategories(..))")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        logger.debug("Before executing controller method: " + joinPoint.getSignature());
    }
 

    // After any service method
    @After("execution(* controller.CategoryController.getAllCategories(..))") 
    public void logAfterServiceMethodA(JoinPoint joinPoint) {
        logger.debug("After executing service method: " + joinPoint.getSignature());
    }
    
   
    
    // Specific: Before login
    @Before("execution(* controller.AuthenticationController.login(..))")
    public void beforeLogin(JoinPoint joinPoint) {
        logger.info("Attempting login for user...");
    }

    // Specific: After logout
    @After("execution(* controller.AuthenticationController.logout(..))")
    
    public void afterLogout(JoinPoint joinPoint) {
        logger.info("User session terminated.");
    }
}
