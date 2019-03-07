package com.online.service.impl;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.Filter;
import com.online.Order;
import com.online.Filter.Operator;
import com.online.dao.KilnDao;
import com.online.entity.online.BoilerInformation;
import com.online.entity.online.ExhaustionHole;
import com.online.entity.online.GovernanceMeasures;
import com.online.entity.online.Kiln;
import com.online.entity.online.Product;
import com.online.entity.online.RawMaterials;
import com.online.entity.online.Enterprise.EnterpriseType;
import com.online.entity.online.embeddable.EnterpriseEmissionsManagement;
import com.online.service.ExhaustionHoleService;
import com.online.service.GovernanceMeasuresService;
import com.online.service.KilnService;
import com.online.service.ProductService;
import com.online.service.RawMaterialsService;
import com.online.util.SpringUtils;
/**
 * 
 * @author 左志平
 * 
 * 窑炉服务实现
 *
 */
@Service("kilnServiceImpl")
public class KilnServiceImpl extends BaseServiceImpl<Kiln, Integer> implements KilnService {
	
	@Autowired
	private GovernanceMeasuresService governanceMeasuresService;
	
	@Autowired
	private ExhaustionHoleService exhaustionHoleService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private RawMaterialsService rawMaterialsService;
	@Autowired
	private KilnDao kilnDao;
	
	@Autowired 
	protected GovernanceMeasuresService governanceMeasurService;
	
