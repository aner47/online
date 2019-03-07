package com.online.service.impl;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Order;
import com.online.dao.SectionDao;
import com.online.entity.DictionaryData;
import com.online.entity.online.ExhaustionHole;
import com.online.entity.online.GovernanceMeasures;
import com.online.entity.online.Product;
import com.online.entity.online.RawMaterials;
import com.online.entity.online.Section;
import com.online.entity.online.Enterprise.EnterpriseType;
import com.online.entity.online.embeddable.EnterpriseEmissionsManagement;
import com.online.service.ExhaustionHoleService;
import com.online.service.GovernanceMeasuresService;
import com.online.service.ProductService;
import com.online.service.RawMaterialsService;
import com.online.service.SectionService;
import com.online.service.SelectService;
import com.online.util.SpringUtils;
/**
 * 
 * @author 左志平
 * 
 * 工段服务实现
 *
 */
@Service("sectionServiceImpl")
public class SectionServiceImpl extends BaseServiceImpl<Section, Integer> implements SelectService, SectionService {
	
	@Autowired
	private GovernanceMeasuresService governanceMeasuresService;
	
	@Autowired
	private ExhaustionHoleService exhaustionHoleService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private RawMaterialsService rawMaterialsService;
	@Autowired
	private SectionDao sectionDao;
	
	
	@Override
	public Section saveSection(Section section,
			Integer exhaustionHoleId, Integer governanceMeasures1, Integer governanceMeasures2, Integer governanceMeasures3, Integer governanceMeasures4) {
		/*int no = 1;
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
		filters.add(new Filter("enterpriseEmissionsManagement.enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId()));
		List<Order> listOrder = new ArrayList<>();
		listOrder.add(Order.desc("createDate"));
		List<Section> lists = findList(null, filters, null);
		if(lists != null && lists.size()>0){
			if(lists.get(0).getFacilityNo() != null){
				no = Integer.parseInt(lists.get(0).getFacilityNo())+1;
			}
			
		}
		section.setFacilityNo(no+"");*/
		EnterpriseEmissionsManagement eem = new EnterpriseEmissionsManagement();
		eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		if(exhaustionHoleId != null){
			eem.setExhaustionHole(exhaustionHoleService.find(exhaustionHoleId));
		}
		if(governanceMeasures1 != null){
			eem.setGovernanceMeasures1(governanceMeasuresService.find(governanceMeasures1));
		}
		if(governanceMeasures2 != null){
			eem.setGovernanceMeasures2(governanceMeasuresService.find(governanceMeasures2));
		}
		if(governanceMeasures3 != null){
			eem.setGovernanceMeasures3(governanceMeasuresService.find(governanceMeasures3));
		}
		if(governanceMeasures4 != null){
			eem.setGovernanceMeasures4(governanceMeasuresService.find(governanceMeasures4));
		}
		section.setEnterpriseEmissionsManagement(eem);
		
		Product product = section.getProduct();
		if (product!=null && product.getName()!=null) {
			section.setProduct(productService.save(product));
		}
		
		section.setProject(SpringUtils.getCurrentProject());
		
		RawMaterials material = null;
		material = section.getRawMaterials1();
		if (material != null &&StringUtils.isNotBlank(material.getName()) && material.getConsumption() != null) {
			section.setRawMaterials1(rawMaterialsService.save(material));
		}else{
			section.setRawMaterials1(null);
		}
		
		material = section.getRawMaterials2();
		if (material != null &&StringUtils.isNotBlank(material.getName())&& material.getConsumption() != null) {
			section.setRawMaterials2(rawMaterialsService.save(material));
		}else{
			section.setRawMaterials2(null);
		}
		
		material = section.getRawMaterials3();
		if (material != null &&StringUtils.isNotBlank(material.getName()) && material.getConsumption() != null) {
			section.setRawMaterials3(rawMaterialsService.save(material));
		}else{
			section.setRawMaterials3(null);
		}
		
		material = section.getRawMaterials4();
		if (material != null &&StringUtils.isNotBlank(material.getName()) && material.getConsumption() != null) {
			section.setRawMaterials4(rawMaterialsService.save(material));
		}else{
			section.setRawMaterials4(null);
		}
		
		material = section.getRawMaterials5();
		if (material != null &&StringUtils.isNotBlank(material.getName()) && material.getConsumption() != null) {
			section.setRawMaterials5(rawMaterialsService.save(material));
		}else{
			section.setRawMaterials5(null);
		}
		
		return save(section);
	}

