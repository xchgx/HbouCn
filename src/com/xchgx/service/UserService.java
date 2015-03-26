package com.xchgx.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xchgx.dao.LoginLogDao;
import com.xchgx.dao.UserDao;
import com.xchgx.domain.LoginLog;
import com.xchgx.domain.Permission;
import com.xchgx.domain.Shortcut;
import com.xchgx.domain.User;
import com.xchgx.exception.UserExistException;
import com.xchgx.zsbwork.bean.Teacher;

/**
 * 用户管理服务器，负责查询用户、注册用户、锁定用户等操作
 *
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private LoginLogDao loginLogDao;

	public void save(User user ){
		userDao.save(user);
	}

	public void merge(User user ){
		userDao.merge(user);
	}
	/**
	 * 注册一个新用户,如果用户名已经存在此抛出UserExistException的异常
	 * 
	 * @param user
	 */
	public void register(User user) throws UserExistException {
		User u = this.getUserByName(user.getName());
		if (u != null) {
			throw new UserExistException("用户名已经存在");
		} else {
			userDao.save(user);
		}
	}

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	public void update(User user) {
		userDao.update(user);
	}

	/**
	 * 根据用户名/密码查询 User对象
	 * 
	 * @param name
	 *            用户名
	 * @return User
	 */
	public User getUserByName(String name) {
		return userDao.getUserByName(name);
	}

	/**
	 * 根据userId加载User对象
	 * 
	 * @param userId
	 * @return
	 */
	public User getUserById(int userId) {
		return userDao.get(userId);
	}

	/**
	 * 将用户锁定，锁定的用户不能够登录
	 * 
	 * @param userName
	 *            锁定目标用户的用户名
	 */
	public void lockUser(String userName) {
		User user = userDao.getUserByName(userName);
		user.setLocked(User.USER_LOCK);
		userDao.update(user);
	}

	/**
	 * 解除用户的锁定
	 * 
	 * @param userName
	 *            解除锁定目标用户的用户名
	 */
	public void unlockUser(String userName) {
		User user = userDao.getUserByName(userName);
		user.setLocked(User.USER_UNLOCK);
		userDao.update(user);
	}

	/**
	 * 根据用户名为条件，执行模糊查询操作
	 * 
	 * @param userName
	 *            查询用户名
	 * @return 所有用户名前导匹配的userName的用户
	 */
	public List<User> queryUserByName(String userName) {
		return userDao.queryUserByName(userName);
	}

	/**
	 * 获取所有用户
	 * 
	 * @return 所有用户
	 */
	public List<User> getAllUsers() {
		return userDao.loadAll();
	}
	/**
	 * 获取所有用户
	 * 
	 * @return 所有用户
	 */
	@SuppressWarnings("unchecked")
	public List<User> getNoTeacherUsers() {
		String hql = "from User u where u.teacher is NULL";
		return userDao.find(hql);
	}
	/**
	 * 登陆成功
	 * 
	 * @param user
	 */
	public void loginSuccess(User user, LoginLog ll) {
		user.setCredit(5 + user.getCredit());
		userDao.update(user);
		loginLogDao.save(ll);
	}

	/**
	 * 根据用户权限初始化桌面图标。
	 */
	public boolean initShortcut(User user, HttpServletRequest request) {
		try {
			File file = getShortcutJsFile(request);
			BufferedWriter out = new BufferedWriter(new FileWriter(file, false));
			Permission permission = user.getPermission();
			if (permission == null) {
				out.write("var shortcut='';");
				out.close();
				return false;
			}
			Set<Shortcut> shortcuts = permission.getShortcuts();
			if (shortcuts.size() == 0) {
				out.write("var shortcut='';");
				out.close();
				return false;
			} else {
				int i = 0;
				for (Shortcut s : shortcuts) {
					System.out.println(s.getId());
					System.out.println(s.getName());
					System.out.println(s.getIco());
					System.out.println(s.getUrl());
					System.out.println(s.getHeight());
					System.out.println(s.getIcoid());
					System.out.println(s.getWidth());
					String start = "var shortcut = [[";
					String start2 = "[";
					String sep = ",";
					String pause = "],";
					String end = "]];";
					String str = "\"";
					StringBuilder stringBuilder = new StringBuilder();
					if (i == 0) {
						stringBuilder.append(start);
					} else {
						stringBuilder.append(start2);
					}
					stringBuilder.append(i);
					stringBuilder.append(sep);

					stringBuilder.append(str);
					stringBuilder.append(s.getName());
					stringBuilder.append(str);
					stringBuilder.append(sep);

					stringBuilder.append(str);
					stringBuilder.append(s.getIco());
					stringBuilder.append(str);
					stringBuilder.append(sep);

					stringBuilder.append(str);
					stringBuilder.append(s.getUrl());
					stringBuilder.append(str);
					stringBuilder.append(sep);

					stringBuilder.append(s.getWidth());
					stringBuilder.append(sep);

					stringBuilder.append(s.getHeight());

					if (i == shortcuts.size() - 1) {
						stringBuilder.append(end);
					} else {
						stringBuilder.append(pause);
					}
					i++;

					out.write(stringBuilder.toString());
					out.newLine();
				}
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	private File getShortcutJsFile(HttpServletRequest request) {
		String path = request.getSession().getServletContext()
				.getRealPath("/manager/js/shortcut.js");
		return new File(path);
	}

	public String getInitShortcut(User user, HttpServletRequest request) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("");
		Permission permission = user.getPermission();
		if (permission == null) {
			return stringBuilder.toString();
		}
		Set<Shortcut> shortcuts = permission.getShortcuts();
		if (shortcuts.size() == 0) {
			return stringBuilder.toString();
		} else {
			int i = 0;
			for (Shortcut s : shortcuts) {
				String start = "<script type=\"text/javascript\">var shortcut = [[";
				String start2 = "[";
				String sep = ",";
				String pause = "],";
				String str = "\"";
				String end = "]];</script>";
				if (i == 0) {
					stringBuilder.append(start);
				} else {
					stringBuilder.append(start2);
				}
				stringBuilder.append(i);
				stringBuilder.append(sep);

				stringBuilder.append(str);
				stringBuilder.append(s.getName());
				stringBuilder.append(str);
				stringBuilder.append(sep);

				stringBuilder.append(str);
				stringBuilder.append(s.getIco());
				stringBuilder.append(str);
				stringBuilder.append(sep);

				stringBuilder.append(str);
				stringBuilder.append(s.getUrl());
				stringBuilder.append(str);
				stringBuilder.append(sep);

				stringBuilder.append(s.getWidth());
				stringBuilder.append(sep);

				stringBuilder.append(s.getHeight());

				if (i == shortcuts.size() - 1) {
					stringBuilder.append(end);
				} else {
					stringBuilder.append(pause);
				}
				i++;
			}
		}
		return stringBuilder.toString();
	}
	/**
	 * 删除用户
	 * @param id
	 */
	public void deleteUserById(Integer id){
		User user = userDao.get(id);
		userDao.remove(user);
	}
	/**
	 * 搜索用户
	 * @param search
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> searchUsersByLike(String search){
		List<User> users = new ArrayList<User>();
		String hql = "from User u where u.name like ? or u.xm like ? or cast(u.credit as string) like ?";
		search = "%"+search+"%";
		System.err.println("进入搜索User表的方法searchUsersByLike...");
		List<User> dbUsers = userDao.find(hql, search, search,search);
		System.err.println("dbUsers.size"+dbUsers.size());
		for(User u: dbUsers){
			System.out.println("查询结果，进入循环，构建JSON。");
			User user = new User(u.getName(), u.getXm(), u.getPassword(), u.getLocked(), u.getCredit());
			user.setId(u.getId());
			System.out.println("创建了User");
			if(u.getPermission() != null){
				Permission permission = new Permission(u.getPermission().getLevel(), u.getPermission().getName(), u.getPermission().getDescription());
				permission.setId(u.getPermission().getId());
				user.setPermission(permission);
				System.out.println("u.getPermission()权限Permission不为空，User配置权限Permission");
			}
			if(u.getTeacher() != null){
				Teacher teacher = new Teacher(u.getTeacher().getJgh(), u.getTeacher().getName(), u.getTeacher().getSex(), u.getTeacher().getAge(), u.getTeacher().getPosition(), u.getTeacher().getPhone(), u.getTeacher().getDescription());
				teacher.setId(u.getTeacher().getId());
				user.setTeacher(teacher);
				System.out.println("User配置了Teacher教师");
			}
			users.add(user);
			System.out.println("user加入到List users.add中");
		}
		System.err.println("拼装List users个数:"+users.size());
		return users;
	}
}
