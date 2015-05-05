package cn.xxx.elec.dao;


import java.util.List;

import cn.xxx.elec.domain.ElecSystemDDL;

public interface ElecSystemDDLDao extends CommonDao<ElecSystemDDL>{
	public final static String SERVICE_NAME = "cn.xxx.elec.dao.impl.ElecSystemDDLDaoImpl";

	List<Object> findKeyWord();

	String findDDLName(String ddlCode, String keyWord);

	
	
	
}
