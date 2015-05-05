package cn.xxx.elec.domain;

import java.util.Date;
/**
 * 
 * 
 * 对应数据库表Elec_User_Role
 *
 */
@SuppressWarnings("serial")
public class ElecRolePopedom implements java.io.Serializable {
	
	private String roleID;
	private String popedomcode;;
	private String remark;
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getPopedomcode() {
		return popedomcode;
	}
	public void setPopedomcode(String popedomcode) {
		this.popedomcode = popedomcode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "ElecRolePopedom [roleID=" + roleID + ", popedomcode="
				+ popedomcode + ", remark=" + remark + "]";
	}

	
}
