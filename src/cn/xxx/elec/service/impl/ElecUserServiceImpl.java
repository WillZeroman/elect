package cn.xxx.elec.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xxx.elec.dao.ElecSystemDDLDao;
import cn.xxx.elec.dao.ElecUserDao;
import cn.xxx.elec.domain.ElecUser;
import cn.xxx.elec.service.ElecUserService;
import cn.xxx.elec.util.GenerateSqlFromExcel;
import cn.xxx.elec.util.MD5keyBean;
import cn.xxx.elec.util.StringHelper;
import cn.xxx.elec.web.vo.ElecUserForm;

@Transactional(readOnly=true)
@Service(ElecUserService.SERVICE_NAME)
public class ElecUserServiceImpl implements ElecUserService {
	
	private ElecUserDao elecUserDao;
	private ElecSystemDDLDao elecSystemDDLDao;
	public ElecUserDao getElecUserDao() {
		return elecUserDao;
	}

	@Resource(name=ElecUserDao.SERVICE_NAME)
	public void setElecUserDao(ElecUserDao elecUserDao) {
		this.elecUserDao = elecUserDao;
	}

	public ElecSystemDDLDao getElecSystemDDLDao() {
		return elecSystemDDLDao;
	}

	@Resource(name=ElecSystemDDLDao.SERVICE_NAME)
	public void setElecSystemDDLDao(ElecSystemDDLDao elecSystemDDLDao) {
		this.elecSystemDDLDao = elecSystemDDLDao;
	}

