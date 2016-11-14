package cn.aynu.manage.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.aynu.manage.dao.MapDao;
import cn.aynu.manage.vo.Caseinfo;
@Repository("mapDao")
public class MapDaoImpl implements MapDao {
	@Resource 
	private SessionFactory sessionFactory;
	/**
	 * �������а�������
	 */
	@SuppressWarnings("unchecked")
	public List<Caseinfo> returnAllCaseinfoes() {
		return sessionFactory.getCurrentSession().createQuery("from Caseinfo").list();
	}
	
}
