package cn.xxx.elec.util;

@SuppressWarnings("serial")
public class XmlObject implements java.io.Serializable {
	private String code;
	private String name;
	private String parentCode;
	private String parentName;
	private boolean flag;
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	@Override
	public String toString() {
		return "XmlObject [code=" + code + ", name=" + name + ", parentCode="
				+ parentCode + ", parentName=" + parentName + "]";
	}
	
	
}
