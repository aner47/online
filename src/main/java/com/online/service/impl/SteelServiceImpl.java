package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.SteelDao;
import com.online.entity.online.Product;
import com.online.entity.online.RawMaterials;
import com.online.entity.online.Steel;
import com.online.service.ProductService;
import com.online.service.RawMaterialsService;
import com.online.service.SteelService;

/**
 * 钢铁信息
 */
@Service("steelServiceImpl")
public class SteelServiceImpl extends BaseServiceImpl<Steel, Integer> implements SteelService {

	@Autowired
	private ProductService productService;

	@Autowired
	private RawMaterialsService rawMaterialsService;
	
	@Autowired
	private SteelDao steelDao;
	
	

	@Override
	public Steel saveSteel(Steel steel) {
		if(steel.getProduct() != null){
			steel.setProduct(productService.save(steel.getProduct()));
		}else{
			steel.setProduct(productService.save(new Product()));
		}
		if(steel.getRawMaterials1() != null){
			steel.setRawMaterials1(rawMaterialsService.save(steel.getRawMaterials1()));
		}else{
			steel.setRawMaterials1(rawMaterialsService.save(new RawMaterials()));
		}
		if(steel.getRawMaterials2() != null){
			steel.setRawMaterials2(rawMaterialsService.save(steel.getRawMaterials2()));
		}else{
			steel.setRawMaterials2(rawMaterialsService.save(new RawMaterials()));
		}
		if(steel.getRawMaterials3() != null){
			steel.setRawMaterials3(rawMaterialsService.save(steel.getRawMaterials3()));
		}else{
			steel.setRawMaterials3(rawMaterialsService.save(new RawMaterials()));
		}
		if(steel.getRawMaterials4() != null){
			steel.setRawMaterials4(rawMaterialsService.save(steel.getRawMaterials4()));
		}else{
			steel.setRawMaterials4(rawMaterialsService.save(new RawMaterials()));
		}
		if(steel.getRawMaterials5() != null){
			steel.setRawMaterials5(rawMaterialsService.save(steel.getRawMaterials5()));
		}else{
			steel.setRawMaterials5(rawMaterialsService.save(new RawMaterials()));
		}
		
		
		
		
		
		

		return save(steel);

	}

	@Override
	public Steel updateSteel(Steel steel) {
//		Steel steelBack = find(steel.getId());
		if(steel.getProduct() != null && steel.getProduct().getId() != null){
		steel.setProduct(productService.update(steel.getProduct()));
		}
		if(steel.getRawMaterials1() != null && steel.getRawMaterials1().getId() != null){
			steel.setRawMaterials1(rawMaterialsService.update(steel.getRawMaterials1()));
		}
		if(steel.getRawMaterials2() != null&& steel.getRawMaterials2().getId() != null){
			steel.setRawMaterials2(rawMaterialsService.update(steel.getRawMaterials2()));
		}
		if(steel.getRawMaterials3() != null&& steel.getRawMaterials3().getId() != null){
			steel.setRawMaterials3(rawMaterialsService.update(steel.getRawMaterials3()));
		}
		if(steel.getRawMaterials4() != null&& steel.getRawMaterials4().getId() != null){
			steel.setRawMaterials4(rawMaterialsService.update(steel.getRawMaterials4()));
		}
		if(steel.getRawMaterials5() != null&& steel.getRawMaterials5().getId() != null){
			steel.setRawMaterials5(rawMaterialsService.update(steel.getRawMaterials5()));
		}
		
		//郑有权
		return update(steel);
	}

	@Override
	public void deleteSteelByEnterpriseId(Integer id) throws Exception {
		//郑有权
		steelDao.deleteSteelByEnterpriseId(id);
	}

}