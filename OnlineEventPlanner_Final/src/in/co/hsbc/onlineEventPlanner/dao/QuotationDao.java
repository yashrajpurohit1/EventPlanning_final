package in.co.hsbc.onlineEventPlanner.dao;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.model.Quotation;

public interface QuotationDao {
	Quotation createQuotation(Quotation quotation);

	Quotation getQuotationById(int id);

	List<Quotation> getAllQuotations();

	void updateQuotation(Quotation quotation);

	void deleteQuotation(int id);

	void updateQuotationStatus(int id, String newStatus);
}
