package com.online.util.datapush;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import org.apache.commons.lang.StringUtils;


/**
 * 数据移动辅助类
 * @author DEV2
 *
 */
public class DataMoveHelp {
	  
	 private JDBCHelp org;
	 private JDBCHelp source;
	 
	 public void close(){
		 org.close();
		 source.close();
	 }
		
	 /**
	  * 初始化
	  * @param source 源数据
	  * @param org 目标数据
	  */
	 public void init(DataSourceBean source,DataSourceBean org){
		 this.org = org.generalJDBCHelp();
		 this.source = source.generalJDBCHelp();
	 }
	 public void moveTable(String sourceTableName,String moveSql,Predicate<String> predicate,boolean update) throws SQLException{
		 moveTable(sourceTableName, moveSql, predicate,new HashMap<>(), update);
	 }
	 
	 /**
	  * 移动表格
	  * @param orgTableName 移动目标数据表名
	  * @param moveSql 获取源数据的sql语句
	  * @param predicate 源数据数据列是否移植判定条件
	  * @param defaultValues 默认字段
	  * @param update 插入失败是否尝试更新
	  * @throws SQLException
	  */
	 public void moveTable(String orgTableName,String moveSql,Predicate<String> predicate,Map<String, Object> defaultValues,boolean update) throws SQLException{
		 List<String> colNames = org.getColNames(orgTableName,predicate);//获取到目标表格所有字段
		 defaultValues.put("founder", "system");
		 List<Map<String, Object>> sqlToList = source.sqlToList(moveSql,(o)->colNames.contains(o));//过滤获源数据
		 if(sqlToList == null){
			 return;
		 }
		 System.out.println("当前"+orgTableName+"|"+sqlToList.size());
		 org.conn.setAutoCommit(false);
		 for (Map<String, Object> map : sqlToList) {
			 String insert = org.insert(orgTableName, map, colNames,defaultValues);
			 if(StringUtils.isNotEmpty(insert) && Objects.equals(true, update)){//插入失败尝试更新
				 org.update(orgTableName, map, colNames, defaultValues, " id=:id");
			 }
		 }
		 org.conn.commit();
	 }
	 
	 String[] tables = {
			 "ol_agricultural_machinery","ol_airplane_type","ol_airport_machinery","ol_barbecue_restaurant","ol_base_info"
			 ,"ol_beasts_birds","ol_beton_stir_station",
			 "ol_exhaustion_hole","ol_governance_measures","ol_boiler_information"
			 ,"ol_boiler_qs","ol_breakdown_service_base"
			 ,"ol_breakdown_service_solvent","ol_build_area","ol_bus_inventory","ol_car_weight","ol_catering","ol_catering_base"
			 ,"ol_catering_fuel","ol_ceit_enterprise","ol_city_road_clean","ol_city_road_clean_machine","ol_civil_aviation"
			 ,"ol_civil_fuel_gas","ol_civil_fuel_gas_month","ol_construction_consumption","ol_construction_machinery"
			 ,"ol_construction_member","ol_construction_period","ol_construction_site","ol_construction_yard","ol_crop"
			 ,"ol_dry_clear_base","ol_dry_clear_solvent","ol_enterprise_fuel_gas","ol_enterprise_fuel_gas_month"
			 ,"ol_enterprise_power","ol_equipment_leaked","ol_fertilizer_pesticide","ol_fishing_boat"
			 ,"ol_fishing_boat_month","ol_food_drug_catering","ol_fule","ol_house_build_site"
			 ,"ol_household_energy","ol_in_co_breakdown_service","ol_in_co_catering","ol_in_co_dry_clean"
			 ,"ol_product","ol_raw_materials","ol_kilnn"
			 ,"ol_leaked_check","ol_livestock_breeding","ol_loading_information","ol_monthly_information","ol_motor_vehicles"
			 ,"ol_motor_vehicles_inventory","ol_non_road","ol_oil_storage","ol_oil_tank","ol_open_yard","ol_passenger_station"
			 ,"ol_petrol_gas_station","ol_petrol_sales","ol_petrol_station_base","ol_petrol_storage_type"
			 ,"ol_photo_file"
			 ,"ol_plough","ol_port","ol_port_mechanical","ol_power_plant","ol_power_plant_month"
			 ,"ol_road_type_num"
			 ,"ol_section"
			 ,"ol_sewage_dispose","ol_single_boiler_fuel","ol_single_boiler_information"
			 ,"ol_slag_car","ol_steel","ol_traffic_car_weight","ol_transport_car","ol_transport_ship","ol_transport_ship_month"
			 ,"ol_vehicle_flowrate"
			 };
	 
