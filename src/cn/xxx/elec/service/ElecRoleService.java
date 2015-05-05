package cn.xxx.elec.service;

import java.util.List;

import cn.xxx.elec.domain.ElecText;
import cn.xxx.elec.domain.ElecUser;
import cn.xxx.elec.util.XmlObject;
import cn.xxx.elec.web.vo.ElecRoleForm;
import cn.xxx.elec.web.vo.ElecTextForm;
import cn.xxx.elec.web.vo.ElecUserForm;

public interface ElecRoleService {
	public final static String SERVICE_NAME = "cn.xxx.elec.service.impl.ElecRoleServiceImpl";

	List<XmlObject> readXml();

	List<XmlObject> readEditXml(String roleId);

	List<ElecUserForm> findUserListByRoleID(String role);

	void save(ElecRoleForm elecRoleForm);
	
}
