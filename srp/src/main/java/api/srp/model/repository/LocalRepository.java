package api.srp.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import api.srp.model.entity.Local;

@Repository
public interface LocalRepository extends MongoRepository<Local, String> {
	
	public boolean existsById(String id);

	
}
