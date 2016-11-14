package cn.aynu.manage.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LogUtil {
	/**
	 * ������־������
	 * @param offset ƫ����
	 * @return
	 */
	public static String generateLogTableName(int offset)
	{
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("00");
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR) ;
		int month = c.get(Calendar.MONTH) + 1+offset;
		if(month > 12)
		{
			year ++ ;
			month -=12;
		}
		if(month < 1)
		{
			year --;
			month +=12;
		}
		return "logs_"+year+"_"+df.format(month);
	}
	
	/**
	 * ������������֮���Լ��
	 * @throws Exception 
	 */
	public static int calculateMonthsBetweenTwoDate(String fromDate, String toDate) throws Exception{
		int result = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Date operEndDate = null;
		Date operStartDate = null;
		//toDate ��д��ʾ����
		if(!ValidateUtil.isValid(toDate))
		{
			operEndDate = new Date();
		}
		else{
			 operEndDate = sdf.parse(toDate);
		}
		c2.setTime(operEndDate);
		//fromDate ��д ��ʾ����Ҫ��ɸѡ����,Ĭ�ϴ� 1970 �� �� ����
		if(!ValidateUtil.isValid(fromDate))
		{
			operStartDate = sdf.parse("1970-01-01");
		}
		else{
			operStartDate = sdf.parse(fromDate);
		}
		c1.setTime(operStartDate);
		int yearsBetweenTwoDates = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
		if(yearsBetweenTwoDates>0){
			result = 12 * yearsBetweenTwoDates + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		}else
			if(yearsBetweenTwoDates == 0){
				result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
			}else{
				return 0;
			}
		return result == 0 ? 1 : Math.abs(result);
	}
}