	@Override
	public Section updateSection(Section section,Integer exhaustionHoleId,
	        Integer governanceMeasures1, 
	Integer governanceMeasures2, Integer governanceMeasures3, Integer governanceMeasures4) {
		
		EnterpriseEmissionsManagement eem = section.getEnterpriseEmissionsManagement();
		if(eem == null){
			eem = new EnterpriseEmissionsManagement();
		}
		eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		
		if (governanceMeasures1!=null) {
			GovernanceMeasures g1 = governanceMeasuresService.find(governanceMeasures1);
			eem.setGovernanceMeasures1(g1);
		}else{
			eem.setGovernanceMeasures1(null);
		}
		if (governanceMeasures2!=null) {
			GovernanceMeasures g2 = governanceMeasuresService.find(governanceMeasures2);
			eem.setGovernanceMeasures2(g2);
		}else{
			eem.setGovernanceMeasures2(null);
		}
		if (governanceMeasures3!=null) {
			GovernanceMeasures g3 = governanceMeasuresService.find(governanceMeasures3);
			eem.setGovernanceMeasures3(g3);
		}else{
			eem.setGovernanceMeasures3(null);
		}
		if (governanceMeasures4!=null) {
			GovernanceMeasures g4 = governanceMeasuresService.find(governanceMeasures4);
			eem.setGovernanceMeasures4(g4);
		}else{
			eem.setGovernanceMeasures4(null);
		}
		if (exhaustionHoleId!=null) {
			ExhaustionHole e = exhaustionHoleService.find(exhaustionHoleId);
			eem.setExhaustionHole(e);
		}else{
			eem.setExhaustionHole(null);
		}
		section.setEnterpriseEmissionsManagement(eem);
		
		if(section.getProduct()!=null){
			if (section.getProduct().getId() != null) {
				section.setProduct(productService.update(section.getProduct()));
			}else{
				section.setProduct(productService.save(section.getProduct()));
			}
		}
		
		if(section.getRawMaterials1()!=null){
			if (section.getRawMaterials1().getId() != null && StringUtils.isNotBlank(section.getRawMaterials1().getName())&& section.getRawMaterials1().getConsumption() != null) {
				section.setRawMaterials1(rawMaterialsService.update(section.getRawMaterials1()));
			}else if(StringUtils.isNotBlank(section.getRawMaterials1().getName()) && section.getRawMaterials1().getConsumption() != null){
				section.setRawMaterials1(rawMaterialsService.save(section.getRawMaterials1()));
			}else if(section.getRawMaterials1().getId() != null){
				RawMaterials rawMaterials1 = rawMaterialsService.find(section.getRawMaterials1().getId());
				rawMaterials1.setProductName(section.getProduct() != null?section.getProduct().getName():null);
				section.setRawMaterials1(rawMaterialsService.update(rawMaterials1));
			}else{
				section.setRawMaterials1(null);
			}
		}
		if(section.getRawMaterials2()!=null){
			if (section.getRawMaterials2().getId() != null&& StringUtils.isNotBlank(section.getRawMaterials2().getName())&& section.getRawMaterials2().getConsumption() != null) {
				section.setRawMaterials2(rawMaterialsService.update(section.getRawMaterials2()));
			}else if(StringUtils.isNotBlank(section.getRawMaterials2().getName())&& section.getRawMaterials2().getConsumption() != null){
				section.setRawMaterials2(rawMaterialsService.save(section.getRawMaterials2()));
			}else if(section.getRawMaterials2().getId() != null){
				RawMaterials rawMaterials2 = rawMaterialsService.find(section.getRawMaterials2().getId());
				rawMaterials2.setProductName(section.getProduct() != null?section.getProduct().getName():null);
				section.setRawMaterials2(rawMaterialsService.update(rawMaterials2));
			}else{
				section.setRawMaterials2(null);
			}
		}
		if(section.getRawMaterials3()!=null){
			if (section.getRawMaterials3().getId() != null && StringUtils.isNotBlank(section.getRawMaterials3().getName())&& section.getRawMaterials3().getConsumption() != null) {
				section.setRawMaterials3(rawMaterialsService.update(section.getRawMaterials3()));
			}else if(StringUtils.isNotBlank(section.getRawMaterials3().getName())&& section.getRawMaterials3().getConsumption() != null){
				section.setRawMaterials3(rawMaterialsService.save(section.getRawMaterials3()));
			}else if(section.getRawMaterials3().getId() != null){
				RawMaterials rawMaterials3 = rawMaterialsService.find(section.getRawMaterials3().getId());
				rawMaterials3.setProductName(section.getProduct() != null?section.getProduct().getName():null);
				section.setRawMaterials3(rawMaterialsService.update(rawMaterials3));
			}else{
				section.setRawMaterials3(null);
			}
		}
		if(section.getRawMaterials4()!=null){
			if (section.getRawMaterials4().getId() != null && StringUtils.isNotBlank(section.getRawMaterials4().getName())&& section.getRawMaterials4().getConsumption() != null) {
				section.setRawMaterials4(rawMaterialsService.update(section.getRawMaterials4()));
			}else if(StringUtils.isNotBlank(section.getRawMaterials4().getName())&& section.getRawMaterials4().getConsumption() != null){
				section.setRawMaterials4(rawMaterialsService.save(section.getRawMaterials4()));
			}else if(section.getRawMaterials4().getId() != null){
				RawMaterials rawMaterials4 = rawMaterialsService.find(section.getRawMaterials4().getId());
				rawMaterials4.setProductName(section.getProduct() != null?section.getProduct().getName():null);
				section.setRawMaterials4(rawMaterialsService.update(rawMaterials4));
			}else{
				section.setRawMaterials4(null);
			}
		}
		if(section.getRawMaterials5()!=null ){
			if (section.getRawMaterials5().getId() != null && StringUtils.isNotBlank(section.getRawMaterials5().getName())&& section.getRawMaterials5().getConsumption() != null) {
				section.setRawMaterials5(rawMaterialsService.update(section.getRawMaterials5()));
			}else if(StringUtils.isNotBlank(section.getRawMaterials5().getName())&& section.getRawMaterials5().getConsumption() != null){
				section.setRawMaterials5(rawMaterialsService.save(section.getRawMaterials5()));
			}else if(section.getRawMaterials5().getId() != null){
				RawMaterials rawMaterials5 = rawMaterialsService.find(section.getRawMaterials5().getId());
				rawMaterials5.setProductName(section.getProduct() != null?section.getProduct().getName():null);
				section.setRawMaterials5(rawMaterialsService.update(rawMaterials5));
			}else{
				section.setRawMaterials5(null);
			}
		}
		

		return update(section,"enterprise","project"); 
	}
	
