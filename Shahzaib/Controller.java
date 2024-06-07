import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<User> users;

    public Controller() {
        users = new ArrayList<>();
    }

    public void registerUser(String name, String email, String password, String role) {
        User newUser = new User(name, email, password, role);
        users.add(newUser);
    }

    public void printAllUsers() {
        System.out.println("All Registered Users:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void registerSeller(String name, String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerSeller'");
    }

