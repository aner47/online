package com.online.util.datapush;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NamedParamSqlUtil {

	static final Log log = LogFactory.getLog(NamedParamSqlUtil.class);

	/**
	 * 分析处理带命名参数的SQL语句。使用Map存储参数，然后将参数替换成? <br/>
	 * 作者：wallimn 时间：2009-1-8 下午12:14:10<br/>
	 * 邮件：wallimn@sohu.com<br/>
	 * 博客：http://blog.csdn.net/wallimn<br/>
	 * 参数：<br/>
	 * 
	 * @param sql
	 * @return
	 */
	public static String parseSql(String sql , Map<Integer, String> paramsMap) {
		String regex = "(:(\\w+))";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(sql);
		paramsMap.clear();
		int idx = 1;
		while (m.find()) {
			// 参数名称可能有重复，使用序号来做Key
			paramsMap.put(new Integer(idx++), m.group(2));
			// System.out.println(m.group(2));
		}
		String result = sql.replaceAll(regex, "?");
		return result;
	}
	public static PreparedStatement fillParameters(Connection connection,String sql, Map<String, Object> pMap,Map<String, Object> defaultValue) throws SQLException {
		String paramName = null;
		Object paramValue = null;
		Map<Integer,String> paramsMap = new HashMap<>();
		sql = parseSql(sql, paramsMap);
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		for (Entry<Integer, String> entry:paramsMap.entrySet()) {
			paramName = entry.getValue();
			Integer idx = entry.getKey().intValue();
			// 不包含会返回null
			
			System.out.println("idx=="+ idx);
			System.out.println("paramName=="+ paramName);
			paramValue = pMap.get(paramName);
			try {
				// paramValue为null，会出错吗？需要测试
				if(paramValue == null){
					paramValue = defaultValue.get(paramName);
				}
				prepareStatement.setObject(idx, paramValue);
			} catch (Exception e) {
				log.error("填充参数出错，原因：" + e.getMessage());
				return null;
			}
		}
		return prepareStatement;
	}


	
	
}