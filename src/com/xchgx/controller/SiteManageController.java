package com.xchgx.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xchgx.cons.CommonConstant;
import com.xchgx.domain.Article;
import com.xchgx.domain.Navigation;
import com.xchgx.domain.Section;
import com.xchgx.domain.User;
import com.xchgx.service.ArticleService;
import com.xchgx.service.DepartmentService;
import com.xchgx.service.DepartmentTypesService;
import com.xchgx.service.EmployeeService;
import com.xchgx.service.FreeMarkertUtil;
import com.xchgx.service.NavigationService;
import com.xchgx.service.PermissionService;
import com.xchgx.service.SchoolService;
import com.xchgx.service.SectionService;
import com.xchgx.service.ShortcutService;
import com.xchgx.service.TeacherService;
import com.xchgx.service.UserService;
import com.xchgx.zsbwork.bean.Department;

@Controller
@RequestMapping("/manager/site")
public class SiteManageController extends BaseController{

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
	private  SectionService sectionService;
	@Autowired
	private NavigationService navigationService;
	@Autowired
	private FreeMarkertUtil freeMarkertUtil;
	// 首页 之 导航页
	/**
	 * 所有导航列表
	 * @return
	 */
	@RequestMapping(value="allNavigations")
	public ModelAndView allNavigations(Integer departmentId){
		ModelAndView mav = new ModelAndView();
		List<Navigation> navigations = navigationService.getAllNavigations(departmentId);
		if(navigations == null){
			navigations = new ArrayList<Navigation>();
		}
		List<Navigation> firstNavigations = navigationService.getFirstNavigations(departmentId);
		if(firstNavigations == null)	{
			firstNavigations = new ArrayList<Navigation>();
		}
		
		mav.setViewName("manager/siteManage/allNavigations");

		mav.addObject("navigations",navigations);
		mav.addObject("firstNavigations",firstNavigations);
		mav.addObject("departmentId",departmentId);
		System.err.println("allNavigations()...departmentId:"+departmentId);
		return mav;
	}
	/**
	 * 首页生成静态 发布页。
	 * @return
	 */
	@RequestMapping(value="releaseFreemarkerIndex")
	public ModelAndView releaseFreemarkerIndex(Integer departmentId){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/siteManage/releaseFreemarkerIndex");
		System.err.println("allNavigations()...departmentId:"+departmentId);
		return mav;
	}
	/**
	 * 一级导航列表 部门
	 * @return departmentId 部门Id
	 */
	@RequestMapping(value="allFirstNavigationsJson", method={RequestMethod.GET})
	public @ResponseBody List<Navigation> allFirstNavigationsJson(Integer departmentId){
		List<Navigation> firstNavigations = navigationService.getFirstNavigations(departmentId);
		List<Navigation> navList = new ArrayList<Navigation>();
		for(Navigation n : firstNavigations){
			Navigation nav = new Navigation(n.getId(), n.getName(), n.getLevel(), n.getUrl(), n.getTip(), n.getIco(), n.getStyle(), n.getSort(), n.isTargetBlank(), n.isDisplay(), n.getDescription(), n.getBoss());
			navList.add(nav);
		}
		return navList;
	}
	/**
	 * 所有部门JSON
	 * @return
	 */
	@RequestMapping(value="/allDepartmentsJson", method={RequestMethod.GET})
	public @ResponseBody List<Department>  allDepartmentsJson(){
		List<Department> departments = departmentService.getAllDepartments();
		List<Department> departmentList = new ArrayList<Department>();
		for(Department d : departments){
			Department department = new Department();
			department.setId(d.getId());
			department.setName(d.getName());
			departmentList.add(department);
		}
		return departmentList;
	}
	/**
	 * 当前用户管理下的部门列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/allManageDepartmentsJson", method={RequestMethod.GET})
	public @ResponseBody List<Department>  allManageDepartmentsJson(HttpServletRequest request){
		User user = getSessionUser(request);
//		List<Department> departments = departmentService.getAllDepartments();
		List<Department> departmentList = new ArrayList<Department>();
		for(Department d : user.getTeacher().getManagerDepartments()){
			Department department = new Department();
			department.setId(d.getId());
			department.setName(d.getName());
			departmentList.add(department);
		}
		return departmentList;
	}
	/**
	 * 创建导航栏
	 * 2014年12月11日18:37:53更新日志：
	 * controller控制层不负责Service层的内容，数据的处理应该放在service层中。这里将代码迁移到了service层处理。
	 * @param navigation
	 * @param fatherNavigationId
	 * @return
	 */
	@RequestMapping(value="createNavigation")
	public @ResponseBody Navigation createNavigation(Navigation navigation, Integer fatherNavigationId){
//		navigationService.save(navigation);
//		System.out.println("保存navigation之后：id="+navigation.getId());
//		if(fatherNavigationId != null){
//			Navigation father = navigationService.getNavigationById(fatherNavigationId);
//			navigation.setFatherNavigation(father);
//		}else{
//			navigation.setFatherNavigation(null);
//			navigation.setLevel(1);
//		}
//		if(navigation.getBoss() != 0){ //！=0 表示这个导航属于部门级导航。不是首页导航
//			Department department = departmentService.getDepartmentById(navigation.getBoss());
//			department.getNavigations().add(navigation);
////			navigation.getDepartments().add(department);
//			departmentService.mergeDepartment(department);
//		}else{
//			navigationService.mergeNavigation(navigation);
//		}
		
		return navigationService.saveNavigation(navigation, fatherNavigationId);
	}

