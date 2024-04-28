package pta.MultistagePoker.Service;

import java.util.List;

import pta.MultistagePoker.dbEntities.Estimate;

public interface EstimateService {

	List<Estimate> getAll();

	void postNew(Estimate bs);

	
	double calcMittelwert(int idTicket);
	double calcKonsistenz(int idTicket);
	
}
