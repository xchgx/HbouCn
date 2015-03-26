package com.xchgx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xchgx.dao.DepartmentTypeDao;
import com.xchgx.dao.PermissionDao;
import com.xchgx.dao.SectionDao;
import com.xchgx.dao.ShortcutDao;
import com.xchgx.dao.UserDao;
import com.xchgx.domain.DepartmentType;
import com.xchgx.domain.Permission;
import com.xchgx.domain.Section;
import com.xchgx.domain.Shortcut;
import com.xchgx.domain.User;
import com.xchgx.zsbwork.bean.Department;
import com.xchgx.zsbwork.bean.School;
import com.xchgx.zsbwork.bean.Teacher;
import com.xchgx.zsbwork.dao.DepartmentDao;
import com.xchgx.zsbwork.dao.SchoolDao;
import com.xchgx.zsbwork.dao.TeacherDao;

@Service
public class InitDatabaseService {

	@Autowired
	private ShortcutDao shortcutDao;
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DepartmentTypeDao departmentTypeDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private SchoolDao schoolDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private SectionDao sectionDao;

	/**
	 * 初始化数据库
	 */
	public String initDbData() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(initUserTableData()+"<br>");
		System.err.println("User初始化完毕");
		stringBuilder.append(initPermissionTableData()+"<br>");
		System.err.println("Permission初始化完毕");
		stringBuilder.append(initShortcutTableData()+"<br>");
		System.err.println("Shortcut初始化完毕");
		stringBuilder.append(initDepartmentTypeTableData()+"<br>");
		System.err.println("DepartmentType初始化完毕");
		stringBuilder.append(initDepartmentTableData()+"<br>");
		System.err.println("Department初始化完毕");
		stringBuilder.append(iniSchoolTableData()+"<br>");
		System.err.println("School初始化完毕");
		stringBuilder.append(initTeacherTableData()+"<br>");
		System.err.println("Teacher初始化完毕");
		stringBuilder.append(initSectionTableData()+"<br>");
		System.err.println("Section初始化完毕");
		return stringBuilder.toString();
	}
	
	/**
	 * 初始化关系表。
	 */
	public String initRelationDb(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(initUserPermissionTableData()+"<br>");
		System.err.println("UserPermission初始化完毕");
		stringBuilder.append(initPermissionShortcutTableData()+"<br>");
		System.err.println("PermissionShortcut初始化完毕");
		stringBuilder.append(initDepartmentAndTypeData()+"<br>");
		System.err.println("DepartmentAndType初始化完毕");
		stringBuilder.append(initUserTeacherData()+"<br>");
		System.err.println("UserTeacher用户与教职工一对一关系初始化完毕");
		stringBuilder.append(initTeacherDepartmentTableData()+"<br>");
		System.err.println("TeacherDepartment初始化完毕");
		stringBuilder.append(initDearptmentAndDepartmentData()+"<br>");
		System.err.println("fatherDepartmentAndchildDepartment初始化完毕");
		stringBuilder.append(initDepartmentSchoolData()+"<br>");
		System.err.println("Department School初始化完毕");
		return stringBuilder.toString();
		
	}
	/**
	 * 清空数据表
	 * @return
	 */
	public String initClearDb(){
		StringBuilder stringBuilder = new StringBuilder();
		userDao.removeAll("from User");
		stringBuilder.append("t_user用户表已清空");
		permissionDao.removeAll("from Permission");
		stringBuilder.append("t_permission权限表已清空");
		shortcutDao.removeAll("from Shortcut");
		stringBuilder.append("t_shortct图标表已清空");
		departmentTypeDao.removeAll("from DepartmentType");
		stringBuilder.append("t_departmentType部门类别表已清空");
		departmentDao.removeAll("from Department");
		stringBuilder.append("t_department部门表已清空");
		schoolDao.removeAll("from School");
		stringBuilder.append("t_school学校表已清空");
		teacherDao.removeAll("from Teacher");
		stringBuilder.append("t_teacher教师表已清空");
		return stringBuilder.toString();
	}

	/**
	 * 初始化t_user表
	 */
	public String  initUserTableData() {
		userDao.save(new User("admin", "超级管理员", "ADMIN1",  0, 100));
		userDao.save(new User("xchgx", "陈刚", "123456",  0, 100));
		userDao.save(new User("jidian", "机电信息工程学院", "123456", 0, 100));
		userDao.save(new User("renwen", "人文学院", "123456", 0, 100));
		userDao.save(new User("caijing", "财经学院", "123456", 0, 100));
		userDao.save(new User("guanli", "管理学院", "123456", 0, 100));
		userDao.save(new User("zhaosheng", "招生办", "123456", 0, 100));
		userDao.save(new User("jiuye", "就业办", "123456", 0, 100));
		userDao.save(new User("jiaowu", "教务处", "123456", 0, 100));
		userDao.save(new User("softroom", "软件工作室", "123456", 0, 100));
		return "t_user表初始化成功(10条记录)";
	}

	/**
	 * 初始化t_permission表
	 */
	public String initPermissionTableData() {
		Permission permission1 = new Permission(1, "超级管理员", "超管可以拥有用户管理+所有部门权限");
		Permission permission2 = new Permission(2, "校级", "所有部门权限");
		Permission permission3 = new Permission(3, "部门级", "所在部门权限");
		Permission permission4 = new Permission(4, "专业级", "部门下的具体专业权限");
		Permission permission5 = new Permission(5, "班级", "专业下的具体班");
		Permission permission6 = new Permission(6, "学生级", "具体学生登录，查询自己相>关信息");
		permissionDao.save(permission1);
		permissionDao.save(permission2);
		permissionDao.save(permission3);
		permissionDao.save(permission4);
		permissionDao.save(permission5);
		permissionDao.save(permission6);
		return "t_permission权限表初始化成功(6条记录)";
	}

	/**
	 * 初始化t_shortcut表
	 */
	public String initShortcutTableData() {
		shortcutDao.save(new Shortcut(0, "我的电脑", "images/icon/computer.png",
				"../manager/building.do", 600, 400, ""));
		shortcutDao.save(new Shortcut(1, "系统控制", "images/icon/11indexico.png",
				"../manager/systemManage.do", 600, 800, ""));
		shortcutDao.save(new Shortcut(2, "新闻管理", "images/icon/12newsico.png",
				"newsManager/main.do", 600, 800, ""));
		shortcutDao.save(new Shortcut(3, "用户管理", "images/icon/13usersico.png",
				"../manager/building.do", 600, 400, ""));
		shortcutDao.save(new Shortcut(4, "初始化", "images/icon/chushihuaico.png",
				"../initDbView.do", 600, 400, ""));	
		shortcutDao.save(new Shortcut(5, "发表文章", "images/icon/releaseArticleico.png",
						"../manager/news/entranceView.do", 600, 900, ""));
		
		return "t_shortcut图标表初始化成功(6条记录)";
	}

	/**
	 * 初始化t_permission 和 t_shortcut 两者之间的关系表PermissionShortcut ;
	 */
	public String  initPermissionShortcutTableData() {
		List<Shortcut> shortcuts = shortcutDao.list();
		List<Permission> permissions = permissionDao.list();
		if (shortcuts != null && permissions != null) {
			for (Permission p : permissions) {
				switch (p.getLevel()) {
				case 1:
					for (Shortcut sc : shortcuts) {
						p.getShortcuts().add(sc);
					}
					break;
				case 2:
					for (Shortcut sc : shortcuts) {
						if (sc.getIcoid() == 4 || sc.getIcoid() == 3)
							continue;// 初始化按钮和用户管理桌面图标不出现
						p.getShortcuts().add(sc);
					}
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				default:
					break;
				}
				permissionDao.save(p);
			}
		}
		return "PermissionShortcut权限图标关系表初始化成功（仅初始化1和2超级权限和校级权限）";
	}

	/**
	 * 初始化用户的权限，t_user表合t_permission表的关系表UserPermission;
	 */
	public String initUserPermissionTableData() {
		List<Permission> permissions = permissionDao.list();
		User user = userDao.getUserByName("admin");
		if(user != null){
			for(Permission p : permissions){
				if(p.getLevel() == 1){
					user.setPermission(p);
				}
			}
		}
				
		return "UserPermission用户权限表初始化成功（用户type与权限level挂钩）";
	}
	
	/**
	 * 初始化部门类型表：校级，部门及，专业级，班级等等t_departmentType表
	 * 	private Integer id;
	private String name;//类型名称，比如说：行政部门，院系，班级。
	private String description;
	private Set<Department> departments;
	 */
	public String initDepartmentTypeTableData(){
		departmentTypeDao.save(new DepartmentType("行政部门","行政管理部门"));
		departmentTypeDao.save(new DepartmentType("院系","各二级学院部门"));
		departmentTypeDao.save(new DepartmentType("校企合作","校企合作部门，比如海乘，电子商务实训室，软件工作室等"));
		return "t_epartmentType部门类别表初始化成功（3条记录）";
	}
	
	/**
	 * 初始化部门表t_department
	 * 	private Integer id;
	private String name;
	private School school;
	private Set<Teacher> teachers;
	private String description;// 备注
	 */
	public String  initDepartmentTableData(){
		departmentDao.save(new Department("信息机电工程学院","各二级学院部门",2));
		departmentDao.save(new Department("人文艺术学院","各二级学院部门",2));
		departmentDao.save(new Department("财经学院","各二级学院部门",2));
		departmentDao.save(new Department("管理学院","各二级学院部门",2));
		departmentDao.save(new Department("继续教育学院","各二级学院部门",2));
		departmentDao.save(new Department("科技培训中心学院","各二级学院部门",2));
		departmentDao.save(new Department("校办","校长办公室",1));
		departmentDao.save(new Department("招生办","招生办公室",2));
		departmentDao.save(new Department("就业办","就业办公式",2));
		departmentDao.save(new Department("教务处","教务处",2));
		departmentDao.save(new Department("学工处","学工处",2));
		departmentDao.save(new Department("人事处","人事处",2));
		departmentDao.save(new Department("财务处","财务处",2));
		departmentDao.save(new Department("电教中心","现代网络教育",2));
		departmentDao.save(new Department("图书馆","图书馆",3));
		departmentDao.save(new Department("总务处","总务处",2));
		departmentDao.save(new Department("保卫处","保卫处",2));
		departmentDao.save(new Department("后勤处","后勤管理公司",2));
		departmentDao.save(new Department("校刊校报","校刊校报",3));
		departmentDao.save(new Department("电子商务实训室","电子商务实训室",3));
		departmentDao.save(new Department("物流实训室","物流实训室",3));
		departmentDao.save(new Department("软件工作室","软件工作室",3)); 
		departmentDao.save(new Department("海乘培训室","海乘培训",2));
		departmentDao.save(new Department("天歌电子公司","天歌电子公司",2));
		departmentDao.save(new Department("水利水电公司","水利水电公司",2));
		departmentDao.save(new Department("软件设计专业","信息机电工程学院",3));
		return "t_department部门表初始化成功（24条记录）";
	}
	
	/**
	 * 初始化关系表t_departmentType 和 t_department 
	 */
	public String initDepartmentAndTypeData(){
		List<Department> departments = departmentDao.list();
		List<DepartmentType> departmentTypes = departmentTypeDao.list();
		for(DepartmentType dt: departmentTypes){
			System.out.println(dt.getName());
			if(dt.getName().indexOf("行政部门")>=0){
				for(Department d:departments){
					if(d.getName().indexOf("办")>=0 || d.getName().indexOf("处")>=0){
						d.setDepartmentType(dt);
						departmentDao.save(d);
						System.out.println(d.getName());
					}
				}
			}
			if(dt.getName().indexOf("院系")>=0){
				for(Department d:departments){
					if(d.getName().indexOf("学院")>=0){
						d.setDepartmentType(dt);
						departmentDao.save(d);
						System.out.println(d.getName());
					}
				}
			}
			if(dt.getName().indexOf("校企合作")>=0){
				for(Department d:departments){
					if(d.getName().indexOf("室")>=0 || d.getName().indexOf("公司")>=0){
						d.setDepartmentType(dt);
						departmentDao.save(d);
						System.out.println(d.getName());
					}
				}
			}
			//departmentTypeDao.save(dt);
		}
		return "部门与部门类别关系表初始化成功（建立关系）";
	}
	
	/**
	 * 初始化学校表。t_school
	 */
	public String iniSchoolTableData(){
		schoolDao.save(new School("湖北开放职业学院","大学","职业学院"));
		return "t_school学校表初始化成功（1条记录）";
	}
	
	/**
	 * 初始化教师表t_teacher
	 */
	public String initTeacherTableData(){
		teacherDao.save(new Teacher("20070613","陈刚","男", 31, "主任", "15527183314", "招生办主任"));
		teacherDao.save(new Teacher("20110614","唐驰","男", 25, "职员", "13554097880", "招生办"));
		return "t_teacher教师表初始化成功（1条记录）";
	}
	/**
	 * 初始化栏目板块表t_section
	 */
	public String initSectionTableData(){
		sectionDao.save(new Section("部门人员", "机电信息学院部门人员信息"));
		sectionDao.save(new Section("部门人员", "光电信息部门人员信息"));
		return "t_teacher教师表初始化成功（1条记录）";
	}

	/**
	 * 初始化用户与教职工一对一关系，
	 */
	public String initUserTeacherData(){
		User user = userDao.getUserByName("admin");
		Teacher teacher = teacherDao.getTeacherByJgh("20070613");
		if(user == null || teacher == null){
			return "用户与教职工建立一对一关系失败，没找到用户或教师";
		}
		user.setTeacher(teacher);
		userDao.save(user);
		return "admin超级管理与陈刚教师建立了一对一关系。";
	}
	
	/**
	 * 初始化Teacher与Department部门的关系。
	 */
	public String initTeacherDepartmentTableData(){
		Teacher teacher = teacherDao.getTeacherByJgh("20070613");
		List<Department> departments = departmentDao.list();
		for(Department d : departments){
			teacher.getArticleDepartments().add(d);
		}
		teacherDao.save(teacher);
		return "Teacher主管部门与所属部门关系建立";
	}
	/**
	 * 初始化部门与子部门的关系
	 */
	public String initDearptmentAndDepartmentData(){
		Department department = departmentDao.getDepartmentByName("软件设计专业");
		Department departmentyx = departmentDao.getDepartmentByName("信息机电工程学院");
		department.setFatherDepartment(departmentyx);
		departmentDao.save(department);
		return "软件设计专业属于信息机电工程学院";
	}
	/**
	 * 初始化部门与学校的关系。
	 */
	public String initDepartmentSchoolData(){
		School school = schoolDao.getSchoolByName("湖北开放职业学院");
		for(Department d : departmentDao.list()){
			d.setSchool(school);
			departmentDao.save(d);
		}
		return "所有部门都初始化为湖北开放职业学院。";
	}
}
