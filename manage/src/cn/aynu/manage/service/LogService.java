package cn.aynu.manage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.aynu.manage.vo.Log;
import cn.aynu.manage.vo.Pager;

public interface LogService extends BaseService<Log> {

	public void createTable(String tableName);

	/**
	 * ��ѯ���i�µ���־  ��������
	 */
	public Map<String,Object> findNearLogs(String operator,String result,int i, String operStartTime, String operEndTime,Integer offset);
	/**
	 * ���¡���ҳ��ѯ��־
	 * @param log
	 * @param operStartTime
	 * @param operEndTime
	 * @param offset
	 * @return
	 */
	public Pager<Log> pagelist(Log log, String operStartTime, String operEndTime, Integer offset);

	
}
