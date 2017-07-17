package com.loupan.boot.repository.centaec.estate;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@Repository
public class LdEstateDao {
	@PersistenceContext(unitName="centaecPersistenceUnit")
	private EntityManager em;
	
	public Map<String,Object> findTop1ByCityIdGreaterThanSortNum(int cityId,int sortNum){
		String sql = "select a.id from ld_estate a "
				+ " left join building b "
				+ " on a.building_id = b.ID "
				+ " where city_id = ?1 and sort_num < ?2 "
				+ " order by a.sort_num desc";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1,cityId);//参数
		query.setParameter(2,sortNum);
		query.setFirstResult(0).setMaxResults(1);//结果集
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//返回形式
		return (Map<String,Object>)query.getSingleResult();
	}
	
	public Map<String,Object> findTop1ByCityIdLessThanSortNum(int cityId,int sortNum){
		String sql = "select a.id from ld_estate a "
				+ " left join building b "
				+ " on a.building_id = b.ID "
				+ " where city_id = ?1 and sort_num > ?2 "
				+ " order by a.sort_num ";
		Query query = em.createNativeQuery(sql);
		query.setParameter(1,cityId);//参数
		query.setParameter(2,sortNum);
		query.setFirstResult(0).setMaxResults(1);//结果集
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//返回形式
		
		return (Map<String,Object>)query.getSingleResult();
	}
	
	public List<Map<String,Object>> findAllCommon(String tableName){
		String sql = "show full columns from "+tableName;
		Query query = em.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//返回形式s
		return (List<Map<String,Object>>)query.getResultList();
	}
	
	public Map<String,Object> findTableCommon(String tableName){
		String sql = "show table status where name = '"+tableName+"'";
		Query query = em.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);//返回形式s
		Map<String,Object> mao = (Map<String,Object>)query.getSingleResult();
		return mao;
	}
}
