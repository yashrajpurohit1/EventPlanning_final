package in.co.hsbc.onlineEventPlanner.service;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.model.Packagee;
import in.co.hsbc.onlineEventPlanner.model.PlanRequest;
import in.co.hsbc.onlineEventPlanner.model.Quotation;

public interface VendorService {
	boolean addPackage(Packagee vendorPackage);
	List<PlanRequest> getAllUserPlanRequests();
	Quotation createQuotation(Quotation quotation);
}
