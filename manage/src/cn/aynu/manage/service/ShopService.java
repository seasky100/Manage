package cn.aynu.manage.service;

import java.util.List;

import cn.aynu.manage.vo.Pager;
import cn.aynu.manage.vo.Person;
import cn.aynu.manage.vo.Shop;

public interface ShopService {
	/**
	 * ���������б�
	 */
	List<Shop> findAllShops();
	/**
	 * �����б�ҳ
	 * @param offset
	 * @return
	 */
	Pager<Shop> pageList(Integer offset);
	
	/**
	 * ҳ����ת
	 */
	Pager<Shop> getShopListByArgs(Shop shop, Integer offset);
	
	/**
	 * ����id��ȡshop����
	 * @param id
	 * @return
	 */
	Shop getShopById(Integer id);
	
	/**
	 * ����������Ϣ
	 * @param shop
	 */
	void update(Shop shop);
	
	/**
	 * ɾ������
	 * @param id
	 */
	void delShop(Integer id);
	
	/**
	 * �������
	 * @param shop
	 */
	void addShop(Shop shop);

}
