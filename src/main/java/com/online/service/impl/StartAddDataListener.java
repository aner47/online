package com.online.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.online.entity.SystemConfig;

import code.BatchSqlTest;
import code.JdbcTest;

@Service
public class StartAddDataListener implements ApplicationListener<ContextRefreshedEvent>  {

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		SystemConfig systemConfig = entityManager.find(SystemConfig.class, 4);
		if(systemConfig != null){
			if(systemConfig.getSysValue().equals("1")){
				return ;
			}
		}
		try {
			batchSqlFile();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * 导入sql脚本
	 * @throws IOException 
	 */
	private void batchSqlFile() throws IOException{
		String[] sqlFiles = {"/data/config.sql","/data/user.sql","/data/menu.sql","/data/role.sql","/data/role_menu.sql","/data/user_role.sql"};
		List<String> sqList = new ArrayList<>();
		for (String sqlFile : sqlFiles) {
			String sql = FileUtils.readFileToString(new File(BatchSqlTest.class.getResource("/").getPath()+sqlFile),"utf8");
		     String sqlArr[] = sql.toString().split("(;\\s*\\rr\\n)|(;\\s*\\n)");
		     for(int i = 0; i<sqlArr.length; i++) {
		          String sql11 = sqlArr[i].replaceAll("--.*", "").trim();
		          if(!"".equals(sql11)) {
		        	  sqList.add(sql11);
		          }
		      }
		}
	    JdbcTest.excuteBatchSql(sqList);
	}

}
