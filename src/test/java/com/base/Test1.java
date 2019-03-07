package com.base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;


public class Test1 extends BaseTest {

	
	@Test
	public void test(){
		String string = "D:/user.sql";
		try {
			String readFileToString = FileUtils.readFileToString(new File(string));
			em.createNativeQuery("BEGIN  " + readFileToString + " END").executeUpdate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
