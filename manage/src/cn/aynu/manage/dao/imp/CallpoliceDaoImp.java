package cn.aynu.manage.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.aynu.manage.dao.CallpoliceDao;
import cn.aynu.manage.vo.Callpolice;
import cn.aynu.manage.vo.Pager;
@Repository("callpoliceDao")
public class CallpoliceDaoImp implements CallpoliceDao {
	@Resource
	private SessionFactory sessionFactory;

	 @SuppressWarnings("unchecked")
	public Pager<Callpolice> pagelist(String hql, String hqlCount, Integer offset) {
		int pageSize=15;
		List<Callpolice> datas =sessionFactory.getCurrentSession().createQuery(hql).setFirstResult((offset-1)*pageSize).setMaxResults(pageSize).list();
		int count=((Long) sessionFactory.getCurrentSession().createQuery(hqlCount).iterate().next()).intValue();
		Pager<Callpolice> pg=new Pager<Callpolice>();
		//��װ�ĸ�����    ��ҳ���ݡ���ҳҳ���С�����ݿ����������ڼ�ҳ
		pg.setDatas(datas);
		pg.setSize(pageSize);
		pg.setTotal(count);
		pg.setOffset(offset);
		return pg;
	}

}
