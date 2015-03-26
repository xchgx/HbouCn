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
 *  属性文件操作。
 * @author cg
 * @version 1.0
 */
public class PropertyFileOperation {
	private  String webPathProperty = "";
	private String ftlPath = "";
	private String indexPath = "";
	private String rootPath = "";
	private String basePath = "";
	public PropertyFileOperation() {
		File f = new File(this.getClass().getResource("/").getPath());
		File b = new File(f.getParent());
		this.rootPath = b.getParent();
		this.indexPath = rootPath+File.separator+"index";
		this.ftlPath = rootPath+File.separator+"WEB-INF"+File.separator+"ftl";
		this.webPathProperty = f.getParent()+File.separator+"webpath.properties";
		this.basePath = b.getParent().substring(b.getParent().lastIndexOf("/"),b.getParent().length());
		 //如果文件不存在，创建一个新的
		try {
			File file=new File(webPathProperty);
			if(!file.exists()){
				file.createNewFile();
				writeProperties("rootPath", getRootPath());
				writeProperties("basePath", getBasePath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
		public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

		public String getWebPathProperty() {
		return webPathProperty;
	}

	public void setWebPathProperty(String webPathProperty) {
		this.webPathProperty = webPathProperty;
	}

	public String getFtlPath() {
		return ftlPath;
	}

	public void setFtlPath(String ftlPath) {
		this.ftlPath = ftlPath;
	}

	public String getIndexPath() {
		return indexPath;
	}

	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

		/**
		    * 写入properties信息
		    * @param parameterName  名称
		    * @param parameterValue 值
		    */
		synchronized public void writeProperties(String parameterName,String parameterValue) {
			System.out.println(webPathProperty);
			 Properties props = new Properties();
			 try {
				 InputStream fis = new FileInputStream(webPathProperty);
				 // 从输入流中读取属性列表（键和元素对）
				 props.load(fis);
				 fis.close();
				 OutputStream fos = new FileOutputStream(webPathProperty);
				 props.setProperty(parameterName, parameterValue);
				 
				 // 以适合使用 load 方法加载到 Properties 表中的格式，
				 // 将此 Properties 表中的属性列表（键和元素对）写入输出流
				 props.store(fos, parameterName);
				 fos.close(); // 关闭流
			 } catch (IOException e) {
				 System.err.println("Visit "+webPathProperty+" for updating "+parameterName+" value error");
			 }
		 }
		 /**
	     *
	     * @Title: readValue
	     * @Description:   通过绝对路径获取properties文件属性，  根据key读取value
	     * @param key   属性key
	     * @return String 返回value
	     */
	synchronized  public String readValue(String key){
	      Properties props = new Properties();
	      InputStream in=null;
	      try{
	          in = new BufferedInputStream(new FileInputStream(webPathProperty));
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
