package com.asklepios.backend_service.util;

import com.asklepios.backend_service.model.generated.pojo.ApLov;
import com.asklepios.backend_service.model.generated.pojo.ApLovValues;
import com.asklepios.backend_service.model.generated.pojo.ApPatient;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
public class PatientAccessAspect {

    @Around("execution(* com.asklepios.backend_service.controller.PatientController.getPatient(..))")
    public Object logPatientAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        logAccess(request,joinPoint);
        Object result = joinPoint.proceed();
        return result;
    }
    @Async
    public void logAccess(String username, String patientId, String ipAddress, Object[] args,Parameter[] parameters) {
        System.out.println("User " + username + " accessed patient record " + patientId + " from IP " + ipAddress);
    }

    @Async
    public void logAccess( HttpServletRequest request,ProceedingJoinPoint joinPoint) {
try {
    String username = (String) request.getSession().getAttribute("loggedInUser");
    //String userName = request.getHeader("User-Agent");


    // Access method parameters
    Object[] methodArgs = joinPoint.getArgs();
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    Parameter[] parameters = method.getParameters();
    for (int i = 0; i < parameters.length; i++) {
        String parameterName = parameters[i].getName();
        Object argument = joinPoint.getArgs()[i];
        System.out.println("Parameter: " + parameterName + ", Value: " + argument);
    }



    Object result = joinPoint.proceed();
    ResponseEntity<?> re = (ResponseEntity<?>) result;
    ApPatient patient = (ApPatient) re.getBody();
    if (patient != null) {
        logAccess(username, patient.getKey() + " " + patient.getPatientMrn(), request.getRemoteAddr(), methodArgs,parameters);
    } else {
        logAccess(username, "searched for " + "", request.getRemoteAddr(), methodArgs,parameters);
    }
}
catch (Exception ex)
{
  ex.printStackTrace();
} catch (Throwable e) {
    throw new RuntimeException(e);
}

    }

}