package cn.xxx.elec.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelper {
	/**  
	* @Name: stringConvertDate
	* @Description: 将String转化成java.util.Date
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-16 （创建日期）
	* @Parameters: String textDate
	* @Return: java.util.Date 
	*/
	public static Date stringConvertDate(String textDate) {
		Date d = null;
		if(textDate!=null && !textDate.equals("")){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			try {
				d = sdf.parse(textDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return d;
	}
	
	/**  
	* @Name: utilDateConvertSqlDate
	* @Description: 将java.util.Date转化为java.sql.Date
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-16 （创建日期）
	* @Parameters: java.util.Date d
	* @Return: java.sql.Date 
	*/
	public static java.sql.Date utilDateConvertSqlDate(Date d){
		java.sql.Date sqlDate = null;
		sqlDate = new java.sql.Date(d.getTime()); 
		return sqlDate;
	}
	
	/**  
	* @Name: dateConvertString
	* @Description: 将java.util.Date转化为String
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-1-24 （创建日期）
	* @Parameters: java.util.Date d
	* @Return: String
	*/
	public static String dateConvertString(Date d){
		return d==null?"":d.toString();
	}
	
	//字符串数组转化为字符串
	public static String ArraysToString(String[] str){
		StringBuffer sb = new StringBuffer("");
		for(int i=0;i<str.length;i++){
			sb.append(str[i]);
		}
		return sb.toString();
	}
	
}
