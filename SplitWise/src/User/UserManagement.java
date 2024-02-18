package User;

import java.util.HashMap;
import java.util.Map;

public class UserManagement {
    private static UserManagement userManagement;
    public Map<String, User> userMap = new HashMap<>();
    public User getUser(String userId){
        return userMap.get(userId);
    }
    public Map<String, User> getUserMap() {
        return userMap;
    }
    public void addUser(User user) {
        userMap.put(user.getUserId(),user);
    }
    public void deleteUser(String userId) {
        userMap.remove(userId);
    }

    public static UserManagement getInstance(){
        if(userManagement == null) {
            userManagement = new UserManagement();
        }
        return userManagement;
    }

}
