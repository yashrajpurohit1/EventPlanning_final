package in.co.hsbc.onlineEventPlanner.service;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.model.Packagee;

public interface PackageService {

	Packagee getPackageTypeById(int packageTypeId);

	int addPackage(Packagee packagee);

	List<Packagee> getAllPackageTypes();

	void updatePackageType(Packagee packageType);

	void deletePackageType(int id);
}
