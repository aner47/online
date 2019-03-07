package com.online.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 经纬度校验工具类
 */
public class LatitudeUtils {

	public static final String KEY_1 = "ZwvKDkc8AosMeMixrVVDfEqK";



	/**
	 * 返回输入地址的经纬度坐标 key lng(经度),lat(纬度)
	 */
	public static Map<String, String> getGeocoderLatitude(String address) {
		if (StringUtils.isEmpty(address))
			return null;
		BufferedReader in = null;
		try {
			address = URLEncoder.encode(address, "UTF-8");
			URL tirc = new URL("http://api.map.baidu.com/geocoder?address=" + address + "&output=json&key=" + KEY_1);
			in = new BufferedReader(new InputStreamReader(tirc.openStream(), "UTF-8"));
			String res;
			StringBuilder sb = new StringBuilder("");
			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			String str = sb.toString();
			Map<String, String> map = null;
			if (StringUtils.isNotEmpty(str)) {
				int lngStart = str.indexOf("lng\":");
				int lngEnd = str.indexOf(",\"lat");
				int latEnd = str.indexOf("},\"precise");
				if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
					String lng = str.substring(lngStart + 5, lngEnd);
					String lat = str.substring(lngEnd + 7, latEnd);
					map = new HashMap<String, String>();
					map.put("lng", lng);
					map.put("lat", lat);
					return map;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 计算地球上任意两点(经纬度)距离
	 * 
	 * @param long1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param long2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 */
	public static double Distance(double long1, double lat1, double long2, double lat2) {
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return d;
	}

	public static String getAreaCode(String lngs, String lats) throws IOException {
		String lng = null;
		String lat = null;
		try {
			lat = java.net.URLEncoder.encode(lats, "UTF-8");
			lng = java.net.URLEncoder.encode(lngs, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String lngAndlat = lat + "," + lng;
		String url = String.format("https://api.map.baidu.com/geocoder/v2/?ak=%s&location=%s&output=json&pois=1", KEY_1,
				lngAndlat);
		URL myURL = null;
		URLConnection httpsConn = null;
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		InputStreamReader insr = null;
		BufferedReader br = null;
		try {
			httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
			if (httpsConn != null) {
				insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				br = new BufferedReader(insr);
				String data = null;
				while ((data = br.readLine()) != null) {
					System.out.println(data);
					JSONObject para = JSONObject.parseObject(data);
					if (para != null) {
						String result = para.getString("result");
						JSONObject paras = JSONObject.parseObject(result);
						if (paras != null) {
							String addressComponent = paras.getString("addressComponent");
							JSONObject parasl = JSONObject.parseObject(addressComponent);
							if (parasl != null) {
//								return parasl.getString("adcode");
								return addressComponent;
							}
						}
					}
				}
			}
		} catch (IOException e) {
		} finally {
			if (insr != null) {
				insr.close();
			}
			if (br != null) {
				br.close();
			}
		}
		return "";
	}

	public static void main(String args[]) {
		Map<String, String> json = LatitudeUtils.getGeocoderLatitude("高新区");
		double di = Distance(118.901666666667, 24.3066666666667, Double.parseDouble(json.get("lng")),
				Double.parseDouble(json.get("lat")));
		System.out.println(json);
		System.out.println("lng : " + json.get("lng"));
		System.out.println("lat : " + json.get("lat"));
		System.out.println(di);
			try {
				long start = System.currentTimeMillis();
				String city = getAreaCode("117.901666666667", "24.3066666666667");
				System.out.println("耗时：：：："+(System.currentTimeMillis()-start));
				System.out.println(city);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}