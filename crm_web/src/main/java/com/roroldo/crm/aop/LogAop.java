package com.roroldo.crm.aop;

import com.roroldo.crm.domain.SysLog;
import com.roroldo.crm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志记录
 * @author 落霞不孤
 */
@Component
@Aspect
public class LogAop {
    private Date visitTime;
    private Class clazz;
    private Method method;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService iSysLogService;

    //  前置通知
    @Before("execution(* com.roroldo.crm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        // 访问的类
        clazz = jp.getTarget().getClass();
        // 访问的方法名
        String methodName = jp.getSignature().getName();
        if (methodName.contains("UI")) {
            return;
        }
        // 方法的参数
        Object[] args = jp.getArgs();
        if (args != null && args.length != 0) {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        } else {
            method = clazz.getMethod(methodName);
        }
    }

    @After("execution(* com.roroldo.crm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) {
        long executionTime = System.currentTimeMillis() - visitTime.getTime();
        // 获取 url
        String url = "";
        if (clazz != null && method != null && clazz != LogAop.class) {
            // 去掉查看日志记录
            if ("com.roroldo.crm.controller.SysLogController".equals(clazz.getName()) && "findAll".equals(method.getName())) {
                return;
            }
            if (method.getName().contains("UI")) {
                return;
            }
            // 获取类上的 requestMapping
            RequestMapping  clazzAnnotation = ((RequestMapping) clazz.getAnnotation(RequestMapping.class));
            if (clazzAnnotation  != null) {
                String classValue = clazzAnnotation.value()[0];
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                String methodValue = methodAnnotation.value()[0];
                url = classValue + methodValue;
                // 获取访问的 ip 地址
                String ip = request.getRemoteAddr();
                // 获取操作者
                SecurityContext context = SecurityContextHolder.getContext();
                User user = ((User) context.getAuthentication().getPrincipal());
                String username = user.getUsername();
                SysLog sysLog = new SysLog();
                sysLog.setUsername(username);
                sysLog.setVisitTime(visitTime);
                sysLog.setIp(ip);
                sysLog.setUrl(url);
                sysLog.setExecutionTime(executionTime);
                sysLog.setMethod("[className] " + clazz.getName() + "[methodName] " + method.getName());
                iSysLogService.save(sysLog);
            }
        }
    }
}