	/**
	 * 编辑导航页面
	 * @param navigationId
	 * @return
	 */
	@RequestMapping(value="navigationEditView", method={RequestMethod.GET})
	public @ResponseBody Navigation navigationEditView(Integer navigationId){
		Navigation navigation = navigationService.getNavigationById(navigationId);
		Navigation nav = new Navigation();
		nav.setId(navigation.getId());
		nav.setName(navigation.getName());
		nav.setLevel(navigation.getLevel());
		nav.setUrl(navigation.getUrl());
		nav.setTip(navigation.getTip());
		nav.setIco(navigation.getIco());
		nav.setStyle(navigation.getStyle());
		nav.setSort(navigation.getSort());
		nav.setTargetBlank(navigation.isTargetBlank());
		nav.setDisplay(navigation.isDisplay());
		if(navigation.getFatherNavigation() != null){
			Navigation father = new Navigation();
			father.setId(navigation.getFatherNavigation().getId()); 
			father.setName(navigation.getFatherNavigation().getName());
			nav.setFatherNavigation(father);
		}
		nav.setDescription(navigation.getDescription());
		nav.setBoss(navigation.getBoss());
		return nav;
	}
	/**
	 * 删除导航页面
	 * @param navigationId
	 * @return
	 */
	@RequestMapping(value="navigationDelete", method={RequestMethod.GET})
	public @ResponseBody Integer navigationDelete(Integer navigationId){
//		Navigation nav =navigationService.getNavigationById(navigationId);
//		if(nav == null){
//			return navigationId;
//		}
//		Integer navId = nav.getId();
//		if(nav.getBoss() == 0){
//			navigationService.deleteNavigationById(navigationId);
//		}else{
//			for(Department d : nav.getDepartments()){
//				d.getNavigations().remove(nav);
//				departmentService.save(d);
//			}
//			navigationService.deleteNavigationById(navId);
//		}
		navigationService.deleteNavigationById(navigationId);
		System.out.println("删除导航("+navigationId+")成功。");
		return navigationId;
	}
	
