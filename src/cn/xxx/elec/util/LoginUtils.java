package cn.xxx.elec.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.lang3.StringUtils;
import cn.xxx.elec.web.vo.UserForm;

import freemarker.template.utility.StringUtil;

public class LoginUtils {

	public static boolean checkNumber(String checkNumber,Map<String, Object> session) {
		//从session中获取验证码的数值
		if(session == null){
			return false;
		}
		String check_number_key = (String) session.get("CHECK_NUMBER_KEY");
		//System.out.println(check_number_key);
		if(StringUtils.isBlank("check_number_key")){
			return false;
		}
		//获得输入的验证码
		//System.out.println(checkNumber);
		//不相等
		if(!check_number_key.equals(checkNumber)){
			return false;
		}
		return true;
	}
	/**  
	* @throws UnsupportedEncodingException 
	 * @Name: rememberMeByCookie
	* @Description: cookie
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-04-03（创建日期）
	* @Parameters: 无
	* @Return: String home 重跳转home.jsp
	*/
	public static void rememberMeByCookie(UserForm user,String path,HttpServletResponse response) throws UnsupportedEncodingException {
		String name = user.getName();
		String password = user.getPassword();
		String rememberMe = user.getRememberMe();
		System.out.println(rememberMe);
		//处理中文字符
		Cookie nameCookie = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
		Cookie pwdCookie = new Cookie("password", URLEncoder.encode(password, "UTF-8"));
		//设置cookie的有效路径 有效路径定义为项目的根路径
		nameCookie.setPath(path+"/");
		pwdCookie.setPath(path+"/");
		if(rememberMe!=null && rememberMe.equals("yes")){
			nameCookie.setMaxAge(7*24*60*60);
			pwdCookie.setMaxAge(7*24*60*60);
		}else{
			nameCookie.setMaxAge(0);
			pwdCookie.setMaxAge(0);
		}
		response.addCookie(nameCookie);
		response.addCookie(pwdCookie);		
	}
	

}
