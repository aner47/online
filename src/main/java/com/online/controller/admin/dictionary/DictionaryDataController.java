package com.online.controller.admin.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.Dictionary;
import com.online.entity.DictionaryData;
import com.online.service.DictionaryDataService;
import com.online.service.DictionaryService;



@Controller
@RequestMapping("/admin/dictionarydata")
public class DictionaryDataController extends BaseController {
	@Autowired
	private DictionaryDataService dictionaryDataService;
	@Autowired
	private DictionaryService dictionaryService;
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String list(ModelMap model,String dictionaryId){
		model.put("dictionaryId", dictionaryId);
		return "/admin/dictionarydata/list";
	}
	
	/**
	 * 增加页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model,long dictionaryId) {
		model.put("dictionaryId", dictionaryId);
		return "/admin/dictionarydata/add";
	}

	/**
	 * 修改页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,int id) {
		model.put("dictionaryData", dictionaryDataService.find(id));
		return "/admin/dictionarydata/update";
	}
	
	/**
	 * 查询
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/query")
	public @ResponseBody Page<DictionaryData> query(Pageable pageable,int dictionaryId){
			pageable.addFilter("dictionary.id",Operator.eq,dictionaryId);
		Page<DictionaryData> findPage = dictionaryDataService.findPage(pageable);
		return findPage;
	}


	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(DictionaryData dictionaryData,int dictionaryId) {
		Dictionary dictionary = dictionaryService.find(dictionaryId);
		dictionaryData.setDictionary(dictionary);
		dictionaryDataService.save(dictionaryData);
		return Message.success();
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(DictionaryData dictionaryData) {
		dictionaryDataService.update(dictionaryData,"dictionary");
		return Message.success();
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(int...ids) {
		for (int i = 0; i < ids.length; i++) {
			dictionaryDataService.delete(ids[i]);
		}

		return Message.success();
	}
	
}
