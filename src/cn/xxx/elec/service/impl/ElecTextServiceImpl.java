package cn.xxx.elec.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xxx.elec.dao.ElecTextDao;
import cn.xxx.elec.domain.ElecText;
import cn.xxx.elec.service.ElecTextService;
import cn.xxx.elec.web.vo.ElecTextForm;

@Transactional(readOnly=true)
@Service(ElecTextService.SERVICE_NAME)
public class ElecTextServiceImpl implements ElecTextService {
	
	private ElecTextDao elecTextDao;
	
	
	public ElecTextDao getElecTextDao() {
		return elecTextDao;
	}

	@Resource(name=ElecTextDao.SERVICE_NAME)
	public void setElecTextDao(ElecTextDao elecTextDao) {
		this.elecTextDao = elecTextDao;
	}

	/**  
	* @Name: saveElecText
	* @Description: 保存ElecText的方法
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-13 （创建日期）
	* @Parameters: ElecText elecText 对象
	* @Return: 无
	*/
	@Override
	@Transactional(readOnly=false)
	public void saveElecText(ElecText et) {
		this.elecTextDao.save(et);
	}
	/**  
	* @Name: findCollectionByNoPage
	* @Description: 使用查询条件查询列表
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-13 （创建日期）
	* @Parameters: ElecText elecText 对象
	* @Return: 无
	*/
	@Override
	public List<ElecText> findCollectionByNoPage(ElecTextForm et) {
	/*	
	 * 
	 * SELECT o.textName,o.textRemark FROM elec_text o  where 1=1     放置到DAO层
		AND o.textName LIKE '%测试%'                            放置到Service层                    
		AND o.textRemark LIKE '%service%'
		ORDER BY o.textDate DESC , o.textName ASC 
     */
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(et != null && StringUtils.isNotBlank(et.getTextName())){
			hqlWhere += " and o.textName like ?";
			paramsList.add("%" + et.getTextName() + "%");
		}
		if(et != null && StringUtils.isNotBlank(et.getTextRemark())){
			hqlWhere +=" and o.textRemark like ?";
			paramsList.add("%" + et.getTextRemark() + "%");
		}
		Object[] params = paramsList.toArray();
		/**
		 * 组织排序语句
		 * ORDER BY o.textDate DESC , o.textName ASC 
		 * 
		 * 
		 */
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("o.textDate", "desc");
		orderBy.put("o.textRemark", "asc");
		List<ElecText> list = this.elecTextDao.findCollectionByNoPage(hqlWhere,params,orderBy);
		return list;
	}

}
