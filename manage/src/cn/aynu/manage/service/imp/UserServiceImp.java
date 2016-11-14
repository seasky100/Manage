package cn.aynu.manage.service.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.aynu.manage.dao.RoleDao;
import cn.aynu.manage.dao.UserDao;
import cn.aynu.manage.service.UserService;
import cn.aynu.manage.util.StringUtil;
import cn.aynu.manage.util.ValidateUtil;
import cn.aynu.manage.vo.Pager;
import cn.aynu.manage.vo.User;
import cn.aynu.manage.vo.safe.Role;
import cn.aynu.manage.vo.safe.Right;
import cn.aynu.manage.vo.safe.Role;
@Service("userService")
public class UserServiceImp implements UserService {
	@Resource
	private UserDao userDao;
	
	/**
	 * �����û��б�
	 */
	public List<User> findAllUsers() {
		String hql = "from User";
		return userDao.findAllUsers(hql);
	}

	/**
	 * �����б�ҳ
	 */
	public Pager<User> pageList(Integer offset) {
		String hql ="from User user order by user.id desc";
		String hqlCount = "select  count(*)  from User ";
		return userDao.pagelist(hql,hqlCount,offset);
	}

	/**
	 * ҳ����ת
	 */
	public Pager<User> getUserListByArgs(User user, Integer offset) {
		String hql ="from User user where 1 = '1'";
		String hqlCount = "select  count(*)  from User where 1='1'";
		if(ValidateUtil.isValid(user.getUsername()))
		{
			hql = hql + " and username like '%"+user.getUsername()+"%'";
			hqlCount = hqlCount + " and username like '%"+user.getUsername()+"%'";
		}
		if(ValidateUtil.isValid(user.getIdcard()))
		{
			hql = hql + " and idcard like '%"+user.getIdcard()+"%'";
			hqlCount = hqlCount + " and idcard like '%"+user.getIdcard()+"%'";
		}
		if(ValidateUtil.isValid(user.getWorkcard()))
		{
			hql = hql + " and workcard like '%"+user.getWorkcard()+"%'";
			hqlCount = hqlCount + " and workcard like '%"+user.getWorkcard()+"%'";
		}
		if(ValidateUtil.isValid(user.getPhone()))
		{
			hql = hql + " and phone like '%"+user.getPhone()+"%'";
			hqlCount = hqlCount + " and phone like '%"+user.getPhone()+"%'";
		}
		hql = hql +" order by user.id desc";
		return userDao.pagelist(hql,hqlCount,offset);
	}

	/**
	 * ����Id��ȡshop����
	 */
	public User getUserById(Integer id) {
		return userDao.getUser(id);
	}

	
	/**
	 * �����û���Ϣ
	 * ����
	 */
	public void update(Object object) {
		userDao.update(object);
	}

	/**
	 * ɾ���û�
	 */
	public void delUser(Integer id) {
		userDao.delUser(getUserById(id));
	}

	/**
	 * ����û�
	 */
	public void addEntity(Object object) {
		userDao.addEntity(object);
	}

	//��֤�û���Ϣ
	public User chekUser(User user) {
		String hql = "from User u where u.username =:username and u.password = :password";
		return userDao.chekUser(hql, user);
	}
	@Resource
	private RoleDao roleDao;
	/**
	 * �õ�û�еĽ�ɫ
	 */
	public List<Role> getWithOutAuthors(Set<Role> roles) {
		String hql;
		if(!ValidateUtil.isValid(roles))
		{
			hql = "from Role";
		}
		else{
			String roleIdString =""; 
			for(Role r : roles)
			{
				roleIdString = roleIdString+r.getId()+",";
			}
			hql = "from Role r where r.id not in ("+roleIdString.substring(0, roleIdString.length()-1)+")";
		}
		return roleDao.findEntityByHQL(hql);
	}

	/**
	 * �޸��û���Ȩ��Ϣ
	 * @param model
	 * @param ids
	 */
	public void updateAuthorize(User user, String[] ids) {
		//��Ҫ�����»�ȡһ��user����Ϊ�Ǳ��ύ�����ģ��ᶪʧ��Ϣ
		user = this.getUserById(user.getId());
		if(!ValidateUtil.isValid(ids))
		{
			user.getRoles().clear();
		}
		else{
			String hql = "from Role r where r.id in("+StringUtil.arr2String(ids)+")";
			user.setRoles(new HashSet<Role>(roleDao.findEntityByHQL(hql))) ;
		}
	}

