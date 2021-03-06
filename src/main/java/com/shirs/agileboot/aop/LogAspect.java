package com.shirs.agileboot.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shirs.agileboot.annotation.OperationLogDetail;
import com.shirs.agileboot.model.OperationLog;
import com.shirs.agileboot.modules.system.service.LogService;
import com.shirs.agileboot.producer.MsgProducer;
import com.shirs.agileboot.common.utils.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 *
 * @author weiwenjun
 * @date 2018/9/12
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private LogService logService;

    @Autowired
    private MsgProducer msgProducer;

    /**
     * 此处的切点是注解的方式，也可以用包名的方式达到相同的效果
     * '@Pointcut("execution(* com.shirs.agileboot.modules.system.service.impl.*.*(..))")'
     */
    @Pointcut("@annotation(com.shirs.agileboot.annotation.OperationLogDetail)")
    public void operationLog(){}


    /**
     * 环绕增强，相当于MethodInterceptor
     */
    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;
        long time = System.currentTimeMillis();
        try {
            res =  joinPoint.proceed();
            time = System.currentTimeMillis() - time;
            return res;
        } finally {
            try {
                //方法执行完成后增加日志
                addOperationLog(joinPoint,res,time);
            }catch (Exception e){
                logger.error("LogAspect 操作失败：" + e.getMessage());
            }
        }
    }

    private void addOperationLog(JoinPoint joinPoint, Object res, long time){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        OperationLog operationLog = new OperationLog();
        operationLog.setRunTime(time);
        operationLog.setReturnValue(JSON.toJSONString(res));
        operationLog.setId(UUID.randomUUID().toString());
        operationLog.setArgs(JSON.toJSONString(joinPoint.getArgs()));
        operationLog.setCreateTime(DateUtils.dateTimeStrToDate(DateUtils.getFormatDateTime(new Date())));
        operationLog.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        operationLog.setUserId("admin(还是写死的)");
        operationLog.setUserName("admin(还是写死的)");
        OperationLogDetail annotation = signature.getMethod().getAnnotation(OperationLogDetail.class);
        if(annotation != null){
            operationLog.setLevel(annotation.level());
            operationLog.setDescription(getDetail(((MethodSignature)joinPoint.getSignature()).getParameterNames(),joinPoint.getArgs(),annotation));
            operationLog.setOperationType(annotation.operationType().getValue());
            operationLog.setOperationUnit(annotation.operationUnit().getValue());
        }
        //logService.insert(operationLog);
        //发送消息到mq
        JSONObject content = (JSONObject) JSON.toJSON(operationLog);
        msgProducer.sendMsg(content);

    }

    /**
     * 对当前登录用户和占位符处理
     * @param argNames 方法参数名称数组
     * @param args 方法参数数组
     * @param annotation 注解信息
     * @return 返回处理后的描述
     */
    private String getDetail(String[] argNames, Object[] args, OperationLogDetail annotation){

        Map<Object, Object> map = new HashMap<>(4);
        for(int i = 0;i < argNames.length;i++){
            map.put(argNames[i],args[i]);
        }

        String detail = annotation.detail();
        try {
            detail = "'" + "#{currentUserName}" + "'=》" + annotation.detail();
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                detail = detail.replace("{{" + k + "}}", JSON.toJSONString(v));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return detail;
    }

    @Before("operationLog()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        logger.info("进入方法前执行.....");
    }

    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "operationLog()")
    public void doAfterReturning(Object ret) {
        logger.info("方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("operationLog()")
    public void throwss(JoinPoint jp){
        logger.info("方法异常时执行.....");
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("operationLog()")
    public void after(JoinPoint jp){
        logger.info("方法最后执行.....");
    }

}