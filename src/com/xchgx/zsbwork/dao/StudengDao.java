package com.xchgx.zsbwork.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xchgx.dao.BaseDao;
import com.xchgx.zsbwork.bean.Student;

@Repository
public class StudengDao extends BaseDao<Student> {

	@SuppressWarnings("unchecked")
	public List<Student> list(){
		return find("from Student");
	}
}
