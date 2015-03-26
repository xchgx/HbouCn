package com.xchgx.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xchgx.domain.Article;
import com.xchgx.domain.DepartmentType;
import com.xchgx.domain.Navigation;
import com.xchgx.domain.Section;
import com.xchgx.service.ArticleService;
import com.xchgx.service.DepartmentService;
import com.xchgx.service.DepartmentTypesService;
import com.xchgx.service.EmployeeService;
import com.xchgx.service.FreeMarkertUtil;
import com.xchgx.service.InitDatabaseService;
import com.xchgx.service.NavigationService;
import com.xchgx.service.PermissionService;
import com.xchgx.service.SchoolService;
import com.xchgx.service.SectionService;
import com.xchgx.service.ShortcutService;
import com.xchgx.service.TeacherService;
import com.xchgx.service.UserService;
import com.xchgx.zsbwork.bean.Department;

@Controller
@RequestMapping("/manager/site/html")
public class DepartmentPageToHtmlThread    {

	@Autowired
	private InitDatabaseService initDatabaseService;
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private DepartmentTypesService departmentTypesService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ShortcutService shortcutService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private NavigationService navigationService;
	@Autowired
	private FreeMarkertUtil freeMarkertUtil; 
	
//	// 获取进度百分比
//	/**
//	 * 通过部门ID生成静态部门页面
//	 * @param request 默认请求对象
//	 * @param departmentId 部门ID
//	 * @param startorend 开启或者关闭静态化进度
//	 * @return Map对象
//	 */
//	@RequestMapping(value="/getDepartmentPageDataToHtmlPercentage", method={RequestMethod.GET})
//	public @ResponseBody String  getDepartmentPageDataToHtmlPercentage(HttpServletRequest request,Integer departmentId){
//		System.out.println("getDepartmentPageDataToHtmlPercentage:"+departmentId);
//		List<Integer> secIds = sectionService.getAllSectionIdsByDepartmentId(departmentId);
//		Integer totalCount = secIds.size() + 5;
//		String primaryKey = "department"+departmentId;
//		System.out.println("secIds:"+secIds.size());
//		String rootPath  =  request.getServletContext().getRealPath("/");
//		String properFile = rootPath+"/WEB-INF/properties/department/departmentHtml.properties";
//		File pf = new File(properFile);
//		if(!pf.exists()){
//			try {
//				pf.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		String dhValue = ProgressbarProperties.readValue(properFile,primaryKey );
//		Integer percent = 100;
//		if(dhValue == null){
//			ProgressbarProperties.writeProperties(properFile, primaryKey, "100");
//			dhValue = "100";
//		}else{
//			try{
//				percent =Integer.parseInt(dhValue);
//			}catch(NumberFormatException n){
//				System.out.println("dhValue:"+dhValue+"不是数字");
//			}
//		}
//		if(percent>=100){
//			Integer per = 0;
//			ProgressbarProperties.writeProperties(properFile, primaryKey, ""+per/totalCount*100);
//			releaseFreemarkerDepartmentIndex(request, departmentId);
//			per++;
//			ProgressbarProperties.writeProperties(properFile, primaryKey, ""+per/totalCount*100);
//			releaseFreemarkerDepartmentWebPath(request, departmentId);
//			per++;
//			ProgressbarProperties.writeProperties(properFile, primaryKey, ""+per/totalCount*100);
//			releaseFreemarkerDepartmentNav(request, departmentId);
//			per++;
//			ProgressbarProperties.writeProperties(properFile, primaryKey, ""+per/totalCount*100);
//			releaseFreemarkerDepartmentSections(request, departmentId);
//			per++;
//			ProgressbarProperties.writeProperties(properFile, primaryKey, ""+per/totalCount*100);
//			releaseFreemarkerDepartmentArticles(request, departmentId);
//			per++;
//			ProgressbarProperties.writeProperties(properFile, primaryKey, ""+per/totalCount*100);
//			for(Integer id : secIds){
//				releaseFreemarkerSectionArticles(request, id);
//				per++;
//				ProgressbarProperties.writeProperties(properFile, primaryKey, ""+per/totalCount*100);
//			}
//			ProgressbarProperties.writeProperties(properFile, primaryKey, ""+ 100);
//			return "100";
//		}else{
//			return ProgressbarProperties.readValue(properFile, primaryKey);
//		}
//	}
	/**
	 *  静态化部门页面的总任务量。
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "getDepartmentTotalPercentage", method = { RequestMethod.GET })
	public @ResponseBody int getDepartmentTotalPercentage(
			Integer departmentId) {
		return  sectionService.getAllSectionIdsByDepartmentId(departmentId).size()*2+6+articleService.getAllArticleIdsByDepartmentId(departmentId).size()*2;
	}
	/**
	 *  通过部门ID获得部门所有文章的ID list
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "getAllArticleIdsByDepartmentId", method = { RequestMethod.GET })
	public @ResponseBody List<Integer> getAllArticleIdsByDepartmentId(Integer departmentId){
		return articleService.getAllArticleIdsByDepartmentId(departmentId);
	}
//	/**
//	 * 判断进度是否结束。
//	 * @param request
//	 * @param departmentId
//	 * @param secIds
//	 * @return
//	 */
//	private Integer htmlProgressIsEnd(Integer departmentId, List<Integer> secIds, HttpSession session){
//		System.out.println("进入到判断静态化进程是否结束。。。"+departmentId);
//		System.out.println("htmlProgressIsEnd... session= "+session);
//		Integer count = 0;
//		if(null == session.getAttribute("releaseFreemarkerDepartmentIndex"+departmentId)
//				|| "" == session.getAttribute("releaseFreemarkerDepartmentIndex"+departmentId)){
//			System.out.println("部门首页进度未初始化。"+departmentId);
//			session.setAttribute("releaseFreemarkerDepartmentIndex"+departmentId, 100);
//		}
//		if( null == session.getAttribute("releaseFreemarkerDepartmentWebPath"+departmentId)
//				|| "" == session.getAttribute("releaseFreemarkerDepartmentWebPath"+departmentId)){
//			System.out.println("部门路径进度未初始化。"+departmentId);
//			session.setAttribute("releaseFreemarkerDepartmentWebPath"+departmentId, 100);
//		}
//		if(null == session.getAttribute("releaseFreemarkerDepartmentNav"+departmentId)
//				|| "" == session.getAttribute("releaseFreemarkerDepartmentNav"+departmentId)){
//			System.out.println("部门导航进度未初始化。"+departmentId);
//			session.setAttribute("releaseFreemarkerDepartmentNav"+departmentId, 100);
//		}
//		if(null == session.getAttribute("releaseFreemarkerDepartmentSections"+departmentId)
//				|| "" == session.getAttribute("releaseFreemarkerDepartmentSections"+departmentId) ){
//			System.out.println("部门板块进度未初始化。"+departmentId);
//			session.setAttribute("releaseFreemarkerDepartmentSections"+departmentId, 100);
//		}
//		if(null == session.getAttribute("releaseFreemarkerDepartmentArticles"+departmentId)
//				|| "" == session.getAttribute("releaseFreemarkerDepartmentArticles"+departmentId)){
//			System.out.println("部门文章列表进度未初始化。"+departmentId);
//			session.setAttribute("releaseFreemarkerDepartmentArticles"+departmentId, 100);
//		}
//
//		System.err.println("releaseFreemarkerDepartmentWebPath"+departmentId+":"
//				+session.getAttribute("releaseFreemarkerDepartmentWebPath"+departmentId) );
//		if((Integer)session.getAttribute("releaseFreemarkerDepartmentWebPath"+departmentId) != 100 ){
//			System.out.println("部门路径进度未完成。"+departmentId);
//			return 0;
//		}
//		count++;
//		System.err.println("releaseFreemarkerDepartmentNav"+departmentId+":"
//				+session.getAttribute("releaseFreemarkerDepartmentNav"+departmentId) );
//		if((Integer)session.getAttribute("releaseFreemarkerDepartmentNav"+departmentId) != 100 ){
//			System.out.println("部门导航进度未完成。"+departmentId);
//			return 0;
//		}
//		count++;
//		System.err.println("releaseFreemarkerDepartmentSections"+departmentId+":"
//				+session.getAttribute("releaseFreemarkerDepartmentSections"+departmentId) );
//		if((Integer)session.getAttribute("releaseFreemarkerDepartmentSections"+departmentId) != 100 ){
//			System.out.println("部门板块进度未完成。"+departmentId);
//			return 0;
//		}
//		count++;
//		System.err.println("releaseFreemarkerDepartmentArticles"+departmentId+":"
//				+session.getAttribute("releaseFreemarkerDepartmentArticles"+departmentId) );
//		if((Integer)session.getAttribute("releaseFreemarkerDepartmentArticles"+departmentId) != 100 ){
//			System.out.println("部门文章列表进度未完成。"+departmentId);
//			return 0;
//		}
//		count++;
//		System.err.println("releaseFreemarkerDepartmentIndex"+departmentId+":"
//				+session.getAttribute("releaseFreemarkerDepartmentIndex"+departmentId) );
//		if((Integer)session.getAttribute("releaseFreemarkerDepartmentIndex"+departmentId) != 100 ){
//			System.out.println("部门首页进度未完成。"+departmentId);
//			return 0;
//		}
//		count++;
//		for(Integer id : secIds){
//			if(null == session.getAttribute("releaseFreemarkerSectionArticles"+id)
//					|| "" == session.getAttribute("releaseFreemarkerSectionArticles"+id) ){
//				System.out.println("部门板块文章列表进度未初始化。"+id);
//				session.setAttribute("releaseFreemarkerSectionArticles"+id, 100);
//			}
//			System.err.println("releaseFreemarkerSectionArticles"+id+":"
//					+session.getAttribute("releaseFreemarkerSectionArticles"+id) );
//			if( (Integer)session.getAttribute("releaseFreemarkerSectionArticles"+id) != 100) {
//				System.out.println("部门板块文章列表进度未完成。"+id);
//				return 0;
//			}
//			count++;
//		}
//		System.out.println("部门进度检查完成，可以开启新的进度"+departmentId);
//		session.setAttribute("progressCount"+departmentId, count);
//		return count;
//	}
	// 获得文章或部门的网络路径
	/**
	 * 通过文章计算当前文章所在的web路径
	 * 
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value = "getWebPathByArticle", method = { RequestMethod.GET })
	public @ResponseBody List<String> getWebPathByArticle(HttpServletRequest request,Integer articleId) {
//		Article article = articleService.getArticleById(articleId);
//		// String path =
//		// "首页>"+article.getDepartment().getName()+">"+article.getSection().getName();
//		List<String> path = new ArrayList<String>();
//		path.add("<a href='"+request.getContextPath()+"'/>首页</a>");
//		path.add("<a href='"+request.getContextPath()+"/index/department/dpt"+article.getDepartment().getId()+".jsp'>"+article.getDepartment().getName()+"</a>");
//		path.add("<a href='"+request.getContextPath()+"/index/section/sec"+article.getSection().getId()+".jsp'>"+article.getSection().getName()+"</a>");
//		return path;
		return freeMarkertUtil.getWebPathByArticle(articleId);
	}

	/**
	 * 通过子部门板块Id所在的web路径
	 * 
	 * @param sectionId
	 * @return
	 */
	@RequestMapping(value = "getWebPathBySection", method = { RequestMethod.GET })
	public @ResponseBody List<String> getWebPathBySection(HttpServletRequest request, Integer sectionId) {
//		Section section = sectionService.getSectionById(sectionId);
//		// String path =
//		// "首页>"+section.getDepartment().getName()+">"+section.getName();
//		List<String> path = new ArrayList<String>();
//		path.add("<a href='"+request.getContextPath()+"'/>首页</a>");
//		path.add("<a href='"+request.getContextPath()+"/index/department/dpt"+section.getDepartment().getId()+".jsp'>"+section.getDepartment().getName()+"</a>");
//		path.add("<a href='"+request.getContextPath()+"/index/section/sec"+section.getId()+".jsp'>"+section.getName()+"</a>");
		return freeMarkertUtil.getWebPathBySection(sectionId);
	}

