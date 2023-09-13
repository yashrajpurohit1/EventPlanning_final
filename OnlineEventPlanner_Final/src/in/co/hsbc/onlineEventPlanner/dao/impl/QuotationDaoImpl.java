package in.co.hsbc.onlineEventPlanner.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.PackageeDao;
import in.co.hsbc.onlineEventPlanner.dao.QuotationDao;
import in.co.hsbc.onlineEventPlanner.model.Packagee;
import in.co.hsbc.onlineEventPlanner.model.Quotation;

public class QuotationDaoImpl implements QuotationDao {
	private Connection connection;

	public QuotationDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Quotation createQuotation(Quotation quotation) {
		String insertQuery = "INSERT INTO quotations (package_type_id, estimated_amount, plan_request_id, user_id, vendor_id, user_status, admin_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setInt(1, quotation.getPackageType().getId());
			preparedStatement.setDouble(2, quotation.getEstimatedAmount());
			preparedStatement.setInt(3, quotation.getPlanRequestId());
			preparedStatement.setInt(4, quotation.getUserId());
			preparedStatement.setInt(5, quotation.getVendorId());
			preparedStatement.setString(6, quotation.getUserStatus());
			preparedStatement.setString(7, quotation.getAdminStatus());
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				quotation.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quotation;
	}

	@Override
	public Quotation getQuotationById(int id) {
		String selectQuery = "SELECT * FROM quotations WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return mapResultSetToQuotation(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Quotation> getAllQuotations() {
		List<Quotation> quotations = new ArrayList<>();
		String selectQuery = "SELECT * FROM quotations";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				quotations.add(mapResultSetToQuotation(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quotations;
	}

	@Override
	public void updateQuotation(Quotation quotation) {
		// TODO Auto-generated method stub
		String updateQuery = "UPDATE quotations SET package_type_id = ?, estimated_amount = ?, plan_request_id = ?, user_id = ?, vendor_id = ?, user_status = ?, admin_status = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setInt(1, quotation.getPackageType().getId());
			preparedStatement.setDouble(2, quotation.getEstimatedAmount());
			preparedStatement.setInt(3, quotation.getPlanRequestId());
			preparedStatement.setInt(4, quotation.getUserId());
			preparedStatement.setInt(5, quotation.getVendorId());
			preparedStatement.setString(6, quotation.getUserStatus());
			preparedStatement.setString(7, quotation.getAdminStatus());
			preparedStatement.setInt(8, quotation.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteQuotation(int id) {
		// TODO Auto-generated method stub
		String deleteQuery = "DELETE FROM quotations WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateQuotationStatus(int id, String newStatus) {
		// TODO Auto-generated method stub
		String updateStatusQuery = "UPDATE quotations SET user_status = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateStatusQuery)) {
			preparedStatement.setString(1, newStatus);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Helper method to map ResultSet to Quotation object
	private Quotation mapResultSetToQuotation(ResultSet resultSet) throws SQLException {
		Quotation quotation = new Quotation();
		quotation.setId(resultSet.getInt("id"));
		quotation.setEstimatedAmount(resultSet.getDouble("estimated_amount"));
		quotation.setPlanRequestId(resultSet.getInt("plan_request_id"));
		quotation.setUserId(resultSet.getInt("user_id"));
		quotation.setVendorId(resultSet.getInt("vendor_id"));
		quotation.setUserStatus(resultSet.getString("user_status"));
		quotation.setAdminStatus(resultSet.getString("admin_status"));

		// Assuming you have a PackageTypeDAO and a method to get PackageType by ID
		int packageTypeId = resultSet.getInt("package_type_id");
		PackageeDao packageDao = new PackageeDaoImpl(connection); // You should inject the DAO instead of
		// creating it here.
		Packagee packageType = packageDao.getPackageTypeById(packageTypeId);
		quotation.setPackageType(packageType);

		return quotation;
		
	}

}
