package cn.xxx.elec.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.xxx.elec.dao.ElecUserRoleDao;
import cn.xxx.elec.domain.ElecUserRole;

@Repository(ElecUserRoleDao.SERVICE_NAME)
public class ElecUserRoleDaoImpl extends CommonDaoImpl<ElecUserRole> implements ElecUserRoleDao {

	@Override
	public List<Object[]> findUserListByRoleID(String role) {
		String sql = "select DISTINCT CASE r.RoleID " +
				"when ? then '1' else '0' end as flag," +
				"u.UserID,u.UserName,u.loginName " +
				"from elec_user u " +
				"left join elec_user_role r " +
				"on u.userID = r.UserID " +
				"and r.roleID= ? " +
				"where u.isDuty='1'";
		Query query = this.getSession().createSQLQuery(sql);
		query.setString(0, role);
		query.setString(1, role);
		return query.list();
	}
	

}
