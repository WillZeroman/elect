package cn.xxx.elec.web.action;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.xxx.elec.service.ElecSystemDDLService;
import cn.xxx.elec.web.vo.ElecSystemDDLForm;

import com.opensymphony.xwork2.ModelDriven;


@Component("elecSystemDDLAction_*")
@SuppressWarnings("serial")
public class ElecSystemDDLAction extends BaseAction implements ModelDriven<ElecSystemDDLForm>{

	private ElecSystemDDLForm elecSystemDDLForm = new ElecSystemDDLForm();
	private ElecSystemDDLService esds;	
	
	public ElecSystemDDLService getEsds() {
		return esds;
	}
	@Resource(name=ElecSystemDDLService.SERVICE_NAME)
	public void setEsds(ElecSystemDDLService esds) {
		this.esds = esds;
	}

	@Override
	public ElecSystemDDLForm getModel() {
		return elecSystemDDLForm;
	}
	/**  
	* @Name: home
	* @Description: 查询所有数据类型，去掉重复的。
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-14 （创建日期）
	* @Parameters: 
	* @Return: String home 跳转到 dictiona.jsp
	*/
	public String home(){
		//System.out.println("elec SYSTEMDDL action------");
		List<ElecSystemDDLForm> list = esds.findKeyWord();
		request.put("systemDDLList", list);
		return "home";
	}
	
	/**  
	* @Name: edit
	* @Description: 查询所有数据类型，去掉重复的。
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-17 （创建日期）
	* @Parameters: 
	* @Return: String edit 跳转到 dictionaryEdit.jsp
	*/
	public String edit(){
		String keyWord = elecSystemDDLForm.getKeyWord();
		List<ElecSystemDDLForm> list = esds.findByKeyWord(keyWord);
		request.put("systemDDL", list);
		return "edit";
	}
	
	/**  
	* @Name: save
	* @Description: 查询所有数据类型，去掉重复的。
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-17 （创建日期）
	* @Parameters: 
	* @Return: String save 跳转到 dictionaryEdit.jsp
	*/
	public String save(){
		esds.saveElecSystemDDL(elecSystemDDLForm);
		
		return "save";
	}
}
