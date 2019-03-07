package com.online.util.image;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.entity.BaseEntity;
import com.online.entity.online.Enterprise;
import com.online.entity.online.PhotoFile;
import com.online.entity.online.Project;
import com.online.excelexport.BeanUtils;
import com.online.service.PhotoFileService;
import com.online.service.SystemConfigService;
import com.online.util.Constants;
import com.online.util.SpringUtils;

import cn.hutool.core.util.ImageUtil;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2019年1月9日 下午8:02:36 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
@Service
public class DrawSavePicService {
	
	@Autowired
	private PhotoFileService  photoFileService ;
	
	
	public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }





	public void drawPic(Map<String, String> map,String enterpriseName,String head,String pathname){
		File file = new File(pathname);
		if(!file.exists()){
			file.mkdirs();
		}
		map = sortMapByKey(map);
		
		List<com.online.util.image.Row> lines = new ArrayList<>();
		String[] label = new String[map.size()+1];
		String[] value = new String[map.size()+1];
		label[0] = "企业名称";
		value[0] = enterpriseName;
		int i = 1;
		for(Entry<String,String> entry : map.entrySet()){
			
			label[i] = entry.getKey().substring(entry.getKey().indexOf("_")+1);
			value[i] = entry.getValue();
			i++;
		}
		
		int colNum = 2;
		int rowNum = label.length%colNum == 0?label.length/colNum:label.length/colNum+1;
		for(int y= 0;y<rowNum;y++){
			List<Col> cols = new ArrayList<>();
			int colLength = (y*colNum)+colNum<label.length?(y*colNum)+colNum:label.length;
			for(int j =y*colNum;j<colLength;j++){
				cols.add(new Col(label[j],value[j],colNum));
			}
			if(cols.size() == 0){
				continue;
			}
			com.online.util.image.Row row = new com.online.util.image.Row(800, cols);
			lines.add(row);
		}
		DrawPic.writeImage(file, lines, head);
		ImageUtil.pressText(file, file, "中科弘清", new Color(10, 10, 10), new Font("黑体", 1, 150), 0, 0, 0.08f);
	}
	@Autowired
	private SystemConfigService systemConfigService;
	
	public void savePhoto(String fileName,String url,Project project,Enterprise enterprise,String departmentType){
		PhotoFile pFile = new PhotoFile();
        pFile.setFilename(fileName);
        pFile.setUrl(url);
        pFile.setProject(project);
        pFile.setEnterprise(enterprise);
        pFile.setDepartmentType(departmentType);
        photoFileService.save(pFile);
	}
	
	public static class Node{
		String mode;
		String type;
		public Node(String mode, String type) {
			super();
			this.mode = mode;
			this.type = type;
		}
		
	}
	static Map<String,Node> map =new HashMap<>();
	static{
		map.put("HouseBuildSite", new Node("住建委房屋建筑工地信息",Constants.PHOTO_TYPE_ZJWFANGWUJIANZHUGONGDI));
		map.put("BetonStirStation", new Node("住建委混凝土搅拌站信息",Constants.PHOTO_TYPE_ZJWJIAOBANZHAN));
		map.put("CityMaterialsStation", new Node("住建委全市道路施工材料站",Constants.PHOTO_TYPE_ZJWCAILIAOZHAN));
		map.put("CityBuildSite", new Node("住建委市政项目建筑工地信息",Constants.PHOTO_TYPE_ZJWSHIZHENGJIANZHUGONGDI));
		map.put("Catering", new Node("城管局餐饮行业信息",Constants.PHOTO_TYPE_CGJCANYIN));
		map.put("BarbecueRestaurant", new Node("城管局烧烤店统计信息",Constants.PHOTO_TYPE_CGJSHAOKAODIAN));
		map.put("PassengerStation", new Node("交通局客运站/货运物流中心",Constants.PHOTO_TYPE_JTJKEYUNZHAN));
		map.put("FoodDrugCatering", new Node("食药监局餐饮行业信息",Constants.PHOTO_TYPE_SYJJCANYIN));
		map.put("OilStorage", new Node("商务局储油库信息",Constants.PHOTO_TYPE_SWJCHUYOUKU));
		map.put("PetrolGasStation", new Node("商务局加油站/加气站信息",Constants.PHOTO_TYPE_SWJJIAYOUZHAN));
		map.put("LivestockBreeding", new Node("农业委畜禽养殖信息",Constants.PHOTO_TYPE_NYWXUQINYANGZHI));
		map.put("InCoCatering", new Node("工商局餐饮企业工商登记信息",Constants.PHOTO_TYPE_GSJCANYIN));
		map.put("InCoBreakdownService", new Node("工商局汽修企业工商登记信息",Constants.PHOTO_TYPE_GSJQIXIU));
		map.put("InCoDryClean", new Node("工商局干洗企业工商登记信息",Constants.PHOTO_TYPE_GSJGANXI));
		map.put("CeitEnterprise", new Node("经信委工业企业名单",Constants.PHOTO_TYPE_JXWGONGYE));
		map.put("EnterprisePower", new Node("供电局企业用电信息",Constants.PHOTO_TYPE_GDJQIYEYONGDIAN));
		map.put("PowerPlantMonth", new Node("供电局电厂发电分月统计信息",Constants.PHOTO_TYPE_GDJFADIANMEIYUE));
		map.put("BoilerQs", new Node("质监局锅炉信息表",Constants.PHOTO_TYPE_ZJJGUOLU));
		map.put("EnterpriseFuelGas", new Node("燃气公司工业企业燃气消耗量信息",Constants.PHOTO_TYPE_RQGSGONGYEXIAOHAO));
		map.put("EnterpriseFuelGasMonth", new Node("燃气公司工业企业燃气消耗年分月统计表",Constants.PHOTO_TYPE_RQGSGONGYEXIAOHAOMEIYUE));
	}
	

	public  <T extends BaseEntity> void  saveImg(List<T> lists){
		
		String rel = SpringUtils.getRequest().getSession().getServletContext().getRealPath("/");
		String simpleDataFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(lists != null && lists.size()>0){
			String save_path = systemConfigService.findBySysKey("save_path").getSysValue()+File.separator+"batch"+File.separator;
			String uplaod_path = systemConfigService.findBySysKey("uplaod_path").getSysValue()+ File.separator;
			Node node = map.get(lists.get(0).getClass().getSimpleName());
			for (T t:lists) {
				Map<String, String> map = BeanUtils.getObj(t);
			//	String entperiseName = BeanUtils.getPropertyByName(t, "enterprise.name");
				Enterprise enterprise = (Enterprise) BeanUtils.getValueByName(t, "enterprise");
				if (enterprise != null) {
					
				
				String fileName = enterprise.getName()+".png";
				String path = save_path+simpleDataFormat+File.separator+UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."),fileName.length());
				String realPath = rel+path;
				String url = uplaod_path+path;
				drawPic(map, enterprise.getName(), node.mode,realPath);
				savePhoto(fileName, url, SpringUtils.getCurrentProject(), enterprise, node.type);
				}
			}
		}
	}
	

}