	/**
	 * 获得Ajax部门下的板块
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "getDepartmentSectionsJson", method = { RequestMethod.GET })
	public @ResponseBody List<Section> getDepartmentSectionsJson(Integer departmentId){
		System.out.println("导航设置getDepartmentSections... "+departmentId);
		List<Section> sections  =  sectionService.getSectionsByDepartmentId(departmentId);
		System.out.println("sections.size="+sections.size());
		List<Section> sectionList = new ArrayList<Section>();
		for(Section s : sections){
			Section section = new Section(s.getName(), s.getDescription());
			System.out.println(section.getName());
			section.setId(s.getId());
			sectionList.add(section);
		}
		System.out.println("sectionList.size="+sectionList.size());
		return sectionList;
	}
	
	/**
	 * 获得Ajax部门下的板块
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value = "getSectionArticlesJson", method = { RequestMethod.GET })
	public @ResponseBody List<Article> getSectionArticlesJson(Integer sectionId){
		System.out.println("sectionList..."+sectionId);
			List<Article> arts = articleService.getAllSectionArticles(sectionId);
			System.out.println("arts.size="+arts.size());
			List<Article> artList = new ArrayList<Article>();
			for(Article a : arts){
				Article article = new Article(a.getTitle(), a.getType(), a.getTitleStyle(), a.isDisplay(), a.getContent(), a.getDate(), a.getIp(), a.getDescription());
				article.setId(a.getId());
				System.out.println(a.getTitle());
				artList.add(article);
			}
			System.out.println("artList.size="+artList.size());
			return artList;
	}
	
	/**
	 * 全部生成导航栏
	 * @return
	 */
	@RequestMapping(value="allNavigationsRelease", method={RequestMethod.GET})
	public @ResponseBody Integer allNavigationsRelease(HttpServletRequest request, Integer departmentId){
		List<Navigation> navigationList;
		if(departmentId == null || departmentId ==0){
			navigationList = navigationService.getFirstNavigations(0);
		}else{
			navigationList = navigationService.getFirstNavigations(departmentId);
		}
		String rootPath = request.getServletContext().getRealPath("/");
		boolean ok = CommonConstant.releaseAllNavigations(request.getContextPath(), rootPath, navigationList,departmentId);
		if(ok)
			return 1;
		else{
			return 0;
		}
	}
	///////////-----------------------------部门页------------------------------------------///////////
	// 部门页面
	/**
	 * 通过部门ID显示相对应的设置页面
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value="departmentPage", method={RequestMethod.GET})
	public ModelAndView departmentPage(Integer departmentId){
		ModelAndView mav = new ModelAndView();
		List<Navigation> navigations = navigationService.getAllNavigations(departmentId);
		if(navigations == null){
			navigations = new ArrayList<Navigation>();
		}
		List<Navigation> firstNavigations = navigationService.getFirstNavigations(departmentId);
		if(firstNavigations == null)	{
			firstNavigations = new ArrayList<Navigation>();
		}
		//sections  articlesBySection
		List<Section> sections = sectionService.getSectionsByDepartmentId(departmentId);
		mav.addObject("sections",sections);
		mav.addObject("navigations",navigations);
		mav.addObject("firstNavigations",firstNavigations);
		Department department = departmentService.getDepartmentById(departmentId);
		mav.addObject("departmentId",department.getId());
		mav.addObject("departmentName",department.getName());
		mav.addObject("departmentType",department.getDepartmentType().getName());

		mav.setViewName("manager/siteManage/departmentPage");
		return mav;
	}
	// 部门下的栏目
	//sectionDelete
	/**
	 * 通过sectionId来删除这个板块对象。
	 * @param sectionId
	 * @return
	 */
	@RequestMapping(value="sectionDelete", method={RequestMethod.GET})
	public @ResponseBody Integer sectionDelete(Integer sectionId){
		Section section = sectionService.getSectionById(sectionId);
		Integer departmentId = section.getDepartment().getId();
		 sectionService.deleteSectionById(sectionId);
		 System.out.println("正在删除栏目"+sectionId);
		 freeMarkertUtil.htmlSectionJsp(sectionId, departmentId, true);
		return sectionId;
	}
	/**
	 * 保存section。。
	 * @param section
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value="sectionEdit", method={RequestMethod.GET})
	public @ResponseBody Section sectionEdit(Section section, Integer departmentId){
		
		Section sec =  sectionService.saveSection(section, departmentId);
		freeMarkertUtil.htmlSectionJsp(section.getId(), section.getDepartment().getId(), false);
		return sec;
	}
	/**
	 * 
	 * @param sectionId
	 * @return 
	 */
	@RequestMapping(value="getSectionById", method={RequestMethod.GET})
	public @ResponseBody Section getSectionById(Integer sectionId){
		System.out.println("getSectionById .. 查询Section"+sectionId);
		Section section = sectionService.getSectionById(sectionId);
		Section sec = new Section();
		sec.setId(section.getId());
		sec.setName(section.getName());
		sec.setDescription(section.getDescription());
		section = null;
		return sec;
	}
	/////-----------------------------------------------下面是废弃的方法，用于参考--------------------------------////////
}
