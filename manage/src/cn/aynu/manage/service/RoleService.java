package cn.aynu.manage.service;

import java.util.List;
import java.util.Set;

import cn.aynu.manage.vo.safe.Right;

public interface RoleService {
	/**
	 * �õ���ǰ��ɫ û�е�Ȩ��
	 * @return
	 */
	public List<Right> getWithoutRights(Set<Right> rights);
}
