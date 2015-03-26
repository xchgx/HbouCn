package com.xchgx.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xchgx.domain.Shortcut;
@Repository
public class ShortcutDao  extends BaseDao<Shortcut>{

	/**
	 * 查询所有Shortcut数据 
	 * @return find("from Shortcut");
	 */
	@SuppressWarnings("unchecked")
	public List<Shortcut> list(){
		return find("from Shortcut order by icoid");
	}
}
