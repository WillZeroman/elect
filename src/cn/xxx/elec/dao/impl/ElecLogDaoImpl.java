package cn.xxx.elec.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import cn.xxx.elec.dao.ElecLogDao;
import cn.xxx.elec.domain.ElecLog;

@Repository(ElecLogDao.SERVICE_NAME)
public class ElecLogDaoImpl extends CommonDaoImpl<ElecLog> implements ElecLogDao {

}
