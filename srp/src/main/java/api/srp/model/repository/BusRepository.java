package api.srp.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import api.srp.model.entity.Bus;

@Repository
public interface BusRepository extends MongoRepository<Bus, String> {
}