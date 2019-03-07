package com.online.excelexport.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import com.online.entity.online.Enterprise;
import com.online.entity.online.ProjectModuleDetail;
import com.online.entity.online.ProjectModuleInfo;
import com.online.excelexport.BeanUtils;
import com.online.excelexport.ExcelWriteInterface;
import com.online.excelexport.ExcelWriteUtils;
import com.online.excelexport.ModuleWirterBean;
import com.online.excelexport.ModuleWriteReturnBean;
import com.online.exportpdf.DataBean;
import com.online.service.SectionService;

@Component
public abstract class BaseImpl<T> implements ExcelWriteInterface {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@Autowired 
	private SectionService sectionService;
   
	private Class<T> entityClass;
	@SuppressWarnings("unchecked")
	public BaseImpl() {
		ResolvableType resolvableType = ResolvableType.forClass(getClass());
		entityClass = (Class<T>) resolvableType.getSuperType().getGeneric().resolve();
	}
	
	@Override
	public ModuleWriteReturnBean writeExcel(ProjectModuleInfo projectModuleInfo, ModuleWirterBean moduleWirterBean,
			Sheet sheet, Enterprise enterprise) {
		// TODO Auto-generated method stub
		List<T> moduleInfoDataList = getModuleInfoDataList(projectModuleInfo, enterprise);
		int startRow = moduleWirterBean.getStartRow();
		int startCol = moduleWirterBean.getStartCol();
		List<ProjectModuleDetail> projectModuleDetails = projectModuleInfo.sortProjectModuleDetails();
//		System.out.println("数据：：："+ moduleInfoDataList.size() + " 模块名称：："+ projectModuleInfo.getName());
		if(moduleInfoDataList== null || moduleInfoDataList.size()==0){
			for (int i = 0;i<projectModuleDetails.size();i++) {
				ExcelWriteUtils.writeValue2Excel("", sheet, startRow, startCol++);
			}
		}else{
			for (T openYard : moduleInfoDataList) {//获得数据
				startCol = moduleWirterBean.getStartCol();
				for (ProjectModuleDetail projectModuleDetail : projectModuleDetails) {
					String value =BeanUtils.getPropertyByName(openYard,projectModuleDetail.getKey());
					
					ExcelWriteUtils.writeValue2Excel(transtranlate(value,projectModuleDetail.getKey()), sheet, startRow, startCol++);
				}
				startRow = startRow +1;
			}
		}
		
		return new ModuleWriteReturnBean(startCol, startRow >moduleWirterBean.getStartRow()?startRow-1:startRow);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DataBean> readData(ProjectModuleInfo projectModuleInfo,
			Enterprise enterprise) {
		return (List<DataBean>)getModuleInfoDataList(projectModuleInfo, enterprise);
	}
	
	
	/**
	 * 获得数据
	 * @param moduleInfo
	 * @param enterprise
	 * @return
	 */
	public  List<T> getModuleInfoDataList(ProjectModuleInfo projectModuleInfo,Enterprise enterprise){
		Integer projectId = projectModuleInfo.getProjectId();
		if(projectId == null){
			projectId = projectModuleInfo.getProjectTemplates().getProjectId();
		}
		Integer enterpriseId = enterprise.getId();
		
		Integer sectionId = -1;
		
		
		String jpql = getJpql();
		//System.out.println(jpql);
		/*if(jpql.indexOf("RawMaterials") != -1){
			Section section = sectionService.findByEnterpriseidAndProject(enterpriseId, projectId);
			if(section != null){
				sectionId = section.getId();
			}
			return entityManager.createQuery(jpql,entityClass).setParameter("projectId", projectId)
					.setParameter("enterpriseId", enterpriseId)
					.setParameter("sectionId", sectionId)
					.getResultList();
		}else{
			return entityManager.createQuery(jpql,entityClass).setParameter("projectId", projectId).setParameter("enterpriseId", enterpriseId).getResultList();
		}*/
		return entityManager.createQuery(jpql,entityClass).setParameter("projectId", projectId).setParameter("enterpriseId", enterpriseId).getResultList();
		
	};
	public abstract String getJpql();
	
	/**
	 * 转义字符
	 * @param value 原值
	 * @param fieldName 字段名称
	 * @return
	 */
	public String transtranlate(String value,String fieldName){
		switch (value) {
		case "false":
			return "否";
		case "true":
			return "是";
		case "organic":
			return "有机溶剂";
		case "inorganic":
			return "无机溶剂";
		case "oil":
			return "油性";
		case "water":
			return "水性";
		case "section":
			return "生产线";
		case "boiler":
			return "锅炉";
		case "kiln":
			return "炉窑";
		case "powerPlant":
			return "自备发电机组";
		case "notSubmit":
			return "未提交";
		case "alreadySubmit":
			return "已提交";
		case "approve":
			return "审核通过";
		case "noApprove":
			return "审核未通过";
		default:
			break;
		}
		return value;
	}
	
	
   
}
