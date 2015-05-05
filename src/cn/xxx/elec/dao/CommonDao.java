package cn.xxx.elec.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import cn.xxx.elec.domain.ElecSystemDDL;
import cn.xxx.elec.domain.ElecText;

public interface CommonDao<T> {
	void save(T t);
	void update(T t);
	T findObjectByID(Serializable id);
	void deleteObjectByIDs(Serializable... ids);
	void deleteObjectByCollection(Collection<T> list);
	void deleteObject(T t);
	List<T> findCollectionByNoPage(String hqlWhere,
			Object[] params, LinkedHashMap<String, String> orderBy);
	void saveObjectByCollection(List<T> list);
	boolean isExist(Serializable id);
	void saveOrUpdate(T t);
}
