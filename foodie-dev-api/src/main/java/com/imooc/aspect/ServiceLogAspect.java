package com.imooc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class ServiceLogAspect {
    public static final Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);
    /**
     * 1.前置通知
     * 2.后置通知
     * 3.环绕通知
     * 4.异常通知
     * 5.最终通知
     */
    /**
     * 第一处 代表返回的类型 *表示返回所有类型
     * 第二处 AOP所要切的面
     * 第三处 ..代表可以切他的下面的子包
     * 第四处 *代表类名 ，*代表下面的所有类
     * 第五处*(..) *代表所有的方法名（）代表的是里面的参数，里面的两个点代表的是任何参数
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.imooc.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint)throws Throwable{
        log.info("==========开始之前{}.{}===========",
                joinPoint.getTarget().getClass(),joinPoint.getSignature().getName());
        //记录时间
        long begin = System.currentTimeMillis();
        //执行过程
        Object result = joinPoint.proceed();
        //记录时间
        long end = System.currentTimeMillis();
        long takeTime = begin - end;

        //对于耗时进行判断，不同的返回。
        if(takeTime>3000){
            log.error("=====结果耗时{}======",takeTime);
        }
        else if(takeTime>2000){
            log.warn("=====结果耗时{}======",takeTime);
        }
        else {
            log.info("=====结果耗时{}======",takeTime);
        }
        //返回结果
        return result;
    }
}
