package com.online.controller.web.monthlyinformation;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Message;
import com.online.Page;
import com.online.Pageable;
import com.online.controller.base.BaseController;
import com.online.entity.DictionaryData;
import com.online.entity.online.BoilerInformation;
import com.online.entity.online.Fuel;
import com.online.entity.online.GasStove;
import com.online.entity.online.Kiln;
import com.online.entity.online.MonthlyInformation;
import com.online.entity.online.PowerPlant;
import com.online.entity.online.Product;
import com.online.entity.online.RawMaterials;
import com.online.entity.online.Section;
import com.online.entity.online.Steel;
import com.online.entity.online.fieldset.FieldModule;
import com.online.entity.online.fieldset.FieldShowConfig;
import com.online.service.BoilerInformationService;
import com.online.service.FieldModuleService;
import com.online.service.FieldShowConfigService;
import com.online.service.FuelService;
import com.online.service.GasStoveService;
import com.online.service.KilnService;
import com.online.service.MonthlyInformationService;
import com.online.service.PowerPlantService;
import com.online.service.RawMaterialsService;
import com.online.service.SectionService;
import com.online.service.SteelService;
import com.online.util.Constants;
import com.online.util.SpringUtils;



@Controller("webMonthlyInformationController")
@RequestMapping("/web/monthlyinformation")
public class MonthlyInformationController extends BaseController{
	
	@Autowired
	private MonthlyInformationService  monthlyInformationService ;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private BoilerInformationService boilerInformationService;
	@Autowired
	private KilnService kilnService;
	@Autowired
	private PowerPlantService plantService;
	@Autowired
	private SteelService steelService;
	@Autowired
	private GasStoveService gasStoveService;
	@Autowired
	private RawMaterialsService rawMaterialsService;
	
	@Autowired
	private FuelService fuelService;
	
	@Autowired
	private FieldModuleService fieldModuleService;
	@Autowired
	private FieldShowConfigService fieldShowConfigService;
	
	
	public static final String TYPE_FUEL = "fuel";
	public static final String TYPE_PRODUCT = "product";
	public static final String TYPE_RAW = "rawMaterials";
	
	
	@RequestMapping("/list")
	public String list(ModelMap model,String monthlyType,Integer sourceId){
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_monthlyinformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		model.put("monthlyType", monthlyType);
		model.put("sourceId", sourceId);
		
		return "/admin/monthlyinformation/list";
	}
	
	/**
	 * 增加分月数据页面
	 */
	@RequestMapping(value = "addPage")
	public String addPage(ModelMap model,String monthlyType,Integer sourceId) {
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_monthlyinformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		model.put("monthlyType", monthlyType);
		model.put("sourceId", sourceId);
		return "/admin/monthlyinformation/add";
	}

	/**
	 * 修改分月数据页面
	 */
	@RequestMapping(value = "updatePage")
	public String updatePage(ModelMap model,Integer id,String monthlyType,Integer sourceId) {
		model.put("monthlyInformation", monthlyInformationService.find(id));
		FieldModule fieldModule = fieldModuleService.findByNameTableType(Constants.FIELD_detail_monthlyinformation);
		if(fieldModule != null){
			FieldShowConfig fieldShowConfig = fieldShowConfigService.findByProjectIdTableTypeFieldModule(fieldModule.getId());
			if(fieldShowConfig != null){
				model.put("fieldShowConfig", fieldShowConfig.getHideField());
			}
		}
		model.put("monthlyType", monthlyType);
		model.put("sourceId", sourceId);
		return "/admin/monthlyinformation/update";
	}
	
	/**
	 * 查看分月数据页面
	 */
	@RequestMapping(value = "viewPage")
	public String viewPage(ModelMap model,Integer id) {
		model.put("monthlyInformation", monthlyInformationService.find(id));
		return "/admin/monthlyinformation/view";
	}
	
