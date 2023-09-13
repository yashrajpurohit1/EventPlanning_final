package in.co.hsbc.onlineEventPlanner.service.impl;

import java.util.List;

import in.co.hsbc.onlineEventPlanner.dao.QuotationDao;
import in.co.hsbc.onlineEventPlanner.model.Quotation;
import in.co.hsbc.onlineEventPlanner.service.QuotationService;

public class QuotationServiceImpl implements QuotationService{
private QuotationDao quotationDao;
public QuotationServiceImpl( QuotationDao quotationDao) {
	this.quotationDao=quotationDao;
}
@Override
public Quotation createQuotation(Quotation quotation) {
	// TODO Auto-generated method stub
	Quotation quotation_=quotationDao.createQuotation(quotation);
	return quotation_;
}
@Override
public Quotation getQuotationById(int id) {
	// TODO Auto-generated method stub
	Quotation quotationById=quotationDao.getQuotationById(id);
	return quotationById;
}
@Override
public List<Quotation> getAllQuotations() {
	// TODO Auto-generated method stub
	List<Quotation> quotation=quotationDao.getAllQuotations();
	return quotation;
}
@Override
public void updateQuotation(Quotation quotation) {
	// TODO Auto-generated method stub
	quotationDao.updateQuotation(quotation);
	
}
@Override
public void deleteQuotation(int id) {
	// TODO Auto-generated method stub
	quotationDao.deleteQuotation(id);
	
}
@Override
public void updateQuotationStatus(int id, String newStatus) {
	// TODO Auto-generated method stub
	quotationDao.updateQuotationStatus(id, newStatus);
}
}
