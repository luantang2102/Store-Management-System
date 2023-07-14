package com.example.storemanagementsystemfx.service;

import com.example.storemanagementsystemfx.dao.itface.IUserDao;
import com.example.storemanagementsystemfx.model.User;

import java.util.List;

public class UserService {
    private IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(String userName, String userPass) {
        return userDao.get(userName, userPass);
    }

    public boolean validate(String username) {
        for(User user : getAll()) {
            if(user.getUserName().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public List<User> getAll() {
        return userDao.getAll();
    }

    public int save(User user) {
        return userDao.save(user);
    }
}
