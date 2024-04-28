package pta.MultistagePoker;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import pta.MultistagePoker.Service.UserService;
import pta.MultistagePoker.Service.UserServiceImpl;
import pta.MultistagePoker.dbEntities.User;

@SpringBootTest
class MultistagePokerApplicationTests {

	@Test
	void contextLoads() {
	}

	
	@Test
	void userGet() {
		UserService srv=new UserServiceImpl();
		
		List<User> usr= srv.getAll();
		System.out.format("\nAnzahl User: %d", usr.size());
	}
	
}
