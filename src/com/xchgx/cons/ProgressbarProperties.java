package com.xchgx.cons;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * <p>进度条属性文件操作。</p>
 * @author cg
 * @version 1.0
 *
 */
public class ProgressbarProperties {
	/**
	    * 写入properties信息
	    * @param filePath  绝对路径（包括文件名和后缀名）
	    * @param parameterName  名称
	    * @param parameterValue 值
	    */
	 public static void writeProperties(String filePath,String parameterName,String parameterValue) {
		 Properties props = new Properties();
		 try {
			 //如果文件不存在，创建一个新的
			 File file=new File(filePath);
			 if(!file.exists()){
				 file.createNewFile();
			 }

			 InputStream fis = new FileInputStream(filePath);
			 // 从输入流中读取属性列表（键和元素对）
			 props.load(fis);
			 fis.close();
			 OutputStream fos = new FileOutputStream(filePath);
			 props.setProperty(parameterName, parameterValue);
			 // 以适合使用 load 方法加载到 Properties 表中的格式，
			 // 将此 Properties 表中的属性列表（键和元素对）写入输出流
			 props.store(fos, parameterName);
			 fos.close(); // 关闭流
		 } catch (IOException e) {
			 System.err.println("Visit "+filePath+" for updating "+parameterName+" value error");
		 }
	 }
	 /**
     *
     * @Title: readValue
     * @Description:   通过绝对路径获取properties文件属性，  根据key读取value
     * @param filePath  properties文件绝对路径（包括文件名和后缀）
     * @param key   属性key
     * @return String 返回value
     */
  public static String readValue(String filePath, String key){
      Properties props = new Properties();
      InputStream in=null;
      try{
          in = new BufferedInputStream(new FileInputStream(filePath));
          props.load(in);
          String value = props.getProperty(key);
          return value;
            
      }catch(Exception e){
          e.printStackTrace();
          return null;
      }finally{
          try {
              in.close();//-----------------------------------important
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }
}
