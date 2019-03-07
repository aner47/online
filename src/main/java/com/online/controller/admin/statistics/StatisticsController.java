package com.online.controller.admin.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.online.controller.base.BaseController;
import com.online.entity.online.Condition;
import com.online.entity.online.Project;
import com.online.service.ProjectService;
import com.online.service.StatisticsService;
import com.online.util.Constants;
import com.online.util.Principal;
import com.online.util.SpringUtils;

/**
 * 填报统计
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2017年8月15日 下午2:41:36 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Controller("statisticsController")
@RequestMapping("/admin/statistics")
public class StatisticsController extends BaseController {

	@Autowired
	private StatisticsService statisticsService;
	
	@Autowired
	private ProjectService projectService;

	@RequestMapping("/placeList")
	public String placeList(ModelMap model) {
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		return "/admin/statistics/placeList";
	}
	@RequestMapping("/timeList")
	public String timeList(ModelMap model) {
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		return "/admin/statistics/timeList";
	}
	@RequestMapping("/userList")
	public String userList(ModelMap model) {
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		return "/admin/statistics/userList";
	}
	@RequestMapping("/inquirerList")
	public String inquirerList(ModelMap model) {
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		return "/admin/statistics/inquirerList";
	}
	@RequestMapping("/enterpriseTypeList")
	public String enterpriseTypeList(ModelMap model) {
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		return "/admin/statistics/enterpriseTypeList";
	}
	@RequestMapping("/normalList")
	public String normalList(ModelMap model) {
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		return "/admin/statistics/normalList";
	}
	@RequestMapping("/simpleList")
	public String simpleList(ModelMap model) {
		Principal attribute = SpringUtils.getPrincipal();
		model.put("principal", attribute);
		return "/admin/statistics/simpleList";
	}

	
	/**
	 * 填报统计
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Map<String, List<Map<String, Object>>> query(Condition condition) {
		
		
		
		//如果项目为空返回null
		if(condition == null || StringUtils.isEmpty(condition.getProject())){
			return null;
		}
		//如果项目为空返回null
		Project project = projectService.findProjectByInvitationCode(condition.getProject());
		if(project == null){
			return null;
		}
		Map<String, List<Map<String, Object>>> returnMap = new HashMap<>();
		
		//地区条件
		Condition areacondition = new Condition();
		areacondition.setProject(project.getId().toString());
		areacondition.setProvinces(condition.getProvinces());
		areacondition.setCity(condition.getCity());
		areacondition.setCounty(condition.getCounty());
		areacondition.setStartDate(condition.getStartDate());
		areacondition.setEndDate(condition.getEndDate());
		areacondition.setIndustry_category_code_main(condition.getIndustry_category_code_main());
		areacondition.setIndustry_category_code_middle(condition.getIndustry_category_code_middle());
		
		//时间条件
		/*Condition datecondition = new Condition();
		datecondition.setProject(project.getId().toString());
		datecondition.setStartDate(condition.getStartDate());
		datecondition.setEndDate(condition.getEndDate());
		
		//用户条件
		Condition usercondition = new Condition();
		usercondition.setProject(project.getId().toString());
		usercondition.setStartDate(condition.getStartDate());
		usercondition.setEndDate(condition.getEndDate());
		
		//填报人条件
		Condition inquirercondition = new Condition();
		inquirercondition.setProject(project.getId().toString());
		inquirercondition.setStartDate(condition.getStartDate());
		inquirercondition.setEndDate(condition.getEndDate());*/
		
		returnMap.put("area", statisticsService.findStatistics(Constants.STATISTICS_AREA,areacondition));
		returnMap.put("date", statisticsService.findStatistics(Constants.STATISTICS_DATE,areacondition));
		returnMap.put("user", statisticsService.findStatistics(Constants.STATISTICS_USER,areacondition));
		returnMap.put("inquirer", statisticsService.findStatistics(Constants.STATISTICS_INQUIRER,areacondition));
		returnMap.put("enterpriseType", statisticsService.findStatistics(Constants.STATISTICS_RNTERPERSETYPE,areacondition));
		returnMap.put("normalMain", statisticsService.findStatistics(Constants.STATISTICS_NORMALMAIN,areacondition));
		returnMap.put("normalMiddle", statisticsService.findStatistics(Constants.STATISTICS_NORMALMIDDLE,areacondition));
		returnMap.put("simpleMain", statisticsService.findStatistics(Constants.STATISTICS_SIMPLEMAIN,areacondition));
		returnMap.put("simpleMiddle", statisticsService.findStatistics(Constants.STATISTICS_SIMPLEMIDDLE,areacondition));
		
		
		return returnMap;
	}
	
	
	

	
	
}
