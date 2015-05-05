package cn.xxx.elec.domain;

import java.util.Date;
/**
 * 
 * 
 * 对应数据库表Elec_User_Role
 *
 */
@SuppressWarnings("serial")
public class ElecUserRole implements java.io.Serializable {
	
	private Integer seqID;
	private String userID;
	private String roleID;
	private String remark;
	public Integer getSeqID() {
		return seqID;
	}
	public void setSeqID(Integer seqID) {
		this.seqID = seqID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
