package cn.xxx.elec.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xxx.elec.dao.ElecSystemDDLDao;
import cn.xxx.elec.domain.ElecSystemDDL;

import cn.xxx.elec.service.ElecSystemDDLService;
import cn.xxx.elec.web.vo.ElecSystemDDLForm;

@Transactional(readOnly=true)
@Service(ElecSystemDDLService.SERVICE_NAME)
public class ElecSystemDDLServiceImpl implements ElecSystemDDLService {
	
	private ElecSystemDDLDao elecSystemDDLDao;

	public ElecSystemDDLDao getElecSystemDDLDao() {
		return elecSystemDDLDao;
	}
	@Resource(name=ElecSystemDDLDao.SERVICE_NAME)
	public void setElecSystemDDLDao(ElecSystemDDLDao elecSystemDDLDao) {
		this.elecSystemDDLDao = elecSystemDDLDao;
	}
	/**  
	* @Name: findKeyWord
	* @Description: 查询所有数据类型，去掉重复的。
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-14 （创建日期）
	* @Parameters: 
	* @Return: List<ElecSystemDDLForm> 数据类型列表
	*/
	@Override
	public List<ElecSystemDDLForm> findKeyWord() {
		List<Object> list = elecSystemDDLDao.findKeyWord();
		List<ElecSystemDDLForm> voList = this.elecSystemDDLObjectToVO(list);
		return voList;
	}
	/**  
	* @Name: elecSystemDDLObjectToVO
	* @Description: 将object转化为VO对象中
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-14 （创建日期）
	* @Parameters: 
	* @Return: List<ElecSystemDDL> 数据类型列表
	*/
	private List<ElecSystemDDLForm> elecSystemDDLObjectToVO(List<Object> list) {
		List<ElecSystemDDLForm> voList = new ArrayList<ElecSystemDDLForm>();
		ElecSystemDDLForm e = null;
		for(int i=0;list!=null && i<list.size();i++)
		{
			e = new ElecSystemDDLForm();
			Object o = list.get(i);
			e.setKeyWord(o.toString());
			voList.add(e);
			
		}
		return voList;
	}
	
	/**  
	* @Name: findByKeyWord
	* @Description: 根据数据类型查找VO类型
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-17 （创建日期）
	* @Parameters: 
	* @Return: List<ElecSystemDDLForm> VO数据类型列表
	*/
	@Override
	public List<ElecSystemDDLForm> findByKeyWord(String keyWord) {
		List<ElecSystemDDL> poList = this.findCollection(keyWord);
		List<ElecSystemDDLForm> voList = this.elecSystemDDLPOToVO(poList);
		return voList;
	}
	/**  
	* @Name: findCollection
	* @Description: 根据数据类型查找PO类型,获取数据项集合
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-17 （创建日期）
	* @Parameters: String keyWord
	* @Return: List<ElecSystemDDL> PO数据类型列表
	*/
	private List<ElecSystemDDL> findCollection(String keyWord) {
		String hqlWhere=" and o.keyWord = ?";
		Object[] params = {keyWord};
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("o.ddlCode", "asc");
		List<ElecSystemDDL> poList = elecSystemDDLDao.findCollectionByNoPage(hqlWhere, params, orderBy);	
		return poList;
	}
	/**  
	* @Name: elecSystemDDLPOToVO
	* @Description: 将ElecSystemDDL的PO转化为VO对象中
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-17 （创建日期）
	* @Parameters: List<ElecSystemDDL> poList PO对象
	* @Return: List<ElecSystemDDLForm> VO数据类型列表
	*/
	private List<ElecSystemDDLForm> elecSystemDDLPOToVO(
			List<ElecSystemDDL> poList) {
		List<ElecSystemDDLForm> list = new ArrayList<ElecSystemDDLForm>();
		ElecSystemDDLForm e = null;
		for(int i=0;poList!=null && i<poList.size();i++){
			ElecSystemDDL po = poList.get(i);
			e = new ElecSystemDDLForm();
			e.setDdlName(po.getDdlName());
			e.setDdlCode(String.valueOf(po.getDdlCode()));
			e.setKeyWord(po.getKeyWord());
			e.setSeqID(String.valueOf(po.getSeqID()));
			list.add(e);
		}
		return list;
	}
	/**  
	* @Name: saveElecSystemDDL
	* @Description: 保存数据字典包括数据类型，数据名称
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-30 （创建日期）
	* @Parameters: ElecSystemDDLForm elecSystemDDLForm 存放页面传递的表单值
	* @Return: 无
	*/
	@Transactional(readOnly=false)
	@Override
	public void saveElecSystemDDL(ElecSystemDDLForm elecSystemDDLForm) {
		
		System.out.println(elecSystemDDLForm);
		//得到页面表单值
		String keyWord = elecSystemDDLForm.getKeywordname();
		String typeFlag = elecSystemDDLForm.getTypeflag();
		String [] itemName = elecSystemDDLForm.getItemname();
		//如果是新增数据类型
		
		if(typeFlag!=null && typeFlag.equals("new")){
			this.saveElecSystemDDLWithParams(keyWord,itemName);		
		}
		else if(typeFlag.equals("add")){
			//查找当前数据类型的列表
			List<ElecSystemDDL> list = this.findCollection(keyWord);
			//删除以前的数据项
			elecSystemDDLDao.deleteObjectByCollection(list);
			//保存当前的数据项
			this.saveElecSystemDDLWithParams(keyWord, itemName);
		}
		
	}
	
	private void saveElecSystemDDLWithParams(String keyWord, String[] itemName) {
		List<ElecSystemDDL> list = new ArrayList<ElecSystemDDL>();
		
		for(int i=0;itemName!=null && i<itemName.length;i++){
			ElecSystemDDL elecSystemDDL = new ElecSystemDDL();
			elecSystemDDL.setDdlCode(new Integer(i+1));
			elecSystemDDL.setDdlName(itemName[i]);
			elecSystemDDL.setKeyWord(keyWord);
			list.add(elecSystemDDL);
		}
		elecSystemDDLDao.saveObjectByCollection(list);
	}
	
	

}
