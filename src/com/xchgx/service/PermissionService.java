package com.xchgx.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xchgx.dao.PermissionDao;
import com.xchgx.domain.Permission;
import com.xchgx.domain.Shortcut;

@Service
public class PermissionService {

	@Autowired
	private PermissionDao permissionDao;
	
	public List<Permission> getAllPermissions(){
		return permissionDao.list();
	}
	public Permission getPermissionById(Integer id){
		return permissionDao.get(id);
	}
	public void savePermission(Permission permission){
		permissionDao.save(permission);
	}
	public void mergePermission(Permission permission){
		permissionDao.merge(permission);
	}
	public void deletePermissionById(Integer id){
		 Permission t = permissionDao.get(id);
		 permissionDao.remove(t);
	}
	/**
	 * 搜索权限
	 * @param search
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Permission> searchPermissionsByLike(String search){
		List<Permission> permissions = new ArrayList<Permission>();
		String hql = "from Permission t where t.name like ? or cast(t.level as string) like ? or t.description like ? ";
		search = "%"+search+"%";
		
		System.err.println("进入搜索Permission表的方法searchPermissionsByLike...");
		List<Permission> dbPermissions = permissionDao.find(hql, search, search,search);
		System.err.println("dbPermissions.size"+dbPermissions.size());
		for(Permission o: dbPermissions){
			System.out.println("查询结果，进入循环，构建JSON。");
			Permission permission = new Permission(o.getLevel(), o.getName(), o.getDescription());
			permission.setId(o.getId());
			if(o.getShortcuts().size() > 0){
				Set<Shortcut> ss = new HashSet<Shortcut>();
				for(Shortcut s: o.getShortcuts()){
					Shortcut shortcut = new Shortcut(s.getIcoid(), s.getName(), s.getIco(), s.getUrl(), s.getHeight(), s.getWidth(), s.getDescription());
					shortcut.setId(s.getId());
					ss.add(shortcut);					
				}
				permission.setShortcuts(ss);
			}
			permissions.add(permission);
		}
		return permissions;
	}
}
