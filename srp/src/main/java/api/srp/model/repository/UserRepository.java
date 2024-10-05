package api.srp.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import api.srp.model.entity.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	public User findByEmail(String email);
	
}
