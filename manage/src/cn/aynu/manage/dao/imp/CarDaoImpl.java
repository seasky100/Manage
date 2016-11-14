package cn.aynu.manage.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.aynu.manage.dao.CarDao;
import cn.aynu.manage.dao.Pagebean;
import cn.aynu.manage.vo.Car;
import cn.aynu.manage.vo.Pager;


@Repository("carDao")
public class CarDaoImpl extends BaseDaoImpl<Car> implements CarDao {
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
	public List<Car> list() {
		int pageOffset =1;
		int pageSize =15;
		String hql ="from Car p order by p.id desc";
		
		return sessionFactory.getCurrentSession().createQuery(hql).setFirstResult((pageOffset-1)*pageSize).setMaxResults(pageSize).list();
	}
     @SuppressWarnings("unchecked")
	public Pager<Car> pagelist(String hql,String hqlCount,int pageOffset){
 		  int pageSize =15;
 		List<Car> datas =sessionFactory.getCurrentSession().createQuery(hql).setFirstResult((pageOffset-1)*pageSize).setMaxResults(pageSize).list();
 		int count = ((Long) sessionFactory.getCurrentSession().createQuery(hqlCount).iterate().next()).intValue();
		
 		Pager<Car> pg=new Pager<Car>();
 		pg.setDatas(datas);
		pg.setSize(pageSize);
		pg.setTotal(count);
		pg.setOffset(pageOffset);
    	return pg; 
     }


	public void addCar(Car car) {
		sessionFactory.getCurrentSession().save(car);
		
	}  


	@SuppressWarnings("unchecked")
	public Pagebean getPagebean(int pagesize, int page) {
		String hql="from Car";
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
		 List<Car> cars=(List<Car>)sessionFactory.getCurrentSession().createQuery(hql).setFirstResult(jinlushu).setMaxResults(pagesize).list();
		 pagebean.setList(cars);
		 pagebean.setAllpage(allpage);
		 pagebean.setCurrentpage(currentpage);
		 pagebean.setAllrow(allrow);
	 }
		return pagebean;
	}


	public Car getCar(int id) {
		return (Car) sessionFactory.getCurrentSession().get(Car.class, id);
	}


	public void update(Car car) {
		System.out.println("dao:�����"+car.getName());
		System.out.println("dao:�����"+car.getNumber());
		System.out.println(car);
		sessionFactory.getCurrentSession().update(car);
	}


	public void del(int id) {
		Car car = this.getCar(id);
		sessionFactory.getCurrentSession().delete(car);
	}

	  /**
	   * ���� ��ѯ���� car ��ѯ��һ��list
	   * @param car
	   * @return
	   */
		public List<Car> getListByArgs(Car car, String hql) {
			return null;
		}
			
	
}