	@Override
	public Section saveSimpleSection(Section section, String governanceMeasures1,
			String governanceMeasures2, String governanceMeasures3, String governanceMeasures4) {
		
		EnterpriseEmissionsManagement eem = new EnterpriseEmissionsManagement();
		eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		
		if(section.getEnterpriseEmissionsManagement() != null && section.getEnterpriseEmissionsManagement().getExhaustionHole() != null){
			ExhaustionHole exhaustionHole = new ExhaustionHole();
			exhaustionHole.setHeight(section.getEnterpriseEmissionsManagement().getExhaustionHole().getHeight());
			eem.setExhaustionHole(exhaustionHoleService.save(exhaustionHole));
		}
		
		if(StringUtils.isNotBlank(governanceMeasures1)){
			GovernanceMeasures  governance1 = new GovernanceMeasures();
			governance1.setName(governanceMeasures1);
			eem.setGovernanceMeasures1(governanceMeasuresService.save(governance1));
		}
		
		if(StringUtils.isNotBlank(governanceMeasures2)){
			GovernanceMeasures  governance2 = new GovernanceMeasures();
			governance2.setName(governanceMeasures2);
			eem.setGovernanceMeasures2(governanceMeasuresService.save(governance2));
		}
		if(StringUtils.isNotBlank(governanceMeasures3)){
			GovernanceMeasures  governance3 = new GovernanceMeasures();
			governance3.setName(governanceMeasures3);
			eem.setGovernanceMeasures3(governanceMeasuresService.save(governance3));
		}
		if(StringUtils.isNotBlank(governanceMeasures4)){
			GovernanceMeasures  governance4 = new GovernanceMeasures();
			governance4.setName(governanceMeasures4);
			eem.setGovernanceMeasures4(governanceMeasuresService.save(governance4));
		}
		section.setEnterpriseEmissionsManagement(eem);
		
		Product product = section.getProduct();
		if (product!=null && product.getName()!=null) {
			section.setProduct(productService.save(product));
		}
		
		section.setProject(SpringUtils.getCurrentProject());
		
		RawMaterials material1 = section.getRawMaterials1();
		if (material1 != null && material1.getName() != null) {
			section.setRawMaterials1(rawMaterialsService.save(material1));
		}
		RawMaterials material2 = section.getRawMaterials2();
		if (material2 != null && material2.getName() != null) {
			section.setRawMaterials2(rawMaterialsService.save(material2));
		}
		RawMaterials material3 = section.getRawMaterials3();
		if (material3 != null && material3.getName() != null) {
			section.setRawMaterials3(rawMaterialsService.save(material3));
		}
		
		
		Section sectionback =  save(section);
		material1.setSectionId(sectionback.getId());
		material2.setSectionId(sectionback.getId());
		material3.setSectionId(sectionback.getId());
		
		
		return sectionback;
		
	}
	
