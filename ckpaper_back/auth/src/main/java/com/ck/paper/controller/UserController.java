package com.ck.paper.controller;

import com.alibaba.fastjson.JSONObject;
import com.ck.paper.util.CommonUtil;
import com.ck.paper.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: hxy
 * @description: 用户/角色/权限相关controller
 * @date: 2017/11/2 10:19
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;



	/**
	 * 根据用户id获取用户名
	 */
	@GetMapping("id")
	public String getUserName(@RequestParam(value = "id")String id) {
		String user = userService.getNickNameById(id);
		return user;
	}

	/**
	 * 获取当前登录用户信息
	 */
	@GetMapping("/currentUser")
	public JSONObject getCurrentUser(){
		return (JSONObject) SecurityUtils.getSubject().getPrincipal();
	}


	/**
	 * 查询用户列表
	 */
	@RequiresPermissions("user:list")
	@GetMapping("/list")
	public JSONObject listUser(HttpServletRequest request) {
		System.out.println("request"+request);
		return userService.listUser(CommonUtil.request2Json(request));
	}

	@RequiresPermissions("user:add")
	@PostMapping("/addUser")
	public JSONObject addUser(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "username, password, nickname,   roleId");
		return userService.addUser(requestJson);
	}

	@RequiresPermissions("user:update")
	@PostMapping("/updateUser")
	public JSONObject updateUser(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, " nickname,   roleId, deleteStatus, userId");
		return userService.updateUser(requestJson);
	}

	@RequiresPermissions(value = {"user:add", "user:update"}, logical = Logical.OR)
	@GetMapping("/getAllRoles")
	public JSONObject getAllRoles() {
		return userService.getAllRoles();
	}

	/**
	 * 角色列表
	 */
	@RequiresPermissions("role:list")
	@GetMapping("/listRole")
	public JSONObject listRole() {
		return userService.listRole();
	}

	/**
	 * 查询所有权限, 给角色分配权限时调用
	 */
	@RequiresPermissions("role:list")
	@GetMapping("/listAllPermission")
	public JSONObject listAllPermission() {
		return userService.listAllPermission();
	}

	/**
	 * 新增角色
	 */
	@RequiresPermissions("role:add")
	@PostMapping("/addRole")
	public JSONObject addRole(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "roleName,permissions");
		return userService.addRole(requestJson);
	}

	/**
	 * 修改角色
	 */
	@RequiresPermissions("role:update")
	@PostMapping("/updateRole")
	public JSONObject updateRole(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "roleId,roleName,permissions");
		return userService.updateRole(requestJson);
	}

	/**
	 * 删除角色
	 */
	@RequiresPermissions("role:delete")
	@PostMapping("/deleteRole")
	public JSONObject deleteRole(@RequestBody JSONObject requestJson) {
		CommonUtil.hasAllRequired(requestJson, "roleId");
		return userService.deleteRole(requestJson);
	}
}
