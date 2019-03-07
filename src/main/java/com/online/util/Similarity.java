package com.online.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author 作者 E-mail: 郑有权
 * @date 创建时间：2018年2月1日 上午10:42:36
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class Similarity {
	static Map<Character, int[]> vectorMap = new HashMap<Character, int[]>();

	static int[] tempArray = null;

	public static double SimilarityBack(String string1, String string2) {

		for (Character character1 : string1.toCharArray()) {
			if (vectorMap.containsKey(character1)) {
				vectorMap.get(character1)[0]++;
			} else {
				tempArray = new int[2];
				tempArray[0] = 1;
				tempArray[1] = 0;
				vectorMap.put(character1, tempArray);
			}
		}
		for (Character character2 : string2.toCharArray()) {
			if (vectorMap.containsKey(character2)) {
				vectorMap.get(character2)[1]++;
			} else {
				tempArray = new int[2];
				tempArray[0] = 0;
				tempArray[1] = 1;
				vectorMap.put(character2, tempArray);
			}
		}
		
		return sim();
		
	}

	// 求余弦相似度
	public static double sim() {
		double result = 0;
		result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
		return result;
	}

	private static double sqrtMulti(Map<Character, int[]> paramMap) {
		double result = 0;
		result = squares(paramMap);
		result = Math.sqrt(result);
		return result;
	}

	// 求平方和
	private static double squares(Map<Character, int[]> paramMap) {
		double result1 = 0;
		double result2 = 0;
		Set<Character> keySet = paramMap.keySet();
		for (Character character : keySet) {
			int temp[] = paramMap.get(character);
			result1 += (temp[0] * temp[0]);
			result2 += (temp[1] * temp[1]);
		}
		return result1 * result2;
	}

	// 点乘法
	private static double pointMulti(Map<Character, int[]> paramMap) {
		double result = 0;
		Set<Character> keySet = paramMap.keySet();
		for (Character character : keySet) {
			int temp[] = paramMap.get(character);
			result += (temp[0] * temp[1]);
		}
		return result;
	}

	public static void main(String[] args) {
		String s1 = "中科弘清发电公司方会计师的开发了坚实的看了附件是的开发积分福建省的咖啡机路上看非金属打飞机的发";
		String s2 = "弘清中公科北京司中科弘清发电公司方会计师的开发了坚实的看了附件是的开发积分福建省的咖啡机路上看非金属打飞机的发";
//		String s1 = "aaassdd";
//    	String s2 = "aaadd";
    	long l3 = System.currentTimeMillis();
		System.out.println(Similarity.SimilarityBack(s1, s2));
		 long l4 = System.currentTimeMillis();
	        System.out.println(l4-l3);
	}

}
