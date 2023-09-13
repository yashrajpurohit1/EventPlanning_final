package in.co.hsbc.onlineEventPlanner.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.ServiceDao;
import in.co.hsbc.onlineEventPlanner.model.Services;

public class ServiceDaoImpl implements ServiceDao {
	private Connection connection;

	public ServiceDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Services createService(Services service) {
		String insertQuery = "INSERT INTO services (name, cost) VALUES (?,?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
				PreparedStatement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setInt(1, service.getId());
			preparedStatement.setString(2, service.getName());
			preparedStatement.setDouble(3, service.getAmount());
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				service.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return service;
	}

	@Override
	public Services getServiceById(int id) {
		String selectQuery = "SELECT * FROM services WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return mapResultSetToService(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Services> getAllServices() {
		List<Services> services = new ArrayList<>();
		String selectQuery = "SELECT * FROM services";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				services.add(mapResultSetToService(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return services;
	}

	@Override
	public ArrayList<Services> getServicesByPackageTypeId(int packageTypeId) {
		ArrayList<Services> services = new ArrayList<>();
		String selectQuery = "SELECT s.* FROM services s "
				+ "INNER JOIN package_type_services pts ON s.id = pts.service_id " + "WHERE pts.package_type_id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			preparedStatement.setInt(1, packageTypeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				services.add(mapResultSetToService(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return services;
	}

	@Override
	public void updateService(Services service) {
		String updateQuery = "UPDATE services SET name = ?, cost = ? WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, service.getName());
			preparedStatement.setDouble(2, service.getAmount());
			preparedStatement.setInt(3, service.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteService(int id) {
		String deleteQuery = "DELETE FROM services WHERE id = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Services mapResultSetToService(ResultSet resultSet) throws SQLException {
		Services service = new Services();
		service.setId(resultSet.getInt("id"));
		service.setName(resultSet.getString("name"));
		service.setAmount(resultSet.getDouble("cost"));
		return service;
	}
}