	/**
	 * ����Role����
	 */
	@SuppressWarnings("unchecked")
	public List<Role> returnAllRoles() {
		String hql = "from Role";
		return userDao.findEntityByHql(hql);
	}

	/**
	 * ��ɫ�б�ҳ
	 */
	public Pager<Role> rolePageList(Integer offset) {
		String hql ="from Role role order by role.id desc";
		String hqlCount = "select  count(*)  from Role ";
		return userDao.rolePagelist(hql,hqlCount,offset);
	}

	/**
	 * ��ɫҳ����ת
	 */
	public Pager<Role> getRoleListByArgs(Role role, Integer offset) {
		String hql ="from Role role where 1 = '1'";
		String hqlCount = "select  count(*)  from Role where 1='1'";
		if(ValidateUtil.isValid(role.getRoleName()))
		{
			hql = hql + " and roleName like '%"+role.getRoleName()+"%'";
			hqlCount = hqlCount + " and roleName like '%"+role.getRoleName()+"%'";
		}
		if(ValidateUtil.isValid(role.getRoleDesc()))
		{
			hql = hql + " and roleDesc like '%"+role.getRoleDesc()+"%'";
			hqlCount = hqlCount + " and roleDesc like '%"+role.getRoleDesc()+"%'";
		}
		hql = hql +" order by role.id desc";
		return userDao.rolePagelist(hql,hqlCount,offset);
	}

	/**
	 * ����id��ȡ��ɫ
	 */
	public Role getRoleById(Integer id) {
		return userDao.getRoleById(id);
	}

	/**
	 * ɾ����ɫ
	 */
	public void delRole(Integer id) {
		userDao.delRole(getRoleById(id));
	}

	/**
	 * Ȩ���б�
	 */
	public Pager<Right> rightPageList(Integer offset) {
		String hql ="from Right order by id desc";
		String hqlCount = "select  count(id)  from Right ";
		return userDao.rightPagelist(hql,hqlCount,offset);
	}

	/**
	 * Ȩ��ҳ����ת
	 */
	public Pager<Right> getRightListByArgs(String queryCommon,Right right, Integer offset) {
		String hql ="from Right where 1 = '1'";
		String hqlCount = "select  count(id)  from Right where 1='1'";
		if(ValidateUtil.isValid(right.getRightName()))
		{
			hql = hql + " and rightName like '%"+right.getRightName()+"%'";
			hqlCount = hqlCount + " and rightName like '%"+right.getRightName()+"%'";
		}
		if(ValidateUtil.isValid(right.getRightDesc()))
		{
			hql = hql + " and rightDesc like '%"+right.getRightDesc()+"%'";
			hqlCount = hqlCount + " and rightDesc like '%"+right.getRightDesc()+"%'";
		}
		if(ValidateUtil.isValid(queryCommon))
		{
			hql = hql + " and common is "+queryCommon;
			hqlCount = hqlCount + " and common is "+queryCommon;
		}
		hql = hql +" order by id desc";
		return userDao.rightPagelist(hql,hqlCount,offset);
	}

	/**
	 * ����id��ȡȨ��
	 */
	public Right getRightById(Integer rightId) {
		return userDao.getRightById(rightId);
	}

	/**
	 * ���½�ɫ
	 */
	public void updateRole(Role role) {
		Role r = roleDao.getEntity(role.getId());
		r.setRoleName(role.getRoleName());
		r.setRoleDesc(role.getRoleDesc());
		roleDao.updateEntity(r);
	}

	/**
	 * �õ���ɫû�е�Ȩ��
	 */
	public List<Right> getWithOutRights(Set<Right> ownRights, List<Right> allRights) {
		List<Right> withOutRights = new ArrayList<Right>();
		for(Right right : allRights){
			if(!ownRights.contains(right)){
				withOutRights.add(right);
			}
		}
		return withOutRights;
	}

	/**
	 * �޸Ľ�ɫ��Ȩ
	 */
	@SuppressWarnings("unchecked")
	public void updateRoleAuthorize(Role role, String[] ownRightsIds) {
		role.getRights().clear();
		if(ValidateUtil.isValid(ownRightsIds)){
			String hql = "from Right where id in (" + StringUtil.arr2String(ownRightsIds) + ")";
			role.setRights(new HashSet<Right>(userDao.findEntityByHql(hql)));
		}
		userDao.update(role);
	}


}
