package cn.aynu.manage.service;

import java.util.List;
import java.util.Map;

import cn.aynu.manage.vo.Caseinfo;
import cn.aynu.manage.vo.Pager;
import cn.aynu.manage.vo.Person;


public interface CaseinfoService extends BaseService<Caseinfo>{
	public Pager<Caseinfo> pagelist(int offset);
	public void del(int id);
	public Caseinfo getCaseinfo(int id);
	public void updatecaseinfo(Caseinfo caseinfo);
	public void addCaseinfo(Caseinfo caseinfo);
	/**
	 * ���ݲ�����ѯ�� һ�� ��ҳ��list Person
	 * 
	 * @param person
	 */
	public Pager<Caseinfo> getPersonListByArgs(int offset,String number,String callStartTime,String callEndTime,String outStartTime,String outEndTime);
	
	/**
	 * ��������caseinfo��id,�����������
	 * @return
	 */
	public List<Caseinfo> retrunAllCaseInfoes();
	/**
	 * ���طֲ�����
	 * @return
	 */
	public List<Map<String,Object>> getAjaxSpreadData();
	
	/**
	 * ���ظ������Ͱ����ķ�������
	 * @return
	 */
	public List<Integer> returnPerTypeCaseInfoCount();
	
	/**
	 * ���ظ������Ͱ�����map(�����ˣ�����������ص���Map,�����¹����ͺʹ���)
	 * @return
	 */
	public Map<String, Object> returnPerTypeCaseInfoMap();
	
	/**
	 * �����¹������Լ���
	 * @return
	 */
	public List<Integer> returnAverCaseinfoSeriousList();
	
	/**
	 * ���շ���
	 * @param d 
	 * @param string2 
	 * @param string 
	 * @return
	 */
	public List<Caseinfo>[] riskAnalysis(Caseinfo caseinfo, double d);
	/**
	 * ���ݷ�Χ���������ƽ�����س̶�
	 * @return 
	 */
	public List<Double> getAvgSeriousByRand();
	/**
	 * ���ܰ�� �� ���� ���س̶�
	 * @param riskSerious 
	 * @return
	 */
	public double getTotalAvgSerious(List<Double> riskSerious);
	
}
