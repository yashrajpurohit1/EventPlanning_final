package in.co.hsbc.onlineEventPlanner.service;

import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.model.Services;

public interface ServiceService {
	Services createService(Services service);

	Services getServiceById(int id);

	List<Services> getAllServices();

	ArrayList<Services> getServicesByPackageTypeId(int packageTypeId);

	void updateService(Services service);

	void deleteService(int id);
}