	/**  
	* @Name: saveElecText
	* @Description: 保存ElecText的方法
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2014-12-13 （创建日期）
	* @Parameters: ElecText elecText 对象
	* @Return: 无
	*/
	@Override
	public List<ElecUserForm> findElecUserList(ElecUserForm elecUserForm) {
		//查询条件
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecUserForm!=null && elecUserForm.getUserName()!=null && 
				!elecUserForm.getUserName().equals("")){
			hqlWhere +=" and o.userName like ?";
			paramsList.add("%"+elecUserForm.getUserName()+"%");
		}
		Object[] params = paramsList.toArray();
		//排序顺序
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("o.onDutyDate", "desc");
		List<ElecUser> list = elecUserDao.findCollectionByNoPage(hqlWhere, params, orderBy);
		List<ElecUserForm> VOList = this.elecUserPOToVO(list);
		return VOList;
	}
	
	/**  
	* @Name: elecUserPOToVO
	* @Description: 将ElecUser的PO对象转化为VO对象
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-1-21 （创建日期）
	* @Parameters: List<ElecUser> list PO对象
	* @Return: List<ElecUserForm>  VO对象
	*/
	private List<ElecUserForm> elecUserPOToVO(List<ElecUser> list) {
		ElecUserForm elecUserForm = null;
		List<ElecUserForm> VOList = new ArrayList<ElecUserForm>();
		for(int i=0;list!=null && i<list.size();i++){
			ElecUser elecUser = list.get(i);
			elecUserForm = new ElecUserForm();
			elecUserForm.setUserID(elecUser.getUserID());
			elecUserForm.setLoginName(elecUser.getLoginName());
			elecUserForm.setUserName(elecUser.getUserName());
			if(elecUser.getSexID()!=null && !elecUser.getSexID().equals(""))
			{
				elecUserForm.setSexID(elecSystemDDLDao.findDDLName(elecUser.getSexID(),"性别"));
				
			}	
			else{
				elecUserForm.setSexID("");
			}
			elecUserForm.setContactTel(elecUser.getContactTel());		
			elecUserForm.setIsDuty((elecUser.getIsDuty()!=null && !elecUser.getIsDuty().equals("")) 
						? elecSystemDDLDao.findDDLName(elecUser.getSexID(),"是否在职") : "");			
			VOList.add(elecUserForm);
			
		}
		return VOList;
	}
	/**  
	* @Name: saveUser
	* @Description: 保存elecUserForm的方法
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-1-24 （创建日期）
	* @Parameters: ElecUserForm elecUserForm 对象
	* @Return: 无
	*/
	@Override
	@Transactional(readOnly=false)
	public void saveUser(ElecUserForm elecUserForm) {
		if(elecUserForm.getUserID()!=null && !elecUserForm.getUserID().equals("")){
			elecUserDao.update(this.toPO(elecUserForm));
		}
		else{
			elecUserDao.save(this.toPO(elecUserForm));	
		}
			
	}
	/**  
	* @Name: toPO
	* @Description: 将elecUserForm对象转化为PO对象
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-1-24 （创建日期）
	* @Parameters: ElecUserForm elecUserForm 对象
	* @Return: ElecUser
	*/
	private ElecUser toPO(ElecUserForm elecUserForm) {
		ElecUser e = new ElecUser();
		if(elecUserForm == null){
			return null;
		}
		e.setUserID(elecUserForm.getUserID());
		e.setUserName(elecUserForm.getUserName());
		e.setJctID(elecUserForm.getJctID());
		e.setLoginName(elecUserForm.getLoginName());
		//使用md5加密
		String pwd = elecUserForm.getLoginPwd();
		//初始化密码
		if(pwd==null || "".equals(pwd)){
			pwd="123456";
		}
		String md5pwd = "";
		//md5Flag 为1 表示没有修改 则不需要加密
		if(elecUserForm.getMd5Flag()!=null && elecUserForm.getMd5Flag().equals("1")){
			md5pwd = pwd;
		}
		else{
			MD5keyBean md5 = new MD5keyBean();
		    md5pwd = md5.getkeyBeanofStr(pwd);
		}	
		e.setLoginPwd(md5pwd);
		e.setSexID(elecUserForm.getSexID());
		e.setBirthday(StringHelper.stringConvertDate(elecUserForm.getBirthday()));
		e.setAddress(elecUserForm.getAddress());
		e.setContactTel(elecUserForm.getContactTel());
		e.setEmail(elecUserForm.getEmail());
		e.setMobile(elecUserForm.getMobile());
		e.setIsDuty(elecUserForm.getIsDuty());
		e.setOnDutyDate(StringHelper.stringConvertDate(elecUserForm.getOnDutyDate()));
		e.setOffDutyDate(StringHelper.stringConvertDate(elecUserForm.getOffDutyDate()));
		e.setRemark(elecUserForm.getRemark());
		return e;
	}
	/**  
	* @Name: findElecUser
	* @Description: 根据user 的id号查找user信息
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-1-24 （创建日期）
	* @Parameters: ElecUserForm elecUserForm
	* @Return: ElecUserForm 对象
	*/
	@Override
	public ElecUserForm findElecUser(ElecUserForm elecUserForm) {
		ElecUser e = new ElecUser();
		e = elecUserDao.findObjectByID(elecUserForm.getUserID());
//		System.out.println(e);
//		System.out.println(e.getBirthday().toString());
		return this.toVO(e,elecUserForm);
	}
	/**  
	* @Name: toVO
	* @Description: 将elecUser对象转化为VO对象
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-1-24 （创建日期）
	* @Parameters: ElecUser elecUser 对象
	* @Return: ElecUserForm
	*/
	private ElecUserForm toVO(ElecUser elecUser,ElecUserForm elecUserForm) {
		//ElecUserForm elecUserForm = new ElecUserForm();
		
		elecUserForm.setUserID(elecUser.getUserID());
		elecUserForm.setJctID((elecUser.getJctID()!=null && !elecUser.getJctID().equals(""))
								?elecSystemDDLDao.findDDLName(elecUser.getJctID(), "所属单位")
								:"");
		elecUserForm.setLoginName(elecUser.getLoginName());
		elecUserForm.setUserName(elecUser.getUserName());
		elecUserForm.setLoginPwd(elecUser.getLoginPwd());
		elecUserForm.setPasswordconfirm(elecUser.getLoginPwd());
		if(elecUser.getSexID()!=null && !elecUser.getSexID().equals(""))
		{
			elecUserForm.setSexID(elecSystemDDLDao.findDDLName(elecUser.getSexID(),"性别"));
			
		}	
		else{
			elecUserForm.setSexID("");
		}
		elecUserForm.setBirthday(StringHelper.dateConvertString(elecUser.getBirthday()));
		elecUserForm.setAddress(elecUser.getAddress());
		elecUserForm.setEmail(elecUser.getEmail());
		elecUserForm.setContactTel(elecUser.getContactTel());	
		elecUserForm.setMobile(elecUser.getMobile());
		elecUserForm.setOffDutyDate(StringHelper.dateConvertString(elecUser.getOffDutyDate()));
		elecUserForm.setOnDutyDate(StringHelper.dateConvertString(elecUser.getOnDutyDate()));
		elecUserForm.setRemark(elecUser.getRemark());
			
		elecUserForm.setIsDuty((elecUser.getIsDuty()!=null && !elecUser.getIsDuty().equals("")) 
					? elecSystemDDLDao.findDDLName(elecUser.getSexID(),"是否在职") : "");			
		
		return elecUserForm;
	}
	
	/**  
	* @Name: deleteElecUser
	* @Description: 删除elecUser对象
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-1-25 （创建日期）
	* @Parameters: ElecUserForm elecUserForm 对象
	* @Return: 
	*/
	@Override
	@Transactional(readOnly=false)
	public void deleteElecUser(ElecUserForm elecUserForm) {
		elecUserDao.deleteObject(this.toPO(elecUserForm));		
	}

	@Override
	public int checkLoginName(String loginName) {
		String hqlWhere = " and o.loginName = ?";
		Object[] params = {loginName};
		List<ElecUser> list = elecUserDao.findCollectionByNoPage(hqlWhere, params, null);
		if(list!=null && list.size()>0){
			return 1;
		}
		else			
			return 0;
	}
	/**  
	* @Name: findByloginName
	* @Description:
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-1-25 （创建日期）
	* @Parameters: ElecUserForm elecUserForm 对象
	* @Return: 
	*/
	@Override
	public ElecUser findByloginName(String loginName) {
		String hqlWhere = " and o.loginName = ?";
		Object[] params = {loginName};
		List<ElecUser> list = elecUserDao.findCollectionByNoPage(hqlWhere, params, null);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		else			
			return null;

	}

	@Override
	public String findPopedomByloginName(String loginName) {
		List<Object> list = elecUserDao.findPopedomByloginName(loginName);
		String popedom = "";
		if(list != null && list.size()>0){
			popedom = String.valueOf(list.get(0));
		}
		return popedom;
	}

	
	@Override
	public Hashtable<String, String> findRoleNameByLoginName(String loginName) {
		List<Object[]> list = elecUserDao.findRoleNameByLoginName(loginName);
		Hashtable<String, String> ht = null;
		if(list!=null && list.size()>0){
			ht = new Hashtable<String, String>();
			for(int i=0;i<list.size();i++){
				Object[] objects = list.get(i);
				ht.put(objects[0].toString(), objects[1].toString());
			}
		}
		return ht;
	}
	/**  
	* @Name: getExcelFiledNameList
	* @Description:
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-5-11 （创建日期）
	* @Parameters: ArrayList Excel标题集
	* @Return: 
	*/
	@Override
	public ArrayList getExcelFiledNameList() {
		String[] titles ={"登录名","用户姓名","性别","联系电话","是否在职"};
		ArrayList filedName = new ArrayList();
		for(int i=0;i<titles.length;i++){
			filedName.add(titles[i]);
		}
		return filedName;
	}
	/**  
	* @Name: getExcelFiledDataList
	* @Description:获取数据
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-5-11 （创建日期）
	* @Parameters: ArrayList Excel数据集
	* @Return: 
	*/
	@Override
	public ArrayList getExcelFiledDataList(ElecUserForm elecUserForm) {
		//查询条件
		String hqlWhere = "";
		List<String> paramsList = new ArrayList<String>();
		if(elecUserForm!=null && elecUserForm.getUserName()!=null && 
				!elecUserForm.getUserName().equals("")){
			hqlWhere +=" and o.userName like ?";
			paramsList.add("%"+elecUserForm.getUserName()+"%");
		}
		Object[] params = paramsList.toArray();
		//排序顺序
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("o.onDutyDate", "desc");
		List<ElecUser> list = elecUserDao.findCollectionByNoPage(hqlWhere, params, orderBy);
		List<ElecUserForm> voList = this.elecUserPOToVO(list);
		//构造报表导出数据
		ArrayList fieldList = new ArrayList();
		for(int i=0;voList!=null && i<voList.size();i++){
			ArrayList dataList = new ArrayList();
			ElecUserForm temp = voList.get(i);
			dataList.add(temp.getLoginName());
			dataList.add(temp.getUserName());
			dataList.add(temp.getSexID());
			dataList.add(temp.getContactTel());
			dataList.add(temp.getIsDuty());
			fieldList.add(dataList);
		}
		return  fieldList;
	}
	/**  
	* @Name: saveElecUserWithExcel
	* @Description:从excel中保存用户数据
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-04（创建日期）
	* @Parameters:
	* @Return: 
	*/
	@Transactional(readOnly=false)
	@Override
	public void saveElecUserWithExcel(ElecUserForm elecUserForm) {
		File file = elecUserForm.getFile();
		GenerateSqlFromExcel generate = new GenerateSqlFromExcel();
		try {
			ArrayList<String[]> list = generate.generateStationBugSql(file);
			ElecUser elecUser = null;
			MD5keyBean md5 = new MD5keyBean();
			for(int i=0;list!=null && i<list.size();i++){
		    	String[] data = list.get(i);
		    	elecUser = new ElecUser();		    	
		    	elecUser.setLoginName(data[0].toString());
		    	elecUser.setLoginPwd(md5.getkeyBeanofStr(data[1]));
		    	elecUser.setUserName(data[2]);
		    	elecUser.setSexID(data[3]);
		    	elecUser.setJctID(data[4]);
		    	elecUser.setAddress(data[5]);
		    	elecUser.setIsDuty(data[6]);
		    	elecUser.setBirthday(StringHelper.stringConvertDate(data[7]));
		    	elecUserDao.save(elecUser);
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**  
	* @Name: findUserByChart
	* @Description: 使用柱状图生成用户统计信息
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-16（创建日期）
	* @Parameters:
	* @Return: 
	*/
	@Override
	public List<ElecUserForm> findUserByChart() {
		List<Object[]> list = elecUserDao.findUserByChart();
		List<ElecUserForm> listVO = this.userChartPO2VO(list);
		return listVO;
	}
	/**  
	* @Name: userChartPO2VO
	* @Description: chart的po转vo对象
	* @Author: wei（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-16（创建日期）
	* @Parameters: List<Object[]> list
	* @Return: List<ElecUserForm>
	*/
	private List<ElecUserForm> userChartPO2VO(List<Object[]> list) {
		List<ElecUserForm> res = new ArrayList<ElecUserForm>();
		ElecUserForm elecUserForm = null;
		for(int i=0;list!=null && i<list.size();i++){
			Object[] obs = list.get(i);
			elecUserForm = new ElecUserForm();
			elecUserForm.setJctname(obs[0].toString());
			elecUserForm.setJctcount(obs[1].toString());
			res.add(elecUserForm);
		}
		return res;
	}

	
	

}
