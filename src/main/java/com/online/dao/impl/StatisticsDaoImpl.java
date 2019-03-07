package com.online.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.online.dao.StatisticsDao;
import com.online.entity.online.Condition;
import com.online.entity.online.Statistics;
import com.online.util.Constants;

/**
 * 填报统计
 */
@Repository("statisticsDaoImpl")
public class StatisticsDaoImpl extends BaseDaoImpl<Statistics, Integer> implements StatisticsDao {
	
	@SuppressWarnings("unchecked")
    public List<Map<String, Object>> findStatistics(String type,Condition condition) {

		List<Map<String, Object>> resultList = new ArrayList<>();
			try {
				StringBuilder sql = new StringBuilder();
				// 如果是地区
				if (Constants.STATISTICS_AREA.equals(type)) {
					sql.append("SELECT (SELECT b.name FROM sys_area b WHERE b.code = a.county) county, COUNT(a.id) count FROM ol_enterprise a, ol_project_enterprise c");
					sql.append(" WHERE c.`ol_enterprise` = a.`id`");
					sql.append(" AND c.`projects`  = ?1 ");
					if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity()) && StringUtils.isNotEmpty(condition.getCounty())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
						sql.append(" AND a.`county` = ?4 ");
						
					}else if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
					}else if(StringUtils.isNotEmpty(condition.getProvinces())){
						sql.append(" AND a.`provinces` = ?2");
					}else if(StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`city` = ?3");
					}
					if(StringUtils.isNotEmpty(condition.getStartDate())|| StringUtils.isNotEmpty(condition.getEndDate())){
						sql.append(" AND (SUBSTRING(a.create_date ,1 ,10) BETWEEN ?5 AND ?6 ) ");
					}
					
