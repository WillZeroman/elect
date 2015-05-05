package cn.xxx.elec.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xxx.elec.dao.ElecRolePopedomDao;
import cn.xxx.elec.dao.ElecSystemDDLDao;
import cn.xxx.elec.dao.ElecUserDao;
import cn.xxx.elec.dao.ElecUserRoleDao;
import cn.xxx.elec.domain.ElecRolePopedom;
import cn.xxx.elec.domain.ElecUserRole;
import cn.xxx.elec.service.ElecRoleService;
import cn.xxx.elec.util.StringHelper;
import cn.xxx.elec.util.XmlObject;
import cn.xxx.elec.web.vo.ElecRoleForm;
import cn.xxx.elec.web.vo.ElecUserForm;

@Transactional(readOnly=true)
@Service(ElecRoleService.SERVICE_NAME)
public class ElecRoleServiceImpl implements ElecRoleService {
	private ElecUserDao elecUserDao;
	private ElecSystemDDLDao elecSystemDDLdao;
	private ElecRolePopedomDao elecRolePopedomDao;
	private ElecUserRoleDao elecUserRoleDao;
	public ElecUserRoleDao getElecUserRoleDao() {
		return elecUserRoleDao;
	}
	@Resource(name=ElecUserRoleDao.SERVICE_NAME)
	public void setElecUserRoleDao(ElecUserRoleDao elecUserRoleDao) {
		this.elecUserRoleDao = elecUserRoleDao;
	}
	public ElecRolePopedomDao getElecRolePopedomDao() {
		return elecRolePopedomDao;
	}
	@Resource(name=ElecRolePopedomDao.SERVICE_NAME)
	public void setElecRolePopedomDao(ElecRolePopedomDao elecRolePopedomDao) {
		this.elecRolePopedomDao = elecRolePopedomDao;
	}
	public ElecUserDao getElecUserDao() {
		return elecUserDao;
	}
	@Resource(name=ElecUserDao.SERVICE_NAME)
	public void setElecUserDao(ElecUserDao elecUserDao) {
		this.elecUserDao = elecUserDao;
	}
	public ElecSystemDDLDao getElecSystemDDLdao() {
		return elecSystemDDLdao;
	}
	@Resource(name=ElecSystemDDLDao.SERVICE_NAME)
	public void setElecSystemDDLdao(ElecSystemDDLDao elecSystemDDLdao) {
		this.elecSystemDDLdao = elecSystemDDLdao;
	}
	/**  
	* @Name: readXml
	* @Description: 解析xml文件 用jdom解析
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-03-31 （创建日期）
	* @Parameters: 
	* @Return: List<XmlObject> 权限的集合
	*/	
	@Override
	public List<XmlObject> readXml() {
		SAXBuilder sb=new SAXBuilder();
		Document doc = null;
		try {
			doc = sb.build(ElecRoleServiceImpl.class.getClassLoader().getResourceAsStream("Function.xml"));
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		} 
		 Element root= doc.getRootElement();
		 List<Element> list= root.getChildren("Function");
		 List<XmlObject> xmlObjects = new ArrayList<XmlObject>();
		 XmlObject o = null;
		  for(int i=0;i<list.size();i++){
		   Element element=(Element)list.get(i);
		   o = new XmlObject();
		   o.setCode(element.getChildText("FunctionCode"));
		   o.setName(element.getChildText("FunctionName"));
		   o.setParentCode(element.getChildText("ParentCode"));
		   o.setParentName(element.getChildText("ParentName"));
		   //System.out.println(o);
		   xmlObjects.add(o);
		  }
		return xmlObjects;
	}
	/**  
	* @Name: readEditXml
	* @Description: 解析xml文件 用jdom解析 并查询角色具有的权限
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-03-31 （创建日期）
	* @Parameters: 
	* @Return: List<XmlObject> 权限的集合
	*/	
	@Override
	public List<XmlObject> readEditXml(String roleId) {
		ElecRolePopedom elecRolePopedom = elecRolePopedomDao.findObjectByID(roleId);
		if(elecRolePopedom == null){
			return this.readXml();
		}
		List<XmlObject> list = this.compareByPopedom(elecRolePopedom.getPopedomcode());
		return list;
	}
	/**  
	* @Name: compareByPopedom
	* @Description:匹配权限
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-03-31 （创建日期）
	* @Parameters: 
	* @Return: List<XmlObject> 权限的集合
	*/	
	private List<XmlObject> compareByPopedom(String popedomcode) {
		List<XmlObject> result = new ArrayList<XmlObject>();
		List<XmlObject> list = this.readXml();
		if(popedomcode == null || popedomcode.equals("")){
			return list;
		}
		for(int i=0;list!=null && i<list.size();i++){
			XmlObject object =list.get(i);
			//表示当前权限被选中
			if(popedomcode.contains(object.getCode())){
				object.setFlag(true);
			}
			else{
				object.setFlag(false);
			}
			result.add(object);
		}
		return result;
	}
	/**  
	* @Name: findUserListByRoleID
	* @Description:查询用户列表集合，获取系统中所有用户，并与该角色进行匹配
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-04-03 （创建日期）
	* @Parameters: 
	* @Return: List<ElecUserForm> 用户的集合
	*/
	@Override
	public List<ElecUserForm> findUserListByRoleID(String role) {
		List<Object[]> list = elecUserRoleDao.findUserListByRoleID(role);
		List<ElecUserForm> formList = this.toVOList(list);
		return formList;
	}
	
