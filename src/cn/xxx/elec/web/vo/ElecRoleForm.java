package cn.xxx.elec.web.vo;

import java.util.Arrays;
import java.util.Date;

public class ElecRoleForm {
	private String role;
	private String selectoper[];
	private String selectuser[];
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String[] getSelectoper() {
		return selectoper;
	}

	public void setSelectoper(String[] selectoper) {
		this.selectoper = selectoper;
	}

	public String[] getSelectuser() {
		return selectuser;
	}

	public void setSelectuser(String[] selectuser) {
		this.selectuser = selectuser;
	}

	@Override
	public String toString() {
		return "ElecRoleForm [role=" + role + ", selectoper="
				+ Arrays.toString(selectoper) + ", selectuser="
				+ Arrays.toString(selectuser) + "]";
	}
	
	
	
	
}
