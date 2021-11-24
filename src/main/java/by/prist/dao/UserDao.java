package by.prist.dao;

import by.prist.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void save (User user) throws SQLException;
    List<User> getAll() throws SQLException;
}
