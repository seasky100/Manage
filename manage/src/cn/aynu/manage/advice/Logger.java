package cn.aynu.manage.advice;

import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;

import com.opensymphony.xwork2.ActionContext;

import cn.aynu.manage.service.LogService;
import cn.aynu.manage.util.StringUtil;
import cn.aynu.manage.vo.Log;
import cn.aynu.manage.vo.User;

/**
 *��־��¼�ǣ�logger
 */
public class Logger {
	/**
	 * ��¼
	 * @param pjp������֪ͨ��Ҫ�����ӵ�
	 * @return �������÷���
	 */
	@Resource
	private LogService logService;
	public Object record(ProceedingJoinPoint pjp)
	{
		//��ʼ������ʱ��
		Log log = new Log();
		//���ò�����
		ActionContext ac = ActionContext.getContext();
		if(ac!=null){
			Map<String,Object> sessionMap = ac.getSession();
	
		if(sessionMap!=null)
		{
			User user = (User) sessionMap.get("user");
			if(user!=null)
			{
				log.setOperator(user.getId()+":"+user.getUsername());
			}
		}
		//���ò�������(����)
		log.setOperName(pjp.getSignature().getName());
		//���ò�������
		Object[] params = pjp.getArgs();
		log.setOperParams(StringUtil.arr2String(params));
		//���ö��󷽷�
		try {
			Object ret = pjp.proceed();
			//���ò������
			log.setOperResult("success");
			if(ret!=null)
			{
				//���÷���ֵ
				log.setResultMsg(ret.toString());
			}
			return ret;
		} catch (Throwable e) {
			e.printStackTrace();
			log.setOperResult("failure");
			log.setResultMsg(e.getMessage());
		}finally {
			logService.saveEntity(log);
		}
		
		}
		else{
			try {
				Object ret = pjp.proceed();
				return ret;
			} catch (Throwable e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
}
