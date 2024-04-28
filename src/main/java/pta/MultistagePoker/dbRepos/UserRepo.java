package pta.MultistagePoker.dbRepos;

import org.springframework.data.repository.CrudRepository;

import pta.MultistagePoker.dbEntities.User;

public interface UserRepo extends CrudRepository<User, Integer> {

}
