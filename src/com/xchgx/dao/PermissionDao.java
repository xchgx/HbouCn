package com.xchgx.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xchgx.domain.Permission;

@Repository
public class PermissionDao extends BaseDao<Permission>{

	@SuppressWarnings("unchecked")
	public List<Permission> list(){
		return find("from Permission");
	}
	
	@SuppressWarnings("unchecked")
	public Permission findPermissionByLevel(Integer level){
		List<Permission> ps = find("from Permission p where p.level=?",level);
		if(ps.size() != 1){
			return null;
		}
		return ps.get(0);
	}
}
