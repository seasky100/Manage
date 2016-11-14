package cn.aynu.manage.dao;

import java.util.List;

import cn.aynu.manage.vo.Caseinfo;
import cn.aynu.manage.vo.Pager;


public interface CaseinfoDao extends BaseDao<Caseinfo>{
	
	public Pager<Caseinfo> pagelist(String hql, String hqlCount, int offset);
    public void del(int id);
    public Caseinfo getCaseinfo(int id);
    public void updatecaseinfo(Caseinfo caseinfo);
    public void addCaseinfo(Caseinfo caseinfo);
	public Object uniqueResult(String hql, Object...objects);
    /**
	 * ��������caseinfo��id,�����������
	 * @return
	 */
	public List<Caseinfo> retrunAllCaseInfoes(String hql);
	
	/**
	 * ���ظ������Ͱ����ķ�������
	 * @return
	 */
	public List<String> returnStringArrByHql(String hql, Object...objects);
	/**
	 * ִ��ԭ��sql��ѯ
	 * @param sql
	 * @return
	 */
	public Object uniqueResultBySql(String sql);
    
}
