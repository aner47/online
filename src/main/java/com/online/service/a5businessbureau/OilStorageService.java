package com.online.service.a5businessbureau;

import com.online.entity.online.a5businessbureau.OilStorage;
import com.online.service.BaseService;
import com.online.service.ExportService;
/**
 * 市商务局-储油库信息
 */
public interface OilStorageService extends BaseService<OilStorage, Integer>,ExportService {
	
	public OilStorage saveOilStorage(OilStorage oilStorage) throws Exception;
	public OilStorage updateOilStorage(OilStorage oilStorage) throws Exception;

}