	/**
	 * 查询分月数据
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<MonthlyInformation> query(Pageable pageable,MonthlyInformation monthlyInformation,String monthlyType,Integer sourceId) {
		pageable.addFilter("project.id", Operator.eq, SpringUtils.getProjectId());
		pageable.addFilter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId());
		//如果分月类型是发电机组
		if("powerplant".equals(monthlyType)){
			pageable.addFilter("powerPlantId", Operator.eq, sourceId);
		//锅炉
		}else if("boiler".equals(monthlyType)){
			pageable.addFilter("boilerId", Operator.eq, sourceId);
		//煤气炉
		}else if("gasstove".equals(monthlyType)){
			pageable.addFilter("gasstoveId", Operator.eq, sourceId);
			
		//炉窑
		}else if("kiln".equals(monthlyType)){
			pageable.addFilter("kilnId", Operator.eq, sourceId);
		//生产线
		}else if("section".equals(monthlyType)){
			pageable.addFilter("sectionId", Operator.eq, sourceId);
		//原辅料	
		}else if("rawmaterials".equals(monthlyType)){
			pageable.addFilter("rawMaterialsId", Operator.eq, sourceId);
		}
		
		return monthlyInformationService.findPage(pageable);
		
	}
	

	/**
	 * 保存分月数据
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Message save(MonthlyInformation  monthlyInformation) {
		monthlyInformation.setProject(SpringUtils.getCurrentProject());
		monthlyInformation.setEnterprise(SpringUtils.getCurrentEnterprise());
		monthlyInformationService.save(monthlyInformation);
		return Message.success();
	}

	/**
	 * 更新分月数据
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(MonthlyInformation monthlyInformation) {
		monthlyInformationService.update(monthlyInformation,"enterprise","project","powerPlantId","boilerId","kilnId","sectionId","rawMaterialsId","gasstoveId");
		return Message.success();
	}

	/**
	 * 删除分月数据
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Integer... ids) {
		for (int i = 0; i < ids.length; i++) {
			 monthlyInformationService.delete(ids[i]);
		}
		return Message.success();
	}
	/**
     * 根据类型查询
     */
    @RequestMapping(value = "/findSourceNameById", method = RequestMethod.POST)
    @ResponseBody
    public List<DictionaryData> findSourceNameById(String param) {
    	
    	String type = "";
    	Integer sourceId = -1;
    	if(StringUtils.isNotEmpty(param)){
    		if(StringUtils.isNotEmpty(param)){
    			String[] split = param.split(",");
    			for (String str : split) {
    				String[] split2 = str.split(":");
    				if(split2.length==2){
    					if("type".equals(split2[0])){
    						type = split2[1];
    					}else if("sourceId".equals(split2[0])){
    						sourceId = Integer.parseInt(split2[1]);
    					}
    					
    				}
    			}
    		}
    	}
    	
        Integer id = SpringUtils.getCurrentEnterprise().getId();
//        Set<String> name=new HashSet<>();
        Map<String, Object> name=new HashMap<>();
        if (StringUtils.isNotEmpty(type)) {
            switch (type) {
            case "section":
            	Section section =sectionService.find(sourceId);
                if (section != null) {
                    setSection(name,section);
                    return returnData(name);
                }
                break;
            case "boiler":
                /*List< BoilerInformation> blist = boilerInformationService.findSourceNameById(id);
                if (blist.size()>0) {
                    for (BoilerInformation boilerInformation : blist) {
                        setBoiler(name,boilerInformation);
                    }
                    return Message.success(name);
                }*/
            	BoilerInformation boilerInformation = boilerInformationService.find(sourceId);
            	if(boilerInformation != null){
            		setBoiler(name,boilerInformation);
            		return returnData(name);
            	}
                break;
            case "kiln":
                /*List< Kiln> kilnlist =kilnService.findSourceNameById(id);
                if (kilnlist.size()>0) {
                    for (Kiln kiln : kilnlist) {
                        setKiln(name,kiln);
                    }
                    return Message.success(name);
                }*/
            	Kiln kiln= kilnService.find(sourceId);
            	if(kiln != null){
            		setKiln(name,kiln);
            		return returnData(name);
            	}
                break;
            case "powerPlant":
                 PowerPlant plant =plantService.find(sourceId);
                 setPowerPlant(name,plant);
                 return returnData(name);
            case "steel":
            	List<Filter> filters = new ArrayList<Filter>();
            	filters.add(new Filter("enterprise.id", Operator.eq, SpringUtils.getCurrentEnterprise().getId()));
            	filters.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
            	
            	List<Steel> steelList =steelService.findList(null, filters, null);
            	if (steelList.size()>0) {
            		for (Steel steel : steelList) {
            			setSteel(name,steel);
            		}
            		return returnData(name);
            	}
            	break;
            case "gasstove":
            	
            	/*List<Filter> gasstovefilters = new ArrayList<Filter>();
            	gasstovefilters.add(new Filter("emissionsManagement.enterprise.id", Operator.eq, SpringUtils.getCurrentEnterprise().getId()));
            	gasstovefilters.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
            	
            	List< GasStove> gasStovelist = gasStoveService.findList(null, gasstovefilters, null);
                if (gasStovelist.size()>0) {
                    for (GasStove gasStove : gasStovelist) {
                    	setGasStove(name,gasStove);
                    }
                    return Message.success(name);
                }*/
            	GasStove gasStove = gasStoveService.find(sourceId);
            	if(gasStove != null){
            		setGasStove(name,gasStove);
            		return returnData(name);
            	}
            	break;
            case "rawmaterials":
            	RawMaterials rawmaterials = rawMaterialsService.find(sourceId);
            	//Section section = sectionService.find(rawmaterials.getSectionId());
            	setRawmaterials(name,rawmaterials);
            	//setSection(name,section);
            	/*List<Section> sectionlist =sectionService.findSourceNameById(id);
            	List<Filter> rawmaterialsFilters = new ArrayList<Filter>();
            	rawmaterialsFilters.add(new Filter("enterprise", Operator.eq, SpringUtils.getCurrentEnterprise().getId()));
            	rawmaterialsFilters.add(new Filter("project", Operator.eq, SpringUtils.getProjectId()));
            	rawmaterialsFilters.add(new Filter("sectionId", Operator.eq, sourceId));
            	List<RawMaterials> rawmaterialsList = rawMaterialsService.findList(null, rawmaterialsFilters, null);
            	
                if (sectionlist.size()>0 || rawmaterialsList.size()>0) {
                    for (Section section : sectionlist) {
                       setSection(name,section);
                    }
                    for (RawMaterials rawmaterials : rawmaterialsList) {
                    	setRawmaterials(name,rawmaterials);
                    }
                    
                    
                }*/
            	return returnData(name);
            default:
                break;
            }
        }
        return null;
    }
    
    
    public List<DictionaryData> returnData(Map<String, Object> name){
    	List<DictionaryData>  list = new ArrayList<>();
		for (Map.Entry<String, Object> entry : name.entrySet()) {
			DictionaryData aData = new DictionaryData();
			aData.setCode(entry.getKey());
			aData.setCodeValue(entry.getKey());
			aData.setOtherValue(JSONObject.toJSONString(entry.getValue()));
			list.add(aData);
		}
		
    	return list;
    }

