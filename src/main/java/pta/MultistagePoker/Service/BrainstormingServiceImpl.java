package pta.MultistagePoker.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import pta.MultistagePoker.dbEntities.Brainstorming;
import pta.MultistagePoker.dbRepos.BrainstormingRepo;

@Repository
@Transactional
@Service
public class BrainstormingServiceImpl implements BrainstormingService {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	BrainstormingRepo repo;
	
	@Override
	public List<Brainstorming> getAll() {
		List<Brainstorming> erg = new ArrayList<>();
		for (Brainstorming e: repo.findAll()) {
			erg.add(e);
		}
		return erg;
	}

	@Override
	public void postNew(Brainstorming bs) {
		repo.save(bs);
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

}
