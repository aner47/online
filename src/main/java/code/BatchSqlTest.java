package code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;


public class BatchSqlTest {
	
	public static void main(String[] args) {
		String sql = "";
		try {
			sql = FileUtils.readFileToString(new File(BatchSqlTest.class.getResource("/").getPath()+"/data/config.sql"),"utf8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> sqList = new ArrayList<>();
	     String sqlArr[] = sql.toString().split("(;\\s*\\rr\\n)|(;\\s*\\n)");
	     for(int i = 0; i<sqlArr.length; i++) {
	          String sql11 = sqlArr[i].replaceAll("--.*", "").trim();
	          if(!"".equals(sql11)) {
	        	  sqList.add(sql11);
	          }
	      }
		
		System.out.println(sql);
	}

}