    /**设置自备发电机组信息
     * @param name
     * @param plant
     */
    private void setPowerPlant(Map<String, Object> name, PowerPlant plant) {
        if (plant!=null) {
            String fuelType = plant.getFuelType();
            if (StringUtils.isNotEmpty(fuelType)) {
                name.put(fuelType,new MonthData(plant.getFuelType(),plant.getFuelConsumption(),plant.getFuelUnit(),plant.getFuelYear(),TYPE_FUEL));
            }
            List<Fuel> fuels = fuelService.findByFilter(new Filter("powerPlantId", Operator.eq, plant.getId()));
            for(Fuel fuel:fuels){
            	name.put(fuel.getFuelType(),new MonthData(fuel.getFuelType(),fuel.getFuelConsumption(),fuel.getFuelUnit(),fuel.getFuelYear(),TYPE_FUEL));
            }
            
            name.put("发电量",new MonthData("发电量",plant.getAnnualPowerGeneration(),"万千瓦时",plant.getFuelYear(),TYPE_PRODUCT));
            name.put("供热量",new MonthData("供热量",plant.getAnnualHeatSupply(),"万吉焦",plant.getFuelYear(),TYPE_PRODUCT));
        }
        
    }

    /**设置窑炉信息
     * @param name
     * @param kiln
     */
    private void setKiln(Map<String, Object> name, Kiln kiln) {
        if (kiln!=null) {
            Product product = kiln.getProduct();
            String pname = product.getName();
            String fuelType = kiln.getFuelType();
            RawMaterials rawMaterials1 = kiln.getRawMaterials1();
            if(rawMaterials1 != null){
            	String name1 = rawMaterials1.getName();
            	if (StringUtils.isNotEmpty(name1)) {
            		name.put(name1,new MonthData(rawMaterials1.getName(),rawMaterials1.getConsumption(),rawMaterials1.getUnit(),kiln.getYear(),TYPE_RAW));
                }
            }
            RawMaterials rawMaterials2 = kiln.getRawMaterials2();
            if(rawMaterials2 != null){
            	String name2 = rawMaterials2.getName();
            	if (StringUtils.isNotEmpty(name2)) {
            		name.put(name2,new MonthData(rawMaterials2.getName(),rawMaterials2.getConsumption(),rawMaterials2.getUnit(),kiln.getYear(),TYPE_RAW));
                }
            }
            
            RawMaterials rawMaterials3 = kiln.getRawMaterials3();
            if(rawMaterials3 != null){
            	String name3 = rawMaterials3.getName();
            	if (StringUtils.isNotEmpty(name3)) {
            		name.put(name3,new MonthData(rawMaterials3.getName(),rawMaterials3.getConsumption(),rawMaterials3.getUnit(),kiln.getYear(),TYPE_RAW));
                }
            }
            RawMaterials rawMaterials4 = kiln.getRawMaterials4();
            if(rawMaterials4 != null){
            	String name4 = rawMaterials4.getName();
            	if (StringUtils.isNotEmpty(name4)) {
            		name.put(name4,new MonthData(rawMaterials4.getName(),rawMaterials4.getConsumption(),rawMaterials4.getUnit(),kiln.getYear(),TYPE_RAW));
                }
            }
            RawMaterials rawMaterials5 = kiln.getRawMaterials5();
            if(rawMaterials5 != null){
            	String name5 = rawMaterials5.getName();
            	if (StringUtils.isNotEmpty(name5)) {
            		name.put(name5,new MonthData(rawMaterials5.getName(),rawMaterials5.getConsumption(),rawMaterials5.getUnit(),kiln.getYear(),TYPE_RAW));
                }
            }
            if (StringUtils.isNotEmpty(fuelType)) {
                name.put(fuelType,new MonthData(kiln.getFuelType(),kiln.getFuelConsumption(),kiln.getFuelUnit(),kiln.getYear(),TYPE_FUEL));
            }
            if (StringUtils.isNotEmpty(pname)) {
                name.put(pname,new MonthData(product.getName(),product.getYield(),product.getUnit(),kiln.getYear(),TYPE_PRODUCT));
            }
            
            List<Fuel> fuels = fuelService.findByFilter(new Filter("kilnId", Operator.eq, kiln.getId()));
            for(Fuel fuel:fuels){
            	name.put(fuel.getFuelType(),new MonthData(fuel.getFuelType(),fuel.getFuelConsumption(),fuel.getFuelUnit(),kiln.getYear(),TYPE_FUEL));
            }
            
            
            
            
        }
        
        
    }

