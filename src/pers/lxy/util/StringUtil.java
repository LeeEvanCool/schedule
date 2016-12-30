package pers.lxy.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;


/**
 * String工具类
 * 
 */
public class StringUtil {

	/**
	 * 字符串不为空
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isNotEmpty(String str){
		return ((str != null) && (str.trim().length() > 0));
	}
	
	/**
	 * 字符串为空
	 * 
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.trim().length() == 0));
	}
	
	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 *            生成字符串的长度
	 * @return
	 */
	public static String getRandomString(int length) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * 写日志信息
	 * 
	 * @param fileName
	 * @param sWord
	 *            void
	 */
	public static void logResult(String fileName, String sWord) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName, true));
			writer.write("\r\n" + sWord);
			writer.flush();
		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
		}
	}
	
	public static void logResult(String sWord) {
		logResult("/usr/local/tomcat/logs/wxpay.txt", sWord);
    }

	/**
	 * 字符串转为二进制字符串
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException String
	 */
	public static String stringToBinary(String str) throws UnsupportedEncodingException {
		if(isNotEmpty(str)) {
			char[] strChar=str.toCharArray();
	        String result="";
	        for(int i=0;i<strChar.length;i++){
	            result +=Integer.toBinaryString(strChar[i])+ " ";
	        }
	        return result;
		} else {
			return "";
		}
	}
	

	/**
	 * 二进制字符串转为正常字符串
	 * 
	 * @param str
	 * @return String
	 */

	public static String binaryToString(String str) {
		if(isNotEmpty(str)) {
			String result = "";
			String[] tempStr=StrToStrArray(str);
	        char[] tempChar=new char[tempStr.length];
	        for(int i=0;i<tempStr.length;i++) {
	            tempChar[i]=BinstrToChar(tempStr[i]);
	        }
	        result = String.valueOf(tempChar);
			return result;
		} else {
			return "";
		}
		
	}
	

	/**
	 * 字符串转为字符串数组，已空格区分 主要为了二进制字符串转换使用
	 * 
	 * @param str
	 * @return String[]
	 */
	public static String[] StrToStrArray(String str) {
        return str.split(" ");
    }
	
	/**
	 * 二进制字符串转换为字符
	 * 
	 * @param binStr
	 * @return char
	 */
	public static char BinstrToChar(String binStr){
        int[] temp=BinstrToIntArray(binStr);
        int sum=0;   
        for(int i=0; i<temp.length;i++){
            sum +=temp[temp.length-1-i]<<i;
        }   
        return (char)sum;
    }
	
	/**
	 *	二进制字符串转为数组
	 * 
	 * @param binStr
	 * @return int[]
	 */
	public static int[] BinstrToIntArray(String binStr) {       
        char[] temp=binStr.toCharArray();
        int[] result=new int[temp.length];   
        for(int i=0;i<temp.length;i++) {
            result[i]=temp[i]-48;
        }
        return result;
    }
}
