package cn.xxx.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xxx.elec.service.ElecRoleService;
import cn.xxx.elec.service.ElecSystemDDLService;
import cn.xxx.elec.util.XmlObject;
import cn.xxx.elec.web.vo.ElecRoleForm;
import cn.xxx.elec.web.vo.ElecSystemDDLForm;
import cn.xxx.elec.web.vo.ElecUserForm;

import com.opensymphony.xwork2.ModelDriven;


@SuppressWarnings("serial")
@Component("elecRoleAction_*")
public class ElecRoleAction extends BaseAction implements ModelDriven<ElecRoleForm> {
	private ElecRoleForm elecRoleForm = new ElecRoleForm();
	
	private ElecRoleService elecRoleService;
	private ElecSystemDDLService elecSystemDDLService;
			
	public ElecRoleService getElecRoleService() {
		return elecRoleService;
	}
	@Resource(name=ElecRoleService.SERVICE_NAME)
	public void setElecRoleService(ElecRoleService elecRoleService) {
		this.elecRoleService = elecRoleService;
	}

	public ElecSystemDDLService getElecSystemDDLService() {
		return elecSystemDDLService;
	}
	@Resource(name=ElecSystemDDLService.SERVICE_NAME)
	public void setElecSystemDDLService(ElecSystemDDLService elecSystemDDLService) {
		this.elecSystemDDLService = elecSystemDDLService;
	}
	/**  
	* @Name: home
	* @Description: 加载角色管理页面
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-03-31 （创建日期）
	* @Parameters: 
	* @Return: home 跳转页面
	*/	
	public String home(){
		//获取角色权限关键字
		List<ElecSystemDDLForm> systemList = elecSystemDDLService.findByKeyWord("角色类型");
		request.put("systemList", systemList);
		//解析xml文件
		List<XmlObject> xmlList= elecRoleService.readXml();
		request.put("xmlList", xmlList);
		return "home";		
	}
	/**  
	* @Name: edit
	* @Description: 1、使用角色的ID查询角色的权限，并与系统所有权限进行比配
	* 				2、使用角色的ID查询角色所拥有的用户
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-03-31 （创建日期）
	* @Parameters: 
	* @Return: edit 跳转页面 roleEdit.jsp
	*/	
	public String edit(){
		//权限
		List<XmlObject> xmlList = elecRoleService.readEditXml(this.elecRoleForm.getRole());
		request.put("xmlList", xmlList);
		//用户
		List<ElecUserForm> userList = elecRoleService.findUserListByRoleID(this.elecRoleForm.getRole());
		request.put("userList", userList);
		return "edit";
	}
	/**  
	* @Name: save
	* @Description: 1、保存角色与权限关系
	* 				2、保存角色与用户关系
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-03-31 （创建日期）
	* @Parameters: 
	* @Return: save 跳转页面 roleIndex.jsp
	*/	
	public String save(){
		System.out.println(this.elecRoleForm);
		elecRoleService.save(this.elecRoleForm);
		return "save";
	}
	@Override
	public ElecRoleForm getModel() {
		return this.elecRoleForm;
	}
	

}

