package com.example.storemanagementsystemfx.dao.itface;

import com.example.storemanagementsystemfx.model.User;
import java.util.List;

public interface IUserDao {
    List<User> getAll();

    User get(String userName, String userPass);

    int save(User user);

    int update(User user);

    int delete(String userId);
}
