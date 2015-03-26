package com.xchgx.cons;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.xchgx.domain.Article;
import com.xchgx.domain.Navigation;

/**
 * 整个应用通用的常量 <br>
 * <b>类描述:</b>
 *
 * <pre>
 * |
 * </pre>
 *
 * @see
 * @since
 */
public class CommonConstant extends HibernateDaoSupport {
	/**
	 * 用户对象放到Session中的键名称
	 */
	public static final String USER_CONTEXT = "USER_CONTEXT";

	/**
	 * 将登录前的URL放到Session中的键名称
	 */
	public static final String LOGIN_TO_URL = "toUrl";

	/**
	 * 每页的记录数
	 */
	public static final int PAGE_SIZE = 3;

	/**
	 * 线程管理，主要管理页面静态化进度线程。
	 */
	public static Map<String, Thread> threadMap = new HashMap<String, Thread>();
	
	private static Configuration hibernateConf = new Configuration();
	public static String tables[] = { "t_address", "t_article", "t_department",
			"t_department_type", "t_employee", "t_login_log",
			"t_luqustudent2014", "t_permission", "t_school", "t_section",
			"t_shortcut", "t_teacher", "t_user", "t_work", };
//	private static String navTempleteOnlyFirst = "<li class=\"bg\"></li><li>___cgnavigationfirstdatatemplete___</li>";
//	private static String navTempleteLi ="<li class=\"bg\"></li><li>___cgnavigationfirstdatatemplete___<div class=\"nav-w\"><div class=\"section-nav1\"><ul>___cgnavigationseconddatatemplete___</ul></div></div></li>";
//	private static String navTempleteUl = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\"	pageEncoding=\"UTF-8\"%><ul class=\"nav\"><li class=\"first\"><a href=\"___projectName___\">办学特色</a></li>___cgnavigationdatatemplete___<li class=\"bg\"></li></ul>";
	/**
	 * 获得Hibernate持久化类
	 * 
	 * @param clazz
	 * @return PersistentClass
	 */
	@SuppressWarnings("rawtypes")
	private static PersistentClass getPersistentClass(Class clazz) {
		synchronized (CommonConstant.class) {
			PersistentClass pc = hibernateConf.getClassMapping(clazz.getName());
			if (pc == null) {
				hibernateConf = hibernateConf.addClass(clazz);
				pc = hibernateConf.getClassMapping(clazz.getName());
			}
			return pc;
		}
	}

