package com.online.service;

import java.util.Date;
import java.util.List;

import com.online.entity.online.BoilerInformation;
/**
 * 
 * @author zuozhiping
 * 
 * 锅炉信息服务接口
 *
 */
public interface BoilerInformationService extends BaseService<BoilerInformation, Integer> {

	
	
	/**
	 * 保存详表锅炉
	 * @author 郑有权
	 * @date 创建时间：2018年2月12日 下午4:15:46 
	 * @param boilerInformation
	 * @param governanceMeasures1
	 * @param governanceMeasures2
	 * @param governanceMeasures3
	 * @param exhaustionHoleId
	 * @return
	 */
	public BoilerInformation saveDetailBoilerInformation(BoilerInformation boilerInformation
			,Integer governanceMeasures1
			, Integer governanceMeasures2
			, Integer governanceMeasures3
			, Integer governanceMeasures4
			, Integer exhaustionHoleId,String allData);
	/**
	 * 更新详表锅炉
	 * @author 郑有权
	 * @date 创建时间：2018年2月12日 下午4:16:02 
	 * @param boilerInformation
	 * @param governanceMeasures1
	 * @param governanceMeasures2
	 * @param governanceMeasures3
	 * @param exhaustionHoleId
	 * @return
	 */
	public BoilerInformation updateDetailBoilerInformation(BoilerInformation boilerInformation
			,Integer governanceMeasures1
			, Integer governanceMeasures2
			, Integer governanceMeasures3
			, Integer governanceMeasures4
			, Integer exhaustionHoleId);
	
	/**
     * 保存简表锅炉
     * @author 郑有权
     * @date 创建时间：2018年2月12日 下午4:24:07 
     * @param boilerInformation
     * @param governanceMeasures1
     * @param governanceMeasures2
     * @param governanceMeasures3
     * @return
     */
    public BoilerInformation simplesave(BoilerInformation boilerInformation
			,String governanceMeasures1
			,String governanceMeasures2
			,String governanceMeasures3);
    /**
     * 更新简表锅炉
     * @author 郑有权
     * @date 创建时间：2018年2月12日 下午5:45:37 
     * @param boilerInformation
     * @param governanceMeasures1
     * @param governanceMeasures2
     * @param governanceMeasures3
     * @return
     */
    public BoilerInformation simpleupdate(BoilerInformation boilerInformation
    		,String governanceMeasures1
			,String governanceMeasures2
			,String governanceMeasures3);
	
    /**
     * 保存普查表锅炉
     * @author 郑有权
     * @date 创建时间：2018年2月12日 下午5:54:12 
     * @param boilerInformation
     * @param governanceMeasures1
     * @param governanceMeasures2
     * @param governanceMeasures3
     * @param putDate1
     * @param putDate2
     * @param putDate3
     * @return
     */
    public BoilerInformation generalsave(BoilerInformation boilerInformation
			, String governanceMeasures1
			, String governanceMeasures2
			,String governanceMeasures3
			,Date putDate1,Date putDate2,Date putDate3);
    /**
     * 更新普查表锅炉
     * @author 郑有权
     * @date 创建时间：2018年2月12日 下午5:54:36 
     * @param boilerInformation
     * @param governanceMeasures1
     * @param governanceMeasures2
     * @param governanceMeasures3
     * @param putDate1
     * @param putDate2
     * @param putDate3
     * @return
     */
    public BoilerInformation generalupdate(BoilerInformation boilerInformation,String governanceMeasures1,String governanceMeasures2,
			String governanceMeasures3,Date putDate1,Date putDate2,Date putDate3);
	

    public List<BoilerInformation> findSourceNameById(Integer id);
    
    
   
}