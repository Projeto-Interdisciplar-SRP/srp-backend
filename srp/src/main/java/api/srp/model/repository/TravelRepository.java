package api.srp.model.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import api.srp.model.entity.Travel;

@Repository
public interface TravelRepository extends MongoRepository<Travel, String> {
	
	List<Travel> findByIdIngresso(String idIngresso); 
	
}
