package com.xchgx.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xchgx.dao.ShortcutDao;
import com.xchgx.domain.Permission;
import com.xchgx.domain.Shortcut;

@Service
public class ShortcutService {

	@Autowired
	private ShortcutDao shortcutDao;
	
	public List<Shortcut> getAllShortcuts(){
		return shortcutDao.list();
	}
	public Shortcut getShortcutById(Integer id){
		return shortcutDao.get(id);
	}
	public void saveShortcut(Shortcut shortcut){
		shortcutDao.save(shortcut);
	}
	public void mergeShortcut(Shortcut shortcut){
		shortcutDao.merge(shortcut);
	}
	public void deleteShortcutById(Integer id){
		 Shortcut t = shortcutDao.get(id);
		 shortcutDao.remove(t);
	}
	/**
	 * 搜索桌面图标
	 * @param search
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Shortcut> searchShortcutsByLike(String search){
		List<Shortcut> shortcuts = new ArrayList<Shortcut>();
		String hql = "from Shortcut t where cast(t.icoid as string) like ? or  t.name like ? or t.ico like ? or t.url like ? or cast(t.height as string) like ? or cast(t.width as string) like ? or t.description like ? ";
		search = "%"+search+"%";
		
		System.err.println("进入搜索Shortcut表的方法searchShortcutsByLike...");
		List<Shortcut> dbShortcuts = shortcutDao.find(hql, search, search,search,search, search,search,search);
		System.err.println("dbShortcuts.size"+dbShortcuts.size());
		for(Shortcut o: dbShortcuts){
			System.out.println("查询结果，进入循环，构建JSON。");
			Shortcut shortcut = new Shortcut(o.getIcoid(), o.getName(), o.getIco(), o.getUrl(), o.getHeight(), o.getWidth(), o.getDescription());
			shortcut.setId(o.getId());
			if(o.getPermissions() != null){
				Set<Permission> permissions = new HashSet<Permission>();
				for(Permission p : o.getPermissions()){
					Permission permission = new Permission(p.getLevel(), p.getName(), p.getDescription());
					permission.setId(p.getId());
					permissions.add(p);
				}
				shortcut.setPermissions(permissions);
			}
			shortcuts.add(shortcut);
		}
		return shortcuts;
	}
}
