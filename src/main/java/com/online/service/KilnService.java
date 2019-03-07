package com.online.service;

import java.util.Date;
import java.util.List;

import com.online.entity.online.Kiln;
/**
 * 
 * @author zuozhiping
 * 
 * 窑炉服务接口
 *
 */
public interface KilnService extends BaseService<Kiln, Integer> {

	Kiln saveKiln(Kiln kiln, Integer governanceMeasures1,Integer governanceMeasures2,Integer governanceMeasures3
			,Integer exhaustionHoleId,Integer exhaustionHoleTailId);

	Kiln updateKiln(Kiln kiln, Integer governanceMeasures1,Integer governanceMeasures2,Integer governanceMeasures3
			,Integer exhaustionHoleId,Integer exhaustionHoleTailId);

    List<Kiln> findSourceNameById(Integer id);
    
    Kiln generalsave(Kiln kiln, String governanceMeasures1, String governanceMeasures2,
			String governanceMeasures3, String governanceMeasures4, Date putDate1, Date putDate2, Date putDate3,
			Date putDate4);
    Kiln updateGeneralKiln(Kiln kiln, String governanceMeasures1, String governanceMeasures2,
    		String governanceMeasures3, String governanceMeasures4, Date putDate1, Date putDate2, Date putDate3,
    		Date putDate4);
    
    List<Kiln> findByEnterpriseid(Integer enterpriseid);
    
    void deleteKiln(Integer id);

}