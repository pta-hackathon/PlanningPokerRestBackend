package pta.MultistagePoker.Service;

import java.util.List;

import pta.MultistagePoker.dbEntities.Brainstorming;

public interface BrainstormingService {

	List<Brainstorming> getAll();

	void postNew(Brainstorming bs);

	void saveall(List<Brainstorming> lst);

	void deleteAll();

}
