package cn.xxx.elec.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.xxx.elec.dao.ElecUserDao;
import cn.xxx.elec.domain.ElecUser;

@Repository(ElecUserDao.SERVICE_NAME)
public class ElecUserDaoImpl extends CommonDaoImpl<ElecUser> implements ElecUserDao {

	@Override
	public List<Object> findPopedomByloginName(String loginName) {
		String sql = "select a.popedomcode from elec_role_popedom a"+
					 " left join elec_user_role b"+ 
					 " on b.RoleID = a.RoleID"+
					 " inner join elec_user c"+
					 " on b.UserID = c.UserID"+
					 " AND c.loginName = ?"+
					 " where c.isDuty='1'";
		Query query = this.getSession().createSQLQuery(sql);
		query.setString(0, loginName);
		List<Object> list = (List<Object>)query.list();
		return list;
	}

	@Override
	public List<Object[]> findRoleNameByLoginName(String loginName) {
		String sql = "select b.ddlCode,b.ddlName from elec_user_role a "+ 
				"left join elec_systemddl b "+
				"on a.RoleID = b.ddlCode "+
				"and b.keyword = '角色类型' "+
				"inner join elec_user c "+ 
				"on a.UserID = c.UserID "+
				"AND c.loginName = ? "+
				"where c.isDuty='1'";
		Query query = this.getSession().createSQLQuery(sql);
		query.setParameter(0, loginName);
		
		List<Object[]> list = (List<Object[]>)query.list();
		
		return list;
	}

}
