package com.online.controller.admin.enterprisedictionaryrep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Order;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.EnterpriseDictionary;
import com.online.entity.EnterpriseDictionaryRep;
import com.online.service.EnterpriseDictionaryRepService;


@Controller
@RequestMapping("/admin/enterprisedictionaryrep")
public class EnterpriseDictionaryRepController extends BaseController {
	@Autowired
	private EnterpriseDictionaryRepService enterpriseDictionaryRepService;
	
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String queryOPage(){
		return "/admin/enterprisedictionaryrep/list";
	}
	
	
	
	/**
	 * 查询
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/query")
	public @ResponseBody Page<EnterpriseDictionaryRep> query(Pageable pageable,EnterpriseDictionaryRep enterpriseDictionaryRep){
		pageable.setOrderDirection(Order.Direction.asc);
		pageable.setOrderProperty("id");
		Page<EnterpriseDictionaryRep> findPage = enterpriseDictionaryRepService.findPage(pageable);
		return findPage;
	}


	
	
}
