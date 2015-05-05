package cn.xxx.elec.web.action;

import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xxx.elec.domain.ElecUser;
import cn.xxx.elec.service.ElecCommonMsgService;
import cn.xxx.elec.service.ElecUserService;
import cn.xxx.elec.util.MD5keyBean;
import cn.xxx.elec.web.vo.ElecCommonMsgForm;
import cn.xxx.elec.web.vo.UserForm;

import com.opensymphony.xwork2.ModelDriven;



@SuppressWarnings("serial")
@Component("elecMenuAction_*")
public class ElecMenuAction extends BaseAction implements ModelDriven<UserForm> {
	
	private UserForm userForm = new UserForm();
	private ElecCommonMsgService ecms;
	private ElecUserService elecUserService;
	
	public ElecUserService getElecUserService() {
		return elecUserService;
	}
	@Resource(name=ElecUserService.SERVICE_NAME)
	public void setElecUserService(ElecUserService elecUserService) {
		this.elecUserService = elecUserService;
	}
	public UserForm getUserForm() {
		return userForm;
	}
	public void setUserForm(UserForm userForm) {
		this.userForm = userForm;
	}
	
	public ElecCommonMsgService getEcms() {
		return ecms;
	}
	@Resource(name=ElecCommonMsgService.SERVICE_NAME)
	public void setEcms(ElecCommonMsgService ecms) {
		this.ecms = ecms;
	}
	/**  
	* @Name: home
	* @Description: 验证用户登录名与密码
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-04-03（创建日期）
	* @Parameters: 无
	* @Return: String home 重跳转home.jsp
	*/
	public String home(){
		String loginName = userForm.getName();
		String password = userForm.getPassword();
		//使用登录名，查询用户是否存在
		ElecUser elecUser = elecUserService.findByloginName(loginName);
		MD5keyBean md5 = new MD5keyBean();
		String md5pwd = md5.getkeyBeanofStr(password);
		if(elecUser==null){
			this.addFieldError("error", "用户名不存在");
			return "error";
		}
		if(password==null || password.equals("") || !elecUser.getLoginPwd().equals(md5pwd)){
			this.addFieldError("error", "密码输入错误");
			return "error";
		}
		session.put("global_user", elecUser);
		//使用登录名查询用户权限
		String popedom = elecUserService.findPopedomByloginName(loginName);
		if(popedom == null || "".equals(popedom)){
			this.addFieldError("error", "当前用户没有权限，请与系统管理员联系");
			return "error";
		}
		session.put("global_popedom", popedom);
		//使用登录名查询用户所对应的角色
		Hashtable<String,String> htable = elecUserService.findRoleNameByLoginName(loginName);
		if(htable ==null){
			this.addFieldError("error", "当前用户没有角色，请与系统管理员联系");
			return "error";
		}
		session.put("global_role", htable);
		return "home";
	}
	public String title(){
		return "title";
	}
	public String left(){
		return "left";
	}
	public String change1(){
		return "change1";
	}
	public String loading(){
		return "loading";
	}
	public String alermJX(){
		return "alermJX";
	}
	/**  
	* @Name: alermSB
	* @Description: 加载设备运行情况到主页上
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-136（创建日期）
	* @Parameters: 无
	* @Return: String alermSB 重跳转alermSB.jsp
	*/
	public String alermSB(){
		List<ElecCommonMsgForm> list = ecms.findElecComonMsgFormListByCurrentDate();
		request.put("commonMsg",list);
		return "alermSB";
	}
	public String alermXZ(){
		return "alermXZ";
	}
	public String alermYS(){
		return "alermYS";
	}
	/**  
	* @Name: alermZD
	* @Description: 加载站点运行情况到主页上
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-136（创建日期）
	* @Parameters: 无
	* @Return: String alermZD 重跳转alermZD.jsp
	*/
	public String alermZD(){
		List<ElecCommonMsgForm> list = ecms.findElecComonMsgFormListByCurrentDate();
		request.put("commonMsg",list);
		return "alermZD";
	}
	
	public String logout(){
		
		return "index";
	}
	
	@Override
	public UserForm getModel() {		
		return userForm;
	}
	
}
