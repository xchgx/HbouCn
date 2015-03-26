package com.xchgx.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xchgx.cons.CommonConstant;
import com.xchgx.domain.Article;
import com.xchgx.domain.DepartmentType;
import com.xchgx.domain.Permission;
import com.xchgx.domain.Section;
import com.xchgx.domain.Shortcut;
import com.xchgx.domain.User;
import com.xchgx.service.ArticleService;
import com.xchgx.service.DepartmentService;
import com.xchgx.service.DepartmentTypesService;
import com.xchgx.service.EmployeeService;
import com.xchgx.service.FreeMarkertUtil;
import com.xchgx.service.PermissionService;
import com.xchgx.service.SchoolService;
import com.xchgx.service.SectionService;
import com.xchgx.service.ShortcutService;
import com.xchgx.service.TeacherService;
import com.xchgx.service.UserService;
import com.xchgx.zsbwork.bean.Department;
import com.xchgx.zsbwork.bean.School;
import com.xchgx.zsbwork.bean.Teacher;

@Controller
@RequestMapping("/manager/sys")
public class SystemManageController extends BaseController{

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
	private FreeMarkertUtil freeMarkertUtil;
	/**
	 * 所有用户列表
	 * @return
	 */
	@RequestMapping(value="allUsers")
	public ModelAndView getAllUser(){
		ModelAndView mav = new ModelAndView();
		List<User> userList = userService.getAllUsers();
		mav.setViewName("manager/systemManage/allUsers");
		mav.addObject("users",userList);
		return mav;
	}
	@RequestMapping(value="searchUsers")
	public ModelAndView searchUsers(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/systemManage/searchUsers");
		return mav;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="doSearchUsers", method={RequestMethod.GET})
	public @ResponseBody List doSearchUsers(String table , String search){
//		System.out.println("table:"+table);
//		System.out.println("search:"+search);
		//通过table传递的PO字符串。查找对应的数据表
		if("User".equals(table)){
			return userService.searchUsersByLike(search);
		}else	if("Teacher".equals(table)){
			return teacherService.searchTeachersByLike(search);
		}else	if("Department".equals(table)){
			return departmentService.searchDepartmentsByLike(search);
		}else	if("Article".equals(table)){
			return articleService.searchArticlesByLike(search);
		}else	if("DepartmentType".equals(table)){
			
		}else	if("Permission".equals(table)){
			return permissionService.searchPermissionsByLike(search);
		}else	if("Shortcut".equals(table)){
			return shortcutService.searchShortcutsByLike(search);
		}else	if("Section".equals(table)){
			return sectionService.searchSectionsByLike(search);
		}else	if("Employee".equals(table)){
			
		}else	if("Student".equals(table)){
			
		}else	if("Work".equals(table)){
			
		}else	if("Address".equals(table)){
			
		}else	if("LoginLog".equals(table)){
			
		}
		return null;
	}
	/**
	 * 无使用者用户
	 * @return
	 */
	@RequestMapping(value="noTeacherUsers")
	public ModelAndView getNoTeacherUser(){
		ModelAndView mav = new ModelAndView();
		List<User> userList = userService.getNoTeacherUsers();
		mav.setViewName("manager/systemManage/allUsers");
		mav.addObject("users",userList);
		return mav;
	}
	/**
	 * 编辑用户页面
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="userEditView", method={RequestMethod.GET})
	public ModelAndView userEditView(Integer userId){
		ModelAndView mav = new ModelAndView();
		User user = userService.getUserById(userId);
		List<Permission> permissions = permissionService.getAllPermissions();
		List<Teacher> teachers = teacherService.getAllTeachers();
		mav.setViewName("manager/systemManage/userEditView");
		mav.addObject("user",user);
		mav.addObject("permissions", permissions);
		mav.addObject("teachers", teachers);
		return mav;
	}
	/**
	 * 创建用户页面
	 * @return
	 */
	@RequestMapping(value="userCreateView", method={RequestMethod.GET})
	public ModelAndView userCreateView(){
		ModelAndView mav = new ModelAndView();
		User user = new User();
		user.setCredit(100);
		user.setLocked(0);
		List<Permission> permissions = permissionService.getAllPermissions();
		List<Teacher> teachers = teacherService.getAllTeachers();
		mav.setViewName("manager/systemManage/userEditView");
		mav.addObject("user",user);
		mav.addObject("permissions", permissions);
		mav.addObject("teachers", teachers);
		return mav;
	}
	
