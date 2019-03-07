package com.online.excelexport;

public class ModuleWriteReturnBean {
	
	private int endCol;
	
	private int startCol;
	
	private int startRow;
	
	private int endRow;

	public int getEndCol() {
		return endCol;
	}

	public void setEndCol(int endCol) {
		this.endCol = endCol;
	}

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

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public ModuleWriteReturnBean(int endCol, int endRow) {
		super();
		this.endCol = endCol;
		this.endRow = endRow;
	}
	
	

}
