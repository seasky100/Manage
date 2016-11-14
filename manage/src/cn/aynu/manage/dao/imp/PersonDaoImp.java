package cn.aynu.manage.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

import cn.aynu.manage.dao.Pagebean;
import cn.aynu.manage.dao.PersonDao;
import cn.aynu.manage.vo.Pager;
import cn.aynu.manage.vo.Person;



public class PersonDaoImp extends BaseDaoImpl<Person> implements PersonDao {
	@Resource
	private SessionFactory sessionFactory;
	
	

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@SuppressWarnings("unchecked")
	public List<Person> list() {
		// TODO Auto-generated method stub
		int pageOffset =1;
		int pageSize =15;
		String hql ="from Person p order by p.id desc";
		
		return sessionFactory.getCurrentSession().createQuery(hql).setFirstResult((pageOffset-1)*pageSize).setMaxResults(pageSize).list();
	}
     @SuppressWarnings("unchecked")
	public Pager<Person> pagelist(String hql,String hqlCount,int pageOffset){
 		  int pageSize =15;
 		 
 		  //query.setFirstResult(0);--------�ӵ�һ����¼��ʼ
 	    //query.setMaxResults(4);--------ȡ��������¼
 		List<Person> datas =sessionFactory.getCurrentSession().createQuery(hql).setFirstResult((pageOffset-1)*pageSize).setMaxResults(pageSize).list();
 		//��ò�ѯ������
 		int count = ((Long) sessionFactory.getCurrentSession().createQuery(hqlCount).iterate().next()).intValue();
		
 		Pager<Person> pg=new Pager<Person>();
 		pg.setDatas(datas);
		pg.setSize(pageSize);
		pg.setTotal(count);
		pg.setOffset(pageOffset);
    	return pg;
     }


	public void addperson(Person person) {
		sessionFactory.getCurrentSession().save(person);
		
	}  


	@SuppressWarnings("unchecked")
	public Pagebean getPagebean(int pagesize, int page) {
		String hql="from Person";
		Pagebean pagebean=new Pagebean();
		//�õ��ܼ�¼��
	 int allrow=sessionFactory.getCurrentSession().createQuery(hql).list().size();
	 if(allrow==0)
	 {
		 pagebean=null;
	 }
	 else{
		 //�õ���ҳ��
		 int allpage=pagebean.allPage(pagesize, allrow);
		 
		 //�õ���ǰҳ   currentpageҪô��1Ҫô��page
		 int currentpage=pagebean.currentpage(page);
		 //�õ���ǰҳ��һ����¼�����ݿ��е�λ��
		 int jinlushu=pagebean.jilushu(pagesize, currentpage);
		 //list�зŵ��Ǳ�ҳ�ڵļ�¼
		 List<Person> person=(List<Person>)sessionFactory.getCurrentSession().createQuery(hql).setFirstResult(jinlushu).setMaxResults(pagesize).list();
		 pagebean.setList(person);
		 pagebean.setAllpage(allpage);
		 pagebean.setCurrentpage(currentpage);
		 pagebean.setAllrow(allrow);
	 }
		return pagebean;
	}


	public Person getperson(int id) {
		return (Person) sessionFactory.getCurrentSession().get(Person.class, id);
	}


	public void update(Person person) {
		// TODO Auto-generated method stub
		System.out.println("dao:�����"+person.getName());
		System.out.println("dao:�����"+person.getNumber());
		System.out.println(person);
		sessionFactory.getCurrentSession().update(person);
	}


	@Override
	public void del(int id) {
		Person p = this.getperson(id);
		sessionFactory.getCurrentSession().delete(p);
	}
	
  /**
   * ���� ��ѯ���� person ��ѯ��һ��list
   * @param person
   * @return
   */
	public List<Person> getListByArgs(Person person, String hql) {
		
		return null;
	}
	
	
}
