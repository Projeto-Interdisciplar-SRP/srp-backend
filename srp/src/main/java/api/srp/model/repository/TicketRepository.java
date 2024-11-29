package api.srp.model.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import api.srp.model.entity.Ticket;
import api.srp.model.entity.Travel;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String> {
	
	public List<Ticket> findByIdUsuario (String idUser);
	
}
