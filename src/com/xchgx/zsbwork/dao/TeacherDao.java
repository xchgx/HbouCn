package com.xchgx.zsbwork.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xchgx.dao.BaseDao;
import com.xchgx.zsbwork.bean.Teacher;

@Repository
public class TeacherDao extends BaseDao<Teacher>{
	@SuppressWarnings("unchecked")
	public List<Teacher> list(){
		return find("from Teacher");
	}
	
	/**
	 * 通过教师的教工号查找Teacher对象。
	 * @param jgh 教工号
	 * @return Teacher
	 */
	@SuppressWarnings("unchecked")
	public Teacher getTeacherByJgh(String jgh){
		//private final String GET_USER_BY_USERNAME = "from User u where u.name = ?";
		List<Teacher> teacher = (List<Teacher>) find("from Teacher t where t.jgh=?", jgh);
		if(teacher.size() != 1){
			return null;
		}else{
			return teacher.get(0);
		}
	}
}
