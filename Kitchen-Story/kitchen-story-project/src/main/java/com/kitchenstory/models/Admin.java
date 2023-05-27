package com.kitchenstory.models;

public class Admin {
	private int admin_id;
	private String email;
	private String full_name;
	private String password;

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Admin() {

	}

	public Admin(int admin_id, String email, String full_name, String password) {
		super();
		this.admin_id = admin_id;
		this.email = email;
		this.full_name = full_name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", email=" + email + ", full_name=" + full_name + ", password="
				+ password + "]";
	}

}
