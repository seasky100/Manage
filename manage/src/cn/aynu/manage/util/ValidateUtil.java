package cn.aynu.manage.util;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.aynu.manage.struts2.UserAware;
import cn.aynu.manage.vo.User;
import cn.aynu.manage.vo.safe.Right;

/**
 * ��֤�ַ������������飬���ϣ������ʼ��Ƿ���Ч
 */
public class ValidateUtil {
	/**
	 * �ַ����Ƿ���Ч
	 */
	public static boolean isValid(String src) {
		// Ϊ�շ���false
		return !(src == null || "".equals(src.trim()));
	}

	/**
	 * ���϶����Ƿ���Ч����ת�Ͷ���
	 */
	public static boolean isValid(Collection col) {
		return !(col == null || col.isEmpty());
	}

	/**
	 * ���������Ƿ���Ч
	 */
	public static boolean isValid(Object[] values) {
		if (values == null || values.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * �Ƿ���һ�������ʼ�
	 */
	public static boolean isEmail(String email) {
		String string = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		if (email.matches(string)) {
			return true;
		}
		return false;
	}

	/**
	 * �Ƿ���һ�����ڸ�ʽ���ַ���
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		// ָ�����ڸ�ʽΪ��λ��/��λ�·�/��λ���ڣ�ע��yyyy/MM/dd���ִ�Сд��
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// ����lenientΪfalse.
			// ����SimpleDateFormat��ȽϿ��ɵ���֤���ڣ�����2007/02/29�ᱻ���ܣ���ת����2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
			// ���throw java.text.ParseException����NullPointerException����˵����ʽ����
			convertSuccess = false;
		}
		return convertSuccess;
	}

	
	
	/**
	 * �ж��Ƿ���Ȩ��
	 */
	public static boolean hasRight(HttpServletRequest req,String namespace,String actionName,Object action)
	{
		if(!ValidateUtil.isValid(namespace)
				||"/".equals(namespace))
		{
			namespace="";
		}
		String url = namespace+"/"+actionName;
		HttpSession session = req.getSession();
		Map<String,Right> map = (Map<String, Right>) req.getServletContext().getAttribute("all_rights_map");
		//System.out.println(url);
		Right r = map.get(url);
		//ע��user
		User user = (User) session.getAttribute("user");
		if(action!=null && action instanceof UserAware && user!=null)
		{
			((UserAware)action).setUser(user);
		}
		//������Դ��
		if(r==null || r.isCommon())
		{
			return true;
		}
		else {
			//��¼��
			if(user == null)
			{
				return false;
			}
			else{
				//��������Ա��
				if(user.isSuperAdmin())
				{
					return true;
				}
				else{
					//��Ȩ�ޣ�
					if(user.hasRight(r))
					{
						return true;
					}
					else{
						return false;
					}
				}
			}
		}
	}
}
