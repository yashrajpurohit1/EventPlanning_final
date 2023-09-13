package in.co.hsbc.onlineEventPlanner.dao;

import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.model.Services;

public interface ServiceDao {
	Services createService(Services service);

	Services getServiceById(int id);

	List<Services> getAllServices();

	ArrayList<Services> getServicesByPackageTypeId(int packageTypeId);

	void updateService(Services service);

	void deleteService(int id);
}
