package cn.xxx.elec.web.action;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import cn.xxx.elec.service.ElecLogService;
import cn.xxx.elec.service.ElecSystemDDLService;
import cn.xxx.elec.service.ElecUserService;
import cn.xxx.elec.util.ChartUtils;
import cn.xxx.elec.util.ExcelFileGenerator;
import cn.xxx.elec.web.vo.ElecSystemDDLForm;
import cn.xxx.elec.web.vo.ElecUserForm;

import com.opensymphony.xwork2.ModelDriven;


@Component("elecUserAction_*")
@SuppressWarnings("serial")
public class ElecUserAction extends BaseAction implements ModelDriven<ElecUserForm>{
	
	private ElecUserService eus;
	private ElecSystemDDLService esds;
	private ElecUserForm elecUserForm = new ElecUserForm();
	private ElecLogService elecLogService;
	

	public ElecLogService getElecLogService() {
		return elecLogService;
	}
	@Resource(name=ElecLogService.SERVICE_NAME)
	public void setElecLogService(ElecLogService elecLogService) {
		this.elecLogService = elecLogService;
	}

	public ElecUserService getEus() {
		return eus;
	}

	@Resource(name=ElecUserService.SERVICE_NAME)
	public void setEus(ElecUserService eus) {
		this.eus = eus;
	}
	@Resource(name=ElecSystemDDLService.SERVICE_NAME)
	public void setEsds(ElecSystemDDLService esds){
		this.esds = esds;
	}
	@Override
	public ElecUserForm getModel() {
		return this.elecUserForm;
	}
	/**  
	* @Name: userIndex
	* @Description: 跳转到用户管理页面
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-1-21 （创建日期）
	* @Parameters: 
	* @Return: String userIndex 
	*/
	public String userIndex(){
		//System.out.println("user manager");
		List<ElecUserForm> list = eus.findElecUserList(elecUserForm);
		request.put("userList", list);
		return "userIndex";
	}
	/**  
	* @Name: userAdd
	* @Description: 跳转到用户添加页面
	* @Author: wei
	* @Version: V1.00 
	* @Create Date: 2015-01-23
	* @Parameters: 
	* @Return: String 值为userAdd
	*/
	public String userAdd(){
		initSystemDDL();
		return "userAdd";
	}
	/**  
	* @Name: initSystemDDL
	* @Description: 初始化用户数据字典中的数据项
	* @Author: wei
	* @Version: V1.00 
	* @Create Date: 2015-01-24
	* @Parameters:无 
	* @Return: 无
	*/
	private void initSystemDDL(){
		List<ElecSystemDDLForm> sexList = esds.findByKeyWord("性别");
		List<ElecSystemDDLForm> jctList = esds.findByKeyWord("所属单位");
		List<ElecSystemDDLForm> isDutyList = esds.findByKeyWord("是否在职");
		request.put("sexList", sexList);
		request.put("jctList", jctList);
		request.put("isDutyList", isDutyList);
	}
	/**  
	* @Name: save
	* @Description: 用户添加
	* @Author: wei
	* @Version: V1.00 
	* @Create Date: 2015-01-23
	* @Parameters: 
	* @Return: String 值为save
	*/
	public String save(){
		eus.saveUser(elecUserForm);
		//2015-5-10 将用户的新增和修改信息添加到日志管理中
		if(elecUserForm.getUserID()!=null && !elecUserForm.getUserID().equals("")){
			elecLogService.saveElecLog(ServletActionContext.getRequest(), "用户管理，修改当前用户【" +elecUserForm.getUserName()+"】的信息");
		}
		else{
			elecLogService.saveElecLog(ServletActionContext.getRequest(), "用户管理，新增用户【" +elecUserForm.getUserName()+"】的信息");

		}
		return "save";
	}
	
	/**  
	* @Name: userEdit
	* @Description: 用户添加
	* @Author: wei
	* @Version: V1.00 
	* @Create Date: 2015-01-23
	* @Parameters: 
	* @Return: String 值为userEdit 跳转到userEdit.jsp
	*/
	public String userEdit(){
		initSystemDDL();
		elecUserForm = eus.findElecUser(elecUserForm);
		//ValueStack stack = ActionContext.getContext().getValueStack();
		//stack.push(elecUserForm);
		//System.out.println(elecUserForm);
		String viewFlag = elecUserForm.getViewFlag();
		request.put("viewFlag", viewFlag);		
		return "userEdit";
	}
	
	/**  
	* @Name: delete
	* @Description: 用户添加
	* @Author: wei
	* @Version: V1.00 
	* @Create Date: 2015-01-23
	* @Parameters: 
	* @Return: String 值为userEdit 跳转到userEdit.jsp
	*/
	public String delete(){
		eus.deleteElecUser(elecUserForm);
		return "save";
	}
	
	/**  
	* @Name: export
	* @Description: 导出excel报表
	* @Author: wei
	* @Version: V1.00 
	* @Create Date: 2015-05-11
	* @Parameters: 
	* @Return: 
	*/
	public String export(){
		//获取excel的表头
		ArrayList fieldName = eus.getExcelFiledNameList(); 
		  //获取excel数据
		ArrayList fieldData = eus.getExcelFiledDataList(elecUserForm);

		try {
			OutputStream out = ServletActionContext.getResponse().getOutputStream();
			ServletActionContext.getResponse().reset();
			ServletActionContext.getResponse().setContentType("application/vnd.ms-excel");
			ExcelFileGenerator generator = new ExcelFileGenerator(fieldName, fieldData);
			generator.expordExcel(out);
			//
			System.setOut(new PrintStream(out));
			out.flush();
			if(out!=null){
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/**  
	* @Name: userImport
	* @Description: 跳转到userImport.jsp
	* @Author: wei
	* @Version: V1.00 
	* @Create Date: 2015-06-15
	* @Parameters: 
	* @Return: 
	*/
	public String userImport(){
		return "userImport";
	}
	/**  
	* @Name: Importdata
	* @Description: 导入excel报表,并刷新userIndex页面
	* @Author: wei
	* @Version: V1.00 
	* @Create Date: 2015-06-04
	* @Parameters: 
	* @Return: 
	*/
	public String Importdata(){
		eus.saveElecUserWithExcel(elecUserForm);
		return "export";
	}
	/**  
	* @Name: userReport
	* @Description: 跳转到userReport.jsp
	* @Author: wei
	* @Version: V1.00 
	* @Create Date: 2015-06-16
	* @Parameters: 
	* @Return: 
	*/
	public String userReport(){		
		List<ElecUserForm> list = eus.findUserByChart();
		String filename = ChartUtils.getUserBarChart(list);
		request.put("filename", filename);
		return "userReport";
	}
}