    /**设置锅炉信息
     * @param name
     * @param boilerInformation
     */
    private void setBoiler(Map<String, Object> name, BoilerInformation boilerInformation) {
        if (boilerInformation!=null) {
            String fuelType = boilerInformation.getFuelType();
            if (StringUtils.isNotEmpty(fuelType)) {
                name.put(fuelType,new MonthData(boilerInformation.getFuelType(),boilerInformation.getFuelConsumption(),boilerInformation.getFuelUnit(),boilerInformation.getFuelYear(),TYPE_FUEL));
            }
            List<Fuel> fuels = fuelService.findByFilter(new Filter("boilerId", Operator.eq, boilerInformation.getId()));
            for(Fuel fuel:fuels){
            	name.put(fuel.getFuelType(),new MonthData(fuel.getFuelType(),fuel.getFuelConsumption(),fuel.getFuelUnit(),fuel.getFuelYear(),TYPE_FUEL));
            }
        }
        
    }

    /**设置生产线信息
     * @param name
     * @param section
     */
    private void setSection(Map<String, Object> name, Section section) {
//    	private void setSection(Set<String> name, Section section) {
        if (section!=null) {
            Product product = section.getProduct();
            String pname = product.getName();
           /* String fuelType = section.getFuelType();
            RawMaterials rawMaterials1 = section.getRawMaterials1();
            String name1 = rawMaterials1.getName();
            RawMaterials rawMaterials2 = section.getRawMaterials2();
            String name2 = rawMaterials2.getName();
            RawMaterials rawMaterials3 = section.getRawMaterials3();
            String name3 = rawMaterials3.getName();
            RawMaterials rawMaterials4 = section.getRawMaterials4();
            String name4 = rawMaterials4.getName();
            RawMaterials rawMaterials5 = section.getRawMaterials5();
            String name5 = rawMaterials5.getName();
           
            if (StringUtils.isNotEmpty(fuelType)) {
                name.add(fuelType);
            }
            if (StringUtils.isNotEmpty(name1)) {
                name.add(name1);
            }
            if (StringUtils.isNotEmpty(name2)) {
                name.add(name2);
            }
            if (StringUtils.isNotEmpty(name3)) {
                name.add(name3);
            }
            if (StringUtils.isNotEmpty(name4)) {
                name.add(name4);
            }
            if (StringUtils.isNotEmpty(name5)) {
                name.add(name5);
            }*/
            
            if (StringUtils.isNotEmpty(pname)) {
                name.put(pname,new MonthData(product.getName(),product.getYield(),product.getUnit(),section.getYear(),TYPE_PRODUCT));
            }
            if (StringUtils.isNotEmpty(section.getFuelType())) {
            	name.put(section.getFuelType(),new MonthData(section.getFuelType(),section.getFuelConsumption(),section.getFuelUnit(),section.getYear(),TYPE_FUEL));
            }
 
 
        }
        
    }	
    
