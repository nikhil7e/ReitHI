package is.hi.hbv501g.reithi.Services;

import is.hi.hbv501g.reithi.Persistence.Entities.User;

import java.util.List;

public interface UserService {
    User save(User user);
    void delete(User user);
    List<User> findAll();
    User findByUserName(String userName);
    User findByID(long ID);
    User login(User user);
}