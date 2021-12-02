package business.entities;

public class User {

    private int id; // just used to demo retrieval of autogen keys in UserMapper
    private String email;
    private String password; // Should be hashed and secured
    private String role;
    private String name;
    private String address;
    private int zip;
    private String city;
    private String phoneNumber;

    public User(String email, String password, String role, String name, String address, int zip, String city, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public User(int id, String email, String role, String name, String address, int zip, String city, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