    /**设置钢铁
     * @param name
     * @param boilerInformation
     */
    private void setSteel(Map<String, Object> name, Steel steel) {
        /*if (steel!=null) {
            String fuelType = steel.getFuelType();
            if (StringUtils.isNotEmpty(fuelType)) {
                name.add(fuelType);
            }
        }*/
    	if (steel!=null) {
            Product product = steel.getProduct();
            String pname = product.getProductType();
            String fuelType = steel.getFuelType();
            RawMaterials rawMaterials1 = steel.getRawMaterials1();
            String name1 = rawMaterials1.getType();
            RawMaterials rawMaterials2 = steel.getRawMaterials2();
            String name2 = rawMaterials2.getType();
            RawMaterials rawMaterials3 = steel.getRawMaterials3();
            String name3 = rawMaterials3.getType();
            RawMaterials rawMaterials4 = steel.getRawMaterials4();
            String name4 = rawMaterials4.getType();
            RawMaterials rawMaterials5 = steel.getRawMaterials5();
            String name5 = rawMaterials5.getType();
            if (StringUtils.isNotEmpty(pname)) {
                name.put(pname,new MonthData(product.getName(),product.getYield(),product.getUnit(),steel.getYear(),TYPE_PRODUCT));
            }
            if (StringUtils.isNotEmpty(fuelType)) {
                name.put(fuelType,new MonthData(steel.getFuelType(),steel.getFuelConsumption(),steel.getFuelUnit(),steel.getYear(),TYPE_FUEL));
            }
            if (StringUtils.isNotEmpty(name1)) {
            	name.put(name1,new MonthData(rawMaterials1.getName(),rawMaterials1.getConsumption(),rawMaterials1.getUnit(),steel.getYear(),TYPE_RAW));
            }
            if (StringUtils.isNotEmpty(name2)) {
            	name.put(name2,new MonthData(rawMaterials2.getName(),rawMaterials2.getConsumption(),rawMaterials2.getUnit(),steel.getYear(),TYPE_RAW));
            }
            if (StringUtils.isNotEmpty(name3)) {
            	name.put(name3,new MonthData(rawMaterials3.getName(),rawMaterials3.getConsumption(),rawMaterials3.getUnit(),steel.getYear(),TYPE_RAW));
            }
            if (StringUtils.isNotEmpty(name4)) {
            	name.put(name4,new MonthData(rawMaterials4.getName(),rawMaterials4.getConsumption(),rawMaterials4.getUnit(),steel.getYear(),TYPE_RAW));
            }
            if (StringUtils.isNotEmpty(name5)) {
                name.put(name5,new MonthData(rawMaterials5.getName(),rawMaterials5.getConsumption(),rawMaterials5.getUnit(),steel.getYear(),TYPE_RAW));
            }
 
 
        }
        
    }
    
