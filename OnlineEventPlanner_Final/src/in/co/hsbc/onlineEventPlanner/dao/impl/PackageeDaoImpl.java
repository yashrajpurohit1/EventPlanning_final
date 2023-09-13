package in.co.hsbc.onlineEventPlanner.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.PackageeDao;
import in.co.hsbc.onlineEventPlanner.dao.ServiceDao;
import in.co.hsbc.onlineEventPlanner.model.Packagee;
import in.co.hsbc.onlineEventPlanner.model.Services;

public class PackageeDaoImpl implements PackageeDao {

	private Connection connection;

	public PackageeDaoImpl(Connection connection) {
		// TODO Auto-generated constructor stub
		this.connection = connection;
	}

	@Override
	public Packagee getPackageTypeById(int packageTypeId) {
		// TODO Auto-generated method stub
		String selectQuery = "SELECT * FROM package_types WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			preparedStatement.setInt(1, packageTypeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return mapResultSetToPackageType(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public int addPackage(Packagee packagee) {
		String insertQuery = "INSERT INTO package_types (type, package_type, amount) VALUES (?, ?, ?)";
		int generatedId = -1; // Initialize with a default value
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setString(1, packagee.getPackageType());
			preparedStatement.setString(2, packagee.getPackageType());
			preparedStatement.setDouble(3, packagee.getAmount());
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				generatedId = generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return generatedId;
	}

	@Override
	public List<Packagee> getAllPackageTypes() {
		List<Packagee> packageTypes = new ArrayList<>();
		String selectQuery = "SELECT * FROM package_types";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				packageTypes.add(mapResultSetToPackageType(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return packageTypes;

	}

	@Override
	public void updatePackageType(Packagee packageType) {
		// TODO Auto-generated method stub
		String updateQuery = "UPDATE package_types SET type = ?, package_type = ?, amount = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, packageType.getPackageType());
			preparedStatement.setString(2, packageType.getPackageType());
			preparedStatement.setDouble(3, packageType.getAmount());
			preparedStatement.setInt(4, packageType.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deletePackageType(int id) {
		// TODO Auto-generated method stub
		String deleteQuery = "DELETE FROM package_types WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private Packagee mapResultSetToPackageType(ResultSet resultSet) throws SQLException {
		Packagee packageType = new Packagee();
		packageType.setId(resultSet.getInt("id"));
		packageType.setPackageType(resultSet.getString("type"));
		packageType.setPackageType(resultSet.getString("package_type"));
		packageType.setAmount(resultSet.getDouble("amount"));

		ServiceDao serviceDAO = new ServiceDaoImpl(connection);
		ArrayList<Services> services = serviceDAO.getServicesByPackageTypeId(packageType.getId());
		packageType.setServices(services);

		return packageType;
	}

	}
