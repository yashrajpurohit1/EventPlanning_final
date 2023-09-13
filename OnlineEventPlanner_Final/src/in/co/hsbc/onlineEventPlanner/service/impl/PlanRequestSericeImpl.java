package in.co.hsbc.onlineEventPlanner.service.impl;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.PlanRequestDao;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;
import in.co.hsbc.onlineEventPlanner.service.PlanRequestService;

public class PlanRequestSericeImpl implements PlanRequestService{
private PlanRequestDao planRequestDao;
public PlanRequestSericeImpl(PlanRequestDao planRequestDao) {
this.planRequestDao=planRequestDao;	
}
@Override
public PlanRequest createPlanRequest(PlanRequest planRequest) {
	// TODO Auto-generated method stub
	PlanRequest planRequests=planRequestDao.createPlanRequest(planRequest);
	return planRequests;
}
@Override
public PlanRequest getPlanRequestById(int id) {
	// TODO Auto-generated method stub
	PlanRequest planreq=planRequestDao.getPlanRequestById(id);
	return planreq;
}
@Override
public List<PlanRequest> getAllPlanRequests() {
	// TODO Auto-generated method stub
	List<PlanRequest> plans=planRequestDao.getAllPlanRequests();
			
	return plans;
}
@Override
public void updatePlanRequest(PlanRequest planRequest) {
	// TODO Auto-generated method stub
	planRequestDao.updatePlanRequest(planRequest);
	
	
}
@Override
public void deletePlanRequest(int id) {
	// TODO Auto-generated method stub
	planRequestDao.deletePlanRequest(id);
	
}
@Override
public boolean associatePlanRequestWithVendor(int planRequestId, int vendorId) {
	// TODO Auto-generated method stub
	boolean associated=planRequestDao.associatePlanRequestWithVendor(planRequestId, vendorId)
			;
	return false;
}
}
