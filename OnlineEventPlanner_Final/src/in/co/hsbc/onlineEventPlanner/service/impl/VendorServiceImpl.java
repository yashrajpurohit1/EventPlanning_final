package in.co.hsbc.onlineEventPlanner.service.impl;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.VendorDao;
import in.co.hsbc.onlineEventPlanner.model.Packagee;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;
import in.co.hsbc.onlineEventPlanner.model.Quotation;
import in.co.hsbc.onlineEventPlanner.service.VendorService;

public class VendorServiceImpl implements VendorService{
private VendorDao vendorDao;
public VendorServiceImpl(VendorDao vendorDao) {
	this.vendorDao=vendorDao;
}
@Override
public boolean addPackage(Packagee vendorPackage) {
	// TODO Auto-generated method stub
	boolean packages=vendorDao.addPackage(vendorPackage);
	return packages;
}
@Override
public List<PlanRequest> getAllUserPlanRequests() {
	// TODO Auto-generated method stub
	List<PlanRequest>  planRequest=vendorDao.getAllUserPlanRequests()
			;
	return planRequest;
}
@Override
public Quotation createQuotation(Quotation quotation) {
	// TODO Auto-generated method stub
	Quotation quotations= vendorDao.createQuotation(quotation);
	return quotations;
}
}
