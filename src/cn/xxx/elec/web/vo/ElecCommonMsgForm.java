package cn.xxx.elec.web.vo;

import java.util.Date;
/**
 * 
 * PO�־ò���󣬶�Ӧ���ݿ��Elec_CommonMsg
 * 
 * 
 * 
 */

@SuppressWarnings("serial")
public class ElecCommonMsgForm implements java.io.Serializable {
	private String comID;	           //����ID
	private String stationRun;		   //վ���������
	private String devRun;				//�豸�������
	private String createDate;			//����
	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getStationRun() {
		return stationRun;
	}
	public void setStationRun(String stationRun) {
		this.stationRun = stationRun;
	}
	public String getDevRun() {
		return devRun;
	}
	public void setDevRun(String devRun) {
		this.devRun = devRun;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "ElecCommonMsg [comID=" + comID + ", stationRun=" + stationRun
				+ ", devRun=" + devRun + ", createDate=" + createDate + "]";
	}
	
}
