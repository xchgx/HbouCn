package com.xchgx.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xchgx.dao.ArticleDao;
import com.xchgx.dao.UserDao;
import com.xchgx.domain.Article;
import com.xchgx.domain.DepartmentType;
import com.xchgx.domain.User;
import com.xchgx.zsbwork.bean.Department;
import com.xchgx.zsbwork.bean.School;
import com.xchgx.zsbwork.bean.Teacher;
import com.xchgx.zsbwork.dao.DepartmentDao;

@Service
public class DepartmentService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private ArticleDao articleDao;
	/**
	 * 获取所有部门
	 * @return
	 */
	public List<Department> list(){
		return departmentDao.list();
	}
	public List<Department> getAllDepartments(){
		return list();
	}
	public void save(Department department){
		departmentDao.save(department);
	}
	public void mergeDepartment(Department department){
		departmentDao.merge(department);
	}
	public Set<Article> getArticlesByDepartment(Integer departmentId){
		Department department = departmentDao.get(departmentId);
		return department.getArticles();
	}
	
	/**
	 * 遍历部门的所有子部门。
	 * @param id
	 * @return
	 */
	public List<Department> getSubDepartments(Integer id){
		Department department = departmentDao.get(id);
		List<Department> list = new ArrayList<Department>();
		Set<Department> set = new HashSet<Department>();
		subDepartments(department, list, set);
		list.remove(department);
		return list;
	}
	
	/**
	 * 递归遍历子部门算法。
	 * @param department
	 * @param list
	 * @param set
	 */
	public void subDepartments(Department department, List<Department> list, Set<Department> set){
		set.add(department);
		list.add(department);
		if(set == null || list == null || set.size() != list.size()){
			list = null;
			set = null;
			System.out.println("发现回路"+department.getName());
			return;
		}
		if(departmentDao.hasChildDepartment(department) && list != null && set != null){
			Set<Department> departmentSet = department.getChildDepartments();
			for(Department d : departmentSet){
				if(set == null || list == null || set.size() != list.size()){
					list = null;
					set = null;
					System.out.println("发现回路"+department.getName());
					return;
				}
				subDepartments(d, list, set);
			}
		}
		
	}
	/**
	 * 通过登陆用户查询该用户管理的部门集合
	 * @param user 登陆用户
	 * @return 主管的部门集合
	 */
	public Set<Department> getUserManagerDepartments(User user){
		return new TeacherService().getTeacherByUser(user).getManagerDepartments();
	}
	/**
	 * 通过登陆用户查询该用户所属的部门。
	 * @param user 登陆用户
	 * @return 所属部门。
	 */
	public Set<Department> getDepartmentsByUser(User user){
		User dbUser = userDao.load(user.getId());
		Teacher teacher = dbUser.getTeacher();
		return  teacher.getDepartments();
	}
	/**
	 * 通过登陆用户查询该用户管理的部门。
	 * @param user 登陆用户
	 * @return 所属部门。
	 */
	public Set<Department> getManagerDepartmentsByUser(User user){
		User dbUser = userDao.load(user.getId());
		Teacher teacher = dbUser.getTeacher();
		return  teacher.getManagerDepartments();
	}
	/**
	 * 通过登陆用户查询该用户有权限发表文章的部门。
	 * @param user 登陆用户
	 * @return 所属部门。
	 */
	public Set<Department> getArticleDepartmentsByUser(User user){
		User dbUser = userDao.load(user.getId());
		Teacher teacher = dbUser.getTeacher();
		return  teacher.getArticleDepartments();
	}
