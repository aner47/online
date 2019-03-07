package com.online.controller.web.enterprisedictionary;

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
import com.online.entity.EnterpriseDictionary;
import com.online.service.EnterpriseDictionaryService;
import com.online.util.Constants;
import com.online.util.SpringUtils;


@Controller("webEnterpriseDictionaryController")
@RequestMapping("/web/enterprisedictionary")
public class EnterpriseDictionaryController extends BaseController {
	@Autowired
	private EnterpriseDictionaryService enterpriseDictionaryService;
	
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String queryOPage(){
		return "/web/enterprisedictionary/list";
	}
	
	/**
	 * 增加页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(HttpServletRequest request, Pageable pageable) {
		return "/web/enterprisedictionary/add";
	}

	/**
	 * 修改页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,int id) {
		model.put("enterpriseDictionary", enterpriseDictionaryService.find(id));
		return "/web/enterprisedictionary/update";
	}
	
	
	/**
	 * 查询
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/query")
	public @ResponseBody Page<EnterpriseDictionary> query(Pageable pageable,EnterpriseDictionary enterpriseDictionary){
	    String name = enterpriseDictionary.getEnterpriseName();
        if (StringUtils.isNotEmpty(name)) {
            pageable.addFilter("enterpriseName", Operator.like, "%"+name.trim()+"%");
        }
        pageable.addFilter("projectId",Operator.eq,SpringUtils.getProjectId());
        pageable.addFilter("source", Operator.eq, SpringUtils.getCurrentSystemUser().getUsername());
		Page<EnterpriseDictionary> findPage = enterpriseDictionaryService.findPage(pageable);
		return findPage;
	}


	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(EnterpriseDictionary enterpriseDictionary) {
		enterpriseDictionary.setProjectId(SpringUtils.getProjectId());
		enterpriseDictionary.setSource(SpringUtils.getCurrentSystemUser().getUsername());
		enterpriseDictionary.setStatus(Constants.ENTERPRISE_DICTIONARY_STATUS_CHECKPENDING);
		enterpriseDictionaryService.saveEntity(enterpriseDictionary);
		return Message.success();
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(EnterpriseDictionary enterpriseDictionary) {
		enterpriseDictionary.setStatus(Constants.ENTERPRISE_DICTIONARY_STATUS_CHECKPENDING);
		enterpriseDictionaryService.update(enterpriseDictionary,"projectId","source");
		return Message.success();
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(int... ids) {
		for (int i = 0; i < ids.length; i++) {
			enterpriseDictionaryService.deleteEntity(ids[i]);
		}

		return Message.success();
	}
	
	
	
}