	@Override
	public Section updateSimpleSection(Section section, String governanceMeasures1, String governanceMeasures2, String governanceMeasures3, String governanceMeasures4) {
		EnterpriseEmissionsManagement eem = section.getEnterpriseEmissionsManagement();
		if(eem == null ){
			eem = new EnterpriseEmissionsManagement();
		}
		eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		
		if(eem != null && eem.getExhaustionHole() != null){
			if(eem.getExhaustionHole().getId() != null){
				ExhaustionHole exhaustionHole = exhaustionHoleService.find(eem.getExhaustionHole().getId());
				exhaustionHole.setHeight(eem.getExhaustionHole().getHeight());
				eem.setExhaustionHole(exhaustionHole);
			}else{
				eem.setExhaustionHole(null);
			}
			
		}
		
		GovernanceMeasures governance1 = null;
		if(StringUtils.isNotBlank(governanceMeasures1) ){
			governance1 = eem.getGovernanceMeasures1();
			if(governance1 != null && governance1.getId() != null){
				governance1.setName(governanceMeasures1);
				eem.setGovernanceMeasures1(governanceMeasuresService.update(governance1));
			}else{
				governance1 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures1,EnterpriseType.GENERAL);
				eem.setGovernanceMeasures1(governance1);
			}
			
		}else{
			eem.setGovernanceMeasures1(null);
		}
		
