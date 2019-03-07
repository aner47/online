package com.online.controller.web.enterprise;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
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
import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.Token;
import com.online.controller.base.BaseController;
import com.online.entity.Area;
import com.online.entity.ProjectEnterpriseUser;
import com.online.entity.online.Enterprise;
import com.online.entity.online.Project;
import com.online.entity.online.TaskManager;
import com.online.exportpdf.PDFTempletExportService;
import com.online.service.AreaService;
import com.online.service.CategoryMenuConfigService;
import com.online.service.EnterpriseService;
import com.online.service.ProjectEnterpriseUserService;
import com.online.service.ProjectService;
import com.online.service.ProjectTypeService;
import com.online.service.TaskManagerService;
import com.online.util.Constants;
import com.online.util.JsonUtil;
import com.online.util.Principal;
import com.online.util.SpringUtils;

/**
 * @author 作者名
 * @email 邮箱名
 * @time 2017年4月26日 下午2:15:47
 */
@Controller("webEnterpriseController")
@RequestMapping("/web/enterprise")
public class EnterpriseController extends BaseController {

	@Autowired
	protected AreaService areaService;
	@Autowired
	protected EnterpriseService enterpriseService;

	@Autowired
	protected ProjectService projectService;

	@Autowired
	private PDFTempletExportService pdfTempletExportService;
	
	@Autowired
	@Lazy
	public TaskManagerService taskManagerService;

	static List<String> imgSuffix = Arrays.asList("JPG", "PNG", "GIF", "JPEG");

	
	@Autowired
	private CategoryMenuConfigService  categoryMenuConfigService ;
	@Autowired
	private ProjectTypeService  projectTypeService ;
	
	@Autowired
	@Lazy
	private ProjectEnterpriseUserService  projectEnterpriseUserService ;
	
	@RequestMapping("/list")
	public String list(ModelMap model) {
		Project project = SpringUtils.getCurrentProject();
		model.put("project", project);
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		
		return "/admin/enterprise/list";
	}

