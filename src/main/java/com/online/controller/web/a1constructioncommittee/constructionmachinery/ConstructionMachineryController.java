package com.online.controller.web.a1constructioncommittee.constructionmachinery;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter;
import com.online.Message;
import com.online.Order;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.online.a1constructioncommittee.ConstructionMachinery;
import com.online.service.a1constructioncommittee.ConstructionMachineryService;
import com.online.util.SpringUtils;



@Controller("webConstructionMachineryController")
@RequestMapping("/web/constructionmachinery")
public class ConstructionMachineryController extends BaseController{
	
	@Autowired
	private ConstructionMachineryService constructionMachineryService;
	
	
	
	
	@RequestMapping("/list")
	public String list(ModelMap model){
		model.put("constructionMachinery", constructionMachineryService.findList(null, Arrays.asList(Filter.eq("project.id", SpringUtils.getProjectId())), Arrays.asList(Order.asc("id"))));
		return "/admin/a1constructioncommittee/constructionmachinery/list";
	}
	
	
	/**
	 * 增加界面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		return "/admin/a1constructioncommittee/constructionmachinery/add";
	}

	
	
	/**
	 * 修改界面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id) {
		model.put("constructionMachinery", constructionMachineryService.find(id));
		return "/admin/a1constructioncommittee/constructionmachinery/update";
	}
	
	/**
	 * 查看界面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("constructionMachinery", constructionMachineryService.find(id));
		return "/admin/a1constructioncommittee/constructionmachinery/view";
	}
	
	/**
	 * 查询
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ConstructionMachinery> query(Pageable pageable,ConstructionMachinery constructionMachinery) {
		return constructionMachineryService.findPage(pageable);
		
	}
	
	public static class ConstructionMachineryFrom{
		List<ConstructionMachinery> constructionMachinerys;

		public List<ConstructionMachinery> getConstructionMachinerys() {
			return constructionMachinerys;
		}

		public void setConstructionMachinerys(List<ConstructionMachinery> constructionMachinerys) {
			this.constructionMachinerys = constructionMachinerys;
		}
		
		
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(ConstructionMachineryFrom constructionMachineryFrom) {
		try {
			System.out.println(constructionMachineryFrom.getConstructionMachinerys().size());
			List<ConstructionMachinery> lists = constructionMachineryFrom.getConstructionMachinerys();
			if(lists != null && lists.size()>0){
				for(ConstructionMachinery constructionMachinery:lists){
					if(constructionMachinery.getId() == null){
						constructionMachineryService.saveConstructionMachinery(constructionMachinery);
					}else{
						constructionMachineryService.updateConstructionMachinery(constructionMachinery);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Message.success();
	}
	
	
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(ConstructionMachinery constructionMachinery) {
		try {
			constructionMachineryService.updateConstructionMachinery(constructionMachinery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Message.success();
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			constructionMachineryService.delete(ids[i]);
		}
		return Message.success();
	}
	
		
}
