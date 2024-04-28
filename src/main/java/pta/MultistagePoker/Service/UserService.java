package pta.MultistagePoker.Service;

import java.util.List;

import pta.MultistagePoker.dbEntities.User;

public interface UserService {

	List<User> getAll();

	User findByName(String name);
	
	void postNew(User us);

	void updateUser(User us);

}
