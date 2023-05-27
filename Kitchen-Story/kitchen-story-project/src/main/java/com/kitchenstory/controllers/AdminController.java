package com.kitchenstory.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kitchenstory.models.Admin;
import com.kitchenstory.services.AdminService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value = "/api/admin/")
public class AdminController {
	public AdminController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private AdminService adminService;

	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@Autowired
	JdbcTemplate jdbc;

	@GetMapping("signin")
	public String signInAdmin(@RequestParam("admin_id") int admin_id, @RequestParam("password") String password) {
		String signInResponse = null;
		boolean userAuthenticated = adminService.signInAdmin(admin_id, password);
		if (userAuthenticated) {

			signInResponse = "User has been authenticated successfully..";
		} else {

			signInResponse = "Incorrect credentials..";
		}
		return signInResponse;

	}

	@PatchMapping("{admin_id}/update/password")
	public String changePassword(@PathVariable("admin_id") int admin_id, @RequestBody Admin admin) throws SQLException {
		System.out.println(admin.toString());
		Admin updatedAdmin = adminService.getAdminById(admin_id);
		updatedAdmin.setAdmin_id(admin_id);
		updatedAdmin.setPassword(admin.getPassword());
		updatedAdmin.getFull_name();
		updatedAdmin.getEmail();
		adminService.changePassword(updatedAdmin);
		return "Password has been changed.. ";
	}
}
