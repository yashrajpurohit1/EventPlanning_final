package in.co.hsbc.onlineEventPlanner.service;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.model.PlanRequest;

public interface PlanRequestService {
	PlanRequest createPlanRequest(PlanRequest planRequest);

	PlanRequest getPlanRequestById(int id);

	List<PlanRequest> getAllPlanRequests();

	void updatePlanRequest(PlanRequest planRequest);

	void deletePlanRequest(int id);

	boolean associatePlanRequestWithVendor(int planRequestId, int vendorId);
}
