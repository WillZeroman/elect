package cn.xxx.elec.domain;

public class ElecSystemDDL {
	private int seqID;
	private String keyWord;
	private int ddlCode;
	private String ddlName;
	public int getSeqID() {
		return seqID;
	}
	public void setSeqID(int seqID) {
		this.seqID = seqID;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public int getDdlCode() {
		return ddlCode;
	}
	public void setDdlCode(int ddlCode) {
		this.ddlCode = ddlCode;
	}
	public String getDdlName() {
		return ddlName;
	}
	public void setDdlName(String ddlName) {
		this.ddlName = ddlName;
	}
	@Override
	public String toString() {
		return "ElecSystemDDL [seqID=" + seqID + ", keyWord=" + keyWord
				+ ", ddlCode=" + ddlCode + ", ddlName=" + ddlName + "]";
	}
	
}
