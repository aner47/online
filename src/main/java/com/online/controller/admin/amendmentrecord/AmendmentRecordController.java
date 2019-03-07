package com.online.controller.admin.amendmentrecord;

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
import com.online.entity.online.AmendmentRecord;
import com.online.service.AmendmentRecordService;
import com.online.util.SpringUtils;

@Controller
@RequestMapping("/admin/amendmentrecord")
public class AmendmentRecordController extends BaseController {

	@Autowired
	private AmendmentRecordService amendmentRecordService;

	@RequestMapping("/list")
	public String list() {
		return "/admin/amendmentrecord/list";
	}

	/**
	 * 增加修改记录
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/amendmentrecord/add";
	}

	/**
	 * 修改记录
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model, Integer id) {
		model.put("amendmentrecord", amendmentRecordService.find(id));
		return "/admin/amendmentrecord/update";
	}

	/**
	 * 查看
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model, Integer id) {
		model.put("amendmentrecord", amendmentRecordService.find(id));
		return "/admin/amendmentrecord/view";
	}

	/**
	 * 查询
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<AmendmentRecord> query(Pageable pageable, AmendmentRecord amendmentRecord) {
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter("enterprise", Operator.eq, SpringUtils.getCurrentEnterprise().getId());
		
		String name = amendmentRecord.getModifier();
		String description = amendmentRecord.getDescription();
		if (StringUtils.isNotEmpty(name)) {
			pageable.addFilter("modifier", Operator.like, "%" + name.trim() + "%");
		}
		if (StringUtils.isNotEmpty(description)) {
			pageable.addFilter("description", Operator.like, "%" + description.trim() + "%");
		}
		return amendmentRecordService.findPage(pageable);

	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(AmendmentRecord amendmentrecord) {
		amendmentRecordService.save(amendmentrecord);
		return Message.success();
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(AmendmentRecord amendmentRecord) {

		amendmentRecordService.update(amendmentRecord);
		return Message.success();
	}

	/**
	 * 删除角色
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (Integer id : ids) {
			amendmentRecordService.delete(id);
		}
		return Message.success();
	}

}