	/**
	 * 编辑或添加一个User对象
	 * @return
	 */
	@RequestMapping(value="/userEdit", method={RequestMethod.POST})
	public ModelAndView userEdit(@Valid User user,  Integer teacherId, Integer permissionId, BindingResult errors){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/systemManage/userEditView");
		if(errors.hasErrors()){
			return mav;
		}
		if(teacherId != null) {
			Teacher teacher  = teacherService.getTeacherById(teacherId);
			 user.setTeacher(teacher);
		}
		if(permissionId != null){
			Permission permission = permissionService.getPermissionById(permissionId);
			 user.setPermission(permission);
		}
		userService.merge(user);
		List<User> userList = userService.getAllUsers();
		mav.setViewName("manager/systemManage/allUsers");
		mav.addObject("users",userList);
		return mav;
	}
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/userDelete", method={RequestMethod.GET})
	public ModelAndView userDelete(Integer userId){
		ModelAndView mav = new ModelAndView();
		userService.deleteUserById(userId);
		List<User> userList = userService.getAllUsers();
		mav.setViewName("manager/systemManage/allUsers");
		mav.addObject("users",userList);
		return mav;
	}
	/**
	 * 所有部门
	 * @return
	 */
	@RequestMapping(value="/allDepartments", method={RequestMethod.GET})
	public ModelAndView allDepartments(){
		ModelAndView mav = new ModelAndView();
		List<Department> departments = departmentService.getAllDepartments();
		mav.setViewName("manager/systemManage/allDepartments");
		mav.addObject("departments",departments);
		return mav;
	}
	/**
	 * 编辑部门页面
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value="departmentEditView", method={RequestMethod.GET})
	public ModelAndView departmentEditView(Integer departmentId){
		ModelAndView mav = new ModelAndView();
		Department department = departmentService.getDepartmentById(departmentId);
		List<Department> departments = departmentService.getAllDepartments();
		List<Department> childDepartments = departmentService.getSubDepartments(departmentId);
		List<School> schools = schoolService.getAllSchool();
		List<DepartmentType> departmentTypes = departmentTypesService.getAllDepartmentTypes();
		mav.setViewName("manager/systemManage/departmentEditView");
		mav.addObject("department",department);
		mav.addObject("childDepartments",childDepartments);
		mav.addObject("departments",departments);
		mav.addObject("schools",schools);
		mav.addObject("departmentTypes",departmentTypes);
		return mav;
	}
	/**
	 * 创建部门页面
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value="departmentCreateView", method={RequestMethod.GET})
	public ModelAndView departmentCreateView(){
		ModelAndView mav = new ModelAndView();
		Department department = new Department();
		List<Department> departments = departmentService.getAllDepartments();
		List<School> schools = schoolService.getAllSchool();
		List<DepartmentType> departmentTypes = departmentTypesService.getAllDepartmentTypes();
		mav.setViewName("manager/systemManage/departmentEditView");
		mav.addObject("department",department);
		mav.addObject("departments",departments);
		mav.addObject("schools",schools);
		mav.addObject("departmentTypes",departmentTypes);
		return mav;
	}
	/**
	 * 创建或编辑一个Department部门对象
		department部门
		id 唯一主键
		name 部门名称
		schoolId 部门归属学校
			#teachers 下属员工，不在部门编辑JSP页面中修改
			#articleTeachers 有发表文章权限的职工，不在部门编辑JSP页面中修改
		departmentTypeId  部门类型
			#manager 部门负责人头头2，不在部门编辑JSP页面中修改
		fatherDepartmentId 上级主管部门
			#childDepartment  下级部门
			#articles 文章列表，不在部门编辑JSP页面中修改，也无法修改。
			#section 板块，不在部门编辑JSP页面中修改。
		提交给 /manager/sys/departmentEdit.do
	 * @return
	 */
	@RequestMapping(value="/departmentEdit", method={RequestMethod.POST})
	public ModelAndView userEdit(Department department,  Integer schoolId, Integer departmentTypeId, Integer fatherDepartmentId ){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("manager/systemManage/departmentEditView");
		department.setName(CommonConstant.changeHtmlTag(department.getName()));
		if(schoolId != null){
			department.setSchool(schoolService.getSchoolById(schoolId));
		}
		if (departmentTypeId != null) {
			department.setDepartmentType(departmentTypesService.getDepartmentTypeById(departmentTypeId));
		}
		if (fatherDepartmentId != null) {
			department.setFatherDepartment(departmentService.getDepartmentById(fatherDepartmentId));
		}
		departmentService.save(department);
		List<Department> departments = departmentService.getAllDepartments();
		mav.setViewName("manager/systemManage/allDepartments");
		mav.addObject("departments",departments);
		return mav;
	}
	/**
	 * 删除部门
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/departmentDelete", method={RequestMethod.GET})
	public ModelAndView departmentDelete(Integer departmentId){
		ModelAndView mav = new ModelAndView();
		departmentService.deleteDepartmentById(departmentId);
		List<Department> departmentList = departmentService.getAllDepartments();
		mav.setViewName("manager/systemManage/allDepartments");
		mav.addObject("departments",departmentList);
		return mav;
	}
	/**
	 * 所有教师
	 * @return
	 */
	@RequestMapping(value="/allTeachers", method={RequestMethod.GET})
	public ModelAndView allTeachers(){
		ModelAndView mav = new ModelAndView();
		List<Teacher> teachers = teacherService.getAllTeachers();
		mav.setViewName("manager/systemManage/allTeachers");
		mav.addObject("teachers",teachers);
		return mav;
	}
	/**
	 * 编辑教师页面
	 * @param departmentId
	 * @return
	 */
	@RequestMapping(value="teacherEditView", method={RequestMethod.GET})
	public ModelAndView teacherEditView(Integer teacherId){
		ModelAndView mav = new ModelAndView();
		Teacher teacher = teacherService.getTeacherById(teacherId);
		List<Teacher> teachers = teacherService.getAllTeachers();
		List<School> schools = schoolService.getAllSchool();
		List<User> users = userService.getAllUsers();
		List<Department> departments = departmentService.getAllDepartments();
		mav.setViewName("manager/systemManage/teacherEditView");
		mav.addObject("teacher",teacher);
		mav.addObject("teachers",teachers);
		mav.addObject("schools",schools);
		mav.addObject("users",users);
		mav.addObject("departments",departments);
		return mav;
	}
	/**
	 * 创建教师页面
	 * @param teacherId
	 * @return
	 */
	@RequestMapping(value="teacherCreateView", method={RequestMethod.GET})
	public ModelAndView teacherCreateView(){
		ModelAndView mav = new ModelAndView();
		Teacher teacher = new Teacher();
		List<Teacher> teachers = teacherService.getAllTeachers();
		List<School> schools = schoolService.getAllSchool();
		List<User> users = userService.getAllUsers();
		List<Department> departments = departmentService.getAllDepartments();
		mav.setViewName("manager/systemManage/teacherEditView");
		mav.addObject("teacher",teacher);
		mav.addObject("teachers",teachers);
		mav.addObject("schools",schools);
		mav.addObject("users",users);
		mav.addObject("departments",departments);
		return mav;
	}
	
