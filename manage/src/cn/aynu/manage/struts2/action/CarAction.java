package cn.aynu.manage.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.aynu.manage.service.CarService;
import cn.aynu.manage.vo.Car;
import cn.aynu.manage.vo.Pager;

@Controller
@Scope("prototype")
public class CarAction extends ActionSupport implements ModelDriven<Car>{
	private static final long serialVersionUID = 8418399327924777512L;
	private Car car = null;
	public CarAction(){
		car = new Car();
	}
	public Car getModel() {
		return car;
	}
	
	private Integer offset = 1;
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	private Pager<Car> page;
	public Pager<Car> getPage() {
		return page;
	}
	public void setPage(Pager<Car> page) {
		this.page = page;
	}
	@Resource
	private CarService carService;
	/**
	 * ��ҳ��ѯcar
	 * @return
	 */
	public String pagelist()
	{
		page = carService.pagelist(offset);
		return "car-list";
	}
	/**
	 * ģ����ѯcar
	 * 
	 * @return
	 */
	public String blurSearch() {
		page = carService.getCarListByArgs(car,offset);
		if (page != null) {
			//selectPageFlag = "listByArgs";
			return "car-list";
		}
		else {
			return ERROR;
		}
	}
	
	public String del()
	{
		carService.del(car.getId());
		return "del-success";
	}
	/**
	 * ���༭����
	 * @return
	 */
	public String toUpdatePage()
	{
		car = carService.getCar(car.getId());
		System.out.println(car);
		return "toUpdatePage";
	}
	
	/**
	 * �޸ĳ�����Ϣ
	 * @return
	 */
	public String updateCar()
	{
		carService.update(car);
		return "update-success";
	}
	/**
	 * ��ӳ�����Ϣ
	 * @return
	 */
	public String addCar()
	{
		carService.addCar(car);
		return "add-success";
	}
	
	public String toAddCarPage()
	{
		return "toAddCarPage";
	}
}
