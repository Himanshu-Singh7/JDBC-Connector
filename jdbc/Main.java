package jdbc;

public class Main {
    public static void main(String[] args) {
        UserDAO user = new UserDAO();
        user.createUserTable();
        user.createUser("Himanshu Singh" , 17);
        user.readUser();
    }
}