	/**
	 * 编辑或者新建一名教师
	 * @param teacher 教师对象 
	 * @param employeeId 中学教师对应的我校招生人员
	 * @param schoolId  教师归属学校
	 * @param departmentId 教师归属部门
	 * @param managerDepartmentId 教师管理的部门
	 * @param userId 教师使用的登录账号
	 * @param articleDepartmentId 教师可在哪些部门发表文章。
	 * @return 
	 */
	@RequestMapping(value="/teacherEdit", method={RequestMethod.POST})
	public ModelAndView teacherEdit(Teacher teacher,Integer employeeId, Integer schoolId, Integer departmentId[], Integer managerDepartmentId[],Integer userId, Integer articleDepartmentId[] ){
		ModelAndView mav = new ModelAndView(); 
		teacherService.saveTeacher(teacher, employeeId, schoolId, departmentId, managerDepartmentId, userId, articleDepartmentId);
		
		List<Teacher> teachers = teacherService.getAllTeachers();
		mav.setViewName("manager/systemManage/allTeachers");
		mav.addObject("teachers",teachers);
		return mav;
	}
	/**
	 * 删除教师
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/teacherDelete", method={RequestMethod.GET})
	public ModelAndView teacherDelete(Integer teacherId){
		ModelAndView mav = new ModelAndView();
		System.out.println("准备删除"+teacherId);
		teacherService.deleteTeacherById(teacherId);
		List<Teacher> teacherList = teacherService.getAllTeachers();
		mav.setViewName("manager/systemManage/allTeachers");
		mav.addObject("teachers",teacherList);
		return mav;
	}
	//*********权限************/