	/**
	 * 通过部门ID计算所在的web路径
	 * 
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "getWebPathByDepartment", method = { RequestMethod.GET })
	public @ResponseBody List<String> getWebPathByDepartment(HttpServletRequest request,
			Integer departmentId) {
//		Department department = departmentService
//				.getDepartmentById(departmentId);
//		List<String> path = new ArrayList<String>();
//		path.add("<a href='"+request.getContextPath()+"'/>首页</a>");
//		path.add("<a href='"+request.getContextPath()+"/index/department/dpt"+department.getId()+".jsp'>"+department.getName()+"</a>");
		
		return freeMarkertUtil.getWebPathByDepartment(departmentId);
	}
	////////////////////////--------------------以下是子部门页面数据静态方法---------------------------------///////////
	// 以下是子部门页面数据静态方法

//	/**
//	 * 静态化所有子部门
//	 * @param request
//	 * @param departmentId
//	 * @return
//	 */
//	@RequestMapping(value = "releaseFreemarkerDepartmentSite", method = { RequestMethod.GET })
//	public @ResponseBody int releaseFreemarkerDepartmentSite(
//			HttpServletRequest request) {
//		int i = releaseFreemarkerAllDepartmentIndex(request);
//		i += releaseFreemarkerAllDepartmentWebPath(request);
//		i += releaseFreemarkerAllDepartmentSections(request);
//		i += releaseFreemarkerAllDepartmentArticles(request);
//		return i;
//	}
//	
//	/**
//	 * 静态化所有部门首页
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "releaseFreemarkerAllDepartmentIndex", method = { RequestMethod.GET })
//	public @ResponseBody int releaseFreemarkerAllDepartmentIndex(
//		 HttpServletRequest request) {
//		String rootPath = request.getServletContext().getRealPath("/");
//		String indexFile = rootPath + "/index/department/dpt";
//
//		String templatePath = rootPath + "/WEB-INF/ftl/department/";
//		String templateName = "/index.jsp.ftl";
//
//		String basePath = request.getContextPath();
//		
//		List<Integer> dptIds = departmentService.getAllDepartmentIds();
//		if(dptIds.size() == 0) return 0;
//		for(Integer id : dptIds){
//			String file = indexFile + id + ".jsp";
//			Department department = departmentService.getDepartmentById(id);
//			Map<String, Object> root = new HashMap<String, Object>();
//			root.put("basePath", basePath);
//			root.put("department", department);
//			FreeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
//			System.out.println("成功生成："+file);
//			System.out.println(file);
//		}
//		return 1;
//	}
	/**
	 * 静态化部门web路径
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerDepartmentWebPath", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerDepartmentWebPath(
			HttpServletRequest request,  Integer departmentId) {
//		String rootPath = request.getServletContext().getRealPath("/");
//		String indexFile = rootPath + "/index/department/content_r1c2_dpt"+departmentId+".jsp";
//		String templatePath = rootPath + "/WEB-INF/ftl/department/";
//		String templateName = "/content_r1c2.jsp.ftl";
//		
//		String basePath = request.getContextPath();
//		List<String> webPath = getWebPathByDepartment(request, departmentId);
//		Map<String, Object> root = new HashMap<String, Object>();
//		root.put("basePath", basePath);
//		root.put("wpath", webPath);
//		root.put("departmentId", departmentId);
//		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,root);
//		System.out.println("releaseFreemarkerDepartmentWebPath："+indexFile);
//		rootPath = null;
//		indexFile = null;
//		templatePath = null;
//		templateName = null;
//		basePath = null;
//		webPath = null;
//		root = null;
//		request = null;
//		departmentId = null;
		return freeMarkertUtil.htmlDepartmentWebPathJsp(departmentId, false);
	}
	/**
	 * 静态化部门页或首页nav.jsp
	 * 
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerDepartmentNav", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerDepartmentNav(
			HttpServletRequest request, Integer departmentId) {
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/nav/navDpt";

		String templatePath = rootPath + "/WEB-INF/ftl/index/";
		String templateName = "/nav.jsp.ftl";

		String basePath = request.getContextPath();
		String navFile = indexFile + departmentId + ".jsp";
		List<Navigation> navigationList = navigationService
				.getFirstNavigations(departmentId);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("basePath", basePath);
		root.put("navigations", navigationList);
		freeMarkertUtil.analysisTemplate(templatePath, templateName, navFile,root);
		System.out.println("releaseFreemarkerDepartmentNav："+indexFile);
		rootPath = null;
		indexFile = null;
		templatePath = null;
		templateName = null;
		basePath = null;
//		webPath = null;
		navigationList = null;
		root = null;
		request = null;
		departmentId = null;
		return 1;
	}
	
//	/**
//	 * 静态化所有部门web路径content_r1c2.jsp
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "releaseFreemarkerAllDepartmentWebPath", method = { RequestMethod.GET })
//	public @ResponseBody int releaseFreemarkerAllDepartmentWebPath(
//			HttpServletRequest request) {
//		String rootPath = request.getServletContext().getRealPath("/");
//		String indexFile = rootPath + "/index/department/content_r1c2_dpt";
//
//		String templatePath = rootPath + "/WEB-INF/ftl/department/";
//		String templateName = "/content_r1c2.jsp.ftl";
//
//		String basePath = request.getContextPath();
//		List<Integer> dptIds = departmentService.getAllDepartmentIds();
//		if(dptIds.size() == 0) return 0;
//		for(Integer id : dptIds){
//			String file = indexFile + id + ".jsp";
//			List<String> webPath = getWebPathByDepartment(id);
//			Map<String, Object> root = new HashMap<String, Object>();
//			root.put("basePath", basePath);
//			root.put("wpath", webPath);
//			freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
//			System.out.println("成功生成："+file);
//			System.out.println(file);
//		}
//		return 1;
//	}
// 
	/**
	 * 静态化子部门板块列表 content_r2c1_dpt"+departmentId+".jsp";
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerDepartmentSections", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerDepartmentSections(
			HttpServletRequest request,  Integer departmentId) {
		freeMarkertUtil.htmlDepartmentSectionsJsp(departmentId,false);//创建
//		String rootPath = request.getServletContext().getRealPath("/");
//		String indexFile = rootPath + "/index/department/content_r2c1_dpt"+departmentId+".jsp";
////		if(new File(indexFile).exists()){
////			return 1;
////		}
//
//		String templatePath = rootPath + "/WEB-INF/ftl/department/";
//		String templateName = "/content_r2c1.jsp.ftl";
//
//		String basePath = request.getContextPath();
//		Department department = departmentService.getDepartmentById(departmentId);
//		
//		Map<String, Object> root = new HashMap<String, Object>();
//		root.put("basePath", basePath);
//		root.put("sections", department.getSections());
//		root.put("department", department);
//		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,root);
//		System.out.println("releaseFreemarkerDepartmentSections："+indexFile);
//		rootPath = null;
//		indexFile = null;
//		templatePath = null;
//		templateName = null;
//		basePath = null;
////		webPath = null;
////		navigationList = null;
//		department = null;
//		root = null;
//		request = null;
//		departmentId = null;
		return 1;
	}
//	/**
//	 * 静态化所有子部门板块列表
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "releaseFreemarkerAllDepartmentSections", method = { RequestMethod.GET })
//	public @ResponseBody int releaseFreemarkerAllDepartmentSections(
//			HttpServletRequest request) {
//		String rootPath = request.getServletContext().getRealPath("/");
//		String indexFile = rootPath + "/index/department/content_r2c1_dpt";
//
//		String templatePath = rootPath + "/WEB-INF/ftl/department/";
//		String templateName = "/content_r2c1.jsp.ftl";
//
//		String basePath = request.getContextPath();
//		List<Integer> dptIds = departmentService.getAllDepartmentIds();
//		if(dptIds.size() == 0) return 0;
//		for(Integer id : dptIds){
//			String file = indexFile + id + ".jsp";
//			Department department = departmentService.getDepartmentById(id);
//			
//			Map<String, Object> root = new HashMap<String, Object>();
//			root.put("basePath", basePath);
//			root.put("sections", department.getSections());
//			root.put("department", department);
//			freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
//			System.out.println("成功生成："+file);
//			System.out.println(file);
//		}
//		return 1;
//	}

	/**
	 * 静态化子部门文章列表
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerDepartmentArticles", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerDepartmentArticles(
			HttpServletRequest request,  Integer departmentId) {
//		String rootPath = request.getServletContext().getRealPath("/");
//		String indexFile = rootPath + "/index/department/content_r2c2_list_dpt"+departmentId;
//
//		String templatePath = rootPath + "/WEB-INF/ftl/department/";
//		String templateName = "/content_r2c2_list.jsp.ftl";
//
//		String basePath = request.getContextPath();
		
//		int pgno=1;
//		Department department = departmentService.getDepartmentById(departmentId);
//		Page page;
//		do{
//			page = articleService.getPageJsonDepartmentArticlesSimple(pgno++, 30, departmentId);
//			freeMarkertUtil.htmlDepartmentJsp(department, page);
//		}while(page.getCurrentPageNo() < page.getTotalPageCount());
		freeMarkertUtil.htmlDepartmentArticleListJsp(departmentId,false);//
//		if(page.getTotalCount() == 0){
//			String file = indexFile + "_1.jsp";
////			if(new File(file).exists()){
////				return 1;
////			}
//			Map<String, Object> root = new HashMap<String, Object>();
//			root.put("basePath", basePath);
//			root.put("departmentId", departmentId);
//			root.put("page", page);
//			root.put("currentPageNo", pgno);
//			root.put("strLength", 20);
//			freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
//			System.out.println("releaseFreemarkerDepartmentArticles："+file);
//		}else 
//		while ( page.getCurrentPageNo() <= page.getTotalPageCount()){
//			String file = indexFile + "_"+page.getCurrentPageNo()+".jsp";
////			if(new File(file).exists()){
////				return 1;
////			}
//			Map<String, Object> root = new HashMap<String, Object>();
//			root.put("basePath", basePath);
//			root.put("departmentId", departmentId);
//			root.put("page", page);
//			root.put("currentPageNo", pgno);
//			root.put("strLength", 20);
//			freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
//			System.out.println("releaseFreemarkerDepartmentArticles："+file);
//			pgno++;
//			page = articleService.getPageJsonDepartmentArticlesSimple(pgno, 30, departmentId);
//		}
//		
//		rootPath = null;
//		indexFile = null;
//		templatePath = null;
//		templateName = null;
//		basePath = null;
////		webPath = null;
////		navigationList = null;
////		department = null;
//		page = null;
//		request = null;
//		departmentId = null;
		return 1;
	}
//	/**
//	 * 静态化所有子部门文章列表
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "releaseFreemarkerAllDepartmentArticles", method = { RequestMethod.GET })
//	public @ResponseBody int releaseFreemarkerAllDepartmentArticles(
//			HttpServletRequest request) {
//		String rootPath = request.getServletContext().getRealPath("/");
//		String indexFile = rootPath + "/index/department/content_r2c2_list_dpt";
//
//		String templatePath = rootPath + "/WEB-INF/ftl/department/";
//		String templateName = "/content_r2c2_list.jsp.ftl";
//
//		String basePath = request.getContextPath();
//		List<Integer> dptIds = departmentService.getAllDepartmentIds();
//		if(dptIds.size() == 0) return 0;
//		for(Integer id : dptIds){
//			int pgno=1;
//			Page page = articleService.getPageJsonDepartmentArticles(pgno, 30, id);
//				while (page.getTotalCount() != 0 && page.getCurrentPageNo() <= page.getTotalPageCount()){
//					String file = indexFile + id + "_"+page.getCurrentPageNo()+".jsp";
//					Map<String, Object> root = new HashMap<String, Object>();
//					root.put("basePath", basePath);
//					root.put("departmentId", id);
//					root.put("page", page);
//					root.put("currentPageNo", pgno);
//					root.put("strLength", 20);
//					freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
//					System.out.println("成功生成："+file);
//					pgno++;
//					page = articleService.getPageJsonDepartmentArticles(pgno, 30, id);
//					System.out.println(file);
//				}
//				if(pgno == 1){
//					
//				}
//		}
//		System.out.println("releaseFreemarkerAllDepartmentArticles Over....");
//		return 1;
//	}

	/**
	 * 获得部门下的板块数目
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "getDepartmentSectionsList", method = { RequestMethod.GET })
	public @ResponseBody List<Integer> getDepartmentSectionsList(Integer departmentId){
		return sectionService.getAllSectionIdsByDepartmentId(departmentId);
	}
	
	/**
	 * 静态化子部门板块文章列表
	 * @param request
	 * @param sectionId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerSectionArticles", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerSectionArticles(
			HttpServletRequest request,  Integer sectionId) {
//		System.out.println("releaseFreemarkerSectionArticles..."+sectionId);
//		String rootPath = request.getServletContext().getRealPath("/");
//		String indexFile = rootPath + "/index/section/sectionArticles"+sectionId;
//
//		String templatePath = rootPath + "/WEB-INF/ftl/section/";
//		String templateName = "/sectionArticles.jsp.ftl";
//
//		String basePath = request.getContextPath();
//		
//		int pgno=1;
//		Page page = articleService.getPageJsonSectionArticlesSimple(1, 20, sectionId);
//		if(page.getTotalPageCount() == 0 || page.getTotalCount() == 0) {
//			String file = indexFile +  "_1.jsp";
////			if(new File(file).exists()){
////				return 1;
////			}
//			Map<String, Object> root = new HashMap<String, Object>();
//			root.put("basePath", basePath);
//			root.put("sectionId", sectionId);
//			root.put("page", page);
//			root.put("currentPageNo", pgno);
//			root.put("strLength", 20);
//			freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
//			System.out.println("releaseFreemarkerSectionArticles："+file);
//		}else
//		while ( page.getCurrentPageNo() <= page.getTotalPageCount()){
//			String file = indexFile +  "_"+pgno+".jsp";
////			if(new File(file).exists()){
////				return 1;
////			}
//			Map<String, Object> root = new HashMap<String, Object>();
//			root.put("basePath", basePath);
//			root.put("sectionId", sectionId);
//			root.put("page", page);
//			root.put("currentPageNo", pgno);
//			root.put("strLength", 20);
//			freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
//			System.out.println("releaseFreemarkerSectionArticles："+file);
//			pgno++;
//			page = articleService.getPageJsonSectionArticlesSimple(pgno, 20, sectionId);
//		}
//
//		rootPath = null;
//		indexFile = null;
//		templatePath = null;
//		templateName = null;
//		basePath = null;
////		webPath = null;
////		navigationList = null;
////		department = null;
//		page = null;
//		request = null;
////		departmentId = null;
//		sectionId = null;
		
		
		return freeMarkertUtil.htmlSectionArticleListJsp(sectionId);
	}
//	/**
//	 * 静态化所有子部门板块文章列表
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = "releaseFreemarkerAllSectionArticles", method = { RequestMethod.GET })
//	public @ResponseBody int releaseFreemarkerAllSectionArticles(
//			HttpServletRequest request) {
//		String rootPath = request.getServletContext().getRealPath("/");
//		String indexFile = rootPath + "/index/section/sectionArticles";
//
//		String templatePath = rootPath + "/WEB-INF/ftl/section/";
//		String templateName = "/sectionArticles.jsp.ftl";
//
//		String basePath = request.getContextPath();
//		List<Integer> dptIds = sectionService.getAllSectionIds();
//		
//		if(dptIds.size() == 0) return 0;
//		for(Integer id : dptIds){
//			Page page = articleService.getPageJsonSectionArticles(1, 20, id);
//			if(page.getTotalPageCount() == 0 || page.getTotalCount() == 0) {
//				return 0;
//			}
//			int pgno=1;
//			while ( page.getCurrentPageNo() <= page.getTotalPageCount()){
//				String file = indexFile + id+ "_"+page.getCurrentPageNo()+".jsp";
//				Map<String, Object> root = new HashMap<String, Object>();
//				root.put("basePath", basePath);
//				root.put("sectionId", id);
//				root.put("page", page);
//				root.put("currentPageNo", pgno);
//				root.put("strLength", 20);
//				freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
//				System.out.println("成功生成："+file);
//				pgno++;
//				page = articleService.getPageJsonDepartmentArticles(pgno, 20, id);
//				if(page.getTotalPageCount() == 0 || page.getTotalCount() == 0) {
//					return 0;
//				}
//				System.out.println(file);
//			}
//		}
//		return 1;
//	}
	/**
	 * 静态化部门首页
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerDepartmentIndex", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerDepartmentIndex(
			HttpServletRequest request,  Integer departmentId) {
		freeMarkertUtil.htmlDepartmentIndex(departmentId,false);
//		System.out.println("releaseFreemarkerDepartmentIndex()...departmentId:"+departmentId);
//		String rootPath = request.getServletContext().getRealPath("/");
//		String basePath = request.getContextPath();
//		String indexFile = rootPath + "/index/department/dpt"+departmentId+".jsp";
////		if(new File(indexFile).exists()){
////			return 1;
////		}
//		String templatePath = rootPath + "/WEB-INF/ftl/department/";
//		String templateName = "/index.jsp.ftl";
//
//		//String basePath = request.getContextPath();
//		System.out.println("releaseFreemarkerDepartmentIndex()...departmentService:"+departmentService);
//		Department department = departmentService.getDepartmentById(departmentId);
//		Map<String, Object> root = new HashMap<String, Object>();
//		root.put("basePath", basePath);
//		root.put("department", department);
//		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,root);
//		System.out.println("releaseFreemarkerDepartmentIndex："+indexFile);
//		
//		rootPath = null;
//		indexFile = null;
//		templatePath = null;
//		templateName = null;
//		basePath = null;
////		webPath = null;
////		navigationList = null;
////		department = null;
////		page = null;
//		root = null;
//		request = null;
////		departmentId = null;
////		sectionId = null;
//		
		return 1;
		
	}
	/**
	 * 静态化子版块首页
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerSectionIndex", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerSectionIndex(
			HttpServletRequest request,  Integer sectionId) {
		
//		System.out.println("releaseFreemarkerSectionIndex()...sectionId:"+sectionId);
//		String rootPath = request.getServletContext().getRealPath("/");
//		String basePath = request.getContextPath();
//		String indexFile = rootPath + "/index/section/sec"+sectionId+".jsp";
////		if(new File(indexFile).exists()){
////			return 1;
////		}
//		String templatePath = rootPath + "/WEB-INF/ftl/section/";
//		String templateName = "/index.jsp.ftl";
//
//		//String basePath = request.getContextPath();
//		System.out.println("releaseFreemarkerDepartmentIndex()...departmentService:"+departmentService);
//		Section section = sectionService.getSectionById(sectionId);
//		Map<String, Object> root = new HashMap<String, Object>();
//		root.put("basePath", basePath);
//		root.put("department", section.getDepartment());
//		root.put("sectionId", section.getId());
//		
//		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,root);
//		System.out.println("releaseFreemarkerSectionIndex："+indexFile);
//		
//		rootPath = null;
//		indexFile = null;
//		templatePath = null;
//		templateName = null;
//		basePath = null;
////		webPath = null;
////		navigationList = null;
////		department = null;
////		page = null;
//		request = null;
////		departmentId = null;
//		sectionId = null;
//		section = null;
//		root = null;
//		
		Section section = sectionService.getSectionById(sectionId);
		freeMarkertUtil.htmlSectionIndexJsp(sectionId, section.getDepartment().getId(), false);
		freeMarkertUtil.htmlSectionListJsp(sectionId, false);
		freeMarkertUtil.htmlSectionWebPathJsp(sectionId, false);
		return 1;
	}
	/**
	 *  静态化文章首页
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerArticleIndex", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerArticleIndex(
			HttpServletRequest request,  Integer articleId) {
//		freeMarkertUtil.htmlArticleIndexAndContentJsp(articleId, false);
//		System.out.println("releaseFreemarkerArticleIndex()...articleId:"+articleId);
//		String rootPath = request.getServletContext().getRealPath("/");
//		String basePath = request.getContextPath();
//		String indexFile = rootPath + "/index/article/art"+articleId+".jsp";
////		if(new File(indexFile).exists()){
////			return 1;
////		}
//		String templatePath = rootPath + "/WEB-INF/ftl/article/";
//		String templateName = "/index.jsp.ftl";
//
//		//String basePath = request.getContextPath();
//		System.out.println("releaseFreemarkerDepartmentIndex()...departmentService:"+departmentService);
//		Article article = articleService.getArticleById(articleId);
//		Map<String, Object> root = new HashMap<String, Object>();
//		root.put("basePath", basePath);
//		root.put("department", article.getDepartment());
//		root.put("article", article);
//		
//		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,root);
//		System.out.println("releaseFreemarkerArticleIndex："+indexFile);
//		
//		rootPath = null;
//		indexFile = null;
//		templatePath = null;
//		templateName = null;
//		basePath = null;
////		webPath = null;
////		navigationList = null;
////		department = null;
////		page = null;
//		request = null;
////		departmentId = null;
//		article = null;
//		articleId = null;
////		sectionId = null;
////		section = null;
//		root = null;
		
		return freeMarkertUtil.htmlArticleIndexAndContentJsp(articleId, false);
	}
	/**
	 * 静态化首页底布栏foot.jsp
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerIndexFoot", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerIndexFoot(
			HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/foot.jsp";
//		if(new File(indexFile).exists()){
//			return 1;
//		}
		String templatePath = rootPath + "/WEB-INF/ftl/index/";
		String templateName = "/foot.jsp.ftl";
		
		Map<String, Object> root = new HashMap<String, Object>();
		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,root);
		System.out.println("releaseFreemarkerIndexFoot"+indexFile);
		
		rootPath = null;
		indexFile = null;
		templatePath = null;
		templateName = null;
//		basePath = null;
//		webPath = null;
//		navigationList = null;
//		department = null;
//		page = null;
		request = null;
//		departmentId = null;
//		article = null;
//		articleId = null;
//		sectionId = null;
//		section = null;
		root = null;
		
		
		return 1;
	}
	/**
	 * 静态化部门动态预览previewDepartmentArticleList{id}.jsp
	 * @param request
	 * @param departmentId
	 * @param size
	 * @param strLength
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerDynPreviewArticleList", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerDynPreviewArticleList(
			HttpServletRequest request, Integer departmentId, Integer size,Integer strLength) {
//		String rootPath = request.getServletContext().getRealPath("/");
//		String indexFile = rootPath + "/index/articleList/preview/previewDepartmentArticleList"+departmentId+".jsp";
////		if(new File(indexFile).exists()){
////			return 1;
////		}
//
//		String templatePath = rootPath + "/WEB-INF/ftl/index/";
//		String templateName = "/previewArticleList.jsp.ftl";
//
//		String basePath = request.getContextPath();
//		
//		Page page = articleService.getPageJsonDepartmentArticlesSimple(1, size, departmentId);
//		Map<String, Object> root = new HashMap<String, Object>();
//		root.put("basePath", basePath);
//		root.put("strLength", strLength);
//		root.put("articles", page.getResult());
//		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,root);
//		System.out.println("releaseFreemarkerDynPreviewArticleList"+indexFile);
//		rootPath = null;
//		indexFile = null;
//		templatePath = null;
//		templateName = null;
////		basePath = null;
////		webPath = null;
////		navigationList = null;
////		department = null;
//		size = null;
//		strLength = null;
//		page = null;
//		request = null;
////		departmentId = null;
////		article = null;
////		articleId = null;
////		sectionId = null;
////		section = null;
//		root = null;
//		
		return freeMarkertUtil.htmlDynPreviewArticleListJsp(departmentId, size, strLength);
	}
	/**
	 * 静态化首页公告栏content_r1c1gonggao.jsp
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerIndexGongGao", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerIndexGongGao(
			HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/content_r1c1gonggao.jsp";

		String templatePath = rootPath + "/WEB-INF/ftl/index/";
		String templateName = "/content_r1c1gonggao.jsp.ftl";

		String basePath = request.getContextPath();
		List<Article> articleList = articleService.getNoticNewest(3);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("basePath", basePath);
		root.put("article", articleList.get(0));
		root.put("articles", articleList);
		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,root);
		System.out.println("releaseFreemarkerIndexGongGao"+indexFile);
		
		rootPath = null;
		indexFile = null;
		templatePath = null;
		templateName = null;
//		basePath = null;
//		webPath = null;
//		navigationList = null;
//		department = null;
//		size = null;
//		strLength = null;
//		page = null;
		request = null;
//		departmentId = null;
//		article = null;
//		articleId = null;
//		sectionId = null;
//		section = null;
		root = null;
		articleList = null;
		
		
		return 1;
	}
	/**
	 * 静态化首页top.jsp
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerIndexTop", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerIndexTop(
			HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/top.jsp";
		String templatePath = rootPath + "/WEB-INF/ftl/index/";
		String templateName = "/top.jsp.ftl";
		String basePath = request.getContextPath();
		Map<String, String> root = new HashMap<String, String>();
		root.put("basePath", basePath);
		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,
				root);
		System.out.println("静态化首页top.jsp");
		
		rootPath = null;
		indexFile = null;
		basePath = null;
		templatePath = null;
		templateName = null;
		request = null;
		root = null;
		
		
		return 1;
	}
	

	// 静态化文章子<div>。
	/**
	 * 静态化子部门板块文章列表
	 * @param request
	 * @param sectionId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerArticle", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerArticle(
			HttpServletRequest request,  Integer articleId) {
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/article/"+articleId+".jsp";
//		if(new File(indexFile).exists()){
//			return 1;
//		}

		String templatePath = rootPath + "/WEB-INF/ftl/article/";
		String templateName = "/article.jsp.ftl";

		String basePath = request.getContextPath();
		
		Article article = articleService.getArticleById(articleId);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("basePath", basePath);
		root.put("article", article);
		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile, root);
		System.out.println("releaseFreemarkerArticle"+indexFile);
		rootPath = null;
		indexFile = null;
		basePath = null;
		templatePath = null;
		templateName = null;
		request = null;
		root = null;
		article = null;
		
		return 1;
	}
	/**
	 * 静态化首页快速通道
	 * @param request
	 * @param sectionId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerIndexQuickMap", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerIndexQuickMap(
			HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/content_r2c3.jsp";

		String templatePath = rootPath + "/WEB-INF/ftl/index/";
		String templateName = "/content_r2c3.jsp.ftl";

		String basePath = request.getContextPath();
		List<DepartmentType> dtList = departmentTypesService.getAllDepartmentTypes();
		String dt1Name = "";
		String dt2Name = "";
		String dt3Name = "";
		List<Department> dpt1List = new ArrayList<Department>();
		List<Department> dpt2List = new ArrayList<Department>();
		List<Department> dpt3List = new ArrayList<Department>();
		for(DepartmentType dt: dtList){
			switch(dt.getId()){
			case 1:
				System.out.println("case 1:");
				dpt1List = departmentService.getDepartmentByDepartmentTypeId(dt.getId());
				dt1Name = dt.getName();
				break;
			case 2:
				System.out.println("case 2:");
				dpt2List = departmentService.getDepartmentByDepartmentTypeId(dt.getId());
				dt2Name = dt.getName();
				break;
			case 3:
				System.out.println("case 3:");
				dpt3List = departmentService.getDepartmentByDepartmentTypeId(dt.getId());
				dt3Name = dt.getName();
				break;
			default :
				System.out.println("case default :");
					break;
			}
		}
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("basePath", basePath);
		root.put("dpt1List", dpt1List);
		root.put("dpt2List", dpt2List);
		root.put("dpt3List", dpt3List);
		root.put("dt1Name", dt1Name);
		root.put("dt2Name", dt2Name);
		root.put("dt3Name", dt3Name);
		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile, root);
		
		rootPath = null;
		indexFile = null;
		basePath = null;
		templatePath = null;
		templateName = null;
		request = null;
		root = null;
		dtList = null;
		dt1Name = null;
		dt2Name = null;
		dt3Name = null;
		dpt1List = null;
		dpt2List = null;
		dpt3List = null;		
		
		return 1;
	}

}
