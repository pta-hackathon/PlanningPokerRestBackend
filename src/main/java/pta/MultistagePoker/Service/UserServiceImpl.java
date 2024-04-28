package pta.MultistagePoker.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import pta.MultistagePoker.dbEntities.User;
import pta.MultistagePoker.dbRepos.UserRepo;

@Repository
@Transactional
@Service
public class UserServiceImpl implements UserService {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	UserRepo repo;
	
	@Override
	public List<User> getAll() {
		List<User> erg = new ArrayList<>();
		for (User e: repo.findAll()) {
			erg.add(e);
		}
		return erg;
	}

	@Override
	public void postNew(User us) {
		repo.save(us);
	}

	@Override
	public void updateUser(User us) {
		repo.save(us);
	}

	@Override
	public User findByName(String name) {
		for (User u: this.getAll()) {
			if (u.getName().equalsIgnoreCase(name)) {
				return u;
			}
		}		
		return null;
	}

}
