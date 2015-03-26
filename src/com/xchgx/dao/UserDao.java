package com.xchgx.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xchgx.domain.User;

@Repository
public class UserDao extends BaseDao<User>  {

	private final String GET_USER_BY_USERNAME = "from User u where u.name = ?";
	private final String QUERY_USER_BY_USERNAME = "from User u where u.name like ?";
	
	/**
	 * 根据用户名查询User对象
	 * @param name
	 * @return 对应name的User对象，如果不存在返回null
	 */
	@SuppressWarnings("unchecked")
	public User getUserByName(String name)
	{
		List<User> users = (List<User>) find(GET_USER_BY_USERNAME, name);
		if(users.size() == 0){
			return null;
		}else{
			return users.get(0);
		}
	}
	/**
	 * 加载所有对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> loadAll(){
		return (List<User>) find("from User");
	}

	/**
	 * 加载所有对象
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> list(){
		return (List<User>) find("from User");
	}
	/**
	 * 根据用户名为模糊查询条件，查询出所有前缀匹配的User对象
	 * @param name 用户名查询条件
	 * @return  用户名前缀匹配的所有User对象
	 */
	@SuppressWarnings("unchecked")
	public List<User> queryUserByName(String name){
		return (List<User>) find(QUERY_USER_BY_USERNAME, "%"+name+"%");
	}
}

