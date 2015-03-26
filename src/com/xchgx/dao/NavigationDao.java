package com.xchgx.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xchgx.domain.Navigation;
@Repository
public class NavigationDao extends BaseDao<Navigation> {

	@SuppressWarnings("rawtypes")
	public List list(){
		return find("from Navigation order by sort");
	}
	@SuppressWarnings("rawtypes")
	public List listByLevel(Integer level, Integer departmentId){
		if(departmentId == 0){
			return find("from Navigation n where n.boss=0 and n.level=? order by n.sort", new Object[]{level});
		}else{
			return find("from Navigation n where n.level=? and n.boss=? order by n.sort", new Object[]{level, departmentId});
		}
		
	}
}
