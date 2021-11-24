package by.prist.entity;

public class User {
    private int id;
    private String login;
    private String password;
    private Telephone telephone;
    private Address address;

    public User(int id, String login, String password, Telephone telephone, Address address) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.telephone = telephone;
        this.address = address;
    }

    public User(String login, String password, Telephone telephone, Address address) {
        this.login = login;
        this.password = password;
        this.telephone = telephone;
        this.address = address;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", telephone=" + telephone +
                ", address=" + address +
                '}';
    }
}
