package api.srp.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import api.srp.model.entity.Place;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {
	
	public boolean existsById(String id);
	
}