	 //项目表
	 private void moveProject(String projectId) throws SQLException{
		 String sql = "SELECT * FROM ol_project WHERE  id="+projectId;
		 moveTable("ol_project", sql, (str)->true,true);	
		 System.out.println("完成项目信息移动");
	 }
	 //项目子项目
	 private void moveSys_project_type(String projectId) throws SQLException{
		 String sql = "SELECT * FROM sys_project_type WHERE project =  "+projectId;
		 moveTable("sys_project_type", sql, (str)->true,true);	
		 System.out.println("完成项目子项目信息移动");
	 }
	
	 //名录库
	 private void moveSysEnterpriseDictionary(String projectId) throws SQLException{
		 String sql = "SELECT * FROM sys_enterprise_dictionary WHERE project_id = "+projectId;
		 moveTable("sys_enterprise_dictionary", sql, (str)->true,false);	
		 System.out.println("完成名录库移动");
	 }
	 //地区
	 private void moveSysArea() throws SQLException{
		 String sql = "SELECT * FROM sys_area ";
		 moveTable("sys_area", sql, (str)->true,false);	
		 System.out.println("完成地区表移动");
	 }
	//企业表
	 private void moveOl_enterprise(String projectId) throws SQLException{
		 String sql = "SELECT en.* FROM ol_enterprise en,sys_project_enterprise_user peu WHERE en.id = peu.enterprise AND peu.project = "+projectId;
		 moveTable("ol_enterprise", sql, (str)->true,false);	
		 System.out.println("完成企业表移动");
	 }
	 
	 
	 //任务
	 private void moveOl_task_manager(String projectId) throws SQLException{
		 String sql = "SELECT * FROM ol_task_manager WHERE project_id = "+projectId;
		 moveTable("ol_task_manager", sql, (str)->true,false);	
		 System.out.println("完成任务移动");
	 }
	 
	//企业项目关系
	 private void moveOl_project_enterprise(String projectId) throws SQLException{
		 String sql = "SELECT * FROM ol_project_enterprise WHERE projects = "+projectId;
		 moveTable("ol_project_enterprise", sql, (str)->true,false);	
		 System.out.println("完成企业项目关系移动");
	 }
	 
	 //用户
	 private void moveSysUser(String projectId) throws SQLException{
		 String sql = "SELECT * FROM sys_user WHERE project =  "+projectId;
		 moveTable("sys_user", sql, (str)->true,false);	
		 System.out.println("完成用户移动");
	 }
	 //用户角色
	 private void moveSysUserRole(String projectId) throws SQLException{
		 String sql = "SELECT sur.* FROM sys_user_role sur,sys_user su WHERE sur.sys_user = su.id AND su.project =  "+projectId;
		 moveTable("sys_user_role", sql, (str)->true,false);	
		 System.out.println("完成用户角色移动");
	 }
		 
	 //企业项目关联关系表
	 private void moveSys_project_enterprise_user(String projectId) throws SQLException{
		 String sql = "SELECT * FROM sys_project_enterprise_user WHERE project = "+projectId;
		 moveTable("sys_project_enterprise_user", sql, (str)->true,false);	
		 System.out.println("完成企业项目关联关系表移动");
	 }
	 
	
	 
	 public void movProject(String projectId,boolean isUpdated) throws SQLException{
//		 moveProject(projectId);
//		 moveSys_project_type(projectId);
//		 moveSysEnterpriseDictionary(projectId);
//	//	 moveSysArea();
//		 moveOl_enterprise(projectId);
//		 moveOl_task_manager(projectId);
//		 moveOl_project_enterprise(projectId);
//		 moveSysUser(projectId);
		 moveSysUserRole(projectId);
//		 moveSys_project_enterprise_user(projectId);
		 
//		 for(int i= 0;i<tables.length;i++){
//			 String sql = "SELECT * FROM "+tables[i] +" WHERE project = "+projectId;
//			 moveTable(tables[i], sql, (str)->true,false);	
//			 System.out.println("完成"+tables[i]+"移动"); 
//		 }
	 }
	 
	 


    
	 
	 public static void main(String[] args) throws SQLException {
		 DataSourceBean orgBean = new DataSourceBean("jdbc:mysql://192.168.1.32:3306/online0129?useUnicode=true&characterEncoding=UTF-8","root","0bee89b07a248e27c83");
		 DataSourceBean sourceBean = new DataSourceBean("jdbc:mysql://192.168.1.81:3306/publish?useUnicode=true&characterEncoding=UTF-8","root","HongQingDB");
		 DataMoveHelp help = new DataMoveHelp();
		 help.init(sourceBean, orgBean);
		 help.movProject("42", false);
		 help.close();
	}
	
	
	

}
