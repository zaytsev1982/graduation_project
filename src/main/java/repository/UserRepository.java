package repository;

import java.util.Optional;
import model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u join fetch u.roles where u.name = :name")
    Optional<User> findByNameAndRoles(@Param("name") String userName);

}
