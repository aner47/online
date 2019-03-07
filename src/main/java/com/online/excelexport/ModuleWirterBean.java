package com.online.excelexport;

/**
 * 写入输入参数
 * @author DEV2
 *
 */
public class ModuleWirterBean {
	
	/**
	 * 起始列
	 */
	private int startCol;
	
	/**
	 * 起始行
	 */
	private int startRow;
	

	
	public int getStartCol() {
		return startCol;
	}

	public void setStartCol(int startCol) {
		this.startCol = startCol;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public ModuleWirterBean(int startCol, int startRow) {
		super();
		this.startCol = startCol;
		this.startRow = startRow;
	}

	public ModuleWirterBean(){
		
	}
	
	

}