	/**
	 * 所有权限
	 * @return
	 */
	@RequestMapping(value="/allPermissions", method={RequestMethod.GET})
	public ModelAndView allPermissions(){
		ModelAndView mav = new ModelAndView();
		List<Permission> permissions = permissionService.getAllPermissions();
		mav.setViewName("manager/systemManage/allPermissions");
		mav.addObject("permissions",permissions);
		return mav;
	}
	/**
	 * 编辑权限页面
	 * @param permissionId
	 * @return
	 */
	@RequestMapping(value="permissionEditView", method={RequestMethod.GET})
	public ModelAndView permissionEditView(Integer permissionId){
		ModelAndView mav = new ModelAndView();
		Permission permission = permissionService.getPermissionById(permissionId);
		List<Shortcut> shortcuts = shortcutService.getAllShortcuts();
		mav.setViewName("manager/systemManage/permissionEditView");
		mav.addObject("permission",permission);
		mav.addObject("shortcuts",shortcuts);
		return mav;
	}
	/**
	 * 创建权限页面
	 * @param permissionId
	 * @return
	 */
	@RequestMapping(value="permissionCreateView", method={RequestMethod.GET})
	public ModelAndView permissionCreateView(){
		ModelAndView mav = new ModelAndView();
		Permission permission = new Permission();
		List<Shortcut> shortcuts = shortcutService.getAllShortcuts();
		mav.setViewName("manager/systemManage/permissionEditView");
		mav.addObject("permission",permission);
		mav.addObject("shortcuts",shortcuts);
		return mav;
	}
	/**
	 * 权限修改操作
	 * @param permission
	 * @param shortcutId
	 * @return
	 */
	@RequestMapping(value="/permissionEdit", method={RequestMethod.POST})
	public ModelAndView permissionEdit(Permission permission,Integer shortcutId[]){
		ModelAndView mav = new ModelAndView(); 
		if(permission.getId()==null){
			permissionService.savePermission(permission);
			System.err.println(permission.getId());
		}
		if(shortcutId !=null ){ 
			for(Integer id : shortcutId){
				permission.getShortcuts().add(shortcutService.getShortcutById(id));
			}
		}
		permissionService.mergePermission(permission);
		mav.setViewName("manager/systemManage/permissionEditView");
		return allPermissions();
	}
	/**
	 * 删除权限
	 * @param permissionId
	 * @return
	 */
	@RequestMapping(value="/permissionDelete", method={RequestMethod.GET})
	public ModelAndView permissionDelete(Integer permissionId){
		//ModelAndView mav = new ModelAndView();
		permissionService.deletePermissionById(permissionId);
		return allPermissions();
	}
	//*********************桌面图标*****************************/

