package cn.aynu.manage.scheduler;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.aynu.manage.service.LogService;
import cn.aynu.manage.util.LogUtil;

/**
 *������־���ʯӢ���� 
 */
public class CreateLogTablesTask extends QuartzJobBean{

	private LogService logService;
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		//1.ɾ�� ȥ�� �Լ� ��ǰ�� ������־�� �� ����
		String lastYeatAgoTable = LogUtil.generateLogTableName(-12);
		//���ݿ����Ƿ������ݱ�
		String sql = "select table_name from information_schema.tables "
				+ "where table_name like 'logs_%' "
				+ "and table_name <= '"+lastYeatAgoTable+"' "
				+ "order by table_name desc ";
		List<String> list = logService.findSqlQuery(null,sql);
		for(String tableName : list)
		{
			String dropSql = "drop table IF EXISTS "+tableName;
			logService.executeSQL(dropSql);
		}
		
		
		//2.�����������µ���־��
		//��һ��
		String tableName = LogUtil.generateLogTableName(1);
		logService.createTable(tableName);
		//�¶���
		tableName = LogUtil.generateLogTableName(2);
		logService.createTable(tableName);
		//������
		tableName = LogUtil.generateLogTableName(3);
		logService.createTable(tableName);
	}
}
