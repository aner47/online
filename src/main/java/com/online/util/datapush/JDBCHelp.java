package com.online.util.datapush;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import org.apache.commons.lang.StringUtils;

public class JDBCHelp {
	private String url;
	private String user;
	private String password;
	public Connection conn;
	public PreparedStatement ps;
	public Statement st;

	public JDBCHelp(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public JDBCHelp() {
		super();
	}

	// 连接数据库的方法
	public void getConnection() {
		if (conn == null) {
			try {
				// 根据数据库连接字符，名称，密码给conn赋值
				conn = DriverManager.getConnection(url, user, password);

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将ResultSet结果集中的记录映射到Map对象中.
	 * 
	 * @param fieldClassName
	 *            是JDBC API中的类型名称,
	 * @param fieldName
	 *            是字段名，
	 * @param rs
	 *            是一个ResultSet查询结果集,
	 * @param fieldValue
	 *            Map对象,用于存贮一条记录.
	 * @throws SQLException
	 */
	private void _recordMappingToMap(String fieldClassName, String fieldName, ResultSet rs,
			Map<String, Object> fieldValue) throws SQLException {
		fieldName = fieldName.toLowerCase();
		// 优先规则：常用类型靠前
		if (Objects.equals("java.lang.String",fieldClassName)) {
			String s = rs.getString(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.lang.Integer",fieldClassName)) {
			int s = rs.getInt(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);// 早期jdk需要包装，jdk1.5后不需要包装
			}
		} else if (Objects.equals("java.lang.Long",fieldClassName)) {
			long s = rs.getLong(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.lang.Boolean",fieldClassName)) {
			boolean s = rs.getBoolean(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.lang.Short",fieldClassName)) {
			short s = rs.getShort(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.lang.Float",fieldClassName)) {
			float s = rs.getFloat(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.lang.Double",fieldClassName)) {
			double s = rs.getDouble(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.sql.Timestamp",fieldClassName)) {
			java.sql.Timestamp s = rs.getTimestamp(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.sql.Date",fieldClassName) || Objects.equals("java.util.Date",fieldClassName)) {
			java.util.Date s = rs.getDate(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.sql.Time",fieldClassName)) {
			java.sql.Time s = rs.getTime(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.lang.Byte",fieldClassName)) {
			byte s = rs.getByte(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, new Byte(s));
			}
		} else if (Objects.equals("[B",fieldClassName) || Objects.equals("byte[]",fieldClassName)) {
			// byte[]出现在SQL Server中
			byte[] s = rs.getBytes(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.math.BigDecimal",fieldClassName)) {
			BigDecimal s = rs.getBigDecimal(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.lang.Object",fieldClassName) || Objects.equals("oracle.sql.STRUCT",fieldClassName)) {
			Object s = rs.getObject(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.sql.Array",fieldClassName) || Objects.equals("oracle.sql.ARRAY",fieldClassName)) {
			java.sql.Array s = rs.getArray(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.sql.Clob",fieldClassName)) {
			java.sql.Clob s = rs.getClob(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else if (Objects.equals("java.sql.Blob",fieldClassName)) {
			java.sql.Blob s = rs.getBlob(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		} else {// 对于其它任何未知类型的处理
			Object s = rs.getObject(fieldName);
			if (rs.wasNull()) {
				fieldValue.put(fieldName, null);
			} else {
				fieldValue.put(fieldName, s);
			}
		}
	}

	public List<Map<String, Object>> sqlToList(String sql, Predicate<String> predicate) throws SQLException {
		getConnection();
		st = (Statement) conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(rs == null){
			return null;
		}
		ResultSetMetaData rsmd = rs.getMetaData();
		int fieldCount = rsmd.getColumnCount();
		List<Map<String, Object>> records = new ArrayList<>();
		while (rs.next()) {
			Map<String, Object> valueMap = new LinkedHashMap<String, Object>();
			for (int i = 1; i <= fieldCount; i++) {
				String fieldClassName = rsmd.getColumnClassName(i);
				String fieldName = rsmd.getColumnName(i);
				if (predicate.test(fieldName)) {// 不符合
					_recordMappingToMap(fieldClassName, fieldName, rs, valueMap);
				}
			}
			records.add(valueMap);
		}
		return records;
	}
	
	
	public List<Map<String, Object>> resultToMap(ResultSet rs) throws SQLException{
		ResultSetMetaData rsmd = rs.getMetaData();
		int fieldCount = rsmd.getColumnCount();
		List<Map<String, Object>> records = new ArrayList<>();
		while (rs.next()) {
			Map<String, Object> valueMap = new LinkedHashMap<String, Object>();
			for (int i = 1; i <= fieldCount; i++) {
				String fieldClassName = rsmd.getColumnClassName(i);
				String fieldName = rsmd.getColumnName(i);
					_recordMappingToMap(fieldClassName, fieldName, rs, valueMap);
			}
			records.add(valueMap);
		}
		return records;
		
		
	}

	public List<String> getColNames(String tableName, Predicate<String> predicate) throws SQLException {
		String sql = "select * from " + tableName + " limit 0,1";
		getConnection();
		Statement st = conn.createStatement(); // 创建用于执行静态sql语句的Statement对象，st属局部变量
		ResultSet rs = st.executeQuery(sql); // 执行sql查询语句，返回查询数据的结果集
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		List<String> coList = new ArrayList<>();
		for (int i = 1; i <= columnCount; i++) {
			String name = rsmd.getColumnName(i);
			if (predicate.test(name)) {
				coList.add(rsmd.getColumnName(i));
			}
		}
		System.out.println(coList);
		return coList;
	}

	public void close() {
		System.out.println("关闭链接！！");
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void finalize() throws Throwable {
		close();
		super.finalize();
	}

	public String insert(String tableName, Map<String, Object> rowData, List<String> names) {
		return insert(tableName, rowData, names, new HashMap<>());
	}

	/**
	 * 插入sql 语句
	 * 
	 * @param tableName
	 * @param rowData
	 * @param names
	 * @param defaultValue
	 * @return
	 */
	public String insert(String tableName, Map<String, Object> rowData, List<String> names,
			Map<String, Object> defaultValue) {
		StringBuilder resultStr = new StringBuilder();
		StringBuilder sBuilder = new StringBuilder("insert into ");
		StringBuilder pBuilder = new StringBuilder();// 占位符
		sBuilder.append(tableName).append(" (");
		for (String string : names) {
			sBuilder.append(string).append(",");
			pBuilder.append("?,");
		}
		sBuilder.delete(sBuilder.length() - 1, sBuilder.length());
		pBuilder.delete(pBuilder.length() - 1, pBuilder.length());
		sBuilder.append(") values(").append(pBuilder.toString()).append(")");
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sBuilder.toString());
			for (int i = 0; i < names.size(); i++) {
				String string = names.get(i);
				Object object = rowData.get(string);
				if (object == null) {
					object = defaultValue.get(string);
				}
				setParamter(ps, object, i + 1);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
		  e.printStackTrace();
		  System.out.println("插入失败表= "+tableName+",id="+rowData.get("id"));
			resultStr.append(rowData.get("id")).append("插入失败;" + e.getMessage() + "\n");
		}
		return resultStr.toString();
	}

	/**
	 * 更新sql 语句
	 * 
	 * @param tableName 表名
	 * @param rowData 数据
	 * @param names 需要跟新的字段列表
	 * @param defaultValue 默认值
	 * @return
	 */
	public String update(String tableName, Map<String, Object> rowData, List<String> names,
			Map<String, Object> defaultValue,String condition) {
		StringBuilder resultStr = new StringBuilder();
		StringBuilder sBuilder = new StringBuilder(" update ");
		sBuilder.append(tableName).append(" set ");
		for (String string : names) {
			sBuilder.append(string).append("=:").append(string).append(",");
		}
		sBuilder.delete(sBuilder.length() - 1, sBuilder.length());
		if(StringUtils.isNotEmpty(condition)){
			sBuilder.append(" where ").append(condition);
		}
		try {
			NamedParamSqlUtil.fillParameters(conn, sBuilder.toString(), rowData, defaultValue).executeUpdate();
		} catch (SQLException e) {
			resultStr.append(rowData.get("id")).append("更新失败;" + e.getMessage() + "\n");
		}
		return resultStr.toString();
	}
	
	
	public void excuteSql(String sql ,Map<String, Object> param) throws SQLException{
		getConnection();
		PreparedStatement fillParameters = NamedParamSqlUtil.fillParameters(conn, sql, param, null);
		ResultSet rs = fillParameters.executeQuery();
		List<Map<String, Object>> resultToMap = resultToMap(rs);
		System.out.println(resultToMap);
		
	}

	/**
	 * 将ResultSet结果集中的记录映射到Map对象中.
	 * 
	 * @param fieldClassName
	 *            是JDBC API中的类型名称,
	 * @param fieldName
	 *            是字段名，
	 * @param rs
	 *            是一个ResultSet查询结果集,
	 * @param fieldValue
	 *            Map对象,用于存贮一条记录.
	 * @throws SQLException
	 */
	private void setParamter(PreparedStatement ps, Object value, int index) throws SQLException {
		ps.setObject(index, value);
	}

	// 测试能否与oracle数据库连接成功
	public static void main(String[] args) throws SQLException {

		String url = "jdbc:mysql://192.168.1.32:3306/online?useUnicode=true&characterEncoding=UTF-8";
		String user = "root";
		String password = "0bee89b07a248e27c83";
		JDBCHelp jdbc_Test = new JDBCHelp(url, user, password);
		
		jdbc_Test.excuteSql("SELECT * FROM ol_project WHERE id = 30",null);
//		jdbc_Test.getConnection();
//		String sql = "update product set  name = :name where id :id";
/*		Map<String,Object> rowData = new HashMap<>();
		
		rowData.put("id", 1);
		rowData.put("name", "苹果8");
		List<String> names = Arrays.asList("name","id");
		
		String update = jdbc_Test.update("product", rowData, names, null," id = :id");
		
		System.out.println(update);*/

		

	}
}
