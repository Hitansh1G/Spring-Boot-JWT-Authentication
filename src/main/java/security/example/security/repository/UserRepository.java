package security.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.example.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

}
