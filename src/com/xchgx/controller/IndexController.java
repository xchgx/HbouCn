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
import org.springframework.web.servlet.ModelAndView;

import com.xchgx.dao.Page;
import com.xchgx.domain.Article;
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
@RequestMapping(value = "/")
public class IndexController {
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

	public SectionService getSectionService() {
		return sectionService;
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		// System.out.println("index controller ... -> user/register");
		return "index";
	}

	@RequestMapping(value = "/manager/index", method = RequestMethod.GET)
	public String managerIndex() {
		// System.out.println("managerIndex controller ... -> user/register");
		return "manager/index";
	}

	/**
	 * 初始化是数据库。测试阶段使用
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initDb", method = RequestMethod.GET)
	public ModelAndView initDb() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/init/initDatabaseView");
		String result = initDatabaseService.initDbData();
		mav.addObject("result", result);
		return mav;
	}

	/**
	 * 初始化是数据库。测试阶段使用
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initRelationDb", method = RequestMethod.GET)
	public ModelAndView initRelationDb() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/init/initDatabaseView");
		String result = initDatabaseService.initRelationDb();
		mav.addObject("result", result);
		return mav;
	}

	// initClearDb
	/**
	 * 初始化是数据库。测试阶段使用
	 * 
	 * @return
	 */
	@RequestMapping(value = "/initClearDb", method = RequestMethod.GET)
	public ModelAndView initClearDb() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/init/initDatabaseView");
		String result = initDatabaseService.initClearDb();
		mav.addObject("result", result);
		return mav;
	}

	/**
	 * 通过部门ID获取部门下的子部门板块列表
	 * 
	 * @param departmentId
	 * @return sectionList
	 */
	@RequestMapping(value = "/section/getSectionsByDepartmentId", method = RequestMethod.GET)
	public @ResponseBody List<Section> getSectionsByDepartmentId(
			Integer departmentId) {
		List<Section> sections = sectionService
				.getSectionsByDepartmentId(departmentId);
		List<Section> sectionList = new ArrayList<Section>();
		for (Section s : sections) {
			Section section = new Section(s.getName(), s.getDescription());
			section.setId(s.getId());
			sectionList.add(section);
		}
		return sectionList;
	}

	/**
	 * 通过文章计算当前文章所在的web路径
	 * 
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value = "getWebPathByArticle", method = { RequestMethod.GET })
	public @ResponseBody List<String> getWebPathByArticle(Integer articleId) {
		Article article = articleService.getArticleById(articleId);
		// String path =
		// "首页>"+article.getDepartment().getName()+">"+article.getSection().getName();
		List<String> path = new ArrayList<String>();
		path.add("首页");
		path.add(article.getDepartment().getName());
		path.add(article.getSection().getName());
		return path;
	}

	/**
	 * 通过子部门板块Id所在的web路径
	 * 
	 * @param sectionId
	 * @return
	 */
	@RequestMapping(value = "getWebPathBySection", method = { RequestMethod.GET })
	public @ResponseBody List<String> getWebPathBySection(Integer sectionId) {
		Section section = sectionService.getSectionById(sectionId);
		// String path =
		// "首页>"+section.getDepartment().getName()+">"+section.getName();
		List<String> path = new ArrayList<String>();
		path.add("首页");
		path.add(section.getDepartment().getName() );
		path.add(section.getName());
		return path;
	}

	/**
	 * 通过部门ID计算所在的web路径
	 * 
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "getWebPathByDepartment", method = { RequestMethod.GET })
	public @ResponseBody List<String> getWebPathByDepartment(
			Integer departmentId) {
		Department department = departmentService
				.getDepartmentById(departmentId);
		List<String> path = new ArrayList<String>();
		path.add("首页");
		path.add(department.getName());
		return path;
	}
////----------------------------以下是静态化主页首页页面-------------------------------/////
	// 以下是静态化主页首页页面
	/**
	 * 生成整个站点HTML静态页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerSite", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerSite(HttpServletRequest request) {
		int i= releaseFreemarkerIndexSite(request);
		i += releaseFreemarkerDepartmentSite(request);
		i+= releaseFreemarkerAllArticles(request);
		return i;
	}

	/**
	 * 静态化首页....
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerIndexSite", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerIndexSite(HttpServletRequest request){
		int i = releaseFreemarkerIndexTop(request);
		i += releaseFreemarkerAllDepartmentsNav(request);
		i += releaseFreemarkerIndexGongGao(request);
		System.out.println("准备静态化预览动态文章列表");
		i += releaseFreemarkerAllDynPreviewArticleList(request, 15, 10);
		System.out.println("静态化预览动态文章列表完成！");
		 releaseFreemarkerDynPreviewArticleList(request, 7, 10, 19);
		return i;
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
		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile, root);
		System.out.println("静态化首页top.jsp");
		return 1;
	}

	/**
	 * 静态化所有部门页nav（包含首页）.jsp
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerAllDepartmentsNav", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerAllDepartmentsNav(
			HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/nav/navDpt";

		String templatePath = rootPath + "/WEB-INF/ftl/index/";
		String templateName = "/nav.jsp.ftl";

		String basePath = request.getContextPath();

		List<Integer> dptIdList = departmentService.getAllDepartmentIds();
		dptIdList.add(0); // 首页不属于部门，但是有特殊的标记0。
		System.out.println("静态化所有部门页nav（包含首页）.jsp");
		for (Integer id : dptIdList) {
			String navFile = indexFile + id + ".jsp";
			// String navFile = indexFile+"111.jsp";
			List<Navigation> navigationList = navigationService
					.getFirstNavigations(id);

			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("navigations", navigationList);
			freeMarkertUtil.analysisTemplate(templatePath, templateName,
					navFile, root);
			System.out.println(navFile);
		}
		return 1;
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
		freeMarkertUtil.analysisTemplate(templatePath, templateName, navFile,
				root);
		System.out.println(navFile);
		return 1;
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
		System.out.println(indexFile);
		return 1;
	}

	/**
	 * 静态化所有部门动态预览previewDepartmentArticleList{autoid}.jsp
	 * @param request
	 * @param size
	 * @param strLength
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerAllDynPreviewArticleList", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerAllDynPreviewArticleList(
			HttpServletRequest request, Integer size, Integer strLength) {
		System.out.println("进入静态化所有部门动态预览方法....");
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/articleList/preview/previewDepartmentArticleList";

		String templatePath = rootPath + "/WEB-INF/ftl/index/";
		String templateName = "/previewArticleList.jsp.ftl";

		String basePath = request.getContextPath();
		List<Integer> dptIds = departmentService.getAllDepartmentIds();
		System.out.println("departmentService.getAllDepartmentIds"+dptIds.size());
		if(dptIds.size() > 0){
			dptIds.add(0);
			for(Integer id : dptIds){
				String file =indexFile + id+".jsp";
				System.out.println("releaseFreemarkerAllDynPreviewArticleList"+id);
				Page page = articleService.getPageJsonDepartmentArticles(1, size, id);
				if(page.getTotalCount() > 0){
					Map<String, Object> root = new HashMap<String, Object>();
					root.put("basePath", basePath);
					root.put("strLength",strLength);
					root.put("articles", page.getResult());
					freeMarkertUtil.analysisTemplate(templatePath, templateName, file,root);
					System.out.println(file);
				}
			}
			return 1;
		}
		return 0;
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
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/articleList/preview/previewDepartmentArticleList"+departmentId+".jsp";

		String templatePath = rootPath + "/WEB-INF/ftl/index/";
		String templateName = "/previewArticleList.jsp.ftl";

		String basePath = request.getContextPath();
		
		Page page = articleService.getPageJsonDepartmentArticles(1, size, departmentId);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("basePath", basePath);
		root.put("strLength", strLength);
		root.put("articles", page.getResult());
		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,root);
		return 1;
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

		String templatePath = rootPath + "/WEB-INF/ftl/index/";
		String templateName = "/foot.jsp.ftl";

//		String basePath = request.getContextPath();
		
		Map<String, Object> root = new HashMap<String, Object>();
		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,root);
		System.out.println(indexFile);
		return 1;
	}
	
	////////////////////////--------------------以下是子部门页面数据静态方法---------------------------------///////////
	// 以下是子部门页面数据静态方法

	/**
	 * 静态化所有子部门
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerDepartmentSite", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerDepartmentSite(
			HttpServletRequest request) {
		int i = releaseFreemarkerAllDepartmentIndex(request);
		i += releaseFreemarkerAllDepartmentWebPath(request);
		i += releaseFreemarkerAllDepartmentSections(request);
		i += releaseFreemarkerAllDepartmentArticles(request);
		return i;
	}
	
	/**
	 * 静态化部门首页
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerDepartmentIndex", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerDepartmentIndex(
			HttpServletRequest request,  Integer departmentId) {
		System.out.println("releaseFreemarkerDepartmentIndex()...departmentId:"+departmentId);
		request.getSession().setAttribute("releaseFreemarkerDepartmentIndex"+departmentId, 1);
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/department/dpt"+departmentId+".jsp";

		String templatePath = rootPath + "/WEB-INF/ftl/department/";
		String templateName = "/index.jsp.ftl";

		String basePath = request.getContextPath();
		System.out.println("releaseFreemarkerDepartmentIndex()...departmentService:"+departmentService);
		Department department = departmentService.getDepartmentById(departmentId);
		if(department == null){
			System.out.println("releaseFreemarkerDepartmentIndex()...department:"+department);
			return 0;
		}
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("basePath", basePath);
		root.put("department", department);
		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,root);
		System.out.println(indexFile);
		request.getSession().setAttribute("releaseFreemarkerDepartmentIndex"+departmentId, 100);
		return 1;
	}
	/**
	 * 静态化所有部门首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerAllDepartmentIndex", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerAllDepartmentIndex(
			HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/department/dpt";

		String templatePath = rootPath + "/WEB-INF/ftl/department/";
		String templateName = "/index.jsp.ftl";

		String basePath = request.getContextPath();
		
		List<Integer> dptIds = departmentService.getAllDepartmentIds();
		if(dptIds.size() == 0) return 0;
		for(Integer id : dptIds){
			String file = indexFile + id + ".jsp";
			Department department = departmentService.getDepartmentById(id);
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("department", department);
			freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
			System.out.println(file);
		}
		return 1;
	}
	/**
	 * 静态化部门web路径
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerDepartmentWebPath", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerDepartmentWebPath(
			HttpServletRequest request,  Integer departmentId) {
		request.getSession().setAttribute("releaseFreemarkerDepartmentWebPath"+departmentId, 1);
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/department/content_r1c2_dpt"+departmentId+".jsp";

		String templatePath = rootPath + "/WEB-INF/ftl/department/";
		String templateName = "/content_r1c2.jsp.ftl";

		String basePath = request.getContextPath();
		List<String> webPath = getWebPathByDepartment(departmentId);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("basePath", basePath);
		root.put("wpath", webPath);
		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,root);
		request.getSession().setAttribute("releaseFreemarkerDepartmentWebPath"+departmentId, 100);
		return 1;
	}
	/**
	 * 静态化所有部门web路径content_r1c2.jsp
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerAllDepartmentWebPath", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerAllDepartmentWebPath(
			HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/department/content_r1c2_dpt";

		String templatePath = rootPath + "/WEB-INF/ftl/department/";
		String templateName = "/content_r1c2.jsp.ftl";

		String basePath = request.getContextPath();
		List<Integer> dptIds = departmentService.getAllDepartmentIds();
		if(dptIds.size() == 0) return 0;
		for(Integer id : dptIds){
			String file = indexFile + id + ".jsp";
			List<String> webPath = getWebPathByDepartment(id);
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("wpath", webPath);
			freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
			System.out.println(file);
		}
		return 1;
	}
 
	/**
	 * 静态化子部门板块列表 content_r2c1_dpt"+departmentId+".jsp";
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerDepartmentSections", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerDepartmentSections(
			HttpServletRequest request,  Integer departmentId) {
		request.getSession().setAttribute("releaseFreemarkerDepartmentWebPath"+departmentId, 1);
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/department/content_r2c1_dpt"+departmentId+".jsp";

		String templatePath = rootPath + "/WEB-INF/ftl/department/";
		String templateName = "/content_r2c1.jsp.ftl";

		String basePath = request.getContextPath();
		Department department = departmentService.getDepartmentById(departmentId);
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("basePath", basePath);
		root.put("sections", department.getSections());
		root.put("department", department);
		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile,root);
		request.getSession().setAttribute("releaseFreemarkerDepartmentWebPath"+departmentId, 100);
		return 1;
	}
	/**
	 * 静态化所有子部门板块列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerAllDepartmentSections", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerAllDepartmentSections(
			HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/department/content_r2c1_dpt";

		String templatePath = rootPath + "/WEB-INF/ftl/department/";
		String templateName = "/content_r2c1.jsp.ftl";

		String basePath = request.getContextPath();
		List<Integer> dptIds = departmentService.getAllDepartmentIds();
		if(dptIds.size() == 0) return 0;
		for(Integer id : dptIds){
			String file = indexFile + id + ".jsp";
			Department department = departmentService.getDepartmentById(id);
			
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("sections", department.getSections());
			root.put("department", department);
			freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
			System.out.println(file);
		}
		return 1;
	}

	/**
	 * 静态化子部门文章列表
	 * @param request
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerDepartmentArticles", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerDepartmentArticles(
			HttpServletRequest request,  Integer departmentId) {
		request.getSession().setAttribute("releaseFreemarkerDepartmentArticles"+departmentId, 1);
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/department/content_r2c2_list_dpt"+departmentId;

		String templatePath = rootPath + "/WEB-INF/ftl/department/";
		String templateName = "/content_r2c2_list.jsp.ftl";

		String basePath = request.getContextPath();
		int pgno=1;
		Page page = articleService.getPageJsonDepartmentArticles(pgno, 30, departmentId);
		while (page.getTotalCount() != 0 && page.getCurrentPageNo() <= page.getTotalPageCount()){
			String file = indexFile + "_"+page.getCurrentPageNo()+".jsp";
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("departmentId", departmentId);
			root.put("page", page);
			root.put("currentPageNo", pgno);
			root.put("strLength", 20);
			freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
			pgno++;
			page = articleService.getPageJsonDepartmentArticles(pgno, 30, departmentId);
			System.out.println(file);
			request.getSession().setAttribute("releaseFreemarkerDepartmentArticles"+departmentId, pgno/page.getTotalPageCount()*100);
		}
		request.getSession().setAttribute("releaseFreemarkerDepartmentArticles"+departmentId, 100);
		return 1;
	}
	/**
	 * 静态化所有子部门文章列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerAllDepartmentArticles", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerAllDepartmentArticles(
			HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/department/content_r2c2_list_dpt";

		String templatePath = rootPath + "/WEB-INF/ftl/department/";
		String templateName = "/content_r2c2_list.jsp.ftl";

		String basePath = request.getContextPath();
		List<Integer> dptIds = departmentService.getAllDepartmentIds();
		if(dptIds.size() == 0) return 0;
		for(Integer id : dptIds){
			int pgno=1;
			Page page = articleService.getPageJsonDepartmentArticles(pgno, 30, id);
				while (page.getTotalCount() != 0 && page.getCurrentPageNo() <= page.getTotalPageCount()){
					String file = indexFile + id + "_"+page.getCurrentPageNo()+".jsp";
					Map<String, Object> root = new HashMap<String, Object>();
					root.put("basePath", basePath);
					root.put("departmentId", id);
					root.put("page", page);
					root.put("currentPageNo", pgno);
					root.put("strLength", 20);
					freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
					pgno++;
					page = articleService.getPageJsonDepartmentArticles(pgno, 30, id);
					System.out.println(file);
				}
				if(pgno == 1){
					
				}
		}
		System.out.println("releaseFreemarkerAllDepartmentArticles Over....");
		return 1;
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
		request.getSession().setAttribute("releaseFreemarkerSectionArticles"+sectionId, 1);
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/section/sectionArticles"+sectionId;

		String templatePath = rootPath + "/WEB-INF/ftl/section/";
		String templateName = "/sectionArticles.jsp.ftl";

		String basePath = request.getContextPath();
		
		int pgno=1;
		Page page = articleService.getPageJsonSectionArticles(1, 20, sectionId);
		while ( page.getCurrentPageNo() <= page.getTotalPageCount()){
			String file = indexFile +  "_"+page.getCurrentPageNo()+".jsp";
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("sectionId", sectionId);
			root.put("page", page);
			root.put("currentPageNo", pgno);
			root.put("strLength", 20);
			freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
			pgno++;
			page = articleService.getPageJsonDepartmentArticles(pgno, 20, sectionId);
			request.getSession().setAttribute("releaseFreemarkerSectionArticles"+sectionId, pgno/page.getTotalPageCount()*100);
		}
		request.getSession().setAttribute("releaseFreemarkerSectionArticles"+sectionId, 100);
		return 1;
	}
	/**
	 * 静态化所有子部门板块文章列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerAllSectionArticles", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerAllSectionArticles(
			HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/section/sectionArticles";

		String templatePath = rootPath + "/WEB-INF/ftl/section/";
		String templateName = "/sectionArticles.jsp.ftl";

		String basePath = request.getContextPath();
		List<Integer> dptIds = sectionService.getAllSectionIds();;
		
		if(dptIds.size() == 0) return 0;
		for(Integer id : dptIds){
			Page page = articleService.getPageJsonSectionArticles(1, 20, id);
			int pgno=1;
			while ( page.getCurrentPageNo() <= page.getTotalPageCount()){
				String file = indexFile + id+ "_"+page.getCurrentPageNo()+".jsp";
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("basePath", basePath);
				root.put("sectionId", id);
				root.put("page", page);
				root.put("currentPageNo", pgno);
				root.put("strLength", 20);
				freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
				pgno++;
				page = articleService.getPageJsonDepartmentArticles(pgno, 20, id);
				System.out.println(file);
			}
		}
		return 1;
	}
	////-------------------------静态化所有文章。-----------------------------------/////
	// 静态化所有文章。
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

		String templatePath = rootPath + "/WEB-INF/ftl/article/";
		String templateName = "/article.jsp.ftl";

		String basePath = request.getContextPath();
		
		Article article = articleService.getArticleById(articleId);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("basePath", basePath);
		root.put("article", article);
		freeMarkertUtil.analysisTemplate(templatePath, templateName, indexFile, root);
		return 1;
	}
	/**
	 * 静态化所有子部门板块文章列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "releaseFreemarkerAllArticles", method = { RequestMethod.GET })
	public @ResponseBody int releaseFreemarkerAllArticles(
			HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("/");
		String indexFile = rootPath + "/index/article/";

		String templatePath = rootPath + "/WEB-INF/ftl/article/";
		String templateName = "/article.jsp.ftl";

		String basePath = request.getContextPath();
		List<Integer> dptIds = articleService.getAllArticleIds();
		
		if(dptIds.size() == 0) return 0;
		for(Integer id : dptIds){
			String file =  indexFile+ id + ".jsp";
			Article article = articleService.getArticleById(id);
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("basePath", basePath);
			root.put("article", article);
			freeMarkertUtil.analysisTemplate(templatePath, templateName, file, root);
			System.out.println(file);
		}
		return 1;
	}
}