/**
 * 通过部门对象，计算当前部门所在的路径。
 * @param department
 * @return
 */
	public String getDepartmentPath(Department department){
		Department dbDepartment = departmentDao.get(department.getId());
		StringBuilder stringBuilder  = new StringBuilder();
		stringBuilder.append(dbDepartment.getName());
		Department father = dbDepartment.getFatherDepartment();
		while(father != null)
		{
			stringBuilder.insert(0, father.getName()+" > ");
			father = father.getFatherDepartment();
		}
		stringBuilder.insert(0,"首页  > ");
		return stringBuilder.toString();
	}
	/**
	 * 通过部门ID，计算当前部门所在的路径。
	 * @param departmentId
	 * @return
	 */
	public String getDepartmentPath(Integer departmentId){
		Department dbDepartment = departmentDao.get(departmentId);
		StringBuilder stringBuilder  = new StringBuilder();
		stringBuilder.append(dbDepartment.getName());
		Department father = dbDepartment.getFatherDepartment();
		while(father != null)
		{
			stringBuilder.insert(0, father.getName()+" > ");
			father = father.getFatherDepartment();
		}
		stringBuilder.insert(0,"首页  > ");
		return stringBuilder.toString();
	}
	/**
	 * 通过用户判断是否有访问部门的权限
	 * @param user
	 * @return
	 */
	public boolean isUserManagerDepartment(User user, Integer departmentId){
		User dbUser = userDao.get(user.getId());
		Teacher teacher = dbUser.getTeacher();
		Set<Department> departments = teacher.getArticleDepartments();
		for(Department d : departments){
			if(d.getId() == departmentId){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 通过ID获取Department对象
	 * @param id
	 * @return
	 */
	public Department getDepartmentById(Integer id){
		System.out.println("进入到departmentService服务层。。id: "+id);
		return departmentDao.get(id);
	}
	/**
	 * 通过Article ID获取Department对象
	 * @param id
	 * @return
	 */
	public Department getDepartmentByArticleId(Integer articleId){
		System.out.println("进入到departmentService服务层。。id: "+articleId);
		return articleDao.get(articleId).getDepartment();
	}
	/**
	 * 通过DepartmentType 部门类型ID获取Department对象
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getDepartmentByDepartmentTypeId(Integer id){
		System.out.println("进入到departmentService服务层。。id: "+id);
		return departmentDao.find("select new com.xchgx.zsbwork.bean.Department(d.id, d.name) from Department d where d.departmentType.id="+id);
	}
	/**
	 * 通过ID删除Department对象
	 * @param id
	 * @return
	 */
	public void deleteDepartmentById(Integer id){
		Department d = departmentDao.get(id);
		departmentDao.remove(d);
	}
	
	/**
	 * 搜索部门
	 * @param search
	 * @return
	 */
	
//	private   Set<Teacher> articleTeachers = new HashSet<Teacher>();//由发表文章权利的教职工
//	private   Set<Department> childDepartments  = new HashSet<Department>();//下级部门，下属部门
//	private   Set<Article> articles = new HashSet<Article>();
//	private   Set<Section> sections = new HashSet<Section>();
//	
//	
	/**
	 * 搜索部门
	 * @param search
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Department> searchDepartmentsByLike(String search){
		List<Department> departments = new ArrayList<Department>();
		String hql = "from Department t where t.name like ? or cast(t.level as string) like ? or t.description like ? ";
		search = "%"+search+"%";
		
		System.err.println("进入搜索Department表的方法searchDepartmentsByLike...");
		List<Department> dbDepartments = departmentDao.find(hql, search, search,search);
		System.err.println("dbDepartments.size"+dbDepartments.size());
		for(Department o: dbDepartments){
			System.out.println("查询结果，进入循环，构建JSON。");
			Department department = new Department(o.getName(), o.getDescription(), o.getLevel());
			department.setId(o.getId());
			if(o.getSchool() != null){
				School school = new School(o.getSchool().getName(), o.getSchool().getType(), o.getSchool().getDescription());
				school.setId(o.getSchool().getId());
				department.setSchool(school);
			}
			if(o.getDepartmentType() != null){
				DepartmentType departmentType = new DepartmentType(o.getDepartmentType().getName(), o.getDepartmentType().getDescription());
				departmentType.setId(o.getDepartmentType().getId());
				department.setDepartmentType(departmentType);
			}
			if(o.getFatherDepartment() != null){
				Department fatherDepartment = new Department(o.getFatherDepartment().getName(), o.getFatherDepartment().getDescription(), o.getFatherDepartment().getLevel());
				fatherDepartment.setId(o.getFatherDepartment().getId());
				department.setFatherDepartment(fatherDepartment);
			}
			if(o.getTeachers().size() > 0){
				Set<Teacher> teachers = new HashSet<Teacher>();
				for(Teacher t : o.getTeachers()){
					Teacher teacher = new Teacher(t.getJgh(), t.getName(), t.getSex(), t.getAge(), t.getPosition(), t.getPhone(), t.getDescription());
					teacher.setId(t.getId());
					teachers.add(teacher);
				}
				department.setTeachers(teachers);
			}
			
			departments.add(department);
		}
		return departments;
	}
	
	/**
	 * 所有部门ID
	 * @return
	 */
	public List<Integer> getAllDepartmentIds(){
		return departmentDao.getAllDepartmentIds();
	}
}

