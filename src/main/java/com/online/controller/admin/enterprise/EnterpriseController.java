	package com.online.controller.admin.enterprise;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.hibernate.validator.internal.util.privilegedactions.GetResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.DocumentException;
import com.online.DataAccessException;
import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Message;
import com.online.Order;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.Token;
import com.online.controller.base.BaseController;
import com.online.entity.Area;
import com.online.entity.ProjectEnterpriseUser;
import com.online.entity.SystemRole;
import com.online.entity.online.Condition;
import com.online.entity.online.Enterprise;
import com.online.entity.online.PhotoFile;
import com.online.entity.online.Project;
import com.online.exportpdf.PDFTempletExportService;
import com.online.service.AreaService;
import com.online.service.EnterpriseBackupService;
import com.online.service.EnterpriseService;
import com.online.service.KilnService;
import com.online.service.PhotoFileService;
import com.online.service.ProjectEnterpriseUserService;
import com.online.service.ProjectService;
import com.online.service.ProjectTypeService;
import com.online.service.RawMaterialsService;
import com.online.service.SectionService;
import com.online.service.SystemMenuConfigService;
import com.online.util.Constants;
import com.online.util.DateHelper;
import com.online.util.JsonUtil;
import com.online.util.Principal;
import com.online.util.SpringUtils;

/**
 * @author 作者名
 * @email 邮箱名
 * @time 2017年4月26日 下午2:15:47
 */
@Controller("enterpriseController")
@RequestMapping("/admin/enterprise")
public class EnterpriseController extends BaseController {

	@Autowired
	protected EnterpriseService enterpriseService;
	@Autowired
	protected EnterpriseBackupService enterpriseBackupService;

	@Autowired
	private ProjectService projectService;
	@Autowired
	private KilnService kilnService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private RawMaterialsService rawMaterialsService;

	@Autowired
	@Lazy
	private ProjectEnterpriseUserService projectEnterpriseUserService;

	@Autowired
	private PDFTempletExportService pdfTempletExportService;

	static List<String> imgSuffix = Arrays.asList("JPG", "PNG", "GIF", "JPEG");

	@Autowired
	private ProjectTypeService projectTypeService;

	@Autowired
	private SystemMenuConfigService systemMenuConfigService;

	@Autowired
	protected AreaService areaService;
	
	@Autowired
	protected PhotoFileService photoFileService;
	
	

	@RequestMapping("/list")
	public String list(ModelMap model) {
		/*
		 * Principal attribute = (Principal) ((ServletRequestAttributes)
		 * RequestContextHolder.getRequestAttributes())
		 * .getRequest().getSession().getAttribute(Constants.SESSION_USERNAME);
		 */
		Principal principal = SpringUtils.getPrincipal();
		model.put("principal", principal);
		return "/admin/enterprise/adminList";
	}

	@RequestMapping("/statistics")
	public String statistics() {
		return "/admin/enterprise/statistics";
	}

	/**
	 * 统计
	 */
	@RequestMapping(value = "/statisticsQuery")
	@ResponseBody
	public Map<String, List<Map<String, Object>>> statisticsQuery(Condition condition, Integer projectId, Date start,
			Date end, String enterpriseType) {

		Map<String, List<Map<String, Object>>> returnMap = new HashMap<>();

		returnMap.put("county_name", enterpriseService.findStatistics(projectId, 1, start, end, enterpriseType));

		return returnMap;
	}

