import by.prist.dao.UserDaoImpl;
import by.prist.entity.Address;
import by.prist.entity.Telephone;
import by.prist.entity.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDaoImpl dao = new UserDaoImpl();

        User user1 = new User("example1","password1",new Telephone("111"),new Address("street1"));
        User user2 = new User("example2","password2",new Telephone("222"),new Address("street2"));
        User user3 = new User("example3","password3",new Telephone("333"),new Address("street3"));

        dao.save(user1);
        dao.save(user2);
        dao.save(user3);

//        List<User>getAllUsers=dao.getAll();
//        System.out.println(getAllUsers);

    }
}
