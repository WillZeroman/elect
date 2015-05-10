package cn.xxx.elec.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.xxx.elec.domain.ElecText;
import cn.xxx.elec.domain.ElecUser;
import cn.xxx.elec.util.XmlObject;
import cn.xxx.elec.web.vo.ElecLogForm;
import cn.xxx.elec.web.vo.ElecRoleForm;
import cn.xxx.elec.web.vo.ElecTextForm;
import cn.xxx.elec.web.vo.ElecUserForm;

public interface ElecLogService {
	public final static String SERVICE_NAME = "cn.xxx.elec.service.impl.ElecLogServiceImpl";

	void saveElecLog(HttpServletRequest request, String details);

	List<ElecLogForm> findElecLogList(ElecLogForm elecLogForm);
}