	/**
	 * 增加企业页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage() {
		return "/admin/enterprise/add";
	}

	/**
	 * 修改企业页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model, Integer enterpriseId, Integer id) {
		model.put("enterprise", enterpriseService.find(enterpriseId));
		model.put("projectEnterpriseUser", projectEnterpriseUserService.find(id));
		ProjectEnterpriseUser projectEnterpriseUser = projectEnterpriseUserService.find(id);
		Integer projectType = null;
		if(projectEnterpriseUser != null){
			projectType = projectEnterpriseUser.getProjectType();
		}
		Project project = SpringUtils.getCurrentProject();
		model.put("project", project);
		if(StringUtils.isNotBlank(project.getSysProductIndustrys())){
			model.put("sysProductIndustrys", project.getSysProductIndustrys());
		}
				
		model.put("projectType", projectType);
		model.put("projectId", SpringUtils.getProjectId());
		return "/admin/enterprise/update";
	}

	/**
	 * 查看企业页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model, Integer id) {
		model.put("enterprise", enterpriseService.find(id));
		return "/admin/enterprise/view";
	}

	/**
	 * 查询企业
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ProjectEnterpriseUser> query(Pageable pageable, Enterprise enterprise, Integer account,
			Integer projectCode, String name, String industryCategoryCodeMain, String industryCategoryNameMain,
			String industryCategoryCodeMiddle, String industryCategoryNameMiddle, String enterpriseType,
			String putOnTime, String stopTime, String enterpriseTask, String modifyDate) {
		// pageable.addOrder("modifyDate", Direction.desc);
		pageable.addOrder(Order.asc("modifyDate"));

		Set<SystemRole> systemRoles = SpringUtils.getCurrentSystemUser().getRoles();
		// 当前用户角色是否包含“任务审核员分部”，包含则查询分部的任务
		boolean isfalg = false;
		for (SystemRole systemRole : systemRoles) {
			if ((Constants.SOLE_ASSESSOR).equals(systemRole.getName())) {
				isfalg = true;
			}
		}
		if (isfalg) {
			pageable.addFilter("assessor", Operator.eq, SpringUtils.getUserId());
		}

		if (projectCode != null) {
			Project project = projectService.find(projectCode);
			if (project != null) {
				Principal attribute = SpringUtils.getPrincipal();
				attribute.setProejctId(project.getId());

				SpringUtils.getRequest().getSession().setAttribute(Constants.SESSION_USERNAME,
						JsonUtil.toJSON(attribute));

				/*
				 * Principal attribute = (Principal) ((ServletRequestAttributes)
				 * RequestContextHolder.getRequestAttributes())
				 * .getRequest().getSession().getAttribute(Constants.
				 * SESSION_USERNAME); attribute.setProejctId(project.getId());
				 */
				pageable.addFilter("project.id", Operator.eq, project.getId());
			}

		} else {
			return new Page<>();
		}

		if (account != null) {
			pageable.addFilter("user.id", Operator.eq, account);
		}
		if (StringUtils.isNotEmpty(enterpriseTask)) {
			pageable.addFilter("taskStatus", Operator.eq, enterpriseTask);
		}

		if (StringUtils.isNotEmpty(name)) {
			if(name.contains("_")){
				name = name.replace("_", "\\_");
			}
			pageable.addFilter("enterprise.name", Operator.like, "%" + name + "%");
		}
		if (StringUtils.isNotEmpty(modifyDate)) {
			List<Date> arr = new ArrayList<>();
			arr.add(DateHelper.getStartDateInterval(modifyDate, -1));
			arr.add(DateHelper.getStartDateInterval(modifyDate, 1));
			pageable.addFilter("modifyDate", Operator.between, arr);
		}
		if (StringUtils.isNotEmpty(putOnTime) && StringUtils.isNotEmpty(stopTime)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				List<Date> arr = new ArrayList<>();
				arr.add(simpleDateFormat.parse(putOnTime));
				arr.add(simpleDateFormat.parse(stopTime));
				pageable.addFilter("enterprise.modifyDate", Operator.between, arr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (StringUtils.isNotEmpty(industryCategoryCodeMain)) {
			pageable.addFilter("enterprise.industryCategoryCodeMain", Operator.eq, industryCategoryCodeMain);
		}
		if (StringUtils.isNotEmpty(industryCategoryNameMain)) {
			pageable.addFilter("enterprise.industryCategoryNameMain", Operator.like,
					"%" + industryCategoryNameMain + "%");
		}
		if (StringUtils.isNotEmpty(industryCategoryCodeMiddle)) {
			pageable.addFilter("enterprise.industryCategoryCodeMiddle", Operator.eq, industryCategoryCodeMiddle);
		}
		if (StringUtils.isNotEmpty(industryCategoryNameMiddle)) {
			pageable.addFilter("enterprise.industryCategoryNameMiddle", Operator.like,
					"%" + industryCategoryNameMiddle + "%");
		}
		if (enterprise != null && enterprise.getAddress() != null && enterprise.getAddress().getProvinces() != null
				&& StringUtils.isNotEmpty(enterprise.getAddress().getProvinces().getCode())) {
			pageable.addFilter("enterprise.address.provinces.code", Operator.eq,
					enterprise.getAddress().getProvinces().getCode());
		}
		if (enterprise != null && enterprise.getAddress() != null && enterprise.getAddress().getCity() != null
				&& StringUtils.isNotEmpty(enterprise.getAddress().getCity().getCode())) {
			pageable.addFilter("enterprise.address.city.code", Operator.eq,
					enterprise.getAddress().getCity().getCode());
		}
		if (enterprise != null && enterprise.getAddress() != null && enterprise.getAddress().getCounty() != null
				&& StringUtils.isNotEmpty(enterprise.getAddress().getCounty().getCode())) {
			pageable.addFilter("enterprise.address.county.code", Operator.eq,
					enterprise.getAddress().getCounty().getCode());
		}
		if (StringUtils.isNotEmpty(enterpriseType)) {
			pageable.addFilter("enterprise.enterpriseType", Operator.eq, enterprise.getEnterpriseType());
		}

		return projectEnterpriseUserService.findPage(pageable);

	}

	/**
	 * 保存企业
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Enterprise enterprise) {
		enterpriseService.save(enterprise);
		return Message.success();
	}

	/**
	 * 删除企业
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			try {
				enterpriseService.deleteByEnterpriseId(ids[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Message.success();
	}

	/**
	 * 选择完企业后保存企业Id进session
	 * 
	 * @param enterpriseId
	 */
	@RequestMapping("/cacheEnterpriseId")
	public @ResponseBody Message cacheEnterpriseId(Integer enterpriseId) {

		// Enterprise enterprise = enterpriseService.find(enterpriseId);

		/*
		 * Principal attribute = (Principal) ((ServletRequestAttributes)
		 * RequestContextHolder.getRequestAttributes())
		 * .getRequest().getSession().getAttribute(Constants.SESSION_USERNAME);
		 * attribute.setEnterpriseId(enterpriseId);
		 */

		Principal attribute = SpringUtils.getPrincipal();
		attribute.setEnterpriseId(enterpriseId);

		SpringUtils.getRequest().getSession().setAttribute(Constants.SESSION_USERNAME, JsonUtil.toJSON(attribute));

		return Message.success();
	}

	/**
	 * 根据行业查询菜单
	 */
	@RequestMapping(value = "findMenuByIndustry")
	@ResponseBody
	public Message findMenuByIndustry(String industryCategoryId) {

		return Message.success(null);
	}

	@RequestMapping("/enterpriseTaskList")
	public String enterpriseTaskList(ModelMap model) {
		// Project project = SpringUtils.getCurrentProject();
		String role = "";
		boolean isfalg = false;
		for (SystemRole systemRole : SpringUtils.getCurrentSystemUser().getRoles()) {
			if ((Constants.ADMIN_SOLE_ASSESSOR).equals(systemRole.getName())) {
				isfalg = true;
			}
		}
		if (isfalg) {
			role = Constants.ADMIN_SOLE_ASSESSOR;
		}
		model.put("role", role);
		model.put("userId", SpringUtils.getUserId());
		return "/admin/enterprise/enterpriseTaskList";
	}

	@RequestMapping("/enterpriseAlreadyTaskList")
	public String enterpriseAlreadyTaskList(ModelMap model) {
		/*
		 * Project project = SpringUtils.getCurrentProject();
		 * model.put("project", project);
		 */
		String role = "";
		boolean isfalg = false;
		for (SystemRole systemRole : SpringUtils.getCurrentSystemUser().getRoles()) {
			if ((Constants.ADMIN_SOLE_ASSESSOR).equals(systemRole.getName())) {
				isfalg = true;
			}
		}
		if (isfalg) {
			role = Constants.ADMIN_SOLE_ASSESSOR;
		}
		model.put("role", role);
		model.put("userId", SpringUtils.getUserId());
		return "/admin/enterprise/enterpriseAlreadyTaskList";
	}

	/**
	 * 审核企业页面
	 */
	@RequestMapping(value = "auditPage")
	public String auditPage(ModelMap model, Integer enterpriseId) {
		model.put("enterpriseId", enterpriseId);
		return "/admin/enterprise/auditPage";
	}

	/**
	 * 审核企业页面
	 */
	@RequestMapping(value = "enterpriseAuditPage")
	public String enterpriseAuditPage(ModelMap model, Integer enterpriseId, Integer projectCode, String enterpriseIds) {
		model.put("enterpriseId", enterpriseId);
		model.put("projectCode", projectCode);
		model.put("enterpriseIds", enterpriseIds);
		return "/admin/enterprise/enterpriseAuditPage";
	}

	/**
	 * 审核企业页面请求详情
	 */
	@RequestMapping(value = "auditPageQuery")
	@ResponseBody
	public Map<String, Object> auditPageQuery(ModelMap model, Integer enterpriseId) {

		Map<String, Object> returnMap = new HashMap<>();

		returnMap.put("enterprise", enterpriseService.find(enterpriseId));
		returnMap.put("kiln", kilnService.findByEnterpriseid(enterpriseId));
		returnMap.put("section", sectionService.findByEnterpriseid(enterpriseId));
		returnMap.put("rawMaterials", rawMaterialsService.findByEnterpriseid(enterpriseId));

		return returnMap;
	}

	/**
	 * 审核，修改企业状态
	 */
	@RequestMapping(value = "/updatestatus")
	@ResponseBody
	public Message updatestatus(String projectId, Integer enterpriseId, Integer status, String opinion,
			Integer nextEnterpriseId) {
		Project project = SpringUtils.getCurrentProject();
		ProjectEnterpriseUser  projectEnterpriseUser  = projectEnterpriseUserService.findProjectTypeByProjectIdAndEnterpriseId(project.getId(), enterpriseId);
		if(projectEnterpriseUser != null){
			if(status == Constants.ENTERPRISE_STATUS_NOTSUBMIT || (Constants.STR_ENTERPRISE_STATUS_ALREADYSUBMIT).equals(projectEnterpriseUser.getTaskStatus())){
				enterpriseService.updatestatus(project.getId(), enterpriseId, status, opinion);
				return Message.success();
			}else{
				return Message.error("非提交状态不能审核", null);
			}
		}
		return Message.error("任务错误", null);
		
		
		
	}

	/**
	 * pdf 是否配置
	 * 
	 * @throws DocumentException
	 * @throws IOException
	 * @throws DataAccessException
	 */
	@RequestMapping(value = "/isExportpdf")
	@ResponseBody
	public Message isExportpdf(HttpServletRequest request, HttpServletResponse response, Integer enterpriseId) {

		try {
			Integer projectId = SpringUtils.getProjectId();
			Enterprise enterprise = enterpriseService.find(enterpriseId);
			pdfTempletExportService.exportPdf(enterprise, projectId);
		} catch (DataAccessException e) {
			// e.printStackTrace();
			return Message.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			return Message.error("模板错误");
		} catch (DocumentException e) {
			e.printStackTrace();
			return Message.error("模板错误");
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("模板错误");
		}

		return Message.success();
	}

	/**
	 * 下载pdf
	 * 
	 * @throws DocumentException
	 * @throws IOException
	 * @throws DataAccessException
	 */
	@RequestMapping(value = "/exportpdf")
	@ResponseBody
	public Message exportpdf(HttpServletRequest request, HttpServletResponse response, Integer enterpriseId,String type)
			throws IOException, DocumentException {

		try {
			Enterprise enterprise = enterpriseService.find(enterpriseId);

			Integer projectId = SpringUtils.getProjectId();

			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			
			
			
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String dateStr = dateformat.format(System.currentTimeMillis());
			String filename = new String((enterprise.getName()).getBytes("GB2312"), "iso8859-1") + "_" + dateStr
					+ ".pdf";
			//如果是预览
			if("preview".equals(type)){
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "inline;filename=" + filename);
			}else{
				//下载
				response.setContentType("octets/stream");
				response.addHeader("Content-Disposition", "attachment;filename=" + filename);
			}

			boolean isFinish = false;
			ProjectEnterpriseUser projectEnterpriseUser = projectEnterpriseUserService.findProjectTypeByProjectIdAndEnterpriseId(projectId, enterpriseId);
			if(Constants.STR_ENTERPRISE_STATUS_APPROVE.equals(projectEnterpriseUser.getTaskStatus())){
				isFinish =  true;
			}
			
			
			List<Enterprise> enterprises = new ArrayList<Enterprise>();
			enterprises.add(enterprise);
			pdfTempletExportService.exprotAllEnterprise(response.getOutputStream(), projectId, "",
					Constants.PDF_PASSWORD, GetResource.class.getClassLoader().getResource("test.png").getPath(),
					Constants.PDF_WATERCHAR, enterprises,isFinish);
		} catch (DataAccessException e) {
			// e.printStackTrace();
			return Message.error(e.getMessage(), null);
		}

		return Message.success();
	}

	/**
	 * 查询所有已提交企业id
	 */
	@RequestMapping(value = "/queryall")
	@ResponseBody
	public List<ProjectEnterpriseUser> queryall() {

		Project project = SpringUtils.getCurrentProject();
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id", Operator.eq, project.getId()));
		filters.add(new Filter("taskStatus", Operator.eq, Constants.STR_ENTERPRISE_STATUS_ALREADYSUBMIT));

		return projectEnterpriseUserService.findList(null, filters, null);
	}

	/**
	 * 根据项目类型查询菜单
	 */
	@RequestMapping(value = "findMenuByProjectType")
	@ResponseBody
	public Message findMenuByProjectType(Integer projectTypeId) {
		if (projectTypeService.find(projectTypeId) != null) {
			String menus = projectTypeService.find(projectTypeId).getMenus();
			String menusBack = systemMenuConfigService.findEndMenus(menus);
			return Message.success(menusBack);
		} else {
			return Message.error("未配置项目类型无菜单");
		}

	}

	@Token(save = true)
	@RequestMapping(value = "dialogEdit")
	public String dialogEdit(ModelMap model, Integer id) {
		if (id == null) {
			model.put("enterprise", enterpriseService.findByAccount(SpringUtils.getUserId()));
		} else {
			model.put("enterprise", enterpriseService.find(id));
		}
		Project project = SpringUtils.getCurrentProject();
		model.put("project", project);
		ProjectEnterpriseUser projectEnterpriseUser = projectEnterpriseUserService
				.findProjectTypeByProjectIdAndEnterpriseId(SpringUtils.getCurrentProject().getId(), id);
		Integer projectType = null;
		if (projectEnterpriseUser != null) {
			projectType = projectEnterpriseUser.getProjectType();
		}
		if (StringUtils.isNotBlank(project.getSysProductIndustrys())) {
			model.put("sysProductIndustrys", project.getSysProductIndustrys());
		}

		model.put("projectType", projectType);

		return "/admin/enterprise/dialogEdit";
	}

	/**
	 * 更新企业
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Enterprise enterprise, Integer projectType) {

		boolean exists;
		try {
			exists = enterpriseService.isexist(enterprise);
			if (exists) {
				return Message.error("企业存在");
			} else {
				Enterprise enterpriseback = enterpriseService.find(enterprise.getId());
				setEnterpriseAddress(enterprise);
				Project project = projectService.find(SpringUtils.getCurrentProject().getId());
				Set<Project> projects = enterpriseback.getProjects();
				projects.add(project);
				enterprise.setProjects(projects);
				enterpriseback = enterpriseService.updateEnterprise(enterprise);
				ProjectEnterpriseUser projectEnterpriseUser = projectEnterpriseUserService
						.findProjectTypeByProjectIdAndEnterpriseId(SpringUtils.getCurrentProject().getId(),
								enterprise.getId());
				projectEnterpriseUser.setProjectType(projectType);
				projectEnterpriseUserService.update(projectEnterpriseUser);
				
				return Message.success();
			}
		} catch (DataAccessException e) {
			return Message.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("更新失败");
		}

	}

	public void setEnterpriseAddress(Enterprise enterprise) {
		if (enterprise != null && enterprise.getAddress() != null) {
			Area provinces = enterprise.getAddress().getProvinces();
			if (provinces != null && StringUtils.isEmpty(provinces.getCode())) {
				enterprise.getAddress().setProvinces(null);
			}
			Area city = enterprise.getAddress().getCity();
			if (city != null && StringUtils.isEmpty(city.getCode())) {
				enterprise.getAddress().setCity(null);
			}
			Area county = enterprise.getAddress().getCounty();
			if (county != null && StringUtils.isEmpty(county.getCode())) {
				enterprise.getAddress().setCounty(null);
			}
			Area street = enterprise.getAddress().getStreet();
			if (street != null && StringUtils.isEmpty(street.getCode())) {
				enterprise.getAddress().setStreet(null);
			}

		}
	}

	/**
	 * 查看地区信息
	 */
	@RequestMapping(value = "viewArea")
	public @ResponseBody Area viewArea(ModelMap model, Integer code) {

		return areaService.findAreaByAreaCode(code.toString());
	}

	/**
	 * 分配审核页面
	 */
	@RequestMapping(value = "allotPage")
	public String allotPage(ModelMap model, Enterprise enterprise, Integer account, Integer projectCode, String name,
			String industryCategoryCodeMain, String industryCategoryNameMain, String industryCategoryCodeMiddle,
			String industryCategoryNameMiddle, String enterpriseType, String putOnTime, String stopTime,
			String enterpriseTask, String modifyDate, String ids) {
		// 如果选择了企业
		if (StringUtils.isNotBlank(ids)) {
			model.put("ids", ids);
		} else {
			List<Filter> filters = new ArrayList<>();
			filters.add(new Filter("taskStatus", Operator.eq, Constants.STR_ENTERPRISE_STATUS_ALREADYSUBMIT));
			if (projectCode != null) {
				filters.add(new Filter("project.id", Operator.eq, projectCode));

				if (account != null) {
					filters.add(new Filter("user.id", Operator.eq, account));
				}
				if (StringUtils.isNotEmpty(name)) {
					filters.add(new Filter("enterprise.name", Operator.like, "%" + name + "%"));
				}
				if (StringUtils.isNotEmpty(modifyDate)) {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					List<Date> arr = new ArrayList<>();
					arr.add(DateHelper.getStartDateInterval(modifyDate, -1));
					arr.add(DateHelper.getStartDateInterval(modifyDate, 1));
					filters.add(new Filter("modifyDate", Operator.between, arr));
				}
				if (StringUtils.isNotEmpty(putOnTime) && StringUtils.isNotEmpty(stopTime)) {
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					try {
						List<Date> arr = new ArrayList<>();
						arr.add(simpleDateFormat.parse(putOnTime));
						arr.add(simpleDateFormat.parse(stopTime));
						filters.add(new Filter("enterprise.modifyDate", Operator.between, arr));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (StringUtils.isNotEmpty(industryCategoryCodeMain)) {
					filters.add(
							new Filter("enterprise.industryCategoryCodeMain", Operator.eq, industryCategoryCodeMain));
				}
				if (StringUtils.isNotEmpty(industryCategoryNameMain)) {
					filters.add(new Filter("enterprise.industryCategoryNameMain", Operator.like,
							"%" + industryCategoryNameMain + "%"));
				}
				if (StringUtils.isNotEmpty(industryCategoryCodeMiddle)) {
					filters.add(new Filter("enterprise.industryCategoryCodeMiddle", Operator.eq,
							industryCategoryCodeMiddle));
				}
				if (StringUtils.isNotEmpty(industryCategoryNameMiddle)) {
					filters.add(new Filter("enterprise.industryCategoryNameMiddle", Operator.like,
							"%" + industryCategoryNameMiddle + "%"));
				}
				if (enterprise != null && enterprise.getAddress() != null
						&& enterprise.getAddress().getProvinces() != null
						&& StringUtils.isNotEmpty(enterprise.getAddress().getProvinces().getCode())) {
					filters.add(new Filter("enterprise.address.provinces.code", Operator.eq,
							enterprise.getAddress().getProvinces().getCode()));
				}
				if (enterprise != null && enterprise.getAddress() != null && enterprise.getAddress().getCity() != null
						&& StringUtils.isNotEmpty(enterprise.getAddress().getCity().getCode())) {
					filters.add(new Filter("enterprise.address.city.code", Operator.eq,
							enterprise.getAddress().getCity().getCode()));
				}
				if (enterprise != null && enterprise.getAddress() != null && enterprise.getAddress().getCounty() != null
						&& StringUtils.isNotEmpty(enterprise.getAddress().getCounty().getCode())) {
					filters.add(new Filter("enterprise.address.county.code", Operator.eq,
							enterprise.getAddress().getCounty().getCode()));
				}
				if (StringUtils.isNotEmpty(enterpriseType)) {
					filters.add(new Filter("enterprise.enterpriseType", Operator.eq, enterprise.getEnterpriseType()));
				}
				List<ProjectEnterpriseUser> projectEnterpriseUsers = projectEnterpriseUserService.findList(null,
						filters, null);
				String enterids = projectEnterpriseUsers.stream().map(o1 -> String.valueOf(o1.getId()))
						.collect(Collectors.joining(","));
				model.put("ids", enterids);
			}
		}
		return "/admin/enterprise/allot";
	}

	/**
	 * 分配审核
	 */
	@RequestMapping(value = "/allot", method = RequestMethod.POST)
	@ResponseBody
	public Message allot(Integer checkAccount, String ids) {

		projectEnterpriseUserService.allot(checkAccount, ids);

		return Message.success();
	}
	/**
	 * 审核详情
	 */
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	@ResponseBody
	public Message audit(Integer enterpriseId) {
		
		Enterprise enterprise = enterpriseService.find(enterpriseId);

		Integer projectId = SpringUtils.getProjectId();
		
		
			Map<String, List<Object>> enters;
			try {
				enters = pdfTempletExportService.enterpriseDetail(enterprise, projectId);
				return Message.success(enters);
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return Message.error(e.getMessage(), null);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return Message.error(e.getMessage(), null);
			}
			
		
		
		
	}
	
	/**
	 * 企业详情
	 */
	@RequestMapping(value = "/showEnterprise", method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String audit(Integer enterpriseId,Integer projectId,String type) {
		
		Enterprise enterprise = enterpriseService.find(enterpriseId);

//		Integer projectId = SpringUtils.getProjectId();
		
			Map<Object, Object> backStr = new HashMap<>();
			Map<Object, List<Object>> enters;
			try {
				enters = pdfTempletExportService.showEnterpriseDetail(enterprise, projectId);
				backStr.put("enterpriseDetail", enters);
			} catch (DataAccessException e) {
				e.printStackTrace();
				backStr.put("enterpriseDetail", e.getMessage());
			}
			
			List<PhotoFile> photos = photoFileService.findList(null, Arrays.asList(Filter.eq("project.id", projectId),Filter.eq("enterprise.id", enterpriseId)), null);
			ProjectEnterpriseUser projectEnterpriseUser = projectEnterpriseUserService.findProjectTypeByProjectIdAndEnterpriseId(projectId, enterpriseId);
			
			backStr.put("photo", photos);
			if(projectEnterpriseUser != null){
				backStr.put("user", 
						new UserInfo(projectEnterpriseUser.getUser().getUsername(),
						projectEnterpriseUser.getSubmitDate() != null?DateFormatUtils.format(projectEnterpriseUser.getSubmitDate(), "yyyy-MM-dd HH:mm:ss"):""));
			}else{
				backStr.put("user", "");
			}
			return  JsonUtil.toJSON(backStr);
		
		
	}
	
	public class UserInfo{
		private String username;
		private String submitDate;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getSubmitDate() {
			return submitDate;
		}
		public void setSubmitDate(String submitDate) {
			this.submitDate = submitDate;
		}
		public UserInfo(String username, String submitDate) {
			super();
			this.username = username;
			this.submitDate = submitDate;
		}
		
		
	}

}
