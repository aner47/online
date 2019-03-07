package com.online.util.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import cn.hutool.core.util.ImageUtil;

public class DrawPic {

	public static void main(String[] args) {
		String filePath = "d:/javaPic.png";
		File file = new File(filePath);
		String[] label = {"企业名称"};
		String[] value = {"发发城际新苑（万科环球村）4.1期53#栋、54#栋、59#-63#栋、65#栋、66#栋、大门及大堂、69#栋（幼儿园）、S4-1#商业及4.1B地下室"};
		List<Row> lines = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			List<Col> cols = new ArrayList<>();
			double random = Math.random()*100;
			System.out.println(random);
			for (int j = 0; j < 1; j++) {
				cols.add(new Col(label[j%3], value[j%3],j==0?2:1));
			}
			if(cols.size() == 0){
				continue;
			}
			Row row = new Row(850, cols);
			lines.add(row);
		}
		writeImage(file, lines, "测试信息");
		ImageUtil.pressText(file, file, "中科弘清", new Color(10, 10, 10), new Font("黑体", 1, 150), 0, 0, 0.08f);
	}

	public static boolean writeImage(File file, List<Row> lines, String head) {
		BufferedImage bi = new BufferedImage(850, 600, BufferedImage.TYPE_INT_BGR);
		Graphics g = bi.getGraphics();
		try {
			if (file.exists()) {
				file.delete();
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		writeHeadImage(bi, g, head);
		g.setFont(new Font("黑体", 1, 14));
		for (int i = 0; i < lines.size(); i++) {
			lines.get(i).drawRow(g, 10, 120 + i * 50);
		}
		g.dispose();
		try {
			boolean val = ImageIO.write(bi, "png", file);
			return val;
		} catch (IOException e) {
			return false;
		}

	}

	/** 通过指定参数写一个图片 */
	public static void writeHeadImage(BufferedImage bi, Graphics g, String head) {
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, 849, 600);// 使用白色背景图片
		g.setColor(new Color(3, 3, 3));// 使用黑色字体
		g.drawRect(0, 0, 849, 599);// 画边框
		g.setFont(new Font("黑体", 1, 30));
		g.drawString(head, 20, 60);
		
		

	}

}
