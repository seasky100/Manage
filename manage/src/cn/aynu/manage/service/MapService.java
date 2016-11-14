package cn.aynu.manage.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface MapService {


	
	/**
	 * ���� �Ա�ʱ�� ��������
	 * @return
	 */
	public List<List<Object>> getCaseTimeData();
	/**
	 * �õ�caseinfo�¼���iСʱ��jСʱ֮���������Ĵ���
	 * @param i
	 * @param j
	 * @return
	 */
	public BigInteger getCaseinfoCountBetweenData(Integer i, Integer j);
	
	/**
	 * �õ�iСʱ��jСʱ ���������� ƽ������ʱ��
	 * @return
	 */
	public BigDecimal getAvgOutTime(Integer i,Integer j);
	
	/**
	 * �õ�iСʱ��jСʱ ���������� ƽ���᰸ʱ��
	 * @return
	 */
	public BigDecimal getAvgDealSuccesstTime(Integer i,Integer j);
	/**
	 * ���ذ���������ʱ��ʹ�����map
	 * @return
	 */
	List<List<Double>> returnAccidentTimeTimesList();

}