	/**  
	* @Name: toVOList
	* @Description:转换为vo对象
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-04-03 （创建日期）
	* @Parameters: List<Object[]> list
	* @Return: List<ElecUserForm> 用户的集合
	*/
	private List<ElecUserForm> toVOList(List<Object[]> list) {
		ElecUserForm elecUserForm = null;
		List<ElecUserForm> VOlist = new ArrayList<ElecUserForm>();
		for(int i=0;list!=null && i<list.size();i++)
		{
			Object[] objects = list.get(i);
			elecUserForm = new ElecUserForm();
			//System.out.println(objects[0].toString());
			elecUserForm.setFlag(objects[0].toString().equals("1")?true:false);
			elecUserForm.setUserID(objects[1].toString());
			elecUserForm.setUserName(objects[2].toString());
			elecUserForm.setLoginName(objects[3].toString());
			//System.out.println(elecUserForm);
			VOlist.add(elecUserForm);
		}
		//System.out.println(VOlist.size());
		return VOlist;
	}
	/**  
	* @Name: save
	* @Description:1、保存角色-权限表
	*              2、保存角色-用户表
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-04-04 （创建日期）
	* @Parameters: ElecRoleForm elecRoleForm
	* @Return: 
	*/
	@Override
	@Transactional(readOnly=false)
	public void save(ElecRoleForm elecRoleForm) {
		//若 角色权限表中不存在roleID 则新增加一条记录
		ElecRolePopedom elecRolePopedom = new ElecRolePopedom();
		elecRolePopedom.setRoleID(elecRoleForm.getRole());
		elecRolePopedom.setPopedomcode(StringHelper.ArraysToString(elecRoleForm.getSelectoper()));
		elecRolePopedomDao.saveOrUpdate(elecRolePopedom);
		
	    //保存用户-角色表
		
		if(elecRoleForm.getSelectuser()!=null){
		    //根据roleID查询所有userRole表中的数据
			String hqlWhere = " and o.roleID = ?";
			Object[] params = {elecRoleForm.getRole()};
			List<ElecUserRole> list = elecUserRoleDao.findCollectionByNoPage(hqlWhere, params, null);
		    //删除
			elecUserRoleDao.deleteObjectByCollection(list);
			//保存
			ElecUserRole elecUserRole = null;
			for(int i=elecRoleForm.getSelectuser().length-1;i>=0;i--){
				elecUserRole = new ElecUserRole();
				elecUserRole.setRoleID(elecRoleForm.getRole());
				elecUserRole.setUserID(elecRoleForm.getSelectuser()[i]);
				elecUserRoleDao.save(elecUserRole);
			}
			
		}
		
	}
	
	

}
