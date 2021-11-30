package business.services;

import business.entities.User;
import business.persistence.Database;
import business.persistence.UserMapper;
import business.exceptions.UserException;

public class UserFacade {
    UserMapper userMapper;

    public UserFacade(Database database) {
        userMapper = new UserMapper(database);
    }

    public User login(String email, String password) throws UserException {
        return userMapper.login(email, password);
    }

    public boolean emailExist(String email) throws UserException {
        return userMapper.emailExist(email);
    }

    public User createUser(String email, String password, String name, String address, int zip, String city, String phoneNumber) throws UserException {
        User user = new User(email, password, "customer", name, address, zip, city, phoneNumber);
        userMapper.createUser(user);
        return user;
    }

}
