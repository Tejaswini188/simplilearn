package com.kitchenstory.daos;

import com.kitchenstory.models.Admin;

public interface AdminDao {
	boolean signInAdmin(int admin_id, String password);
	Admin getAdminById(int admin_id);
	int changePassword(Admin admin);
}
