package cn.xxx.elec.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xxx.elec.dao.ElecCommonMsgDao;
import cn.xxx.elec.domain.ElecCommonMsg;
import cn.xxx.elec.service.ElecCommonMsgService;
import cn.xxx.elec.util.StringHelper;
import cn.xxx.elec.web.vo.ElecCommonMsgForm;

@Transactional(readOnly=true)
@Service(ElecCommonMsgService.SERVICE_NAME)
public class ElecCommonMsgServiceImpl implements ElecCommonMsgService {
	
	private  ElecCommonMsgDao elecCommonMsgDao;
	

	public ElecCommonMsgDao getElecCommonMsgDao() {
		return elecCommonMsgDao;
	}
	@Resource(name=ElecCommonMsgDao.SERVICE_NAME)
	public void setElecCommonMsgDao(ElecCommonMsgDao elecCommonMsgDao) {
		this.elecCommonMsgDao = elecCommonMsgDao;
	}
	/**  
	* @Name: saveElecCommonMsg
	* @Description: 保存页面（VO）ElecCommonMsgForm的方法
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-13 （创建日期）
	* @Parameters: ElecCommonMsgForm elecCommonMsgForm 对象
	* @Return: 无
	*/
	@Transactional(readOnly=false)
	@Override
	public void saveElecCommonMsg(ElecCommonMsgForm elecCommonMsgForm) {
		elecCommonMsgDao.save(this.elecCommonMsgVOToPO(elecCommonMsgForm));
		
	}
	/**  
	* @Name: elecCommonMsgVOToPO
	* @Description: ElecCommonMsg从VO转到PO
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-13 （创建日期）
	* @Parameters: ElecCommonMsgForm elecCommonMsgForm 对象
	* @Return: 无
	*/
	public ElecCommonMsg elecCommonMsgVOToPO(ElecCommonMsgForm elecCommonMsgForm){
		ElecCommonMsg e = new ElecCommonMsg();
		e.setStationRun(elecCommonMsgForm.getStationRun());
		e.setDevRun(elecCommonMsgForm.getDevRun());
		e.setCreateDate(new Date());
		return e;
	}
	
	/**  
	* @Name: findElecComonMsgFormList
	* @Description: 使用查询条件查询列表
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-14 （创建日期）
	* @Parameters: 无
	* @Return: List<ElecCommonMsgForm> 
	*/
	@Override
	public List<ElecCommonMsgForm> findElecComonMsgFormList() {
		String hqlWhere="";
		Object[] params = null;
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("o.createDate", "desc");
		List<ElecCommonMsg> poList = this.elecCommonMsgDao.findCollectionByNoPage(hqlWhere, params, orderBy);
		List<ElecCommonMsgForm> voList = this.elecComonMsgPOListToVOList(poList);
		return voList;
	}
	/**  
	* @Name: elecComonMsgPOListToVOList
	* @Description: 代办事宜的PO转化为VO对象
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-14 （创建日期）
	* @Parameters: List<ElecCommonMsg> poList
	* @Return: List<ElecCommonMsgForm> 
	*/
	private List<ElecCommonMsgForm> elecComonMsgPOListToVOList(
			List<ElecCommonMsg> poList) {
		List<ElecCommonMsgForm> voList = new ArrayList<ElecCommonMsgForm>();
		ElecCommonMsgForm elecCommonMsgForm = null;
		for(int i=0;poList!=null && i<poList.size();i++)
		{
			elecCommonMsgForm = new ElecCommonMsgForm();
			ElecCommonMsg ecm = poList.get(i);
			elecCommonMsgForm.setComID(ecm.getComID());
			elecCommonMsgForm.setStationRun(ecm.getStationRun());
			elecCommonMsgForm.setDevRun(ecm.getDevRun());
			elecCommonMsgForm.setCreateDate(String.valueOf(ecm.getCreateDate()!=null ? ecm.getCreateDate() : ""));
			voList.add(elecCommonMsgForm);
		}
		return voList;
	}
	/**  
	* @Name: findElecComonMsgFormListByCurrentDate
	* @Description: 查找今天的代办事宜 
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-16 （创建日期）
	* @Parameters: 
	* @Return: List<ElecCommonMsgForm> 
	*/
	@Override
	public List<ElecCommonMsgForm> findElecComonMsgFormListByCurrentDate() {
		//获取当前日期(yyyy-MM-dd)		
		java.sql.Date d = StringHelper.utilDateConvertSqlDate(new java.util.Date());
		//System.out.println(d);
		String hqlWhere=" and o.createDate = ?";
		Object[] params = new Object[]{d};
		//Object[] params = {d};
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("o.createDate", "desc");
		List<ElecCommonMsg> list = elecCommonMsgDao.findCollectionByNoPage(hqlWhere, params, orderBy);
		for(int i=0;list!=null && i<list.size();i++){
			System.out.println(list.get(i));
		}
		List<ElecCommonMsgForm> voList = this.elecComonMsgPOListToVOList(list);
		return voList;
	}



}
