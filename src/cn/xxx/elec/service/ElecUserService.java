package cn.xxx.elec.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import cn.xxx.elec.domain.ElecUser;
import cn.xxx.elec.web.vo.ElecUserForm;

public interface ElecUserService {
	public final static String SERVICE_NAME = "cn.xxx.elec.service.impl.ElecUserServiceImpl";

	List<ElecUserForm> findElecUserList(ElecUserForm elecUserForm);

	void saveUser(ElecUserForm elecUserForm);

	ElecUserForm findElecUser(ElecUserForm elecUserForm);

	void deleteElecUser(ElecUserForm elecUserForm);

	int checkLoginName(String loginName);

	ElecUser findByloginName(String loginName);

	String findPopedomByloginName(String loginName);

	Hashtable<String,String> findRoleNameByLoginName(String loginName);

	ArrayList getExcelFiledNameList();

	ArrayList getExcelFiledDataList(ElecUserForm elecUserForm);

	void saveElecUserWithExcel(ElecUserForm elecUserForm);
	
}
