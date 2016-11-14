package cn.aynu.manage.struts2.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.aynu.manage.service.ShopService;
import cn.aynu.manage.vo.Pager;
import cn.aynu.manage.vo.Shop;
@Controller("shopAction")
@Scope("prototype")
public class ShopAction extends ActionSupport implements ModelDriven<Shop> {
	
	private static final long serialVersionUID = -5721942523484658870L;
	
	@Resource
	private ShopService shopService;
	
	private Shop shop = new Shop();
	private Pager<Shop> page = null;
	private Integer offset = 1;
	private int pageType = 3;
	//��ҳ��ѯ��־  ��list ��listByArgs��
	private String selectPageFlag;
	//��������ɾ�����̵�id����
	private List<Integer> delShopIds = new ArrayList<Integer>();
	/**
	 * �����б�
	 * @return String
	 */
	public String shopList(){
		page = shopService.pageList(offset);
		if (page != null) {
			return "toShopListPage";
		}
		else {
			return ERROR;
		}
	}
	
	/**
	 * ҳ����ת
	 */
	public String blurSearch(){
		page = shopService.getShopListByArgs(shop,offset);
		if (page != null) {
			selectPageFlag = "listByArgs";
			return "per_list";
		}
		else {
			return ERROR;
		}
	}
	
	/**
	 * ȥ����������Ϣҳ��
	 * @return
	 */
	public String toUpdatePage(){
		shop = shopService.getShopById(shop.getId());
		return "toUpdatePage";
	}
	
	/**
	 * ����������Ϣ
	 * @return
	 */
	public String updateShop(){
		System.out.println(shop);
		shopService.update(shop);
		return "toUpdateSuccessPage";
	}
	
	/**
	 * ɾ������
	 * @return
	 */
	public String delShop(){
		shopService.delShop(shop.getId());
		return "del";
	}
	
	/**
	 * �������
	 * @return
	 */
	public String addShop(){
		shopService.addShop(shop);
		return "addSuccess";
	}
	
	/**
	 * ����ɾ������
	 * @return
	 */
	public String batchDelShop(){
		System.out.println("offset: "+offset);
		for(Integer id : delShopIds){
			shopService.delShop(id);
		}
		return "batchDel-success";
	}
	
	
	/**
	 * setter/getter
	 * @return
	 */
	public Shop getModel() {
		return shop;
	}
	public Pager<Shop> getPage() {
		return page;
	}
	public void setPage(Pager<Shop> page) {
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
	public List<Integer> getDelShopIds() {
		return delShopIds;
	}
	public void setDelShopIds(List<Integer> delShopIds) {
		this.delShopIds = delShopIds;
	}
	
	public String toAddShopPage()
	{
		return "toAddShopPage";
	}
	
}
