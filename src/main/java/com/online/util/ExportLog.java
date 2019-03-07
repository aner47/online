package com.online.util;

import java.util.List;
import java.util.Map;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2018年10月22日 上午10:05:17 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
public class ExportLog {
	
	//导入成功数量
	private Integer successNum;
	
	private Map<String, Integer> maps;
	
	private List<Fail> listFails;
	
	public Integer getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(Integer successNum) {
		this.successNum = successNum;
	}
	
	

	public Map<String, Integer> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, Integer> maps) {
		this.maps = maps;
	}

	public List<Fail> getListFails() {
		return listFails;
	}

	public void setListFails(List<Fail> listFails) {
		this.listFails = listFails;
	}






	public static class Fail{
		private Integer row;
		
		private String msg;

		public Integer getRow() {
			return row;
		}

		public void setRow(Integer row) {
			this.row = row;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Fail(Integer row, String msg) {
			super();
			this.row = row;
			this.msg = msg;
		}

		
		
		
	}

}


