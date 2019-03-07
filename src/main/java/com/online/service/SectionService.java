package com.online.service;

import java.util.Date;
import java.util.List;

import com.online.entity.online.Section;
/**
 * 
 * @author zuozhiping
 * 
 * 工段服务接口
 *
 */
public interface SectionService extends BaseService<Section, Integer> {

	Section saveSection(Section section, Integer exhaustionHoleId, 
	        Integer governanceMeasures1,Integer governanceMeasures2,Integer governanceMeasures3,Integer governanceMeasures4);
	/**
	 *  保存简表工段
     * @param section
     * @param governanceMeasures1 脱硫工艺
     * @param governanceMeasures2除尘工艺
     * @param governanceMeasures3 脱销工艺
     * @param governanceMeasures4 voc措施
	 */
	Section saveSimpleSection(Section section,   String governanceMeasures1,
            String governanceMeasures2, String governanceMeasures3, String governanceMeasures4);
	
	Section saveGeneralSection(Section section, String governanceMeasures1,
			String governanceMeasures2, String governanceMeasures3, String governanceMeasures4
			,Date putDate1,Date putDate2,Date putDate3,Date putDate4);

	Section updateSection(Section section,Integer exhaustionHoleId,
	        Integer governanceMeasures1, 
	Integer governanceMeasures2, Integer governanceMeasures3, Integer governanceMeasures4);
	/**更新简表工段
	 * @param section
	 * @param governanceMeasures1
	 * @param governanceMeasures2
	 * @param governanceMeasures3
	 * @param governanceMeasures4
	 */
	Section updateSimpleSection(Section section, String governanceMeasures1, String governanceMeasures2, 
	        String governanceMeasures3, String governanceMeasures4);
	
	Section updateGeneralSection(Section section, String governanceMeasures1,
			String governanceMeasures2, String governanceMeasures3, String governanceMeasures4
			,Date putDate1,Date putDate2,Date putDate3,Date putDate4);
	
    List<Section> findSourceNameById(Integer id);
    
    List<Section> findByEnterpriseid(Integer enterpriseid);
    
    Section findByEnterpriseidAndProject(Integer enterpriseid,Integer projectId);

    void deleteSection(Integer id);
}