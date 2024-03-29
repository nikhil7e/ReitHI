package is.hi.hbv501g.reithi.Persistence.Repositories;

import is.hi.hbv501g.reithi.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);
    void delete(User user);
    List<User> findAll();
    User findByUserName(String userName);
    User findByID(long ID);
}