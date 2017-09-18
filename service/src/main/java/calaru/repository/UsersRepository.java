package calaru.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import calaru.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{

}
