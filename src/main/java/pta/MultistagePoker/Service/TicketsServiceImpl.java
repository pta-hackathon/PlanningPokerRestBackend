package pta.MultistagePoker.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import pta.MultistagePoker.dbEntities.Tickets;
import pta.MultistagePoker.dbRepos.TicketsRepo;

@Repository
@Transactional
@Service
public class TicketsServiceImpl implements TicketsService {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	TicketsRepo repo;
	
	@Override
	public List<Tickets> getAll() {
		List<Tickets> erg = new ArrayList<>();
		for (Tickets e: repo.findAll()) {
			erg.add(e);
		}
		return erg;
	}


}
