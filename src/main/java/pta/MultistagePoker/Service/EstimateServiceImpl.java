package pta.MultistagePoker.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import pta.MultistagePoker.dbEntities.Estimate;
import pta.MultistagePoker.dbRepos.EstimateRepo;

@Repository
@Transactional
@Service
public class EstimateServiceImpl implements EstimateService {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	EstimateRepo repo;
	
	@Override
	public List<Estimate> getAll() {
		List<Estimate> erg = new ArrayList<>();
		for (Estimate e: repo.findAll()) {
			erg.add(e);
		}
		return erg;
	}

	@Override
	public void postNew(Estimate bs) {
		repo.save(bs);
	}

	@Override
	public double calcMittelwert(int idTicket) {
		double rc=0.0;
		int cnt=0;
		for (Estimate est: repo.findAll()) {
			if (est.getIdTicket()==idTicket) {
				double wrt=(est.getMaxVal()+est.getMinVal()) / 2;
				rc+=wrt;
				cnt++;
			}
		}
		return (cnt==0) ? 0 : rc/cnt;		
	}

	@Override
	public double calcKonsistenz(int idTicket) {
		double mitte=calcMittelwert(idTicket);
		double sumabw=0.0;
		for (Estimate est: repo.findAll()) {
			if (est.getIdTicket()==idTicket) {
				double wrt=(est.getMaxVal()+est.getMinVal()) / 2;
				double diff=Math.abs(mitte-wrt);
				sumabw+=diff*diff;
			}
		}
		return Math.sqrt(sumabw);		
	}

	@Override
	public void delete(int userid, int ticketid) {
		for (Estimate est: repo.findAll()) {
			if (est.getIdUser()==userid && est.getIdTicket()==ticketid) {
				repo.deleteById(est.getId());
			}
		}		
	}

}
