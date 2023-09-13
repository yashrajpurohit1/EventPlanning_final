package in.co.hsbc.onlineEventPlanner.service.impl;

import java.util.ArrayList;
import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.ServiceDao;
import in.co.hsbc.onlineEventPlanner.model.Services;
import in.co.hsbc.onlineEventPlanner.service.ServiceService;

public class ServiceServiceImpl implements ServiceService{
private ServiceDao serviceDao;
public ServiceServiceImpl(ServiceDao serviceDao) {
	this.serviceDao=serviceDao;
}
@Override
public Services createService(Services service) {
	// TODO Auto-generated method stub
	Services services=serviceDao.createService(service);
	return services;
}
@Override
public Services getServiceById(int id) {
	// TODO Auto-generated method stub
	Services serviceById=serviceDao.getServiceById(id);
	return serviceById;
}
@Override
public List<Services> getAllServices() {
	// TODO Auto-generated method stub
	List<Services> services=serviceDao.getAllServices();
			
	return services;
}
@Override
public ArrayList<Services> getServicesByPackageTypeId(int packageTypeId) {
	// TODO Auto-generated method stub
	 ArrayList<Services> services=serviceDao.getServicesByPackageTypeId(packageTypeId)
			 ;
	return services;
}
@Override
public void updateService(Services service) {
	// TODO Auto-generated method stub
	serviceDao.updateService(service);
	
}
@Override
public void deleteService(int id) {
	// TODO Auto-generated method stub
	serviceDao.deleteService(id);
	
}
}
