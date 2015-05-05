package cn.xxx.elec.service;

import java.util.List;

import cn.xxx.elec.domain.ElecCommonMsg;
import cn.xxx.elec.web.vo.ElecCommonMsgForm;

public interface ElecCommonMsgService {
	public final static String SERVICE_NAME = "cn.xxx.elec.service.impl.ElecCommonMsgServiceImpl";
	void saveElecCommonMsg(ElecCommonMsgForm elecCommonMsgForm);
	//List<ElecCommonMsg> findCollectionByNoPage(
	//		ElecCommonMsg elecCommonMsg);
	List<ElecCommonMsgForm> findElecComonMsgFormList();
	List<ElecCommonMsgForm> findElecComonMsgFormListByCurrentDate();
	
}
