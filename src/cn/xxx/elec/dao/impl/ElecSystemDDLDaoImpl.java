package cn.xxx.elec.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.xxx.elec.dao.ElecSystemDDLDao;
import cn.xxx.elec.domain.ElecSystemDDL;

@Repository(ElecSystemDDLDao.SERVICE_NAME)
public class ElecSystemDDLDaoImpl extends CommonDaoImpl<ElecSystemDDL> implements ElecSystemDDLDao {

	/**  
	* @Name: findKeyWord
	* @Description: 查询所有数据类型，去掉重复的。
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-14 （创建日期）
	* @Parameters: 
	* @Return: List<ElecSystemDDL> 数据类型列表
	*/
	@Override
	public List<Object> findKeyWord() {
		String hql = "select DISTINCT o.keyWord from ElecSystemDDL o";
		List<Object> list = this.getSession().createQuery(hql).list();
		return list;
	}
	
	/**  
	* @Name: findDDLName
	* @Description: 使用数据类型和数据项的编号获取数据项的名称
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-1-21 （创建日期）
	* @Parameters: String ddlCode 数据项编号
	* 				String keyWord 数据类型
	* @Return: String 数据项名称
	*/
	@Override
	public String findDDLName(String ddlCode, String keyWord) {
		String hql = "from ElecSystemDDL where keyWord = '" + keyWord +
				"' and ddlCode = " + ddlCode;
		List<ElecSystemDDL> list = this.getSession().createQuery(hql).list();
		String ddlName = "";
		if(list!=null && list.size()>0){
			ElecSystemDDL e = list.get(0);
			ddlName = e.getDdlName();
		}
		return ddlName;
	}

}
