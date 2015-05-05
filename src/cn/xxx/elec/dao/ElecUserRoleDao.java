package cn.xxx.elec.dao;

import java.util.List;

import cn.xxx.elec.domain.ElecUserRole;

public interface ElecUserRoleDao extends CommonDao<ElecUserRole>{
	public final static String SERVICE_NAME = "cn.xxx.elec.dao.impl.ElecUserRoleImpl";

	List<Object[]> findUserListByRoleID(String role);
	
	
}
