package com.online.util;

/**
 * @author 作者 E-mail: 郑有权
 * @date 创建时间：2018年4月4日 下午4:27:55
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public class SimilarityRatio {

	private static int compare(String str, String target) {
		int d[][];// 矩阵
		int n = str.length();
		int m = target.length();
		int i;// 遍历str的
		int j;// 遍历target的
		char ch1; // str的
		char ch2; // target的
		int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new int[n + 1][m + 1];
		for (i = 0; i <= n; i++) { // 初始化第一列
			d[i][0] = i;
		}

		for (j = 0; j <= m; j++) { // 初始化第一行
			d[0][j] = j;
		}

		for (i = 1; i <= n; i++) { // 遍历str
			ch1 = str.charAt(i - 1);
			// 去匹配target
			for (j = 1; j <= m; j++) {
				ch2 = target.charAt(j - 1);
				if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				}
				// 左边+1,上边+1, 左上角+temp取最小
				d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
			}
		}
		return d[n][m];
	}

	private static int min(int one, int two, int three) {
		return (one = one < two ? one : two) < three ? one : three;
	}

	/**
	 *  * 获取两字符串的相似度  
	 */

	public static double getSimilarityRatio(String str, String target) {
		// 去除空白字符、换行、标点符号
		String regex = "[\\pP\\p{Punct}\\s]";
		return 1 - (double) compare(str.replaceAll(regex, ""), target.replaceAll(regex, ""))
				/ Math.max(str.length(), target.length());
	}

	public static void main(String[] args) {
//		String str = "论据容易理解,把它们分成小块,并以相似的格式和句子结构呈现每一个论据";
//		String target = "根据领域关系可以合并领域相同或相似的句子，得到句群及其领域";
		String str = "中科弘清发电公司方会计师的开发了坚实的看了附件是的开发积分福建省的咖啡机路上看非金属打飞机的发";
		String target = "弘清中公科北京司中科弘清发电公司方会计师的开发了坚实的看了附件是的开发积分福建省的咖啡机路上看非金属打飞机的发";
		SimilarityRatio lt = new SimilarityRatio();
		System.out.println("similarityRatio=" + lt.getSimilarityRatio(str, target));
	}

}
