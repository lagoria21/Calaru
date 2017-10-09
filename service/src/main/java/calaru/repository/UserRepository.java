package calaru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import calaru.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    public User findByEmail(String email);
    
    public User findById(int id);
    
    
}