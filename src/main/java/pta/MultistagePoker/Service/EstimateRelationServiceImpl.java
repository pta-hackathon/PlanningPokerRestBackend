package pta.MultistagePoker.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import pta.MultistagePoker.dbEntities.EstimateRelation;
import pta.MultistagePoker.dbRepos.EstimateRelationRepo;

@Repository
@Transactional
@Service
public class EstimateRelationServiceImpl implements EstimateRelationService {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	EstimateRelationRepo repo;
	
	@Override
	public List<EstimateRelation> getAll() {
		List<EstimateRelation> erg = new ArrayList<>();
		for (EstimateRelation e: repo.findAll()) {
			erg.add(e);
		}
		return erg;
	}

	@Override
	public void postNew(EstimateRelation bs) {
		repo.save(bs);
	}

}
