package com.online.controller.admin.dictionary;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.online.service.DictionaryService;


@Controller
@RequestMapping("/admin/dictionary")
public class DictionaryController extends BaseController {
	@Autowired
	private DictionaryService dictionaryService;
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String queryOPage(){
		return "/admin/dictionary/list";
	}
	
	/**
	 * 增加页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(HttpServletRequest request, Pageable pageable) {
		return "/admin/dictionary/add";
	}

	/**
	 * 修改页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,int id) {
		model.put("user", dictionaryService.find(id));
		return "/admin/dictionary/update";
	}
	
	/**
	 * 查询
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/query")
	public @ResponseBody Page<Dictionary> query(Pageable pageable,Dictionary dictionary){
	    String name = dictionary.getName();
        if (StringUtils.isNotEmpty(name)) {
            pageable.addFilter("name", Operator.like, "%"+name.trim()+"%");
        }
		Page<Dictionary> findPage = dictionaryService.findPage(pageable);
		return findPage;
	}


	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Dictionary dictionary) {
		dictionary.setEnabled(true);
		dictionaryService.save(dictionary);
		return Message.success();
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Dictionary dictionary) {
		dictionaryService.update(dictionary);
		return Message.success();
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(int... ids) {
		for (int i = 0; i < ids.length; i++) {
			dictionaryService.delete(ids[i]);
		}

		return Message.success();
	}
	
}
