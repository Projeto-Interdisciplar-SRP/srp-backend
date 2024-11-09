package api.srp.model.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import api.srp.model.entity.User;

public interface UserRepository extends MongoRepository<User, String>{
	
	public User findByEmail(String email);
	List<User> findByAdm(int adm);
	
}
