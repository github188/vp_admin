/**
 * <p>Title: LogAspect.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��7��22��
 * @version 1.0.0
 */
package cn.ittc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * <p>Title: LogAspect</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��6��7��
 */
@Component(value="aspectBean")
public class LogAspect {
	public void doAfter(JoinPoint jp) {
		Object[] args = jp.getArgs();
		if (args.length > 0) {
//			System.out.println("����:" + args[0].toString());
		}
		System.out.println(jp.getTarget());
		
	}

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long time = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		time = System.currentTimeMillis() - time;
		System.out.println("process time: " + time + " ms");
		return retVal;
	} 
	
	public void doBefore(JoinPoint jp) {  
        System.out.println("log ��ʼ���еķ���: "  
                + jp.getTarget().getClass().getName() + "."  
                + jp.getSignature().getName());  
    }
	public void doThrowing(JoinPoint jp, Throwable ex) {  
 
        sendEx(ex.getMessage());
    }  
  
    private void sendEx(String ex) {  

    } 

}
