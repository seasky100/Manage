package cn.aynu.manage.service;

import java.util.List;
import java.util.Set;

import cn.aynu.manage.vo.Pager;
import cn.aynu.manage.vo.User;
import cn.aynu.manage.vo.safe.Role;
import cn.aynu.manage.vo.safe.Right;
import cn.aynu.manage.vo.safe.Role;

public interface UserService {
	/**
	 * ���������б�
	 */
	List<User> findAllUsers();
	/**
	 * �����б�ҳ
	 * @param offset
	 * @return
	 */
	Pager<User> pageList(Integer offset);
	
	/**
	 * ҳ����ת
	 */
	Pager<User> getUserListByArgs(User user, Integer offset);
	
	/**
	 * ����id��ȡshop����
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);
	
	
	/**
	 * ɾ������
	 * @param id
	 */
	void delUser(Integer id);
	
	/**
	 * �����¼
	 * @param shop
	 */
	void addEntity(Object object);
	
	/**
	 * ����
	 * @param shop
	 */
	void update(Object object);
	
	/**
	 * ����Role
	 * @param role
	 */
	void updateRole(Role role);
	
	/**
	 * ��֤���û���Ϣ
	 * @param user
	 * @return
	 */
	User chekUser(User user);
	/**
	 * �õ�δ���Ȩ�޵� ��ɫ
	 * @param set 
	 * @return
	 */
	List<Role> getWithOutAuthors(Set<Role> set);
	
	/**
	 * �޸��û���Ȩ��Ϣ
	 * @param model
	 * @param ids
	 */
	public void updateAuthorize(User user, String[] ids);
	
	/**
	 * ����Role����
	 * @return
	 */
	List<Role> returnAllRoles();
	
	/**
	 * ��ɫ�б�ҳ
	 * @param offset
	 * @return
	 */
	Pager<Role> rolePageList(Integer offset);
	
	/**
	 * ��ɫҳ����ת
	 * @param role
	 * @param offset
	 * @return
	 */
	Pager<Role> getRoleListByArgs(Role role, Integer offset);
	/**
	 * Ȩ��ҳ����ת
	 * @param role
	 * @param offset
	 * @return
	 */
	Pager<Right> getRightListByArgs(String queryCommon,Right right, Integer offset);
	
	/**
	 * ����Id��ȡ��ɫ
	 * @param id
	 * @return
	 */
	Role getRoleById(Integer id);
	
	/**
	 * ɾ����ɫ
	 * @param id
	 */
	void delRole(Integer id);
	
	/**
	 * Ȩ���б�
	 * @param offset
	 * @return
	 */
	Pager<Right> rightPageList(Integer offset);
	
	/**
	 * ����id��ȡȨ��
	 * @param rightId
	 * @return
	 */
	Right getRightById(Integer rightId);
	
	/**
	 * �õ���ɫû�е�Ȩ��
	 * @param rights
	 * @return
	 */
	List<Right> getWithOutRights(Set<Right> rights, List<Right> ownRights);
	
	/**
	 * �޸Ľ�ɫ��Ȩ
	 * @param role
	 * @param ownRightsIds
	 */
	void updateRoleAuthorize(Role role, String[] ownRightsIds);
	
}
