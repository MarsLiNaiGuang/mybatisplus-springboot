package com.baomidou.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springboot.entity.User;
import com.baomidou.springboot.service.IUserService;

/**
 * 
 * 代码生成器，参考源码测试用例：
 * 
 * /mybatis-plus/src/test/java/com/baomidou/mybatisplus/test/generator/MysqlGenerator.java
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	/**
	 * AR 部分测试
	 */
	@RequestMapping("/hello")
	public String index() {
		return "hello";
	}
	
	@RequestMapping("/test")
	public Page<User> test() {
		User user = new User("testAr", 0, 1);
		System.err.println("删除所有：" + user.delete(null));
		user.insert();
		System.err.println("查询插入结果：" + user.selectById().toString());
		user.setName("mybatis-plus-ar");
		System.err.println("更新：" + user.updateById());		
		return user.selectPage(new Page<User>(0, 12), null);
	}

	/**
	 * 增删改查 CRUD
	 */
	@RequestMapping("/test1")
	public User test1() {
		System.err.println("删除一条数据：" + userService.deleteById(1L));
		System.err.println("deleteAll：" + userService.deleteAll());
		System.err.println("插入一条数据：" + userService.insert(new User(1L, "张三", 17, 1)));
		User user = new User("张三", 17, 1);
		boolean result = userService.insert(user);
		// 自动回写的ID
		Long id = user.getId();
		System.err.println("插入一条数据：" + result + ", 插入信息：" + user.toString());
		System.err.println("查询：" + userService.selectById(id).toString());
		System.err.println("更新一条数据：" + userService.updateById(new User(1L, "三毛", 18, 2)));
		for(int i=0;i<5;++i){
			userService.insert(new User(Long.valueOf(100+i), "张三"+i, 17+i, 1));
		}
		Page<User> userListPage = userService.selectPage(new Page<User>(1,5), new EntityWrapper<>(new User()));
		System.err.println("total="+userListPage.getTotal()+", current list size="+userListPage.getRecords().size());
		return userService.selectById(1L);
	}

	/**
	 * 插入 OR 修改
	 */
	@RequestMapping("/test2")
	public User test2() {
		userService.insertOrUpdate(new User(1L, "王五", 19, 3));
		return userService.selectById(1L);
	}

	/**
	 * 分页 PAGE
	 */
	@RequestMapping("/test3")
	public Page<User> test3() {
		return userService.selectPage(new Page<User>(0, 12));
	}

}
