package by.prist.dao;

import by.prist.entity.Address;
import by.prist.entity.Telephone;
import by.prist.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final String URL = "jdbc:postgresql://localhost:5432/clients";
    private final String USER = "postgres";
    private final String PASSWORD = "root";
    Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

    public UserDaoImpl() throws SQLException {
    }

    @Override
    public void save(User user) throws SQLException {
        try {

            connection.setAutoCommit(false);

            String sql = "insert into users.telephones values (default,?) returning telephone_id";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getTelephone().getNumber());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int telephoneId = resultSet.getInt(1);

            String sql2 = "insert into users.address values (default,?) returning address_id";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
            preparedStatement1.setString(1, user.getAddress().getAddress());
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();
            int addressId = resultSet1.getInt(1);

            String sql3 = "insert into  users.client values (default,?,?,?,?)";
            PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
            preparedStatement3.setString(1, user.getLogin());
            preparedStatement3.setString(2, user.getPassword());
            preparedStatement3.setInt(3, telephoneId);
            preparedStatement3.setInt(4, addressId);
            preparedStatement3.execute();
            connection.commit();
        } catch (SQLException throwables) {
            connection.rollback();
            throwables.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }


    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            String sql = """
                    SELECT * FROM users.client c 
                    left join users.user_address ua on c.id=ua.user_address_id
                    left join users.address a on a.address_id=ua.address_id
                    left join users.user_telephones ut on c.id=ut.user_telephone_id
                    left join users.telephones t on t.telephone_id=ut.telephone_id""";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        new Telephone(resultSet.getInt("telephone_id"), resultSet.getString("number")),
                        new Address(resultSet.getInt("address_id"), resultSet.getString("address"))
                );
                userList.add(user);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return userList;
    }
}