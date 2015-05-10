package cn.xxx.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xxx.elec.service.ElecLogService;
import cn.xxx.elec.web.vo.ElecLogForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
@Component("elecLogAction_*")
public class ElecLogAction extends BaseAction implements
		ModelDriven<ElecLogForm> {
	private ElecLogForm elecLogForm = new ElecLogForm();
	private ElecLogService elecLogService;
	
	public ElecLogService getElecLogService() {
		return elecLogService;
	}
	@Resource(name=ElecLogService.SERVICE_NAME)
	public void setElecLogService(ElecLogService elecLogService) {
		this.elecLogService = elecLogService;
	}
    
	@Override
	public ElecLogForm getModel() {		
		return elecLogForm;
	}
	/**  
	* @Name: home
	* @Description: 跳转到日志管理页面 查询出所有日志信息
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-5-10 （创建日期）
	* @Parameters: 
	* @Return: String "home" 
	*/
	public String home(){
		List<ElecLogForm> list = elecLogService.findElecLogList(elecLogForm);
		return "home";
	}
}
