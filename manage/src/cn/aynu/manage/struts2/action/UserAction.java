package cn.aynu.manage.struts2.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.aynu.manage.service.RightService;
import cn.aynu.manage.service.RoleService;
import cn.aynu.manage.service.UserService;
import cn.aynu.manage.vo.Pager;
import cn.aynu.manage.vo.User;
import cn.aynu.manage.vo.safe.Right;
import cn.aynu.manage.vo.safe.Role;
/**
 * @author yehao
 *
 */
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	private static final long serialVersionUID = -5115531220769808535L;

	@Resource
	private UserService userService;
	
	private User user = new User();
	private Role role = new Role();
	private Right right = new Right();
	private Pager<User> page = null;
	private Pager<Role> rolePage = null;
	private Pager<Right> rightPage = null;
	private Integer offset = 1;
	private int pageType = 3;
	//��ҳ��ѯ��־  ��list ��listByArgs��
	private String selectPageFlag;
	//��������ɾ��user��id����
	private List<Integer> delUserIds = new ArrayList<Integer>();
	//��������ɾ��role��id����
	private List<Integer> delRoleIds = new ArrayList<Integer>();
	//��������ɾ��right��id����
	private List<Integer> delRightIds = new ArrayList<Integer>();
	private int msg;
	//��ɫ����
	private List<Role> roles;
	//Ȩ�޼���
	private Set<Right> rights;
	//��ɫid
	private Integer roleId;
	//Ȩ��Id
	private Integer rightId;
	//��ɫû�е�Ȩ��
	private List<Right> withOutRight;
	private String[] ownRightsIds;
	/**
	 * �û��б�
	 * @return String
	 */
	public String userList(){
		page = userService.pageList(offset);
		if (page != null) {
			return "toUserListPage";
		}
		else {
			return ERROR;
		}
	}
	
	/**
	 * ��ɫ�б�
	 * @return
	 */
	public String roleList(){
		rolePage = userService.rolePageList(offset);
		if (rolePage != null) {
			return "toRoleListPage";
		}
		else {
			return ERROR;
		}
	}

	/**
	 * Ȩ���б�
	 * @return
	 */
	public String rightList(){
		rightPage = userService.rightPageList(offset);
		if (rightPage != null) {
			return "toRightListPage";
		}
		else {
			return ERROR;
		}
	}
	
	/**
	 * ҳ����ת
	 */
	public String blurSearch(){
		page = userService.getUserListByArgs(user,offset);
		if (page != null) {
			selectPageFlag = "listByArgs";
			return "per_list";
		}
		else {
			return ERROR;
		}
	}

	/**
	 *��ɫҳ����ת
	 */
	public String blurSearchRole(){
		rolePage = userService.getRoleListByArgs(role,offset);
		if (rolePage != null) {
			selectPageFlag = "listByArgs";
			return "role_list";
		}
		else {
			return ERROR;
		}
	}

	//��ѯ �� �Ƿ��ǹ�����Դ
	private String queryCommon;
	public String getQueryCommon() {
		return queryCommon;
	}

	public void setQueryCommon(String queryCommon) {
		this.queryCommon = queryCommon;
	}

	/**
	 *��ɫҳ����ת
	 */
	public String blurSearchRight(){
		rightPage = userService.getRightListByArgs(queryCommon,right,offset);
		if (rightPage != null) {
			selectPageFlag = "listByArgs";
			return "right_list";
		}
		else {
			return ERROR;
		}
	}
	
	/**
	 * ȥ�����û���Ϣҳ��
	 * @return
	 */
	public String toUpdatePage(){
		user = userService.getUserById(user.getId());
		return "toUpdatePage";
	}
	
	/**
	 * ȥ���½�ɫ��Ϣҳ��
	 * @return
	 */
	public String toUpdateRolePage(){
		role = userService.getRoleById(roleId);
		return "toUpdateRolePage";
	}
	/**
	 * ȥ����Ȩ����Ϣҳ��
	 * @return
	 */
	public String toUpdateRightPage(){
		right = userService.getRightById(rightId);
		return "toUpdateRightPage";
	}
	
	/**
	 * �����û���Ϣ
	 * @return
	 */
	public String updateUser(){
		userService.update(user);
		return "toUpdateSuccessPage";
	}
	
	/**
	 * ���½�ɫ��Ϣ
	 * @return
	 */
	public String updateRole(){
		role.setId(roleId);
		userService.updateRole(role);
		return "toUpdateSuccessPage";
	}

	/**
	 * ���½�Ȩ����Ϣ
	 * @return
	 */
	public String updateRight(){
		
		right.setId(rightId);
		userService.update(right);
		return "toUpdateSuccessPage";
	}
	
	/**
	 * ɾ���û�
	 * @return
	 */
	public String delUser(){
		userService.delUser(user.getId());
		return "del";
	}

	/**
	 * ɾ����ɫ
	 * @return
	 */
	public String delRole(){
		userService.delRole(roleId);
		return "delRole";
	}
	
	/**
	 * ����û�
	 * @return
	 */
	public String addUser(){
		userService.addEntity(user);
		return "addSuccess";
	}

	/**
	 * ��ӽ�ɫ
	 * @return
	 */
	public String addRole(){
		userService.addEntity(role);
		return "addSuccess";
	}
	
	/**
	 * ����ɾ���û�
	 * @return
	 */
	public String batchDelUser(){
		for(Integer id : delUserIds){
			userService.delUser(id);
		}
		return "batchDel-success";
	}

	/**
	 * ����ɾ����ɫ
	 * @return
	 */
	public String batchDelRole(){
		for(Integer id : delRoleIds){
			userService.delRole(id);
		}
		return "batchDelRole-success";
	}
	@Resource
	private RightService rightService;
	// ��֤�û���Ϣ
	public String checkUser() {
		User newUser = userService.chekUser(user);
		if (newUser == null)
			msg = 2;

		else {
			msg = 1;
			//��ʼ��Ȩ�޳������飬������Ȩ���ܺ�
			Integer maxPos = rightService.getMaxRightPos();
			newUser.setRightSum(new long[maxPos+1]);
			newUser.caculateRightSum();
			ActionContext.getContext().getSession().put("user", newUser);
		}

		return SUCCESS;
	}
	
	
	
	
	/**
	 * setter/getter
	 * @return
	 */
	public User getModel() {
		return user;
	}
	public Pager<User> getPage() {
		return page;
	}
	public void setPage(Pager<User> page) {
		this.page = page;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public int getPageType() {
		return pageType;
	}
	public void setPageType(int pageType) {
		this.pageType = pageType;
	}
	public String getSelectPageFlag() {
		return selectPageFlag;
	}
	public void setSelectPageFlag(String selectPageFlag) {
		this.selectPageFlag = selectPageFlag;
	}
	public List<Integer> getDelUserIds() {
		return delUserIds;
	}
	public void setDelUserIds(List<Integer> delUserIds) {
		this.delUserIds = delUserIds;
	}
	public int getMsg() {
		return msg;
	}
	public void setMsg(int msg) {
		this.msg = msg;
	}
	//û�е�Ȩ��
	private List<Role> withOutAuthor;
	public List<Role> getWithOutAuthor() {
		return withOutAuthor;
	}
	public void setWithOutAuthor(List<Role> withOutAuthor) {
		this.withOutAuthor = withOutAuthor;
	}

	/**
	 * ���û���Ȩ�������
	 * 1.�õ�����Ȩ��ɫ
	 * 2.�õ�δ��Ȩ��ɫ
	 * @return
	 */
	public String toAuthorizePage()
	{	
		user = userService.getUserById(user.getId());
		withOutAuthor = userService.getWithOutAuthors(user.getRoles());
		return "toAuthorizePage";
	}
	
	@Resource
	private RoleService roleService;
	/**
	 * ����ɫ��Ȩ�������
	 * @return
	 */
	public String toRoleAuthorizePage(){
		//��ȡ�Ѿ�ӵ�е�Ȩ��
		role = userService.getRoleById(roleId);
		rights = role.getRights();
		//��ȡû�е�Ȩ��
		withOutRight = roleService.getWithoutRights(role.getRights());
		return "toRoleAuthorizePage";
	}
	
	public String[] getOwnRoleIds() {
		return ownRoleIds;
	}
	public void setOwnRoleIds(String[] ownRoleIds) {
		this.ownRoleIds = ownRoleIds;
	}
	//�����򴫹����� ��ɫ id
	private String[] ownRoleIds;
	/**
	 * �޸��û���Ȩ
	 * @return
	 */
	public String updateAuthorize()
	{
		userService.updateAuthorize(user, ownRoleIds);
		return "updateAuthorize-success";
	}

	/**
	 * �޸Ľ�ɫ��Ȩ
	 * @return
	 */
	public String updateRoleAuthorize()
	{
		Role role = userService.getRoleById(roleId);
		userService.updateRoleAuthorize(role, ownRightsIds);
		return "updateAuthorize-success";
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Set<Right> getRights() {
		return rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

	public Pager<Role> getRolePage() {
		return rolePage;
	}
	public void setRolePage(Pager<Role> rolePage) {
		this.rolePage = rolePage;
	}
	public List<Integer> getDelRoleIds() {
		return delRoleIds;
	}
	public void setDelRoleIds(List<Integer> delRoleIds) {
		this.delRoleIds = delRoleIds;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Pager<Right> getRightPage() {
		return rightPage;
	}
	public void setRightPage(Pager<Right> rightPage) {
		this.rightPage = rightPage;
	}
	public List<Integer> getDelRightIds() {
		return delRightIds;
	}
	public void setDelRightIds(List<Integer> delRightIds) {
		this.delRightIds = delRightIds;
	}
	public Integer getRightId() {
		return rightId;
	}
	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}
	public Right getRight() {
		return right;
	}
	public void setRight(Right right) {
		this.right = right;
	}
	public List<Right> getWithOutRight() {
		return withOutRight;
	}
	public void setWithOutRight(List<Right> withOutRight) {
		this.withOutRight = withOutRight;
	}
	public String[] getOwnRightsIds() {
		return ownRightsIds;
	}
	public void setOwnRightsIds(String[] ownRightsIds) {
		this.ownRightsIds = ownRightsIds;
	}
	
	/**
	 * �û��ǳ�
	 * @return
	 */
	public String logout(){
		ActionContext.getContext().getSession().clear();
		return "toIndexPage";
	}
	
	public String toHomePage()
	{
		return "toHomePage";
	}
	public String toAddUserPage()
	{
		return "toAddUserPage";
	}
	
	public String toAddRolePage()
	{
		return "toAddRolePage";
	}
	
}
