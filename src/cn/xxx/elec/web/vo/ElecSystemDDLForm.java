package cn.xxx.elec.web.vo;

import java.util.Arrays;

public class ElecSystemDDLForm {
	private String seqID;
	private String keyWord;
	private String ddlCode;
	private String ddlName;
	private String keywordname; //保存数据字典的关键字
	//保存数据字典的标识，
	//new：新建一种数据类型 并添加数据项保存，add：在原有的数据类型中编辑，并保存
	private String typeflag;
	//保存数据字典的数据项名称
	private String [] itemname;
	public String getSeqID() {
		return seqID;
	}
	public void setSeqID(String seqID) {
		this.seqID = seqID;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getDdlCode() {
		return ddlCode;
	}
	public void setDdlCode(String ddlCode) {
		this.ddlCode = ddlCode;
	}
	public String getDdlName() {
		return ddlName;
	}
	public void setDdlName(String ddlName) {
		this.ddlName = ddlName;
	}
	public String getKeywordname() {
		return keywordname;
	}
	public void setKeywordname(String keywordname) {
		this.keywordname = keywordname;
	}
	public String getTypeflag() {
		return typeflag;
	}
	public void setTypeflag(String typeflag) {
		this.typeflag = typeflag;
	}
	public String[] getItemname() {
		return itemname;
	}
	public void setItemname(String[] itemname) {
		this.itemname = itemname;
	}
	@Override
	public String toString() {
		return "ElecSystemDDLForm [seqID=" + seqID + ", keyWord=" + keyWord
				+ ", ddlCode=" + ddlCode + ", ddlName=" + ddlName
				+ ", keywordname=" + keywordname + ", typeflag=" + typeflag
				+ ", itemname=" + Arrays.toString(itemname) + "]";
	}
	
	
}