	/**
	 * 增加企业页面
	 */
	@Token(save = true)
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model) {
		Project project = SpringUtils.getCurrentProject();
		model.put("project", project);
		return "/admin/enterprise/add";
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
		ProjectEnterpriseUser projectEnterpriseUser = projectEnterpriseUserService.findProjectTypeByProjectIdAndEnterpriseId(SpringUtils.getCurrentProject().getId(),
				id);
		Integer projectType = null;
		if(projectEnterpriseUser != null){
			projectType = projectEnterpriseUser.getProjectType();
		}
		if(StringUtils.isNotBlank(project.getSysProductIndustrys())){
			model.put("sysProductIndustrys", project.getSysProductIndustrys());
		}
				
		model.put("projectType", projectType);
		
		return "/admin/enterprise/dialogEdit";
	}

	/**
	 * 修改企业页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model, Integer id) {
		if (id == null) {
			model.put("enterprise", enterpriseService.findByAccount(SpringUtils.getUserId()));
		} else {
			model.put("enterprise", enterpriseService.find(id));
		}
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
	 * 查看企业信息
	 */
	@RequestMapping(value = "viewData")
	public @ResponseBody Enterprise viewData(ModelMap model, Integer id) {
		return enterpriseService.find(id);
	}

	/**
	 * 查询企业
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<ProjectEnterpriseUser> query(Pageable pageable, Enterprise enterprise, String account, String proejctCode,
			String name, String industryCategoryCodeMain, String industryCategoryNameMain,
			String industryCategoryCodeMiddle, String industryCategoryNameMiddle, String enterpriseType,String taskStatus) {
		
		
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter("user.id", Operator.eq, SpringUtils.getUserId());
		
		if (StringUtils.isNotEmpty(taskStatus)) {
			pageable.addFilter("taskStatus", Operator.eq, taskStatus);
		}
		if (StringUtils.isNotEmpty(name)) {
			pageable.addFilter("enterprise.name", Operator.like, "%" + name + "%");
		}
		if (StringUtils.isNotEmpty(industryCategoryCodeMain)) {
			pageable.addFilter("enterprise.industryCategoryCodeMain", Operator.eq, industryCategoryCodeMain);
		}
		if (StringUtils.isNotEmpty(industryCategoryNameMain)) {
			pageable.addFilter("enterprise.industryCategoryNameMain", Operator.like, "%" + industryCategoryNameMain + "%");
		}
		if (StringUtils.isNotEmpty(industryCategoryCodeMiddle)) {
			pageable.addFilter("enterprise.industryCategoryCodeMiddle", Operator.eq, industryCategoryCodeMiddle);
		}
		if (StringUtils.isNotEmpty(industryCategoryNameMiddle)) {
			pageable.addFilter("enterprise.industryCategoryNameMiddle", Operator.like, "%" + industryCategoryNameMiddle + "%");
		}
		if (enterprise != null && enterprise.getAddress() != null && enterprise.getAddress().getProvinces() != null
				&& StringUtils.isNotEmpty(enterprise.getAddress().getProvinces().getCode())) {
			pageable.addFilter("enterprise.address.provinces.code", Operator.eq, enterprise.getAddress().getProvinces().getCode());
		}
		if (enterprise != null && enterprise.getAddress() != null && enterprise.getAddress().getCity() != null
				&& StringUtils.isNotEmpty(enterprise.getAddress().getCity().getCode())) {
			pageable.addFilter("enterprise.address.city.code", Operator.eq, enterprise.getAddress().getCity().getCode());
		}
		if (enterprise != null && enterprise.getAddress() != null && enterprise.getAddress().getCounty() != null
				&& StringUtils.isNotEmpty(enterprise.getAddress().getCounty().getCode())) {
			pageable.addFilter("enterprise.address.county.code", Operator.eq, enterprise.getAddress().getCounty().getCode());
		}
		if (StringUtils.isNotEmpty(enterpriseType)) {
			pageable.addFilter("enterprise.enterpriseType", Operator.eq, enterprise.getEnterpriseType());
		}
		return projectEnterpriseUserService.findPage(pageable);

	}

	/**
	 * 保存企业 保存企业后保存生产信息
	 
	@Token(remove = true)
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(Enterprise enterprise, HttpServletRequest request, HttpServletResponse response) {

		boolean exists;
		try {
			exists = enterpriseService.isexist(enterprise);
			if (exists) {
				request.getSession(true).setAttribute("token", request.getParameter("token"));
				return Message.error("企业存在");
			} else {
				enterprise.setAccount(SpringUtils.getUserId());
				setEnterpriseAddress(enterprise);
				enterprise.addProject(projectService.find(SpringUtils.getCurrentProject().getId()));
				
				enterpriseService.saveEnterprise(enterprise);

				return Message.success();
			}
		} catch (DataAccessException e) {
			return Message.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("添加失败");
		}

	}*/

	/**
	 * 更新企业
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Enterprise enterprise,Integer projectType) {
		
		
		
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
				
				
				return Message.success();
			}
		} catch (DataAccessException e) {
			return Message.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("更新失败");
		}
		
		
	}

	/**
	 * 删除企业
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			try {
				TaskManager taskManager = taskManagerService.findByEnterpriseId(ids[i]);
				if (taskManager != null) {
					return Message.error("有任务关联不能删除企业！", null);
				}else{
					enterpriseService.deleteByEnterpriseId(ids[i]);
				}
				
			} catch(DataAccessException e){
				return Message.error(e.getMessage(), null);
			}catch (ConstraintViolationException e) {
				e.printStackTrace();
				return Message.error("企业有其他项目关联不能删除", null);
			} catch (Exception e) {
				e.printStackTrace();
				return Message.error("", null);
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
	public @ResponseBody Message cacheEnterpriseId(ModelMap map, Integer enterpriseId) {

		//企业是否完整
		if(enterpriseService.isFinish(enterpriseId)){
			Enterprise enterprise = enterpriseService.find(enterpriseId);
			Set<Project> projects = enterprise.getProjects();
			Project project = projectService.find(SpringUtils.getCurrentProject().getId());

			if (!isExist(projects, project)) {
				enterprise.addProject(project);
				enterpriseService.update(enterprise);
			}

			Principal attribute = SpringUtils.getPrincipal();
			attribute.setEnterpriseId(enterpriseId);
			
			SpringUtils.getRequest().getSession().setAttribute(Constants.SESSION_USERNAME, JsonUtil.toJSON(attribute));
			// Enterprise currentEnterprise = SpringUtils.getCurrentEnterprise();
			// Integer id = currentEnterprise.getId();

			return Message.success();
		}else{
			return Message.error("请补全企业信息",null);
		}
		
		
	}

	private boolean isExist(Set<Project> projects, Project currentProject) {
		for (Project project : projects) {
			if (project.getId() == currentProject.getId()) {
				return true;
			}
		}
		return false;
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
	 * 保存照片
	 */
	@RequestMapping(value = "/upload")
	@ResponseBody
	@Transactional
	public Message save(HttpServletRequest request, String url, String filename) {
		String suffix = filename.substring(filename.lastIndexOf(".") + 1);
		if (imgSuffix.contains(suffix.toUpperCase())) {

		} else {
			return Message.error("上传图片格式错误！");
		}
		return Message.success();
	}

	/**
	 * 修改状态-提交按钮
	 */
	@RequestMapping(value = "/updatestatus")
	@ResponseBody
	public Message updatestatus(Integer enterpriseId,String enterpriseType) {
		Enterprise enterprise = enterpriseService.find(enterpriseId);
		try {
			enterpriseService.submitEnterprise(SpringUtils.getProjectId(),enterpriseId, Constants.ENTERPRISE_STATUS_ALREADYSUBMIT);
		}catch (DataAccessException e) {
			return Message.error(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			return Message.error(e.getMessage());
		}
		
		return Message.success();
		
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
	public Message isExportpdf(HttpServletRequest request, HttpServletResponse response, Integer enterpriseId)
			{
		
		
		try {
			Integer projectId = SpringUtils.getProjectId();
			Enterprise enterprise = enterpriseService.find(enterpriseId);
			pdfTempletExportService.exportPdf(enterprise,projectId);
		} catch (DataAccessException e) {
			//e.printStackTrace();
			return Message.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			return Message.error("模板错误");
		} catch (DocumentException e) {
			e.printStackTrace();
			return Message.error("模板错误");
		}catch (Exception e) {
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
			{
		
		Integer projectId = SpringUtils.getProjectId();
		
		Enterprise enterprise = enterpriseService.find(enterpriseId);
		
		try {
			request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		//response.setContentType("octets/stream");
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
		
//		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		List<Enterprise> enterprises = new ArrayList<Enterprise>();
		enterprises.add(enterprise);
		
		pdfTempletExportService.exprotAllEnterprise(response.getOutputStream(), projectId, "",
				Constants.PDF_PASSWORD,GetResource.class.getClassLoader().getResource("test.png").getPath(), Constants.PDF_WATERCHAR, enterprises,isFinish);
		try {
			response.getOutputStream().close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DataAccessException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
		return Message.success();
	}
	
	
	/**
	 * 查看地区信息
	 */
	@RequestMapping(value = "viewArea")
	public @ResponseBody Area viewArea(ModelMap model, Integer code) {
		
		return areaService.findAreaByAreaCode(String.valueOf(code));
	}
	
	/**
	 * 根据行业查询菜单
	 */
	@RequestMapping(value = "findMenuByIndustry")
	@ResponseBody
	public Message  findMenuByIndustry(String industryCategoryId) {
		return Message.success(categoryMenuConfigService.findMenuByIndustry(industryCategoryId));
	}
	
	/**
	 * 根据项目类型查询菜单
	 */
	@RequestMapping(value = "findMenuByProjectType")
	@ResponseBody
	public Message  findMenuByProjectType(Integer projectTypeId) {
		if(projectTypeService.find(projectTypeId) != null){
			return Message.success(projectTypeService.find(projectTypeId).getMenus());
		}else{
			return Message.error("未配置项目类型无菜单");
		}
		
	}
	
	

}
