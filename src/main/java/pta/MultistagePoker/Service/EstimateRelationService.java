package pta.MultistagePoker.Service;

import java.util.List;

import pta.MultistagePoker.dbEntities.EstimateRelation;

public interface EstimateRelationService {

	List<EstimateRelation> getAll();

	void postNew(EstimateRelation bs);

}
