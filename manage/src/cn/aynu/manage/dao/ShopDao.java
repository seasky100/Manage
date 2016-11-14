package cn.aynu.manage.dao;

import java.util.List;

import cn.aynu.manage.vo.Pager;
import cn.aynu.manage.vo.Shop;

public interface ShopDao extends BaseDao<Shop>{
	/**
	 * ���������б�
	 */
	List<Shop> findAllShops(String hql, Object...objects);
	
	/**
	 * �����б�ҳ
	 * @param hql
	 * @param hqlCount
	 * @param offset
	 * @return
	 */
	Pager<Shop> pagelist(String hql, String hqlCount, Integer offset);
	
	/**
	 * ����id��ȡShop����
	 * @param id
	 * @return
	 */
	Shop getShop(Integer id);

	/**
	 * ����������Ϣ
	 * @param shop
	 */
	void update(Shop shop);

	/**
	 * ɾ������
	 * @param id
	 */
	void delShop(Shop shop);

	/**
	 * �������
	 * @param shop
	 */
	void addShop(Shop shop);

}