	/**
	 * 所有桌面图标
	 * @return
	 */
	@RequestMapping(value="/allShortcuts", method={RequestMethod.GET})
	public ModelAndView allShortcuts(){
		ModelAndView mav = new ModelAndView();
		List<Shortcut> shortcuts = shortcutService.getAllShortcuts();
		mav.setViewName("manager/systemManage/allShortcuts");
		mav.addObject("shortcuts",shortcuts);
		return mav;
	}
	/**
	 * 编辑桌面图标页面
	 * @param shortcutId
	 * @return
	 */
	@RequestMapping(value="shortcutEditView", method={RequestMethod.GET})
	public ModelAndView shortcutEditView(Integer shortcutId){
		ModelAndView mav = new ModelAndView();
		Shortcut shortcut = shortcutService.getShortcutById(shortcutId);
		List<Permission> permissions = permissionService.getAllPermissions();
		mav.setViewName("manager/systemManage/shortcutEditView");
		mav.addObject("shortcut",shortcut);
		mav.addObject("permissions",permissions);
		return mav;
	}
	/**
	 * 创建桌面图标页面
	 * @param shortcutId
	 * @return
	 */
	@RequestMapping(value="shortcutCreateView", method={RequestMethod.GET})
	public ModelAndView shortcutCreateView(){
		ModelAndView mav = new ModelAndView();
		Shortcut shortcut = new Shortcut();
		List<Permission> permissions = permissionService.getAllPermissions();
		mav.setViewName("manager/systemManage/shortcutEditView");
		mav.addObject("shortcut",shortcut);
		mav.addObject("permissions",permissions);
		return mav;
	}
	/**
	 * 桌面图标修改操作
	 * @param shortcut
	 * @param shortcutId
	 * @return
	 */
	@RequestMapping(value="/shortcutEdit", method={RequestMethod.POST})
	public ModelAndView shortcutEdit(Shortcut shortcut,Integer permissionId[]){
		ModelAndView mav = new ModelAndView(); 
		if(shortcut.getId()==null){
			shortcutService.saveShortcut(shortcut);
			System.err.println(shortcut.getId());
		}
		if(permissionId !=null ){ 
			for(Integer id : permissionId){
				//shortcut.getPermissions().add(permissionService.getPermissionById(id));
				Permission permission = permissionService.getPermissionById(id);
				permission.getShortcuts().add(shortcut);
				permissionService.mergePermission(permission);
			}
		}
		shortcutService.mergeShortcut(shortcut);
		mav.setViewName("manager/systemManage/shortcutEditView");
		return allShortcuts();
	}
	/**
	 * 删除桌面图标
	 * @param shortcutId
	 * @return
	 */
	@RequestMapping(value="/shortcutDelete", method={RequestMethod.GET})
	public ModelAndView shortcutDelete(Integer shortcutId){
		//ModelAndView mav = new ModelAndView();
		shortcutService.deleteShortcutById(shortcutId);
		return allShortcuts();
	}
	
