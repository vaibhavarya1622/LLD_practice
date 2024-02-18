package command;

import User.User;
import User.UserManagement;

public class AddUserCommand implements Command{
    private final UserManagement userManagement;
    public AddUserCommand(UserManagement userManagement) {
        this.userManagement = userManagement;
    }
    @Override
    public void execute(String[] args) {
        User user = new User();
        user.setUserId(args[1]);
        user.setName(args[2]);
        user.setPhone(args[3]);
        user.setEmail(args[4]);
        userManagement.addUser(user);
    }
}
