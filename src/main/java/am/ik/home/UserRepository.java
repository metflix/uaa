package am.ik.home;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT x FROM User x WHERE x.userId IN (:ids) ORDER BY x.username")
    List<User> findByIds(@Param("ids") List<String> ids);
}
