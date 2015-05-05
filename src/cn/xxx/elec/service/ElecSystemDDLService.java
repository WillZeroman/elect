package cn.xxx.elec.service;

import java.util.List;

import cn.xxx.elec.domain.ElecCommonMsg;
import cn.xxx.elec.web.vo.ElecCommonMsgForm;
import cn.xxx.elec.web.vo.ElecSystemDDLForm;

public interface ElecSystemDDLService {
	public final static String SERVICE_NAME = "cn.xxx.elec.service.impl.ElecSystemDDLServiceImpl";

	List<ElecSystemDDLForm> findKeyWord();

	List<ElecSystemDDLForm> findByKeyWord(String keyWord);

	void saveElecSystemDDL(ElecSystemDDLForm elecSystemDDLForm);
	
}
