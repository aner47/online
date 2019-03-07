package com.online.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/** 
	* @author  作者 E-mail: 郑有权
	* @date 创建时间：2019年1月14日 下午5:15:37 
	* @version 1.0 
	* @parameter  
	* @since  
	* @return  
	*/
public class PropertyUtil {

	/** Logger */
	public final static Logger logger = Logger.getLogger(PropertyUtil.class);
	
//	 private static final Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
	    private static Properties props;
	    static{
	        loadProps();
	    }

	    synchronized static private void loadProps(){
	        logger.info("开始加载properties文件内容.......");
	        props = new Properties();
	        InputStream in = null;
	        try {
	        	in = PropertyUtil.class.getClassLoader().getResourceAsStream("zkhq.properties");
	 
	            props.load(in);
	        } catch (FileNotFoundException e) {
	            logger.error("zkhq.properties文件未找到");
	        } catch (IOException e) {
	            logger.error("出现IOException");
	        } finally {
	            try {
	                if(null != in) {
	                    in.close();
	                }
	            } catch (IOException e) {
	                logger.error("zkhq.properties文件流关闭出现异常");
	            }
	        }
	        logger.info("加载properties文件内容完成...........");
//	        logger.info("properties文件内容：" + props);
	    }

	    public static String getProperty(String key){
	        if(null == props) {
	            loadProps();
	        }
	        return props.getProperty(key);
	    }

	    public static String getProperty(String key, String defaultValue) {
	        if(null == props) {
	            loadProps();
	        }
	        return props.getProperty(key, defaultValue);
	    }
	    
}
