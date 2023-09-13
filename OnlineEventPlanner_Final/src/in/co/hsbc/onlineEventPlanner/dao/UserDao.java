package in.co.hsbc.onlineEventPlanner.dao;

import java.sql.SQLException;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.model.PlanRequest;
import in.co.hsbc.onlineEventPlanner.model.Quotation;
import in.co.hsbc.onlineEventPlanner.model.User;

public interface UserDao {
User viewProfile(int userId) throws SQLException;
boolean updateUser(int UserId,String emial,String mobileNum,String address) throws SQLException;
boolean changePassword(int userId, String newPassword) throws SQLException;
boolean sendPlanRequest(PlanRequest planRequest,int vendorId) throws SQLException;
List<Quotation> getAllQuotations(int userId) throws SQLException;
boolean updateQuotationStatus(int quotationId, String status) throws SQLException;
}
