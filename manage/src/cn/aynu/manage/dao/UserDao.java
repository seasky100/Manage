package cn.aynu.manage.dao;

import java.util.List;

import cn.aynu.manage.vo.Pager;
import cn.aynu.manage.vo.User;
import cn.aynu.manage.vo.safe.Right;
import cn.aynu.manage.vo.safe.Role;

public interface UserDao {
	/**
	 * �����û��б�
	 */
	List<User> findAllUsers(String hql, Object...objects);
	
	/**
	 * �û��б�ҳ
	 * @param hql
	 * @param hqlCount
	 * @param offset
	 * @return
	 */
	Pager<User> pagelist(String hql, String hqlCount, Integer offset);
	
	/**
	 * ����id��ȡUser����
	 * @param id
	 * @return
	 */
	User getUser(Integer id);

	/**
	 * ����
	 * @param User
	 */
	void update(Object object);

	/**
	 * ɾ���û�
	 * @param id
	 */
	void delUser(User user);

	/**
	 * ����û�
	 * @param User
	 */
	void addUser(User user);
	
	//��ѯ��ɫ
	User chekUser(String hql, User user);

	/**
	 * ����ʵ�弯�ϸ���Hql�Ͳ���
	 * @param hql
	 * @return List<T>
	 */
	@SuppressWarnings("rawtypes")
	List findEntityByHql(String hql, Object...objects);
	
	/**
	 * ��ɫ�б�ҳ
	 * @param hql
	 * @param hqlCount
	 * @param offset
	 * @return
	 */
	Pager<Role> rolePagelist(String hql, String hqlCount, Integer offset);
	
	/**
	 * �����¼
	 * @param object
	 */
	void addEntity(Object object);
	
	/**
	 * ����Id��ȡ��ɫ
	 * @param id
	 * @return
	 */
	Role getRoleById(Integer id);

	/**
	 * ɾ����ɫ
	 * @param role
	 */
	void delRole(Role role);
	
	/**
	 * Ȩ���б�
	 * @param hql
	 * @param hqlCount
	 * @param offset
	 * @return
	 */
	Pager<Right> rightPagelist(String hql, String hqlCount, Integer offset);
	
	/**
	 * ����id��ȡȨ��
	 * @param rightId
	 * @return
	 */
	Right getRightById(Integer rightId);
	
}
