package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.DataAccessException;
import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.PDFTemplateDao;
import com.online.entity.online.PDFTemplateFile;
import com.online.service.PDFTemplateService;
/**
 * 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2017年9月5日 下午3:55:16 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return
 */
@Service("pdfTemplateServiceImpl")
public class PDFTemplateServiceImpl extends BaseServiceImpl<PDFTemplateFile, Integer> implements  PDFTemplateService {

	@Autowired PDFTemplateDao pdfTemplateDao;
	
	
	@Override
	public PDFTemplateFile findName(String enterpriseType,Integer projectId,Integer projectTypeId,String pdftemplateName) {
		/*// TODO Auto-generated method stub
		//郑有权
		return pdfTemplateDao.findName(name);*/
		
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("enterpriseType", Operator.eq, enterpriseType));
		filters.add(new Filter("projectId", Operator.eq, projectId));
		filters.add(new Filter("projectTypeId", Operator.eq, projectTypeId));
		if(StringUtils.isNotBlank(pdftemplateName)){
			filters.add(new Filter("pdftemplateName", Operator.eq, pdftemplateName));
		}else{
			filters.add(Filter.isNull("pdftemplateName"));
		}
		
		
		List<PDFTemplateFile> pdfTemplateFiles = findList(null, filters, null);
		if(pdfTemplateFiles !=  null &&pdfTemplateFiles.size()>0){
			return pdfTemplateFiles.get(0);
		}
		return null;
		
	}


	@Override
	public PDFTemplateFile savePDFTemplateFile(PDFTemplateFile pDFTemplateFile) throws Exception {
		//郑有权
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("enterpriseType", Operator.eq, pDFTemplateFile.getEnterpriseType()));
		filters.add(new Filter("projectId", Operator.eq, pDFTemplateFile.getProjectId()));
		filters.add(new Filter("projectTypeId", Operator.eq, pDFTemplateFile.getProjectTypeId()));
		filters.add(new Filter("pdftemplateName", Operator.eq, pDFTemplateFile.getPdftemplateName()));
		
		List<PDFTemplateFile> pdfTemplateFiles = findList(null, filters, null);
		if(pdfTemplateFiles !=  null &&pdfTemplateFiles.size()>0){
			throw new DataAccessException("该企业类型和项目类型模板已存在");
		}
		
		return save(pDFTemplateFile);
	}


	@Override
	public PDFTemplateFile updatePDFTemplateFile(PDFTemplateFile pdfTemplateFile) throws Exception {
		//郑有权
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(new Filter("enterpriseType", Operator.eq, pdfTemplateFile.getEnterpriseType()));
		filters.add(new Filter("projectId", Operator.eq, pdfTemplateFile.getProjectId()));
		filters.add(new Filter("projectTypeId", Operator.eq, pdfTemplateFile.getProjectTypeId()));
		
		List<PDFTemplateFile> pdfTemplateFiles = findList(null, filters, null);
		if(pdfTemplateFiles !=  null &&pdfTemplateFiles.size()>0){
			PDFTemplateFile pdfTemplateFileBack = pdfTemplateFiles.get(0);
			if(pdfTemplateFileBack.getEnterpriseType().equals(pdfTemplateFile.getEnterpriseType())
					&& pdfTemplateFileBack.getProjectId() == pdfTemplateFile.getProjectId()
					&& pdfTemplateFileBack.getProjectTypeId() == pdfTemplateFile.getProjectTypeId()){
				return update(pdfTemplateFile,"filename","url","pdftemplateName");
			}else{
				throw new DataAccessException("该企业类型和项目类型模板已存在");
			}
			
		}
		return update(pdfTemplateFile,"filename","url","pdftemplateName");
		
	}
	
    
    
}