	/**
	 * 获得表名
	 * 
	 * @param clazz
	 *            映射到数据库的po类
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getTableName(Class clazz) {
		return getPersistentClass(clazz).getTable().getName();
	}

	/**
	 * 获得列名
	 * 
	 * @param clazz
	 *            映射到数据库的po类
	 * @param icol
	 *            第几列
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getColumnName(Class clazz, int icol) {
		return getPersistentClass(clazz).getTable().getColumn(icol).getName();
	}

	/**
	 * 获得所有列名
	 * 
	 * @param clazz
	 *            映射到数据库的po类
	 * @return List<String> 列名
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<String> getColumnNames(Class clazz) {
		Iterator<Column> itr = getPersistentClass(clazz).getTable()
				.getColumnIterator();
		List<String> columns = new ArrayList<String>();
		while (itr.hasNext()) {
			Column tmp = itr.next();
			columns.add(tmp.getName());
		}
		return columns;
	}

	public static String changeHtmlTag(String code) {
		code = code.replaceAll("&", "&amp;");
		code = code.replaceAll("<", "&lt;");
		code = code.replaceAll(">", "&gt;");
		code = code.replaceAll("\"", "&quot;");
		return code;
	}
	//  动态生成导航栏
	/**
	 * 动态生成导航栏。
	 * 
	 * @param navList
	 * @return
	 */
	public static boolean releaseAllNavigations(String projectName,String rootPath,
			List<Navigation> navList, Integer departmentId) {
		System.err
				.println(projectName+","+rootPath + "," + navList.size() + "," + departmentId);
		String workPath = rootPath + File.separator + "index"
				+ File.separator + "nav" + File.separator ;
		File file = new File(workPath + "navDpt"
				+ departmentId + ".jsp");

		try {
			 FileReader fr = new FileReader(workPath + "navTempleteUl.jsp");
			  BufferedReader bf = new BufferedReader(fr);
			  String temp = null;
			  String navTempleteUl = "";
			  String navTempleteOnlyFirst = "";
			  String navTempleteLi = ""; 
			  while ((temp=bf.readLine())!=null){
				  navTempleteUl+=temp.trim();
			  }
			bf.close();
			fr.close();
			
			fr = new FileReader(workPath + "navTempleteOnlyFirst.jsp");
			bf = new BufferedReader(fr);
			 while ((temp=bf.readLine())!=null){
				 navTempleteOnlyFirst+=temp.trim();
			  }
			 bf.close();
			 fr.close();
			 
			 fr = new FileReader(workPath + "navTempleteLi.jsp");
				bf = new BufferedReader(fr);
				 while ((temp=bf.readLine())!=null){
					 navTempleteLi += temp.trim();
				  }
			bf.close();
			fr.close();
				 
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file,false);//覆盖写入
			BufferedWriter bw = new BufferedWriter(fw);
			//sb.append(ul);
			String ul = ""; //所有导航栏目
			int count = 0;
			for(Navigation nav :  navList){		//遍历所有导航栏对象
				if(count >= 7) break;
				if(!nav.isDisplay()) continue;
				if(nav.getChildNavigations().size() > 0){		//存在子导航
					String target = nav.isTargetBlank()?"target=\"_blank\"":"";
					String a = "<a href=\""+nav.getUrl()+"\" "+target+"  title=\""+nav.getTip()+"\">"+nav.getName()+"</a>";//生成一级导航超链接
					String li = navTempleteLi.replaceAll("___cgnavigationfirstdatatemplete___", a);//从模板中替换成一级导航超链接
					String childNav = "";//为子导航栏做准备
					for(Navigation child : nav.getChildNavigations()){//遍历子导航栏
						if(!child.isDisplay())continue;
						String tg = nav.isTargetBlank()?"target=\"_blank\"":"";
						childNav +="<li><a href=\""+child.getUrl()+"\" "+tg+" title=\""+child.getTip()+"\">"+child.getName()+"</a></li>";//生成子导航列表
					}
					ul += li.replaceAll("___cgnavigationseconddatatemplete___", childNav);
				}else{//没有子导航
					String a = "<a href=\""+nav.getUrl()+"\" title=\""+nav.getTip()+"\">"+nav.getName()+"</a>";
					ul += navTempleteOnlyFirst.replaceAll("___cgnavigationfirstdatatemplete___", a);
				}
				count++;
			}
			String data = navTempleteUl.replaceAll("___cgnavigationdatatemplete___",ul);
			data  = data.replaceAll("___projectName___", projectName);
			bw.write(data);
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 反向解密，练习用的在这个项目中没有作用。
	 */
	/*
	 * public void aa(){ String c= "1c10121a181e121a0f1016110b4d4d4d";
	 * //4e1c4e4f4e4d4e1e4e474e1a4e4d4e1e4f194e4f4e494e4e4f1d4b1b4b1b4b1b String
	 * pass = ""+c.length()+","; for(int i=0;i<c.length(); i+=2){ String a =
	 * c.substring(i, i+1); String b = c.substring(i+1, i+2); String dcode =
	 * a+b; int d1 = Integer.parseInt(a, 16); int d2 = Integer.parseInt(b, 16);
	 * int d = d1*16+d2; System.out.print(d); d = 255-d;
	 * System.out.print("["+d+"]"); if(d < 128 ){ d+=128; }else if( d> 127){
	 * d-=128; } System.out.print("("+d+"){"+(char)d+"}-"); pass += (char)d;
	 * pass += ","+pass.length(); System.out.println();
	 * System.out.println(pass); } }
	 */
	
	/**
	 * 转换持久层List为静态List
	 * @param articleList
	 * @return Page
	 */
	public static List<Article> convertHbmArticlesListToArrList(List<Article> list){
		List<Article> artList = new ArrayList<Article>();
		for(Article a : list){
			Article article = new Article(a.getTitle(), a.getType(), a.getTitleStyle(), a.isDisplay(), a.getContent(), a.getDate(), a.getIp(), a.getDescription());
			article.setId(a.getId());
			artList.add(article);
		}
		return artList;
	}
	public static String getProjectName(){
		return "";
	}
}
