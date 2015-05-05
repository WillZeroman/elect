package cn.xxx.elec.service;

import java.util.List;

import cn.xxx.elec.domain.ElecText;
import cn.xxx.elec.web.vo.ElecTextForm;

public interface ElecTextService {
	public final static String SERVICE_NAME = "cn.xxx.elec.service.impl.ElecTextServiceImpl";
	void saveElecText(ElecText et);
	List<ElecText> findCollectionByNoPage(
			ElecTextForm et);
}
