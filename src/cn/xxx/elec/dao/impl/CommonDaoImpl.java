package cn.xxx.elec.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.xxx.elec.dao.CommonDao;
import cn.xxx.elec.domain.ElecSystemDDL;
import cn.xxx.elec.util.GenericSuperClass;

public class CommonDaoImpl<T> implements CommonDao<T> {

	private SessionFactory sessionFactory = null;
	@SuppressWarnings("rawtypes")
	private Class entity = (Class)GenericSuperClass.getClass(this.getClass());
	private Query query = null;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name="mySessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void save(T t) {
//		sessionFactory = new Configuration().configure().buildSessionFactory();
//		Session s = sessionFactory.getCurrentSession();
//		s.beginTransaction();
//		System.out.println(t);
		this.getSession().save(t);
//		s.getTransaction().commit();
	}

	@Override
	public void update(T t) {
		//this.getSession().beginTransaction();
		this.getSession().update(t);
		//this.getSession().getTransaction().commit();
	}

	@Override
	public T findObjectByID(Serializable id) {
		//this.getSession().beginTransaction();
		T t = (T)this.getSession().get(entity,id);
		//this.getSession().getTransaction().commit();
		return t;
	}
	
	
	/**  
	* @Name: deleteObjectByIDs
	* @Description: ͨ��id����ɾ�����
	* @Author: wei�����ߣ�
	* @Version: V1.00 ���汾�ţ�
	* @Create Date: 2014-12-13 ���������ڣ�
	* @Parameters: Serializable[] ids ����ID������
	* @Return: ��
	*/
	@Override
	public void deleteObjectByIDs(Serializable... ids) {
		this.getSession().beginTransaction();
		for(int i=0;ids!=null && i<ids.length;i++)
		{
			Serializable id = ids[i];
			Object o = (Object)this.getSession().get(entity, id);
			this.getSession().delete(o);
		}		
		this.getSession().getTransaction().commit();
		
	}
	/**  
	* @Name: deleteObjectByCollection
	* @Description: ͨ���ɾ�����
	* @Author: wei�����ߣ�
	* @Version: V1.00 ���汾�ţ�
	* @Create Date: 2014-12-13 
	* @Parameters: Collection<T> list ���϶���
	* @Return: ��
	*/
	@Override
	public void deleteObjectByCollection(Collection<T> list) {
		Iterator<T> iterator = list.iterator();
		while(iterator.hasNext()){
			this.deleteObject(iterator.next());
		}			
	}

	@Override
	public void deleteObject(T t) {
	//	this.getSession().beginTransaction();
		this.getSession().delete(t);
		//this.getSession().getTransaction().commit();
		
	}
	
	
	/**  
	* @Name: findCollectionByNoPage
	* @Description: ����������в�ѯ�����Զ���������ʹ��
	* @Author: wei�����ߣ�
	* @Version: V1.00 ���汾�ţ�
	* @Create Date: 2014-12-13 ���������ڣ�
	* @Parameters: String hqlWhere, 
				   Object[] params, 
				   LinkedHashMap<String, String> orderBy
	* @Return: List<T> ���͵�ֵ
	*/
	@Override
	public List<T> findCollectionByNoPage(String hqlWhere,
			Object[] params, LinkedHashMap<String, String> orderBy) {
		/*	
		 * 
		 * SELECT o.textName,o.textRemark FROM elec_text o  where 1=1     ���õ�DAO��
			AND o.textName LIKE '%����%'                            ���õ�Service��                    
			AND o.textRemark LIKE '%service%'
			ORDER BY o.textDate DESC , o.textName ASC 
	     */
		//������඼���Բ�ѯ
		query = null;
		String hql = "from "+ entity.getSimpleName() +" o where 1=1";
		hql = hql + hqlWhere + this.orderByCondition(orderBy);
		System.out.println("HQL is :" + hql);
		query = this.getSession().createQuery(hql);
		setParams(params);
	/*	List list = query.list();
		for(int i=0;list!=null && i<list.size();i++){
			System.out.println(list.get(i));
		}*/
		return query.list();
	}
	/**  
	* @Name: setParams
	* @Description: ȡ��hql�Ĳ���
	* @Author: wei�����ߣ�
	* @Version: V1.00 ���汾�ţ�
	* @Create Date: 2014-12-13 ���������ڣ�
	* @Parameters: 
	* @Return: ��
	*/
	private void setParams(Object[] params){	
		/*if(params !=null){
			System.out.println("params.length = " + params.length);		
		}else{
			System.out.println("params is null");
		}*/
		for(int i=0;params!=null && i<params.length;i++){
			query.setParameter(i, params[i]);
		}
	}
	/**  
	* @Name: orderByCondition
	* @Description: ��Map���͵�orderBy���ת��ΪString
	* @Author: wei�����ߣ�
	* @Version: V1.00 ���汾�ţ�
	* @Create Date: 2014-12-13 ���������ڣ�
	* @Parameters:
	* @Return: ��
	*/
	private String orderByCondition(LinkedHashMap<String, String> orderBy){
		StringBuffer buffer = new  StringBuffer("");
		if(orderBy != null)
		{
			buffer.append(" order by ");
			for(Map.Entry<String, String> map : orderBy.entrySet())
			{
				buffer.append(map.getKey() + " " + map.getValue() + ",");
			}
			buffer.deleteCharAt(buffer.length()-1);
		}
		return buffer.toString();
	}


	
	/**  
	* @Name: saveObjectByCollection
	* @Description: ���漯�϶���
	* @Author: wei�����ߣ�
	* @Version: V1.00 ���汾�ţ�
	* @Create Date: 2014-12-31 ���������ڣ�
	* @Parameters: List<> list
	* @Return: ��
	*/
	@Override
	public void saveObjectByCollection(List<T> list) {
		//this.getSession().beginTransaction();
		for(int i=0;list!=null && i<list.size();i++){
			this.save(list.get(i));
		}
		
	}

	@Override
	public boolean isExist(Serializable id) {
		if(this.findObjectByID(id)==null){
			return false;
		}
		else{
			return true;
		}
	}

	@Override
	public void saveOrUpdate(T t) {
		this.getSession().saveOrUpdate(t);
		
	}
}