    /**设置煤气炉
     * @param name
     * @param gasStove
     */
    private void setGasStove(Map<String, Object> name, GasStove gasStove) {
        if (gasStove!=null) {
            String fuelType = gasStove.getFuelType();
            if (StringUtils.isNotEmpty(fuelType)) {
                name.put(fuelType,new MonthData(gasStove.getFuelType(),gasStove.getFuelConsumption(),gasStove.getFuelUnit(),gasStove.getFuelYear(),TYPE_FUEL));
            }
            List<Fuel> fuels = fuelService.findByFilter(new Filter("gasstoveId", Operator.eq, gasStove.getId()));
            for(Fuel fuel:fuels){
            	name.put(fuel.getFuelType(),new MonthData(fuel.getFuelType(),fuel.getFuelConsumption(),fuel.getFuelUnit(),fuel.getFuelYear(),TYPE_FUEL));
            }
            name.put("煤气产生量",new MonthData("煤气产生量",gasStove.getAnnualOutput(),"万立方米",gasStove.getFuelYear(),TYPE_FUEL));
        }
        
    }
    /**设置原辅料
     * @param name
     * @param gasStove
     */
    private void setRawmaterials(Map<String, Object> name,RawMaterials rawmaterials) {
    	if (rawmaterials!=null) {
    		String getName = rawmaterials.getName();
    		if (StringUtils.isNotEmpty(getName)) {
    			name.put(getName, new MonthData(rawmaterials.getName(), rawmaterials.getConsumption(), rawmaterials.getUnit(),rawmaterials.getYear(),TYPE_RAW));
    		}
    	}
    	
    }
    
    class MonthData{
    	public String dataName;
    	public Double consumption;
    	public String unit;
    	public String year;
    	public String type;
    	
    	
    	
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getDataName() {
			return dataName;
		}
		public void setDataName(String dataName) {
			this.dataName = dataName;
		}
		public Double getConsumption() {
			return consumption;
		}
		public void setConsumption(Double consumption) {
			this.consumption = consumption;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		
		public MonthData(String dataName, Double consumption, String unit, String year, String type) {
			super();
			this.dataName = dataName;
			this.consumption = consumption;
			this.unit = unit;
			this.year = year;
			this.type = type;
		}
		
		
		
    	
    }
	
	
}
