package cn.xxx.elec.dao;

import java.util.List;

import cn.xxx.elec.domain.ElecUser;

public interface ElecUserDao extends CommonDao<ElecUser>{
	public final static String SERVICE_NAME = "cn.xxx.elec.dao.impl.ElecUserImpl";

	List<Object> findPopedomByloginName(String loginName);

	List<Object[]> findRoleNameByLoginName(String loginName);

	
	
}
