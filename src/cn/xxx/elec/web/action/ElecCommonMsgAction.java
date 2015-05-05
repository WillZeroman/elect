package cn.xxx.elec.web.action;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xxx.elec.service.ElecCommonMsgService;
import cn.xxx.elec.web.vo.ElecCommonMsgForm;

import com.opensymphony.xwork2.ModelDriven;


@Component("elecCommonMsgAction_*")
@SuppressWarnings("serial")
public class ElecCommonMsgAction extends BaseAction implements ModelDriven<ElecCommonMsgForm>{
	
	private ElecCommonMsgService ecms;
	private ElecCommonMsgForm elecCommonMsgForm = new ElecCommonMsgForm();
	public ElecCommonMsgService getEcms() {
		return ecms;
	}

	@Resource(name=ElecCommonMsgService.SERVICE_NAME)
	public void setEcms(ElecCommonMsgService ecms) {
		this.ecms = ecms;
	}
	/**  
	* @Name: actingIndex
	* @Description: 查询所有代办事宜列表
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-14 （创建日期）
	* @Parameters: 
	* @Return: String actingIndex 跳转到 actingIndex.jsp
	*/
	public String actingIndex(){
		List<ElecCommonMsgForm> list = ecms.findElecComonMsgFormList();
		request.put("commonList", list);
		return "actingIndex";
	}
	/**  
	* @Name: save
	* @Description: 保存所有代办事宜列表
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-14 （创建日期）
	* @Parameters: 无
	* @Return: 重定向 actingIndex.jsp
	*/
	public String save(){
		ecms.saveElecCommonMsg(elecCommonMsgForm);
		return "save";
	}

	@Override
	public ElecCommonMsgForm getModel() {
		return elecCommonMsgForm;
	}
}
