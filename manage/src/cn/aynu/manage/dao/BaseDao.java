package cn.aynu.manage.dao;

import java.util.List;

import cn.aynu.manage.vo.Pager;

public interface BaseDao<T> {
	//д����
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void updateEntity(T t);
	public void deleteEntity(T t);
	//��֪���ж��ٸ�����
	public void batchEntityByHQL(String hql,Object...objects);
	
	//ִ��sql���
	public void executeSQL(String sql,Object...objects);
	
	//������
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> findEntityByHQL(String hql,Object...objects);
	//ֻ��һ�������
	public Object uniqueResultByHQL(String hql,Object...objects);
	
	/**
	 *ԭ����sql����ѯ ,clazz�ж��Ƿ񱣴�Ϊ����
	 */
	public List findSqlQuery(Class clazz,String sql,Object...objects);
	
	public Object uniqueResultBySql(String sql,Object...objects);
	/**
	 * ��ҳ��ѯ �б�
	 */
	public Pager<T> pagelist(String hql, String hqlCount, Integer offset);
}
