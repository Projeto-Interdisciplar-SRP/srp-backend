package api.srp.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import api.srp.model.entity.Ticket;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
	
}
