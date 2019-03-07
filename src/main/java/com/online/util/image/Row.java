package com.online.util.image;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Row {
	
	int width;
	
	List<Col> cols = new ArrayList<>();
	
	
	public Row(int width, List<Col> cols) {
		super();
		this.width = width;
		this.cols = cols;
	}


	public void drawRow( Graphics g,int x,int y){
		int itemW = width/cols.stream().mapToInt(o1->o1.colspan).sum();
		int cur = 0;
		for (int i = 0; i < cols.size(); i++) {
			Col col = cols.get(i);
			int colLength = col.toString().length();
			int stage = 28;		//每行最长字数
			int coly = 15;		//y坐标下移尺寸
			if(colLength>stage){
				int row = colLength%stage == 0?colLength/stage:colLength/stage+1;
				for(int k=0;k<row;k++){
					if(stage*k+stage>colLength){
						g.drawString(col.toString().substring(stage*k), x+cur*itemW, y+k*coly);
					}else{
						g.drawString(col.toString().substring(stage*k,stage*k+stage), x+cur*itemW, y+k*coly);
					}
				}
				
				//g.drawString(col.toString().substring(0,30), x+cur*itemW, y);
				//g.drawString(col.toString().substring(30,60), x+cur*itemW, y+15);
				//g.drawString(col.toString().substring(60), x+cur*itemW, y+30);
			}else{
				g.drawString(col.toString(), x+cur*itemW, y);
			}
			
			cur += col.colspan;
		}
	}
	
}
