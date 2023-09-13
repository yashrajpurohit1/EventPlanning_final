package in.co.hsbc.onlineEventPlanner.service.impl;

import java.sql.SQLException;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.UserDao;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;
import in.co.hsbc.onlineEventPlanner.model.Quotation;
import in.co.hsbc.onlineEventPlanner.model.User;
import in.co.hsbc.onlineEventPlanner.service.UserService;

public class UserServiceImpl implements UserService{
private UserDao userDao;
public UserServiceImpl(UserDao userDao) {
	this.userDao=userDao;
}
@Override
public User viewProfile(int userId) throws SQLException {
	User user=userDao.viewProfile(userId);
	return user;
}
@Override
public boolean updateUser(int UserId, String emial, String mobileNum, String address) throws SQLException {
	boolean updated=userDao.updateUser(UserId, emial, mobileNum, address);
	return updated;
}
@Override
public boolean changePassword(int userId, String newPassword) throws SQLException {
	boolean passWordChanged=userDao.changePassword(userId, newPassword);
	return passWordChanged;
}
@Override
public boolean sendPlanRequest(PlanRequest planRequest, int vendorId) throws SQLException {
	boolean planRequestSend=userDao.sendPlanRequest(planRequest, vendorId);
	return planRequestSend;
}
@Override
public List<Quotation> getAllQuotations(int userId) throws SQLException {
	 List<Quotation> quotations=userDao.getAllQuotations(userId);
	return quotations;
}
@Override
public boolean updateQuotationStatus(int quotationId, String status) throws SQLException {
	boolean statuss=userDao.updateQuotationStatus(quotationId, status);
	return statuss;
}
}
