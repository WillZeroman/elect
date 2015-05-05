package cn.xxx.elec.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.xxx.elec.dao.ElecSystemDDLDao;
import cn.xxx.elec.domain.ElecSystemDDL;

@Repository(ElecSystemDDLDao.SERVICE_NAME)
public class ElecSystemDDLDaoImpl extends CommonDaoImpl<ElecSystemDDL> implements ElecSystemDDLDao {

	/**  
	* @Name: findKeyWord
	* @Description: ��ѯ�����������ͣ�ȥ���ظ��ġ�
	* @Author: wei�����ߣ�
	* @Version: V1.00 ���汾�ţ�
	* @Create Date: 2014-12-14 ���������ڣ�
	* @Parameters: 
	* @Return: List<ElecSystemDDL> ���������б�
	*/
	@Override
	public List<Object> findKeyWord() {
		String hql = "select DISTINCT o.keyWord from ElecSystemDDL o";
		List<Object> list = this.getSession().createQuery(hql).list();
		return list;
	}
	
	/**  
	* @Name: findDDLName
	* @Description: ʹ���������ͺ�������ı�Ż�ȡ�����������
	* @Author: wei�����ߣ�
	* @Version: V1.00 ���汾�ţ�
	* @Create Date: 2015-1-21 ���������ڣ�
	* @Parameters: String ddlCode ��������
	* 				String keyWord ��������
	* @Return: String ����������
	*/
	@Override
	public String findDDLName(String ddlCode, String keyWord) {
		String hql = "from ElecSystemDDL where keyWord = '" + keyWord +
				"' and ddlCode = " + ddlCode;
		List<ElecSystemDDL> list = this.getSession().createQuery(hql).list();
		String ddlName = "";
		if(list!=null && list.size()>0){
			ElecSystemDDL e = list.get(0);
			ddlName = e.getDdlName();
		}
		return ddlName;
	}

}
