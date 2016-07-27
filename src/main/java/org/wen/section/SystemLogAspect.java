package org.wen.section;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.wen.entity.User;
import org.wen.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * 一个切点类
 * 2016年7月26日18:41:06
 * 温海林
 */
@Aspect
@Component
public class SystemLogAspect {
    private static final String USER = "user";
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

    //Service层切点
    @Pointcut("@annotation(org.wen.section.SystemServiceLog)")
    public void serviceAspect() {

    }

    //Controller层切点
    @Pointcut("@annotation(org.wen.section.SystemControllerLog)")
    public void controllerAspect() {

    }

    /**
     * 前置通知，用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 表示目标类连接点对象
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //得到用户信息
        User user = (User) session.getAttribute(USER);
        //获取请求IP
        String ip = request.getRemoteAddr();
        try {
            /*=============打印输出日志信息=============*/
            logger.info("=============Controller层前置通知开始============");
            logger.info("C---请求方法：{}", joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
            logger.info("C---方法描述：{}", getControllerMethodDescription(joinPoint));
            logger.info("C---请求人：{}", user.getName());
            logger.info("C---请求IP：", ip);
            logger.info("C---前置通知结束！");
        } catch (Exception e) {
            logger.error("==C-前置异常通知==");
            logger.error("C---异常信息：{}", e.getMessage());
        }
    }

    /**
     * 异常通知，用于拦截service层记录异常日志
     *
     * @param joinPoint 表示目标类连接点对象
     * @param e 异常
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取用户
        User user = (User) session.getAttribute(USER);
        //获取请求IP
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数，并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params += JsonUtil.toJson(joinPoint.getArgs()[i]) + ";";
            }
        }
        try {
            logger.info("=======Service层异常通知开始=======");
            logger.info("S---异常代码：{}", e.getClass().getName());
            logger.info("S---异常信息：{}", e.getMessage());
            logger.info("S---异常方法：{}", joinPoint.getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
            logger.info("S---方法描述", getServiceMthodDescription(joinPoint));
            logger.info("S---请求人：{}", user.getName());
            logger.info("S---请求IP：{}", ip);
            logger.info("S---请求参数：{}", params);
            logger.info("===========异常通知结束==========");
        } catch (Exception e1) {
            logger.error("====一异常通知异常====");
            logger.error("S---异常信息：{}", e1.getMessage());
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methonName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if(method.getName().equals(methonName)){
                Class[] clazzs = method.getParameterTypes();
                if(clazzs.length == arguments.length){
                    description = method.getAnnotation(SystemServiceLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog. class).description();
                    break;
                }
            }
        }
        return description;
    }
}