		GovernanceMeasures governance2 = null;
		if(StringUtils.isNotBlank(governanceMeasures2) ){
			governance2 = eem.getGovernanceMeasures2();
			if(governance2 != null && governance2.getId() != null){
				governance2.setName(governanceMeasures2);
				eem.setGovernanceMeasures2(governanceMeasuresService.update(governance2));
			}else{
				governance2 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures2,EnterpriseType.GENERAL);
				eem.setGovernanceMeasures2(governance2);
			}
			
		}else{
			eem.setGovernanceMeasures2(null);
		}
		GovernanceMeasures governance3 = null;
		if(StringUtils.isNotBlank(governanceMeasures3) ){
			governance3 = eem.getGovernanceMeasures3();
			if(governance3 != null && governance3.getId() != null){
				governance3.setName(governanceMeasures3);
				eem.setGovernanceMeasures3(governanceMeasuresService.update(governance3));
			}else{
				governance3 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures3,EnterpriseType.GENERAL);
				eem.setGovernanceMeasures3(governance3);
			}
		}else{
			eem.setGovernanceMeasures3(null);
		}
		GovernanceMeasures governance4 = null;
		if(StringUtils.isNotBlank(governanceMeasures4) ){
			governance4 = eem.getGovernanceMeasures4();
			if(governance4 != null && governance4.getId() != null){
				governance4.setName(governanceMeasures4);
				eem.setGovernanceMeasures4(governanceMeasuresService.update(governance4));
			}else{
				governance4 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures4,EnterpriseType.GENERAL);
				eem.setGovernanceMeasures4(governance4);
			}
			
		}else{
			eem.setGovernanceMeasures4(null);
		}
        section.setEnterpriseEmissionsManagement(eem);
        
		if(section.getProduct()!=null && section.getProduct().getId() != null){
			section.setProduct(productService.update(section.getProduct()));
		}
		RawMaterials material1 = section.getRawMaterials1();
		if(material1 !=null && material1.getId() != null){
			material1.setSectionId(section.getId());
			section.setRawMaterials1(rawMaterialsService.update(material1));
		}
		RawMaterials material2 = section.getRawMaterials2();
		if(material2!=null && material2.getId() != null){
			material2.setSectionId(section.getId());
			section.setRawMaterials2(rawMaterialsService.update(material2));
		}
		RawMaterials material3 = section.getRawMaterials3();
		if(material3!=null && material3.getId() != null){
			material3.setSectionId(section.getId());
			section.setRawMaterials3(rawMaterialsService.update(material3));
		}
		
		
		
		
		
		return update(section,"enterprise","project"); 
	}

	

	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<DictionaryData> list = new ArrayList<>();
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,SpringUtils.getProjectId()));
		filters.add(new Filter("enterpriseEmissionsManagement.enterprise.id",Operator.eq,SpringUtils.getPrincipal().getEnterpriseId()));
		List<Section> findList = findList(null, filters, null);
		for (Section section : findList) {
			DictionaryData data = new DictionaryData();
			
			if("product".equals(param)){
				data.setCode(section.getId().toString());
				data.setCodeValue(section.getProduct().getName());
			}else{
				data.setCode(section.getId().toString());
				data.setCodeValue(section.getName());
			}
			
			
			list.add(data);
		}
		return list;
	}

    @Override
    public List<Section> findSourceNameById(Integer id) {
        return sectionDao.findSourceNameById(id);
    }

	@Override
	public Section saveGeneralSection(Section section, String governanceMeasures1, String governanceMeasures2,
			String governanceMeasures3, String governanceMeasures4, Date putDate1, Date putDate2, Date putDate3,
			Date putDate4) {
		EnterpriseEmissionsManagement eem = new EnterpriseEmissionsManagement();
		eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		
		if(section.getEnterpriseEmissionsManagement() != null && section.getEnterpriseEmissionsManagement().getExhaustionHole() != null){
			ExhaustionHole exhaustionHole = new ExhaustionHole();
			exhaustionHole.setHeight(section.getEnterpriseEmissionsManagement().getExhaustionHole().getHeight());
			eem.setExhaustionHole(exhaustionHoleService.save(exhaustionHole));
		}
		
		if(StringUtils.isNotBlank(governanceMeasures1)){
			GovernanceMeasures  governance1 = new GovernanceMeasures();
			governance1.setName(governanceMeasures1);
			governance1.setPutDate(putDate1);
			eem.setGovernanceMeasures1(governanceMeasuresService.save(governance1));
		}
		
		if(StringUtils.isNotBlank(governanceMeasures2)){
			GovernanceMeasures  governance2 = new GovernanceMeasures();
			governance2.setName(governanceMeasures2);
			governance2.setPutDate(putDate2);
			eem.setGovernanceMeasures2(governanceMeasuresService.save(governance2));
		}
		if(StringUtils.isNotBlank(governanceMeasures3)){
			GovernanceMeasures  governance3 = new GovernanceMeasures();
			governance3.setName(governanceMeasures3);
			governance3.setPutDate(putDate3);
			eem.setGovernanceMeasures3(governanceMeasuresService.save(governance3));
		}
		if(StringUtils.isNotBlank(governanceMeasures4)){
			GovernanceMeasures  governance4 = new GovernanceMeasures();
			governance4.setName(governanceMeasures4);
			governance4.setPutDate(putDate4);
			eem.setGovernanceMeasures4(governanceMeasuresService.save(governance4));
		}
		section.setEnterpriseEmissionsManagement(eem);
		
		Product product = section.getProduct();
		if (product!=null && product.getName()!=null) {
			section.setProduct(productService.save(product));
		}
		
		section.setProject(SpringUtils.getCurrentProject());
		
		RawMaterials material = null;
		material = section.getRawMaterials1();
		if (material != null && material.getName() != null) {
			section.setRawMaterials1(rawMaterialsService.save(material));
		}
		
		
		return save(section);
	}

	@Override
	public Section updateGeneralSection(Section section, String governanceMeasures1, String governanceMeasures2,
			String governanceMeasures3, String governanceMeasures4, Date putDate1, Date putDate2, Date putDate3,
			Date putDate4) {
		
		
		EnterpriseEmissionsManagement eem = section.getEnterpriseEmissionsManagement();
		if(eem == null ){
			eem = new EnterpriseEmissionsManagement();
		}
		eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		
		if(eem != null && eem.getExhaustionHole() != null){
			if(eem.getExhaustionHole().getId() != null){
				ExhaustionHole exhaustionHole = exhaustionHoleService.find(eem.getExhaustionHole().getId());
				exhaustionHole.setHeight(eem.getExhaustionHole().getHeight());
				eem.setExhaustionHole(exhaustionHole);
			}else{
				eem.setExhaustionHole(null);
			}
			
		}
		
		GovernanceMeasures governance1 = null;
		if(StringUtils.isNotBlank(governanceMeasures1) ){
			governance1 = eem.getGovernanceMeasures1();
			if(governance1 != null && governance1.getId() != null){
				governance1.setName(governanceMeasures1);
				governance1.setPutDate(putDate1);
				eem.setGovernanceMeasures1(governanceMeasuresService.update(governance1));
			}else{
				governance1 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures1,EnterpriseType.GENERAL);
				governance1.setPutDate(putDate1);
				eem.setGovernanceMeasures1(governance1);
			}
			
		}else{
			eem.setGovernanceMeasures1(null);
		}
		
		GovernanceMeasures governance2 = null;
		if(StringUtils.isNotBlank(governanceMeasures2) ){
			governance2 = eem.getGovernanceMeasures2();
			if(governance2 != null && governance2.getId() != null){
				governance2.setName(governanceMeasures2);
				governance2.setPutDate(putDate2);
				eem.setGovernanceMeasures2(governanceMeasuresService.update(governance2));
			}else{
				governance2 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures2,EnterpriseType.GENERAL);
				governance2.setPutDate(putDate2);
				eem.setGovernanceMeasures2(governance2);
			}
			
		}else{
			eem.setGovernanceMeasures2(null);
		}
		GovernanceMeasures governance3 = null;
		if(StringUtils.isNotBlank(governanceMeasures3) ){
			governance3 = eem.getGovernanceMeasures3();
			if(governance3 != null && governance3.getId() != null){
				governance3.setName(governanceMeasures3);
				governance3.setPutDate(putDate3);
				eem.setGovernanceMeasures3(governanceMeasuresService.update(governance3));
			}else{
				governance3 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures3,EnterpriseType.GENERAL);
				governance3.setPutDate(putDate3);
				eem.setGovernanceMeasures3(governance3);
			}
			
		}else{
			eem.setGovernanceMeasures3(null);
		}
		GovernanceMeasures governance4 = null;
		if(StringUtils.isNotBlank(governanceMeasures4) ){
			governance4 = eem.getGovernanceMeasures4();
			if(governance4 != null && governance4.getId() != null){
				governance4.setName(governanceMeasures4);
				governance4.setPutDate(putDate4);
				eem.setGovernanceMeasures4(governanceMeasuresService.update(governance4));
			}else{
				governance4 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures4,EnterpriseType.GENERAL);
				governance4.setPutDate(putDate4);
				eem.setGovernanceMeasures4(governance4);
			}
			
		}else{
			eem.setGovernanceMeasures4(null);
		}
        section.setEnterpriseEmissionsManagement(eem);
        
		if(section.getProduct()!=null && section.getProduct().getId() != null){
			section.setProduct(productService.update(section.getProduct()));
		}
		
		if(section.getRawMaterials1()!=null && section.getRawMaterials1().getId() != null){
			section.setRawMaterials1(rawMaterialsService.update(section.getRawMaterials1()));
		}
		
		
		
		return update(section,"enterprise","project"); 
		
	}

	@Override
	public List<Section> findByEnterpriseid(Integer enterpriseid) {
		//郑有权
		List<Section> lists = findByFilter(new Filter("enterpriseEmissionsManagement.enterprise", Operator.eq, enterpriseid));
		if(lists != null && lists.size()>0){
			return lists;
		}
		return null;
	}

	@Override
	public Section findByEnterpriseidAndProject(Integer enterpriseid, Integer projectId) {
		// TODO Auto-generated method stub
		//郑有权
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id", Operator.eq, projectId));
		filters.add(new Filter("enterpriseEmissionsManagement.enterprise.id", Operator.eq, enterpriseid));
		List<Section> lists = findList(null, filters, null);
		
		
		if(lists != null && lists.size()>0){
			return lists.get(0);
		}
		return null;
	}

	@Override
	public void deleteSection(Integer id) {
		Section entry = find(id);
		
		if(entry != null && entry.getEnterpriseEmissionsManagement() != null){
			
			if(entry.getProduct() != null){
				 productService.delete(entry.getProduct().getId());
			}
			if(entry.getRawMaterials1() != null){
				rawMaterialsService.delete(entry.getRawMaterials1().getId());
			}
			if(entry.getRawMaterials2() != null){
				rawMaterialsService.delete(entry.getRawMaterials2().getId());
			}
			if(entry.getRawMaterials3() != null){
				rawMaterialsService.delete(entry.getRawMaterials3().getId());
			}
			if(entry.getRawMaterials4() != null){
				rawMaterialsService.delete(entry.getRawMaterials4().getId());
			}
			if(entry.getRawMaterials5() != null){
				rawMaterialsService.delete(entry.getRawMaterials5().getId());
			}
			/*if(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures1() != null){
				governanceMeasuresService.delete(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures1().getId());
			}
			if(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures2() != null){
				governanceMeasuresService.delete(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures2().getId());
			}
			if(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures3() != null){
				governanceMeasuresService.delete(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures3().getId());
			}
			if(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures4() != null){
				governanceMeasuresService.delete(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures4().getId());
			}*/
		}	
			
			delete(id);
		
	}
	
}