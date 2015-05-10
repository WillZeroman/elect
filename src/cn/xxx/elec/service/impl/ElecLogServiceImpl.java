package cn.xxx.elec.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xxx.elec.dao.ElecLogDao;
import cn.xxx.elec.domain.ElecLog;
import cn.xxx.elec.domain.ElecUser;
import cn.xxx.elec.service.ElecLogService;
import cn.xxx.elec.web.vo.ElecLogForm;
@Transactional(readOnly=true)
@Service(ElecLogService.SERVICE_NAME)
public class ElecLogServiceImpl implements ElecLogService {
	private ElecLogDao elecLogDao;

	public ElecLogDao getElecLogDao() {
		return elecLogDao;
	}
	@Resource(name=ElecLogDao.SERVICE_NAME)
	public void setElecLogDao(ElecLogDao elecLogDao) {
		this.elecLogDao = elecLogDao;
	}
	/**  
	* @throws  
	* @Name: saveElecLog
	* @Description: 保存日志记录
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-05-10（创建日期）
	* @Parameters: HttpServletRequest request
	* 			   String details
	* @Return: 
	*/
	@Transactional(readOnly=false)
	public void saveElecLog(HttpServletRequest request, String details) {
		ElecLog elecLog = new ElecLog();
		elecLog.setIpAddress(request.getRemoteAddr());
		ElecUser elecUser = (ElecUser) request.getSession().getAttribute("global_user");	
		elecLog.setOpeName(elecUser.getUserName());
		elecLog.setOpeTime(new Date());
		elecLog.setDetails(details);
		System.out.println(elecLog);
		elecLogDao.save(elecLog);		
	}
	/**  
	* @throws  
	* @Name: saveElecLog
	* @Description: 保存日志记录
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-05-10（创建日期）
	* @Parameters: HttpServletRequest request
	* 			   String details
	* @Return: 
	*/
	public List<ElecLogForm> findElecLogList(ElecLogForm elecLogForm) {
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecLogForm!=null && elecLogForm.getOpeName()!=null && elecLogForm.getOpeTime()!=null){
			hqlWhere += " and o.opeName like ?";
			paramsList.add(elecLogForm.getOpeName());
		}
		Object[] params = paramsList.toArray();
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("o.opeTime", "desc");
		List<ElecLog> list = elecLogDao.findCollectionByNoPage(hqlWhere, params, orderBy);
		List<ElecLogForm> listVO = this.toVOList(list);
		return listVO;
	}
	/**  
	* @throws  
	* @Name: toVOList
	* @Description: PO 转换为VO对象
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-05-10（创建日期）
	* @Parameters: List<ElecLog> list
	* @Return: List<ElecLogForm>
	*/
	private List<ElecLogForm> toVOList(List<ElecLog> list) {
		List<ElecLogForm> listVO = new ArrayList<ElecLogForm>();
		ElecLogForm elecLogForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			ElecLog elecLog = list.get(i);
			elecLogForm = new ElecLogForm();
			elecLogForm.setLogID(elecLog.getLogID());
			elecLogForm.setIpAddress(elecLog.getIpAddress());
			elecLogForm.setOpeName(elecLog.getOpeName());
			elecLogForm.setOpeTime(String.valueOf(elecLog.getOpeTime()!=null?elecLog.getOpeTime():""));
			elecLogForm.setDetails(elecLog.getDetails());
			listVO.add(elecLogForm);
		}
		return listVO;
	}
	
}
