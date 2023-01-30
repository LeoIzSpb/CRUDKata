package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.DAO.UserDao;
import web.model.User;

import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserById(int id) {
        return  userDao.getUserById(id);
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void removeUser( int id) {
        userDao.removeUser(id);
    }

    public void updateUser(@Valid User user) {
        userDao.updateUser(user);
    }

}
