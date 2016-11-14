package cn.aynu.manage.struts2.interceptor;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

import cn.aynu.manage.service.RightService;
import cn.aynu.manage.util.ValidateUtil;

/**
 * ����ʱ �� ʹ�ã����Ȩ�޵�Rights���У� ����ֹ�Ѿ������ Ȩ���� ��ʧ����Ȩ�޵�append ��������util������ӣ�
 * @author yehao
 */
@Component
public class CatchUrlInterceptor implements Interceptor {
	private static final long serialVersionUID = -2567029236017133639L;

	@Resource
	private RightService rightService;
	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation arg0) throws Exception {
		ActionProxy ap = arg0.getProxy();
		//actionName
		String actionName = ap.getActionName();
		String nameSpace = ap.getNamespace();
		if(!ValidateUtil.isValid(nameSpace)||nameSpace.equals("/"))
		{
			nameSpace = "";
		}
		String url = nameSpace+"/"+actionName;
		//���Ȩ��(�õ�rightService)�����ݿ��rights��
		//���ַ�ʽ
		//(1)
		/*ApplicationContext ac = (ApplicationContext) arg0.getInvocationContext().getApplication().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		RightService rs = (RightService) ac.getBean("rightService");*/
//		//(2)
//		ServletContext sc = ServletActionContext.getServletContext();
//		ApplicationContext ac1 = WebApplicationContextUtils.getWebApplicationContext(sc);
		rightService.appendRightUrl(url);
		return arg0.invoke();
	}

}