	/**********************文章编辑**************************/
	/**
	 * 所有文章
	 * @return
	 */
	@RequestMapping(value="/allArticles", method={RequestMethod.GET})
	public ModelAndView allArticles(){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("manager/systemManage/allArticles");
		mav.addObject("articlesPage",articleService.getFirstAllPage());
		return mav;
	}
	/**
	 * 分页显示文章列表
	 * @param index 页码
	 * @return
	 */
	@RequestMapping(value="/allArticlesPage", method={RequestMethod.GET})
	public ModelAndView allArticlesPage(Integer index){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("manager/systemManage/allArticles");
		mav.addObject("articlesPage",articleService.getNumberAllPage(index));
		return mav;
	}
	
	
	/**
	 * 编辑文章页面
	 * @param articleId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="articleEditView", method={RequestMethod.GET})
	public ModelAndView articleEditView(HttpServletRequest request,Integer articleId){
		ModelAndView mav = new ModelAndView();
		if(articleId == null){
			return articleCreateView(request);
		}
		Article article = articleService.getArticleById(articleId);
		mav.setViewName("manager/systemManage/articleEditView");
		mav.addObject("article",article);
		mav.addObject("teacher", getSessionUser(request).getTeacher());
		Permission permission = getSessionUser(request).getPermission();
		if(permission.getLevel() <= 2){
			mav.addObject("departments", departmentService.getAllDepartments());
		}else{
			mav.addObject("departments", new ArrayList(getSessionUser(request).getTeacher().getArticleDepartments()));
		}
		return mav;
	}
	/**
	 * 创建文章页面
	 * @param articleId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="articleCreateView", method={RequestMethod.GET})
	public ModelAndView articleCreateView(HttpServletRequest request){
		System.out.println("创建新文章发布页面：articleCreateView");
		ModelAndView mav = new ModelAndView();
		Article article = new Article();
		mav.setViewName("manager/systemManage/articleEditView");
		mav.addObject("article",article);
		mav.addObject("teacher", getSessionUser(request).getTeacher());
		Permission permission = getSessionUser(request).getPermission();
		if(permission.getLevel() <= 2){
			mav.addObject("departments", departmentService.getAllDepartments());
		}else{
			mav.addObject("departments", new ArrayList(getSessionUser(request).getTeacher().getArticleDepartments()));
		}
		return mav;
	}
	/**
	 * 文章修改操作
	 * @param article
	 * @param teacherId
	 * @param departmentId
	 * @param sectionId
	 * @return
	 */
	@RequestMapping(value="/articleEdit", method={RequestMethod.POST})
	public @ResponseBody String articleEdit(HttpServletRequest request ,Article article, Integer departmentId, Integer sectionId){
		//ModelAndView mav = new ModelAndView(); 
		int artId = 0;
		if(article.getId()==null){
//			articleService.saveArticle(article);
			artId = 5;
			System.err.println(article.getId());
		}else{
			artId=100+article.getId();
		}
		article.setAuthor(getSessionUser(request).getTeacher());
		if(departmentId != null){
			Department department = departmentService.getDepartmentById(departmentId);
			article.setDepartment(department);
		}
		System.out.println(sectionId);
		if(sectionId != null){
			Section section = sectionService.getSectionById(sectionId);
			article.setSection(section);
			System.out.println(section.getName());
		}
//		if(article.getTitleStyle().equals("2")){
//			 article.setTitle("<b style='color:red'>"+article.getTitle()+"</b>");
//		}else if(article.getTitleStyle().equals("3")){
//			article.setTitle("<i>"+article.getTitle()+"</i>");
//		}
//		System.out.println("articleEdit :   article.titleStyle="+article.getTitleStyle());
		article.setIp(request.getRemoteAddr());
		articleService.saveArticle(article);//包含更新文件
		//mav.setViewName("redirect:/manager/systemManage.do");
		freeMarkertUtil.htmlArticleJsp( article.getId(), departmentId, sectionId, false);//false表示创建更新
		String closeWindowScript = "";
//		closeWindowScript+="<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"></head>";
		closeWindowScript+= "<script type=\"text/javascript\" src=\""+request.getContextPath()+"/jquery-ui-1.11.1/external/jquery/jquery.js\"></script>";
		closeWindowScript+= "<script charset=\"utf-8\" src=\""+request.getContextPath()+"/js/articleCloseMessage.js\"></script>";
		closeWindowScript+= "<script charset=\"utf-8\">";
		closeWindowScript+="articleCloseMessage("+artId+");";
//		closeWindowScript+= "str='\u6587\u7ae0\u6dfb\u52a0\u6210\u529f\uff0c\u662f\u5426\u7ee7\u7eed\u6dfb\u52a0\u6587\u7ae0\uff1f'";
//		closeWindowScript+="if(confirm(unescape(str.replace(/\\\\/g, \"%\")))){";
//		closeWindowScript+="history.go(-1);";
//		closeWindowScript+="}else{";
//		closeWindowScript += "var obj = this.parent.window.document.getElementById('window_"+artId+"_warp');";
//		closeWindowScript += "var w = $(obj).attr('window');";
//		closeWindowScript += "$(\".task-window li[window='\"+w+\"']\",this.parent.window.document).remove();";
//		closeWindowScript += "$(obj).remove();";
//		closeWindowScript+="}";
		closeWindowScript += "</script>";
//		closeWindowScript += "</html>";
		/**
if(confirm("文章添加成功，是否继续添加文章？")){//\u6587\u7ae0\u6dfb\u52a0\u6210\u529f\uff0c\u662f\u5426\u7ee7\u7eed\u6dfb\u52a0\u6587\u7ae0\uff1f

	alert("是");
}else{
	alert("不是");
}
		 */
		return closeWindowScript;
	}
//	@RequestMapping(value="/articleEdit", method={RequestMethod.POST})
//	public ModelAndView articleEdit(HttpServletRequest request,Article article, Integer departmentId, Integer sectionId){
//		ModelAndView mav = new ModelAndView(); 
//		if(article.getId()==null){
//			articleService.saveArticle(article); 
//			System.err.println(article.getId());
//		}
//		article.setAuthor(getSessionUser(request).getTeacher());
//		if(departmentId != null){
//			Department department = departmentService.getDepartmentById(departmentId);
//			article.setDepartment(department);
//		}
//		System.out.println(sectionId);
//		if(sectionId != null){
//			Section section = sectionService.getSectionById(sectionId);
//			article.setSection(section);
//			System.out.println(section.getName());
//		}
//		articleService.mergeArticle(article);
//		mav.setViewName("redirect:/manager/systemManage.do");
//		return mav;
//	}
	/**
	 * 删除文章
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value="/articleDelete", method={RequestMethod.GET})
	public @ResponseBody String articleDelete(Integer articleId){
		System.out.println("删除的文章ID："+articleId);
		Integer departmentId = departmentService.getDepartmentByArticleId(articleId).getId();
//		Integer departmentId = article.getDepartment().getId(); //可能延迟加载的问题，所以这里无法获得department
		Integer sectionId = sectionService.getSectionByArticleId(articleId).getId();
//		Integer sectionId = article.getSection().getId();
		
		articleService.deleteArticleById(articleId);//包含删除文件
		
		//更新文章首页和文章页
		freeMarkertUtil.htmlArticleJsp( articleId, departmentId, sectionId,  true);//true表示删除文件
		//更新部门文章列表
		return "<script>alert('Delete "+articleId+" OK!');history.go(-1);</script>";
		
	}
	/**
	 * 通过部门ID查找部门下的栏目
	 * @param departmentId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="getDepartmentSections", method={RequestMethod.GET})
	public @ResponseBody List getDepartmentSections(Integer departmentId){
		Department department = departmentService.getDepartmentById(departmentId);
		List<Section> sections = new ArrayList<Section>();
		for(Section s : department.getSections()){
			Section section = new Section(s.getName(), s.getDescription());
			section.setId(s.getId());
			sections.add(section);
		}
		return sections;
	}
	/**
	 * 快速添加部门下的栏目板块，目前栏目板块海没有添加子栏目的功能，数据库中只是预留了这个字段。
	 * @param departmentId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="doCreateSection", method={RequestMethod.GET})
	public @ResponseBody List doCreateSection(HttpServletRequest request, Integer departmentId, String sectionName, String sectionDescription){
//		Department department = departmentService.getDepartmentById(departmentId);
//		for(Section s : department.getSections()){ //判断相同部门下是否已经存在这个板块名称。。
//			if(s.getName().equals(sectionName)){
//				return getDepartmentSections(departmentId);
//			}
//		}
		Section section = new Section(sectionName, sectionDescription);
//		section.setDepartment(department);
		sectionService.saveSection(section,departmentId);
//		FreeMarkertUtil.htmlSectionJsp(request.getServletContext().getRealPath("/"), request.getContextPath(), section);
		return getDepartmentSections(departmentId);
	}
	// 属性设置
	
	@RequestMapping(value="initPropertiesFile", method={RequestMethod.GET})
	public @ResponseBody ModelAndView initPropertiesFile(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		if(getSessionUser(request).getPermission().getLevel() == 1){
			
		}
		mav.setViewName("manager/systemManage/initPropertiesFile");
		return mav;
	}
}
