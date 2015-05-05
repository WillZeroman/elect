package cn.xxx.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.xxx.elec.dao.ElecCommonMsgDao;
import cn.xxx.elec.domain.ElecCommonMsg;

@Repository(ElecCommonMsgDao.SERVICE_NAME)
public class ElecCommonMsgDaoImpl extends CommonDaoImpl<ElecCommonMsg> implements ElecCommonMsgDao {

}
