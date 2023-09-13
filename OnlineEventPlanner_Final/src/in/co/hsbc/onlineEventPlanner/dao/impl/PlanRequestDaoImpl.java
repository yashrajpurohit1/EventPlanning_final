package in.co.hsbc.onlineEventPlanner.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.PlanRequestDao;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;

public class PlanRequestDaoImpl implements PlanRequestDao{

	    private Connection connection;

	    public PlanRequestDaoImpl(Connection connection) {
	        this.connection = connection;
	    }

	    @Override
	    public PlanRequest createPlanRequest(PlanRequest planRequest) {
	        String insertQuery = "INSERT INTO plan_requests (from_date, to_date, number_of_persons) VALUES (?, ?, ?)";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
	            preparedStatement.setDate(1, new java.sql.Date(planRequest.getFromDate().getTime()));
	            preparedStatement.setDate(2, new java.sql.Date(planRequest.getToDate().getTime()));
	            preparedStatement.setInt(3, planRequest.getNumOfAttendent());
	            preparedStatement.executeUpdate();
	            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                planRequest.setPlanRequestId(generatedKeys.getInt(1));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return planRequest;
	    }

	    @Override
	    public PlanRequest getPlanRequestById(int id) {
	        String selectQuery = "SELECT * FROM plan_requests WHERE request_id = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
	            preparedStatement.setInt(1, id);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                return mapResultSetToPlanRequest(resultSet);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    @Override
	    public List<PlanRequest> getAllPlanRequests() {
	        List<PlanRequest> planRequests = new ArrayList<>();
	        String selectQuery = "SELECT * FROM plan_requests";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
	            ResultSet resultSet = preparedStatement.executeQuery();
	            while (resultSet.next()) {
	                planRequests.add(mapResultSetToPlanRequest(resultSet));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return planRequests;
	    }

	    @Override
	    public void updatePlanRequest(PlanRequest planRequest) {
	        String updateQuery = "UPDATE plan_requests SET from_date = ?, to_date = ?, number_of_persons = ? WHERE request_id = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
	            preparedStatement.setDate(1, new java.sql.Date(planRequest.getFromDate().getTime()));
	            preparedStatement.setDate(2, new java.sql.Date(planRequest.getToDate().getTime()));
	            preparedStatement.setInt(3, planRequest.getNumOfAttendent());
	            preparedStatement.setInt(4, planRequest.getPlanRequestId());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void deletePlanRequest(int id) {
	        String deleteQuery = "DELETE FROM plan_requests WHERE request_id = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
	            preparedStatement.setInt(1, id);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private PlanRequest mapResultSetToPlanRequest(ResultSet resultSet) throws SQLException {
	        PlanRequest planRequest = new PlanRequest();
	        planRequest.setPlanRequestId(resultSet.getInt("request_id"));
	        planRequest.setFromDate(resultSet.getDate("from_date"));
	        planRequest.setToDate(resultSet.getDate("to_date"));
	        planRequest.setNumOfAttendent(resultSet.getInt("number_of_persons"));
	        // You may add logic here to fetch additional information if needed
	        return planRequest;
	    }

	    @Override
	    public boolean associatePlanRequestWithVendor(int planRequestId, int vendorId) {
	        String insertAssociationQuery = "INSERT INTO plan_request_vendor_association (plan_request_id, vendor_id) VALUES (?, ?)";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(insertAssociationQuery)) {
	            preparedStatement.setInt(1, planRequestId);
	            preparedStatement.setInt(2, vendorId);
	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	}

