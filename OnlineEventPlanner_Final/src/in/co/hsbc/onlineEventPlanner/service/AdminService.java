package in.co.hsbc.onlineEventPlanner.service;

import java.sql.SQLException;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.model.User;
import in.co.hsbc.onlineEventPlanner.model.Vendor;

public interface AdminService {
	int addVendor(Vendor v) throws SQLException;
	List<Vendor> getAllVendors() throws SQLException;
	List<User> getAllUserRegistrations() throws SQLException;
	boolean activateUserRegistration(int userId) throws SQLException;
}
