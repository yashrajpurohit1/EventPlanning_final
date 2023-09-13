package in.co.hsbc.onlineEventPlanner.service.impl;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.PackageeDao;
import in.co.hsbc.onlineEventPlanner.model.Packagee;
import in.co.hsbc.onlineEventPlanner.service.PackageService;

public class PackageServiceImpl implements PackageService{
private PackageeDao packageDao;
public PackageServiceImpl(PackageeDao packageDao) {
	this.packageDao=packageDao;
}
@Override
public Packagee getPackageTypeById(int packageTypeId) {
	// TODO Auto-generated method stub
	Packagee packagee=packageDao.getPackageTypeById(packageTypeId);
	return packagee;
}
@Override
public int addPackage(Packagee packagee) {
	// TODO Auto-generated method stub
	int add=packageDao.addPackage(packagee);
	return add;
}
@Override
public List<Packagee> getAllPackageTypes() {
	// TODO Auto-generated method stub
	List<Packagee> packages=packageDao.getAllPackageTypes();
	return packages;
}
@Override
public void updatePackageType(Packagee packageType) {
	packageDao.updatePackageType(packageType);
	
}
@Override
public void deletePackageType(int id) {
	// TODO Auto-generated method stub
	packageDao.deletePackageType(id);
	
}
}
