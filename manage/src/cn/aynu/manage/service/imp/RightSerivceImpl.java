package cn.aynu.manage.service.imp;


import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.aynu.manage.dao.imp.RightDaoImpl;
import cn.aynu.manage.service.RightService;
import cn.aynu.manage.vo.safe.Right;

@Service("rightService")
public class RightSerivceImpl implements RightService {
	@Resource
	private RightDaoImpl rightDao;
	public void saveOrUpdateRight(Right model) {
		int pos = 0;
		long code = 1L;
		//�����ݿ�ȡ������Ȩ����
		/**
		 * ���ݿ��������������ݣ�Ч��̫��
		 */
//		String hql ="from Right r order by r.rightPos desc,r.rightCode desc";
//		List<Right> rights = rightDao.findEntityByHQL(hql);
//		if(ValidateUtil.isValid(rights))
//		 {
//			code = rights.get(0).getRightCode();
//			pos = rights.get(0).getRightPos();
//			if(code >= 1L<<60)
//			{
//				pos++;
//				code = 1L;
//			}
//			else {
//				code = code<<1;
//			}
//		 }
		/**
		 * �ۺϺ�����ѯ��Ч�ʸ���
		 */
		String hql = "select max(r.rightPos),max(r.rightCode) from Right r where r.rightPos ="
				+ "(select max(rr.rightPos) from Right rr)";
		Object[] arr = (Object[]) rightDao.uniqueResultByHQL(hql);
		//���ݿ���û��Ȩ��
		if(arr[0] == null)
		{
			pos = 0;
			code = 1L;
		}
		else {
			pos = (Integer)arr[0];
			code = (Long)arr[1];
			if(code >= (1L << 60))
			{
				pos = pos + 1;
				code = 1L;
			}
			else {
				code = code << 1;
			}
			
		}
		model.setRightCode(code);
		model.setRightPos(pos);
		rightDao.saveOrUpdateEntity(model);
	}
	
	
	public void appendRightUrl(String url) {
		//�жϵ�ǰ���ݿ����Ƿ������urlȨ��
		String hql = "select count(id) from Right r where r.rightUrl = ?";
		Long count  = (Long)rightDao.uniqueResultByHQL(hql, url);
		if(count == 0)
		{
			//û�и�Ȩ�ޣ����
			Right r = new Right();
			r.setRightUrl(url);
			this.saveOrUpdateRight(r);
		}
	}
	public List<Right> findRightInRange(String[] noOwnRightIds) {
/*		String hql ="from Right r where r.id in("+StringUtil.arr2String(noOwnRightIds)+")";
		return this.findEntityByHQL(hql);*/
		return null;
	}
	public List<Right> findRightNoInRange(Set<Right> rights) {
/*		if(!ValidateUtil.isValid(rights))
		{
			return this.findAllEntites();
		}
		else{
			String hql ="from Right r where r.id not in("+StringUtil.extractEntity2String(rights)+")";
			return this.findEntityByHQL(hql);
		}*/
		return null;
	}
//	private String extractRightIds(Set<Right> rights) {
//		String temp = "";
//		for(Right r : rights)
//		{
//			temp = temp + r.getId()+",";
//		}
//		return temp.substring(0,temp.length()-1);
//	}
	public Integer getMaxRightPos() {
		String hql = "select max(r.rightPos) from Right r";
		Integer pos = (Integer) rightDao.uniqueResultByHQL(hql);
		return pos;
	}
	public Right getEntityByUrl(String url) {
		String hql = "from Right where rightUrl = ?";
		return (Right) rightDao.uniqueResultByHQL(hql, url);
	}

	/**
	 * ���� Right������ ����list����
	 */
	public List<Right> findAllEntites() {
		String hql = "from Right";
		return rightDao.findEntityByHQL(hql);
	}
}
