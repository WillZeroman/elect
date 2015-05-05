package cn.xxx.elec.web.vo;

import java.util.Date;
/**
 * 
 * PO持久层对象，对应数据库表Elec_CommonMsg
 * 
 * 
 * 
 */

@SuppressWarnings("serial")
public class ElecCommonMsgForm implements java.io.Serializable {
	private String comID;	           //主键ID
	private String stationRun;		   //站点运行情况
	private String devRun;				//设备运行情况
	private String createDate;			//日期
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
