package cn.xxx.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.xxx.elec.dao.ElecTextDao;
import cn.xxx.elec.domain.ElecText;

@Repository(ElecTextDao.SERVICE_NAME)
public class ElecTextDaoImpl extends CommonDaoImpl<ElecText> implements ElecTextDao {

}