	@Override
	@Transactional
	public Kiln saveKiln(Kiln kiln, Integer governanceMeasures1,Integer governanceMeasures2,Integer governanceMeasures3
			,Integer exhaustionHoleId,Integer exhaustionHoleTailId) {
		int no = 1;
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
		filters.add(new Filter("enterpriseEmissionsManagement.enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId()));
		List<Order> listOrder = new ArrayList<>();
		listOrder.add(Order.desc("createDate"));
		List<Kiln> lists = findList(null, filters, null);
		if(lists != null && lists.size()>0){
			if(lists.get(0).getNo() != null){
				no = lists.get(0).getNo()+1;
			}
			
		}
		kiln.setNo(no);
		
		EnterpriseEmissionsManagement eem = kiln.getEnterpriseEmissionsManagement();
		if(eem == null){
			eem = new EnterpriseEmissionsManagement();
		}
		eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		kiln.setEnterpriseEmissionsManagement(eem);
		if(governanceMeasures1 != null){
			eem.setGovernanceMeasures1(governanceMeasuresService.find(governanceMeasures1));
		}
		if(governanceMeasures2 != null){
			eem.setGovernanceMeasures2(governanceMeasuresService.find(governanceMeasures2));
		}
		if(governanceMeasures3 != null){
			eem.setGovernanceMeasures3(governanceMeasuresService.find(governanceMeasures3));
		}
		if(exhaustionHoleId != null){
			eem.setExhaustionHole(exhaustionHoleService.find(exhaustionHoleId));
		}
		if(exhaustionHoleTailId != null){
			kiln.setExhaustionHoleTail(exhaustionHoleService.find(exhaustionHoleTailId));
		}
		
		
		
		
		
		kiln.setEnterpriseEmissionsManagement(eem);
		
		
		Product product = kiln.getProduct();
		if (product!=null && StringUtils.isNotBlank(product.getName())) {
			Product productback = productService.save(product);
			kiln.setProduct(productback);
		}
		
		RawMaterials material = null;
		material = kiln.getRawMaterials1();
		if (material != null && StringUtils.isNotBlank(material.getName())) {
			kiln.setRawMaterials1(rawMaterialsService.save(material));
		}else{
			kiln.setRawMaterials1(null);
		}
		
		material = kiln.getRawMaterials2();
		if (material != null && StringUtils.isNotBlank(material.getName())) {
			kiln.setRawMaterials2(rawMaterialsService.save(material));
		}else{
			kiln.setRawMaterials2(null);
		}
		
		material = kiln.getRawMaterials3();
		if (material != null &&  StringUtils.isNotBlank(material.getName())) {
			kiln.setRawMaterials3(rawMaterialsService.save(material));
		}else{
			kiln.setRawMaterials3(null);
		}
		
		material = kiln.getRawMaterials4();
		if (material != null &&  StringUtils.isNotBlank(material.getName())) {
			kiln.setRawMaterials4(rawMaterialsService.save(material));
		}else{
			kiln.setRawMaterials4(null);
		}
		
		material = kiln.getRawMaterials5();
		if (material != null &&  StringUtils.isNotBlank(material.getName())) {
			kiln.setRawMaterials5(rawMaterialsService.save(material));
		}else{
			kiln.setRawMaterials5(null);
		}
		
		kiln.setProject(SpringUtils.getCurrentProject());
		
		return save(kiln);
	}
	@Override
	@Transactional
	public Kiln generalsave(Kiln kiln, String governanceMeasures1, String governanceMeasures2,
			String governanceMeasures3, String governanceMeasures4, Date putDate1, Date putDate2, Date putDate3,
			Date putDate4) {
		
		EnterpriseEmissionsManagement eem = kiln.getEnterpriseEmissionsManagement();
		if(eem == null){
			eem = new EnterpriseEmissionsManagement();
		}
		eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		kiln.setEnterpriseEmissionsManagement(eem);
		
		if(eem != null && eem.getExhaustionHole() != null){
			ExhaustionHole exhaustionHole = new ExhaustionHole();
			exhaustionHole.setHeight(kiln.getEnterpriseEmissionsManagement().getExhaustionHole().getHeight());
			eem.setExhaustionHole(exhaustionHoleService.save(exhaustionHole));
		}
		if(StringUtils.isNotBlank(governanceMeasures1)){
			GovernanceMeasures  governance1 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures1,EnterpriseType.GENERAL);
			governance1.setPutDate(putDate1);
			eem.setGovernanceMeasures1(governance1);
		}
		
		if(StringUtils.isNotBlank(governanceMeasures2)){
			GovernanceMeasures  governance2 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures2,EnterpriseType.GENERAL);
			governance2.setPutDate(putDate2);
			eem.setGovernanceMeasures2(governance2);
		}
		if(StringUtils.isNotBlank(governanceMeasures3)){
			GovernanceMeasures  governance3 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures3,EnterpriseType.GENERAL);
			governance3.setPutDate(putDate3);
			eem.setGovernanceMeasures3(governance3);
		}
		if(StringUtils.isNotBlank(governanceMeasures4)){
			GovernanceMeasures  governance4 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures4,EnterpriseType.GENERAL);
			governance4.setPutDate(putDate4);
			eem.setGovernanceMeasures4(governance4);
		}
		kiln.setEnterpriseEmissionsManagement(eem);
		
		Product product = kiln.getProduct();
		if (product!=null && product.getName()!=null) {
			kiln.setProduct(productService.save(product));
		}
		
		RawMaterials material = null;
		material = kiln.getRawMaterials1();
		if (material != null && StringUtils.isNotBlank(material.getName())) {
			kiln.setRawMaterials1(rawMaterialsService.save(material));
		}
		
		material = kiln.getRawMaterials2();
		if (material != null && StringUtils.isNotBlank(material.getName())) {
			kiln.setRawMaterials2(rawMaterialsService.save(material));
		}
		
		material = kiln.getRawMaterials3();
		if (material != null && StringUtils.isNotBlank(material.getName())) {
			kiln.setRawMaterials3(rawMaterialsService.save(material));
		}
		
		material = kiln.getRawMaterials4();
		if (material != null && StringUtils.isNotBlank(material.getName())) {
			kiln.setRawMaterials4(rawMaterialsService.save(material));
		}
		
		material = kiln.getRawMaterials5();
		if (material != null && StringUtils.isNotBlank(material.getName())) {
			kiln.setRawMaterials5(rawMaterialsService.save(material));
		}
		
		kiln.setProject(SpringUtils.getCurrentProject());
		
		return save(kiln);
	}

	@Transactional
	@Override
	public Kiln updateKiln(Kiln kiln, Integer governanceMeasures1,Integer governanceMeasures2,Integer governanceMeasures3
			,Integer exhaustionHoleId,Integer exhaustionHoleTailId) {
		
		EnterpriseEmissionsManagement eem = kiln.getEnterpriseEmissionsManagement();
		eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		if (governanceMeasures1!=null) {
			GovernanceMeasures g1 = governanceMeasuresService.find(governanceMeasures1);
			eem.setGovernanceMeasures1(g1);
		}
		if (governanceMeasures2!=null) {
			GovernanceMeasures g2 = governanceMeasuresService.find(governanceMeasures2);
			eem.setGovernanceMeasures2(g2);
		}
		if (governanceMeasures3!=null) {
			GovernanceMeasures g3 = governanceMeasuresService.find(governanceMeasures3);
			eem.setGovernanceMeasures3(g3);
		}
		if (exhaustionHoleId!=null) {
			ExhaustionHole e = exhaustionHoleService.find(exhaustionHoleId);
			eem.setExhaustionHole(e);
		}
		kiln.setEnterpriseEmissionsManagement(eem);
		
		if (exhaustionHoleTailId!=null) {
			ExhaustionHole e = exhaustionHoleService.find(exhaustionHoleTailId);
			kiln.setExhaustionHoleTail(e);
		}
		if(kiln.getProduct()!=null && kiln.getProduct().getId() != null){
			kiln.setProduct(productService.update(kiln.getProduct()));
		}
		
		if(kiln.getRawMaterials1()!=null && kiln.getRawMaterials1().getId() != null){
			kiln.setRawMaterials1(rawMaterialsService.update(kiln.getRawMaterials1()));
		}else if(kiln.getRawMaterials1()!=null && StringUtils.isNotBlank(kiln.getRawMaterials1().getName()) ){
			kiln.setRawMaterials1(rawMaterialsService.save(kiln.getRawMaterials1()));
		}else{
			kiln.setRawMaterials1(null);
		}
		
		if(kiln.getRawMaterials2()!=null && kiln.getRawMaterials2().getId() != null){
			kiln.setRawMaterials2(rawMaterialsService.update(kiln.getRawMaterials2()));
		}else if(kiln.getRawMaterials2()!=null && StringUtils.isNotBlank(kiln.getRawMaterials2().getName()) ){
			kiln.setRawMaterials2(rawMaterialsService.save(kiln.getRawMaterials2()));
		}else{
			kiln.setRawMaterials2(null);
		}
		if(kiln.getRawMaterials3()!=null && kiln.getRawMaterials3().getId() != null){
			kiln.setRawMaterials3(rawMaterialsService.update(kiln.getRawMaterials3()));
		}else if(kiln.getRawMaterials3()!=null && StringUtils.isNotBlank(kiln.getRawMaterials3().getName()) ){
			kiln.setRawMaterials3(rawMaterialsService.save(kiln.getRawMaterials3()));
		}else{
			kiln.setRawMaterials3(null);
		}
		if(kiln.getRawMaterials4()!=null && kiln.getRawMaterials4().getId() != null){
			kiln.setRawMaterials4(rawMaterialsService.update(kiln.getRawMaterials4()));
		}else if(kiln.getRawMaterials4()!=null && StringUtils.isNotBlank(kiln.getRawMaterials4().getName()) ){
			kiln.setRawMaterials4(rawMaterialsService.save(kiln.getRawMaterials4()));
		}else{
			kiln.setRawMaterials4(null);
		}
		if(kiln.getRawMaterials5()!=null && kiln.getRawMaterials5().getId() != null){
			kiln.setRawMaterials5(rawMaterialsService.update(kiln.getRawMaterials5()));
		}else if(kiln.getRawMaterials5()!=null && StringUtils.isNotBlank(kiln.getRawMaterials5().getName()) ){
			kiln.setRawMaterials5(rawMaterialsService.save(kiln.getRawMaterials5()));
		}else{
			kiln.setRawMaterials5(null);
		}
		
		int no = 1;
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
		filters.add(new Filter("enterpriseEmissionsManagement.enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId()));
		List<Order> listOrder = new ArrayList<>();
		listOrder.add(Order.desc("createDate"));
		List<Kiln> lists = findList(null, filters, null);
		if(lists != null && lists.size()>0){
			if(lists.get(0).getNo() != null){
				no = lists.get(0).getNo()+1;
			}
			
		}
		if(kiln.getNo() == null){
			kiln.setNo(no);
		}
		
		

		return update(kiln,"project"); 
	}
	@Transactional
	@Override
	public Kiln updateGeneralKiln(Kiln kiln, String governanceMeasures1, String governanceMeasures2,
    		String governanceMeasures3, String governanceMeasures4, Date putDate1, Date putDate2, Date putDate3,
    		Date putDate4) {
		
		EnterpriseEmissionsManagement eem = kiln.getEnterpriseEmissionsManagement();
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
		
//		GovernanceMeasures  governance2 = eem.getGovernanceMeasures2();
//		governance2.setName(governanceMeasures2);
//		governance2.setPutDate(putDate2);
//		eem.setGovernanceMeasures2(governance2);
//		GovernanceMeasures  governance3 = eem.getGovernanceMeasures3();
//		governance3.setName(governanceMeasures3);
//		governance3.setPutDate(putDate3);
//		eem.setGovernanceMeasures3(governance3);
//		GovernanceMeasures  governance4 = eem.getGovernanceMeasures4();
//		governance4.setName(governanceMeasures4);
//		governance4.setPutDate(putDate4);
//		eem.setGovernanceMeasures4(governance4);
//		kiln.setEnterpriseEmissionsManagement(eem);
		
//		Product product = kiln.getProduct();
//		product.setName(kiln.getProduct().getName());
//		product.setYield(kiln.getProduct().getYield());
//		product.setUnit(kiln.getProduct().getUnit());
//		if(product != null &&StringUtils.isNotBlank(product.getName())){
//			if(product.getId() != null){
//				kiln.setProduct(productService.update(product));
//			}
//			
//		}else{
//			kiln.setProduct(null);
//		}
		if(kiln.getProduct()!=null && kiln.getProduct().getId() != null){
			kiln.setProduct(productService.update(kiln.getProduct()));
		}
		
		
		
		
		RawMaterials material = kiln.getRawMaterials1();
		
//		material.setName(kiln.getRawMaterials1().getName());
//		material.setConsumption(kiln.getRawMaterials1().getConsumption());
//		material.setUnit(kiln.getRawMaterials1().getUnit());
		if(material != null && StringUtils.isNotBlank(material.getName())){
			if(material.getId() != null ){
				kiln.setRawMaterials1(rawMaterialsService.update(material));
			}
			
		}
		
		
		
		return update(kiln,"project"); 
//		update(kiln);
	}

    @Override
    public List<Kiln> findSourceNameById(Integer id) {
        return kilnDao.findSourceNameById(id);
    }
	@Override
	public List<Kiln> findByEnterpriseid(Integer enterpriseid) {
		// TODO Auto-generated method stub
		//郑有权
		List<Kiln> lists = findByFilter(new Filter("enterpriseEmissionsManagement.enterprise.id", Operator.eq, enterpriseid));
		if(lists != null && lists.size()>0){
			return lists;
		}
		return null;
		
	}
	@Override
	public void deleteKiln(Integer id) {
		// TODO Auto-generated method stub
		//郑有权
		Kiln entry = find(id);
		
		
		
		if(entry != null && entry.getEnterpriseEmissionsManagement() != null){
			/*if(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures1() != null){
				governanceMeasurService.delete(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures1().getId());
			}
			if(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures2() != null){
				governanceMeasurService.delete(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures2().getId());
			}
			if(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures3() != null){
				governanceMeasurService.delete(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures3().getId());
			}
			if(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures4() != null){
				governanceMeasurService.delete(entry.getEnterpriseEmissionsManagement().getGovernanceMeasures4().getId());
			}
			if(entry.getEnterpriseEmissionsManagement().getExhaustionHole() != null){
				exhaustionHoleService.delete(entry.getEnterpriseEmissionsManagement().getExhaustionHole().getId());
			}*/
			if(entry.getProduct() != null){
				 productService.delete(entry.getProduct().getId());
			}
			
			delete(id);
		}
	}
	
}