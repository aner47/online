package com.online.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.online.MonthlyInformation;
import com.online.entity.online.RawMaterials;
import com.online.entity.online.Section;
import com.online.service.MonthlyInformationService;
import com.online.service.RawMaterialsService;
import com.online.service.SectionService;
/**
 * 
 * @author 左志平
 * 
 * 原辅料服务实现
 *
 */
@Service("rawMaterialsServiceImpl")
public class RawMaterialsServiceImpl extends BaseServiceImpl<RawMaterials, Integer> implements RawMaterialsService {

	@Autowired
	@Lazy
	private SectionService sectionService;
	
	
	@Autowired
	private MonthlyInformationService monthlyInformationService;
	
	@Override
	public List<RawMaterials> findByEnterpriseid(Integer enterpriseId) {
		List<RawMaterials> lists = findByFilter(new Filter("enterprise", Operator.eq, enterpriseId));
		if(lists != null && lists.size()>0){
			return lists;
		}
		return null;
	}

	@Override
	public RawMaterials saveEnter(RawMaterials rawMaterials) {
		//郑有权
		Section section = sectionService.find(rawMaterials.getSectionId());
		RawMaterials rawMaterialsback = save(rawMaterials);
		if(section != null){
			if(section.getRawMaterials1() == null){
				section.setRawMaterials1(rawMaterialsback);
			}else if(section.getRawMaterials2() == null){
				section.setRawMaterials2(rawMaterialsback);
			}else if(section.getRawMaterials3() == null){
				section.setRawMaterials3(rawMaterialsback);
			}else if(section.getRawMaterials4() == null){
				section.setRawMaterials4(rawMaterialsback);
			}else if(section.getRawMaterials5() == null){
				section.setRawMaterials5(rawMaterialsback);
			}
		}
		
		return rawMaterialsback;
	}

	@Override
	public RawMaterials updateEnter(RawMaterials rawMaterials) {
		//如果是更新原辅料，先获取旧section，把原辅料置为null
		Integer id = rawMaterials.getId();
		Integer sectionId = find(id).getSectionId();
		Section section = sectionService.find(sectionId);
		if(section !=  null){
			if(section.getRawMaterials1() !=null && id.equals(section.getRawMaterials1().getId())){
				section.setRawMaterials1(null);
			}else if(section.getRawMaterials2() !=null &&id.equals(section.getRawMaterials2().getId())){
				section.setRawMaterials2(null);
			}else if(section.getRawMaterials3() !=null &&id.equals(section.getRawMaterials3().getId())){
				section.setRawMaterials3(null);
			}else if(section.getRawMaterials4() !=null &&id.equals(section.getRawMaterials4().getId())){
				section.setRawMaterials4(null);
			}else if(section.getRawMaterials5() !=null &&id.equals(section.getRawMaterials5().getId())){
				section.setRawMaterials5(null);
			}
		}
		
		RawMaterials rawMaterialsback = update(rawMaterials);
		
		Section sectionNew = sectionService.find(rawMaterials.getSectionId());
		
		if(sectionNew != null){
			if(sectionNew.getRawMaterials1() == null){
				sectionNew.setRawMaterials1(rawMaterialsback);
			}else if(sectionNew.getRawMaterials2() == null){
				sectionNew.setRawMaterials2(rawMaterialsback);
			}else if(sectionNew.getRawMaterials3() == null){
				sectionNew.setRawMaterials3(rawMaterialsback);
			}else if(sectionNew.getRawMaterials4() == null){
				sectionNew.setRawMaterials4(rawMaterialsback);
			}else if(sectionNew.getRawMaterials5() == null){
				sectionNew.setRawMaterials5(rawMaterialsback);
			}
		}
		
		
		
		
		//郑有权
		return rawMaterialsback;
	}

	@Override
	public RawMaterials updateSimpleEnter(RawMaterials rawMaterials) {
		//郑有权
		return update(rawMaterials,"rawMaterialsType");
	}

	@Override
	@Transactional
	public void deleteEnter(Integer id) {
		//郑有权 
		RawMaterials rawMaterials = find(id);
		Section section = sectionService.find(rawMaterials.getSectionId());
		if(section != null){
			if(section.getRawMaterials1() !=null && id.equals(section.getRawMaterials1().getId())){
				section.setRawMaterials1(null);
			}else if(section.getRawMaterials2() !=null &&id.equals(section.getRawMaterials2().getId())){
				section.setRawMaterials2(null);
			}else if(section.getRawMaterials3() !=null &&id.equals(section.getRawMaterials3().getId())){
				section.setRawMaterials3(null);
			}else if(section.getRawMaterials4() !=null &&id.equals(section.getRawMaterials4().getId())){
				section.setRawMaterials4(null);
			}else if(section.getRawMaterials5() !=null &&id.equals(section.getRawMaterials5().getId())){
				section.setRawMaterials5(null);
			}
			
		}
		
		List<MonthlyInformation> month = monthlyInformationService.findByFilter(new Filter("rawMaterialsId", Operator.eq, rawMaterials.getId()));
		if(month != null){
			for(MonthlyInformation monthlyInformation:month){
				monthlyInformationService.delete(monthlyInformation);
			}
		}
		
		delete(rawMaterials);
	}
	
}
