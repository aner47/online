package com.online.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.online.CustomFilter;
import com.online.Filter;
import com.online.Page;
import com.online.Pageable;
import com.online.Filter.Operator;
import com.online.dao.EnterpriseDao;
import com.online.entity.online.BoilerInformation;
import com.online.entity.online.Enterprise;
import com.online.entity.online.EquipmentLeaked;
import com.online.entity.online.ExhaustionHole;
import com.online.entity.online.Fuel;
import com.online.entity.online.GovernanceMeasures;
import com.online.entity.online.Kiln;
import com.online.entity.online.LeakedCheck;
import com.online.entity.online.LoadingInformation;
import com.online.entity.online.MonthlyInformation;
import com.online.entity.online.OilTank;
import com.online.entity.online.OpenYard;
import com.online.entity.online.PhotoFile;
import com.online.entity.online.PowerPlant;
import com.online.entity.online.RawMaterials;
import com.online.entity.online.Section;
import com.online.entity.online.SewageDispose;
import com.online.util.Constants;

/**
 * 
 * @author 左志平
 * 
 *         dao-企业实现
 *
 */
@Repository("enterpriseDaoImpl")
public class EnterpriseDaoImpl extends BaseDaoImpl<Enterprise, Integer> implements EnterpriseDao {

	@Override
	public Enterprise findByEnterpriseName(String enterpriseName) {
		String jpql = "select enterprise from Enterprise enterprise where name=:enterpriseName";
		try {
			return entityManager.createQuery(jpql, Enterprise.class).setParameter("enterpriseName", enterpriseName)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Enterprise findByAccount(Integer userId) {
		String sql = "SELECT enterprise FROM Enterprise enterprise WHERE account=:account";
		try {
			return entityManager.createQuery(sql, Enterprise.class).setParameter("account", userId).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Enterprise> findALLByAccount(List<Integer> userIds) {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("account", Operator.in, userIds));
		return findList(null, null, filters, null);
	}

	public List<Map<String, Object>> findALLEnterpriseByAccount(List<Integer> userIds, String polluteSource) {

		List<Map<String, Object>> resultList = new ArrayList<>();
		if (userIds != null && userIds.size() > 0) {
			try {
				String sql = "";
				// 如果是锅炉
				if (Constants.EXPORT_POLLUTESOURCE_BOILER.equals(polluteSource)) {
					sql = "SELECT t1.name,t1.code,t1.longitude,t1.latitude,t1.provinces,t1.city,t1.county,t1.street,t1.house_number"
							+ ",t1.industry_category_code_main,t1.industry_category_code_middle,t1.industry_category_name_main"
							+ ",t1.industry_category_name_middle "
							+ ",t7.gross_product,t7.energy_used,t7.work_day,t7.day_hours,t7.nomal_production_hour"
							+ ",t8.name provineces_name,t9.name city_name,t10.name county_name,t11.name street_name"
							+ ",t2.boiler_type,t2.steam_ton,t2.operation,t2.fuel_type,t2.fuel_consumption"
							+ ",t2.fuel_unit,t2.fuel_sulfur_content,t2.fuel_ash,t2.fuel_volatiles"
							+ ",t3.so2,t3.nox,t3.tsp,t3.voc,t3.height,t3.diameter,t3.temperature,t3.flow"
							+ ",t3.emissions"
							+ ",t4.name t4name,t4.work_hours t4work_hours,t4.collect_efficiency t4collect_efficiency"
							+ ",t4.so2 t4so2,t4.nox t4nox,t4.co t4co,t4.pm10 t4pm10,t4.pm25 t4pm25,t4.bc t4bc"
							+ ",t4.oc t4oc,t4.voc t4voc,t4.nh3 t4nh3"
							+ ",t5.name t5name,t5.work_hours t5work_hours,t5.collect_efficiency t5collect_efficiency"
							+ ",t5.so2 t5so2,t5.nox t5nox,t5.co t5co,t5.pm10 t5pm10,t5.pm25 t5pm25,t5.bc t5bc"
							+ ",t5.oc t5oc,t5.voc t5voc,t5.nh3 t5nh3"
							+ ",t6.name t6name,t6.work_hours t6work_hours,t6.collect_efficiency t6collect_efficiency"
							+ ",t6.so2 t6so2,t6.nox t6nox,t6.co t6co,t6.pm10 t6pm10,t6.pm25 t6pm25,t6.bc t6bc"
							+ ",t6.oc t6oc,t6.voc t6voc,t6.nh3 t6nh3"
//							+ ",t12.name t12name,t12.month_type t12month_type,t12.unit t12unit,t12.january t12january"
//							+ ",t12.february t12february,t12.march t12march,t12.april t12april,t12.may t12may"
//							+ ",t12.june t12june,t12.july t12july,t12.august t12august,t12.september t12september"
//							+ ",t12.october t12october,t12.november t12november,t12.december t12december" 
							+ " FROM ol_enterprise t1 "
							+ "left join ol_base_info t7 on t1.`id` = t7.`enterprise` "
							+ "left join sys_area t8 on t1.provinces = t8.code "
							+ "left join sys_area t9 on t1.city = t9.code "
							+ "left join sys_area t10 on t1.county = t10.code "
							+ "left join sys_area t11 on t1.street = t11.code "
							+ "left join ol_boiler_information t2 ON t1.id=t2.`enterprise`  "
							+ "LEFT JOIN ol_exhaustion_hole t3 ON t3.`id` = t2.`exhaustion_hole` "
							+ "LEFT JOIN ol_governance_measures t4 ON t4.`id` = t2.`governance_measures1`  "
							+ "LEFT JOIN ol_governance_measures t5 ON t5.`id` = t2.`governance_measures2` "
							+ "LEFT JOIN ol_governance_measures t6 ON t6.`id` = t2.`governance_measures3` "
//							+ "LEFT JOIN ol_monthly_information t12 ON t1.id = t12.enterprise AND t12.month_type = 'fuel' "
							+ "WHERE t1.account in ?1 ";
				} else if (Constants.EXPORT_POLLUTESOURCE_OPENYARD.equals(polluteSource)) {
					// 露天
					sql = "SELECT t1.name,t1.code,t1.longitude,t1.latitude,t1.provinces,t1.city,t1.county,t1.street,t1.house_number"
							+ ",t1.industry_category_code_main,t1.industry_category_code_middle,t1.industry_category_name_main"
							+ ",t1.industry_category_name_middle "
							+ ",t7.gross_product,t7.energy_used,t7.work_day,t7.day_hours,t7.nomal_production_hour"
							+ ",t8.name provineces_name,t9.name city_name,t10.name county_name,t11.name street_name"
							+ ",t2.material_type,t2.cargo_trips,t2.carry_amount,t2.load_amount,t2.area,t2.height,t2.moisture_content"
							+ ",t2.measure1,t2.measure2,t2.measure3,t2.car_cleaning,t2.broken,t2.closed"
							+ " FROM ol_enterprise t1  " + "left join ol_base_info t7 on t1.`id` = t7.`enterprise` "
							+ "left join sys_area t8 on t1.provinces = t8.code "
							+ "left join sys_area t9 on t1.city = t9.code "
							+ "left join sys_area t10 on t1.county = t10.code "
							+ "left join sys_area t11 on t1.street = t11.code "
							+ "left join ol_open_yard t2 ON t1.id=t2.`enterprise`  " + "WHERE t1.account in ?1 ";
				} else if (Constants.EXPORT_POLLUTESOURCE_OILTANK.equals(polluteSource)) {
					// 石油
					sql = "SELECT t1.name,t1.code,t1.longitude,t1.latitude,t1.provinces,t1.city,t1.county,t1.street,t1.house_number"
							+ ",t1.industry_category_code_main,t1.industry_category_code_middle,t1.industry_category_name_main"
							+ ",t1.industry_category_name_middle "
							+ ",t7.gross_product,t7.energy_used,t7.work_day,t7.day_hours,t7.nomal_production_hour"
							+ ",t8.name provineces_name,t9.name city_name,t10.name county_name,t11.name street_name"
							+ ",t2.tank_material_type,t2.tank_type,t2.height,t2.avg_diameter,t2.capacity,t2.work_days"
							+ ",t2.storage_capacity,t2.storage_cycle,t2.fill_times" + " FROM ol_enterprise t1  "
							+ "LEFT JOIN ol_base_info t7 ON t1.`id` = t7.`enterprise`  "
							+ "LEFT JOIN sys_area t8 ON t1.provinces = t8.code  "
							+ "LEFT JOIN sys_area t9 ON t1.city = t9.code  "
							+ "LEFT JOIN sys_area t10 ON t1.county = t10.code  "
							+ "LEFT JOIN sys_area t11 ON t1.street = t11.code  "
							+ "LEFT JOIN ol_oil_tank t2 ON t1.id=t2.`enterprise`   " + "WHERE t1.account in ?1 ";
				} else if (Constants.EXPORT_POLLUTESOURCE_OTHER.equals(polluteSource)) {
					// 工段和炉窑
					sql = "SELECT t1.name,t1.code,t1.longitude,t1.latitude,t1.provinces,t1.city,t1.county,t1.street,t1.house_number"
							+ ",t1.industry_category_code_main,t1.industry_category_code_middle,t1.industry_category_name_main"
							+ ",t1.industry_category_name_middle "
							+ ",t7.gross_product,t7.energy_used,t7.work_day,t7.day_hours,t7.nomal_production_hour"
							+ ",t8.name provineces_name,t9.name city_name,t10.name county_name,t11.name street_name"
							+ ",t2.name t2name,t2.put_on_date ,t2.kiln_type,t2.fuel_type,t2.fuel_consumption,t2.fuel_unit"
							+ ",t2.fuel_sulfur_content"
							+ ",t3.so2 t3so2,t3.nox t3nox,t3.tsp t3tsp,t3.voc t3voc,t3.height t3height"
							+ ",t3.diameter t3diameter,t3.temperature t3temperature,t3.flow t3flow,t3.emissions t3emissions"
							+ ",t4.name t4name,t4.work_hours t4work_hours,t4.collect_efficiency t4collect_efficiency"
							+ ",t4.so2 t4so2,t4.nox t4nox,t4.co t4co,t4.pm10 t4pm10,t4.pm25 t4pm25,t4.bc t4bc"
							+ ",t4.oc t4oc,t4.voc t4voc,t4.nh3 t4nh3"
							+ ",t5.name t5name,t5.work_hours t5work_hours,t5.collect_efficiency t5collect_efficiency"
							+ ",t5.so2 t5so2,t5.nox t5nox,t5.co t5co,t5.pm10 t5pm10,t5.pm25 t5pm25,t5.bc t5bc"
							+ ",t5.oc t5oc,t5.voc t5voc,t5.nh3 t5nh3"
							+ ",t6.name t6name,t6.work_hours t6work_hours,t6.collect_efficiency t6collect_efficiency"
							+ ",t6.so2 t6so2,t6.nox t6nox,t6.co t6co,t6.pm10 t6pm10,t6.pm25 t6pm25,t6.bc t6bc"
							+ ",t6.oc t6oc,t6.voc t6voc,t6.nh3 t6nh3" + ",t12.name sectio_name"
							+ ",t13.so2 t13_so2,t13.nox t13_nox,t13.tsp t13_tsp,t13.voc t13_voc"
							+ ",t13.height t13_height,t13.diameter t13_diameter,t13.temperature t13_temperature"
							+ ",t13.flow t13_flow"
							+ ",t14.so2 t14so2,t14.nox t14nox,t14.co t14co,t14.pm10 t14pm10,t14.pm25 t14pm25,t14.bc t14bc"
							+ ",t14.oc t14oc,t14.voc t14voc,t14.nh3 t14nh3"
							+ ",t15.so2 t15so2,t15.nox t15nox,t15.co t15co,t15.pm10 t15pm10,t15.pm25 t15pm25,t15.bc t15bc"
							+ ",t15.oc t15oc,t15.voc t15voc,t15.nh3 t15nh3"
							+ ",t16.so2 t16so2,t16.nox t16nox,t16.co t16co,t16.pm10 t16pm10,t16.pm25 t16pm25,t16.bc t16bc"
							+ ",t16.oc t16oc,t16.voc t16voc,t16.nh3 t16nh3"
							+ ",t17.name t17_name,t17.solvent_type t17_solvent_type,t17.voc_rate t17_voc_rate"
							+ ",t17.voc_volatility t17_voc_volatility,t17.consumption t17_consumption,t17.unit t17_unit"
							+ ",t18.name t18_name,t18.solvent_type t18_solvent_type,t18.voc_rate t18_voc_rate"
							+ ",t18.voc_volatility t18_voc_volatility,t18.consumption t18_consumption,t18.unit t18_unit"
							+ ",t19.name t19_name,t19.solvent_type t19_solvent_type,t19.voc_rate t19_voc_rate"
							+ ",t19.voc_volatility t19_voc_volatility,t19.consumption t19_consumption,t19.unit t19_unit"
							+ ",t20.name t20_name,t20.solvent_type t20_solvent_type,t20.voc_rate t20_voc_rate"
							+ ",t20.voc_volatility t20_voc_volatility,t20.consumption t20_consumption,t20.unit t20_unit"
							+ ",t21.name t21_name,t21.solvent_type t21_solvent_type,t21.voc_rate t21_voc_rate"
							+ ",t21.voc_volatility t21_voc_volatility,t21.consumption t21_consumption,t21.unit t21_unit"
							+ ",t22.name t22_name,t22.solvent_type t22_solvent_type,t22.voc_rate t22_voc_rate"
							+ ",t22.voc_volatility t22_voc_volatility,t22.consumption t22_consumption,t22.unit t22_unit"
							+ ",t23.name t23_name,t23.solvent_type t23_solvent_type,t23.voc_rate t23_voc_rate"
							+ ",t23.voc_volatility t23_voc_volatility,t23.consumption t23_consumption,t23.unit t23_unit"
							+ ",t24.name t24_name,t24.solvent_type t24_solvent_type,t24.voc_rate t24_voc_rate"
							+ ",t24.voc_volatility t24_voc_volatility,t24.consumption t24_consumption,t24.unit t24_unit"
							+ ",t25.name t25_name,t25.solvent_type t25_solvent_type,t25.voc_rate t25_voc_rate"
							+ ",t25.voc_volatility t25_voc_volatility,t25.consumption t25_consumption,t25.unit t25_unit"
							+ ",t26.name t26_name,t26.solvent_type t26_solvent_type,t26.voc_rate t26_voc_rate"
							+ ",t26.voc_volatility t26_voc_volatility,t26.consumption t26_consumption,t26.unit t26_unit"
							+ ",t27.name t27name,t27.yield t27yield,t27.unit t27unit"
							+ ",t28.name t28name,t28.yield t28yield,t28.unit t28unit"
//							+ ",t29.name t29name,t29.month_type t29month_type,t29.unit t29unit,t29.january t29january"
//							+ ",t29.february t29february,t29.march t29march,t29.april t29april,t29.may t29may"
//							+ ",t29.june t29june,t29.july t29july,t29.august t29august,t29.september t29september"
//							+ ",t29.october t29october,t29.november t29november,t29.december t29december"
							+ " FROM ol_enterprise t1  "
							+ "LEFT JOIN ol_base_info t7 ON t1.`id` = t7.`enterprise`  "
							+ "LEFT JOIN sys_area t8 ON t1.provinces = t8.code  "
							+ "LEFT JOIN sys_area t9 ON t1.city = t9.code  "
							+ "LEFT JOIN sys_area t10 ON t1.county = t10.code  "
							+ "LEFT JOIN sys_area t11 ON t1.street = t11.code  "
							+ "LEFT JOIN ol_kilnn t2 ON t1.id=t2.`enterprise`  "
							+ "LEFT JOIN ol_exhaustion_hole t3 ON t3.`id` = t2.`exhaustion_hole`  "
							+ "LEFT JOIN ol_governance_measures t4 ON t4.`id` = t2.`governance_measures1`   "
							+ "LEFT JOIN ol_governance_measures t5 ON t5.`id` = t2.`governance_measures2`  "
							+ "LEFT JOIN ol_governance_measures t6 ON t6.`id` = t2.`governance_measures3`  "
							+ "left join ol_raw_materials t17 on t17.id = t2.raw_materials1  "
							+ "left join ol_raw_materials t18 on t18.id = t2.raw_materials2  "
							+ "left join ol_raw_materials t19 on t19.id = t2.raw_materials3  "
							+ "left join ol_raw_materials t20 on t20.id = t2.raw_materials4  "
							+ "left join ol_raw_materials t21 on t21.id = t2.raw_materials5  "
							+ "left join ol_product t27 on t27.id = t2.product  "
							+ "LEFT JOIN ol_section t12 ON t1.id = t12.enterprise  "
							+ "LEFT JOIN ol_exhaustion_hole t13 ON t13.`id` = t12.`exhaustion_hole`  "
							+ "LEFT JOIN ol_governance_measures t14 ON t14.`id` = t12.`governance_measures1`   "
							+ "LEFT JOIN ol_governance_measures t15 ON t15.`id` = t12.`governance_measures2`  "
							+ "LEFT JOIN ol_governance_measures t16 ON t16.`id` = t12.`governance_measures3`  "
							+ "left join ol_raw_materials t22 on t22.id = t12.raw_materials1  "
							+ "left join ol_raw_materials t23 on t23.id = t12.raw_materials2  "
							+ "left join ol_raw_materials t24 on t24.id = t12.raw_materials3  "
							+ "left join ol_raw_materials t25 on t25.id = t12.raw_materials4  "
							+ "left join ol_raw_materials t26 on t26.id = t12.raw_materials5  "
							+ "LEFT JOIN ol_product t28 ON t28.id = t12.product  "
//							+ "left join ol_monthly_information t29 on t1.id = t29.enterprise " 
							+ "WHERE t1.account in ?1 ";
				}

				Query createNativeQuery = entityManager.createNativeQuery(sql);
				// Query createNativeQuery = entityManager.createQuery(sql);
				createNativeQuery.setParameter(1, userIds);
				createNativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

				resultList = createNativeQuery.getResultList();

			} catch (Exception e) {
				return resultList;
			}

		}

		return resultList;

	}

	@Transactional
	@Override
	public void deleteByEnterpriseId(Integer enterpriseId) throws Exception {
		// 郑有权

		// 锅炉
		String sql2 = "delete t2 FROM ol_boiler_information t2 WHERE enterprise =?1";
		int t2 = entityManager.createNativeQuery(sql2, BoilerInformation.class).setParameter(1, enterpriseId)
				.executeUpdate();
		// 工段信息
		String sql6 = "delete t6 FROM ol_section t6 WHERE enterprise =?1";
		int t6 = entityManager.createNativeQuery(sql6, Section.class).setParameter(1, enterpriseId).executeUpdate();
		// 炉窑信息
		String sql5 = "delete t5 FROM ol_kilnn t5 WHERE enterprise =?1";
		int t5 = entityManager.createNativeQuery(sql5, Kiln.class).setParameter(1, enterpriseId).executeUpdate();
		//发电机组
		String sql13 = "delete t13 FROM ol_power_plant t13 WHERE enterprise =?1";
		int t13 = entityManager.createNativeQuery(sql13, PowerPlant.class).setParameter(1, enterpriseId)
				.executeUpdate();
		// 排放口统计
		String sql3 = "delete t3 FROM ol_exhaustion_hole t3 WHERE enterprise =?1";
		int t3 = entityManager.createNativeQuery(sql3, ExhaustionHole.class).setParameter(1, enterpriseId)
				.executeUpdate();
		// 污染治理措施
		String sql4 = "delete t4 FROM ol_governance_measures t4 WHERE enterprise =?1";
		int t4 = entityManager.createNativeQuery(sql4, GovernanceMeasures.class).setParameter(1, enterpriseId)
				.executeUpdate();
		// 露天堆场信息
		String sql7 = "delete t7 FROM ol_open_yard t7 WHERE enterprise =?1";
		int t7 = entityManager.createNativeQuery(sql7, OpenYard.class).setParameter(1, enterpriseId).executeUpdate();
		// 石油产品储罐信息
		String sql8 = "delete t8 FROM ol_oil_tank t8 WHERE enterprise =?1";
		int t8 = entityManager.createNativeQuery(sql8, OilTank.class).setParameter(1, enterpriseId).executeUpdate();
		// 分月数据信息
		String sql9 = "delete t9 FROM ol_monthly_information t9 WHERE enterprise =?1";
		int t9 = entityManager.createNativeQuery(sql9, MonthlyInformation.class).setParameter(1, enterpriseId)
				.executeUpdate();
		// 传照片
		String sql10 = "delete t10 FROM ol_photo_file t10 WHERE enterprise =?1";
		int t10 = entityManager.createNativeQuery(sql10, PhotoFile.class).setParameter(1, enterpriseId).executeUpdate();
		// 原辅料
		String sql12 = "delete t12 FROM ol_raw_materials t12 WHERE enterprise =?1";
		int t12 = entityManager.createNativeQuery(sql12, RawMaterials.class).setParameter(1, enterpriseId)
				.executeUpdate();
		// 有机液体装载
		String sql14 = "delete t14 FROM ol_loading_information t14 WHERE enterprise =?1";
		int t14 = entityManager.createNativeQuery(sql14, LoadingInformation.class).setParameter(1, enterpriseId)
				.executeUpdate();
		// 有机液体装载
		String sql16 = "delete t16 FROM ol_leaked_check t16 WHERE enterprise =?1";
		int t16 = entityManager.createNativeQuery(sql16, LeakedCheck.class).setParameter(1, enterpriseId)
				.executeUpdate();
		// 有机液体装载
		String sql15 = "delete t15 FROM ol_equipment_leaked t15 WHERE enterprise =?1";
		int t15 = entityManager.createNativeQuery(sql15, EquipmentLeaked.class).setParameter(1, enterpriseId)
				.executeUpdate();
		
		String sql20 = "delete t20 FROM ol_fule t20 WHERE enterprise =?1";
		int t20 = entityManager.createNativeQuery(sql20, Fuel.class).setParameter(1, enterpriseId)
				.executeUpdate();
		
		String sql21 = "delete t21 FROM ol_sewage_dispose t21 WHERE enterprise =?1";
		int t21 = entityManager.createNativeQuery(sql21, SewageDispose.class).setParameter(1, enterpriseId)
				.executeUpdate();
		
		
		// 企业
//		String sql11 = "delete t11 FROM ol_enterprise t11 WHERE id =?1";
//		int t11 = entityManager.createNativeQuery(sql11, Enterprise.class).setParameter(1, enterpriseId)
//				.executeUpdate();
		remove(find(enterpriseId));

	}

	@Override
	public void exportEnterprise() throws Exception {
		// TODO Auto-generated method stub
		// 郑有权

	}

	@Override
	public List<Enterprise> findALLByProjectid(Integer projectId) throws Exception {
		
		String sql = "SELECT a.* FROM  ol_enterprise a,ol_project_enterprise b WHERE a.id = b.ol_enterprise AND b.projects = ?1";
		
		Query createNativeQuery = entityManager.createNativeQuery(sql,Enterprise.class);
		createNativeQuery.setParameter(1, projectId);

		return (List<Enterprise>)createNativeQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Enterprise> findCustomPage(CustomFilter customFilter) throws Exception {
		Pageable pageable = customFilter.getPageable();
		
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
//		sql.append("SELECT a.* FROM  ol_enterprise a,ol_project_enterprise b WHERE a.id = b.ol_enterprise");
//		sql1.append("SELECT count(a.id) FROM  ol_enterprise a,ol_project_enterprise b WHERE a.id = b.ol_enterprise");
		sql.append("SELECT a.* FROM  ol_enterprise a,sys_project_enterprise_user b WHERE a.id = b.enterprise");
		sql1.append("SELECT count(a.id) FROM  ol_enterprise a,sys_project_enterprise_user b WHERE a.id = b.enterprise");
		if(StringUtils.isNotBlank(customFilter.getProject())){
//			sql.append(" AND b.projects = ?1");
//			sql1.append(" AND b.projects = ?1");
			sql.append(" AND b.project = ?1");
			sql1.append(" AND b.project = ?1");
		}
		if(StringUtils.isNotBlank(customFilter.getUser())){
//			sql.append(" AND a.account in (?4)");
//			sql1.append(" AND a.account in (?4)");
			sql.append(" AND b.user in (?4)");
			sql1.append(" AND b.user in (?4)");
		}
		
		if (StringUtils.isNotBlank(customFilter.getEnterpriseName())) {
			sql.append(" AND a.name like  ?5 ");
			sql1.append(" AND a.name like  ?5 ");
		}
		if (StringUtils.isNotBlank(customFilter.getIndustryCategoryCodeMain())) {
			sql.append(" AND a.industry_category_code_main =  ?6 ");
			sql1.append(" AND a.industry_category_code_main =  ?6 ");
		}
		if (StringUtils.isNotBlank(customFilter.getIndustryCategoryCodeMiddle())) {
			sql.append(" AND a.industry_category_code_middle =  ?7 ");
			sql1.append(" AND a.industry_category_code_middle =  ?7 ");
		}
		if (StringUtils.isNotBlank(customFilter.getProvincesCode())) {
			sql.append(" AND a.provinces =  ?8 ");
			sql1.append(" AND a.provinces =  ?8 ");
		}
		if (StringUtils.isNotBlank(customFilter.getCityCode())) {
			sql.append(" AND a.city =  ?9 ");
			sql1.append(" AND a.city =  ?9 ");
		}
		if (StringUtils.isNotBlank(customFilter.getCountyCode())) {
			sql.append(" AND a.county =  ?10 ");
			sql1.append(" AND a.county =  ?10 ");
		}
		if (StringUtils.isNotBlank(customFilter.getEnterpriseType())) {
			sql.append(" AND a.enterprise_type =  ?11 ");
			sql1.append(" AND a.enterprise_type =  ?11 ");
		}
		if (StringUtils.isNotBlank(customFilter.getStatus())) {
//			sql.append(" AND a.status =  ?14 ");
//			sql1.append(" AND a.status =  ?14 ");
			sql.append(" AND b.task_status =  ?14 ");
			sql1.append(" AND b.task_status =  ?14 ");
		}
		
		if(StringUtils.isNotBlank(customFilter.getStartDate()) || StringUtils.isNotBlank(customFilter.getEndDate())){
			sql.append(" AND ( SUBSTRING(a.create_date, 1, 10) BETWEEN ?12 AND ?13) ");
			sql1.append(" AND ( SUBSTRING(a.create_date, 1, 10) BETWEEN ?12 AND ?13) ");
		}
		
		sql.append(" ORDER BY a.create_date ");
		sql1.append(" ORDER BY a.create_date ");
		sql.append(" limit ?2 , ?3");
		
		Query createNativeQuery = entityManager.createNativeQuery(sql.toString(),Enterprise.class);
		Query createNativeQuery1 = entityManager.createNativeQuery(sql1.toString());
		if(StringUtils.isNotBlank(customFilter.getProject())){
			createNativeQuery.setParameter(1, customFilter.getProject());
			createNativeQuery1.setParameter(1, customFilter.getProject());
		}
		createNativeQuery.setParameter(2, (pageable.getPageNumber()-1)*pageable.getPageSize());
		createNativeQuery.setParameter(3, pageable.getPageSize());
		
		
		if(StringUtils.isNotBlank(customFilter.getUser())){
			createNativeQuery.setParameter(4, customFilter.getUser());
			createNativeQuery1.setParameter(4, customFilter.getUser());
		}
		if (StringUtils.isNotBlank(customFilter.getEnterpriseName())) {
			createNativeQuery.setParameter(5, "%"+customFilter.getEnterpriseName()+"%");
			createNativeQuery1.setParameter(5, "%"+customFilter.getEnterpriseName()+"%");
		}
		
		if (StringUtils.isNotBlank(customFilter.getIndustryCategoryCodeMain())) {
			createNativeQuery.setParameter(6, customFilter.getIndustryCategoryCodeMain());
			createNativeQuery1.setParameter(6, customFilter.getIndustryCategoryCodeMain());
		}
		if (StringUtils.isNotBlank(customFilter.getIndustryCategoryCodeMiddle())) {
			createNativeQuery.setParameter(7, customFilter.getIndustryCategoryCodeMiddle());
			createNativeQuery1.setParameter(7, customFilter.getIndustryCategoryCodeMiddle());
		}
		if (StringUtils.isNotBlank(customFilter.getProvincesCode())) {
			createNativeQuery.setParameter(8, customFilter.getProvincesCode());
			createNativeQuery1.setParameter(8, customFilter.getProvincesCode());
		}
		if (StringUtils.isNotBlank(customFilter.getCityCode())) {
			createNativeQuery.setParameter(9, customFilter.getCityCode());
			createNativeQuery1.setParameter(9, customFilter.getCityCode());
		}
		if (StringUtils.isNotBlank(customFilter.getCountyCode())) {
			createNativeQuery.setParameter(10, customFilter.getCountyCode());
			createNativeQuery1.setParameter(10, customFilter.getCountyCode());
		}
		if (StringUtils.isNotBlank(customFilter.getEnterpriseType())) {
			createNativeQuery.setParameter(11, customFilter.getEnterpriseType());
			createNativeQuery1.setParameter(11, customFilter.getEnterpriseType());
		}
		if (StringUtils.isNotBlank(customFilter.getStatus())) {
			createNativeQuery.setParameter(14, customFilter.getStatus());
			createNativeQuery1.setParameter(14, customFilter.getStatus());
		}
		
		if(StringUtils.isNotBlank(customFilter.getStartDate()) || StringUtils.isNotBlank(customFilter.getEndDate())){
			createNativeQuery.setParameter(12, customFilter.getStartDate());
			createNativeQuery1.setParameter(12, customFilter.getStartDate());
			createNativeQuery.setParameter(13, customFilter.getEndDate());
			createNativeQuery1.setParameter(13, customFilter.getEndDate());
		}
		
		Long count = Long.parseLong(createNativeQuery1.getSingleResult().toString());
		
	
		//郑有权
		return new Page<>((List<Enterprise>)createNativeQuery.getResultList(), count, pageable);
	}

	@Override
	public void updatestatus(Integer enterpriseId,Integer status,String opinion) {
		String jpql = "update Enterprise enterprise set status=:status,opinion=:opinion where id=:id";
		try {
			entityManager.createQuery(jpql).setParameter("status", status).setParameter("opinion", opinion).setParameter("id", enterpriseId)
					.executeUpdate();
		} catch (Exception e) {
			
		}
		
	}

	@Override
	public List<Map<String, Object>> findStatistics(Integer projectId, Integer type, Date start, Date end,
			String enterpriseType) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT (SELECT b.name FROM sys_area b WHERE b.code = enter.county) county,puser.task_status taskStatus,COUNT(*) count  FROM ol_enterprise enter,sys_project_enterprise_user puser WHERE puser.enterprise = enter.id AND puser.project = ?1 AND enter.enterprise_type=?4 ");
		if(start != null && end != null){
			sql.append(" AND enter.create_date BETWEEN ?2 AND ?3");
		}
		sql.append(" GROUP BY enter.county,puser.task_status ");
		
		Query createNativeQuery = entityManager.createNativeQuery(sql.toString());
		
		createNativeQuery.setParameter(1, projectId);
		createNativeQuery.setParameter(4, enterpriseType);
		
		if(start != null && end != null){
			createNativeQuery.setParameter(2, start);
			createNativeQuery.setParameter(3, end);
		}
		createNativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		resultList = createNativeQuery.getResultList();
		
		return resultList;
	}
	
	


}