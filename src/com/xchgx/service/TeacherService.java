package com.xchgx.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xchgx.dao.UserDao;
import com.xchgx.domain.Article;
import com.xchgx.domain.User;
import com.xchgx.zsbwork.bean.Department;
import com.xchgx.zsbwork.bean.School;
import com.xchgx.zsbwork.bean.Teacher;
import com.xchgx.zsbwork.dao.DepartmentDao;
import com.xchgx.zsbwork.dao.EmployeeDao;
import com.xchgx.zsbwork.dao.SchoolDao;
import com.xchgx.zsbwork.dao.TeacherDao;

@Service
public class TeacherService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private TeacherDao teacherDao;
	@Autowired
	private SchoolDao schoolDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DepartmentDao departmentDao;
	/**
	 * 获得登陆用户对应的教职工信息。
	 * @param user 登陆用户
	 * @return 教师信息
	 */
	public Teacher getTeacherByUser(User user){
		User dbUser = userDao.load(user.getId());
		return dbUser.getTeacher();
	}
	public List<Teacher> getAllTeachers(){
		return teacherDao.list();
	}
	public Teacher getTeacherById(Integer id){
		return teacherDao.get(id);
	}
	public void saveTeacher(Teacher teacher){
		teacherDao.save(teacher);
	}
	/**
	 * 保存教师编辑页面传递过来的设置
	 * @param teacher 教师
	 * @param employeeId 中学教师对应我校的招生人员
	 * @param schoolId 教师归属学校
	 * @param departmentId 教师归属部门
	 * @param managerDepartmentId 教师管理下的部门
	 * @param userId 教师的登录账号
	 * @param articleDepartmentId 教师可以发表文章的部门。
	 */
	public void saveTeacher(Teacher teacher,Integer employeeId, Integer schoolId, Integer departmentId[], Integer managerDepartmentId[],Integer userId, Integer articleDepartmentId[]){
		// ---判断教师属于新建还是修改 start{
//		teacherDao.save(teacher); //不能直接保存。。。
//		System.err.println(teacher.getId()+"管理的部门有："+teacher.getManagerDepartments().size());
		
		if(teacher.getId()==null){ //创建一名教师
			System.out.println("创建Teacher");
			createTeacher(teacher, employeeId, schoolId, departmentId, managerDepartmentId, userId, articleDepartmentId);
		}else{
			System.out.println("保存修改的Teacher");
			modifyTeacher(teacher, employeeId, schoolId, departmentId, managerDepartmentId, userId, articleDepartmentId);
		}
	}
	/**
	 * 单独写一个创建教师的服务
	 * @param teacher
	 * @param employeeId
	 * @param schoolId
	 * @param departmentId
	 * @param managerDepartmentId
	 * @param userId
	 * @param articleDepartmentId
	 */
	public void createTeacher(Teacher teacher,Integer employeeId, Integer schoolId, Integer departmentId[], Integer managerDepartmentId[],Integer userId, Integer articleDepartmentId[]){
	 
		// ---设置教师归属学校 start{
		if(schoolId != null){
			System.out.println();
			teacher.setSchool(schoolDao.get(schoolId));
		} 
		//  设置教师归属学校 stop---- }
				
		// ---设置招生人员，当教师是中学老师的时候，才可以设置该项 start{
		if(employeeId != null){
			teacher.setEmployee(employeeDao.get(employeeId));
		} 
		//设置招生人员，当教师是中学老师的时候，才可以设置该项 stop----}
		
		// ---给教师配置一个登陆账号  start{
		if(userId != null){
			teacher.setUser(userDao.get(userId));
		} 
		// 给教师配置一个登陆账号  stop---- }
				
		// ---设置教师归属部门  start{
		if(departmentId !=null){
			for(Integer id : departmentId){
				teacher.getDepartments().add(departmentDao.get(id));
			}
		} 
		// 设置教师归属部门  stop----}
		
		// ---设置教师管理下的部门 start{
		if(managerDepartmentId !=null){
			for(Integer id : managerDepartmentId){
				Department department = departmentDao.get(id);
				System.out.println(teacher.getName()+"新增的管理部门："+department.getName());
				department.setManager(teacher);
				departmentDao.save(department);
			}
		} 
		// 设置教师管理下的部门 stop----}
		
		// ----设置教师可以发表文章的部门 start{
		if(articleDepartmentId !=null ){ 
			for(Integer id : articleDepartmentId){
				teacher.getArticleDepartments().add(departmentDao.get(id));
			}
		} 
		// 设置教师可以发表文章的部门 stop----}
		teacherDao.save(teacher); //保存teacher
	}
	/**
	 * 修改已经存在的教师。。
	 * @param teacher
	 * @param employeeId
	 * @param schoolId
	 * @param departmentId
	 * @param managerDepartmentId
	 * @param userId
	 * @param articleDepartmentId
	 */
	public void modifyTeacher(Teacher teacher,Integer employeeId, Integer schoolId, Integer departmentId[], Integer managerDepartmentId[],Integer userId, Integer articleDepartmentId[]){
			
		Teacher teach = teacherDao.get(teacher.getId());
		teach.setJgh(teacher.getJgh());
		teach.setName(teacher.getName());
		teach.setSex(teacher.getSex());
		teach.setAge(teacher.getAge());
		teach.setPosition(teacher.getPosition());
		teach.setPhone(teacher.getPhone());
		teach.setDescription(teacher.getDescription());
		
		// ---设置教师归属学校 start{
		if(schoolId != null){
			teach.setSchool(schoolDao.get(schoolId));
		}else{
			teach.setSchool(null);
		}
		//  设置教师归属学校 stop---- }
				
		// ---设置招生人员，当教师是中学老师的时候，才可以设置该项 start{
		if(employeeId != null){
			teach.setEmployee(employeeDao.get(employeeId));
		}else{
			teach.setEmployee(null);
		}
		//设置招生人员，当教师是中学老师的时候，才可以设置该项 stop----}
		
		// ---给教师配置一个登陆账号  start{
		if(userId != null){
			teach.setUser(userDao.get(userId));
		}else{
			teach.setUser(null);
		}
		// 给教师配置一个登陆账号  stop---- }
				
		// ---设置教师归属部门  start{
		if(departmentId !=null){
			for(Integer id : departmentId){
				teach.getDepartments().add(departmentDao.get(id));
			}
		}else{
			teach.setDepartments(new HashSet<Department>());
		}
		// 设置教师归属部门  stop----}
		
		// ---设置教师管理下的部门 start{
		if(managerDepartmentId !=null){
			for(Integer id : managerDepartmentId){
				Department department = departmentDao.get(id);
				System.out.println(teach.getName()+"新增的管理部门："+department.getName());
				department.setManager(teach);
				departmentDao.save(department);
			}
		}else{
			System.out.println(teach.getName()+"准备删除所有管理部门");
			System.out.println(teach.getName()+"有"+teach.getManagerDepartments().size()+"个管理部门");
			if(teach.getManagerDepartments().size() != 0){   		//hibernate设置控制权在部门表。所以要通过部门表来删除
				for(Department d : teach.getManagerDepartments()){
					System.out.println(teach.getName()+"删除管理部门："+d.getName());
					d.setManager(null);
					departmentDao.save(d);
				}
			}
		}
		// 设置教师管理下的部门 stop----}
		
		// ----设置教师可以发表文章的部门 start{
		if(articleDepartmentId !=null ){ 
			for(Integer id : articleDepartmentId){
				teach.getArticleDepartments().add(departmentDao.get(id));
			}
		}else{
			teach.setArticleDepartments(new HashSet<Department>());
		}
		// 设置教师可以发表文章的部门 stop----}
		
		teacherDao.save(teach); //保存teacher
	}
	public void mergeTeacher(Teacher teacher){
		teacherDao.merge(teacher);
	}
	public void deleteTeacherById(Integer id){
		Teacher t = teacherDao.get(id);
		// ---清除教师与已经发表文章的关系。
		for(Article a : t.getArticles()){
			a.setAuthor(null);
		}
		// ---清除教师与拥有发表文章权限的部门之间的关系。
		t.setArticleDepartments(new HashSet<Department>());
		// ---清除教师与归属部门的关系。
		t.setDepartments(new HashSet<Department>());
		// ---清除教师与管理下部门的关系。
		for(Department d : t.getManagerDepartments()){
			d.setManager(null);
			departmentDao.save(d);
		}
		teacherDao.save(t);
		teacherDao.remove(t);
		 System.out.println("已经执行删除语句");
	}
	/**
	 * 搜索教师
	 * @param search
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Teacher> searchTeachersByLike(String search){
		List<Teacher> teachers = new ArrayList<Teacher>();
		String hql = "from Teacher t where t.jgh like ? or  t.name like ? or t.sex like ? or cast(t.age as string) like ? or t.position like ? or t.phone like ? or t.description like ? ";
		search = "%"+search+"%";
		
		System.err.println("进入搜索Teacher表的方法searchTeachersByLike...");
		List<Teacher> dbTeachers = teacherDao.find(hql, search, search,search,search, search,search,search);
		System.err.println("dbTeachers.size"+dbTeachers.size());
		for(Teacher o: dbTeachers){
			System.out.println("查询结果，进入循环，构建JSON。");
			Teacher teacher = new Teacher(o.getJgh(), o.getName(), o.getSex(), o.getAge(), o.getPosition(), o.getPhone(), o.getDescription());
			teacher.setId(o.getId());
//			if(o.getEmployee() == null){
//				Employee e = o.getEmployee();
//				Employee employee = new Employee(e.getName(), e.getPassword(), e.getSex(), e.getPhone(), e.getAge(), e.getEmail(), e.getDescription());
//				employee.setId(e.getId());
//			}
			if(o.getSchool() != null){
				School s = o.getSchool();
				School school = new School(s.getName(), s.getType(), s.getDescription());
				school.setId(s.getId());
			}
			if(o.getUser() != null){
				User u = o.getUser();
				User user = new User(u.getName(), u.getXm(), u.getPassword(), u.getLocked(), u.getCredit());
				user.setId(u.getId());
			}
			if(o.getDepartments().size() != 0){
				Set<Department> departments = new HashSet<Department>();
				for(Department d: o.getDepartments()){
					Department department = new Department(d.getName(), d.getDescription(), d.getLevel());
					department.setId(d.getId());
					departments.add(department);
				}
				teacher.setDepartments(departments);
			}
			if(o.getManagerDepartments().size() != 0){
				Set<Department> departments = new HashSet<Department>();
				for(Department d: o.getManagerDepartments()){
					Department department = new Department(d.getName(), d.getDescription(), d.getLevel());
					department.setId(d.getId());
					departments.add(department);
				}
				teacher.setManagerDepartments(departments);
			}
			if(o.getArticleDepartments().size() != 0){
				Set<Department> departments = new HashSet<Department>();
				for(Department d: o.getArticleDepartments()){
					Department department = new Department(d.getName(), d.getDescription(), d.getLevel());
					department.setId(d.getId());
					departments.add(department);
				}
				teacher.setArticleDepartments(departments);
			}
			if(o.getArticles().size() != 0){
				Set<Article> articles = new HashSet<Article>();
				for(Article a : o.getArticles()){
					Article article = new Article(a.getTitle(), a.getType(), a.getTitleStyle(), a.isDisplay(), a.getContent(), a.getDate(), a.getIp(), a.getDescription());
					article.setId(a.getId());
					articles.add(a);
				}
				teacher.setArticles(articles);
			}
//			if(o.getZsbStudent().size() != 0){
//				Set<Student> students = new HashSet<Student>();
//				for(Student stu: o.getZsbStudent()){
//					Student student = new Student();
//					student.setName(stu.getName());
//					student.setLuQuZhuanYe(stu.getLuQuZhuanYe());
//					students.add(student);
//				}
//				teacher.setZsbStudent(students);
//			}
			teachers.add(teacher);
		}
		return teachers;
	}
}