					sql.append(" GROUP BY a.county ORDER BY count DESC");
							
				} else if (Constants.STATISTICS_DATE.equals(type)) {
					// 日期
					sql.append("SELECT SUBSTRING(a.create_date ,1 ,10) date, COUNT(a.id) count FROM ol_enterprise a, ol_project_enterprise b where b.`ol_enterprise` = a.`id`");
					sql.append(" AND b.`projects`  = ?1 ");
					if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity()) && StringUtils.isNotEmpty(condition.getCounty())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
						sql.append(" AND a.`county` = ?4 ");
						
					}else if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
					}else if(StringUtils.isNotEmpty(condition.getProvinces())){
						sql.append(" AND a.`provinces` = ?2");
					}else if(StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`city` = ?3");
					}
					if(StringUtils.isNotEmpty(condition.getStartDate())|| StringUtils.isNotEmpty(condition.getEndDate())){
						sql.append(" AND (SUBSTRING(a.create_date ,1 ,10) BETWEEN ?5 AND ?6 ) ");
					}
					sql.append(" GROUP BY  SUBSTRING(a.create_date ,1 ,10) ");
					
				} else if (Constants.STATISTICS_USER.equals(type)) {
					// 用户
					sql.append("SELECT  b.username user,  COUNT(a.id) count  FROM   ol_enterprise a, sys_user b , ol_project_enterprise c WHERE a.`account` = b.`id` AND c.`ol_enterprise` = a.`id`");
					sql.append(" AND c.`projects`  = ?1 ");
					if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity()) && StringUtils.isNotEmpty(condition.getCounty())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
						sql.append(" AND a.`county` = ?4 ");
						
					}else if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
					}else if(StringUtils.isNotEmpty(condition.getProvinces())){
						sql.append(" AND a.`provinces` = ?2");
					}else if(StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`city` = ?3");
					}
					if(StringUtils.isNotEmpty(condition.getStartDate())|| StringUtils.isNotEmpty(condition.getEndDate())){
						sql.append(" AND (SUBSTRING(a.create_date ,1 ,10) BETWEEN ?5 AND ?6 ) ");
					}
					sql.append(" GROUP BY a.account ORDER BY count DESC ");
				} else if (Constants.STATISTICS_INQUIRER.equals(type)) {
					// 填报人
					sql.append("SELECT  a.inquirer inquirer,  COUNT(a.id) count FROM  ol_enterprise a,  ol_project_enterprise b WHERE b.`ol_enterprise` = a.`id`");
					sql.append(" AND b.`projects`  = ?1 ");
					if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity()) && StringUtils.isNotEmpty(condition.getCounty())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
						sql.append(" AND a.`county` = ?4 ");
						
					}else if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
					}else if(StringUtils.isNotEmpty(condition.getProvinces())){
						sql.append(" AND a.`provinces` = ?2");
					}else if(StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`city` = ?3");
					}
					if(StringUtils.isNotEmpty(condition.getStartDate())|| StringUtils.isNotEmpty(condition.getEndDate())){
						sql.append(" AND (SUBSTRING(a.create_date ,1 ,10) BETWEEN ?5 AND ?6 ) ");
					}
					sql.append(" GROUP BY a.inquirer ORDER BY count DESC  ");
				}else if (Constants.STATISTICS_RNTERPERSETYPE.equals(type)) {
					//企业类型
					sql.append("SELECT  a.enterprise_type enterprise_type,  COUNT(a.id) count FROM  ol_enterprise a,  ol_project_enterprise b WHERE b.`ol_enterprise` = a.`id`");
					sql.append(" AND b.`projects`  = ?1 ");
					if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity()) && StringUtils.isNotEmpty(condition.getCounty())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
						sql.append(" AND a.`county` = ?4 ");
						
					}else if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
					}else if(StringUtils.isNotEmpty(condition.getProvinces())){
						sql.append(" AND a.`provinces` = ?2");
					}else if(StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`city` = ?3");
					}
					if(StringUtils.isNotEmpty(condition.getStartDate())|| StringUtils.isNotEmpty(condition.getEndDate())){
						sql.append(" AND (SUBSTRING(a.create_date ,1 ,10) BETWEEN ?5 AND ?6 ) ");
					}
					if(StringUtils.isNotBlank(condition.getIndustry_category_code_main())){
						sql.append("AND a.industry_category_code_main = ?7");
					}
					if(StringUtils.isNotBlank(condition.getIndustry_category_code_middle())){
						sql.append("AND a.industry_category_code_middle = ?8");
					}
					
					sql.append(" GROUP BY a.enterprise_type ORDER BY count DESC  ");
				}else if (Constants.STATISTICS_NORMALMAIN.equals(type)) {
					//详表大类
					sql.append("SELECT  a.industry_category_code_main normalMain,  COUNT(a.id) count FROM  ol_enterprise a,  ol_project_enterprise b WHERE b.`ol_enterprise` = a.`id`");
					sql.append(" AND b.`projects`  = ?1 ");
					if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity()) && StringUtils.isNotEmpty(condition.getCounty())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
						sql.append(" AND a.`county` = ?4 ");
						
					}else if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
					}else if(StringUtils.isNotEmpty(condition.getProvinces())){
						sql.append(" AND a.`provinces` = ?2");
					}else if(StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`city` = ?3");
					}
					if(StringUtils.isNotEmpty(condition.getStartDate())|| StringUtils.isNotEmpty(condition.getEndDate())){
						sql.append(" AND (SUBSTRING(a.create_date ,1 ,10) BETWEEN ?5 AND ?6 ) ");
					}
					sql.append("AND a.enterprise_type = 'NORMAL'");
					sql.append("AND a.industry_category_code_main != ''");
					sql.append(" GROUP BY a.industry_category_code_main ORDER BY count DESC  ");
				}else if (Constants.STATISTICS_NORMALMIDDLE.equals(type)) {
					//详表中类
					sql.append("SELECT  a.industry_category_code_middle normalMiddle,  COUNT(a.id) count FROM  ol_enterprise a,  ol_project_enterprise b WHERE b.`ol_enterprise` = a.`id`");
					sql.append(" AND b.`projects`  = ?1 ");
					if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity()) && StringUtils.isNotEmpty(condition.getCounty())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
						sql.append(" AND a.`county` = ?4 ");
						
					}else if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
					}else if(StringUtils.isNotEmpty(condition.getProvinces())){
						sql.append(" AND a.`provinces` = ?2");
					}else if(StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`city` = ?3");
					}
					if(StringUtils.isNotEmpty(condition.getStartDate())|| StringUtils.isNotEmpty(condition.getEndDate())){
						sql.append(" AND (SUBSTRING(a.create_date ,1 ,10) BETWEEN ?5 AND ?6 ) ");
					}
					sql.append("AND a.enterprise_type = 'NORMAL'");
					sql.append("AND a.industry_category_code_middle != ''");
					sql.append(" GROUP BY a.industry_category_code_middle ORDER BY count DESC  ");
				}else if (Constants.STATISTICS_SIMPLEMAIN.equals(type)) {
					//简表大类
					sql.append("SELECT  a.industry_category_code_main simpleMain,  COUNT(a.id) count FROM  ol_enterprise a,  ol_project_enterprise b WHERE b.`ol_enterprise` = a.`id`");
					sql.append(" AND b.`projects`  = ?1 ");
					if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity()) && StringUtils.isNotEmpty(condition.getCounty())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
						sql.append(" AND a.`county` = ?4 ");
						
					}else if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
					}else if(StringUtils.isNotEmpty(condition.getProvinces())){
						sql.append(" AND a.`provinces` = ?2");
					}else if(StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`city` = ?3");
					}
					if(StringUtils.isNotEmpty(condition.getStartDate())|| StringUtils.isNotEmpty(condition.getEndDate())){
						sql.append(" AND (SUBSTRING(a.create_date ,1 ,10) BETWEEN ?5 AND ?6 ) ");
					}
					sql.append("AND a.enterprise_type = 'SIMPLE'");
					sql.append("AND a.industry_category_code_main != ''");
					sql.append(" GROUP BY a.industry_category_code_main ORDER BY count DESC  ");
				}else if (Constants.STATISTICS_SIMPLEMIDDLE.equals(type)) {
					//简表中类
					sql.append("SELECT  a.industry_category_code_middle simpleMiddle,  COUNT(a.id) count FROM  ol_enterprise a,  ol_project_enterprise b WHERE b.`ol_enterprise` = a.`id`");
					sql.append(" AND b.`projects`  = ?1 ");
					if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity()) && StringUtils.isNotEmpty(condition.getCounty())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
						sql.append(" AND a.`county` = ?4 ");
						
					}else if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`provinces` = ?2 ");
						sql.append(" AND a.`city` = ?3 ");
					}else if(StringUtils.isNotEmpty(condition.getProvinces())){
						sql.append(" AND a.`provinces` = ?2");
					}else if(StringUtils.isNotEmpty(condition.getCity())){
						sql.append(" AND a.`city` = ?3");
					}
					if(StringUtils.isNotEmpty(condition.getStartDate())|| StringUtils.isNotEmpty(condition.getEndDate())){
						sql.append(" AND (SUBSTRING(a.create_date ,1 ,10) BETWEEN ?5 AND ?6 ) ");
					}
					sql.append("AND a.enterprise_type = 'SIMPLE'");
					sql.append("AND a.industry_category_code_middle != ''");
					sql.append(" GROUP BY a.industry_category_code_middle ORDER BY count DESC  ");
				}

				Query createNativeQuery = entityManager.createNativeQuery(sql.toString());
				// Query createNativeQuery = entityManager.createQuery(sql);
				createNativeQuery.setParameter(1, condition.getProject());
				
				if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity()) && StringUtils.isNotEmpty(condition.getCounty())){
					createNativeQuery.setParameter(2, condition.getProvinces());
					createNativeQuery.setParameter(3, condition.getCity());
					createNativeQuery.setParameter(4, condition.getCounty());
					
				}else if(StringUtils.isNotEmpty(condition.getProvinces()) && StringUtils.isNotEmpty(condition.getCity())){
					createNativeQuery.setParameter(2, condition.getProvinces());
					createNativeQuery.setParameter(3, condition.getCity());
				}else if(StringUtils.isNotEmpty(condition.getProvinces())){
					createNativeQuery.setParameter(2, condition.getProvinces());
				}else if(StringUtils.isNotEmpty(condition.getCity())){
					createNativeQuery.setParameter(3, condition.getCity());
				}
				
				if(StringUtils.isNotEmpty(condition.getStartDate()) || StringUtils.isNotEmpty(condition.getEndDate())){
					createNativeQuery.setParameter(5, condition.getStartDate());
					createNativeQuery.setParameter(6, condition.getEndDate());
				}
				
				if (Constants.STATISTICS_RNTERPERSETYPE.equals(type)) {
				
					if(StringUtils.isNotBlank(condition.getIndustry_category_code_main())){
						createNativeQuery.setParameter(7, condition.getIndustry_category_code_main());
					}
					if(StringUtils.isNotBlank(condition.getIndustry_category_code_middle())){
						createNativeQuery.setParameter(8, condition.getIndustry_category_code_middle());
					}
				}
				
				createNativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

				resultList = createNativeQuery.getResultList();

			} catch (Exception e) {
				return resultList;
			}


		return resultList;

	}


}